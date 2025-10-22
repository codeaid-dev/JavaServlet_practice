package model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;

public class RsDBUtil {
  private static String DB_PATH = null;

  static {
    try {
      Class.forName("org.sqlite.JDBC"); // JDBCドライバ登録
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  // ----------------------------------------
  // 初期処理（DBファイル存在チェック+テーブル作成+初期ユーザー追加）
  // ----------------------------------------
  public static void init(ServletContext context) {
    System.out.println("[RsDBUtil] start init...");

    try {
      String dirPath = context.getRealPath("/WEB-INF/db");
      File dir = new File(dirPath);
      if (!dir.exists()) {
        dir.mkdirs(); // dbフォルダを作成
        System.out.println("[RsDBUtil] Created directory: " + dir.getAbsolutePath());
      }

      File dbFile = new File(dir, "reservation.db");
      DB_PATH = "jdbc:sqlite:" + dbFile.getAbsolutePath();
      System.out.println("[RsDBUtil] DB Path = " + DB_PATH);

    } catch (Exception e) {
      e.printStackTrace();
    }

    createTables(); // テーブル作成
    insertDefaultUsers(); // 初期ユーザー
    System.out.println("[RsDBUtil] finish initialize");
  }

  // ----------------------------------------
  // コネクション取得
  // ----------------------------------------
  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(DB_PATH);
  }

  // ----------------------------------------
  // テーブル作成
  // ----------------------------------------
  private static void createTables() {
    String createUsers = """
        CREATE TABLE IF NOT EXISTS users (
            username TEXT PRIMARY KEY,
            password TEXT NOT NULL,
            role TEXT NOT NULL CHECK(role IN ('admin','user'))
        );
        """;
    String createReservations = """
        CREATE TABLE IF NOT EXISTS reservation (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            date TEXT NOT NULL,
            time TEXT NOT NULL,
            note TEXT,
            username TEXT NOT NULL,
            FOREIGN KEY(username) REFERENCES users(username)
        );
        """;
    try (Connection con = getConnection(); Statement st = con.createStatement()) {
      st.execute(createUsers);
      st.execute(createReservations);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to create tables", e);
    }
  }

  // ----------------------------------------
  // 初期ユーザーを挿入（存在しなければ）
  // ----------------------------------------
  private static void insertDefaultUsers() {

    List<RsUser> defaults = Arrays.asList(
        new RsUser("admin", "admin123", "admin"),
        new RsUser("user1", "userpass", "user"),
        new RsUser("user2", "userpass", "user"));

    String sqlCheck = "SELECT COUNT(*) FROM users WHERE username = ?";
    String sqlInsert = "INSERT INTO users(username,password,role) VALUES(?,?,?)";
    try (Connection con = getConnection()) {
      for (RsUser u : defaults) {
        try (PreparedStatement ps = con.prepareStatement(sqlCheck)) {
          ps.setString(1, u.getUsername());
          try (ResultSet rs = ps.executeQuery()) {
            if (rs.next() && rs.getInt(1) == 0) {
              try (PreparedStatement ins = con.prepareStatement(sqlInsert)) {
                ins.setString(1, u.getUsername());
                ins.setString(2, u.getPassword());
                ins.setString(3, u.getRole());
                ins.executeUpdate();
              }
            }
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // ----------------------------------------
  // ユーザー認証
  // 成功時に Map {username, role} を返す
  // ----------------------------------------
  public static Map<String, String> findUser(String username, String password) {
    String sql = "SELECT username, role FROM users WHERE username=? AND password=?";
    try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
      ps.setString(1, username);
      ps.setString(2, password);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          Map<String, String> m = new HashMap<>();
          m.put("username", rs.getString("username"));
          m.put("role", rs.getString("role"));
          return m;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  // ----------------------------------------
  // 全予約取得
  // ----------------------------------------
  public static List<Map<String, String>> getAllReservations() {
    List<Map<String, String>> list = new ArrayList<>();
    String sql = "SELECT * FROM reservation ORDER BY date, time";
    try (Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        Map<String, String> r = new HashMap<>();
        r.put("id", String.valueOf(rs.getInt("id")));
        r.put("name", rs.getString("name"));
        r.put("date", rs.getString("date"));
        r.put("time", rs.getString("time"));
        r.put("note", rs.getString("note"));
        r.put("username", rs.getString("username"));
        list.add(r);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  // ----------------------------------------
  // IDによる予約取得
  // ----------------------------------------
  public static Map<String, String> getReservationById(int id) {
    String sql = "SELECT * FROM reservation WHERE id = ?";
    try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          Map<String, String> r = new HashMap<>();
          r.put("id", String.valueOf(rs.getInt("id")));
          r.put("name", rs.getString("name"));
          r.put("date", rs.getString("date"));
          r.put("time", rs.getString("time"));
          r.put("note", rs.getString("note"));
          r.put("username", rs.getString("username"));
          return r;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  // ----------------------------------------
  // 重複チェック（同じ date & time があるか）。excludeId があればその id を除外。
  // ----------------------------------------
  public static boolean isDuplicate(String date, String time, Integer excludeId) {
    String sql = "SELECT COUNT(*) FROM reservation WHERE date = ? AND time = ?";
    if (excludeId != null)
      sql += " AND id != ?";
    try (Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
      ps.setString(1, date);
      ps.setString(2, time);
      if (excludeId != null)
        ps.setInt(3, excludeId);
      try (ResultSet rs = ps.executeQuery()) {
        return rs.next() && rs.getInt(1) > 0;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return true; // エラー時は安全のため重複扱い
  }

  // ----------------------------------------
  // 予約追加（成功:true, 重複or失敗:false)
  // ----------------------------------------
  public static boolean insertReservation(String name, String date, String time, String note, String username) {
    if (isDuplicate(date, time, null))
      return false;
    String sql = "INSERT INTO reservation(name,date,time,note,username) VALUES(?,?,?,?,?)";
    try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
      ps.setString(1, name);
      ps.setString(2, date);
      ps.setString(3, time);
      ps.setString(4, note);
      ps.setString(5, username);
      ps.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  // ----------------------------------------
  // 予約更新（excludeId を使って重複除外）
  // ----------------------------------------
  public static boolean updateReservation(int id, String name, String date, String time, String note) {
    if (isDuplicate(date, time, id))
      return false;
    String sql = "UPDATE reservation SET name=?, date=?, time=?, note=? WHERE id=?";
    try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
      ps.setString(1, name);
      ps.setString(2, date);
      ps.setString(3, time);
      ps.setString(4, note);
      ps.setInt(5, id);
      ps.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  // ----------------------------------------
  // 予約削除（管理者のみが呼ぶ想定。RsDBUtil は権限チェックしない）
  // ----------------------------------------
  public static boolean deleteReservation(int id) {
    String sql = "DELETE FROM reservation WHERE id = ?";
    try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
      ps.setInt(1, id);
      int affected = ps.executeUpdate();
      return affected > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
}
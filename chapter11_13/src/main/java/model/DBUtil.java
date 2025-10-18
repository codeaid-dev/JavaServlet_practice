package model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletContext;

public class DBUtil {
  private static String DB_PATH = null;

  static {
    try {
      Class.forName("org.sqlite.JDBC"); // JDBCドライバ登録
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  // ----------------------------------------
  // ① 初期処理（DBファイル存在チェック＋テーブル作成）
  // ----------------------------------------
  public static void init(ServletContext context) {
    System.out.println("[DBUtil] start init...");

    try {
      // 本番環境
      String dirPath = context.getRealPath("/WEB-INF/db");
      File dir = new File(dirPath);
      if (!dir.exists()) {
        dir.mkdirs(); // dbフォルダを作成
        System.out.println("[DBUtil] Created directory: " + dir.getAbsolutePath());
      }

      File dbFile = new File(dir, "bbs.db");
      DB_PATH = "jdbc:sqlite:" + dbFile.getAbsolutePath();
      System.out.println("DB Path = " + DB_PATH);

    } catch (Exception e) {
      e.printStackTrace();
    }

    createTable(); // テーブル作成
    System.out.println("[DBUtil] finish initialize");
  }

  // ----------------------------------------
  // ② テーブル作成
  // ----------------------------------------
  public static void createTable() {
    String sql = """
            CREATE TABLE IF NOT EXISTS posts (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                message TEXT NOT NULL,
                created_at TEXT DEFAULT (datetime('now', 'localtime'))
            );
        """;

    try (Connection conn = getConnection();
        Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(sql);
      System.out.println("[DBUtil] create posts table");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // ----------------------------------------
  // ③ コネクション取得
  // ----------------------------------------
  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(DB_PATH);
  }

  // ----------------------------------------
  // ④ 投稿一覧取得
  // ----------------------------------------
  public static java.util.List<Post> getAllPosts() {
    java.util.List<Post> list = new java.util.ArrayList<>();
    String sql = "SELECT * FROM posts ORDER BY id DESC";

    try (Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {

      while (rs.next()) {
        list.add(new Post(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("message"),
            rs.getString("created_at")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  // ----------------------------------------
  // ⑤ 投稿追加
  // ----------------------------------------
  public static boolean insertPost(String name, String message) {
    String sql = "INSERT INTO posts (name, message) VALUES (?, ?)";
    try (Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, name);
      ps.setString(2, message);
      ps.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}

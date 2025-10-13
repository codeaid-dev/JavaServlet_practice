package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
  private static String DB_PATH = null;

  static {
    try {
      Class.forName("org.sqlite.JDBC");
      System.out.println("SQLite JDBC Driver loaded.");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  // 初期化(プロジェクト直下のファイルパスを作成)
  public static void init(String realPath) {
    java.io.File file = new java.io.File(realPath);
    file.getParentFile().mkdirs();
    DB_PATH = "jdbc:sqlite:" + file.getAbsolutePath();
    System.out.println("DB Path: " + DB_PATH);
  }

  // 接続を取得
  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(DB_PATH);
  }

  // テーブル作成
  public static void createTable() {
    String sql = """
        CREATE TABLE IF NOT EXISTS users (
          id INTEGER PRIMARY KEY AUTOINCREMENT,
          name TEXT NOT NULL,
          score INTEGER NOT NULL
        )
        """;
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
      stmt.execute(sql);
      System.out.println("Table created or already exists.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // データ挿入
  public static void insertData() {
    int[] ids = { 1, 2, 3 };
    String[] names = { "Yamada", "Tanaka", "Suzuki" };
    int[] scores = { 85, 79, 63 };
    try (Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users VALUES(?, ?, ?)")) {
      for (int i = 0; i < 3; i++) {
        pstmt.setInt(1, ids[i]);
        pstmt.setString(2, names[i]);
        pstmt.setInt(3, scores[i]);
        pstmt.executeUpdate();
      }
      System.out.println("Sample data inserted.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // 70点以上選択
  public static List<User> selectData() {
    List<User> list = new ArrayList<>();
    String sql = "SELECT * FROM users WHERE score >= ?";
    try (Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, 70);
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          list.add(new User(
              rs.getInt("id"),
              rs.getString("name"),
              rs.getInt("score")));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  // テーブル削除
  public static void deleteAll() {
    String sql = "DELETE FROM users";
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(sql);
      System.out.println("All data deleted.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}

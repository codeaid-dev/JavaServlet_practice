package model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletContext;

public class DBUtil {
  private static String dbUrl;

  static {
    try {
      Class.forName("org.sqlite.JDBC"); // JDBCドライバ登録
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private DBUtil() {
  }

  public static void init(ServletContext context)
      throws Exception {
    System.out.println("[StudyRecord] start DBUtil init...");
    String dbPath = context.getRealPath("/WEB-INF/db");
    File dir = new File(dbPath);
    if (!dir.exists()) {
      dir.mkdirs();
      System.out.println("[StudyRecord] Created directory: " + dir.getAbsolutePath());
    }
    String dbFile = dbPath + "/study.db";
    dbUrl = "jdbc:sqlite:" + dbFile;
    createTables();
    System.out.println("[StudyRecord] finish DBUtil initialize: " + dbFile);
  }

  public static Connection getConnection()
      throws SQLException {

    if (dbUrl == null) {
      throw new SQLException(
          "データベースが初期化されていません");
    }

    return DriverManager.getConnection(dbUrl);
  }

  private static void createTables()
      throws SQLException {

    String sql = """
        CREATE TABLE IF NOT EXISTS study_records (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            subject TEXT NOT NULL,
            study_date TEXT NOT NULL,
            minutes INTEGER NOT NULL,
            memo TEXT,
            created_at TEXT DEFAULT CURRENT_TIMESTAMP
        )
        """;

    try (Connection con = getConnection();
        Statement stmt = con.createStatement()) {

      stmt.executeUpdate(sql);
    }
  }
}

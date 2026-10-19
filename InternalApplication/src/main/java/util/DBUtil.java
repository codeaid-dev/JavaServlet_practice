package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletContext;

public class DBUtil {
  private static String dbUrl;

  private DBUtil() {
  }

  public static void init(ServletContext context) throws Exception {
//    System.out.println("【DBUtil.init】開始");
//    System.out.println("【DBUtil.init】classLoader = "
//        + DBUtil.class.getClassLoader());
//    System.out.println("【DBUtil.init】class = "
//        + DBUtil.class);
//    System.out.println("【DBUtil.init】classCodeSource = "
//        + DBUtil.class.getProtectionDomain()
//            .getCodeSource().getLocation());
    String dbPath = context.getRealPath("/WEB-INF/db");
    if (dbPath == null) {
      throw new IllegalStateException("データベースの保存先を取得できません。");
    }
    File dbDir = new File(dbPath);
    if (!dbDir.exists() && !dbDir.mkdirs()) {
      throw new IllegalStateException("データベース保存先を作成できません。");
    }
    File dbFile = new File(dbDir, "application.db");
    dbUrl = "jdbc:sqlite:" + dbFile.getAbsolutePath();
    Class.forName("org.sqlite.JDBC");
//    System.out.println("【DBUtil.init】dbUrl = " + dbUrl);
    createTables();
//    System.out.println("【DBUtil.init】完了");
  }

  public static Connection getConnection() throws SQLException {
//    System.out.println("【DBUtil.getConnection】classLoader = "
//        + DBUtil.class.getClassLoader());
//    System.out.println("【DBUtil.getConnection】class = "
//        + DBUtil.class);
//    System.out.println("【DBUtil.getConnection】classCodeSource = "
//        + DBUtil.class.getProtectionDomain()
//            .getCodeSource().getLocation());
//    System.out.println("【DBUtil.getConnection】dbUrl = " + dbUrl);
    if (dbUrl == null) {
      throw new SQLException("データベースが初期化されていません。");
    }
    Connection conn = DriverManager.getConnection(dbUrl);
    try (Statement stmt = conn.createStatement()) {
      stmt.execute("PRAGMA foreign_keys = ON");
    }
    return conn;
  }

  private static void createTables()
      throws SQLException {
    try (Connection conn = getConnection();
        Statement stmt = conn.createStatement()) {
      stmt.execute("""
          CREATE TABLE IF NOT EXISTS users (
              id INTEGER PRIMARY KEY AUTOINCREMENT,
              username TEXT NOT NULL UNIQUE,
              password TEXT NOT NULL,
              display_name TEXT NOT NULL,
              role TEXT NOT NULL
                  CHECK (role IN ('EMPLOYEE', 'APPROVER', 'ADMIN')),
              active INTEGER NOT NULL DEFAULT 1
                  CHECK (active IN (0, 1)),
              created_at TEXT NOT NULL
                  DEFAULT CURRENT_TIMESTAMP
          )
          """);

      stmt.execute("""
          CREATE TABLE IF NOT EXISTS application_types (
              id INTEGER PRIMARY KEY AUTOINCREMENT,
              type_name TEXT NOT NULL UNIQUE,
              active INTEGER NOT NULL DEFAULT 1
                  CHECK (active IN (0, 1))
          )
          """);

      stmt.execute("""
          CREATE TABLE IF NOT EXISTS applications (
              id INTEGER PRIMARY KEY AUTOINCREMENT,
              applicant_id INTEGER NOT NULL,
              approver_id INTEGER NOT NULL,
              application_type_id INTEGER NOT NULL,
              title TEXT NOT NULL,
              reason TEXT NOT NULL,
              amount INTEGER,
              status TEXT NOT NULL DEFAULT 'PENDING'
                  CHECK (status IN (
                      'PENDING',
                      'APPROVED',
                      'REJECTED',
                      'WITHDRAWN'
                  )),
              applied_at TEXT NOT NULL
                  DEFAULT CURRENT_TIMESTAMP,
              updated_at TEXT NOT NULL
                  DEFAULT CURRENT_TIMESTAMP,

              FOREIGN KEY (applicant_id)
                  REFERENCES users(id),

              FOREIGN KEY (approver_id)
                  REFERENCES users(id),

              FOREIGN KEY (application_type_id)
                  REFERENCES application_types(id)
          )
          """);

      stmt.execute("""
          CREATE TABLE IF NOT EXISTS approval_records (
              id INTEGER PRIMARY KEY AUTOINCREMENT,
              application_id INTEGER NOT NULL,
              approver_id INTEGER NOT NULL,
              action TEXT NOT NULL
                  CHECK (action IN (
                      'APPROVED',
                      'REJECTED'
                  )),
              comment TEXT,
              processed_at TEXT NOT NULL
                  DEFAULT CURRENT_TIMESTAMP,

              FOREIGN KEY (application_id)
                  REFERENCES applications(id),

              FOREIGN KEY (approver_id)
                  REFERENCES users(id)
          )
          """);

      // 読み込み(SELECT)を高速にするためINDEXを作成
      stmt.execute("""
          CREATE INDEX IF NOT EXISTS
              idx_applications_applicant
          ON applications(applicant_id)
          """);

      stmt.execute("""
          CREATE INDEX IF NOT EXISTS
              idx_applications_approver_status
          ON applications(approver_id, status)
          """);
    }
  }
}

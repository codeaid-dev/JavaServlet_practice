package model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;

public class DBUtil {
  private static String dbUrl;

  // call from listener
  public static void init(ServletContext context) {
    String dbDir = context.getRealPath("/WEB-INF/db");
    File dir = new File(dbDir);
    if (!dir.exists())
      dir.mkdirs();
    String path = new File(dir, "tasks.db").getAbsolutePath();
    dbUrl = "jdbc:sqlite:" + path;

    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    createTablesIfNotExist();
  }

  private static Connection getConnection() throws SQLException {
    if (dbUrl == null)
      throw new IllegalStateException("DBUtil not initialized.");
    return DriverManager.getConnection(dbUrl);
  }

  private static void createTablesIfNotExist() {
    String sql = """
        CREATE TABLE IF NOT EXISTS task (
          id INTEGER PRIMARY KEY AUTOINCREMENT,
          title TEXT NOT NULL,
          description TEXT,
          due_date TEXT,
          status TEXT NOT NULL,
          created_at TEXT DEFAULT (datetime('now','localtime'))
        );
        """;
    try (Connection con = getConnection(); Statement st = con.createStatement()) {
      st.execute(sql);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to create tables", e);
    }
  }

  // CRUD + move

  public static List<Map<String, String>> findAllByStatus(String status) {
    String sql = "SELECT id, title, description, due_date, status FROM task WHERE status = ? ORDER BY created_at DESC";
    List<Map<String, String>> list = new ArrayList<>();
    try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
      ps.setString(1, status);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          Map<String, String> m = new HashMap<>();
          m.put("id", String.valueOf(rs.getInt("id")));
          m.put("title", rs.getString("title"));
          m.put("description", rs.getString("description"));
          m.put("due_date", rs.getString("due_date"));
          m.put("status", rs.getString("status"));
          list.add(m);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public static Map<String, String> findById(int id) {
    String sql = "SELECT * FROM task WHERE id = ?";
    try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          Map<String, String> m = new HashMap<>();
          m.put("id", String.valueOf(rs.getInt("id")));
          m.put("title", rs.getString("title"));
          m.put("description", rs.getString("description"));
          m.put("due_date", rs.getString("due_date"));
          m.put("status", rs.getString("status"));
          return m;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static boolean insertTask(String title, String desc, String dueDate, String status) {
    String sql = "INSERT INTO task(title, description, due_date, status) VALUES(?,?,?,?)";
    try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
      ps.setString(1, title);
      ps.setString(2, desc);
      ps.setString(3, dueDate);
      ps.setString(4, status);
      ps.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static boolean updateTask(int id, String title, String desc, String dueDate, String status) {
    String sql = "UPDATE task SET title=?, description=?, due_date=?, status=? WHERE id=?";
    try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
      ps.setString(1, title);
      ps.setString(2, desc);
      ps.setString(3, dueDate);
      ps.setString(4, status);
      ps.setInt(5, id);
      return ps.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static boolean deleteTask(int id) {
    String sql = "DELETE FROM task WHERE id = ?";
    try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
      ps.setInt(1, id);
      return ps.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static boolean moveTask(int id, String newStatus) {
    String sql = "UPDATE task SET status = ? WHERE id = ?";
    try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
      ps.setString(1, newStatus);
      ps.setInt(2, id);
      return ps.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}

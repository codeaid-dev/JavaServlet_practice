package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletContext;

import model.User;
import util.DBUtil;

public class UserDAO {

  private final ServletContext context;

  public UserDAO(ServletContext context) {
    this.context = context;
  }

  /**
   * ユーザーIDとパスワードで認証する。
   */
  public User authenticate(String username, String password)
      throws SQLException {

    String sql = """
        SELECT id, username, display_name, role
        FROM users
        WHERE username = ?
          AND password = ?
          AND active = 1
        """;

    try (Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, username);
      pstmt.setString(2, password);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          return new User(
              rs.getInt("id"),
              rs.getString("username"),
              rs.getString("display_name"),
              rs.getString("role"));
        }
      }
    }

    return null;
  }

  /**
   * ユーザーIDからユーザーを取得する。
   */
  public User findByUsername(String username)
      throws SQLException {

    String sql = """
        SELECT id, username, display_name, role
        FROM users
        WHERE username = ?
          AND active = 1
        """;

    try (Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, username);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          return new User(
              rs.getInt("id"),
              rs.getString("username"),
              rs.getString("display_name"),
              rs.getString("role"));
        }
      }
    }

    return null;
  }

  /**
   * 全ユーザーを取得する。
   */
  public List<User> findAll() throws SQLException {

    List<User> users = new ArrayList<>();

    String sql = """
        SELECT id, username, display_name, role
        FROM users
        WHERE active = 1
        ORDER BY id
        """;

    try (Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {

      while (rs.next()) {
        users.add(new User(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("display_name"),
            rs.getString("role")));
      }
    }

    return users;
  }
}
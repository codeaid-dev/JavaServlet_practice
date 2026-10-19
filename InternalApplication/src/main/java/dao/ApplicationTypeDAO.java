package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletContext;
import model.ApplicationType;
import util.DBUtil;

public class ApplicationTypeDAO {

  private final ServletContext context;

  public ApplicationTypeDAO(ServletContext context) {
    this.context = context;
  }

  public List<ApplicationType> findAll() throws SQLException {

    List<ApplicationType> types = new ArrayList<>();

    String sql = """
        SELECT id, type_name
        FROM application_types
        WHERE active = 1
        ORDER BY id
        """;

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

      while (rs.next()) {
        ApplicationType type = new ApplicationType(
          rs.getInt("id"),
          rs.getString("type_name")
        );

        types.add(type);
      }
    }

    return types;
  }
}

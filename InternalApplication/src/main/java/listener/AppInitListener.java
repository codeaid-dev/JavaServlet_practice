package listener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import util.DBUtil;

@WebListener
public class AppInitListener implements ServletContextListener {

  public AppInitListener() {
  }

  public void contextInitialized(ServletContextEvent sce) {
    ServletContext context = sce.getServletContext();
    try {
//      System.out.println("【AppInitListener】初期化開始");
      DBUtil.init(context);
//      System.out.println("【AppInitListener】DBUtil.init完了");
      createInitialUsers(context);
//      System.out.println("【AppInitListener】初期ユーザー登録完了");
      initializeApplicationTypes(context);
//      System.out.println("【AppInitListener】申請種別登録完了");

      context.log("社内申請管理システムの初期化が完了しました。");

    } catch (Exception e) {
      context.log("社内申請管理システムの初期化に失敗しました。", e);

      throw new IllegalStateException("アプリケーションを初期化できません。", e);
    }
  }

  private void initializeApplicationTypes(
      ServletContext context) throws SQLException {

    String sql = """
        INSERT OR IGNORE INTO application_types(type_name)
        VALUES (?)
        """;

    String[] types = {
        "休暇申請",
        "出張申請",
        "経費申請",
        "備品購入申請"
    };

    try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      for (String type : types) {
        ps.setString(1, type);
        ps.executeUpdate();
      }
    }
  }

  private void createInitialUsers(ServletContext context) {
    String[][] users = {
        { "employee01", "Passw0rd!", "山田太郎", "EMPLOYEE" },
        { "employee02", "Passw0rd!", "田中二郎", "EMPLOYEE" },
        { "approver01", "Passw0rd!", "佐藤花子", "APPROVER" },
        { "admin01", "Passw0rd!", "管理者", "ADMIN" }
    };

    String sql = """
        INSERT OR IGNORE INTO users
            (id, username, password, display_name, role)
        VALUES (?, ?, ?, ?, ?)
        """;

    try (Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      for (int i = 0; i < users.length; i++) {
        pstmt.setInt(1, i + 1);
        pstmt.setString(2, users[i][0]);
        pstmt.setString(3, users[i][1]);
        pstmt.setString(4, users[i][2]);
        pstmt.setString(5, users[i][3]);
        pstmt.executeUpdate();
      }

    } catch (SQLException e) {
      throw new RuntimeException("初期ユーザーの登録に失敗しました。", e);
    }
  }

  public void contextDestroyed(ServletContextEvent sce) {
  }

}

package listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import model.DBUtil;

@WebListener
public class AppInitListener
    implements ServletContextListener {

  @Override
  public void contextInitialized(
      ServletContextEvent event) {

    try {
      DBUtil.init(event.getServletContext());

    } catch (Exception e) {
      throw new RuntimeException(
          "データベースの初期化に失敗しました", e);
    }
  }
}

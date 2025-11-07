package listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import model.DBUtil;

@WebListener
public class AppInitListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext ctx = sce.getServletContext();
    DBUtil.init(ctx); // DB ファイルパス決めてテーブル作成
    ctx.log("[ReservationApp]AppInitListener: DBUtil initialized.");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
  }
}

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
    ServletContext context = sce.getServletContext();
    DBUtil.init(context);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
  }
}

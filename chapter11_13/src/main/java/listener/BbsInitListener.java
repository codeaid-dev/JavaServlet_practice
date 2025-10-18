package listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import model.DBUtil;

@WebListener
public class BbsInitListener implements ServletContextListener {

  public void contextInitialized(ServletContextEvent sce) {
    ServletContext context = sce.getServletContext();
    DBUtil.init(context);
    System.out.println("[AppInitListener] start Web application - start init.");
  }

  public void contextDestroyed(ServletContextEvent sce) {
    System.out.println("[AppInitListener] stop Web application - finish process.");
  }

}

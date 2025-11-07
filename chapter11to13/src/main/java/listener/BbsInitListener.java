package listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import model.BbsDBUtil;

@WebListener
public class BbsInitListener implements ServletContextListener {

  public void contextInitialized(ServletContextEvent sce) {
    ServletContext context = sce.getServletContext();
    BbsDBUtil.init(context);
    System.out.println("[BbsInitListener] start Web application - start init.");
  }

  public void contextDestroyed(ServletContextEvent sce) {
    System.out.println("[BbsInitListener] stop Web application - finish process.");
  }

}

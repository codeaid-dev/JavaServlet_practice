package listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import model.RsDBUtil;

@WebListener
public class RsInitListener implements ServletContextListener {

  public void contextInitialized(ServletContextEvent sce) {
    ServletContext context = sce.getServletContext();
    context.log("[RsInitListener] contextInitialized - RsDBUtil.init()");
    RsDBUtil.init(context);
  }

  public void contextDestroyed(ServletContextEvent sce) {
  }
}

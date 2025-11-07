package listener;

import java.util.HashSet;
import java.util.Set;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class Ex3Listener implements ServletContextListener {

  public void contextInitialized(ServletContextEvent sce) {
    Set<String> allowedIPs = new HashSet<>(Set.of(
        "127.0.0.1",
        "0:0:0:0:0:0:0:1"));
    ServletContext application = sce.getServletContext();
    application.setAttribute("allowedIPs", allowedIPs);
  }

  public void contextDestroyed(ServletContextEvent sce) {
    ServletContext application = sce.getServletContext();
    application.removeAttribute("allowedIPs");
  }

}

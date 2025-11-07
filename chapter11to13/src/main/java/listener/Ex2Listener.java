package listener;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class Ex2Listener implements ServletContextListener {

  public void contextInitialized(ServletContextEvent sce) {
    Map<String, String> products = new HashMap<>();
    products.put("1", "商品A");
    products.put("2", "商品B");
    products.put("3", "商品C");

    sce.getServletContext().setAttribute("products", products);
  }

  public void contextDestroyed(ServletContextEvent sce) {
    sce.getServletContext().removeAttribute("products");
  }

}

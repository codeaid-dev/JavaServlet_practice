package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;

@WebServlet("/ex8")
public class Ex8 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletContext application = getServletContext();
    if (application.getAttribute("products") == null) {
      List<Product> products = new ArrayList<>();
      products.add(new Product("商品1", 1000, 0));
      products.add(new Product("商品2", 2000, 0));
      products.add(new Product("商品3", 3000, 0));
      application.setAttribute("products", products);
    }

    request.getRequestDispatcher("/WEB-INF/jsp/ex8.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletContext application = getServletContext();
    List<Product> products = (List<Product>) application.getAttribute("products");
    HttpSession session = request.getSession();
    List<Product> cart = (List<Product>) session.getAttribute("cart");

    if (cart == null) {
      cart = new ArrayList<>();
      for (Product product : products) {
        product.setQuantity(0);
      }
    }

    if (request.getParameter("add_to_cart") != null) {
      String[] option = request.getParameterValues("option");
      if (option != null) {
        for (String item : option) {
          for (Product product : products) {
            if (item.equals(product.getName())) {
              int quantity = Integer.valueOf(request.getParameter("quantity" + product.getName()));
              if (cart.contains(product)) {
                for (Product c : cart) {
                  if (c.getName().equals(product.getName())) {
                    c.setQuantity(c.getQuantity() + quantity);
                  }
                }
              } else {
                product.setQuantity(product.getQuantity() + quantity);
                cart.add(product);
              }
            }
          }
        }
      }
      if (cart.size() != 0) {
        session.setMaxInactiveInterval(60);
        session.setAttribute("cart", cart);
      }
    } else if (request.getParameter("del_from_cart") != null) {
      session.removeAttribute("cart");
    }

    request.getRequestDispatcher("/WEB-INF/jsp/ex8.jsp").forward(request, response);
  }

}

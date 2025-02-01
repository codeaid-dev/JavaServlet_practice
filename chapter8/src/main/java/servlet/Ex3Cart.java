package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ex3cart")
public class Ex3Cart extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ex3Cart.jsp");
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    List<String> cart = (List<String>) session.getAttribute("cart");

    if (cart == null) {
      cart = new ArrayList<>();
    }

    // 商品が追加された場合、リストに保存
    String product = request.getParameter("product");
    if (product != null && !product.isEmpty()) {
      cart.add(product);
    }

    // セッションにカートを保存
    session.setAttribute("cart", cart);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ex3Cart.jsp");
    dispatcher.forward(request, response);
  }

}

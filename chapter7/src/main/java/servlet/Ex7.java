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
import model.Product;

@WebServlet("/ex7")
public class Ex7 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Product> productList = new ArrayList<>();
    int pcPrice = Integer.parseInt(request.getParameter("pcPrice"));
    int spPrice = Integer.parseInt(request.getParameter("spPrice"));
    int tabPrice = Integer.parseInt(request.getParameter("tabPrice"));
    int keyPrice = Integer.parseInt(request.getParameter("keyPrice"));
    String[] products = request.getParameterValues("products");
    for (String product : products) {
      int price = 0;
      if (product.equals("パソコン")) {
        price = pcPrice;
      } else if (product.equals("スマートフォン")) {
        price = spPrice;
      } else if (product.equals("タブレット")) {
        price = tabPrice;
      } else if (product.equals("キーボード")) {
        price = keyPrice;
      }
      productList.add(new Product(product, price));
    }
    request.setAttribute("products", productList);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ex7.jsp");
    dispatcher.forward(request, response);
  }

}

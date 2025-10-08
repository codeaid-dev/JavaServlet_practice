package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

@WebServlet("/ex8")
public class Ex8 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Product> productList = new ArrayList<>();
    productList.add(new Product("りんご", 100));
    productList.add(new Product("バナナ", 150));
    productList.add(new Product("オレンジ", 120));

    request.setAttribute("products", productList);
    request.getRequestDispatcher("/WEB-INF/jsp/ex8.jsp").forward(request, response);
  }

}

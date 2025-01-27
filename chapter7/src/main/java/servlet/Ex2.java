package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex2")
public class Ex2 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] fruits = { "林檎", "桃", "西瓜", "葡萄", "梨" };
    request.setAttribute("fruits", fruits);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ex2.jsp");
    dispatcher.forward(request, response);
  }

}

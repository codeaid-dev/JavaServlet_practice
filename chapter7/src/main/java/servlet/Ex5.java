package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex5")
public class Ex5 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String age = request.getParameter("age");
    String email = request.getParameter("email");
    request.setAttribute("username", username);
    request.setAttribute("age", age);
    request.setAttribute("email", email);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ex5.jsp");
    dispatcher.forward(request, response);
  }

}

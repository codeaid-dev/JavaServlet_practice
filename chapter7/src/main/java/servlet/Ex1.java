package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ex1")
public class Ex1 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String age = request.getParameter("age");
    username = username != null ? username : "";
    age = age != null ? age : "";
    request.setAttribute("username", username);
    request.setAttribute("age", age);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/ex1.jsp");
    dispatcher.forward(request, response);
  }

}

package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet("/ex6")
public class Ex6 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String age = request.getParameter("age");
    String email = request.getParameter("email");
    age = (age != null && age.length() != 0) ? age : "0";
    User user = new User(username, Integer.parseInt(age), email);
    request.setAttribute("user", user);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ex6.jsp");
    dispatcher.forward(request, response);
  }

}

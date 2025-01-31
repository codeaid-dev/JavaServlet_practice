package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ex2welcome")
public class Ex2Welcome extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ex2.jsp");
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String user = request.getParameter("user");
    String password = request.getParameter("password");
    if ("1234".equals(password)) {
      HttpSession session = request.getSession();
      session.setMaxInactiveInterval(30);
      session.setAttribute("user", user);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ex2.jsp");
      dispatcher.forward(request, response);
    } else {
      response.sendRedirect("ex2");
    }
  }

}

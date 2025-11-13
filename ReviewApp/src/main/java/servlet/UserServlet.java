package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    String user = request.getParameter("user");
    if (user == null || user.trim().isEmpty()) {
      request.setAttribute("error", "ユーザー名を入力してください。");
      RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
      rd.forward(request, response);
      return;
    }

    HttpSession session = request.getSession();
    session.setMaxInactiveInterval(60);
    session.setAttribute("user", user.trim());

    response.sendRedirect("review");
  }

}

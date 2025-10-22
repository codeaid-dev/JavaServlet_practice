package servlet;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.RsDBUtil;

@WebServlet("/login")
public class RsLogin extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    Map<String, String> user = RsDBUtil.findUser(username, password);
    if (user != null) {
      HttpSession session = request.getSession();
      session.setAttribute("user", user); // mapに username, role を持つ
      response.sendRedirect(request.getContextPath() + "/list");
    } else {
      request.setAttribute("error", "ユーザー名またはパスワードが違います");
      request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }
  }
}

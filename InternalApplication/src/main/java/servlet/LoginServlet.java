package servlet;

import java.io.IOException;

import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;
import dao.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(
        "/WEB-INF/jsp/login.jsp")
        .forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");

    String username = request.getParameter("username");
    String password = request.getParameter("password");

    UserDAO userDAO = new UserDAO(getServletContext());

    try {
      User user = userDAO.authenticate(username, password);

      if (user == null) {
        request.setAttribute("error",
            "ユーザーIDまたはパスワードが正しくありません。");

        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
               .forward(request, response);
        return;
      }

      HttpSession oldSession = request.getSession(false);

      if (oldSession != null) {
        oldSession.invalidate();
      }

      HttpSession session = request.getSession(true);

      session.setAttribute("userId", user.getId());
      session.setAttribute("username", user.getUsername());
      session.setAttribute("displayName", user.getDisplayName());
      session.setAttribute("role", user.getRole());

      session.setMaxInactiveInterval(30 * 60);

      response.sendRedirect(
        request.getContextPath() + "/applications"
      );

    } catch (SQLException e) {
      throw new ServletException("ログイン処理に失敗しました。", e);
    }
  }
}

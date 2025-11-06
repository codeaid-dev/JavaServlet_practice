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

@WebServlet("/edit")
public class RsEdit extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession s = request.getSession(false);
    if (s == null || s.getAttribute("user") == null) {
      response.sendRedirect(request.getContextPath() + "/login");
      return;
    }
    int id = Integer.parseInt(request.getParameter("id"));
    Map<String, String> r = RsDBUtil.getReservationById(id);
    if (r == null) {
      response.sendRedirect(request.getContextPath() + "/list");
      return;
    }
    // 権限チェック：admin か 自分の登録か
    Map<String, String> user = (Map<String, String>) s.getAttribute("user");
    boolean editable = "admin".equals(user.get("role")) || user.get("username").equals(r.get("username"));
    if (!editable) {
      response.sendRedirect(request.getContextPath() + "/list");
      return;
    }
    request.setAttribute("data", r);
    request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
  }

}

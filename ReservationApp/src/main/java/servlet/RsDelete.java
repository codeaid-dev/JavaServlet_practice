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

@WebServlet("/delete")
public class RsDelete extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession s = request.getSession(false);
    if (s == null || s.getAttribute("user") == null) {
      response.sendRedirect(request.getContextPath() + "/login");
      return;
    }
    Map<String, String> user = (Map<String, String>) s.getAttribute("user");
    if (!"admin".equals(user.get("role"))) {
      response.sendRedirect(request.getContextPath() + "/list");
      return;
    }
    int id = Integer.parseInt(request.getParameter("id"));
    RsDBUtil.deleteReservation(id);
    response.sendRedirect(request.getContextPath() + "/list");
  }

}

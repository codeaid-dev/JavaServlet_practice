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

@WebServlet("/update")
public class RsUpdate extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
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

    Map<String, String> user = (Map<String, String>) s.getAttribute("user");
    boolean editable = "admin".equals(user.get("role")) || user.get("username").equals(r.get("username"));
    if (!editable) {
      response.sendRedirect(request.getContextPath() + "/list");
      return;
    }

    String name = request.getParameter("name");
    String date = request.getParameter("date");
    String time = request.getParameter("time");
    String note = request.getParameter("note");

    boolean ok = RsDBUtil.updateReservation(id, name, date, time, note);
    if (ok) {
      response.sendRedirect(request.getContextPath() + "/list");
    } else {
      request.setAttribute("error", "同じ日時の予約が既に存在します。");
      request.setAttribute("data", RsDBUtil.getReservationById(id));
      request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
    }
  }
}

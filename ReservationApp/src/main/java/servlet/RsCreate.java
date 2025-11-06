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

@WebServlet("/create")
public class RsCreate extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession s = request.getSession(false);
    if (s == null || s.getAttribute("user") == null) {
      response.sendRedirect(request.getContextPath() + "/login");
      return;
    }
    request.getRequestDispatcher("/WEB-INF/jsp/create.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    HttpSession s = request.getSession(false);
    if (s == null || s.getAttribute("user") == null) {
      response.sendRedirect(request.getContextPath() + "/login");
      return;
    }
    Map<String, String> user = (Map<String, String>) s.getAttribute("user");
    String username = user.get("username");

    String name = request.getParameter("name");
    String date = request.getParameter("date");
    String time = request.getParameter("time");
    String note = request.getParameter("note");

    boolean ok = RsDBUtil.insertReservation(name, date, time, note, username);
    if (ok) {
      response.sendRedirect(request.getContextPath() + "/list");
    } else {
      request.setAttribute("error", "同じ日時の予約が既に存在します。");
      request.getRequestDispatcher("/WEB-INF/jsp/create.jsp").forward(request, response);
    }
  }
}

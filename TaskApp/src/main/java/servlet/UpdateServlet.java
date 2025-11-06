package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.DBUtil;

@WebServlet("/task/update")
public class UpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    int id = Integer.parseInt(request.getParameter("id"));
    String title = request.getParameter("title");
    String desc = request.getParameter("description");
    String due = request.getParameter("due_date");
    String status = request.getParameter("status");
    DBUtil.updateTask(id, title, desc, due, status);
    response.sendRedirect(request.getContextPath() + "/tasks");
  }

}

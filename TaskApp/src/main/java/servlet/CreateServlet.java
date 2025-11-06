package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.DBUtil;

@WebServlet("/task/create")
public class CreateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/jsp/create.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    String title = request.getParameter("title");
    String desc = request.getParameter("description");
    String due = request.getParameter("due_date");
    String status = request.getParameter("status");
    if (status == null)
      status = "TODO";
    DBUtil.insertTask(title, desc, due, status);
    response.sendRedirect(request.getContextPath() + "/tasks");
  }

}

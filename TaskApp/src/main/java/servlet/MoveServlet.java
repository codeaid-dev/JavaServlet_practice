package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.DBUtil;

@WebServlet("/task/move")
public class MoveServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    String to = request.getParameter("to"); // TODO / DOING / DONE
    DBUtil.moveTask(id, to);
    response.sendRedirect(request.getContextPath() + "/tasks");
  }

}

package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.DBUtil;

@WebServlet("/tasks")
public class ListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Map<String, String>> todos = DBUtil.findAllByStatus("TODO");
    List<Map<String, String>> doing = DBUtil.findAllByStatus("DOING");
    List<Map<String, String>> done = DBUtil.findAllByStatus("DONE");
    request.setAttribute("todos", todos);
    request.setAttribute("doing", doing);
    request.setAttribute("done", done);
    request.getRequestDispatcher("/WEB-INF/jsp/tasks.jsp").forward(request, response);
  }

}

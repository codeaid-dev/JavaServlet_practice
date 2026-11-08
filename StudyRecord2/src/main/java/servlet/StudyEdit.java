package servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.StudyRecordDAO;
import model.StudyRecord;

@WebServlet("/study/edit")
public class StudyEdit extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private StudyRecordDAO dao;

  public void init(ServletConfig config) throws ServletException {
    dao = new StudyRecordDAO();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String idParam = request.getParameter("id");
    try {
      int id = Integer.parseInt(idParam);
      StudyRecord record = dao.findById(id);
      if (record == null) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        return;
      }
      request.setAttribute("record", record);
      request.getRequestDispatcher("/WEB-INF/jsp/study-edit.jsp")
          .forward(request, response);
    } catch (NumberFormatException e) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    } catch (SQLException e) {
      throw new ServletException(e);
    }
  }
}

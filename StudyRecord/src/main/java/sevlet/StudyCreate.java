package sevlet;

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

@WebServlet("/study/create")
public class StudyCreate extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private StudyRecordDAO dao;

  public void init(ServletConfig config) throws ServletException {
    dao = new StudyRecordDAO();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/jsp/study-form.jsp")
        .forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");

    String subject = request.getParameter("subject");
    String studyDate = request.getParameter("studyDate");
    String minutesParam = request.getParameter("minutes");
    String memo = request.getParameter("memo");

    try {
      int minutes = Integer.parseInt(minutesParam);
      if (subject == null || subject.isBlank()
          || studyDate == null
          || studyDate.isBlank()
          || minutes <= 0) {
        response.sendError(
            HttpServletResponse.SC_BAD_REQUEST);
        return;
      }

      StudyRecord record = new StudyRecord(subject, studyDate, minutes, memo);

      dao.insert(record);

      response.sendRedirect(
          request.getContextPath() + "/study/list");

    } catch (NumberFormatException e) {
      response.sendError(
          HttpServletResponse.SC_BAD_REQUEST);

    } catch (SQLException e) {
      throw new ServletException(e);
    }
  }

}

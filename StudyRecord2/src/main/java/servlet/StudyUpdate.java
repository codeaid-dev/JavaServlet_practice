package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.StudyRecordDAO;
import model.StudyRecord;

@WebServlet("/study/update")
public class StudyUpdate extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private StudyRecordDAO dao;

  public void init(ServletConfig config) throws ServletException {
    dao = new StudyRecordDAO();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");

    String idParam =
        request.getParameter("id");
    String subject =
        request.getParameter("subject");
    String studyDate =
        request.getParameter("studyDate");
    String minutesParam =
        request.getParameter("minutes");
    String memo =
        request.getParameter("memo");

    try {
      int id = Integer.parseInt(idParam);
      int minutes = Integer.parseInt(minutesParam);
      // 入力チェック
      if (id <= 0
              || subject == null
              || subject.isBlank()
              || subject.length() > 100
              || studyDate == null
              || minutes <= 0
              || minutes > 1440
              || (memo != null && memo.length() > 1000)) {
          response.sendError(
              HttpServletResponse.SC_BAD_REQUEST);
          return;
      }
      // 日付形式のチェック
      LocalDate.parse(studyDate);
      // DTOにデータを格納
      StudyRecord record = new StudyRecord(subject, studyDate, minutes, memo);
      record.setId(id);
      // 更新処理
      boolean result = dao.update(record);
      if (!result) {
          response.sendError(HttpServletResponse.SC_NOT_FOUND);
          return;
      }
      // PRGパターン
      response.sendRedirect(request.getContextPath() + "/study/list");
    } catch (NumberFormatException
            | DateTimeParseException e) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    } catch (SQLException e) {
      throw new ServletException(e);
    }
  }
}

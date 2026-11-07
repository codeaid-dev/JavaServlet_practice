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

@WebServlet("/study/list")
public class StudyList extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private StudyRecordDAO dao;

  public void init(ServletConfig config) throws ServletException {
    dao = new StudyRecordDAO();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      request.setAttribute("records", dao.findAll());

      request.getRequestDispatcher("/WEB-INF/jsp/study-list.jsp")
          .forward(request, response);

    } catch (SQLException e) {
      throw new ServletException(e);
    }
  }
}

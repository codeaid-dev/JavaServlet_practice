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

@WebServlet("/study/delete")
public class StudyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudyRecordDAO dao;

	public void init(ServletConfig config) throws ServletException {
	  dao = new StudyRecordDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    String idParam = request.getParameter("id");
    try {
      int id = Integer.parseInt(idParam);
      if (id <= 0) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return;
      }
      boolean result = dao.delete(id);
      if (!result) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        return;
      }
      response.sendRedirect(request.getContextPath() + "/study/list");
    } catch (NumberFormatException e) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    } catch (SQLException e) {
      throw new ServletException(e);
    }
  }
}

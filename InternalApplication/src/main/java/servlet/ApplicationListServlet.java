package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.ApplicationDAO;
import model.Application;

@WebServlet("/applications")
public class ApplicationListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);

    // ログインしていない場合はログイン画面へ
    if (session == null || session.getAttribute("userId") == null) {
      response.sendRedirect(
          request.getContextPath() + "/login"
      );
      return;
    }

    int userId = (Integer) session.getAttribute("userId");
    String role = (String) session.getAttribute("role");

    ApplicationDAO applicationDAO =
        new ApplicationDAO(getServletContext());

    try {
      List<Application> applications;

      if ("EMPLOYEE".equals(role)) {
        // 自分の申請だけを表示
        applications =
            applicationDAO.findByApplicantId(userId);
      } else if ("APPROVER".equals(role)) {
        // 自分が担当する申請を表示
        applications =
            applicationDAO.findByApproverId(userId);
      } else if ("ADMIN".equals(role)) {
        // 管理者はすべての申請を表示
        applications = applicationDAO.findAll();
      } else {
        response.sendError(
            HttpServletResponse.SC_FORBIDDEN
        );
        return;
      }

      request.setAttribute("applications", applications);
      request.getRequestDispatcher(
          "/WEB-INF/jsp/application-list.jsp"
      ).forward(request, response);
    } catch (SQLException e) {
      throw new ServletException(
          "申請一覧の取得に失敗しました。", e
      );
    }
  }
}

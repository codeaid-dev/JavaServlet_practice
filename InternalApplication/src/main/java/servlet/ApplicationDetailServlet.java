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
import dao.ApprovalRecordDAO;
import model.ApprovalRecord;

@WebServlet("/applications/detail")
public class ApplicationDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);

    if (session == null
            || session.getAttribute("userId") == null) {

      response.sendRedirect(
          request.getContextPath() + "/login"
      );
      return;
    }

    String idText = request.getParameter("id");
    int applicationId;
    try {
      applicationId = Integer.parseInt(idText);
    } catch (NumberFormatException e) {
      response.sendError(
          HttpServletResponse.SC_BAD_REQUEST
      );
      return;
    }

    int userId = (Integer) session.getAttribute("userId");
    String role = (String) session.getAttribute("role");

    ApplicationDAO dao =
        new ApplicationDAO(getServletContext());

    try {
      Application application = dao.findById(applicationId);

      if (application == null) {
        response.sendError(
            HttpServletResponse.SC_NOT_FOUND
        );
        return;
      }

      // 申請内容を閲覧できるユーザーか確認する
      boolean allowed;

      if ("ADMIN".equals(role)) {
        allowed = true;
      } else if ("EMPLOYEE".equals(role)) {
        allowed = application.getApplicantId() == userId;
      } else if ("APPROVER".equals(role)) {
        allowed = application.getApproverId() == userId;
      } else {
        allowed = false;
      }

      if (!allowed) {
        response.sendError(
            HttpServletResponse.SC_FORBIDDEN
        );
        return;
      }
      // 承認・却下履歴を取得する
      ApprovalRecordDAO approvalRecordDAO =
          new ApprovalRecordDAO();

      List<ApprovalRecord> approvalRecords =
          approvalRecordDAO.findByApplicationId(applicationId);

      // JSPに渡すデータを設定する
      request.setAttribute("application", application);
      request.setAttribute("approvalRecords", approvalRecords);

      request.getRequestDispatcher(
          "/WEB-INF/jsp/application-detail.jsp"
      ).forward(request, response);

    } catch (SQLException e) {
      throw new ServletException(
          "申請詳細または承認履歴の取得に失敗しました。", e
      );
    }
  }
}

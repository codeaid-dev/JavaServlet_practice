package servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.ApplicationDAO;
import model.Application;

@WebServlet("/applications/decision")
public class ApplicationDecisionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("UTF-8");

    HttpSession session = request.getSession(false);

    if (session == null
            || session.getAttribute("userId") == null) {

      response.sendRedirect(
          request.getContextPath() + "/login"
      );
      return;
    }

    String role = (String) session.getAttribute("role");

    // 承認者以外は処理できない
    if (!"APPROVER".equals(role)) {
      response.sendError(
          HttpServletResponse.SC_FORBIDDEN
      );
      return;
    }

    int approverId = (Integer) session.getAttribute("userId");
    int applicationId;

    try {
      applicationId = Integer.parseInt(
          request.getParameter("id")
      );
    } catch (NumberFormatException e) {
      response.sendError(
          HttpServletResponse.SC_BAD_REQUEST
      );
      return;
    }

    String action = request.getParameter("action");
    String comment = request.getParameter("comment");

    // 承認または却下以外は受け付けない
    if (!"APPROVED".equals(action)
            && !"REJECTED".equals(action)) {
      response.sendError(
          HttpServletResponse.SC_BAD_REQUEST
      );
      return;
    }

    if (comment != null) {
      comment = comment.trim();
    }

    // 却下時はコメントを必須とする
    if ("REJECTED".equals(action)
            && (comment == null || comment.isEmpty())) {
      request.setAttribute(
          "error",
          "却下理由を入力してください。"
      );

      showApplicationDetail(
          request, response, applicationId, approverId
      );
      return;
    }

    // コメントの最大文字数
    if (comment != null && comment.length() > 1000) {
      request.setAttribute(
          "error",
          "コメントは1000文字以内で入力してください。"
      );

      showApplicationDetail(
          request, response, applicationId, approverId
      );
      return;
    }

    ApplicationDAO dao =
        new ApplicationDAO(getServletContext());

    try {
      // 申請の存在と担当承認者を確認する
      Application application = dao.findById(applicationId);

      if (application == null) {
        response.sendError(
            HttpServletResponse.SC_NOT_FOUND
        );
        return;
      }

      if (application.getApproverId() != approverId) {
        response.sendError(
            HttpServletResponse.SC_FORBIDDEN
        );
        return;
      }

      // DAO側でも担当者とステータスを再確認して更新する
      boolean success = dao.processDecision(
          applicationId,
          approverId,
          action,
          comment
      );

      if (!success) {
        request.setAttribute(
            "error",
            "この申請は既に処理されているか、処理できない状態です。"
        );

        showApplicationDetail(
            request, response, applicationId, approverId
        );
        return;
      }

      // 二重送信を避けるため一覧へリダイレクトする
      response.sendRedirect(
          request.getContextPath() + "/applications"
      );

    } catch (SQLException e) {
      throw new ServletException(
          "承認・却下処理に失敗しました。", e
      );
    }
  }

  /**
   * エラー時に申請詳細画面を再表示する。
   */
  private void showApplicationDetail(
        HttpServletRequest request,
        HttpServletResponse response,
        int applicationId,
        int approverId)
        throws ServletException, IOException {

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

      if (application.getApproverId() != approverId) {
        response.sendError(
            HttpServletResponse.SC_FORBIDDEN
        );
        return;
      }

      request.setAttribute("application", application);

      request.getRequestDispatcher(
          "/WEB-INF/jsp/application-detail.jsp"
      ).forward(request, response);

    } catch (SQLException e) {
      throw new ServletException(
          "申請詳細の取得に失敗しました。", e
      );
    }
  }
}

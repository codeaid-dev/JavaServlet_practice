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

@WebServlet("/applications/withdraw")
public class ApplicationWithdrawServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);

    // ログイン確認
    if (session == null ||
          session.getAttribute("userId") == null) {
      response.sendRedirect(
          request.getContextPath() + "/login"
      );
      return;
    }

    // 申請者本人による操作に限定
    String role = (String) session.getAttribute("role");

    if (!"EMPLOYEE".equals(role)) {
      response.sendError(
          HttpServletResponse.SC_FORBIDDEN
      );
      return;
    }

    int applicantId;
    int applicationId;

    try {
      applicantId = (Integer) session.getAttribute("userId");
      applicationId = Integer.parseInt(
          request.getParameter("id")
      );
      if (applicationId <= 0) {
        throw new NumberFormatException();
      }
    } catch (NumberFormatException | NullPointerException e) {
      response.sendError(
          HttpServletResponse.SC_BAD_REQUEST,
          "申請番号が不正です。"
      );
      return;
    }

    ApplicationDAO dao =
        new ApplicationDAO(getServletContext());

    try {
      boolean success = dao.withdraw(
          applicationId,
          applicantId
      );

      if (success) {
        response.sendRedirect(
            request.getContextPath() + "/applications"
        );
      } else {
        // 存在しない申請、他人の申請、
        // または承認待ち以外の申請
        request.setAttribute(
            "error",
            "申請を取り下げられませんでした。"
            + "申請者本人の承認待ち申請であることを確認してください。"
        );

        response.setStatus(
            HttpServletResponse.SC_CONFLICT
        );

        request.getRequestDispatcher(
            "/WEB-INF/jsp/application-withdraw-error.jsp"
        ).forward(request, response);
      }
    } catch (SQLException e) {
      throw new ServletException(
          "申請の取り下げに失敗しました。",
          e
      );
    }
  }
}

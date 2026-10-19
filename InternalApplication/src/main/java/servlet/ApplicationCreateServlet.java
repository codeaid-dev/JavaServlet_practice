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
import dao.ApplicationTypeDAO;
import dao.UserDAO;
import model.ApplicationType;
import model.User;

@WebServlet("/applications/create")
public class ApplicationCreateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);

    if (session == null || session.getAttribute("userId") == null) {

      response.sendRedirect(
          request.getContextPath() + "/login"
      );
      return;
    }

    String role = (String) session.getAttribute("role");

    // 一般社員だけが申請を作成できる
    if (!"EMPLOYEE".equals(role)) {
      response.sendError(
          HttpServletResponse.SC_FORBIDDEN
      );
      return;
    }

    try {
      showCreateForm(request, response);

    } catch (SQLException e) {
      throw new ServletException(
          "申請作成画面の表示に失敗しました。", e
      );
    }
  }

  /**
   * 申請を登録する。
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");

    HttpSession session = request.getSession(false);

    if (session == null || session.getAttribute("userId") == null) {
      response.sendRedirect(
          request.getContextPath() + "/login"
      );
      return;
    }

    String role = (String) session.getAttribute("role");

    if (!"EMPLOYEE".equals(role)) {
      response.sendError(
          HttpServletResponse.SC_FORBIDDEN
      );
      return;
    }

    String title = request.getParameter("title");
    String reason = request.getParameter("reason");
    String typeIdText = request.getParameter("applicationTypeId");
    String amountText = request.getParameter("amount");

    if (title != null) {
      title = title.trim();
    }

    if (reason != null) {
      reason = reason.trim();
    }

    if (typeIdText != null) {
      typeIdText = typeIdText.trim();
    }

    if (amountText != null) {
      amountText = amountText.trim();
    }

    // 入力チェック
    if (title == null || title.isEmpty()
            || reason == null || reason.isEmpty()
            || typeIdText == null || typeIdText.isEmpty()) {

      request.setAttribute(
          "error", "申請種別、件名、申請理由を入力してください。"
      );

      try {
        showCreateForm(request, response);
      } catch (SQLException e) {
        throw new ServletException(
            "申請種別の取得に失敗しました。", e
        );
      }
      return;
    }

    int applicationTypeId;
    Integer amount = null;

    try {
        applicationTypeId = Integer.parseInt(typeIdText);

        if (amountText != null && !amountText.isEmpty()) {
            amount = Integer.valueOf(amountText);

            if (amount < 0) {
                throw new NumberFormatException();
            }
        }

    } catch (NumberFormatException e) {
      request.setAttribute(
          "error", "申請種別または金額の入力が正しくありません。"
      );

      try {
        showCreateForm(request, response);
      } catch (SQLException ex) {
        throw new ServletException(
            "申請種別の取得に失敗しました。", ex
        );
      }
      return;
    }

    int applicantId = (Integer) session.getAttribute("userId");

    try {
      // 申請種別が現在有効かどうか確認する
      ApplicationTypeDAO typeDAO =
          new ApplicationTypeDAO(getServletContext());

      List<ApplicationType> types = typeDAO.findAll();

      boolean validType = types.stream()
          .anyMatch(type -> type.getId() == applicationTypeId);

      if (!validType) {
        request.setAttribute(
            "error", "選択された申請種別は利用できません。"
        );

        showCreateForm(request, response);
        return;
      }

      // 承認者をDBから取得する
      UserDAO userDAO = new UserDAO(getServletContext());
      User approver = userDAO.findByUsername("approver01");

      if (approver == null
              || !"APPROVER".equals(approver.getRole())) {

          throw new ServletException(
              "承認者 approver01 が正しく登録されていません。"
          );
      }

      // 申請を登録する
      ApplicationDAO applicationDAO =
          new ApplicationDAO(getServletContext());

      applicationDAO.create(
          applicantId,
          approver.getId(),
          applicationTypeId,
          title,
          reason,
          amount
      );

      // 登録後は申請一覧へ
      response.sendRedirect(
          request.getContextPath() + "/applications"
      );
    } catch (SQLException e) {
      throw new ServletException(
          "申請の登録に失敗しました。", e
      );
    }
  }

  /**
   * 申請種別を取得してフォームを表示する。
   */
  private void showCreateForm(
          HttpServletRequest request,
          HttpServletResponse response)
          throws SQLException, ServletException, IOException {

    ApplicationTypeDAO typeDAO =
        new ApplicationTypeDAO(getServletContext());

    request.setAttribute("types", typeDAO.findAll());

    request.getRequestDispatcher(
        "/WEB-INF/jsp/application-create.jsp"
    ).forward(request, response);
  }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletContext;
import model.Application;
import model.ApplicationStatus;
import util.DBUtil;

public class ApplicationDAO {

  private final ServletContext context;

  public ApplicationDAO(ServletContext context) {
    this.context = context;
  }

  /**
   * 申請を新規登録する。
   */
  public void create(
          int applicantId,
          int approverId,
          int applicationTypeId,
          String title,
          String reason,
          Integer amount) throws SQLException {

      String sql = """
          INSERT INTO applications (
              applicant_id,
              approver_id,
              application_type_id,
              title,
              reason,
              amount,
              status
          )
          VALUES (?, ?, ?, ?, ?, ?, 'PENDING')
          """;

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setInt(1, applicantId);
      pstmt.setInt(2, approverId);
      pstmt.setInt(3, applicationTypeId);
      pstmt.setString(4, title);
      pstmt.setString(5, reason);

      if (amount == null) {
        pstmt.setNull(6, Types.INTEGER);
      } else {
        pstmt.setInt(6, amount);
      }

      pstmt.executeUpdate();
    }
  }

  /**
   * 指定した社員の申請を取得する。
   */
  public List<Application> findByApplicantId(int applicantId)
          throws SQLException {

    String sql = baseSelectSql()
        + " WHERE a.applicant_id = ?"
        + " ORDER BY a.id DESC";

    return findByCondition(sql, applicantId);
  }

  /**
   * 指定した承認者が担当する申請を取得する。
   */
  public List<Application> findByApproverId(int approverId)
          throws SQLException {

    String sql = baseSelectSql()
        + " WHERE a.approver_id = ?"
        + " ORDER BY a.id DESC";

    return findByCondition(sql, approverId);
  }

  /**
   * すべての申請を取得する。
   */
  public List<Application> findAll() throws SQLException {

    List<Application> applications = new ArrayList<>();

    String sql = baseSelectSql()
        + " ORDER BY a.id DESC";

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

      while (rs.next()) {
        applications.add(mapRow(rs));
      }
    }

    return applications;
  }

  /**
   * 申請一覧を取得するSQLの共通部分。
   */
  private String baseSelectSql() {

    return """
        SELECT
            a.id,
            a.applicant_id,
            a.approver_id,
            a.application_type_id,
            applicant.display_name AS applicant_name,
            approver.display_name AS approver_name,
            t.type_name,
            a.title,
            a.reason,
            a.amount,
            a.status,
            a.applied_at,
            a.updated_at
        FROM applications a
        JOIN users applicant
            ON a.applicant_id = applicant.id
        JOIN users approver
            ON a.approver_id = approver.id
        JOIN application_types t
            ON a.application_type_id = t.id
        """;
  }

  /**
   * 条件を指定して申請一覧を取得する。
   */
  private List<Application> findByCondition(
          String sql, int userId) throws SQLException {

    List<Application> applications = new ArrayList<>();

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setInt(1, userId);

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          applications.add(mapRow(rs));
        }
      }
    }

    return applications;
  }

  /**
   * ResultSetの1行をApplicationオブジェクトに変換する。
   */
  private Application mapRow(ResultSet rs) throws SQLException {

    Application application = new Application();

    application.setId(rs.getInt("id"));
    application.setApplicantId(rs.getInt("applicant_id"));
    application.setApproverId(rs.getInt("approver_id"));
    application.setApplicationTypeId(rs.getInt("application_type_id"));

    application.setApplicantName(rs.getString("applicant_name"));
    application.setApproverName(rs.getString("approver_name"));
    application.setTypeName(rs.getString("type_name"));

    application.setTitle(rs.getString("title"));
    application.setReason(rs.getString("reason"));

    int amount = rs.getInt("amount");
    application.setAmount(
      rs.wasNull() ? null : amount
    );

    application.setStatus(
        ApplicationStatus.valueOf(rs.getString("status"))
    );

    application.setAppliedAt(rs.getString("applied_at"));
    application.setUpdatedAt(rs.getString("updated_at"));

    return application;
  }

  /**
   * 申請番号から申請情報を取得する。
   */
  public Application findById(int applicationId)
          throws SQLException {

    String sql = baseSelectSql()
        + " WHERE a.id = ?";

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setInt(1, applicationId);

      try (ResultSet rs = pstmt.executeQuery()) {

        if (rs.next()) {
          return mapRow(rs);
        }
      }
    }

    return null;
  }

  /**
   * 申請を承認または却下し、承認履歴を登録する。
   *
   * @param applicationId 申請番号
   * @param approverId 承認者のユーザーID
   * @param action APPROVED または REJECTED
   * @param comment 承認・却下コメント
   * @return 処理成功時はtrue、処理対象外の場合はfalse
   */
  public boolean processDecision(
          int applicationId,
          int approverId,
          String action,
          String comment) throws SQLException {

    if (!"APPROVED".equals(action)
            && !"REJECTED".equals(action)) {
      throw new IllegalArgumentException(
          "不正な承認処理です。"
      );
    }

    String updateSql = """
        UPDATE applications
        SET status = ?,
            updated_at = CURRENT_TIMESTAMP
        WHERE id = ?
          AND approver_id = ?
          AND status = 'PENDING'
        """;

    String insertSql = """
        INSERT INTO approval_records (
            application_id,
            approver_id,
            action,
            comment
        )
        VALUES (?, ?, ?, ?)
        """;

    try (Connection conn = DBUtil.getConnection()) {

      conn.setAutoCommit(false);

      try {
        // 1. 申請ステータスを更新する
        try (PreparedStatement pstmt =
                conn.prepareStatement(updateSql)) {

          pstmt.setString(1, action);
          pstmt.setInt(2, applicationId);
          pstmt.setInt(3, approverId);

          int updated = pstmt.executeUpdate();

          if (updated != 1) {
            conn.rollback();
            return false;
          }
        }

        // 2. 承認履歴を登録する
        try (PreparedStatement pstmt =
                conn.prepareStatement(insertSql)) {

          pstmt.setInt(1, applicationId);
          pstmt.setInt(2, approverId);
          pstmt.setString(3, action);
          pstmt.setString(4, comment);

          pstmt.executeUpdate();
        }

        // 3. 両方成功した場合に確定する
        conn.commit();

        return true;

      } catch (SQLException | RuntimeException e) {

        try {
          conn.rollback();
        } catch (SQLException rollbackException) {
          e.addSuppressed(rollbackException);
        }

        throw e;

      } finally {
        try {
          conn.setAutoCommit(true);
        } catch (SQLException e) {
          // 接続終了時の後処理なので、ここでは再送出しない
        }
      }
    }
  }

  /**
   * 申請を取り下げる
   *
   * @param applicationId 申請番号
   * @param applicantId 申請者ID
   * @return 取り下げに成功した場合は true
   */
  public boolean withdraw(
          int applicationId,
          int applicantId) throws SQLException {

    String sql =
        "UPDATE applications " +
        "SET status = 'WITHDRAWN', " +
        "    updated_at = CURRENT_TIMESTAMP " +
        "WHERE id = ? " +
        "  AND applicant_id = ? " +
        "  AND status = 'PENDING'";

    try (
      Connection conn = DBUtil.getConnection();
      PreparedStatement pstmt = conn.prepareStatement(sql)
    ) {
      pstmt.setInt(1, applicationId);
      pstmt.setInt(2, applicantId);
      int updatedRows = pstmt.executeUpdate();
      return updatedRows == 1;
    }
  }
}

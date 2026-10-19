package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ApprovalRecord;
import util.DBUtil;

public class ApprovalRecordDAO {

  /**
   * 指定された申請の承認・却下履歴を取得する
   */
  public List<ApprovalRecord> findByApplicationId(
          int applicationId) throws SQLException {

    List<ApprovalRecord> records = new ArrayList<>();

    String sql =
        "SELECT " +
        "    ar.id, " +
        "    ar.application_id, " +
        "    ar.approver_id, " +
        "    u.display_name AS approver_name, " +
        "    ar.action, " +
        "    ar.comment, " +
        "    ar.processed_at " +
        "FROM approval_records ar " +
        "JOIN users u " +
        "    ON ar.approver_id = u.id " +
        "WHERE ar.application_id = ? " +
        "ORDER BY ar.id ASC";

    try (
      Connection conn = DBUtil.getConnection();
      PreparedStatement pstmt = conn.prepareStatement(sql)
    ) {

      pstmt.setInt(1, applicationId);

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          ApprovalRecord record =
              new ApprovalRecord();
          record.setId(rs.getInt("id"));
          record.setApplicationId(
            rs.getInt("application_id")
          );
          record.setApproverId(
            rs.getInt("approver_id")
          );
          record.setApproverName(
            rs.getString("approver_name")
          );
          record.setAction(
            rs.getString("action")
          );
          record.setComment(
            rs.getString("comment")
          );
          record.setProcessedAt(
            rs.getString("processed_at")
          );
          records.add(record);
        }
      }
    }
    return records;
  }
}

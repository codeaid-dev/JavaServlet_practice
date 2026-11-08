package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DBUtil;
import model.StudyRecord;

public class StudyRecordDAO {

  // 全件取得
  public List<StudyRecord> findAll() throws SQLException {

    List<StudyRecord> list = new ArrayList<>();

    String sql = """
        SELECT * FROM study_records
        ORDER BY study_date DESC, id DESC
        """;

    try (Connection con = DBUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {

      while (rs.next()) {
        list.add(mapRecord(rs));
      }
    }

    return list;
  }

  // 1件取得
  public StudyRecord findById(int id) throws SQLException {

    String sql = "SELECT * FROM study_records WHERE id = ?";

    try (Connection con = DBUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {

      ps.setInt(1, id);

      try (ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
          return mapRecord(rs);
        }
      }
    }

    return null;
  }

  // 新規登録
  public boolean insert(StudyRecord record)
      throws SQLException {

    String sql = """
        INSERT INTO study_records
        (subject, study_date, minutes, memo)
        VALUES (?, ?, ?, ?)
        """;

    try (Connection con = DBUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {

      ps.setString(1, record.getSubject());
      ps.setString(2, record.getStudyDate());
      ps.setInt(3, record.getMinutes());
      ps.setString(4, record.getMemo());

      return ps.executeUpdate() == 1;
    }
  }

  // ResultSetからDTOを生成
  private StudyRecord mapRecord(ResultSet rs)
      throws SQLException {

    StudyRecord record = new StudyRecord();

    record.setId(rs.getInt("id"));
    record.setSubject(rs.getString("subject"));
    record.setStudyDate(rs.getString("study_date"));
    record.setMinutes(rs.getInt("minutes"));
    record.setMemo(rs.getString("memo"));
    record.setCreatedAt(rs.getString("created_at"));

    return record;
  }
}
package model;

public class StudyRecord {

  private int id;
  private String subject;
  private String studyDate;
  private int minutes;
  private String memo;
  private String createdAt;

  public StudyRecord() {
  }

  public StudyRecord(String subject, String studyDate,
      int minutes, String memo) {
    this.subject = subject;
    this.studyDate = studyDate;
    this.minutes = minutes;
    this.memo = memo;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getStudyDate() {
    return studyDate;
  }

  public void setStudyDate(String studyDate) {
    this.studyDate = studyDate;
  }

  public int getMinutes() {
    return minutes;
  }

  public void setMinutes(int minutes) {
    this.minutes = minutes;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
}

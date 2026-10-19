package model;

public class ApprovalRecord {

  private int id;
  private int applicationId;
  private int approverId;
  private String approverName;
  private String action;
  private String comment;
  private String processedAt;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(int applicationId) {
    this.applicationId = applicationId;
  }

  public int getApproverId() {
    return approverId;
  }

  public void setApproverId(int approverId) {
    this.approverId = approverId;
  }

  public String getApproverName() {
    return approverName;
  }

  public void setApproverName(String approverName) {
    this.approverName = approverName;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getProcessedAt() {
    return processedAt;
  }

  public void setProcessedAt(String processedAt) {
    this.processedAt = processedAt;
  }

  /**
   * 画面表示用の操作名を取得する
   */
  public String getActionLabel() {
    if ("APPROVED".equals(action)) {
      return "承認";
    }

    if ("REJECTED".equals(action)) {
      return "却下";
    }

    if ("WITHDRAWN".equals(action)) {
      return "取り下げ";
    }
    return action;
  }
}

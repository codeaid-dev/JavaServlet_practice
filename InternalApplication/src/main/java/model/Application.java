package model;

public class Application {

  private int id;
  private int applicantId;
  private int approverId;
  private int applicationTypeId;

  private String applicantName;
  private String approverName;
  private String typeName;

  private String title;
  private String reason;
  private Integer amount;

  private ApplicationStatus status;

  private String appliedAt;
  private String updatedAt;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getApplicantId() {
    return applicantId;
  }

  public void setApplicantId(int applicantId) {
    this.applicantId = applicantId;
  }

  public int getApproverId() {
    return approverId;
  }

  public void setApproverId(int approverId) {
    this.approverId = approverId;
  }

  public int getApplicationTypeId() {
    return applicationTypeId;
  }

  public void setApplicationTypeId(int applicationTypeId) {
    this.applicationTypeId = applicationTypeId;
  }

  public String getApplicantName() {
    return applicantName;
  }

  public void setApplicantName(String applicantName) {
    this.applicantName = applicantName;
  }

  public String getApproverName() {
    return approverName;
  }

  public void setApproverName(String approverName) {
    this.approverName = approverName;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public ApplicationStatus getStatus() {
    return status;
  }

  public void setStatus(ApplicationStatus status) {
    this.status = status;
  }

  public String getAppliedAt() {
    return appliedAt;
  }

  public void setAppliedAt(String appliedAt) {
    this.appliedAt = appliedAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
}

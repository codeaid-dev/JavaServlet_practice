package model;

public enum ApplicationStatus {

  PENDING("承認待ち"),
  APPROVED("承認済み"),
  REJECTED("却下"),
  WITHDRAWN("取り下げ");

  private final String label;

  ApplicationStatus(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}

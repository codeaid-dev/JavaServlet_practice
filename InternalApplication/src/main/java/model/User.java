package model;

public class User {

  private int id;
  private String username;
  private String displayName;
  private String role;

  public User() {
  }

  public User(
      int id,
      String username,
      String displayName,
      String role) {

    this.id = id;
    this.username = username;
    this.displayName = displayName;
    this.role = role;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}

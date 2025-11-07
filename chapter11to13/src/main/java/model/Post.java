package model;

public class Post {
  private int id;
  private String name;
  private String message;
  private String createdAt;

  public Post(int id, String name, String message, String createdAt) {
    this.id = id;
    this.name = name;
    this.message = message;
    this.createdAt = createdAt;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getMessage() {
    return message;
  }

  public String getCreatedAt() {
    return createdAt;
  }
}

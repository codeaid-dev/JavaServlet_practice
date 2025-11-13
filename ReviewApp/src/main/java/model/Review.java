package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Review {
  private String user;
  private String product;
  private String comment;
  private String date;

  public Review(String user, String product, String comment) {
    this.user = user;
    this.product = product;
    this.comment = comment;
    this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

  public String getUser() {
    return user;
  }

  public String getProduct() {
    return product;
  }

  public String getComment() {
    return comment;
  }

  public String getDate() {
    return date;
  }
}

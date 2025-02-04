package model;

import java.io.Serializable;

public class User implements Serializable {
  private String username;
  private int age;
  private String email;

  public User() {
  }

  public User(String username, int age, String email) {
    this.username = username;
    this.age = age;
    this.email = email;
  }

  public String getName() {
    return username;
  }

  public void setName(String username) {
    this.username = username;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}

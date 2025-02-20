package model;

import java.io.Serializable;

public class User implements Serializable {
  private String username;
  private int age;

  public User() {
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

}

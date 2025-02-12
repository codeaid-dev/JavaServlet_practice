package model;

import java.io.Serializable;

public class Data implements Serializable {
  private String person;
  private int number;
  private int price;

  public Data() {
  }

  public Data(String person, int number, int price) {
    this.person = person;
    this.number = number;
    this.price = price;
  }

  public String getPerson() {
    return this.person;
  }

  public int getNumber() {
    return this.number;
  }

  public int getPrice() {
    return this.price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

}

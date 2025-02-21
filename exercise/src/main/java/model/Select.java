package model;

import java.io.Serializable;

public class Select implements Serializable {
  private String text;
  private int number;

  public Select() {
  }

  public Select(String text, int number) {
    this.text = text;
    this.number = number;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getNumber() {
    return this.number;
  }

  public void setNumber(int number) {
    this.number = number;
  }
}

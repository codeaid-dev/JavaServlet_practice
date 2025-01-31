package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Favorite implements Serializable {
  private List<String> sports = new ArrayList<>();
  private List<String> players = new ArrayList<>();

  public Favorite() {
  }

  public void setSport(String s) {
    sports.add(s);
  }

  public void setPlayer(String p) {
    players.add(p);
  }

  public List<String> getSports() {
    return this.sports;
  }

  public List<String> getPlayers() {
    return this.players;
  }

}

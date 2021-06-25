/**
 * CSCI 2110
 * TeamWins.java
 * This is an object class that stores a team name and the total number of wins for that team in the tournament
 * Created by Mason Clarke, March 6, 2021
 */ 
public class TeamWins {

  private String team;
  private int wins;

  public TeamWins(String team) {
    this.team = team;
    this.wins = 0;
  }

  public String getTeam() {
    return team;
  }

  public void setTeam(String team) {
    this.team = team;
  }

  public int getWins() {
    return wins;
  }

  public void setWins(int wins) {
    this.wins = wins;
  }

}

/**
 * CSCI 2110
 * MatchRecord.java
 * This is an object class for storing match information from FIFA world cup Soccer matches
 * Created by Mason Clarke, March 6, 2021
 */
public class MatchRecord {

  private String roundNumber;
  private String dateAndTime;
  private String stadiumName;
  private String homeTeam;
  private String awayTeam;
  private String group;
  private String result;

  /**
   * Default constructor for MatchRecord objects.
   * @param roundNumber Match round number
   * @param dateAndTime Date and time match took place
   * @param stadiumName Stadium where game was played
   * @param homeTeam Home team in match
   * @param awayTeam Away team in match
   * @param group Team group
   * @param result Final score of game
   */
  public MatchRecord(String roundNumber, String dateAndTime, String stadiumName, String homeTeam, String awayTeam,
                     String group, String result) {
    this.roundNumber = roundNumber;
    this.dateAndTime = dateAndTime;
    this.stadiumName = stadiumName;
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.group = group;
    this.result = result;
  }

  /**
   * Secondary constructor for MatchRecord objects when match does not have a team group
   * @param roundNumber Match round number
   * @param dateAndTime Date and time match took place
   * @param stadiumName Stadium where game was played
   * @param homeTeam Home team in match
   * @param awayTeam Away team in match
   * @param result Final score of the game
   */
  public MatchRecord(String roundNumber, String dateAndTime, String stadiumName, String homeTeam, String awayTeam,
                     String result) {
    this.roundNumber = roundNumber;
    this.dateAndTime = dateAndTime;
    this.stadiumName = stadiumName;
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.group = null;
    this.result = result;
  }

  //Getters and Setters
  public String getRoundNumber() {
    return roundNumber;
  }

  public void setRoundNumber(String roundNumber) {
    this.roundNumber = roundNumber;
  }

  public String getDateAndTime() {
    return dateAndTime;
  }

  public void setDateAndTime(String dateAndTime) {
    this.dateAndTime = dateAndTime;
  }

  public String getStadiumName() {
    return stadiumName;
  }

  public void setStadiumName(String stadiumName) {
    this.stadiumName = stadiumName;
  }

  public String getHomeTeam() {
    return homeTeam;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public void setHomeTeam(String homeTeam) {
    this.homeTeam = homeTeam;
  }

  public String getAwayTeam() {
    return awayTeam;
  }

  public void setAwayTeam(String awayTeam) {
    this.awayTeam = awayTeam;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}

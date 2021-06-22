import java.util.*;

/**
 * CSCI 2110
 * MatchStats.java
 * This is an object class that stores MatchRecord objects in an unordered list and implements methods for analyzing
 * match statistics
 * Created by Mason Clarke, March 6, 2021
 */
public class MatchStats {

  private List<MatchRecord> matchList;

  /** Default constructor for MatchStats objects. Crates a new empty unordered List for storing MatchRecord objects */
  public MatchStats(List<MatchRecord> list) {
    this.matchList = list;
  }

  /**
   * Prints the names of all teams from a specified team group
   * @param group Group to be searched for
   */
  public void displayTeams(String group) {
    char groupChar = group.charAt(0);
    Set<String> teamNames = new HashSet<>();
    MatchRecord tempRecord = matchList.first();
    if (tempRecord.getGroup() != null && tempRecord.getGroup().charAt(6) == groupChar) {
      teamNames.add(tempRecord.getHomeTeam());
      teamNames.add(tempRecord.getAwayTeam());
    }
    for (int i = 0; i < matchList.size() - 1; i++) {
      tempRecord = matchList.next();
      if (tempRecord.getGroup() != null && tempRecord.getGroup().charAt(6) == groupChar) {
        teamNames.add(tempRecord.getHomeTeam());
        teamNames.add(tempRecord.getAwayTeam());
      }
    }
    String separator = "";
    for (String name : teamNames) {
      System.out.print(separator + name);
      separator = ", ";
    }
  }

  /**
   * Prints the number of matches played by a specified team.
   * @param team Team to calculate number of matches for
   * @return returns an int number of matches played
   */
  public int numMatches(String team) {
    int count = 0;
    MatchRecord tempRecord = matchList.first();
    if (tempRecord.getHomeTeam().equals(team) || tempRecord.getAwayTeam().equals(team)) {
      count++;
    }
    for (int i = 0; i < matchList.size() - 1; i++) {
      tempRecord = matchList.next();
      if (tempRecord.getHomeTeam().equals(team) || tempRecord.getAwayTeam().equals(team)) {
        count++;
      }
    }
    return count;
  }

  /**
   * This is a helper method for highestTotalGoals() and lowestTotalGoals() that scans the unordered list and calculates
   * the total number of goals scored by each team then stores the information in a HashMap.
   * @return HashMap containing information on goals scored by each team
   */
  private Map<String, Integer> teamGoals() {
    Map<String, Integer> goalsScored = new HashMap<>();
    MatchRecord tempRecord = matchList.first();
    goalsScored.put(tempRecord.getHomeTeam(), 0);
    goalsScored.put(tempRecord.getAwayTeam(), 0);
    for (int i = 0; i < matchList.size() - 1; i++) {
      tempRecord = matchList.next();
      goalsScored.put(tempRecord.getHomeTeam(), 0);
      goalsScored.put(tempRecord.getAwayTeam(), 0);
    }
    tempRecord = matchList.first();
    int homeTeamScore = Character.getNumericValue(tempRecord.getResult().charAt(0));
    int awayTeamScore = Character.getNumericValue(tempRecord.getResult().charAt(4));
    goalsScored.put(tempRecord.getHomeTeam(), goalsScored.get(tempRecord.getHomeTeam()) + homeTeamScore);
    goalsScored.put(tempRecord.getAwayTeam(), goalsScored.get(tempRecord.getAwayTeam()) + awayTeamScore);
    for (int i = 0; i < matchList.size() - 1; i++) {
      tempRecord = matchList.next();
      homeTeamScore = Character.getNumericValue(tempRecord.getResult().charAt(0));
      awayTeamScore = Character.getNumericValue(tempRecord.getResult().charAt(4));
      goalsScored.put(tempRecord.getHomeTeam(), goalsScored.get(tempRecord.getHomeTeam()) + homeTeamScore);
      goalsScored.put(tempRecord.getAwayTeam(), goalsScored.get(tempRecord.getAwayTeam()) + awayTeamScore);
    }
    return goalsScored;
  }

  /**
   * This method prints the names of all teams with the highest goals scored count. If multiple teams are tied it will
   * print the names of all teams.
   */
  public void highestTotalGoals() {
    int highestGoals = 0;
    Map<String, Integer> tempMap = teamGoals();
    ArrayList<String> teamNames =  new ArrayList<>();
    for (Map.Entry<String, Integer> entry : tempMap.entrySet()) {
      if (entry.getValue() > highestGoals) {
        highestGoals = entry.getValue();
      }
    }
    addToTeamNames(highestGoals, tempMap, teamNames);
  }

  /**
   * This method prints the name of the team with the lowest goal scored count. If multiple teams are tied it will
   * print the names of all teams.
   */
  public void lowestTotalGoals() {
    int lowestGoals = Integer.MAX_VALUE;
    Map<String, Integer> tempMap = teamGoals();
    ArrayList<String> teamNames =  new ArrayList<>();
    for (Map.Entry<String, Integer> entry : tempMap.entrySet()) {
      if (entry.getValue() < lowestGoals) {
        lowestGoals = entry.getValue();
      }
    }
    addToTeamNames(lowestGoals, tempMap, teamNames);

  }

  /**
   * This is a helper method used by highestTotalGoals() and lowestTotalGoals() which scans a HashMap of goal
   * information and places team names in an ArrayList if that teams total goals matches the specified goal number,
   * then prints the team names of all teams with that total number of goals
   * @param goals Number of goals scored to search for
   * @param tempMap HashMap of teams and their total goals scored
   * @param teamNames ArrayList the place team names on based on the input goals
   */
  private void addToTeamNames(int goals, Map<String, Integer> tempMap, ArrayList<String> teamNames) {
    for (Map.Entry<String, Integer> entry : tempMap.entrySet()) {
      if(entry.getValue() == goals) {
        teamNames.add(entry.getKey());
      }
    }
    String separator = "";
    for (String name : teamNames) {
      System.out.print(separator + name);
      separator = ", ";
    }
    System.out.println();
  }

  /**
   * This method prints match information for all matches played at a specified stadium. Stadium names must be input as
   * the full name with Stadium Name and city if they are designated as such.
   * @param stadium Stadium name to print match information for
   */
  public void matchesPlayedAt(String stadium) {
    MatchRecord tempRecord = matchList.first();
    if (tempRecord.getStadiumName().equals(stadium)) {
      System.out.println(tempRecord.getHomeTeam() + " vs. " + tempRecord.getAwayTeam() + " " + tempRecord.getResult());
    }
    for (int i = 0; i < matchList.size() - 1; i++) {
      tempRecord = matchList.next();
      if (tempRecord.getStadiumName().equals(stadium)) {
        System.out.println(tempRecord.getHomeTeam() + " vs. " + tempRecord.getAwayTeam() + " " +
                tempRecord.getResult());
      }
    }
  }

  /**
   * This method prints the match information for all matches played on a specific day. Match date must be entered in
   * in for form dd/mm/yyyy
   * @param date Date of the matchs played
   */
  public void matchesPlayedOn(String date) {
    MatchRecord tempRecord = matchList.first();
    if (tempRecord.getDateAndTime().substring(0,10).equals(date)) {
      System.out.println(tempRecord.getHomeTeam() + " vs. " + tempRecord.getAwayTeam() + " " + tempRecord.getResult());
    }
    for (int i = 0; i < matchList.size() - 1; i++) {
      tempRecord = matchList.next();
      if (tempRecord.getDateAndTime().substring(0,10).equals(date)) {
        System.out.println(tempRecord.getHomeTeam() + " vs " + tempRecord.getAwayTeam() + " " + tempRecord.getResult());
      }
    }
  }

  /**
   * This method takes in a string team name and prints the total number of matches played by the given team
   * @param teamName Team to calculate matches for
   */
  public void matchesPlayedBy(String teamName) {
    MatchRecord tempRecord = matchList.first();
    if (tempRecord.getHomeTeam().equals(teamName) || tempRecord.getAwayTeam().equals(teamName)) {
      System.out.println(tempRecord.getHomeTeam() + " vs. " + tempRecord.getAwayTeam() + " " + tempRecord.getResult());
    }
    for (int i = 0; i < matchList.size() - 1; i++) {
      tempRecord = matchList.next();
      if (tempRecord.getHomeTeam().equals(teamName) || tempRecord.getAwayTeam().equals(teamName)) {
        System.out.println(tempRecord.getHomeTeam() + " vs. " + tempRecord.getAwayTeam() + " " +
                tempRecord.getResult());
      }
    }
  }

  /**
   * This method calculates the total number of wins for each team in the unordered list and then prints the team
   * rankings with with the number of wins for each team in descending order.
   */
  public void displayTeamsTopDown() {
    Set<String> teamsToAdd = new HashSet<>();
    ArrayList<TeamWins> teamWins = new ArrayList<>();

    //Creat TeamWins object for each team and place in ArrayList
    MatchRecord tempRecord = matchList.first();
    teamsToAdd.add(tempRecord.getHomeTeam());
    teamsToAdd.add(tempRecord.getAwayTeam());
    TeamWins tempWin;
    for (int i = 0; i < matchList.size() - 1; i++) {
      tempRecord = matchList.next();
      teamsToAdd.add(tempRecord.getHomeTeam());
      teamsToAdd.add(tempRecord.getAwayTeam());
    }
    for (String name : teamsToAdd) {
     tempWin = new TeamWins(name);
      teamWins.add(tempWin);
    }

    //Determine winning teams and track win totals
    tempRecord = matchList.first();
    int homeScore = Character.getNumericValue(tempRecord.getResult().charAt(0));
    int awayScore = Character.getNumericValue(tempRecord.getResult().charAt(4));
    incrementWins(teamWins, tempRecord, homeScore, awayScore);
    for (int i = 0; i < matchList.size() - 1; i++) {
      tempRecord = matchList.next();
      homeScore = Character.getNumericValue(tempRecord.getResult().charAt(0));
      awayScore = Character.getNumericValue(tempRecord.getResult().charAt(4));
      incrementWins(teamWins, tempRecord, homeScore, awayScore);
    }

    //Sort teams based on wins and print team rankings
    teamWins.sort(new CustomComparator());
    for (int i = 0; i < teamWins.size(); i++) {
      System.out.println(i + 1 + ". Team: " + teamWins.get(i).getTeam() + " Wins: " + teamWins.get(i).getWins());
    }
  }

  /**
   * This is a helper method for displayTeamsTopDown() which determines the winner of a match based on the
   * MatchRecord and increments the number of wins for the winning team in the corresponding TeamWins object
   * @param teamWins ArrayList of TeamWins objects that contains the win record for all teams
   * @param tempRecord current MatchRecord object used to determine winning team
   * @param homeScore home team score
   * @param awayScore away team score
   */
  private void incrementWins(ArrayList<TeamWins> teamWins, MatchRecord tempRecord, int homeScore, int awayScore) {
    String winningTeam;
    if (homeScore == awayScore) {
      winningTeam = "";
    }
    else if(homeScore > awayScore) {
      winningTeam = tempRecord.getHomeTeam();
    } else {
      winningTeam = tempRecord.getAwayTeam();
    }
    for (TeamWins teamWin : teamWins) {
      if (teamWin.getTeam().equals(winningTeam)) {
        teamWin.setWins(teamWin.getWins() + 1);
      }
    }
  }
}

import java.util.Comparator;

/**
 * CSCI 2110
 * CustomComparator.java 
 * This is a Comparator class that is used for the purpose of sorting TeamWins objects based on the number of wins
 * for each team.
 * Created by Mason Clarke, March 6, 2021
 */
public class CustomComparator implements Comparator<TeamWins> {

  @Override
  public int compare(TeamWins team1, TeamWins team2) {
    return team2.getWins() - team1.getWins();
  }
}

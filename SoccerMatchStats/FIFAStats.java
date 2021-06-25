import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * CSCI 2110
 * FIFAStats.java
 * This is a demo program for the purpose of testing the MatchStats class. This class takes in a file destination, reads
 * in match information from the file and creates MatchRecord objects to be stored in an unordered List.
 * Created by Mason Clarke, March 6, 2021
 */
public class FIFAStats {

  public static void main(String [] args) throws IOException {

    //Create Scanners to take in File name and read info from file
    Scanner in = new Scanner(System.in);
    System.out.println("Please enter filename:");
    String fileName = in.nextLine();
    File file = new File(fileName);
    Scanner inputFile = new Scanner(file);

    //Create unordered List to store MatchRecord objects then read info from file for each Match
    List<MatchRecord> matchList = new List<>();
    while(inputFile.hasNext()) {
      String round = inputFile.nextLine();
      if (round.length() == 1) {
        String dateAndTime = inputFile.nextLine();
        String location = inputFile.nextLine();
        String homeTeam = inputFile.nextLine();
        String awayTeam = inputFile.nextLine();
        String group = inputFile.nextLine();
        String result = inputFile.nextLine();
        MatchRecord tempRecord = new MatchRecord(round, dateAndTime, location, homeTeam, awayTeam, group, result);        
      } else {
        String dateAndTime = inputFile.nextLine();
        String location = inputFile.nextLine();
        String homeTeam = inputFile.nextLine();
        String awayTeam = inputFile.nextLine();
        String result = inputFile.nextLine();
        MatchRecord tempRecord = new MatchRecord(round, dateAndTime, location, homeTeam, awayTeam, result);        
      }
      matchList.add(tempRecord);
    }

    //Test displayTeams()
    System.out.println("The following teams were in Group A");
    MatchStats matchStats = new MatchStats(matchList);
    matchStats.displayTeams("A");
    System.out.println("\n");

    //Test numMatches()
    System.out.println("How many matches did France play?");
    int franceMatches = matchStats.numMatches("France");
    System.out.println("France played " + franceMatches + " matches");
    System.out.println();


    //Test highestTotalGoals()
    System.out.println("The teams that scored the highest number of goals were:");
    matchStats.highestTotalGoals();
    System.out.println();

    //Test lowestTotalGoals()
    System.out.println("The teams that scored the lowest number of goals were:");
    matchStats.lowestTotalGoals();
    System.out.println();

    //Test matchesPlayedAt()
    System.out.println("The following were the matches played at Luzhniki Stadium, Moscow:");
    matchStats.matchesPlayedAt("Luzhniki Stadium, Moscow");
    System.out.println();

    //Test matchesPlayedOn()
    System.out.println("The following matches were played on 17/06/2018");
    matchStats.matchesPlayedOn("17/06/2018");
    System.out.println();

    //Test matchesPlayedBy()
    System.out.println("Following is the list of games played by Sweden:");
    matchStats.matchesPlayedBy("Sweden");
    System.out.println();

    //Test displayTeamsTopDown()
    System.out.println("Following is the ranking of all teams from the Tournament:");
    matchStats.displayTeamsTopDown();
  }
}

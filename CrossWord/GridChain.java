import java.util.*;
import java.util.LinkedList;
import java.util.Scanner; 

/**
 * CSCI 2110
 * @author Mason Clarke
 * @version 1.0
 * A program that will read in the data for and construct an NxN word search grid and a list of words,
 * then will determine if the grid is chained.
 * Assumptions: A grid is considered "chained" if for every found word in the grid, there is a direct
 * path of links between every other found word.
 */
public class GridChain {

  public static void main (String [] args) {
    Scanner in = new Scanner(System.in);

    // Read in size of grid (N X N)
    int gridSize = in.nextInt();

    //Read in the grid of letters
    char [][] letterGrid = new char [gridSize][gridSize];

    //Read in lines of text, populate array
    for (int i = 0; i < gridSize ; i ++) {
      for (int j = 0; j < gridSize; j ++) {
        String letterToPut = in.next();
        char letterChar = letterToPut.charAt(0);
        letterGrid[i][j] = letterChar;
      }
    }

    //Read in size of word list
    int wordListSize = in.nextInt();

    //Read in W lines of words
    String [] wordList = new String [wordListSize];

    for (int i = 0; i < wordListSize; i ++) {
      wordList[i] = in.next();
    }

    ArrayList<WordLocation> foundWordLocations = new ArrayList<>();

    //Scan for word first letter match
    for (String wordToCheck : wordList) {
      for (int j = 0; j < gridSize; j++) {
        for (int k = 0; k < gridSize; k++) {
          if (letterGrid[j][k] == wordToCheck.charAt(0)) {
            ArrayList<WordLocation> tempLocations = findWord(j, k, gridSize, wordToCheck, letterGrid);
            foundWordLocations.addAll(tempLocations);
          }
        }
      }
    }

    //Print Chained Status
    if (isGridChained(foundWordLocations)) {
      System.out.println("Grid is chained");
    }
  }

  /*
   * The following method was adapted from original code at
   * URL: https://www.geeksforgeeks.org/search-a-word-in-a-2d-grid-of-characters/
   * Retrieved on Jan 16, 2020
   * Author: Rachana Soma
   */
  /**
   * Given a matching first word letter, this method searches the grid
   * in all 8 directions for a word match and returns matches found as an int
   * @param currRow grid starting row position
   * @param currCol grid starting column position
   * @param gridSize the NxN size of the grid
   * @param word the word the find
   * @param grid the word grid to search
   * @return int # of word matches found
   */
  private static ArrayList<WordLocation> findWord (int currRow, int currCol, int gridSize, String word, char [][] grid) {

    ArrayList <WordLocation> foundWords = new ArrayList<>();
    int [] colPosition = {0,1,1,1,0,-1,-1,-1};
    int [] rowPosition = {1,1,0,-1,-1,-1,0,1};
    int wordLength = word.length();

    for (int i = 0; i < 8; i++) {

      int colShift = currCol + colPosition[i];
      int rowShift = currRow + rowPosition[i];
      int startPos;

      for (startPos = 1; startPos < wordLength; startPos ++) {

        //handle out of bounds before searching position
        if (colShift >= gridSize || colShift < 0 || rowShift >= gridSize || rowShift < 0) {
          break;
        }

        //If next letter doesn't match break
        if(grid[rowShift][colShift] != word.charAt(startPos)) {
          break;
        }

        //shift to next position to search
        colShift += colPosition[i];
        rowShift += rowPosition[i];
      }

      if (startPos == wordLength) {
        foundWords.add(new WordLocation(word, currRow, currCol, rowPosition[i] ,colPosition[i]));
      }
    }

    return foundWords;
  }
  /* Cited code ends*/

  /**
   * Given a single list of WordLocation objects, will determine if there is a
   * continuous chain of connections between all found words.
   * @param locList list of found wordLocation objects
   * @return Will return true if grid is chained, and a list of unchained words if the grid is not.
   */
  private static boolean isGridChained(ArrayList<WordLocation> locList) {
    ArrayList<WordLocation> tempArray = new ArrayList<>();
    WordLocation tempLocation = locList.remove(0);
    tempArray.add(tempLocation);
    while(locList.size() > 0) {
      WordLocation locationFound = findIntersection(tempArray, locList);
      if (locationFound != null) {
        locList.remove(locationFound);
        tempArray.add(locationFound);
      } else {
        String tempString = null;

        for (WordLocation notMatch: locList) {
          if (tempString == notMatch.locationName) {
          } else {
            System.out.println(notMatch.locationName);
          }
          tempString = notMatch.locationName;
        }

        return false;
      }
    }

    return true;
  }


  /**
   * Given two lists of WordLocation objects, will find if there are
   * any words which share a distinct letter location between lists
   * @param pushedToList List WordLocations are being pushed to if found
   * @param pulledFromList List WordLocations are being pull from to find
   * @return returns WordLocation from pulledFromList that intersects WordLocation from pushedToList, or null
   * if no intersection found
   */
  private static WordLocation findIntersection (ArrayList<WordLocation> pushedToList, ArrayList<WordLocation> pulledFromList) {
    for (WordLocation wLocPullFrom: pulledFromList) {
      int [][] tempArrayPull = wLocPullFrom.letterLocations.clone();

      for (WordLocation wLocPushTo: pushedToList) {
        int [][] tempArrayPush = wLocPushTo.letterLocations.clone();

        for (int[] ints : tempArrayPull) {
          for (int[] arrayPush : tempArrayPush) {
            if (Arrays.equals(arrayPush, ints)) {
              return wLocPullFrom;
            }
          }
        }
      }
    }

    return null;
  }
}



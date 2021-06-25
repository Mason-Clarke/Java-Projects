
import java.util.*;

/**
 * CSCI 2110
 * @author Mason Clarke 
 * @version 1.0
 * A program that will take in the size of an NxN word search grid, the lines of letters of the grid, a number
 * of words and a word list, then searches the grid to find the number of matches of each word.
 */
public class GridSearch{

  public static void main(String[] args) {
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

    //Populate word list
    for (int i = 0; i < wordListSize; i++) {
      wordList[i] = in.next();
    }

    // Array to store match counts
    int [] matches = new int [wordListSize];

    // Search for first letter of each word
    for (int i = 0; i < wordList.length; i++) {
      String wordToCheck = wordList[i];

      for (int j = 0; j < gridSize; j++) {
        for (int k = 0; k < gridSize; k++) {
          if(letterGrid[j][k] == wordToCheck.charAt(0)) {
            matches[i] += findWord(j, k, gridSize, wordToCheck, letterGrid);
          }
        }
      }
    }

    //Print words and how many matches of each found
    for (int i = 0; i < wordListSize; i ++) {
      System.out.println(matches[i] + " " + wordList[i]);
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
  static int findWord (int currRow, int currCol, int gridSize, String word, char [][] grid) {

    int [] colPosition = {0,1,1,1,0,-1,-1,-1};
    int [] rowPosition = {1,1,0,-1,-1,-1,0,1};
    int wordLength = word.length();
    int matchesFound = 0;

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
        matchesFound ++;
      }
    }

    return matchesFound;
  }
  /* Cited code ends*/
}

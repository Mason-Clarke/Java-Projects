import java.util.*;
import java.util.LinkedList;
import java.util.Scanner;

public class GridChained {

  public static void main (String [] args){
    //Crete Scanner
    Scanner in = new Scanner(System.in);

    // Read in size of grid (N X N)
    int gridSize = in.nextInt();

    //Read in the grid of letters
    char [][] letterGrid = new char [gridSize][gridSize];
//    letterGrid = buildGrid(gridSize);


    //Read in lines of text, populate array
    for (int i = 0; i < gridSize ; i ++){
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

    for (int i = 0; i < wordListSize; i++) {
      wordList[i] = in.next();
    }

    LinkedList<WordLocation> foundWordLocations = new LinkedList<WordLocation>();
  }


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
  static int findWord (int currRow, int currCol, int gridSize, String word, char [][] grid){

    int [] colPosition = {0,1,1,1,0,-1,-1,-1};
    int [] rowPosition = {1,1,0,-1,-1,-1,0,1};
    int wordLength = word.length();
    int matchesFound = 0;

    for (int i = 0; i < 8; i++){

      int colShift = currCol + colPosition[i];
      int rowShift = currRow + rowPosition[i];
      int startPos;

      for (startPos = 1; startPos < wordLength; startPos ++){
        //handle out of bounds before searching position
        if (colShift >= gridSize || colShift < 0 || rowShift >= gridSize || rowShift < 0){
          break;
        }
        //If next letter doesn't match break
        if(grid[rowShift][colShift] != word.charAt(startPos)){
          break;
        }
        //shift to next position to search
        colShift += colPosition[i];
        rowShift += rowPosition[i];
      }
      if (startPos == wordLength){
        matchesFound ++;
      }
    }
    return matchesFound;
  }
}



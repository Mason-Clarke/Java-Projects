import java.util.*;

/**
 * CSCI 2110
 * @author Mason Clarke
 * @version 1.0
 *  a word search program
 *
 *
 */

public class GridEquiv {
  public static void main(String[] args) {
    //Crete Scanner
    Scanner in = new Scanner(System.in);

    // Read in size of grid (N X N)
    int gridSize1 = in.nextInt();

    //Read in the grid of letters
    char [][] letterGrid1 = new char[gridSize1][gridSize1];

    //Read in lines of text, populate array
    for (int i = 0; i < gridSize1 ; i ++){
      for (int j = 0; j < gridSize1; j ++) {
        String letterToPut = in.next();
        char letterChar = letterToPut.charAt(0);
        letterGrid1[i][j] = letterChar;
      }
		}
    int gridSize2 = in.nextInt();

    //Read in the grid of letters
    char [][] letterGrid2 = new char[gridSize2][gridSize2];

    //Read in lines of text, populate array
    for (int i = 0; i < gridSize2 ; i ++){
      for (int j = 0; j < gridSize2; j ++) {
        String letterToPut = in.next();
        char letterChar = letterToPut.charAt(0);
        letterGrid2[i][j] = letterChar;
      }
    }

    //Read in size of word list
    int wordListSize = in.nextInt();

    //Read in W lines of words
    String [] wordList = new String [wordListSize];
    for (int i = 0; i < wordListSize; i++) {
      wordList[i] = in.next();
    }


    int [] matches1 = new int [wordListSize];

    // Search for words
    //@edgecase wordlist is empty
    for (int i = 0; i < wordList.length; i++) {
      String wordToCheck = wordList[i];

      for (int j = 0; j < gridSize1; j++) {
        for (int k = 0; k < gridSize1; k++) {
          if(letterGrid1[j][k] == wordToCheck.charAt(0)){
            matches1[i] += findWord(j, k, gridSize1, wordToCheck, letterGrid1);
          }
        }
      }
    }
    int [] matches2 = new int [wordListSize];

      // Search for words
      //@edgecase wordlist is empty
    for (int i = 0; i < wordList.length; i++) {
        String wordToCheck = wordList[i];

        for (int j = 0; j < gridSize2; j++) {
            for (int k = 0; k < gridSize2; k++) {
                if(letterGrid2[j][k] == wordToCheck.charAt(0)){
                    matches2[i] += findWord(j, k, gridSize2, wordToCheck, letterGrid2);
                }
            }
        }
    }

    if (Arrays.equals(matches1, matches2)){
      System.out.print("Grids are equivalent");
    }
    else {
      for(int i = 0; i < wordListSize; i++){
        if(matches1[i] != matches2[i]){
          System.out.println(wordList[i]);
        }
      }
    }
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
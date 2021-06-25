/**
 * CSCI 2110
 * @author Mason Clarke 
 * @version 1.0
 * A WordLocation class that when a word is found in a word search grid allows for the creation
 * of a WordLocation object that stores the individual letter locations of the found word in the grid.
 */
public class WordLocation {

  public String locationName;
  public int rowStart, columnStar;
  public int colDirection, rowDirection;
  public int [][] letterLocations;

  /**
   * Constructor for WordLocation Objects
   * @param word The string word that was found
   * @param row The row position in the grid of the first word letter
   * @param col The column position in the grid of the first word letter
   * @param rowDir an integer representing the row direction of the word
   * @param colDir an integer representing the column direction of the word
   */
  public WordLocation(String word, int row, int col, int rowDir, int colDir) {
    this.locationName = word;
    this.rowStart = row;
    this.columnStar = col;
    this.rowDirection = rowDir;
    this.colDirection = colDir;

    letterLocations = new int[word.length()][2];
    int rowTemp = row;
    int colTemp = col;

    for (int i = 0; i < word.length(); i++) {
      letterLocations[i][0] = rowTemp;
      letterLocations[i][1] = colTemp;
      rowTemp += rowDirection;
      colTemp += colDirection;
    }
  }
}

public class WordLocation {


  public String locationName;
  public int rowStart, columnStar;
  public int colDirection, rowDirection;
  public int [][] letterLocations;




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

      System.out.println("After adding to locations");
      for (int nums : letterLocations[i]
      ) {
        System.out.println(nums);
      }

      rowTemp += rowDirection;
      colTemp += colDirection;

      System.out.println("col and row temp after update");

      System.out.println(rowTemp);
      System.out.println(colTemp);
    }
  }




}

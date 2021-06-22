public class LocationsTester {

  public static void main (String [] args){

    WordLocation word1 = new WordLocation("cart", 5,4, -1,-1);

    System.out.println("Post create");
    for (int l = 0; l < 4 ; l++) {
      for (int m = 0; m < 2; m++) {
        System.out.println(word1.letterLocations[l][m]);
      }
    }

  }
}

import java.util.*;

/**
 * A demo class which creates new Field, Ball and two player objects, generates random numbers for and then performs
 * ten kicks for each player, then displays kick and Ball location information
 * @author Mason Clarke
 * @date Feb 1, 2021
 */
public class Demo {

  public static void main (String [] args){

    //Create new Field, Ball and two Player objects
    Field f1 = new Field(0, 0, 300.0, 200.0);
    Ball b1 = new Ball (20.0, 20.0);
    Player p1 = new Player("Mason");
    Player p2 = new Player("Kyle");

    //Create arrays to hold kick distances and angles
    double [] p1Kicks= new double [10];
    double [] p2Kicks = new double [10];
    double [] p1Angles = new double [10];
    double [] p2Angles = new double [10];

    //Generate random kick distances for both Players
    for(int i = 0; i < 10; i++){
      p1Kicks[i] = Math.random() * 150 + 1;
      p2Kicks[i] = Math.random() * 150 + 1;
    }

    //Generate random kick angles for both Players
    for (int i = 0; i < 10; i++){
      p1Angles[i] = Math.random() * 360 + 1;
      p2Angles[i] = Math.random() * 360 + 1;
    }

    //Print out field, player and ball setup
    System.out.println("SOCCER GAME SETUP!");
    System.out.println(p1.toString());
    System.out.println(p2.toString());
    System.out.println(f1.toString());
    System.out.println(b1.toString());

    //Counter for player turn
    int playerNumberOne = 0;
    int playerNumberTwo = 0;

    //Perform player kicks
    for(int i = 0; i < 10; i++){
      p1.kick(f1, b1, p1Kicks[playerNumberOne], p1Angles[playerNumberOne]);
      playerNumberOne += 1;
      p2.kick(f1, b1, p2Kicks[playerNumberTwo], p2Angles[playerNumberTwo]);
      playerNumberTwo += 1;
    }
  }
}

import java.util.*;

/**
 * A class for creating objects of the Player class that contains methods for displaying player information as well
 * as allowing the player to perform kicks on Ball objects
 * @author Mason Clarke
 * @date Feb 1, 2021
 */
public class Player {

  //Initialize instance variables
  private String name;

  /**
   * Constructor for objects of type Player
   * @param name Name of the player
   */
  public Player (String name){
    this.name = name;
  }

  //Overridden toString method
  public String toString(){
    return "Player " + name;
  }

  /**
   * Equals method for compaing doubles
   * @param first double value to be tested
   * @param second double value to be tested
   * @return boolean, true if double values are within range of preset value eps
   */
  public boolean equals(double first, double second) {
    double eps = 10 ^ -8;
    if (first - second <= eps || first - second >= -(eps)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Method for simulating a player kicking a ball. If the ball hits a boundary wall of the given Field then it is
   * reflected. If the kick would leave the ball outside one boundary it is singly reflected and that axis value is
   * reset. If it would lay outside two boundaries then both are reset to the original position
   * @param f Field object which contains the Ball
   * @param b Ball object to be kicked
   * @param d Distance the Ball object is to be kicked
   * @param r Direction the Ball object is to be kicked in degees
   */
  public void kick (Field f, Ball b, double d, double r){
    double r_radians = Math.toRadians(r);
    double sinValue = Math.sin(r_radians);
    double cosValue = Math.cos(r_radians);
    double newX = b.getBx() + (d*cosValue);
    double newY = b.getBy() - (d*sinValue);
    System.out.printf("Player %s kicks the ball for a distance of %.1f pixels at %.1f degrees\n", name, d, r);
    if ((newX < f.xPos && newY < f.yPos) || (newX < f.xPos && newY > (f.yPos + f.width)) || (newX > (f.xPos + f.length))
            && (newY > (f.yPos + f.width)) || (newX > (f.xPos + f.length) && (newY < f.yPos))){
      b.setBx(b.getBx());
      b.setBy(b.getBy());
      System.out.println("Ball was  double reflected and reset to original position");
      System.out.println( b.toString());
      return;
    }
    if (((newX < (f.xPos + f.length)) && (newX > f.xPos)) && ((newY < (f.yPos + f.width)) && (newY > f.yPos))){
      b.setBx(newX);
      b.setBy(newY);
      System.out.println( b.toString());
      return;
    }
    if (((newY < f.yPos) && (newX < (f.xPos + f.length))) || ((newY > (f.yPos + f.width)) &&
            (newX < (f.xPos + f.length)))) {
      b.setBx(newX);
      System.out.println("Ball was reflected");
      System.out.println( b.toString());
      return;
    }
    if ((newX < f.xPos && (newY < (f.yPos + f.width))) || ((newX > (f.xPos + f.length)) &&
            (newY < (f.yPos + f.width)))) {
      System.out.println("Ball was reflected");
      b.setBy(newY);
      System.out.println( b.toString());
    }
  }
}

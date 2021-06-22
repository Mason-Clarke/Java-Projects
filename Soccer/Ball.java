import java.util.*;

/**
 * A class for creating and displaying information for Ball objects. A Ball is an object that has a 2 point
 * representation  on a 2D plane.
 * @author Mason Clarke
 * @date Feb 1, 2021
 */
public class Ball {

  //Initialize instance variables
  private double bx, by;

  /**
   * Constructor for Ball objects.
   * @param x x-coordinate of Ball
   * @param y y-coordinate of Ball
   */
  public Ball (double x, double y){
    this.bx = x;
    this.by = y;
  }

  //Getters and Setters
  public double getBx() {
    return bx;
  }

  public double getBy() {
    return by;
  }

  public void setBx(double bx) {
    this.bx = bx;
  }

  public void setBy(double by) {
    this.by = by;
  }

  //Overridden toString method
  public String toString (){
    return String.format("Ball position: %.1f, %.1f\n", bx, by);
  }
}

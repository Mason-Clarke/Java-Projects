import java.util.*;

/**
 * A class for creating Field objects that will be the grounds for Players performing kicks on Ball objects. The Field
 * is a simple 2D plane.
 * @author Mason Clarke
 * @date Feb 1, 2021
 */
public class Field {
  //Initialize field instance variables
  double xPos, yPos, length, width;

  /**
   * Constructor for objects of Field type. Instantiates a field object with given starting x and y position, and
   * length and width
   * @param xPos starting x coordinate
   * @param yPos starting y coordinate
   * @param length field length
   * @param width field width
   */
  public Field(double xPos, double yPos, double length, double width){
    this.xPos = xPos;
    this.yPos = yPos;
    this.length = length;
    this.width = width;
  }

  //Overridden toString method to return field position and dimensions
  public String toString(){
    return String.format("Field: [%.1f, %.1f] %.1f, %.1f", xPos, yPos, length, width);
  }

}

import java.util.Comparator;

/**
 * CSCI 2110
 * Pair.java
 * This is an object class used to link a specific character to the probability of occurrence found in a text file
 * Created by Mason Clarke, Mar 30, 2021
 */
public class Pair implements Comparable<Pair>, Comparator<Pair> {

  private char value;
  private double prob;

  //Empty constructor for use in Comparator sorting
  public Pair(){
  }

  /**
   * Default constructor for Pair objects
   * @param value character to be stored
   * @param prob probability of occurrence in text file
   */
  public Pair(char value, double prob) {
    this.value = value;
    this.prob = prob;
  }

  //Getters and Setters
  public char getValue() {
    return value;
  }

  public void setValue(char value) {
    this.value = value;
  }

  public double getProb() {
    return prob;
  }

  public void setProb(double prob) {
    this.prob = prob;
  }

  //Overridden toString method for displaying pair data
  @Override
  public String toString() {
    return "Pair{" +
            "value=" + value +
            ", prob=" + prob +
            '}';
  }

  /**
   * Overridden compareTo method used for comparing Pair objects based on the stored probabilities
   * @param p pair object to compare this to
   * @return returns negative if p has a higher probability than this pair, positive if the opposite or 0 if they are
   * the same
   */
  @Override
  public int compareTo(Pair p) {
    return Double.compare(this.getProb(), p.getProb());
  }

  /**
   * Overridden compare method used for sorting lists of Pair objects
   * @param o1 first pair to compare
   * @param o2 second Pair to compare
   * @return returns negative if o1.probability less than o2.probability, positive if the opposite ore 0 if the same
   */
  @Override
  public int compare(Pair o1, Pair o2) {
    return o1.compareTo(o2);
  }
}

/**
 * CSCI 2110
 * CodeCharPair.java
 * This object class is used to create CodeCharPair objects that store a specific character and the Huffman code linked
 * to that character
 * Created by Mason Clarke, April 1, 2021
 */
public class CodeCharPair {

  private String code;
  private String character;

  /**
   * Constructor for CodeCharPair objects
   * @param code string representation of the Huffman Code
   * @param character String representation of the character
   */
  public CodeCharPair(String code, String character) {
    this.code = code;
    this.character = character;
  }

  //Getters and Setters
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCharacter() {
    return character;
  }

  public void setCharacter(String s) {
    this.character = character;
  }
}

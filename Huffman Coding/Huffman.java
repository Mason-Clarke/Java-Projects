import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * CSCI 2110
 * Huffman.java
 * This class has methods for encoding and decoding text files using Huffman Coding.
 */
public class Huffman {

  /**
   * This method is used for encoding text files using Huffman Coding. It reads in a text file, creates a Huffman Tree,
   * uses that tree to create a set of Huffman codes for that text file, then encodes and outputs an encoded text file
   * and a text file containing the huffman codes.
   * @throws IOException will throw IOException if the specified input filepath is incorrect
   */
  public static void encode () throws IOException {

    //Take input from user specifying file path
    Scanner in = new Scanner(System.in);
    System.out.print("Please enter the path of the file to encode: ");
    String fileName = in.nextLine();
    File file = new File(fileName);
    Scanner inputFile = new Scanner(file);

    //Calculate frequency of char in text file and create char/probability Pairs
    int [] freq = new int[256];
    String text = "";
    while (inputFile.hasNextLine()) {
      text += inputFile.nextLine() + "\n";
    }
    inputFile.close();
    char [] chars = text.replaceAll("\\s", "").toCharArray();
    for (char c : chars) {
      freq[c] ++;
    }
    ArrayList<Pair> pairList = new ArrayList<>();
    for (int i = 0; i < freq.length; i++) {
      if (freq[i] != 0){
        char c = (char) i;
        pairList.add(new Pair(c, Math.round(freq[i] * 10000d/chars.length)/10000d));
      }
    }

    //Build Huffman Tree
    ArrayList<BinaryTree<Pair>> firstQueue = new ArrayList<>();
    ArrayList<BinaryTree<Pair>> secondQueue = new ArrayList<>();
    ArrayList<Pair> pairCopy = new ArrayList<>(pairList); //For later use
    pairList.sort(new Pair());
    for (Pair p : pairList) {
      BinaryTree<Pair> tempTree = new BinaryTree<>();
      tempTree.makeRoot(p);
      firstQueue.add(tempTree);
    }
    BinaryTree<Pair> firstTree;
    BinaryTree<Pair> secondTree;
    while(firstQueue.size() > 1) {
      if (secondQueue.isEmpty()) {
        firstTree = firstQueue.remove(0);
        secondTree = firstQueue.remove(0);
      } else {
        if (firstQueue.get(0).getData().compareTo(secondQueue.get(0).getData()) < 0) {
          firstTree = firstQueue.remove(0);
        } else {
          firstTree = secondQueue.remove(0);
        }
        if (secondQueue.isEmpty()) {
          secondTree = firstQueue.remove(0);
        } else {
          if (firstQueue.get(0).getData().compareTo(secondQueue.get(0).getData()) < 0) {
            secondTree = firstQueue.remove(0);
          } else {
            secondTree = secondQueue.remove(0);
          }
        }
      }
      secondQueue.add(newTree(firstTree, secondTree));
    }
    firstTree = firstQueue.remove(0);
    secondTree= secondQueue.remove(0);
    secondQueue.add(newTree(firstTree, secondTree));
    while(secondQueue.size() > 1) {
      firstTree = secondQueue.remove(0);
      secondTree = secondQueue.remove(0);
      secondQueue.add(newTree(firstTree, secondTree));
    }
    BinaryTree<Pair> huffmanTree = secondQueue.remove(0);

    //Generate txt file with Huffman Codes for each char
    PrintWriter output = new PrintWriter("Huffman.txt");
    String [] codeList = findEncoding(huffmanTree);
    output.println("Symbol\tProb.\t\tHuffman Code");
    output.flush();
    for (int i = 0; i < pairCopy.size(); i++) {
      double prob = pairCopy.get(i).getProb();
      char c = pairCopy.get(i).getValue();
      String charPrint = Character.toString(c);
      output.printf("%s\t\t%.4f\t\t%s\n", charPrint, prob, codeList[c]);
      output.flush();
    }
    System.out.println("Printing codes to Huffman.txt");

    //Encode file
    file = new File(fileName);
    inputFile = new Scanner(file);
    output = new PrintWriter("Encoded.txt");
    while (inputFile.hasNextLine()) {
      String textToEncode = inputFile.nextLine();
      for (int i = 0; i < textToEncode.length(); i++) {
        if (textToEncode.charAt(i) != ' ') {
          int encodeNumber = textToEncode.charAt(i);
          output.print(codeList[encodeNumber]);
        } else {
          output.print(" ");
        }
        output.flush();
      }
      output.println();
    }
    System.out.println("Printing encoded text to Encoded.txt\n\n*\t*\t*\t*\t*\n");
  }

  /**
   * Method for creating a new binary tree as the root for two existing binary trees, then attaching them as leaves. The
   * new binary tree root contains the summed probabilities of the leaf nodes and a stand in character.
   * @param tree1 tree to be left child of new root
   * @param tree2 tree to the right child of new root
   * @return a new binary tree root with two children
   */
  public static BinaryTree<Pair> newTree(BinaryTree<Pair> tree1, BinaryTree<Pair> tree2) {
    BinaryTree<Pair> newRoot = new BinaryTree<>();
    newRoot.makeRoot(new Pair('⁂', tree1.getData().getProb() + tree2.getData().getProb()));
    newRoot.attachLeft(tree1);
    newRoot.attachRight(tree2);
    return newRoot;
  }

  /**
   * Wrapper method for calling findEncoding
   * @param bt binary tree object
   * @return a string array containing Huffman codes
   */
  public static String[] findEncoding(BinaryTree<Pair> bt) {
    String [] result = new String[256];
    findEncoding(bt, result, "");
    return result;
  }

  /**
   * This method finds the appropriate huffman codes for the leaf nodes in a huffman tree and appends them to a String
   * array
   * @param bt binary tree to find codes for
   * @param a String array to store codes in
   * @param prefix initially an empty string used to build the code from tree traversal
   */
  public static void findEncoding(BinaryTree<Pair> bt, String[] a, String prefix) {
    if (bt.getLeft() == null && bt.getRight() == null) {
      a[bt.getData().getValue()] = prefix;
    } else {
      findEncoding(bt.getLeft(), a, prefix+"0");
      findEncoding(bt.getRight(), a, prefix+"1");
    }
  }

  /**
   * This method is used to decode an encoded message. It takes in a file with the encoded message and a file containing
   * the Huffman codes for each character, then produces a txt file containing the decoded message.
   * @throws IOException throws IOException if either input file paths are incorrect
   */
  public static void decode() throws IOException {

    //Take input for Encoded file
    Scanner in = new Scanner(System.in);
    System.out.print("Please enter the path of the encoded file: ");
    String fileName = in.nextLine();
    File encodedFile = new File(fileName);

    //Take input for Huffman Code file and create list of CodeCharPair objects
    System.out.print("Please enter the path of the file containing Huffman Codes: ");
    fileName = in.nextLine();
    File huffmanCodes = new File(fileName);
    Scanner inputFile = new Scanner(huffmanCodes);
    ArrayList<CodeCharPair> pairs = new ArrayList<>();
    String throwAwayLine = inputFile.nextLine();
    while (inputFile.hasNext()) {
      String character = inputFile.next();
      inputFile.next();
      String code = inputFile.next();
      CodeCharPair tempPair = new CodeCharPair(code, character);
      pairs.add(tempPair);
    }
    inputFile.close();

    //read each character in text and convert to proper letter
    PrintWriter output = new PrintWriter("Decoded.txt");
    String builtCode = "";
    String text;
    inputFile = new Scanner(encodedFile);
    while (inputFile.hasNextLine()) {
      text = inputFile.nextLine();
      for(int i = 0; i < text.length(); i++) {
        builtCode += text.charAt(i);
        if (!builtCode.equals(" ")) {
          for (CodeCharPair c : pairs) {
            if (builtCode.equals(c.getCode())) {
              output.print(c.getCharacter());
              builtCode = "";
              output.flush();
              break;
            }
          }
        } else {
          output.print(" ");
          output.flush();
          builtCode = "";
        }
      }
      if (inputFile.hasNextLine()) {
        output.println();
        output.flush();
      }
    }
    inputFile.close();
    System.out.println("Printing decoded text to Decoded.txt");
  }
}




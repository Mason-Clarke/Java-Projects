import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/** 
 * CSCI 2110
 * Assignment4.java
 * This class contains methods for manipulating OrderedLists to merge, determine differences between, and determine
 * commonalities between them. It also has a demo program which reads in a list of words from a text file, creates
 * several OrderedLists and performs manipulations on them.
 * Created by Mason Clarke, March 20, 2021
 */
public class Assignment4 {

  public static void main(String [] args) throws FileNotFoundException {

    //Create scanner and prompt user for file path
    Scanner in = new Scanner(System.in);
    System.out.println("Please enter file path:");
    String fileName = in.nextLine();
    File file = new File(fileName);
    Scanner inputFile = new Scanner(file);

    //Take in length of first list and generate exclusive random number list1
    System.out.println("Please enter an integer n1 between 1000 and 1500 (inclusive)");
    int n1 = in.nextInt();
    ArrayList<Integer> intArray1 = new ArrayList<>();
    generateNumbers(intArray1, n1, 2000);

    //Take in length of second list and generate exclusive random number list2
    System.out.println("Please enter an integer n2 between 1000 and 1500 (inclusive)");
    int n2 = in.nextInt();
    ArrayList<Integer> intArray2 = new ArrayList<>();
    generateNumbers(intArray2, n2, 2000);

    //Take in length of third list and generate exclusive random number list3
    System.out.println("Please enter an integer n3 between 1000 and 1500 (inclusive)");
    int n3 = in.nextInt();
    ArrayList<Integer> intArray3 = new ArrayList<>();
    generateNumbers(intArray3, n3, 2000);


    //2a. Create array list of all words from RandomWords.txt
    ArrayList<String> wordList = new ArrayList<>();
    while (inputFile.hasNext()) {
      String wordToAdd = inputFile.nextLine();
      if (!wordToAdd.equals("")) {
        wordList.add(wordToAdd);
      }
    }

    //2b. Create orderedList1
    OrderedList<String> orderedList1 = new OrderedList<>();
    for (int i = 0; i < n1; i++) {
      orderedList1.insert(wordList.get(intArray1.get(i)));
    }
    System.out.print("List 1: ");
    orderedList1.enumerate();
    System.out.println();

    //2c. Create orderedList2
    OrderedList<String> orderedList2 = new OrderedList<>();
    for (int i = 0; i < n2; i++) {
      orderedList2.insert(wordList.get(intArray2.get(i)));
    }
    System.out.print("List 2: ");
    orderedList2.enumerate();
    System.out.println();

    //2d. Create list by merging orderedList1 and orderedList2
    OrderedList<String> list1 = merge(orderedList1, orderedList2);
    System.out.print("Merged List: ");
    list1.enumerate();
    System.out.println();

    //2e. Create list of common items between orderedList1 and orderedList2
    OrderedList<String> list2 = common(orderedList1, orderedList2);
    System.out.print("Common Words: ");
    list2.enumerate();
    System.out.println();

    //2f. Creat ordered list of differences between orderedList1 and orderedList2
    OrderedList<String> list3 = difference(orderedList1, orderedList2);
    System.out.print("Different Words: ");
    list3.enumerate();
    System.out.println();

    //2g. Create orderedList3
    OrderedList<String> orderedList3 = new OrderedList<>();
    for (int i = 0; i < n3; i++) {
      orderedList3.insert(wordList.get(intArray3.get(i)));
    }
    System.out.print("List 3: ");
    orderedList3.enumerate();
    System.out.println();

    //2h. Created ordered list by merging orderedList1, orderedList2, and orderedList3
    OrderedList<String> list4 = threeMerger(orderedList1, orderedList2, orderedList3);
    System.out.print("Merge of Three Lists: ");
    list4.enumerate();
  }

  /**
   * This method uses the two finger walking approach to merge two ordered list into a new list that has no repeat
   * entries
   * @param list1 first ordered list
   * @param list2 second ordered list
   * @return returns ordered list containing unique elements from each list
   */
  public static <T extends Comparable<T>> OrderedList<T> merge(OrderedList<T> list1, OrderedList<T> list2) {

    //Create return list and grab first item from each list
    OrderedList<T> returnList = new OrderedList<>();
    T listItem1 = list1.first();
    T listItem2 = list2.first();

    //Walk through each item until one list is exhausted
    while (listItem1 != null && listItem2 != null) {
      if (listItem1.compareTo(listItem2) < 0) {
        returnList.add(listItem1);
        listItem1 = list1.next();
      } else if (listItem1.compareTo(listItem2) > 0) {
        returnList.add(listItem2);
        listItem2 = list2.next();
      } else {
        returnList.add(listItem1);
        listItem1 = list1.next();
        listItem2 = list2.next();
      }
    }

    //If list 1 not fully parsed add remaining items to returnList
    while (listItem1 != null) {
      returnList.add(listItem1);
      listItem1 = list1.next();
    }

    //If list 2 not fully parsed add remaining items to returnList
    while (listItem2 != null) {
      returnList.add(listItem2);
      listItem2 = list2.next();
    }
    return returnList;
  }

  /**
   * This method uses the two finger walking approach to merge two ordered lists into a new ordered list that contains
   * only the common elements from each list
   * @param list1 first ordered list to search
   * @param list2 second ordered list to search
   * @return ordered list of common objects from each list
   */
  public static <T extends Comparable<T>> OrderedList<T> common(OrderedList<T> list1, OrderedList<T> list2) {

    //Create return list and grab first item from each list
    OrderedList<T> returnList = new OrderedList<>();
    T listItem1 = list1.first();
    T listItem2 = list2.first();

    //Walk through each item until one list is exhausted
    while (listItem1 != null && listItem2 != null) {
      if (listItem1.compareTo(listItem2) < 0) {
        listItem1 = list1.next();
      } else if (listItem1.compareTo(listItem2) > 0) {
        listItem2 = list2.next();
      } else {
        returnList.add(listItem1);
        listItem1 = list1.next();
        listItem2 = list2.next();
      }
    }
    return returnList;
  }

  /**
   * This method uses the two finger walking approach to compare two ordered lists and create a new list that contains
   * only the elements that exist in list1 but not in list2
   * @param list1 first ordered list to compare
   * @param list2 second ordered list to compare
   * @return ordered list of elements that exist on list 1 but not in list 2
   */
  public static <T extends  Comparable<T>> OrderedList<T> difference(OrderedList<T> list1, OrderedList<T> list2) {

    //Create return list and grab first item from each list
    OrderedList<T> returnList = new OrderedList<>();
    T listItem1 = list1.first();
    T listItem2 = list2.first();

    //Walk through each item until one list is exhausted
    while (listItem1 != null && listItem2 != null) {
      if (listItem1.compareTo(listItem2) < 0) {
        returnList.add(listItem1);
        listItem1 = list1.next();
      } else if (listItem1.compareTo(listItem2) > 0) {
        listItem2 = list2.next();
      } else {
        listItem1 = list1.next();
        listItem2 = list2.next();
      }
    }

    //If list 1 not fully parsed add remaining items to returnList
    while (listItem1 != null) {
      returnList.add(listItem1);
      listItem1 = list1.next();
    }
    return returnList;
  }

  /**
   * This method expands the two-finger walking approach and merges three ordered lists, returning a new ordered list
   * that contains no repeat entries
   * @param list1 first list to be merged
   * @param list2 second list to be merged
   * @param list3 third list to be merged
   * @return ordered list of unique entries from all three lists
   */
  public static <T extends Comparable<T>> OrderedList<T> threeMerger(OrderedList<T> list1, OrderedList<T> list2,
                                                                     OrderedList<T> list3) {
    return merge(merge(list1, list2), list3);
  }

  /**
   * This method is used to generate a random list of integers with no duplicate entries between 0 (inclusive) and the
   * bound (exclusive)
   * @param list list to fill with random numbers
   * @param size desired size of the list
   * @param bound maximum number to be generated
   */
  private static void generateNumbers(ArrayList<Integer> list, int size, int bound) {
    Random rand = new Random();
    int randNum = rand.nextInt(bound);
    list.add(randNum);
    while (list.size() <= size) {
      randNum = rand.nextInt(bound);
      if (!list.contains(randNum)) {
        list.add(randNum);
      }
    }
  }
}

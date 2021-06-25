//Source: Introduction to Java Programming by Daniel Liang
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A Merge sort class that implements a merge sort algorithm used to sort ArrayList's of Institute objects
 * @author Original: Danile Liang
 * @author Modified by Mason Clarke
 * @date Feb 14, 2021
 */
public class MergeSort {

	/**
	 * A method for sorting ArrayLists's of Institute objects using the merge sort method. Sorting is done by comparing
	 * Institution names, and in the case of a tie then by the Institute unitId.
	 * @param a ArrayList of institute objects to be sorted
	 * @return returns the sorted ArrayList of Institute objects
	 */
 public static ArrayList<Institute> mergeSort(ArrayList<Institute> a){
	 if(a.size()<2) {
		 return a;
	 }
	 int middle = a.size()/2;
	 ArrayList<Institute> left = new ArrayList<Institute>();
	 ArrayList<Institute> right = new ArrayList<Institute>();
	 int i=0;
	 for(i=0; i<middle; i++)
		 left.add(a.get(i));
	 for(int j=0; j<a.size() - middle; j++){
		 right.add(j, a.get(i));
		 i++;
	 }
	 left = mergeSort(left);
	 right = mergeSort(right);
	 ArrayList<Institute> result = merge(left,right);
	 return result;
 }
 
 /**
  * This method merges two sorted ArrayLists. They might be of slightly different lengths.
  * The resulting array should be sorted and should contain all values (including duplicates)
  * from the original two input arrays.
	* @param l the left side split of an ArrayList to be merged
	* @param r the right side split of an ArrayList to be merged
	* @return result the merged ArrayLists
  */
 public static ArrayList<Institute> merge(ArrayList<Institute> l, ArrayList<Institute> r){
 ArrayList<Institute> result = new ArrayList<Institute>(l.size() + r.size());
	if (l.size() + r.size() == 0)
	   return result;
	else if (l.size() + r.size() == 1)
	   return result;

	int i=0, j=0, k=0;
	while (i < l.size() && j < r.size())
	{
		if (l.get(i).compareTo(r.get(j)) < 0)
		{
			result.add(k, l.get(i));
			k++;
			i++;
		}
		else if (l.get(i).compareTo(r.get(j)) > 0)
		{
			result.add(k, r.get(j));
			k++;
			j++;
		}
		else
		{
			result.add(k, l.get(i));
			k++;
			i++;
			result.add(k, r.get(j));
			k++;
			j++;
		}
	}
	if (j == r.size())
	{
		while(i<l.size())
		{
			result.add(k, l.get(i));
			k++;
			i++;
		}
	}
	else if (i==l.size())
	{
		while(j<r.size())
		{
			result.add(k, r.get(j));
			k++;
			j++;
		}
	}
	return result;
    }
}

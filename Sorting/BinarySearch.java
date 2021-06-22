import java.util.ArrayList;
import java.util.*;

/**
 * A class that defiens and implements a Binary Search algorithm for searching through ArrayList's of Institute objects.
 * Search is performed based on Institute unitId's.
 * @author Original: Unknown
 * @author Modified by Mason Clarke
 * @date Feb 14, 2021
 */
public class BinarySearch{

	/**
	 * A binary search method that scans an ArrayList of Institute objects for a given unitId
	 * @param inst ArrayList of Institute objects
	 * @param key unitId to be found in the form of a long
	 * @param first starting index of the ArrayList
	 * @param last index of the last item in the ArrayList
	 * @return returns an int 1 if the given unitId is found and -1 if not.
	 */
	public static int binSearch(ArrayList<Institute> inst, long key, int first, int last){
		Long keyLong = key;
		int mid = (first+last)/2;
		while (first<=last){
			if (keyLong.compareTo(inst.get(mid).getUnitId())<0)
				last = mid-1;
			else if (keyLong.compareTo(inst.get(mid).getUnitId())>0)
				first = mid+1;
			else
				//key found
				break;
			mid = (first+last)/2;
		}
		if (first > last)
			return -1;
		else
			return 1;
	}

	/**
	 * A wrapper method used for calling the above binSearch method
	 * @param inst the ArrayList of Institute objeccts to be searched
	 * @param key the Institute unitId the be found
	 * @return return the result of the above binSearch method, either 1 if the object is found and -1 if not
	 */
	public static int binSearch(ArrayList<Institute> inst, long key){
		int result = binSearch(inst, key, 0, inst.size() -1);
		return result;
	}
}

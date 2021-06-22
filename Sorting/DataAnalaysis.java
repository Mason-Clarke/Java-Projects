import java.util.*;
import java.io.*;

/**
 * A demo program which is used to determine runtime complexity of various search and sort algorithms based on their
 * execution time. Analysis is being performed on an ArrayList of Institute objects. Execution times for each
 * search/sort are reported in ms.
 * @author Mason Clarke
 * @date Feb 14, 2021
 */
public class DataAnalaysis{

	public static void main(String[] args) throws FileNotFoundException {

		// Read the file representing the data set line by line.
		Scanner in = new Scanner(System.in);
		System.out.println("Enter File Path:");
		String filename = in.nextLine();
		File file = new File(filename);
		Scanner inputFile = new Scanner(file);
		StringTokenizer token;

		// Separate each line into its individual components.
		// Create an Institution object representing each record and store
		// it in an ArrayList of Institution objects.
		ArrayList<Institute> instituteList = new ArrayList<Institute>();
		String throwAway = inputFile.nextLine();
		while(inputFile.hasNext()) {
			String line = inputFile.nextLine();
			token = new StringTokenizer(line, ",");
			String unitIdString = token.nextToken();
			long unitIdNum = Long.valueOf(unitIdString);
			String institutionName = token.nextToken();
			String city = token.nextToken();
			String stateAbbreviation = token.nextToken();
			String zip = token.nextToken();
			String accredAgency = token.nextToken();
			String institutionURL = token.nextToken();
			String lattitudeString = token.nextToken();
			double lattitudeNum;
			if(lattitudeString.equals("NULL")){
				lattitudeNum = -1.0;
			} else {
				lattitudeNum = Double.valueOf(lattitudeString);
			}
			String longitudeString = token.nextToken();
			double longitudeNum;
			if(longitudeString.equals("NULL")){
				longitudeNum = -1.0;
			} else {
				longitudeNum = Double.valueOf(longitudeString);
			}
			String ccUndergradProfile = token.nextToken();
			Institute temp = new Institute(unitIdNum, institutionName, city, stateAbbreviation, zip, accredAgency,
							institutionURL, lattitudeNum, longitudeNum, ccUndergradProfile);
			instituteList.add(temp);
		}
		inputFile.close();

	// Create a copy of the ArrayList and sort it by calling the BubbleSort method. 
	// Record the execution time of bubbleSort.
		ArrayList<Institute> bubbleSortArray = new ArrayList<Institute>();
		for (int i = 0; i < instituteList.size(); i ++){
			Institute tempInst = instituteList.get(i);
			Institute newIns = new Institute(tempInst.getUnitId(), tempInst.getInstitutionName(), tempInst.getCity(),
							tempInst.getStateAbreviation(), tempInst.getZip(), tempInst.getAccredagency(),
							tempInst.getInstitutionURL(), tempInst.getLatitude(), tempInst.getLongitude(),
							tempInst.getCcUndergradProfile());
			bubbleSortArray.add(i, newIns);
		}
		long bubbleSortStart = System.currentTimeMillis();
		bubbleSortArray = bubbleSort(bubbleSortArray);
		long bubbleSortEnd = System.currentTimeMillis();
		long bubbleSortDur = bubbleSortEnd - bubbleSortStart;
		System.out.println("Bubble Sort: " + bubbleSortDur + " ms");


	// Create a copy of the ArrayList and sort it by calling the BubbleSort method. 
	// Record the execution time of mergeSort.
		ArrayList<Institute> mergeSortArray = new ArrayList<Institute>();
		for (int i = 0; i < instituteList.size(); i ++){
			Institute tempInst = instituteList.get(i);
			Institute newIns = new Institute(tempInst.getUnitId(), tempInst.getInstitutionName(), tempInst.getCity(),
							tempInst.getStateAbreviation(), tempInst.getZip(), tempInst.getAccredagency(),
							tempInst.getInstitutionURL(), tempInst.getLatitude(), tempInst.getLongitude(),
							tempInst.getCcUndergradProfile());
			mergeSortArray.add(i, newIns);
		}
		long mergeSortStart = System.currentTimeMillis();
		mergeSortArray = mergeSort(mergeSortArray);
		long mergeSortEnd = System.currentTimeMillis();
		long mergeSortDur = mergeSortEnd - mergeSortStart;
		System.out.println("Merge Sort: " + mergeSortDur + " ms");


	// Generate the random keys in the range 1,000,000 and 5,000,000 and store them in an ArrayList
		ArrayList<Integer> randomKeys = new ArrayList<Integer>();
		Random randIntGen = new Random();
		int bottom = 100000;
		int top = 50000000;
		for (int i = 0; i < 50; i++){
			int randomInt = randIntGen.nextInt(top - bottom) + bottom;
			randomKeys.add(i, randomInt);
		}

	// Search the initial ArrayList for each of the generated keys using linearSearch
	// Record the execution times for the linear searches.
		long linSearchStart = System.currentTimeMillis();
		for (int i = 0; i < randomKeys.size(); i++) {
			linearSearch(instituteList, randomKeys.get(i));
		}
		long linSearchEnd = System.currentTimeMillis();
		long linSearchDur = linSearchEnd - linSearchStart;
		System.out.println("Linear Search: " + linSearchDur + " ms");

	// Search the initial ArrayList for the same set of keys using binarySearch
	// Record the execution times for the binary searches.
		long binSearchStart = System.currentTimeMillis();
		for (int i = 0; i < randomKeys.size(); i++) {
			binarySearch(instituteList, randomKeys.get(i));
		}
		long binSearchEnd = System.currentTimeMillis();
		long binSearchDur = binSearchEnd - binSearchStart;
		System.out.println("Binary Search: " + binSearchDur + " ms");
	}

	/**
	 * A method for sorting ArrayLists of Institute objects using the bubble sort method. Objects are sorted based first
	 * alphabetically on Institution name, and then numerically based on Institution unitId.
	 * @param inst ArrayList of Institute objects to be sorted
	 * @return sorted list of Institute objects
	 */
	public static ArrayList<Institute> bubbleSort(ArrayList<Institute> inst)
	{ 
		for (int i = 1; i < inst.size(); i ++) {
			for (int j = 0; j < inst.size() - i; j ++) {
				if (inst.get(j).compareTo(inst.get(j + 1)) > 0){
					Institute tempInstitute = inst.get(j);
					inst.set(j, inst.get(j + 1));
					inst.set (j + 1, tempInstitute);
				}
			}
		}
		return inst;
	}

	/**
	 * A method which makes a call to the mergeSort method from the MergeSort class to perform a merge sort on an
	 * ArrayList of Institute objects.
	 * @param inst ArrayList of Institute objects to the sorted
	 * @return the sorted ArrayList of Institute objects
	 */
	public static ArrayList<Institute> mergeSort(ArrayList<Institute> inst) {
		inst = MergeSort.mergeSort(inst);
		return inst;
	}

	/**
	 * A method for performing linear search on an ArrayList of Instituted objects.
	 * @param inst ArrayList of Instituted objects to be searched
	 * @param key unitId of Institute object to be found
	 * @return true if objects is found and false otherwises
	 */
	public static boolean linearSearch(ArrayList<Institute> inst, int key) {
		// COMPLETE THIS METHOD
		for (int i = 0; i < inst.size(); i ++){
			if(inst.get(i).getUnitId() == key){
				return true;
			}
		}
		return false;
	}

	/**
	 * Method which calls the binarySearch method from the BinarySearch class used to perform binary search on an
	 * ArrayList of Institute objects.
	 * @param inst ArrayList of Institute objects to be searched
	 * @param key unitId of the Institute object to be found
	 * @return true if given object is found and false otherwise
	 */
	public static boolean binarySearch(ArrayList<Institute> inst, long key) {
		// COMPLETE THIS METHOD
		int found = BinarySearch.binSearch(inst, key);
		if (found == 1){
			return true;
		} else {
			return false;
		}
	}
}

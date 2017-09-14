/**
 * 
 * filename: SortingTest.java
 * 
 * version: 1.0 02/08/2017
 *
 *         revisions: Initial version
 */

//import statements are placed here
import java.util.ArrayList;
import java.util.Random;

/*
 * 
 * This program will perform three type of sorting such as Merge Sort, Bucket Sort and Insertion Sort.
 * and will compare performance of all above sort alongwith two data - uniform data and Gausann data  
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair 
 */

public class SortingTest {
	public double data[], dataForISort[], dataForMSort[], dataForBSort[];

	/*
	 * This is a constructor of the class
	 */
	public SortingTest() {
		// This will perform all types of sorts with uniform data
		System.out.println("======Uniform Data=========");
		for (int i = 10; i <= 1000000; i = i * 10) {
			System.out.println("Size of input data size : " + i);
			generateUniformData(i);
			copyData();
			long startTime = takeTime();
			performInsertionSort();
			long stopTime = takeTime();
			printTime("Insertion Sort", startTime, stopTime);
			startTime = takeTime();
			performMergeSort(dataForMSort, 0, dataForMSort.length);
			stopTime = takeTime();
			printTime("Merge Sort", startTime, stopTime);
			startTime = takeTime();
			performBucketSort(dataForBSort, i);
			stopTime = takeTime();
			printTime("Bucket Sort", startTime, stopTime);
		}
		// This will perform all types of sorts with gausann data
		System.out.println("=====Gausann Data===========");
		for (int i = 10; i <= 1000000; i = i * 10) {
			System.out.println("Size of input data size : " + i);
			generateGausannData(i);
			copyData();
			long startTime = takeTime();
			performInsertionSort();
			long stopTime = takeTime();
			printTime("Insertion Sort", startTime, stopTime);
			startTime = takeTime();
			performMergeSort(dataForMSort, 0, dataForMSort.length);
			stopTime = takeTime();
			printTime("Merge Sort", startTime, stopTime);
			startTime = takeTime();
			performBucketSort(dataForBSort, 10);
			stopTime = takeTime();
			printTime("Bucket Sort", startTime, stopTime);
		}
	}

	/*
	 * This function will generate uniform data and insert into data
	 * 
	 * @param size
	 *            size is defined for size of array
	 */
	public void generateUniformData(int size) {
		Random random = new Random();
		data = new double[size];
		for (int i = 0; i < data.length; i++)
			data[i] = random.nextDouble();
	}

	/*
	 * This function will generate Guassian Data with mean of 0.5 and Standard
	 * Deviation as .0001
	 * 
	 * @param size
	 *            size is defined for an array "data" so that it can contain unsorted random number
	 */
	public void generateGausannData(int size) {
		Random random = new Random();
		data = new double[size];
		for (int i = 0; i < data.length; i++)
			data[i] = (random.nextGaussian() * 1 / 1000) + 0.5; //This is in built function provided by Java for Gaussian data
	}

	/*
	 * 
	 * This function will copy data to another data for different  type of sort such as dataForBSort(Bucket sort),
	 * dataForMSort (Merge Sort) and dataForISort (Insertion Sort)
	 */
	public void copyData() {
		dataForISort = dataForBSort = dataForMSort = data;
	}

	/*
	 * This function will return current time since this program is used for performance purpose
	 */
	public long takeTime() {
		return System.nanoTime();
	}

	/*
	 * 
	 * This function will print an output of execution time alongwith type of sort. 
	 * 
	 * @param sortType
	 *            Type of sort to be performed.
	 * @param startTime
	 *            Start time before an execution is to be started.
	 * @param stopTime
	 *            Stop time after an execution is to be executed.
	 */

	public void printTime(String sortType, long startTime, long stopTime) {
		System.out.println("Execution time for " + sortType + " : "
				+ (stopTime - startTime));
	}

	/*
	 * This function will perform an insertion sort with time complexity of O(
	 * square of n)
	 * 
	 * Reference :  https://interactivepython.org/runestone/static/pythonds/SortSearch/TheInsertionSort.htmll
	 */
	public void performInsertionSort() {
		for (int i = 1; i < dataForISort.length; i++) {
			double curr = dataForISort[i];
			int position = i;
			while (position > 0 && dataForISort[position - 1] > curr) {
				dataForISort[position] = dataForISort[position - 1];
				position = position - 1;
			}
			dataForISort[position] = curr;
		}
	}

	/*
	 * This function will perform an insertion sort for bucket sort purpose.
	 * 
	 * @param data
	 *            It will accept unsorted data in double format.
	 * Reference :  https://interactivepython.org/runestone/static/pythonds/SortSearch/TheInsertionSort.html
	 */
	public void performInsertionSort(Double data[]) {
		for (int i = 1; i < data.length; i++) {
			double curr = data[i];
			int position = i;
			while (position > 0 && data[position - 1] > curr) {
				data[position] = data[position - 1];
				position = position - 1;
			}
			data[position] = curr;
		}
	}

	/*
	 * This function will perform an bucket sort.
	 * Reference : http://www.growingwiththeweb.com/2015/06/bucket-sort.html
	 * 
	 * @param inputArray
	 *            It will accept unsorted data to be sorted.
	 * @param bucketSize
	 *            the size of bucket
	 */
	public void performBucketSort(double[] inputArray, int bucketSize) {
		if (inputArray.length == 0) {
			return;
		}
		//this below statment will check the minimum and maximum value in an array. 
		double minValue = inputArray[0];
		double maxValue = inputArray[0];
		for (int i = 1; i < inputArray.length; i++) {
			if (inputArray[i] < minValue) {
				minValue = inputArray[i];
			} else if (inputArray[i] > maxValue) {
				maxValue = inputArray[i];
			}
		}
		//this below statment will calculate the bucket count  using arraylist of arraylist. 
		int bucketCount = (int) ((maxValue - minValue) / bucketSize + 1);
		ArrayList<ArrayList<Double>> buckets = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < bucketCount; i++) {
			buckets.add(new ArrayList<Double>());
		}
		//this below statment will distribute the input data into buckets  
		for (int i = 0; i < inputArray.length; i++) {
			buckets.get((int) ((inputArray[i] - minValue) / bucketSize) * 10)
					.add(inputArray[i]);
		}
		//As it contains of array of array, it retrieve array and perform insertion sort for each array. 
		int currentIndex = 0;
		for (int i = 0; i < buckets.size(); i++) {
			Double[] bucketArray = new Double[buckets.get(i).size()];
			bucketArray = buckets.get(i).toArray(bucketArray);
			performInsertionSort(bucketArray);
			for (int j = 0; j < bucketArray.length; j++) {
				inputArray[currentIndex++] = bucketArray[j];
			}
		}
	}

	/*
	 * This function will perform merge sort with time complexity of O(nlogn)
	 * Reference : http://www.sanfoundry.com/java-program-implement-merge-sort/
	 * 
	 * @param dataForMSort
	 *            The unsorted data is to be sorted.
	 * @param min
	 *            The minimum value of position for index.
	 * @param max
	 *            The maximum value of position for index.
	 */
	public void performMergeSort(double dataForMSort[], int min, int max) {
		// size of array
		int N = max - min;
		//stop if array size is 1		
		if (N <= 1)   
			return;
		// calculate the middle value  
 		int mid = min + N / 2;  
		//recursive call function from 0 to half
		performMergeSort(dataForMSort, min, mid); 
		//recursive call function from half to array length.
		performMergeSort(dataForMSort, mid, max); 
		double[] temp = new double[N];
		int i = min, j = mid;
		// this will check two individual element and compare with each other and combine it to temp.
		for (int k = 0; k < N; k++) {
			if (i == mid)
				temp[k] = dataForMSort[j++];
			else if (j == max)
				temp[k] = dataForMSort[i++];
			else if (dataForMSort[j] > dataForMSort[i])
				temp[k] = dataForMSort[j++];
			else
				temp[k] = dataForMSort[i++];
		}
		// this will copy the sorted data to data for merge sort. 
		for (int k = 0; k < N; k++)
			dataForMSort[min + k] = temp[k];

	}
	
	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 */

	public static void main(String[] args) {
		SortingTest a = new SortingTest();
	}
}

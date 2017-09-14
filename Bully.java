/**
 * 
 * filename: Bully.java
 * 
 * version: 1.0 02/22/2017
 *
 *         revisions: Initial version
 */

//import statements are placed here
import java.util.Scanner;

/*
 * This program prints out the number of incident of bullying occurred.
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair 
 */

public class Bully {
	int data[], temp[], left[], right[], position = 0;
	boolean left_status = false, right_status = false;

	/**
	 * This is an constructor, which will be called once an object is created.
	 * 
	 */
	public Bully() {
		int count = 0;
		Scanner sc = new Scanner(System.in);
		int size = enterSize(sc); // accept the size of elements.
		data = new int[size];
		temp = new int[size];
		left = new int[size];
		right = new int[size];
		acceptInput(sc); // accept the each elements till the size of elements
		performPosition(); // get position of right, left in each sub array.
		for (int i = 0; i < position; i++) {
			if (left[i] != right[i]) {
				count += mergeSort(data, temp, left[i], right[i]);
			}
		}
		System.out.println(count);
	}

	/**
	 * This function will accept the size for element for individual elements.
	 * 
	 * @param sc
	 *            This is an object of Scanner
	 */
	private int enterSize(Scanner sc) {
		return sc.nextInt();
	}

	/**
	 * This function will get the position of each right and each left in array,
	 * ensuring that the data is not -1. It start from the end of array in order
	 * to find the position of right making sure that the data is not -1 and
	 * store in array. Similarly for left position and increment the position.
	 */
	private void performPosition() {
		for (int i = data.length - 1; i >= 0; i--) {
			if (right_status == false && data[i] != -1) {
				right[position] = i;
				right_status = true;
			}
			if (((i - 1) >= 0 && data[i - 1] == -1) || (i == 0)
					&& left_status == false && data[i] != -1) {
				left[position++] = i;
				left_status = true;
			}
			if (data[i] == -1) {
				right_status = false;
				left_status = false;
			}
		}
	}

	/**
	 * This function will accept the individual elements til the size of
	 * elements.
	 * 
	 * @param sc
	 *            This is an object of Scanner
	 */
	private void acceptInput(Scanner sc) {
		for (int i = 0; i < data.length; i++)
			data[i] = sc.nextInt();
	}

	/**
	 * This function will perform merge Sort in descending order with the time
	 * complexity of O(nlogn)
	 * 
	 * @param data
	 *            this is input data which is unsorted.
	 * @param temp
	 *            this is output data, which is sorted in descending order.
	 * @param left
	 *            this is left position, the minimum position where the data is
	 *            to be sorted.
	 * @param right
	 *            this is right position, the maximum position where the data is
	 *            to be sorted.
	 * @return the counting inversion, where the value of a[i] < a[j] , where i
	 *         < j
	 */
	private int mergeSort(int data[], int temp[], int left, int right) {
		int middle, count = 0;
		if (right > left) {
			middle = (left + right) / 2;
			count = mergeSort(data, temp, left, middle);
			count += mergeSort(data, temp, middle + 1, right);
			count += mergeTogether(data, temp, left, middle + 1, right);
		}
		return count;
	}

	/**
	 * This function will perform merge sort in descending order
	 * 
	 * @param data
	 *            The data, which is unsorted.
	 * @param temp
	 *            The data, which is to be sorted in descending order.
	 * @param left
	 *            this is left position, the minimum position where the data is
	 *            to be sorted.
	 * @param right
	 *            this is right position, the maximum position where the data is
	 *            to be sorted.
	 * @param middle
	 *            this is middle position
	 * 
	 * @return the counting inversion of each sub-array after it is merged
	 *         together.
	 */
	private int mergeTogether(int[] data, int[] temp, int left, int middle,
			int right) {
		int i, j, k;
		int count = 0;
		k = i = left;
		j = middle;
		while ((i <= middle - 1) && (j <= right)) {
			if (data[i] >= data[j]) {
				temp[k++] = data[i++];

			} else {
				temp[k++] = data[j++];
				count = count + (middle - i);// if A[j] is greater than A[i],
												// there are (middle - 1)
												// inversion since the left and
												// right sub-array are sorted
			}
		}

		while (i <= middle - 1)
			temp[k++] = data[i++]; //remaining data on i side 
		while (j <= right)
			temp[k++] = data[j++]; //remaining data on j side
		for (i = left; i <= right; i++) //copy the data from temp to data.
			data[i] = temp[i];

		return count;
	}

	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		new Bully(); // Create an object of this class name.

	}

}

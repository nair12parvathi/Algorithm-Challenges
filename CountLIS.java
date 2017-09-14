

/**
 * 
 * filename: CountLIS.java
 * 
 * version: 1.0 04/03/2017
 * 
 * revisions: Initial version
 */
// import statements are placed here

import java.util.Scanner;

/*
 * This program will calculate the different possible of LIS sequence with maximum length. 
 * 
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair
 */
public class CountLIS {
	int data[];
	int dataLIS[], countLIS[];

	/**
	 * This is a constructor, which will be called once an object is created.
	 * 
	 */	
	public CountLIS() {
		Scanner sc = new Scanner(System.in);
		int size = acceptSize(sc);
		acceptInput(size, sc);
		long length = findLIS();
		countLIS();
		System.out.println(findOutput(length));
	}

	/**
	 * This function will compute an output for longest increasing sequence.
	 * 
	 * @param length
	 * 			the length of the longest increasing sequence. 
	 * @return
	 * 			the number of possible of Longest increasing sequence with max length.
	 */
	private int findOutput(long length) {
		int result = 0;
		for (int i = 0; i < dataLIS.length; i++) {
			if (dataLIS[i] == length)
				result += countLIS[i];
		}
		return result;
	}
	/**
	 * This function will compute the different possible for longest increasing sequence for all length.
	 */
	private void countLIS() {
		countLIS = new int[data.length];
		countLIS[0] = 1;
		for (int i = 1; i < countLIS.length; i++)
			countLIS[i] = 0;
		for (int right = 1; right < data.length; right++) {
			for (int left = 0; left < right; left++) {
				if (dataLIS[right] == 1)
					countLIS[right] = 1;
				if ((dataLIS[right] - 1) == dataLIS[left]
						&& data[right] > data[left]) {
					countLIS[right] += countLIS[left];

				}

			}
		}

	}
    /**
     * This function will generate the longest increasing subsequence.
     * 
     * @return
     * 		the maximum length of longest increasing subsequence.
     */
	private int findLIS() {
		int max = -1;
		dataLIS = new int[data.length];
		for (int right = 0; right < data.length; right++) {
			dataLIS[right] = 1;
			for (int left = 0; left < right; left++) {
				if (data[right] > data[left]
						&& dataLIS[right] < dataLIS[left] + 1)
					dataLIS[right]++;

				if (left == right - 1 && dataLIS[left] > max)
					max = dataLIS[left];
			}
		}
		return max;
	}

	/**
	 * This function will accept the input for size. 
	 * @param size
	 * 			the length of input.
	 * @param sc
	 * 			Scanner object
	 */
	private void acceptInput(int size, Scanner sc) {
		data = new int[size];
		for (int i = 0; i < size; i++)
			data[i] = sc.nextInt();
	}

	/**
	 * This function will accept the length for input.
	 * 
	 * @param sc
	 *            Scanner object, which is used to accept input from user.
	 * 
	 * @return return user entered input.
	 */
	private int acceptSize(Scanner sc) {
		return sc.nextInt();
	}

	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String arg[]) {
		new CountLIS();
	}
}

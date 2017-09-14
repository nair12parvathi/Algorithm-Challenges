/**
 * 
 * filename: Angles.java
 * 
 * version: 1.0 02/21/2017
 *
 *         revisions: Initial version
 */

//import statements are placed here
import java.util.*;

/*
 * This program determines the number of right triangles that can formed by choosing sets of three points, 
 * given n different points in 2-dimensional space 
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair 
 */
public class Angles {
	int n;
	int startIndex;
	int[][] input;
	int totalCount;
	Scanner sc;
	ArrayList<Double> al;
	double error = 0.00000001;

	/**
	 * This is a constructor, which will be called once an object is created.
	 * 
	 */
	public Angles() {
		totalCount = 0;
		sc = new Scanner(System.in);
		al = new ArrayList<Double>();
		takeInput(this.n, sc);
	}

	/**
	 * This function takes the input.
	 * 
	 * @param n
	 *            number of 2 dimensional points
	 * @param sc
	 *            scanner object to read
	 */
	private void takeInput(int n, Scanner sc) {
		n = sc.nextInt();
		input = new int[n][2];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2; j++) {
				input[i][j] = sc.nextInt();
			}
		}
		doWork(n);
	}

	/**
	 * This function counts the total number of right triangles from the given n
	 * points
	 * 
	 * @param n
	 *            n is the total number of 2 dimensional points
	 */
	private void doWork(int n) {
		int k = 0;
		// We take one point at a time. Considering that as a vertex, we compute
		// slopes with all other points, then sort them and then search for each
		// point's inverse using binary search
		for (int i = 0; i < n; i++) {
			// this takes the one point
			int x1 = input[i][0];
			int y1 = input[i][1];
			al.clear();
			// this block of code computes slopes of segments formed by
			// input[i][0],
			// input[i][1] with all the other points
			for (int j = 0; j < n; j++) {
				// this block of code is to discard the chance of slope being
				// computed of a line formed by a point with itself
				if (i == j) {
					continue;
				}
				int x2 = input[j][0];
				int y2 = input[j][1];
				// this block of code calls the function computeSlope and stores
				// the returned value in an arraylist al.
				double slope = 0.0;
				slope = computeSlope(input[i][0], input[i][1], input[j][0], input[j][1]);
				al.add(slope);
			}
			// this sorts the slopes stored in arraylist al.
			Collections.sort(al);
			double previous = 0;
			int currentCount = 0;
			// this block of code is used to get the index value of the first
			// positive element in the arraylist al
			startIndex = 0;
			for (int z = 0; z < al.size(); z++) {
				if (al.get(z) > (double) 0.0) {
					startIndex = z;
					break;
				}
			}

			// this block of code checks for the inverse for all the negative
			// elements using binary search
			for (int index = 0; index < startIndex; index++) {
				double inverse = (double) -1 / (double) al.get(index);
				// if the next element in the arraylist al is same as the
				// previous one, it doesn't call the binary search function.
				// It just increments the counter
				if (previous != al.get(index)) {
					previous = al.get(index);
					currentCount = BinarySearch(inverse, startIndex);
				}
				totalCount = totalCount + currentCount;

			}
		}
		System.out.println(totalCount);
	}

	/**
	 * this function searches for the value in the variable 'inverse' in the
	 * arraylist using Binary Search technique
	 * 
	 * @param inverse
	 *            this is the inverse slope, which is being searched in the
	 *            arraylist. If we find the inverse, that means it forms a right
	 *            angled triangle as equivalence between slope and its inverse
	 *            proves the lines are perpendicular
	 * 
	 * @return count- this is the count of right angled triangles.
	 */
	private int BinarySearch(double inverse, int startIndex) {
		int leftScan = startIndex;
		int rightScan = startIndex;
		int low = startIndex;
		int high = al.size() - 1;
		int count = 0;
		double error = 0.000001;
		while (high >= low) {
			int middle = (low + high) / 2;
			if (al.get(middle) <= inverse + error && al.get(middle) >= inverse - error) {
				count++;
				leftScan = middle - 1;
				rightScan = middle + 1;
				// if the value in inverse is found in the arraylist, we scan
				// both left and right sides of that element to find duplicates
				// and increment the counter which keeps track of the count of
				// the right angled triangles
				while (leftScan >= low
						&& (al.get(leftScan) <= inverse + error && al.get(leftScan) >= inverse - error)) {
					count++;
					leftScan--;
				}
				while (rightScan <= high
						&& (al.get(rightScan) <= inverse + error && al.get(rightScan) >= inverse - error)) {
					count++;
					rightScan++;
				}
				return count;
			} else if (al.get(middle) < inverse) {
				low = middle + 1;
			} else {
				high = middle - 1;
			}
		}
		return 0;
	}

	/**
	 * This function computes the slope
	 * 
	 * @param x1
	 *            x coordinate of first point
	 * @param y1
	 *            y coordinate of first point
	 * @param x2
	 *            x coordinate of second point
	 * @param y2
	 *            y coordinate of second point
	 * 
	 * @return slope- the slope value computed is returned
	 */
	private double computeSlope(int x1, int y1, int x2, int y2) {
		double slope;
		if ((x2 - x1) == 0 && (y2 - y1) != 0)
			slope = 10000;
		else if ((x2 - x1) != 0 && (y2 - y1) == 0)
			slope = (double) -1 / (double) 10000;
		else {
			slope = (double) (y2 - y1) / (double) (x2 - x1);
		}
		return slope;
	}

	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		new Angles();// object is created here
	}
}

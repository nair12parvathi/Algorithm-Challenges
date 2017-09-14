

/**
 * 
 * filename: Planters.java
 * 
 * version: 1.0 02/06/2017
 *
 *         revisions: Initial version
 */
//import statements are placed here
import java.util.Scanner;

/*
 * This program prints out whether all planter can be replanted in another bigger planter or not. 
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair 
 */

public class Planters {
	int initialP, extraP;
	int plantersI[], plantersE[];

	/**
	 * This is an constructor, which will be called once an object is created.
	 * 
	 */
	public Planters() {
		Scanner sc = new Scanner(System.in);
		initialP = sc.nextInt();
		extraP = sc.nextInt();
		plantersI = new int[initialP];
		plantersE = new int[extraP];
		acceptSize(sc);
		performMergeSort(plantersI, 0, plantersI.length);
		performMergeSort(plantersE, 0, plantersE.length);
		isPlanterValid();
		sc.close();
	}

	/**
	 * This function will check whether all planter can be replanted or not.
	 * 
	 */
	public void isPlanterValid() {
		// before calling this function, ensure that the planter are in
		// descending order
		// the variable position is used to maintain for extra planter position.
		int position = 0, previous = 0;
		for (int i = 0; i < plantersI.length; i++) {
			// this check for first position of planter whether it is lesser
			// than the first position of extra planter
			// if yes, it will be assigned to zero. Also, the planter is mainted
			// in one variable "previous"
			if (i == 0 && position < plantersE.length
					&& plantersI[i] < plantersE[position]) {
				plantersE[position++] = 0;
				previous = plantersI[i];
				plantersI[i] = 0;
			} else {
				// if no, it will return No as the the first position of planter
				// can not be replanted in extra planter.
				if (i == 0) {
					printOutput(0);
					System.exit(1);
				} else {
					// Except i = 0, it will check whether it can be replanted
					// in previous planter
					if (plantersI[i] < previous) {
						previous = plantersI[i];
						plantersI[i] = 0;
					} else {
						// Except i = 0, if it can not replanted in previous
						// planter, then it will check in extra planter.
						if (position < plantersE.length
								&& plantersI[i] < plantersE[position]) {
							plantersE[position++] = 0;
							previous = plantersI[i];
							plantersI[i] = 0;
						} else {
							// Except i = 0, if it can not replanted in previous
							// planter as well as extra planter, it will exit
							// with output as "NO"
							printOutput(0);
							System.exit(1);
						}
					}
				}
			}
		}
		// It will check if all planter are replanted in another planter , then
		// it will output as YES, otherwise No.
		for (int i = 0; i < plantersI.length; i++) {
			if (plantersI[i] != 0) {
				printOutput(0);
				break;
			}
		}
		printOutput(1);
	}

	/**
	 * Given the number i, this function will print out an output "YES" if an
	 * number is 1, which indicate all planters can be replanted, else "NO",
	 * which indicates some planter can not be replanted.
	 * 
	 * @param i
	 *            non negative number either in value of 0 or 1.
	 */
	public void printOutput(int i) {
		System.out.println((i == 0) ? "NO" : "YES");
	}

	/**
	 * This function will accept the size for initial planter as well as extra
	 * planter
	 * 
	 * @param sc
	 *            This is an object of Scanner
	 */
	public void acceptSize(Scanner sc) {
		for (int i = 0; i < plantersI.length; i++)
			plantersI[i] = sc.nextInt();
		for (int j = 0; j < plantersE.length; j++)
			plantersE[j] = sc.nextInt();
	}

	/**
	 * This function will sort data in descending order.
	 * 
	 * @param data
	 *            It will accept data which is to be sorted.
	 * @param min
	 *            : the lower value of an index for an array.
	 * @param max
	 *            : the upper value of an index for an array.
	 */
	public void performMergeSort(int data[], int min, int max) {
		int N = max - min;
		if (N <= 1)
			return;
		int mid = min + N / 2;
		performMergeSort(data, min, mid);
		performMergeSort(data, mid, max);
		int[] temp = new int[N];
		int i = min, j = mid;
		for (int k = 0; k < N; k++) {
			if (i == mid)
				temp[k] = data[j++];
			else if (j == max)
				temp[k] = data[i++];
			else if (data[j] > data[i])
				temp[k] = data[j++];
			else
				temp[k] = data[i++];
		}
		for (int k = 0; k < N; k++)
			data[min + k] = temp[k];

	}

	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 */

	public static void main(String[] args) {
		Planters obj = new Planters(); // Create an object of this class name.
	}
}

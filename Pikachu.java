/*You have the following coin in your possession: 1, 2, 5, 10, 10, 25, 25, 25, 50, and you 
*would like to buy a Pikachu figure. A Pikachu figure costs 53 and in order to buy it, you 
*need the exact amount. Can you buy it with the coins you have? Write a program which can 
*find out the answer. You can use arrays, and you have to use an iterative algorithm. You 
*can hardcode all values in your program. You cannot use any classes besides the String class. 
*In addition to the described requirements your solution has to find the smallest number of coins 
*used. You have to print out the smallest set, if such a set exist.
*/

/**
 * 
 * filename: Pikachu.java
 * 
 * version: 1.0 09/04/2016
 * 
 * @author Parvathi Nair
 * 
 * @author Arpit Vora
 * 
 *         revisions: Initial version
 *
 */

/**
 * 
 * This program checks if you have the exact amount to get a Pikachu and gives
 * the smallest number of coins used.
 * 
 *
 */
public class Pikachu {
	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 * 
	 * 
	 */
	public static void main(String[] args) {
		int[] coins = { 1, 2, 5, 10, 10, 25, 25, 25, 50 }; // coins we have
		int size = coins.length; // lengh of array which is the number of coins
		int powerSet = (int) Math.pow(2, size); // total number of subsets
												// possible
		int i = powerSet; // assigning powerSet to i
		int j = size; // assigning size to j
		int[][] PowerSet = new int[i][j]; // PowerSet is a 2D array that holds
											// all the possible subsets
		int amount = 53; // amount stores the cost of Pikachu
		int counter = 0; // used to count the number of coins in each subset/row
		int total = 0; // used to calculate the sum in each subset
		int min_j = size; // used to track the minimum number of coins used
		int row = 0; // used to save the row of interest, that is the row which
						// uses the minimum number of coins

		// Below we store all the possible subsets in the 2D array PowerSet, row
		// ranges from 0 to 511 and column ranges from 0 to 8
		for (i = 0; i < powerSet; i++) {
			for (j = 0; j < size; j++) {
				if ((i & (1 << j)) > 0) {
					PowerSet[i][j] = coins[j];
				}
			}
		}

		// Here we take each row for checking the number of coins
		for (i = 0; i < powerSet; i++) {
			total = 0; // total initialized to 0
			counter = 0; // counter initialized to 0

			// here we checks the sum of the coins in each
			// row. I fit goes above the costs then break the loop, goto next
			// row. counter keeps track of the coins used each time
			for (j = 0; j < size; j++) {
				if (PowerSet[i][j] != 0) {
					counter++;
					total = total + PowerSet[i][j];
					if (total > amount) {
						break;
					}
				}
			}
			// if the coins total to the cost of pikachu it checks if the
			// counter is less than minimum 'min_j' and if yes it saves the
			// counter as the new minimum. The variable row saves the row number
			// where we use the minimum number of coins
			if (total == amount) {
				if (counter < min_j) {
					min_j = counter;
					row = i;
				}
			}
		}
		System.out.print(amount + " = ");
		// We have the row number which has the minimum number of coins, so we
		// just print the coins in it here
		for (int k = 0; k < size; k++) {
			if (PowerSet[row][k] != 0)
				System.out.print(PowerSet[row][k] + " ");
		}
	}
}

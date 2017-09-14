
/**
 * 
 * filename: Evensum.java
 * 
 * version: 1.0 01/26/2017
 *
 *         revisions: Initial version
 */

//import statements are placed here
import java.util.Scanner;

/*
 * This program reads in a series of non-negative integers and prints out the sum of all inputs that are even
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair 
 */
public class Evensum {
	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		Evensum obj = new Evensum(); // creates an object of the class Evensum
		Scanner sc = new Scanner(System.in);// creates an object of Scanner
											// class
		int num = sc.nextInt(); // total numbers in the input
		if (num > 0) {
			obj.calcEvenSum(num, sc);
		}

	}

	/**
	 * Given the number of non-negative integers in the input, this function
	 * checks if the number is even and add only if it is even
	 * 
	 * @param num
	 *            non-negative number that gives the total number of integers in
	 *            the input
	 * @param sc
	 *            object of Scanner
	 */
	private void calcEvenSum(int num, Scanner sc) {
		int currNum, result = 0; // currNum is the current number that is read
									// by scanner object and result is the sum
									// of even numbers
		for (int i = 0; i < num; i++) {
			currNum = sc.nextInt();
			if (currNum % 2 == 0) {
				result = result + currNum;
			}
		}
		System.out.println(result);
	}

}

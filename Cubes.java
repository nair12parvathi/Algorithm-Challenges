/**
 * 
 * filename: Cubes.java
 * 
 * version: 1.0 01/26/2017
 *
 *         revisions: Initial version
 */

//import statements are placed here
import java.util.*;

/*
 * This program prints out perfect cubes given an upper bound
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair 
 */
public class Cubes {
	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // creates an object of Scanner
												// class
		int num = sc.nextInt(); // a non-negative number which acts as the upper
								// bound for calculation of perfect cubes from 0
		Cubes obj = new Cubes(); // creates an object of the class Cubes
		obj.calculateCube(num);
	}

	/**
	 * Given a number, this function prints out in increasing order, starting
	 * from 0, all positive perfect cubes less than or equal to that value
	 * 
	 * @param num
	 *            a non-negative number which acts as the upper bound for
	 *            calculation of perfect cubes from 0
	 */
	private void calculateCube(int num) {
		int i = 0;
		while ((i * i * i) <= num) {
			System.out.println(i * i * i);
			i++;
		}
	}
}

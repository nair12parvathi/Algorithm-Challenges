/**
 * 
 * filename: Headache.java
 * 
 * version: 1.0 04//2017
 *
 *         revisions: Initial version
 */
package algos5;

//import statements are placed here
import java.util.Scanner;

/*
 * This program computes the minimum units of headache you will incur getting all of the company employees on the ride.
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair 
 */
public class Headache {
	Scanner sc;
	int m;
	int n;
	String[] mLineSplit;
	String[] nLineSplit;
	StringBuffer mLine;
	StringBuffer nLine;
	int[][] S;

	public Headache() {
		sc = new Scanner(System.in);
		m = 0;
		n = 0;
		mLine = new StringBuffer();
		nLine = new StringBuffer();
		getInput();
		computeHeadache();
		display();
	}

	private void display() {
		System.out.println(S[m][n]);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(S[i][j]+" \t");
			}
			System.out.println();
		}
	}

	/**
	 * This function computes the headache, makes the dynamic programing array
	 * and computes the final result
	 */
	private void computeHeadache() {

		int temp = 0;
		int min = 0;
		int i = 0;
		int j = 0;

		// Base cases

		// for i=0,j=0, that is no one is there in either of the lines
		S[0][0] = 0;
		// for i=0 j=1, that is only one person is there in one of the line(m)
		S[0][1] = 0;
		// for i=1,j=0, that is only one person is there in one of the line(n)
		S[1][0] = 0;

		// for i=0, j>1 , that is when we are considering that people are only
		// there in 2nd line
		for (j = 2; j < n + 1; j++) {
			min = 4 + S[0][j - 1];
			if (("" + nLine.charAt(j) + nLine.charAt(j - 1)).equals("EN")

					|| ("" + nLine.charAt(j) + nLine.charAt(j - 1)).equals("NE")) {
				temp = S[0][j - 2] + 5;
				if (temp < min) {
					min = temp;
				}

			} else {
				temp = S[0][j - 2] + 0;
				if (temp < min) {
					min = temp;
				}
			}
			S[0][j] = min;
		}

		// for i>1 , j=0 , that is when we are considering that people are there
		// only on 1st line
		for (i = 2; i < m + 1; i++) {
			min = 4 + S[i - 1][0];
			if (("" + mLine.charAt(i) + mLine.charAt(i - 1)).equals("EN")
					|| ("" + mLine.charAt(i) + mLine.charAt(i - 1)).equals("NE")) {
				temp = S[i - 2][0] + 5;
				if (temp < min) {
					min = temp;
				}
			} else {
				temp = S[i - 2][0] + 0;
				if (temp < min) {
					min = temp;
				}
			}
			S[i][0] = min;
		}

		// for i=1,j=1, if both the lines have only one person in the lines
		if (("" + mLine.charAt(1) + nLine.charAt(1)).equals("EN")
				|| ("" + mLine.charAt(1) + nLine.charAt(1)).equals("NE")) {
			S[1][1] = 4;
		} else
			S[1][1] = 0;

		// for i=1,j>1, if 1st line has only one person in the line and 2nd line
		// has n persons
		i = 1;
		for (j = 2; j < n + 1; j++) {
			if (("" + mLine.charAt(i) + nLine.charAt(j)).equals("EN")
					|| ("" + mLine.charAt(i) + nLine.charAt(j)).equals("NE")) {
				min = 5 + S[i - 1][j - 1];
			} else
				min = S[i - 1][j - 1];

			// considering person from 1st line alone
			temp = 4 + S[i - 1][j];
			if (temp < min) {
				min = temp;
			}
			// considering person from 2nd line alone
			temp = 4 + S[i][j - 1];
			if (temp < min) {
				min = temp;
			}

			// considering 2 people from one line only
			if (("" + nLine.charAt(j) + nLine.charAt(j - 1)).equals("EN")
					|| ("" + nLine.charAt(j) + nLine.charAt(j - 1)).equals("NE"))
				temp = 3 + 5 + S[i][j - 2];
			else
				temp = 3 + S[i][j - 2];
			if (temp < min) {
				min = temp;
			}
			S[i][j] = min;
		}

		// for i>1, j=1, there are more than 1 people in 1st line and only 1
		// person in second line
		j = 1;
		for (i = 2; i < m + 1; i++) {
			if (("" + mLine.charAt(i) + nLine.charAt(j)).equals("EN")
					|| ("" + mLine.charAt(i) + nLine.charAt(j)).equals("NE")) {
				min = 5 + S[i - 1][j - 1];
			} else
				min = S[i - 1][j - 1];

			// considering person from 1st line alone
			temp = 4 + S[i - 1][j];
			if (temp < min) {
				min = temp;
			}
			// considering person from 2nd line alone
			temp = 4 + S[i][j - 1];
			if (temp < min) {
				min = temp;
			}

			// Considering 2 perople from the same line
			if (("" + mLine.charAt(i) + mLine.charAt(i - 1)).equals("EN")
					|| ("" + mLine.charAt(i) + mLine.charAt(i - 1)).equals("NE"))
				temp = 3 + 5 + S[i - 2][j];
			else
				temp = 3 + S[i - 2][j];
			if (temp < min) {
				min = temp;
			}
			S[i][j] = min;
		}

		// Generalized form --> when both lines have more than 2 people in both
		// lines
		for (i = 2; i < m + 1; i++) {
			for (j = 2; j < n + 1; j++) {

				// considering one person from both lines
				if (("" + mLine.charAt(i) + nLine.charAt(j)).equals("EN")
						|| ("" + mLine.charAt(i) + nLine.charAt(j)).equals("NE")) {
					min = 5 + S[i - 1][j - 1];

				} else {
					min = S[i - 1][j - 1];
				}

				// considering person from 1st line alone
				temp = 4 + S[i - 1][j];
				if (temp < min) {
					min = temp;
				}
				// considering person from 2nd line alone
				temp = 4 + S[i][j - 1];
				if (temp < min) {
					min = temp;
				}

				// Considering 2 people from the same line
				if (("" + mLine.charAt(i) + mLine.charAt(i - 1)).equals("EN")
						|| ("" + mLine.charAt(i) + mLine.charAt(i - 1)).equals("NE"))
					temp = 3 + 5 + S[i - 2][j];
				else
					temp = 3 + S[i - 2][j];
				if (temp < min) {
					min = temp;
				}

				if (("" + nLine.charAt(j) + nLine.charAt(j - 1)).equals("EN")
						|| ("" + nLine.charAt(j) + nLine.charAt(j - 1)).equals("NE"))
					temp = 3 + 5 + S[i][j - 2];
				else
					temp = 3 + S[i][j - 2];
				if (temp < min) {
					min = temp;
				}
				S[i][j] = min;
			}
		}

	}

	/**
	 * This function takes input from the command line
	 */
	private void getInput() {

		m = Integer.parseInt(sc.nextLine());
		n = Integer.parseInt(sc.nextLine());
		S = new int[m + 1][n + 1];
		mLineSplit = sc.nextLine().split(" ");
		nLineSplit = sc.nextLine().split(" ");
		mLine.append("" + 0);
		nLine.append("" + 0);
		for (int i = 0; i < mLineSplit.length; i++) {
			mLine.append(mLineSplit[i]);
		}
		for (int j = 0; j < nLineSplit.length; j++) {
			nLine.append(nLineSplit[j]);
		}

	}

	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		new Headache();

	}

}

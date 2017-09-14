package HW3;

/**
 * 
 * filename: MorseVowel.java
 * 
 * version: 1.0 03/22/2017
 *
 *         revisions: Initial version
 */
//import statements are placed here
import java.util.*;

/*
 * This program determines the number of possible decodings for this Morse Code string that comprise only vowels
 * using dynamic programming.
 *
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair 
 */

public class MorseVowel {
	Scanner sc;
	HashMap<String, String> hm;

	/**
	 * This is a constructor, which will be called once an object is created.
	 * 
	 */
	public MorseVowel() {
		hm = new HashMap<String, String>();
		sc = new Scanner(System.in);
		initializeHashMap();
		takeInput();
	}

	/**
	 * This function is used to initialize the Hash Map with Morse code for each
	 * and every vowel.
	 */
	private void initializeHashMap() {
		hm.put(".-", "A");
		hm.put(".", "E");
		hm.put("..", "I");
		hm.put("---", "O");
		hm.put("..-", "U");

	}

	/**
	 * This function will accept the length of string and ensure that it matches
	 * the user input.
	 */
	private void takeInput() {
		int n = sc.nextInt();
		String input = sc.next();
		if (n == input.length())
			findCombinations(input, n);
		else {
			System.out.println("Invalid string");
		}

	}

	/**
	 * This function will calculate the number of combination of vowel from the
	 * given Morse code.
	 * 
	 * @param input
	 *            It is data which is form of Morse code with length of n
	 * @param n
	 *            It is the length of string.
	 */
	private void findCombinations(String input, int n) {
		int window = 0;
		int[] S = new int[n]; // the number of combination stored in position k
								// for string length of k
		int[] temp = new int[3]; // the temp is defined as there is maximum of
									// length 3 for vowels.

		for (int i = 0; i < n; i++) {
			// base case where i = 0
			if (i == 0 && input.charAt(i) == '.') {
				S[i] = 1; // the number of combination is 1
				continue;
			}
			// negative case where i = 1 and invalid character is provided.
			if (i == 1 && input.charAt(i) == '.' && input.charAt(i - 1) == '-') {
				System.out.println(0);
				return;
			}
			// use sliding window 2 indicate there is two character so far
			if (i == 1) {
				window = 2;
			}
			// use sliding window 3 indicate there is at least three character
			// so far
			if (i > 1) {
				window = 3;
			}

			int k = i;
			int j = window - 1;
			if (i != 0) {
				// from 1 onwards, if it matches single morse code, it assigns the previous
				// combination to temp 
				if (hm.containsKey("" + input.charAt(i))) {
					if (k > 0)
						temp[j--] = S[--k];
				} else {
					temp[j--] = 0;
					k--;
				}
				//Check if it matches current and previous position for a vowel, it assigns 
				//previous combination where k > 0  
				//Otherwise it assign 0. 
				if (hm.containsKey("" + input.charAt(i - 1) + input.charAt(i))) {
					if (window == 2) {
						if (k == 0) {
							temp[j--] = 1;
						}
					} else if (k > 0) {
						temp[j--] = S[--k];
					}
				} else {
					temp[j--] = 0;
					k--;
				}
			}
			if (window == 2) {
				temp[2] = 0;
			}
			if (window == 3) {
				if (hm.containsKey("" + input.charAt(i - 2)
						+ input.charAt(i - 1) + input.charAt(i))) {
					if (k == 0) {
						temp[j--] = 1;
					} else
						temp[j--] = S[--k];
				} else {
					temp[j--] = 0;
					k--;
				}
			}
			//the sum of three combination at different position using sliding window
			int sum = temp[0] + temp[1] + temp[2];
			S[i] = sum;

		}
		//Output of this program 
		System.out.println(S[n - 1]);
	}

	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		new MorseVowel();
	}
}

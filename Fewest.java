

/**
 * 
 * filename: Fewest.java
 * 
 * version: 1.0 02/22/2017
 *
 *         revisions: Initial version
 */
//import statements are placed here
import java.util.Random;
import java.util.Scanner;

/*
 * This program prints out whether the minimum number of elements required to achieve a sum greater than the target. 
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair 
 */
public class Fewest {
	int target;

	/**
	 * This is an constructor, which will be called once an object is created.
	 * 
	 */
	public Fewest() {
		int elements[];
		Scanner sc = new Scanner(System.in);
		int size = enterSize(sc); // accept the size of elements.
		elements = new int[size];
		enterInput(elements, sc); // accept the target number and each
									// individual element till the size of
									// elements
		System.out.println(findTarget(elements, size, target));
	}

	/**
	 * This function will check whether the minimum number of element to achieve
	 * more than target.
	 * 
	 */
	private int findTarget(int number[], int size, int target) {
		// Generate random number between 0 to size and assigned to pivot
		int pivot = number[new Random().nextInt(size) + 0];
		// generate the size for L, which stand for any elements less than
		// pivot,
		// E , which stand for any elements equal to pivot and
		// G, which stand for any elements greater than pivot.
		int L[] = new int[size];
		int E[] = new int[size];
		int G[] = new int[size];
		// initializes all size, sum = 0
		int size_L = 0, size_E = 0, size_G = 0;
		int SumE = 0, SumG = 0;
		// Partition is performed for all elements which is less than, equal to
		// and greater than and sum for each partition is calculated.
		for (int rows = 0; rows < size; rows++) {
			if (number[rows] < pivot) {
				L[size_L++] = number[rows];
			}
			if (number[rows] == pivot) {
				E[size_E++] = number[rows];
				SumE += number[rows];
			}
			if (number[rows] > pivot) {
				G[size_G++] = number[rows];
				SumG += number[rows];
			}

		}
		// the logic follow similar approach as finding the median.
		// if the sum of elements which is greater than pivot is greater than
		// target, we need to do recursively with G elements to get
		// minimum amount of elements.
		if (SumG > target)
			return findTarget(G, size_G, target);
		// if the sum of elements, which is greater and equal to pivot is
		// greater than target, we need to do analyze whether the size of
		// elements in Equal partition is more than one or not.
		// If more than one elements, we need to get mimumum element in equal to
		// achieve the target.
		// If equal to one, just return the size of partition of Equal and
		// Greater list.
		else if ((SumG + SumE) > target) {
			if (size_E == 1)
				return (size_E + size_G);
			else {
				size_E = 0;
				target -= SumG;
				while (target >= 0) {
					target -= pivot;
					size_E++;
				}
				return (size_G + size_E);
			}
			// lastly, the sum of equal and greater partition is lesser than
			// target,
			// we need to get some elements from lower partition to achieve the
			// target.

		} else
			return ((size_E + size_G) + findTarget(L, size_L,
					(target - (SumE + SumG))));

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
	 * This function will accept the target and the data for each individual
	 * elements.
	 * 
	 * @elements This is an array which data for each individual is accepted.
	 * 
	 * @param sc
	 *            This is an object of Scanner
	 */
	private void enterInput(int elements[], Scanner sc) {
		target = sc.nextInt();
		for (int rows = 0; rows < elements.length; rows++)
			elements[rows] = sc.nextInt();
	}

	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		new Fewest();

	}

}

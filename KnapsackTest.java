

/**
 * 
 * filename: KnapsackTest.java
 * 
 * version: 1.0 03/22/2017
 * 
 * revisions: Initial version
 */
// import statements are placed here

/*
 * This program determines the time performance between the Recursive Knapsack
 * program and Indivisible Knapsack problem.
 * 
 * 
 * @author vpb8262 Vishal Bulchandani
 * 
 * @author pan7447 Parvathi Nair
 */
public class KnapsackTest {

	private int value[], weight[];
	long start, finish;
	int size[] = { 5, 10, 20, 50, 100, 1000, 10000 };
	
	/**
	 * This is a constructor, which will be called once an object is created.
	 * 
	 */
	public KnapsackTest() {
		calculateTime_DP();
		calculateTime_Recursive();
	}
	/**
	 * This is a function which calculate the time for recursion of Knapsack problem
	 *
	 */
	private void calculateTime_Recursive()
	{
		for (int i = 0; i < size.length; i++) {
			weight = value = new int[size[i]];
			for (int j = 0; j < weight.length; j++)
				weight[j] = value[j] = 1;
			start = System.nanoTime();
			System.out.println(knapsack_rec(size[i] - 1, size[i] / 2));
			finish = System.nanoTime();
			System.out
					.println("Time execution for Recursive Knapsack in nanosecond : "
							+ (finish - start));
		}
	}

/**
 * This function which calculate the time of Knapsack problem using Dynamic Programming
 */
	
	private void calculateTime_DP(){
		for (int i = 0; i < size.length; i++) {
			weight = value = new int[size[i]];
			for (int j = 0; j < weight.length; j++)
				weight[j] = value[j] = 1;
			start = System.nanoTime();
			knapsack_indivisible(size[i] - 1, size[i] / 2);
			finish = System.nanoTime();
			System.out
					.println("Time execution for Dynamic Programming Knapsack in nanosecond : "
							+ (finish - start));
		}
		
	}
/**
 * This function will calculate the knapsack problem using recursion method.
 * This psuedocode is mentioned in slide itself. 
 * @param size 
 * 			the size of an item of weight.
 * @param capacity
 * 			the capacity of weight to be required.
 * @return
 */
	public int knapsack_rec(int size, int capacity) {
		if (size <= 0)
			return 0;
		if (capacity < weight[size])
			return (knapsack_rec(size - 1, capacity));
		return java.lang.Math.max(knapsack_rec(size - 1, capacity), value[size]
				+ knapsack_rec(size - 1, capacity - weight[size]));
	}

	/**
	 * This function will calculate the knapsack problem using dynamic programming.
	 * This psuedocode is mentioned in slide.   
	 * @param size
	 * 
	 * @param capacity
	 * 			the capacity of weight to be required
	 */
	public void knapsack_indivisible(int size, int capacity) {
		int result[][] = new int[size + 1][capacity + 1];
		for (int i = 0; i <= capacity; i++)
			result[0][i] = 0;
		for (int j = 0; j <= size; j++)
			result[j][0] = 0;
		for (int col = 1; col <= capacity; col++) {
			for (int row = 1; row <= size; row++) {
				result[row][col] = result[row - 1][col];
				if (capacity > weight[row]
						&& (result[row - 1][col - weight[row]] + value[row] > result[row][col]))
					result[row][col] = result[row - 1][col - weight[row]]
							+ value[row];
			}

		}
		System.out.println(result[size][capacity]);
	}
	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		new KnapsackTest();
	}

}

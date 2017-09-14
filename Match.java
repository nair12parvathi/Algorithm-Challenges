/**
 * 
 * filename: Match.java
 * 
 * version: 1.0 02/08/2017
 *
 *         revisions: Initial version
 */

//import statements are placed here
import java.util.*;
/*
 * Given 2 sets of preferences for stable matching, this program determines the number of elements from the first set that have only
one valid partner 
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair 
 */

public class Match {
	static Scanner sc = new Scanner(System.in);
	static int firstGroup[][];
	static int secondGroup[][];
	static int fg[];
	static int sg[];
	int flag;
	static int secondGroupP[];
	private int count = 0;
	private static int size;
	private int counter;
	private boolean[] firstGroupMembersPaired;
	static int[] firstPartnered;
	static int[] secondPartnered;
	int[][] Position;
	private static HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();

	/*
	 * This is a constructor of the class
	 */
	public Match(int[] fg, int[] sg, int[][] firstGroup, int[][] secondGroup, int flag) {
		this.fg = fg;
		this.sg = sg;
		this.firstGroup = firstGroup;
		this.secondGroup = secondGroup;
		initialize();
		counter = 0;
		firstGroupMembersPaired = new boolean[size];
		SecondGroupPosition(secondGroup);
		stableMatching(flag);
	}

	/*
	 * This function forms pairs with the help of the preference lists of both
	 * first and second group
	 * 
	 * @param flag this is used to basically identify, if the function acts
	 * firstGroup optimal or secondGroup optimal if flag is 0 it is firstGroup
	 * optimal and if flag is 1 it is secondGroup optimal
	 */
	private void stableMatching(int flag) {
		// initializes both the firstPartnered and secondPartnered to -1, that
		// is no one in both the groups have been partnered
		for (int i = 0; i < size; i++)
			firstPartnered[i] = secondPartnered[i] = -1;

		while (counter < size) {
			int notEngaged;

			for (notEngaged = 0; notEngaged < size; notEngaged++)
				if (!firstGroupMembersPaired[notEngaged])
					break;

			for (int i = 0; i < size && !firstGroupMembersPaired[notEngaged]; i++) {
				int index = secondIndexOf(firstGroup[notEngaged][i]);
				// this is executed when the second group members have not been
				// partnered and are available
				if (secondGroupP[index] == -1) {
					// if flag is 0 first group is proposing second group and we
					// PUT the values in hash map and if flag is 1, then we
					// second group is proposing first group and we get the
					// values from the hashmap and check if same partners are
					// formed. If same partners are formed we increment the
					// count.
					if (flag == 0) {
						hm.put(notEngaged, index);
						firstPartnered[notEngaged] = index;
						secondPartnered[firstGroup[notEngaged][i]] = notEngaged;
					}

					else {

						if (hm.get(index) == notEngaged)
							count++;
					}
					secondGroupP[index] = fg[notEngaged];
					firstGroupMembersPaired[notEngaged] = true;
					counter++;
				}

				// this is executed when second group members have been
				// partnered but wants to check if the current proposed
				// partner is better than the already partnered one
				else {
					int currentPartner = secondGroupP[index];
					if (morePreference(currentPartner, fg[notEngaged], index)) {
						if (flag == 0) {
							hm.put(notEngaged, index);
						} else {
							if (hm.get(index) == notEngaged)
								count++;
						}

						secondGroupP[index] = fg[notEngaged];
						firstGroupMembersPaired[notEngaged] = true;
						firstGroupMembersPaired[firstIndexOf(currentPartner)] = false;
					}
				}
			}
		}
		if (flag == 1)
			System.out.println(count);

	}

	/*
	 * This function takes the secondGroup preference matrix as input and
	 * creates a new position matrix where it stores the preference of the
	 * partners as value of matrix
	 * 
	 * @param int[][] secondGroup this is the preference matrix of the
	 * secondGroup
	 */
	private void SecondGroupPosition(int[][] secondGroup) {
		Position = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Position[i][secondGroup[i][j]] = j;
			}
		}

	}

	/*
	 * This function checks if the secondGroup member prefers the current
	 * partner or the new partner
	 * 
	 * @param curPartner this is the current partner assigned
	 * 
	 * @param newPartner this is the newPartner whose preference is to be
	 * checked with the current partner
	 * 
	 * @param index this is the secondGroup member, that is x-coordinate of the
	 * position matrix
	 * 
	 */
	private boolean morePreference(int curPartner, int newPartner, int index) {

		if (Position[index][curPartner] < Position[index][newPartner])
			return false;
		else
			return true;

	}

	/*
	 * This function returns the member with which the member of the first group
	 * has partnered
	 * 
	 * @param curr this is the member whose partner is to be returned
	 */
	private int firstIndexOf(int curr) {
		if (firstPartnered[curr] != -1)
			return firstPartnered[curr];
		else
			return curr;

	}

	/*
	 * This function returns the member with which the member of the second
	 * group has partnered
	 * 
	 * @param curr this is the member whose partner is to be returned
	 */
	private int secondIndexOf(int curr) {
		if (secondPartnered[curr] != -1)
			return secondPartnered[curr];
		else

			return curr;
	}

	/*
	 * This function initializes the array secondGroupP to -1, -1 indicates that
	 * the member has never been proposed to
	 */
	public void initialize() {
		secondGroupP = new int[size];
		for (int i = 0; i < secondGroupP.length; i++)
			secondGroupP[i] = -1;
	}

	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {

		size = sc.nextInt();
		fg = sg = new int[size];
		for (int i = 0; i < size; i++)
			fg[i] = sg[i] = i;

		firstGroup = new int[size][size];
		secondGroup = new int[size][size];
		firstPartnered = new int[size];
		secondPartnered = new int[size];
		secondGroupP = new int[size];

		for (int row = 0; row < firstGroup.length; row++) {
			for (int col = 0; col < firstGroup[row].length; col++) {
				firstGroup[row][col] = sc.nextInt();
			}
		}
		for (int row = 0; row < secondGroup.length; row++) {
			for (int col = 0; col < secondGroup[row].length; col++) {
				secondGroup[row][col] = sc.nextInt();
			}
		}
		new Match(fg, sg, firstGroup, secondGroup, 0);
		new Match(sg, fg, secondGroup, firstGroup, 1);

	}

}


/**
 * 
 * filename: Ftree.java
 * 
 * version: 1.0 04/26/2017
 * 
 * revisions: Initial version
 */
// import statements are placed here

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * This program will compute the cost of F - Spanning if cycle do not form, else -1
 * 
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair
 */

public class FTree {
	int edge_no, vert_no, edgeData[][];
	LinkedList<Integer>[] edges, backup_edge;

	/**
	 * This is a constructor, which will be called once an object is created.
	 * 
	 */
	private FTree() {
		takeInput(); // accept the input
		sortbyAsc(2); // sort the column number 2 by ascending order (weight of
						// edges)
		sortbyDesc(3); // sort the column number 3 by descending order (f value)
		performKushalAlgorithm();
	}

	/**
	 * This function will sort the data by ascending order with respect to
	 * column. The time complexity is O(nlogn)
	 */
	private void sortbyAsc(int i) {
		final int col = i;
		Arrays.sort(edgeData, new Comparator<int[]>() {
			public int compare(int[] arg0, int[] arg1) {
				return Integer.compare(arg0[col], arg1[col]);
			}
		});
	}

	/**
	 * This function will sort the data by descending order with respect to
	 * column. The time complexity is O(nlogn)
	 */
	private void sortbyDesc(int i) {
		final int col = i;
		Arrays.sort(edgeData, new Comparator<int[]>() {
			public int compare(int[] arg0, int[] arg1) {
				return Integer.compare(arg1[col], arg0[col]);
			}
		});
	}

	/**
	 * This function will perform Kushal Algorithm using BFS approach.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void performKushalAlgorithm() {
		int edgeCount = 0;
		int edgeWeight = 0;
		int position = 0;
		edges = new LinkedList[vert_no];

		for (int i = 0; i < vert_no; i++)
			edges[i] = new LinkedList<Integer>();
		// this below for loop function will accept all edge whose fvalue is 1.
		for (position = 0; position < edge_no; position++) {
			if (edgeData[position][3] == 0)
				break;
			edges[edgeData[position][0]].add(edgeData[position][1]);
			edges[edgeData[position][1]].add(edgeData[position][0]);
			edgeWeight += edgeData[position][2];
		}
		// this below function will check all fvalue 1 edge form a cycle or not.
		if (isCyclic(edges)) {
			System.out.println(-1);
			System.exit(0);
		}
		edgeCount = position;
		// this below function will accept other edge whose fvalue is 0,
		// providing fvalue 1 edge do not form a cycle
		for (; position < edge_no; position++) {
			if (edgeCount == vert_no - 1) {
				break;
			}
			edges[edgeData[position][0]].add(edgeData[position][1]);
			edges[edgeData[position][1]].add(edgeData[position][0]);
			// if edge form a cyle, we have to remove last edge ensuring that it
			// wont form a cycle.
			if (isCyclic(edges)) {
				edges[edgeData[position][0]].removeLast();
				edges[edgeData[position][1]].removeLast();
			} else {
				edgeWeight += edgeData[position][2];
				edgeCount++;

			}

		}
		// the minimum spanning trees is satisfied only if the number of edges
		// is equal to the number of vertex.
		if (edgeCount == vert_no - 1)
			System.out.println(edgeWeight);
		else
			System.out.println(-1);
	}

	/**
	 * This function will check if given edge will form a cycle or not.
	 * 
	 * @param alist
	 *            : list of edge
	 * 
	 * @return true if graph with given edge forms a cycle or false if graph
	 *         with given edge did not form a cycle.
	 */
	public Boolean isCyclic(LinkedList<Integer>[] alist) {
		// visited array indicates if the vertex is visited
		// and if visited it stores the parent of the vertex
		int[] parent = new int[vert_no];
		// -1 indicates not visited
		Arrays.fill(parent, -1);
		for (int i = 0; i < vert_no; i++) {
			if (parent[i] != -1)
				continue;
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(i);
			parent[i] = i;
			while (!q.isEmpty()) {
				int src = q.poll();
				// This for loop will check in each vertex in each edge, who end
				// vertex whether
				// it have multiple parent or not.
				// If it have multiple parent, it forms cycle.
				for (int adj : alist[src]) {
					if (adj == src || (parent[adj] != -1 && parent[src] != adj))
						return true;
					if (parent[src] == adj)
						continue;
					parent[adj] = src;
					q.add(adj);
				}
			}
		}
		return false;
	}

	/**
	 * The function will accept the vertex and edge and the following edge with
	 * their weight and fvalue.
	 * 
	 */
	private void takeInput() {
		Scanner ob = new Scanner(System.in);
		vert_no = ob.nextInt();
		edge_no = ob.nextInt();
		edgeData = new int[edge_no][4];
		for (int row = 0; row < edge_no; row++) {
			edgeData[row][0] = ob.nextInt();
			edgeData[row][1] = ob.nextInt();
			edgeData[row][2] = ob.nextInt();
			edgeData[row][3] = ob.nextInt();

		}
	}

	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 */

	public static void main(String[] args) {
		new FTree();
	}

}

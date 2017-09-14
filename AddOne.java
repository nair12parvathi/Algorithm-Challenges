
/**
 * 
 * filename: AddOne.java
 * 
 * version: 1.0 04/26/2017
 *
 *         revisions: Initial version
 */

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Stack;

/**
 * Given a directed graph and assuming it is not strongly connected, this
 * program determines if it is possible to add just a single edge to the graph
 * such that it becomes strongly connected. It displayes 'YES' if it can and
 * 'NO' if it can't
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair
 *
 */
public class AddOne {
	Scanner sc;
	List<List<Integer>> adj_list;
	List<List<Integer>> reverse_adj_list;
	List<List<Integer>> connectedComponents;
	List<List<Integer>> reverse_connectedComponents;
	int vert;
	int countComp;
	int[] compBelong;
	int destSCC;
	int prevsrc;
	int prevdest;

	/**
	 * This is the constructor which will be called once the object is created
	 */
	public AddOne() {
		sc = new Scanner(System.in);
		adj_list = new ArrayList<List<Integer>>();
		reverse_adj_list = new ArrayList<List<Integer>>();
		connectedComponents = new ArrayList<List<Integer>>();
		reverse_connectedComponents = new ArrayList<List<Integer>>();
		countComp = 0;
		destSCC = 0;
		prevsrc = -1;
		prevdest = -1;
	}

	/**
	 * This is the main method
	 * 
	 * @param args
	 *            command line arguments are ignored
	 */
	public static void main(String[] args) {
		AddOne addone = new AddOne();
		addone.initialize();
	}

	/**
	 * This method is used to initialize the adjacency list depending on the
	 * number of vertices taken as input from the console
	 */
	private void initialize() {
		vert = Integer.parseInt(sc.nextLine());
		// This array is created to store the component number each vertex of
		// the original graph belong to
		compBelong = new int[vert];
		for (int i = 0; i < vert; i++) {
			adj_list.add(new ArrayList<Integer>());
			reverse_adj_list.add(new ArrayList<Integer>());
		}
		addEdges();
		findSCC();
		checkStronglyConnected();
	}

	/**
	 * This method creates adjacency list taking input from the console
	 */
	private void addEdges() {
		for (int i = 0; i < vert; i++) {
			int src = i;
			String destNodes = sc.nextLine();
			String[] split = destNodes.split(" ");
			if (split.length == 1 && Integer.parseInt(split[0]) == 0) {
				continue;
			}
			for (int j = 0; j < split.length; j++) {
				int dest = Integer.parseInt(split[j]);
				if (dest != 0) {
					addEdge(src, dest - 1);
				}
			}
		}
	}

	/**
	 * This method finds the strongly connected components and updates the
	 * number of components formed in variable countComp
	 */
	private void findSCC() {
		// Stack is used to store the vertices according to finishing time
		Stack<Integer> stack = new Stack<Integer>();
		boolean visited[] = new boolean[vert];
		for (int i = 0; i < vert; i++) {
			if (visited[i] == false)
				fillStack(i, visited, stack);
		}
		reverseGraph();
		visited = new boolean[vert];
		while (stack.empty() == false) {
			int v = (int) stack.pop();
			if (visited[v] == false) {
				DFSInRevGraph(v, visited);
				// this variable is used to store the number of components
				// formed in the graph
				countComp++;
			}
		}
	}

	/**
	 * This method creates the graph considering the strongly connected
	 * components as vertices
	 */
	private void checkStronglyConnected() {
		initNewGraph();
		boolean[] visited = new boolean[compBelong.length];
		for (int i = 0; i < compBelong.length; i++) {
			for (int j = 0; j < adj_list.get(i).size(); j++) {
				int n = adj_list.get(i).get(j);
				// here we check if there is an edge between two vertices in the
				// original graph and they belong to different components then
				// draw an edge between those components
				if (compBelong[i] != compBelong[n]) {
					int src = compBelong[i];
					int dest = compBelong[n];
					addEdgeForSCC(src, dest);
				}
			}
		}
		addNewEdgeFromLastToFirstSCC();
	}

	/**
	 * This method fills the adjacency list for the graph created where strongly
	 * connected components are the new vertices
	 * 
	 * @param src
	 *            -vertex in which edge is to be added
	 * @param dest
	 *            - destination vertex of the edge
	 */
	private void addEdgeForSCC(int src, int dest) {
		if (src != prevsrc) {
			connectedComponents.get(src).add(dest);
			prevsrc = src;
			prevdest = dest;
		} else {
			if (dest != prevdest) {
				connectedComponents.get(src).add(dest);
				prevsrc = src;
				prevdest = dest;
			}
		}
	}

	/**
	 * This creates an edge from the last component to the first component of
	 * the strongly connected component graph
	 */
	private void addNewEdgeFromLastToFirstSCC() {
		int src = connectedComponents.size() - 1;
		int dest = 0;
		connectedComponents.get(src).add(dest);
		computeResult();
	}

	/**
	 * This method applies the algorithm to find strongly connected components
	 * in the newly formed graph of strongly connected components from the
	 * original graph
	 * 
	 * If the strongly connected component formed is only 1, then the original
	 * graph can be a strongly connected graph if an edge is added and the
	 * answer 'YES' is displayed
	 */
	private void computeResult() {
		//This variable is used to keep track of the number of components formed in the graph
		int countCompSCC = 0;
		//Stack is used to store the vertices according to their finishing time.
		Stack<Integer> stack = new Stack<Integer>();
		boolean visited[] = new boolean[countComp];
		for (int i = 0; i < countComp; i++) {
			if (visited[i] == false)
				fillStackSCC(i, visited, stack);
		}
		reverseGraphSCC();
		visited = new boolean[countComp];
		while (!stack.empty()) {
			int v = (int) stack.pop();
			if (visited[v] == false) {
				DFSInRevGraphSCC(v, visited);
				countCompSCC++;
			}
		}

		if (countCompSCC == 1) {
			System.out.println("YES");
		} else
			System.out.println("NO");
	}

	/**
	 * 
	 * This method applies Depth First Search in the reverse graph of the
	 * Strongly connected component graph
	 * 
	 * @param v-
	 *            vertex from where DFS is started
	 * @param visited-
	 *            boolean array to keep track of visited vertices
	 */
	private void DFSInRevGraphSCC(int v, boolean[] visited) {
		visited[v] = true;
		int n;
		for (int i = 0; i < reverse_connectedComponents.get(v).size(); i++) {
			n = reverse_connectedComponents.get(v).get(i);
			if (!visited[n])
				DFSInRevGraphSCC(n, visited);
		}
	}

	/**
	 * This method forms the reverse graph of the strongly connected component
	 * graph
	 */
	private void reverseGraphSCC() {
		for (int i = 0; i < countComp; i++) {
			for (int j = 0; j < connectedComponents.get(i).size() && connectedComponents.get(i).size() > 0; j++) {
				int src = connectedComponents.get(i).get(j);
				int dest = i;
				addReverseEdgeSCC(src, dest);
			}
		}
	}

	/**
	 * This method adds edges in the reverse graph of strongly connected
	 * component graph
	 * 
	 * @param src
	 *            -vertex in which edge is to be added
	 * @param dest
	 *            - destination vertex of the edge
	 */
	private void addReverseEdgeSCC(int src, int dest) {
		reverse_connectedComponents.get(src).add(dest);
	}

	/**
	 * This method stores the vertices in the order of finishing time which is
	 * used tomake reverse of the graph
	 * 
	 * @param v-vertex
	 *            from where DFS is applied
	 * @param visited-boolean
	 *            array to keep track of the vertices visited
	 * @param stack-store
	 *            the vertices in order of their finishing time
	 */
	private void fillStackSCC(int v, boolean[] visited, Stack<Integer> stack) {
		visited[v] = true;
		for (int i = 0; i < connectedComponents.get(v).size(); i++) {
			int n = connectedComponents.get(v).get(i);
			if (!visited[n])
				fillStackSCC(n, visited, stack);
		}
		//The vertices are pushed into stack as and when it finishes, and there are no more further path to go
		stack.push(new Integer(v));
	}

	/**
	 * This method initializes the arraylist to form the new graph, considering
	 * the strongly connected components of the original graph as vertices
	 */
	private void initNewGraph() {
		for (int i = 0; i < countComp; i++) {
			connectedComponents.add(new ArrayList<Integer>());
			reverse_connectedComponents.add(new ArrayList<Integer>());
		}
	}

	/**
	 * This method applies DFS in the revers graph of the original graph
	 * 
	 * @param v-
	 *            vertex from where DFS is applied
	 * @param visited-boolean
	 *            array to keep track of the visited vertices
	 */
	private void DFSInRevGraph(int v, boolean[] visited) {
		visited[v] = true;
		compBelong[v] = countComp;
		int n;
		for (int i = 0; i < reverse_adj_list.get(v).size(); i++) {
			n = reverse_adj_list.get(v).get(i);
			if (!visited[n])
				DFSInRevGraph(n, visited);
		}
	}

	/**
	 * This method creates the reverse of the original graph
	 */
	private void reverseGraph() {
		for (int i = 0; i < vert; i++) {
			for (int j = 0; j < adj_list.get(i).size() && adj_list.get(i).size() > 0; j++) {
				int src = adj_list.get(i).get(j);
				int dest = i;
				addReverseEdge(src, dest);
			}
		}
	}

	/**
	 * This method adds edges in the reverse graph of the original graph
	 * 
	 * @param src
	 *            -vertex in which edge is to be added
	 * @param dest
	 *            - destination vertex of the edge
	 */
	private void addReverseEdge(int src, int dest) {
		reverse_adj_list.get(src).add(dest);
	}

	/**
	 * 
	 * This method stores the vertices in the order of finishing time which is
	 * used tomake reverse of the graph
	 * 
	 * @param v-vertex
	 *            from where DFS is applied
	 * @param visited-boolean
	 *            array to keep track of the vertices visited
	 * @param stack-store
	 *            the vertices in order of their finishing time
	 * 
	 */
	private void fillStack(int v, boolean[] visited, Stack<Integer> stack) {
		visited[v] = true;
		if (adj_list.get(v).size() == 0) {
			stack.push(new Integer(v));
			return;
		}
		for (int i = 0; i < adj_list.get(v).size(); i++) {
			int n = adj_list.get(v).get(i);
			if (!visited[n])
				fillStack(n, visited, stack);
		}
		//The vertices are pushed into stack as and when it finishes, and there are no more further path to go
		stack.push(new Integer(v));
	}

	/**
	 * This method adds edges,given the source and destination, basically fills
	 * the adjacency list
	 * 
	 * @param src
	 *            -vertex in which edge is to be added
	 * @param dest
	 *            - destination vertex of the edge
	 */
	private void addEdge(int src, int dest) {
		adj_list.get(src).add(dest);
	}
}

/**
 * 
 * filename: VertexWeight.java
 * 
 * version: 1.0 04/26/2017
 * 
 * revisions: Initial version
 */
// import statements are placed here

import java.util.Scanner;

/*
 * This program will compute the shortest path of vertex weight.
 * 
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair
 */
class VertexWeight{
	int V;
	int startNode;
	int vertex[];
	int weight[][];
	int dist[];
	boolean parent[];

	/**
	 * This is a constructor, which will be called once an object is created.
	 * 
	 */
	public VertexWeight() {
		acceptInput();
	}

	/**
	 * This function will accept the input for edge or vertex and its weight.
	 */
	private void acceptInput() {
		Scanner ob = new Scanner(System.in);
		V = ob.nextInt();
		init();
		int E = ob.nextInt();
		for (int i = 0; i < V; i++)
			vertex[i] = ob.nextInt();
		for (int num = 0; num < E; num++) {
			int v1 = ob.nextInt();
			int v2 = ob.nextInt();
			weight[v1 - 1][v2 - 1] = vertex[v2 - 1];
			weight[v2 - 1][v1 - 1] = vertex[v1 - 1];
		}
		startNode = ob.nextInt() - 1;
		dijkstra(startNode);
	}

	/**
	 * This function will perform the array initialization of given size.
	 */
	private void init() {
		vertex = new int[V];
		dist = new int[V];
		parent = new boolean[V];
		weight = new int[V][V];
	}

	/**
	 * This function will pick up the minimum distance of vertex from set of vertice , which is not visited
	 * 
	 * @return the position of vertex.
	 */
	private int minDistance() {
		int min = Integer.MAX_VALUE, min_index = -1;
		for (int v = 0; v < V; v++)
			if (parent[v] == false && dist[v] <= min) {
				min = dist[v];
				min_index = v;
			}
		return min_index;
	}

	/*
	 * This function will perform dijkstra algorithm with given start node.
	 */
	private void dijkstra(int src) {
		// Initialize all distances as INFINITE and parent[] as false, which indicate it is not visited
		for (int i = 0; i < V; i++) {
			dist[i] = Integer.MAX_VALUE;
			parent[i] = false;
		}
		dist[src] = vertex[src]; // since start vertex is itself having weight.

		// Find shortest path for all vertices
		for (int count = 0; count < V - 1; count++) {
			int u = minDistance();
			//Mark the vertex as visited
			parent[u] = true;
			update(u);

		}
		//print the output
		printDistance();
	}

	/**
	 * 
	 * This function will update dist[v] if the total weight of path from source to v through u 
	 * is smaller than current value of dist[v] 
	 * @param u
	 *            : The index where shortest paths is calculated form vertex u.
	 */
	private void update(int u) {
		for (int v = 0; v < V; v++)
			if (parent[v] == false && weight[u][v] != 0
					&& dist[u] != Integer.MAX_VALUE
					&& dist[u] + weight[u][v] < dist[v]) {
				dist[v] = dist[u] + weight[u][v];

			}
	}

	/**
	 * The distance is printed for all vertex.
	 */
	private void printDistance() {
		for (int i = 0; i < dist.length; i++)
			System.out.print(dist[i] + " ");
	}

	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		new VertexWeight();
	}
}


/**
 * 
 * filename: Components.java
 * 
 * version: 1.0 04/03/2017
 * 
 * revisions: Initial version
 */
// import statements are placed here

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/*
 * This program will calculate the overall number of connected components of given undirected graph. 
 * 
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair
 */

public class Components {
	int vert;
	int edge;
	List<List<Integer>> adjList = new ArrayList<List<Integer>>();
	/**
	 * This is a constructor, which will be called once an object is created.
	 * 
	 */
	public Components() {
		Scanner sc = new Scanner(System.in);
		acceptInput(sc); // accept an input for number of vertex and edges
		createAdjList();
		acceptEdge(sc); // accept an input for edge
		System.out.println(countConnectedComponents());
	}

	/**
	 * This function create adjacent list for each and every vertex 
	 * 
	 */
	private void createAdjList() {
		for(int i =0;i<vert;i++)
				adjList.add(new ArrayList<Integer>());
	}

	/**
	 * This function is used to count the connected component of given
	 * undirected graph with edge and vertex.
	 * 
	 */
	private int countConnectedComponents() {
		int result = 0;
		boolean visited[] = new boolean[vert];
		Arrays.fill(visited, false);
		for(int position=0;position<vert;position++)
		{
			if(!visited[position]){
				dfs(visited, position, adjList);
				result++;
			}
		}
		return result;
	}
	/**
	 * This function will perform depth search using adjacent list. 
	 * @param visited
	 * 				the array of visited indicate whether it is visited or unvisited. 
	 * @param position
	 * 				the vertex numbered as i.
	 * @param adjList
	 * 				tbe adjacent list for each and every vertex
	 */
	private void dfs(boolean[] visited, int position, List<List<Integer>> adjList) {
			visited[position] = true;
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(position);
			while(!stack.isEmpty()){
				int value = stack.pop();
				for(int elements :adjList.get(value))
				{
					if(!visited[elements])
					{
						visited[elements] = true;
						stack.push(elements);
					}
				}
				
			}
		
	}

	/**
	 * The function will accept the edge between two vertex in undirected graph.
	 * 
	 * @param sc
	 *            Scanner sc is an object for Scanner
	 */
	private void acceptEdge(Scanner sc) {
		for(int i = 0; i<edge; i++){
			int first = sc.nextInt() - 1 ;
			int second = sc.nextInt() - 1;
			adjList.get(first).add(second);
			adjList.get(second).add(first);
		}
	}

	/**
	 * The function will accept the vertex and edge.
	 * 
	 */
	private void acceptInput(Scanner sc) {
		vert = sc.nextInt();
		edge = sc.nextInt();
	}

	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String arg[]) {
		new Components();
	}
}

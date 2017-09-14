

/**
 * 
 * filename: Chessboard.java
 * 
 * version: 1.0 05/10/2017
 *
 *         revisions: Initial version
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * Given a chessboard, this program checks if it is possible to cover all of the
 * empty squares on the chess board by non-overlapping dominos
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair
 *
 */
public class Chessboard {
	int d1;
	int d2;
	int[][] matrix;
	Scanner sc;
	Element[][] matrixElements;
	List<List<Integer>> adj_list;
	Element source;
	Element sink;
	int countBlack;
	int countWhite;
	HashMap<Integer, Integer> predecessor_list;
	Queue<Integer> queue;
	int countPaths;

	/**
	 * This is the constructor
	 */
	public Chessboard() {
		queue = new LinkedList<Integer>();
		predecessor_list = new HashMap<Integer, Integer>();
		sc = new Scanner(System.in);
		takeInputRowCol();
		matrix = new int[d1][d2];
		takeInputChessboard();

		initializeElements();
		adj_list = new ArrayList<List<Integer>>();
		createBipartiteGraph();

		while (countWhite != 0) {
			predecessor_list = new HashMap<Integer, Integer>();
			queue = new LinkedList<Integer>();
			searchPathBFS();

			countWhite--;
		}

		/**
		 * Here it checks if the total paths obtained is equal to the number of
		 * black vertices. If both are equal, display 'Yes' else 'No'
		 */
		if (countPaths == countBlack) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

	}

	/**
	 * This function runs BFS to check for paths from the source node to the
	 * sink node. It keeps track of the number of paths it gets in variable
	 * countPaths
	 */
	private void searchPathBFS() {
		Integer start = 0;
		Integer end = adj_list.size() - 1;
		queue.add(start);
		predecessor_list.put(0, 0);
		while (!queue.isEmpty()) {
			int current = queue.remove();
			if (current == end) {
				countPaths++;
				formResidualGraph(start, end);
				break;
			}
			for (int i = 0; i < adj_list.get(current).size(); i++) {
				if (!predecessor_list.containsKey(adj_list.get(current).get(i))) {
					queue.add(adj_list.get(current).get(i));
					predecessor_list.put(adj_list.get(current).get(i), current);
				}
			}

		}

	}

	/**
	 * This function checks the path and reverses the edges in the graph, it
	 * basically forms the residual graph
	 * 
	 * @param start
	 *            -node where the path begins
	 * @param end
	 *            -node where the path ends
	 */
	private void formResidualGraph(int start, int end) {
		int current;

		current = end;
		while (current != start) {

			current = predecessor_list.get(current);

			adj_list.get(current).remove((Integer) end);
			adj_list.get(end).add(current);
			end = current;

		}

	}

	/**
	 * This function creates the bipartite graph. It adds two extra vertices
	 * 'source' and 'sink'. Source is connected to the elements in the
	 * chessboard which are assumed to be black and they are connected to their
	 * neighbors which are white and the whites are connected to the sink
	 */
	private void createBipartiteGraph() {
		adj_list.add(new ArrayList<Integer>()); // for source
		for (int i = 0; i < d1; i++) {
			for (int j = 0; j < d2; j++) {
				if (matrix[i][j] == 0) {
					adj_list.add(new ArrayList<Integer>());
				}
			}
		}
		adj_list.add(new ArrayList<Integer>()); // for sink

		addEdges();
		checkBlackEqualsWhite();

	}

	/**
	 * Here, it checks if the number of black boxes is equal to the number of
	 * white boxes. If it is not equal then dominos cannot be placed, so it
	 * displays 'No'
	 */
	private void checkBlackEqualsWhite() {

		if (countBlack != countWhite) {
			System.out.println("NO");
			System.exit(0);
		}
	}

	/**
	 * This function adds edges to the vertices, also keeps track of the total
	 * number of blacks
	 */
	private void addEdges() {
		Element right;
		Element bottom;
		Element left;
		Element top;

		for (int i = 0; i < d1; i++) {
			for (int j = 0; j < d2; j++) {
				if (matrix[i][j] == 0 && matrixElements[i][j].color.equals("black")) {
					countBlack++;
					adj_list.get(0).add(matrixElements[i][j].indexPos);
					// right
					if ((j + 1 < d2) && matrix[i][j + 1] == 0 && matrixElements[i][j + 1].color.equals("white")) {
						right = matrixElements[i][j + 1];
						adj_list.get(matrixElements[i][j].indexPos).add(right.indexPos);

					}
					// bottom
					if ((i + 1 < d1) && matrix[i + 1][j] == 0 && matrixElements[i + 1][j].color.equals("white")) {
						bottom = matrixElements[i + 1][j];
						adj_list.get(matrixElements[i][j].indexPos).add(bottom.indexPos);
					}
					// left
					if ((j - 1 >= 0) && matrix[i][j - 1] == 0 && matrixElements[i][j - 1].color.equals("white")) {
						left = matrixElements[i][j - 1];
						adj_list.get(matrixElements[i][j].indexPos).add(left.indexPos);
					}
					// top
					if ((i - 1 >= 0) && matrix[i - 1][j] == 0 && matrixElements[i - 1][j].color.equals("white")) {
						top = matrixElements[i - 1][j];
						adj_list.get(matrixElements[i][j].indexPos).add(top.indexPos);
					}
				} else if (matrix[i][j] == 0 && matrixElements[i][j].color.equals("white")) {
					countWhite++;
					adj_list.get(matrixElements[i][j].indexPos).add(adj_list.size() - 1);
				}
			}
		}

	}

	/**
	 * This function creates the matrix of objects (Element) and initializes the
	 * source and sink
	 */
	private void initializeElements() {
		matrixElements = new Element[d1][d2];
		boolean start = true;
		boolean isWhite;
		String color = "black";
		int k = 1;
		for (int i = 0; i < d1; i++) {
			isWhite = !start;
			for (int j = 0; j < d2; j++) {
				if (!isWhite) {
					color = "black";
					isWhite = true;
				} else {
					color = "white";
					isWhite = false;
				}
				if (matrix[i][j] == 0) {
					Element element = new Element(k, matrix[i][j], color);
					matrixElements[i][j] = element;
					k++;
				}
			}
			start = !start;
		}
		source = new Element(0, 0, "red");
		sink = new Element(k, 0, "red");
	}

	/**
	 * This function takes input from command line
	 */
	private void takeInputChessboard() {

		for (int i = 0; i < d1; i++) {
			for (int j = 0; j < d2; j++) {

				matrix[i][j] = sc.nextInt();
			}
		}
	}

	/**
	 * This function takes input form command line
	 */
	private void takeInputRowCol() {
		d1 = sc.nextInt();
		d2 = sc.nextInt();
	}

	/**
	 * This is the main method
	 * 
	 * @param args
	 *            command line arguments are ignored
	 */
	public static void main(String[] args) {
		Chessboard chessboard = new Chessboard();
	}

}

/**
 * This is the class Element which stores the color (black or white), position
 * of index to keep track of the vertices, and value which is 1 or 0 depending
 * on whether that position is occupied or not
 */
class Element {
	int indexPos;
	int value;
	String color;

	public Element(int indexPos, int value, String color) {
		this.indexPos = indexPos;
		this.value = value;
		this.color = color;
	}
}

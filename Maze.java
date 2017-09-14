
/**
 * 
 * filename: Maze.java
 * 
 * version: 1.0 04/26/2017
 *
 *         revisions: Initial version
 */
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Given a d1*d2 array of 0s and 1s, , plus exactly one number 2 and exactly one
 * number 3, this program computes path from 2 to 3 considering 0s as empty
 * spaces and 1s as walls
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair
 *
 */
public class Maze {
	Scanner sc;
	int[][] matrix;
	int row;
	int col;
	int srcRow;
	int srcCol;
	Queue<Element> queue;
	boolean[][] visited;
	Element endElement = null;
	Element startElement;
	HashMap<Element, Element> pred;

	/**
	 * This is a constructor, called once the object of this class is created
	 */
	public Maze() {
		sc = new Scanner(System.in);
		takeInput();
		matrix = new int[row][col];
		visited = new boolean[row][col];
		fillMatrix();
		queue = new LinkedList<Element>();
		pred = new HashMap<Element, Element>();
		if (bfsSearch())
			System.out.println(findDist(startElement, endElement));
		else
			System.out.println(-1);

	}

	/**
	 * 
	 * This method does breadth first search in the matrix considering the
	 * element 2 as source node and element 3 as destination node. It returns
	 * true if destination is reachable and false if not reachable
	 * 
	 * @return - returns a boolean value. true, if there is a path from 2 to 3
	 */
	private boolean bfsSearch() {
		Element e;
		Element eNew;
		int r = srcRow;
		int c = srcCol;
		e = new Element(r, c, matrix[srcRow][srcCol]);

		startElement = e;
		queue.add(e);
		visited[r][c] = true;
		pred.put(e, e);

		int right;
		int top;
		int left;
		int bottom;

		while (!queue.isEmpty()) {
			Element currElement = queue.remove();
			r = currElement.r;
			c = currElement.c;
			// checks if destiantion element is reached
			if (currElement.value == 3) {
				endElement = currElement;
				return true;
			}
			
			//considers the element/neighbor to the right of the current element
			if (c + 1 < col) {
				right = matrix[r][c + 1];

				eNew = new Element(r, c + 1, right);
				if (right != 1 && !visited[r][c + 1]) {
					queue.add(eNew);
					visited[r][c + 1] = true;
					pred.put(eNew, currElement);

				}
			}
			
			//considers the element/neighbor to the top of the current element
			if (r - 1 >= 0) {
				top = matrix[r - 1][c];
				eNew = new Element(r - 1, c, top);
				if (top != 1 && !visited[r - 1][c]) {
					queue.add(eNew);
					visited[r - 1][c] = true;
					pred.put(eNew, currElement);

				}
			}
			
			//considers the element/neighbor to the left of the current element
			if (c - 1 >= 0) {
				left = matrix[r][c - 1];
				eNew = new Element(r, c - 1, left);
				if (left != 1 && !visited[r][c - 1]) {
					queue.add(eNew);
					visited[r][c - 1] = true;
					pred.put(eNew, currElement);
				}
			}

			//considers the element/neighbor to the bottom of the current element
			if (r + 1 < row) {
				bottom = matrix[r + 1][c];
				eNew = new Element(r + 1, c, bottom);
				if (bottom != 1 && !visited[r + 1][c]) {
					queue.add(eNew);
					visited[r + 1][c] = true;
					pred.put(eNew, currElement);

				}
			}

		}
		return false;
	}

	/**
	 * 
	 * @param startElement-this
	 *            is the source element which is 2
	 * @param endElement-this
	 *            is the destination element which is 3
	 * @return dist- this is the total number of hops or total distance from 2
	 *         to 3
	 */
	private int findDist(Element startElement, Element endElement) {
		int dist = 0;
		Element current;

		current = endElement;
		while (!current.equals(startElement)) {
			dist++;
			current = pred.get(current);
		}

		return dist;

	}

	/**
	 * This method takes input from the console and fills the matrix
	 */
	private void fillMatrix() {
		int value;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				value = sc.nextInt();
				if (value == 2) {
					srcRow = i;
					srcCol = j;
				}
				matrix[i][j] = value;
			}
		}

	}

	/**
	 * This method takes input form the console for the number of rows and
	 * number of columns
	 */
	private void takeInput() {
		String rowCol = sc.nextLine();
		String[] split = rowCol.split(" ");
		row = Integer.parseInt(split[0]);
		col = Integer.parseInt(split[1]);

	}

	/**
	 * 
	 * @param args
	 *            command line arguments ignored
	 */
	public static void main(String[] args) {
		new Maze();
	}

}

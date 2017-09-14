
//import statements are placed here
import java.util.Scanner;

/*
 * This program is to computes the minimum  minimum possible length of a triangulation.
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair 
 */

class Point {
	double x, y;

	/**
	 * This is a constructor given in Point
	 * 
	 * @param x
	 *            x-coordinates of a point
	 * @param y
	 *            y-coordinates of a point
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * This function will calculate distance between two point.
	 * 
	 * @param p1
	 *            This is a point with x and y coordinates.
	 * @return the distance between two point.
	 */
	protected double calcDistance(Point p1) {
		return (Math.sqrt(Math.pow((this.x - p1.x), 2)
				+ Math.pow((this.y - p1.y), 2)));
	}

}

public class Triangulation {
	private final double MAX = 10000000;

	/**
	 * This is a constructor
	 */
	public Triangulation() {
		Point p[] = acceptInput();
		calculateMin(p);
	}

	/**
	 * This function will calculate the minimum possible length of a
	 * triangulation.
	 * 
	 * @param p
	 *            Set of all points
	 */

	private void calculateMin(Point p[]) {
		double result[][] = new double[p.length][p.length];
		for (int front = 0; front < p.length; front++) {
			for (int i = 0, j = front; j < p.length; i++, j++) {
				if (j <= i + 2)
				{
					result[i][j] = 0.0;
				}
				else {
					System.out.println(i + " outside " +  j + ""); 
					result[i][j] = MAX;
					for (int k = i + 1; k < j; k++) {
						System.out.println(i + " outside " +  k + " outside " + j + " outside ");
						System.out.println("PayCost " + payCost(p, i, j, k));
						double val = result[i][k] + result[k][j]
								+ payCost(p, i, j, k);
						if (result[i][j] > val)
							result[i][j] = val;
					}
				}
			}
		}
		printData(result, p);
		System.out.println((int) (result[0][p.length - 1]));
	}

	private void printData(double result[][], Point p[]) {
		System.out.println(" ");
		for(int row = p.length-1; row>=0 ; row--){
			for(int col = p.length-1; col>=0; col--){
					System.out.print(result[row][col] + "   ");
			}
			System.out.println("     ");
		}
		
	}

	/**
	 * This function will calculate the distance based on whether it is one edge or two edge.
	 * @param p
	 * 			Set of points
	 * @param i
	 * 			the coordinates of point
	 * @param j
	 * 			the coordinates of point
	 * @param k
	 * 			the coordinates of point
	 * @return
	 * 		return the distance based on one edge or two edge.
	 */
	private double payCost(Point[] p, int i, int j, int k) {
		//it is one edge.
		if (k == i + 1)
			return p[k].calcDistance(p[j]);
		//it is one edge
		if (k == j - 1)
			return p[k].calcDistance(p[i]);
		else
			return p[k].calcDistance(p[j]) + p[k].calcDistance(p[i]);
	}
	/**
	 * This function will accept the input. 
	 * @return
	 * 		Set of all point
	 */
	private Point[] acceptInput() {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		Point p[] = new Point[size];
		for (int i = 0; i < size; i++) {
			p[i] = new Point(sc.nextDouble(), sc.nextDouble());
		}
		sc.close();
		return p;
	}

	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		new Triangulation();
	}

}

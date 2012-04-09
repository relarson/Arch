package lab4;

/**
 * Name: Ross Larson
 * Lab Section: H 
 * Date: September 22, 2009
 * Vector.java
 * CSE 131 Lab 4
 */

public class Point {

	private final double x, y;
	
	public Point(double a, double b) {
		x = a;
		y = b;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public Point plus(Vector v) {
		return new Point (x + v.getDeltaX(), y + v.getDeltaY());
	}
	
	public Vector minus(Point p) {
		double xp = p.getX(), yp = p.getY();
		return new Vector (this.x - xp, this.y - yp);
	}
	
	public double distance(Point p) {
		return minus(p).magnitude();
	}
	
	public String toString() {
		return ("(" + x + ", " + y + ")");
	}
}

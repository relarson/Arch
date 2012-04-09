package lab4;
import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Name: Ross Larson
 * Lab Section: H 
 * Date: September 22, 2009
 * Vector.java
 * CSE 131 Lab 4
 */


public class PointTest {

	@Test
	public void init() {
		Point p = new Point(5, -3);
		assertEquals(5.0,  p.getX(), .01);
		assertEquals(-3.0, p.getY(), .01);
	}
	
	@Test
	public void arith() {
		Point p = new Point(5, -3);
		Vector v = new Vector (5, -3);
		Point pPlusV = p.plus(v);
		//
		// Make sure the old point did not change
		//
		comparePoints(new Point(5, -3), p);
		//
		// Make sure the new point is right
		//
		comparePoints(new Point(10, -6), pPlusV);
		compareDoubles(10, pPlusV.getX());
		compareDoubles(-6, pPlusV.getY());
		//
		// Test toString visually
		System.out.println("TA check p:      " + p);
		System.out.println("TA check pplusV: " + pPlusV);
	}
	
	public void comparePoints(Point one, Point two) {
		compareDoubles(one.getX(), two.getX());
		compareDoubles(one.getY(), two.getY());
	}
	
	public void compareDoubles(double one, double other) {
		assertEquals(one, other, 0.01);
	}
	
	public void compareVectors(Vector one, Vector two) {
		compareDoubles(one.getDeltaX(), two.getDeltaX());
		compareDoubles(one.getDeltaY(), two.getDeltaY());
	}
	
	@Test
	public void dist() {
		Point p = new Point(5, -3);
		Point q = new Point(10, -15);
		compareDoubles(13, p.distance(q));
	}
	
	@Test
	public void minus() {
		Point p = new Point(5, -3);
		Point q = new Point(10, -6);
		Vector correct = new Vector (5, -3);
		compareVectors(correct, q.minus(p));
	}
	
}

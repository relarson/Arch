package lab4;
import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Name: Ross Larson
 * Lab Section: H 
 * Date: September 26, 2009
 * Vector.java
 * CSE 131 Lab 4
 */


public class Point3DTest {

	@Test
	public void init() {
		Point3D p = new Point3D(5, -3, 2);
		assertEquals(5.0,  p.getX(), .01);
		assertEquals(-3.0, p.getY(), .01);
		assertEquals(2.0, p.getZ(), .01);
	}
	
	@Test
	public void arith() {
		Point3D p = new Point3D(5, -3, 2);
		Vector3D v = new Vector3D (5, -3, 2);
		Point3D pPlusV = p.plus(v);
		//
		// Make sure the old point did not change
		//
		comparePoints(new Point3D(5, -3, 2), p);
		//
		// Make sure the new point is right
		//
		comparePoints(new Point3D(10, -6, 4), pPlusV);
		compareDoubles(10, pPlusV.getX());
		compareDoubles(-6, pPlusV.getY());
		compareDoubles(4, pPlusV.getZ());
		//
		// Test toString visually
		System.out.println("TA check p:      " + p);
		System.out.println("TA check pplusV: " + pPlusV);
	}
	
	public void comparePoints(Point3D one, Point3D two) {
		compareDoubles(one.getX(), two.getX());
		compareDoubles(one.getY(), two.getY());
		compareDoubles(one.getZ(), two.getZ());
	}
	
	public void compareDoubles(double one, double other) {
		assertEquals(one, other, 0.01);
	}
	
	public void compareVectors(Vector3D one, Vector3D two) {
		compareDoubles(one.getDeltaX(), two.getDeltaX());
		compareDoubles(one.getDeltaY(), two.getDeltaY());
		compareDoubles(one.getDeltaZ(), two.getDeltaZ());
	}
	
	@Test
	public void dist() {
		Point3D p = new Point3D(5, -3, 2);
		Point3D q = new Point3D(10, -15, 2);
		compareDoubles(13, p.distance(q));
	}
	
	@Test
	public void minus() {
		Point3D p = new Point3D(5, -3, 2);
		Point3D q = new Point3D(10, -6, 4);
		Vector3D correct = new Vector3D (5, -3, 2);
		compareVectors(correct, q.minus(p));
	}
	
}
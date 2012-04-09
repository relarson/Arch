package lab4;
import static org.junit.Assert.*;
import org.junit.Test;

public class Vector3DTest {
	@Test
	public void init() {
		Vector3D v = new Vector3D(5, -3, 2);
		assertEquals(5.0,  v.getDeltaX(), .01);
		assertEquals(-3.0, v.getDeltaY(), .01);
		assertEquals(2.0, v.getDeltaZ(), .01);
	}

	@Test
	public void arith() {
		Vector3D v = new Vector3D(5, -3, 2);
		Vector3D vPlusV = v.plus(v);
		//
		// Make sure the old vector did not change
		//
		compareVectors(new Vector3D(5, -3, 2), v);
		//
		// Make sure the new vector is right
		//
		compareVectors(new Vector3D(10, -6, 4), vPlusV);
		compareDoubles(10, vPlusV.getDeltaX());
		compareDoubles(-6, vPlusV.getDeltaY());
		compareDoubles(4, vPlusV.getDeltaZ());
		//
		// Test toString visually
		System.out.println("TA check v:      " + v);
		System.out.println("TA check vplusV: " + vPlusV);
	}

	/**
	 * Compare two Vectors JUnit-style, failing if they do not
	 * agree on their x and y deltas.
	 * @param one
	 * @param two
	 */
	public void compareVectors(Vector3D one, Vector3D two) {
		compareDoubles(one.getDeltaX(), two.getDeltaX());
		compareDoubles(one.getDeltaY(), two.getDeltaY());
		compareDoubles(one.getDeltaZ(), two.getDeltaZ());
	}


	/**
	 * Why did I write this method?
	 * @param one    one of two doubles to compare
	 * @param other  other of two doubles to compare
	 */
	public void compareDoubles(double one, double other) {
		assertEquals(one, other, 0.01);
	}



	@Test
	public void scale() {
		Vector3D v = new Vector3D(5, -3, 2);
		Vector3D bigger = v.scale(1.5);
		Vector3D smaller = v.scale(0.75);
		compareDoubles( 7.5,   gx(bigger));
		compareDoubles(-4.5,   gy(bigger));
		compareDoubles( 3.0,   gz(bigger));
		compareDoubles( 3.75,  gx(smaller));
		compareDoubles( 2.25,  gy(smaller.deflectY()));
		compareDoubles(-2.25,  gy(smaller.deflectX()));
	}

	/**
	 * Why did I write this method?
	 * @param v a vector
	 * @return v's x component
	 */
	public double gx(Vector3D v) {
		return v.getDeltaX();
	}

	/**
	 * Why did I write this method?
	 * @param v a vector
	 * @return v's y component
	 */
	public double gy(Vector3D v) {
		return v.getDeltaY();
	}
	
	public double gz(Vector3D v) {
		return v.getDeltaZ();
	}
	
	@Test
	public void rescale() {
		Vector3D v = new Vector3D(3, 4, 5);
		compareDoubles(Math.sqrt(50), v.magnitude());
		compareDoubles(6.0, gx(v.rescale(2*Math.sqrt(50))));
		compareDoubles(8.0, gy(v.rescale(2*Math.sqrt(50))));
		compareDoubles(10.0, gz(v.rescale(2*Math.sqrt(50))));
	}

	@Test
	public void specialCases() {
		Vector3D v = new Vector3D(0, 0, 0);
		Vector3D r = v.rescale(5);
		compareDoubles(0, v.magnitude());
		compareDoubles(5, r.magnitude());
		compareDoubles(5, gx(r));
		compareDoubles(0, gy(r));
	}

}

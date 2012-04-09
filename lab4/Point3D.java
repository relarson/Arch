package lab4;

/**
 * Name: Ross Larson
 * Lab Section: H 
 * Date: September 2, 2009
 * Vector.java
 * CSE 131 Lab 4
 */

public class Point3D {

	private final double x, y, z;
	
	public Point3D(double a, double b, double c) {
		x = a;
		y = b;
		z = c;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public Point3D plus(Vector3D v) {
		return new Point3D (x + v.getDeltaX(), y + v.getDeltaY(), z + v.getDeltaZ());
	}
	
	public Vector3D minus(Point3D p) {
		double xp = p.getX(), yp = p.getY(), zp = p.getZ();
		return new Vector3D (this.x - xp, this.y - yp, this.z - zp);
	}
	
	public double distance(Point3D p) {
		return minus(p).magnitude();
	}
	
	public String toString() {
		return ("(" + x + ", " + y +  ", " + z +")");
	}
}


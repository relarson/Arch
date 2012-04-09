package lab4;

/**
 * Name: Ross Larson
 * Lab Section: H 
 * Date: September 22, 2009
 * Vector.java
 * CSE 131 Lab 4
 */
//TA Grade:95 
public class Vector {
	private final double deltaX, deltaY;
	
	public Vector (double x, double y) {
		deltaX = x;
		deltaY = y;
	}
	
	public double getDeltaX() {
		return deltaX;
	}
	
	public double getDeltaY() {
		return deltaY;
	}
	
	public double magnitude() {
		return Math.sqrt(Math.pow(deltaX,2) + Math.pow(deltaY,2));
	}
	
	public Vector deflectX() {
		return new Vector(-1*deltaX, deltaY);
	}
	
	public Vector deflectY() {
		return new Vector(deltaX, -1*deltaY);
	}
	
	public Vector plus(Vector v) {
		double x1 = this.deltaX, y1 = this.deltaY;
		double x2 = v.getDeltaX(), y2 = v.getDeltaY();
		return new Vector(x1 + x2, y1 + y2);
	}
	
	public Vector minus(Vector v) {
		return plus(v.deflectX().deflectY());
	}
	
	public Vector scale(double factor) {
		return new Vector(deltaX*factor, deltaY*factor);
	}
	//TA Please comment about special case -5
	public Vector rescale(double magnitude) {
		double mag = magnitude(), fac = magnitude/mag;
		if (mag == 0) {
			return new Vector(magnitude, 0);
		}
		return scale (fac);
	}
	
	public double dotProduct(Vector other) {
		return (this.getDeltaX()*other.getDeltaX()) + (this.getDeltaY() * other.getDeltaY());
	}
	
	public String toString() {
		return ("<" + deltaX + ", " + deltaY + ">"); 
	}


}

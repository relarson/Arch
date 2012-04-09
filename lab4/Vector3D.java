package lab4;

/**
 * Name: Ross Larson
 * Lab Section: H 
 * Date: September 22, 2009
 * Vector.java
 * CSE 131 Lab 4
 */

public class Vector3D {
	private final double deltaX, deltaY, deltaZ;
	
	public Vector3D (double x, double y, double z) {
		deltaX = x;
		deltaY = y;
		deltaZ = z;
	}
	
	public double getDeltaX() {
		return deltaX;
	}
	
	public double getDeltaY() {
		return deltaY;
	}
	
	public double getDeltaZ() {
		return deltaZ;
	}
	
	public double magnitude() {
		return Math.sqrt(Math.pow(deltaX,2) + Math.pow(deltaY,2) + Math.pow(deltaZ,2));
	}
	
	public Vector3D deflectX() {
		return new Vector3D(-1*deltaX, deltaY, deltaZ);
	}
	
	public Vector3D deflectY() {
		return new Vector3D(deltaX, -1*deltaY, deltaZ);
	}
	
	public Vector3D deflectZ() {
		return new Vector3D(deltaX, deltaY, -1*deltaZ);
	}
	
	public Vector3D plus(Vector3D v) {
		double x1 = this.deltaX, y1 = this.deltaY, z1 = this.deltaZ;
		double x2 = v.getDeltaX(), y2 = v.getDeltaY(), z2 = v.getDeltaZ();
		return new Vector3D(x1 + x2, y1 + y2, z1 + z2);
	}
	
	public Vector3D minus(Vector3D v) {
		return plus(v.deflectX().deflectY().deflectZ());
	}
	
	public Vector3D scale(double factor) {
		return new Vector3D(deltaX*factor, deltaY*factor, deltaZ*factor);
	}
	
	public Vector3D rescale(double magnitude) {
		double mag = magnitude(), fac = magnitude/mag;
		if (mag == 0) {
			return new Vector3D(magnitude, 0, 0);
		}
		return scale (fac);
	}
	
	public String toString() {
		return ("<" + deltaX + ", " + deltaY + ", " + deltaZ + ">"); 
	}


}


package arch;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;

import lab4.Vector;

import nip.*;

/**
 * Models an arch using masses and springs.
 *
 */
public class Arch implements ActionListener {

	final public double ROUNDDURATION = 1.0; // in seconds
	final private GraphicsPanel panel;
	final public static double TOTALMASS=50;
	private Timer timer;
	private Mass[] masses;
	private SpringBetweenMasses[] springs;
	int count = 0;
	
	/**
	 * Set up the simulation by depositing the specified
	 * number of masses (MassRect) onto the panel, with two
	 * SpringBetweenMasses between each adjacent pair of masses.
	 * The two SpringBetweenMasses are needed because one end of
	 * each Spring is viewed as fixed.
	 * @param panel The panel on which to place things
	 * @param numMasses The number of masses for the Arch
	 */

	public Arch(GraphicsPanel panel, int numMasses) {
		panel.getMainImage().fillRegion(0, 0, panel.getWidth(), panel.getHeight(), Color.WHITE);
		this.panel = panel;
		timer = new Timer(20, this);
		
		masses = new Mass[numMasses];
		springs = new SpringBetweenMasses[numMasses*2-2];
		
		double gap = panel.getWidth()/(numMasses);
		double loc = gap;
		
		//make masses and add gravity
		masses[0] = new FixedMass(0, 20, TOTALMASS/numMasses, panel);
		for (int i = 1 ; i < numMasses-1 ; i ++) {
			masses[i] = new MovableMass((int) loc, 20, TOTALMASS/numMasses, panel);
			MassRect mr = (MassRect) masses[i];
			mr.affectedByForce((ForceProvider) new Gravity(masses[i]));
			loc += gap;
		}
		masses[numMasses-1] = new FixedMass((int) loc, 20, TOTALMASS/numMasses, panel);
		
		//make springs
		springs[0] = new SpringBetweenMasses((MassRect) masses[0], (MassRect) masses[1], this);
		int b = 1;
		for (int a = 1 ; a < (numMasses-1)*2 ; a++, b++) {
			if (b < numMasses-1) {
				springs[a] = new SpringBetweenMasses((MassRect) masses[b], (MassRect) masses[b-1], this);
				a++;
				springs[a] = new SpringBetweenMasses((MassRect) masses[b], (MassRect) masses[b+1], this);
			}
		}
		springs[numMasses*2-3] = new SpringBetweenMasses((MassRect) masses[numMasses-1], (MassRect) masses[numMasses-2], this);
		
		//add force from spring
		MassRect start = (MassRect) masses[0];
		start.affectedByForce((ForceProvider) springs[0]);
		int c = 1;
		for (int a = 1 ; a < (numMasses*2)-4 ; a++, c++) {
			if (c < numMasses) {
				MassRect mr = (MassRect) masses[c];
				mr.affectedByForce((ForceProvider) springs[a]);
				a++;
				mr.affectedByForce((ForceProvider) springs[a]);
			}
		}
//		MassRect end = (MassRect) masses[numMasses-1];  				// not needed
//		end.affectedByForce((ForceProvider) springs[numMasses*2-3]); 	// not needed

	}

	/**
	 * Allows a graphics component to be added to the arch panel.
	 * The SpringBetweenMasses class needs this so it can update the Spring
	 * visualization (Line) that describes the Spring.
	 * @param g
	 */
	public void addComponent(Graphic g) {
		panel.add(g);
	}

	/**
	 * Allows a graphics component to be removed from the arch panel
	 * The SpringBetweenMasses class needs this so it can update the Spring
	 * visualization (Line) that describes the Spring.
	 * @param g
	 */
	public void removeComponent(Graphic g) {
		panel.remove(g);
	}


	/**
	 * Start the swing timer
	 */
	public void run() {
		timer.start();
	}

	/**
	 * Stop the swing timer
	 */
	public void stop() {
		timer.stop();
	}

	/**
	 * Run one round of the simulation.
	 * For each mass, compute (but don't apply)
	 * the force that the mass
	 * by calling mass.sumForces().
	 * Then, for each mass, apply the force that it
	 * should experience.
	 * Then, for each Spring, have it relocate itself
	 * based on its masses' positions.
	 */
	public void round() {
		count++;
		Vector[] forces = new Vector[masses.length];
		for (int i = 1 ; i < masses.length-1 ; i++){
			MovableMass m = (MovableMass) masses[i];
			forces[i] = m.sumForces();
		}
		for (int i = 1 ; i < masses.length-1 ; i ++)  {
			MovableMass m = (MovableMass) masses[i];
			m.applyForce(forces[i], 1);
		}
		for (int s = 0 ; s < springs.length ; s++) {
			springs[s].locate();
		}
		//System.out.println(count);
		// FIXME: perform one round as described above
	}

	/**
	 * Each clock tick, perform one round of the
	 * simulation.
	 */
	public void actionPerformed(ActionEvent e) {
		round();
	}

}

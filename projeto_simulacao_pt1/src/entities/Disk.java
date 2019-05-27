package entities;


/**
 * Class representing a Disk entity on the system.
 * 
 * @author Kaio Oliveira
 *
 */
public class Disk extends Entity {
	public Disk(String name, double mean, double avg, int numInPorts) {
		super(name, mean, avg, numInPorts, new double[]{1.0});
	}
}

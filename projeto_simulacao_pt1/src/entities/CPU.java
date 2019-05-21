package entities;

import eduni.simjava.Sim_port;

/**
 * Class representing a CPU entity on the system.
 * 
 * @author Kaio Oliveira
 *
 */
public class CPU extends Entity {

	public CPU(String name, double mean, double avg, int numInPorts, double[] probPorts) {
		super(name, mean, avg, numInPorts, probPorts);
	}
}

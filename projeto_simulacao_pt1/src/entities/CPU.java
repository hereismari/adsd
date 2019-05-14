package entities;

import eduni.simjava.Sim_port;

/**
 * Class representing a CPU entity on the system.
 * 
 * @author Kaio Oliveira
 *
 */
public class CPU extends Entity {

	private Sim_port[] inPorts;
	private Sim_port[] outPorts;

	private int numInPorts;
	private int numOutPorts;

	/**
	 * Constructor of a CPU entity for this system implementation.
	 *
	 * Consists of calling the superclass {@link}Entity constructor
	 * passing the name and the min/max parameters to calculate the
	 * delay based on a uniform distribution. Also, this entity may
	 * have a variant number of In/Out ports thus it receives the
	 * number of In/Out ports as parameters on this constructor.
	 *
	 * @param name {@link}String representing an unique identifier for the CPU.
	 * @param min double representing the minimum value used on the
	 * 			uniform distribution to calculate the delay.
	 * @param max double representing the maximum value (not included)
	 * 			used on the uniform distribution to calculate the delay.
	 * @param numInPorts number of In ports on the CPU.
	 * @param numOutPorts number of Out ports on the CPU.
	 */
	public CPU(String name, double min, double max, int numInPorts, int numOutPorts) {
		super(name, min, max);
		this.numInPorts = numInPorts;
		this.numOutPorts = numOutPorts;
		this.initializePorts();
	}

	@Override
	protected void initializePorts() {
		this.inPorts = new Sim_port[this.numInPorts];
		this.outPorts = new Sim_port[this.numOutPorts];

		for (int i = 0; i < this.numInPorts; i++) {
			this.inPorts[i] = new Sim_port("");
			add_port(inPorts[i]);
		}

		for (int i = 0; i < numOutPorts; i++) {
			this.outPorts[i] = new Sim_port("");
			add_port(outPorts[i]);
		}
	}
}

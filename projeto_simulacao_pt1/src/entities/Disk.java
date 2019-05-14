package entities;

import eduni.simjava.Sim_port;

/**
 * Class representing a Disk entity on the system.
 * 
 * @author Kaio Oliveira
 *
 */
public class Disk extends Entity {

	private Sim_port inA;
	private Sim_port inB;
	private Sim_port out;

	/**
	 * Constructor of a Disk entity for this system implementation.
	 *
	 * Consists of calling the superclass {@link}Entity constructor
	 * passing the name and the min/max parameters to calculate the
	 * delay based on a uniform distribution.
	 *
	 * @param name {@link}String representing an unique identifier for the Disk.
	 * @param min double representing the minimum value used on the
	 * 			uniform distribution to calculate the delay.
	 * @param max double representing the maximum value (not included)
	 * 			used on the uniform distribution to calculate the delay.
	 */
	public Disk(String name, double min, double max) {
		super(name, min, max);
		this.initializePorts();
	}

	@Override
	protected void initializePorts() {
		this.inA = new Sim_port("InDiskA");
		this.inB = new Sim_port("InDiskB");
		this.out = new Sim_port("OutDisk");
		
		add_port(inA);
		add_port(inB);
		add_port(out);
	}
}

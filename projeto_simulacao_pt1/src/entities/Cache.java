package entities;

import eduni.simjava.Sim_port;

/**
 * Class representing a Cache memory entity on the system.
 * 
 * @author Kaio Oliveira
 *
 */
public class Cache extends Entity {

	private Sim_port in;
	private Sim_port outA;
	private Sim_port outB;

	/**
	 * Constructor of a Cache entity for this system implementation.
	 *
	 * Consists of calling the superclass {@link}Entity constructor
	 * passing the name and the min/max parameters to calculate the
	 * delay based on a uniform distribution.
	 *
	 * @param name {@link}String representing an unique identifier for the Cache.
	 * @param min double representing the minimum value used on the
	 * 			uniform distribution to calculate the delay.
	 * @param max double representing the maximum value (not included)
	 * 			used on the uniform distribution to calculate the delay.
	 */
	public Cache(String name, double min, double max) {
		super(name, min, max);
		this.initializePorts();
	}
	
	@Override
	protected void initializePorts() {
		this.in = new Sim_port("InCache");
		this.outA = new Sim_port("OutCacheA");
		this.outB = new Sim_port("OutCacheB");
		
		add_port(in);
		add_port(outA);
		add_port(outB);
	}
}

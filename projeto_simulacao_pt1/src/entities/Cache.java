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
	
	public Cache (String name, double min, double max) {
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

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

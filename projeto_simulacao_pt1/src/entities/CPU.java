package entities;

import eduni.simjava.Sim_port;

/**
 * Class representing a CPU entity on the system.
 * 
 * @author Kaio Oliveira
 *
 */
public class CPU extends Entity {
	
	private static final int MAX_NUMBER_OF_PORTS = 5;
	
	private Sim_port[] inPorts;
	private Sim_port[] outPorts;

	public CPU (String name, double min, double max) {
		super(name, min, max);
		this.initializePorts();
	}

	@Override
	protected void initializePorts() {
		this.inPorts = new Sim_port[MAX_NUMBER_OF_PORTS];
		this.outPorts = new Sim_port[MAX_NUMBER_OF_PORTS];
		
		for (int i = 0; i < MAX_NUMBER_OF_PORTS; i++) {
			this.inPorts[i] = new Sim_port("");
			this.outPorts[i] = new Sim_port("");
			
			add_port(inPorts[i]);
			add_port(outPorts[i]);
		}
	}
	
}

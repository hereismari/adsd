package entities;

import eduni.simjava.Sim_port;

/**
 * Class representing a Load Balancer entity on the system. The load
 * balancer is responsible for receiving requests from a source and
 * deciding to which Application Service will forward the request, mainly
 * based on the current load on the Application Services.
 * 
 * @author Kaio Oliveira
 *
 */
public class LoadBalancer extends Entity {
	
	private Sim_port in;
	private Sim_port out;
	
	public LoadBalancer (String name, double min, double max) {
		super(name, min, max);
		this.initializePorts();
	}

	@Override
	protected void initializePorts() {
		this.in = new Sim_port("InLB");
		this.out = new Sim_port("OutLB");
		
		add_port(in);
		add_port(out);
	}

}

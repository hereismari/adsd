package entities;

import eduni.simjava.*;

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

	/**
	 * Constructor of a Load Balancer entity for this system implementation.
	 *
	 * Consists of calling the superclass {@link}Entity constructor
	 * passing the name and the min/max parameters to calculate the
	 * delay based on a uniform distribution.
	 *
	 * @param name {@link}String representing an unique identifier for the Load Balancer.
	 * @param min double representing the minimum value used on the
	 */
	public LoadBalancer(String name, double mean, double avg) {
		super(name, mean, avg);
	}

	@Override
	protected void initializePorts() {
		this.in = new Sim_port("In");
		this.out = new Sim_port("Out");
		
		add_port(in);
		add_port(out);
	}

	public void body() {
		while(Sim_system.running()){
			Sim_event e = new Sim_event();
			sim_get_next(e);
			sim_process(sample());
			sim_completed(e);
		}
	}
}

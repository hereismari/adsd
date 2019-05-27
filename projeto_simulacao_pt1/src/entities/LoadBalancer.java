package entities;

import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_uniform_obj;

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
		super(name, mean, avg, 1, new double[]{0.25, 0.25, 0.5});
	}
	
	@Override
	public void body() {
		super.body();
		
		if (Sim_system.running() && Sim_system.sim_clock() % 100 == 0) {
			redistribute();
		}
	}
	
	private void redistribute() {
		double acumulatedProbability = 0.0;
		Sim_uniform_obj twentyFivePercentRandom = new Sim_uniform_obj("UniformProbability", 0.0, 0.25);
		
		for (int i = 0; i < getProbPorts().length -1; i++) {
			double p = twentyFivePercentRandom.sample();
			getProbPorts()[i] = p;
			acumulatedProbability += p;
		}
		
		getProbPorts()[2] = 1.0 - acumulatedProbability;
		defineProbRanges();
	}
}

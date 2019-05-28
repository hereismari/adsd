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

	private int previousSimClock;

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
		this.previousSimClock = -1;
	}

	@Override
	public void body() {
		while(Sim_system.running()){
			bodyInsideLoop();
			int currSimClock = (int)Sim_system.sim_clock();
			if (currSimClock % 100 == 0 && previousSimClock % 100 == 0) {
				redistribute();
			}
			previousSimClock = currSimClock;
		}
	}
	
	private void redistribute() {
		double acumulatedProbability = 0.0;
		Sim_uniform_obj twentyFivePercentRandom = new Sim_uniform_obj("UniformProbability", 0.0, 0.25);
		double[] newProbPorts = new double[getProbPorts().length];
		
		for (int i = 0; i < newProbPorts.length -1; i++) {
			double p = twentyFivePercentRandom.sample();
			newProbPorts[i] = p;
			acumulatedProbability += p;
		}
		
		newProbPorts[2] = 1.0 - acumulatedProbability;
		setProbPorts(newProbPorts);
		defineProbRanges();
	}
}

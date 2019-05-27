package entities;

import eduni.simjava.*;
import eduni.simjava.distributions.Sim_normal_obj;
import eduni.simjava.distributions.Sim_random_obj;

/**
 * Abstract class representing the base entity on the system.
 * 
 * @author Kaio Oliveira
 *
 */
public abstract class Entity extends Sim_entity {

	private Sim_stat stat;
	private Sim_normal_obj delay;

	private double[] probPorts;
	private int numInPorts;

	protected Sim_port[] inPorts;
	protected Sim_port[] outPorts;

	private String entityName;
	
	private Tuple<Double, Double>[] probRanges;

	protected final Sim_random_obj randomProb = new Sim_random_obj("RandomProbability");
	
	/**
	 * Constructor of the base entity for this system implementation.
	 *
	 * Consists of calling the {@link}Sim_entity (superclass) constructor
	 * passing the name received to identify the entity; also creates
	 * Statistics gatherers ({@link}Sim_stat) and calculates the delay
	 * as an uniform distribution according to parameters.
	 *
	 */
	public Entity(String name, double mean, double avg, int numInPorts, double[] probPorts) {
		super(name);

        stat = new Sim_stat();
        stat.add_measure(Sim_stat.UTILISATION);
        stat.add_measure(Sim_stat.SERVICE_TIME);
        stat.add_measure(Sim_stat.WAITING_TIME);
        stat.add_measure(Sim_stat.QUEUE_LENGTH);
        set_stat(stat);

        delay = new Sim_normal_obj(name.concat("_Delay"), mean, avg);
		add_generator(delay);

		this.entityName = name;
		this.numInPorts = numInPorts;
		this.probPorts = probPorts;
		
		initializePorts();
		defineProbRanges();
	}

	private Sim_port[] createPorts(String name, int numberOfPorts) {
		Sim_port[] ports = new Sim_port[numberOfPorts];
		for (int i = 0; i < numberOfPorts; i++) {
			ports[i] = new Sim_port(name.concat(Integer.toString(i + 1)));
			System.out.println(name.concat(Integer.toString(i + 1)));
			add_port(ports[i]);
		}
		return ports;
	}

	public double sample() {
		return delay.sample();
	}
	
	public Tuple<Double, Double>[] getProbRanges() {
		return probRanges;
	}

	public double[] getProbPorts() {
		return probPorts;
	}
	
	/**
	 * Initialize entity ports with proper names/identifiers and
	 * registry the ports with the {@link}add_port() SimJava method.
	 */
	private void initializePorts() {
		this.inPorts = createPorts(entityName.concat("In"), numInPorts);
		this.outPorts = createPorts(entityName.concat("Out"), probPorts.length);
	}
	
	@SuppressWarnings("unchecked")
	protected void defineProbRanges() {
		this.probRanges = (Tuple<Double, Double>[]) new Tuple[probPorts.length];
		
		double acumulatedProb = 0.0;
		for (int i = 0; i < probPorts.length; i++) {
			Tuple<Double, Double> probRange = new Tuple<Double, Double>(acumulatedProb, acumulatedProb + probPorts[i]);
			acumulatedProb += probPorts[i];
			probRanges[i] = probRange;
		}
	}
	
	@Override
	public void body() {
		while(Sim_system.running()){
			Sim_event e = new Sim_event();
			sim_get_next(e);
			sim_process(sample());
			sim_completed(e);
			
			double p = randomProb.sample();
			
			for (int i = 0; i < getProbRanges().length; i++) {
				if (p > getProbRanges()[i].x && p <= getProbRanges()[i].y) {
					sim_schedule(outPorts[i], 0.0, 0);
				}
			}
		}
	}
	
	class Tuple<X, Y> {
		public final X x;
		public final Y y;

		public Tuple(X x, Y y) {
			this.x = x;
			this.y = y;
		}
	}
}

package entities;

import eduni.simjava.*;
import eduni.simjava.distributions.Sim_normal_obj;

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

        delay = new Sim_normal_obj(name.concat("Delay"), mean, avg);
		add_generator(delay);

		this.entityName = name;
		this.numInPorts = numInPorts;
		this.probPorts = probPorts;
		initializePorts();
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

	/**
	 * Initialize entity ports with proper names/identifiers and
	 * registry the ports with the {@link}add_port() SimJava method.
	 */
	protected void initializePorts() {
		this.inPorts = createPorts(entityName.concat("In"), numInPorts);
		this.outPorts = createPorts(entityName.concat("Out"), probPorts.length);
	}

	public double sample() {
		return delay.sample();
	}

}

package entities;

import eduni.simjava.*;
import eduni.simjava.distributions.Sim_uniform_obj;

/**
 * Abstract class representing the base entity on the system.
 * 
 * @author Kaio Oliveira
 *
 */
public abstract class Entity extends Sim_entity {

	private Sim_stat stat;
	private Sim_uniform_obj delay;
	
	/**
	 * Constructor of the base entity for this system implementation.
	 * 
	 * Consists of calling the {@link}Sim_entity (superclass) constructor
	 * passing the name received to identify the entity; also creates
	 * Statistics gatherers ({@link}Sim_stat) and calculates the delay
	 * as an uniform distribution according to parameters.
	 * 
	 * @param name String representing the unique identifier of the entity.
	 * @param min double representing the minimum value used on the
	 * 			uniform distribution to calculate the delay.
	 * @param max double representing the maximum value (not included)
	 * 			used on the uniform distribution to calculate the delay.
	 */
	public Entity(String name, double min, double max) {
		super(name);
		
        stat = new Sim_stat();
        stat.add_measure(Sim_stat.UTILISATION);
        stat.add_measure(Sim_stat.SERVICE_TIME);
        stat.add_measure(Sim_stat.WAITING_TIME);
        stat.add_measure(Sim_stat.QUEUE_LENGTH);
        set_stat(stat);
        
        delay = new Sim_uniform_obj("Delay", min, max);
        add_generator(delay);
	}
	
	/**
	 * Initialize entity ports with proper names/identifiers and
	 * registry the ports with the {@link}add_port() SimJava method.
	 */
	protected abstract void initializePorts();

}

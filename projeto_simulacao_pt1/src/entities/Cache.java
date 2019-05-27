package entities;

/**
 * Class representing a Cache memory entity on the system.
 * 
 * @author Kaio Oliveira
 *
 */
public class Cache extends Entity {

	public Cache(String name, double mean, double avg) {
		super(name, mean, avg, 1, new double[]{0.7, 0.3});
	}
}

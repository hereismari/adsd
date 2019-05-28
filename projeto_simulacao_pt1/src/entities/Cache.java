package entities;

/**
 * Class representing a Cache memory entity on the system.
 * 
 * @author Kaio Oliveira
 *
 */
public class Cache extends Entity {

	public Cache(String name, double mean, double avg) {
		// Hit probability: 0.2
		super(name, mean, avg, 1, new double[]{0.2, 0.8});
	}
}

package distributions;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class representing a continuos Uniform Distribution.
 * 
 * @author Marianne Monteiro
 *
 */
public class ContinuosUD {

    private double origin;
    private double bound;

	public DiscreteUD(double min, double max) {
        this.origin = min;
        // nextDouble is normally exclusive of the top value,
        // so add 1 to make it inclusive
        this.bound = max + 1;
    }
    
    public sample() {
        return ThreadLocalRandom.current().nextDouble(this.origin, this.bound);
    }
}

package distributions;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class representing a discrete Uniform Distribution.
 * 
 * @author Marianne Monteiro
 *
 */
public class DiscreteUD {

    private int origin;
    private int bound;

	public DiscreteUD(int min, int max) {
        this.origin = min;
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        this.bound = max + 1;
    }
    
    public sample() {
        return ThreadLocalRandom.current().nextInt(this.origin, this.bound);
    }
}

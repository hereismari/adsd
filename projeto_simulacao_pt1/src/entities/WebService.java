package entities;

public class WebService extends Entity{

	public WebService(String name, double mean, double avg) {
		super(name, mean, avg, 1, new double[] { 1.0 });
	}

	@Override
	public void body() {
		
	}
}

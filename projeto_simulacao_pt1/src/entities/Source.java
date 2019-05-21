package entities;

import eduni.simjava.*;

public class Source extends Entity {      // The class for the source
  private Sim_port out;

  public Source(String name, double mean, double avg) {
    super(name, mean, avg);
  }

	@Override
	protected void initializePorts() {
		this.out = new Sim_port("Out");
		add_port(out);
	}

  public void body() {
    for (int i = 0; i < 100; i++) {
      sim_schedule(out, 0.0, 0);       // Send the load balancer a job
      sim_pause(sample());
    }
  }
}
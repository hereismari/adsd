package sourceAndSink;

import eduni.simjava.*;

class Source extends Sim_entity {      // The class for the source
  private Sim_port loadBalancerOut;
  private double delay;

  Source(String name, double delay) {
    super(name);
    this.delay = delay;
    loadBalancerOut = new Sim_port("LoadBalanceOut");
    add_port(loadBalancerOut);
  }

  public void body() {
    for (int i=0; i < 100; i++) {
      sim_schedule(loadBalancerOut, 0.0, 0);       // Send the load balancer a job
      sim_pause(delay);                // TODO: change delay to 1/lambda
    }
  }
}
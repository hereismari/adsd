import eduni.simjava.Sim_system;
import entities.*;

public class Main {
	
	public static void main(String[] args) {
		Sim_system.initialise();

		// Create entities

		// lambda = 5 rps
		// interval around 0.2
		Source source = new Source("Source", 0.2, 0.00001);

		// TS around 0.2
		LoadBalancer loadBalancer = new LoadBalancer("LoadBalancer", 0.05, 0.00002);

		// Connect entities
		Sim_system.link_ports("Source", "SourceOut1", "LoadBalancer", "LoadBalancerIn1");

		Sim_system.run();
	}

}

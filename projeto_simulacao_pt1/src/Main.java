import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_random_obj;
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

		// Connect source to LoadBalancer
		Sim_system.link_ports("Source", "SourceOut1", "LoadBalancer", "LoadBalancerIn1");
		
		// Application Service 1 init
		CPU cpuAS1 = new CPU("CPU_Application_Service_1", 0.5, 0.00002, 2, new double[]{0.9, 0.1});
		Disk diskAS1 = new Disk("Disk_Application_Service_1", 0.85, 0.0002, 1);
		
		// Application Service 2 init
		CPU cpuAS2 = new CPU("CPU_Application_Service_2", 0.5, 0.00002, 2, new double[]{0.9, 0.1});
		Disk diskAS2 = new Disk("Disk_Application_Service_2", 0.85, 0.0002, 1);
		
		// Application Service 3 init - GPU based
		CPU gpuAS3 = new CPU("GPU_Application_Service_3", 0.05, 0.00002, 2, new double[]{0.9, 0.1});
		Disk diskAS3 = new Disk("Disk_Application_Service_3", 0.85, 0.0002, 1);
		
		// Database Server init, having a cache
		CPU cpuDBS = new CPU("CPU_Database_Server", 0.5, 0.00002, 5, new double[]{0.3, 0.2, 0.5});
		Cache cacheDB = new Cache("Cache_Database_Server", 0.085, 0.0002);
		Disk diskDBS = new Disk("Disk_Database_Server", 0.85, 0.0002, 2);
		
		// Web service init
		WebService ws = new WebService("Web_Service", 0.2, 0.00001);
		
		/**
		 *  Connect ports
		 */
		
		// LoadBalancer to ApplicationServices CPU
		Sim_system.link_ports("LoadBalancer", "LoadBalancerOut1", "CPU_Application_Service_1", "CPU_Application_Service_1In1");
		Sim_system.link_ports("LoadBalancer", "LoadBalancerOut2", "CPU_Application_Service_2", "CPU_Application_Service_2In1");
		Sim_system.link_ports("LoadBalancer", "LoadBalancerOut3", "GPU_Application_Service_3", "GPU_Application_Service_3In1");
		
		// ApplicationService 1: CPU to Disk and Disk to CPU
		Sim_system.link_ports("CPU_Application_Service_1", "CPU_Application_Service_1Out2", "Disk_Application_Service_1", "Disk_Application_Service_1In1");
		Sim_system.link_ports("Disk_Application_Service_1", "Disk_Application_Service_1Out1", "CPU_Application_Service_1", "CPU_Application_Service_1In2");

		// ApplicationService 2: CPU to Disk and Disk to CPU
		Sim_system.link_ports("CPU_Application_Service_2", "CPU_Application_Service_2Out2", "Disk_Application_Service_2", "Disk_Application_Service_2In1");
		Sim_system.link_ports("Disk_Application_Service_2", "Disk_Application_Service_2Out1", "CPU_Application_Service_2", "CPU_Application_Service_2In2");
		
		// ApplicationService 3: CPU to Disk and Disk to CPU
		Sim_system.link_ports("GPU_Application_Service_3", "GPU_Application_Service_3Out2", "Disk_Application_Service_3", "Disk_Application_Service_3In1");
		Sim_system.link_ports("Disk_Application_Service_3", "Disk_Application_Service_3Out1", "GPU_Application_Service_3", "GPU_Application_Service_3In2");
		
		// ApplicationServices CPU to DatabaseServer CPU
		Sim_system.link_ports("CPU_Application_Service_1", "CPU_Application_Service_1Out1", "CPU_Database_Server", "CPU_Database_ServerIn1");
		Sim_system.link_ports("CPU_Application_Service_2", "CPU_Application_Service_2Out1", "CPU_Database_Server", "CPU_Database_ServerIn2");
		Sim_system.link_ports("GPU_Application_Service_3", "GPU_Application_Service_3Out1", "CPU_Database_Server", "CPU_Database_ServerIn3");

		// DatabaseServer: CPU to Cache and Cache to CPU
		Sim_system.link_ports("CPU_Database_Server", "CPU_Database_ServerOut1", "Cache_Database_Server", "Cache_Database_ServerIn1");
		Sim_system.link_ports("Cache_Database_Server", "Cache_Database_ServerOut1", "CPU_Database_Server", "CPU_Database_ServerIn5");		
		// DatabaseServer: Cache to Disk
		Sim_system.link_ports("Cache_Database_Server", "Cache_Database_ServerOut2", "Disk_Database_Server", "Disk_Database_ServerIn1");
		// DatabaseServer: CPU to Disk
		Sim_system.link_ports("CPU_Database_Server", "CPU_Database_ServerOut2", "Disk_Database_Server", "Disk_Database_ServerIn2");
		// DatabaseServer: Disk to CPU
		Sim_system.link_ports("Disk_Database_Server", "Disk_Database_ServerOut1", "CPU_Database_Server", "CPU_Database_ServerIn4");
		// DatabaseServer: CPU to WebService
		Sim_system.link_ports("CPU_Database_Server", "CPU_Database_ServerOut3", "Web_Service", "Web_ServiceIn1");
		
		/**
		 *  End of port connecting
		 */
		
		Sim_system.run();
	}

}

package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.dao.IHubMessageDao;
import gov.nih.nci.integration.dao.ServiceInvocationMessageDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceInvokerConfig {
	
	@Autowired
	IHubMessageDao iHubMessageDao;
	
	@Autowired
	ServiceInvocationMessageDao serviceInvocationMessageDao;
	
	@Bean
	public ServiceBroadcaster serviceBroadcaster() {
		return new DefaultServiceBroadcaster(serviceInvocationMessageDao); 
	}
}

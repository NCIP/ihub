package gov.nih.nci.integration.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author chandrasekaravr
 *
 */
@Configuration
public interface DaoConfig {

	@Bean
	IHubMessageDao iHubMessageDao();

	@Bean
	ServiceInvocationMessageDao serviceInvocationMessageDao();

}
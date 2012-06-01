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

	/**
	 * iHubMessageDao
	 * @return IHubMessageDao
	 */
	@Bean
	IHubMessageDao iHubMessageDao();

	/**
	 * serviceInvocationMessageDao
	 * @return ServiceInvocationMessageDao
	 */
	@Bean
	ServiceInvocationMessageDao serviceInvocationMessageDao();

}
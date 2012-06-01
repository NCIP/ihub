package gov.nih.nci.integration.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Default DAO config package that configures it to use JPA.
 * 
 * @author chandrasekaravr
 */

@Configuration
public class DefaultDaoConfig implements DaoConfig {

	@PersistenceContext(unitName = "ihub-messages")
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.integration.dao.DaoConfig#iHubMessageDao()
	 */
	@Bean
	@Scope("prototype")
	@Override
	public IHubMessageDao iHubMessageDao() {
		return new DefaultIHubMessageDao(em);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.integration.dao.DaoConfig#serviceInvocationMessageDao()
	 */
	@Bean
	@Scope("prototype")
	@Override
	public ServiceInvocationMessageDao serviceInvocationMessageDao() {
		return new DefaultServiceInvocationMessageDao(em);
	}

}

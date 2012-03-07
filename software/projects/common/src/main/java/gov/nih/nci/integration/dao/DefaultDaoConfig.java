package gov.nih.nci.integration.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Default DAO config package that configures it to use JPA.
 * 
 * @author chandrasekaravr
 */

@Configuration
public class DefaultDaoConfig implements DaoConfig {

    @PersistenceContext
    private EntityManager em;
    
    /* (non-Javadoc)
	 * @see gov.nih.nci.integration.dao.DaoConfig#iHubMessageDao()
	 */
    @Bean
    @Override
    public IHubMessageDao iHubMessageDao() {
        return new IHubMessageDao(em);
    }

    /* (non-Javadoc)
	 * @see gov.nih.nci.integration.dao.DaoConfig#serviceInvocationMessageDao()
	 */
    @Bean
    @Override
    public ServiceInvocationMessageDao serviceInvocationMessageDao() {
        return new ServiceInvocationMessageDao(em);
    }

}

package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.dao.IHubMessageDao;
import gov.nih.nci.integration.dao.ServiceInvocationMessageDao;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * ServiceInvokerConfig Class
 * 
 * @author Vinodh
 * 
 */
@Configuration
public class ServiceInvokerConfig {

    @Autowired
    private IHubMessageDao iHubMessageDao;

    @Autowired
    private ServiceInvocationMessageDao serviceInvocationMessageDao;

    private int corePoolSize = 10;

    @Bean
    public ServiceBroadcaster serviceBroadcaster() {
        return new DefaultServiceBroadcaster(serviceInvocationMessageDao);
    }

    @Bean
    public Executor executor() {
        return Executors.newScheduledThreadPool(10);
    }

    /**
     * This must not be used as singleton, one must be created for each message
     * 
     * @return ServiceInvocatorAndResultAggregator
     */
    @Bean
    @Scope("prototype")
    public ServiceInvocatorAndResultAggregator serviceInvocatorAndResultAggregator() {
        return new TransactionalServiceInvocatorAndResultAggregator(serviceBroadcaster(), serviceInvocationMessageDao,
                executor());
    }
}

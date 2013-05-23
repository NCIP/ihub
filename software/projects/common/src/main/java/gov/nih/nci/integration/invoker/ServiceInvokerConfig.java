/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.invoker;

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
    private ServiceInvocationMessageDao serviceInvocationMessageDao;

    /**
     * The method is used to get the instance of DefaultServiceBroadcaster
     * @return instance of DefaultServiceBroadcaster
     */
    @Bean
    public ServiceBroadcaster serviceBroadcaster() {
        return new DefaultServiceBroadcaster(serviceInvocationMessageDao);
    }

    /**
     * Creates a thread pool that can schedule commands to run after a given delay
     * @return Executor that executes submitted Runnable tasks
     */
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

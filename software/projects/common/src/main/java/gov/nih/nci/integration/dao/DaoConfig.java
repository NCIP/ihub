/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
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
     * 
     * @return IHubMessageDao
     */
    @Bean
    IHubMessageDao iHubMessageDao();

    /**
     * serviceInvocationMessageDao
     * 
     * @return ServiceInvocationMessageDao
     */
    @Bean
    ServiceInvocationMessageDao serviceInvocationMessageDao();

}

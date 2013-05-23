/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.caaers.invoker;

import gov.nih.nci.integration.util.CommonsPropertyPlaceholderConfigurer;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaAERSPropsConfig {
	
	/**
     * Loads properties from classpath
     *
     * @return the property place holder configures
     */
    @Bean
    public PropertyPlaceholderConfigurer commonPropertyPlaceholderConfigurer() {
        final PropertyPlaceholderConfigurer configurer =
                new CommonsPropertyPlaceholderConfigurer("ihub", "caaers-client.properties");
        configurer.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }
}

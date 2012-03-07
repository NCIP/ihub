package gov.nih.nci.integration.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;

public class CommonIntegrationConfig {

	/**
     * Loads properties from "classpath*:/commonIntegration.properties" location
     *
     * @return the property place holder configures
     */
    @Bean
    public PropertyPlaceholderConfigurer commonsPropertyPlaceholderConfigurer() {
        final PropertyPlaceholderConfigurer configurer =
                new CommonsPropertyPlaceholderConfigurer("integration", "commonIntegration.properties");
        configurer.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }
}

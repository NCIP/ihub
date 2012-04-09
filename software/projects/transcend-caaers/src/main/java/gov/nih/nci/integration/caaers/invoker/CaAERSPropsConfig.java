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
                new CommonsPropertyPlaceholderConfigurer("ihub", "transcend-caaers.properties");
        configurer.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }
}

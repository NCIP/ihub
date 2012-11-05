package gov.nih.nci.integration.dao;

import gov.nih.nci.integration.util.CommonsPropertyPlaceholderConfigurer;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * This class is used to load the properties from transcend-ihub.properties
 * @author Vinodh
 *
 */
@Import( { JpaConfig.class })
@Configuration
public class CommonPropsConfig {

    /**
     * Loads properties from classpath
     * 
     * @return the property place holder configures
     */
    @Bean
    public PropertyPlaceholderConfigurer commonPropertyPlaceholderConfigurer() {
        final PropertyPlaceholderConfigurer configurer = new CommonsPropertyPlaceholderConfigurer("ihub",
                "transcend-ihub.properties");
        configurer.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }
}

package gov.nih.nci.integration.util;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * PropertyPlaceholderConfigurer that resolves properties from any of following locations in the same order
 * <p/>
 * 
 * <pre>
 * #a)${user.home}/.integration/${projectName}/${propertyFileName}
 * #b)${app.home}/.integration/${propertyFileName} (where ${app.home) is a system property
 * #c)classpath:${propertyFileName}
 * </pre>
 * 
 * @author Chandrasekaravr
 */
public final class CommonsPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private final Properties props;

    /**
     * 
     * @param projectName - project name
     * @param propertyFileName - property file name
     */
    public CommonsPropertyPlaceholderConfigurer(String projectName, String propertyFileName) {
        super();
        props = CommonsPropertyLoaderUtil.loadProperties(projectName, propertyFileName);
        this.setProperties(props);
    }

    /**
     * Get property method to get the property value programmatically
     * 
     * @param key String representing the key
     * @return String value of the property
     */
    public String getProperty(String key) {
        if (props == null) {
            return null;
        }
        return props.getProperty(key);
    }

}

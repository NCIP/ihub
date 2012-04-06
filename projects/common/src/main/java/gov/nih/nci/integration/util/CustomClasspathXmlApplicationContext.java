package gov.nih.nci.integration.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.MalformedURLException;

import org.springframework.beans.BeansException;

/**
 * Spring application context that uses custom url classloader
 * 
 * @author <a href="mailto:vinodh.rc@semanticbits.com">Vinodh Chandrasekaran</a>
 * 
 */
public class CustomClasspathXmlApplicationContext extends ClassPathXmlApplicationContext {

    private static final String MC_IHUB_LIB = "./ihub-lib/";

    /**
     * @param configLocations Array of spring xml file locations
     * @throws BeansException - Exception thrown, if any
     * @throws MalformedURLException - Exception thrown, if any
     */
    public CustomClasspathXmlApplicationContext(String... configLocations) throws BeansException, MalformedURLException {
        this(MC_IHUB_LIB, configLocations);
    }

    /**
     * @param configLocation spring xml file location
     * @throws BeansException - Exception thrown, if any
     * @throws MalformedURLException - Exception thrown, if any
     */
    public CustomClasspathXmlApplicationContext(String configLocation) throws BeansException, MalformedURLException {
        this(MC_IHUB_LIB, configLocation);
    }

    /**
     * @param customLocation cutom lib location
     * @param configLocations Array of spring xml file locations
     * @throws BeansException - Exception thrown, if any
     * @throws MalformedURLException - Exception thrown, if any
     */
    public CustomClasspathXmlApplicationContext(String customLocation, String... configLocations) throws BeansException,
            MalformedURLException {
        super();
        init(customLocation);
        setConfigLocations(configLocations);
        refresh();
    }

    /**
     * @param customLocation custom lib location
     * @param configLocation spring xml file location
     * @throws BeansException - Exception thrown, if any
     * @throws MalformedURLException - Exception thrown, if any
     */
    public CustomClasspathXmlApplicationContext(String customLocation, String configLocation) throws BeansException,
            MalformedURLException {
        super();
        init(customLocation);
        setConfigLocation(configLocation);
        refresh();
    }

    private void init(String customLocation) throws MalformedURLException {
        final CustomUrlClassLoader jcl = new CustomUrlClassLoader(customLocation);
        setClassLoader(jcl);
    }
}

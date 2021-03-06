/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.util;

import java.net.MalformedURLException;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
     * @param customLocation cutom lib location
     * @param configLocations Array of spring xml file locations
     * @throws BeansException - Exception thrown, if any
     * @throws MalformedURLException - Exception thrown, if any
     */
    public CustomClasspathXmlApplicationContext(String customLocation, String... configLocations)
            throws BeansException, MalformedURLException {
        this(new String[] { customLocation }, configLocations);
    }

    /**
     * @param customLocations cutom lib locations
     * @param configLocations Array of spring xml file locations
     * @throws BeansException - Exception thrown, if any
     * @throws MalformedURLException - Exception thrown, if any
     */
    public CustomClasspathXmlApplicationContext(String[] customLocations, String... configLocations)
            throws BeansException, MalformedURLException {
        super();
        init(customLocations);
        setConfigLocations(configLocations);
        refresh();
    }

    private void init(String[] customLocation) throws MalformedURLException {
        final CustomUrlClassLoader jcl = new CustomUrlClassLoader(customLocation);
        setClassLoader(jcl);
        Thread.currentThread().setContextClassLoader(jcl);
    }
}

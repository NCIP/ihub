/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * This utility class resolve properties from any of following locations in the same order
 * <pre>
 * #a)${user.home}/.integration/${project.name}/${propertyFile.name}
 * #b)${app.home}/.integration/${project.name}/${propertyFile.name} (where ${app.home) is a system property
 * #c)classpath:${propertyFile.name}
 * </pre>
 * property files will be used in order. So if property is available at
 * ${user.home}/.integration/${project.name}/${propertyFile.name}
 * project will simply ignore that property from other property files.
 *
 * @author Chandrasekaravr
 */
public final class CommonsPropertyLoaderUtil {

    private static final Logger LOG = LoggerFactory.getLogger(CommonsPropertyLoaderUtil.class);

    private static final String USER_HOME = "user.home";
    private static final String APP_HOME = "app.home";

    /**
     * Utility classes cannot have public constructor
     */
    private CommonsPropertyLoaderUtil() {
        // private constructor
    }

    /**
     * Load properties  from  following locations in mentioned order
     * <pre>
     * #a)${user.home}/.integration/${ant.project.name}/${ant.project.name}.properties  (where ${user.home) is a system property)
     * #b)${app.home}/.integration/${ant.project.name} (where ${catalina.home) is a system property)
     * #c)classpath:${ant.project.name}.properties
     * </pre>
     *
     * @param projectName      - project name
     * @param propertyFileName - property file name
     * @return properties if it can resolve any of the property file, exceptions if no property file is available
     */
    public static Properties loadProperties(final String projectName, final String propertyFileName) {
        if ( StringUtils.isEmpty(projectName) || StringUtils.isEmpty(propertyFileName) ) {
        	throw new IllegalArgumentException("Project name and property file name must not be null!");
        }

        final String userHomePathToScan = String.format("user.home/.integration/%s/%s", projectName, propertyFileName);
        final String appHomePathToScan = String.format("app.home/.integration/%s/%s",
                projectName, propertyFileName);

        final Properties properties = new Properties();

        //1. first load property from classpath
        fillProperties(new ClassPathResource(propertyFileName), properties);

        //2. now merge properties from tomcat
        fillPropertiesFromFileSystem(properties, APP_HOME, appHomePathToScan);

        //3. finally merge properties from user.home
        fillPropertiesFromFileSystem(properties, USER_HOME, userHomePathToScan);

        for (Map.Entry<Object,Object> entry : properties.entrySet()) {
            LOG.info(String.format("loaded property - %s:%s", entry.getKey(), entry.getValue()));
        }
        return properties;

    }

    /**
     * fill properties from file system
     *
     * @param properties          - properties to merge
     * @param environmentVariable - environment variable (for ex : user.home, app.home etc)
     * @param pathToScan          - the detailed path to scan
     */
    private static void fillPropertiesFromFileSystem(Properties properties, final String environmentVariable,
                                                     String pathToScan) {
        if (StringUtils.isBlank(System.getProperty(environmentVariable))) {
            LOG.info(String.format("%s is not set. So skipping property lookup from %s",
                    environmentVariable, environmentVariable));
        } else {
            final String detailedEnvironmentPath =
                    StringUtils.replace(pathToScan, environmentVariable, System.getProperty(environmentVariable));
            fillProperties(new FileSystemResource(detailedEnvironmentPath), properties);
        }
    }


    /**
     * fill properties from given resource
     *
     * @param resource   - resource
     * @param properties - properties to fill
     */
    private static void fillProperties(Resource resource, Properties properties) {
        try {
            if (resource.exists()) {
                LOG.info("merging properties form : " + resource.getDescription());
                PropertiesLoaderUtils.fillProperties(properties, resource);
            } else {
                LOG.info(String.format("can not merge property from %s as resource does not exists.",
                        resource.getDescription()));
            }
        } catch (IOException e) {
            final String message = "error while loading properties from resource" + resource;
            LOG.error(message, e);
            throw new RuntimeException(message, e);
        }
    }

}



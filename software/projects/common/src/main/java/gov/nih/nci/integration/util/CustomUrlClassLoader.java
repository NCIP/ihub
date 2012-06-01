package gov.nih.nci.integration.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom classloader that loads files from custom lib location
 * 
 * @author <a href="mailto:vinodh.rc@semanticbits.com">Vinodh Chandrasekaran</a>
 * 
 */

public class CustomUrlClassLoader extends URLClassLoader {

	/**
	 * constructor that takes the custom lib locations
	 * 
	 * @param customLocations
	 *            String varargs representing the location of the jars
	 * @throws MalformedURLException
	 *             - exception thrown, if any
	 */
	public CustomUrlClassLoader(String... customLocations)
			throws MalformedURLException {
		super(getCustomUrls(customLocations), CustomUrlClassLoader.class
				.getClassLoader());
	}

	/**
	 * constructor that takes the custom lib locations and parent classloader
	 * 
	 * @param customLocation
	 *            String varargs representing the location of the jars
	 * @param parent
	 *            parent classloader
	 * @throws MalformedURLException
	 *             - exception thrown, if any
	 */
	public CustomUrlClassLoader(ClassLoader parent, String... customLocations)
			throws MalformedURLException {
		super(getCustomUrls(customLocations), parent);
	}

	private static URL[] getCustomUrls(String... customLocations)
			throws MalformedURLException {
		final List<URL> urlsList = new ArrayList<URL>();
		for (String loc : customLocations) {
			addFilesToList(loc, urlsList);
		}

		return (URL[]) urlsList.toArray(new URL[urlsList.size()]);
	}

	private static void addFilesToList(String customLocation, List urlsList)
			throws MalformedURLException {
		final File loc = new File(customLocation);
		if (!loc.exists()) {
			throw new IllegalArgumentException(
					"The specified custom location does not exist, "
							+ customLocation);
		}
		urlsList.add(loc.toURI().toURL());
		final File[] jarFiles = loc.listFiles();
		if (jarFiles == null || jarFiles.length == 0) {
			throw new IllegalArgumentException(
					"The specified custom location does not contain any files, "
							+ customLocation);
		}

		for (File file : jarFiles) {
			urlsList.add(file.toURI().toURL());
		}
	}
}

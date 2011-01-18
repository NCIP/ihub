package gov.nih.nci.ihub.writer.ncies.infrastructure.validation;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.cagrid.gme.client.GlobalModelExchangeClient;
import org.cagrid.gme.domain.XMLSchemaNamespace;
import org.xml.sax.SAXException;

/**
 * Gets the schemas from the Globel Model Exchange (GME).
 * 
 * @author marwahah
 * 
 */
public class GMESchemaFactory implements CaxchangeSchemaFactory {
	// The url to the GME from which schemas are retrieved
	private String GMEGridServiceLocation;
	// The directory location where the schemas are cached.
	private String cacheDirectoryLocation;
	private static SchemaFactory schemaFactory = null;
	private static Map<String, Schema> schemaCache = null;
	private Logger logger = LogManager.getLogger(GMESchemaFactory.class);

	/**
	 * Gets the schema for the given namespace from GME.
	 * 
	 * @param namespaceName
	 * @return
	 * @throws SchemaFactoryException
	 */
	public Schema getSchema(String serviceType, String namespaceName)
			throws SchemaFactoryException {
		Schema schema = null;
		try {
			schema = schemaCache.get(namespaceName);
			File schemaFile = null;
			if (GMEGridServiceLocation == null) {
				throw new SchemaFactoryException(
						"GME url needs to be specified for validating payloads.");
			}
			if (schema == null) {
				// Get the schema from GME or the file cache and parse it.
				GlobalModelExchangeClient client = new GlobalModelExchangeClient(
						GMEGridServiceLocation);
				XMLSchemaNamespace namespace = new XMLSchemaNamespace(
						namespaceName);
				Map<XMLSchemaNamespace, File> gmeSchema = client.cacheSchemas(
						namespace, new File(cacheDirectoryLocation));
				schemaFile = gmeSchema.get(namespace);
				if (!schemaFile.canRead()) {
					throw new SchemaFactoryException(
							"Error getting schema for namespace :"
									+ namespaceName);
				} else {
					// Parse and cache the schema
					schema = schemaFactory.newSchema(schemaFile);
					schemaCache.put(namespaceName, schema);
				}
			}
		} catch (SAXException e2) {
			throw new SchemaFactoryException(
					"Error parsing schema for namespace " + namespaceName, e2);
		} catch (Exception e3) {
			throw new SchemaFactoryException(
					"Error while getting schema for message type "
							+ serviceType, e3);
		}

		return schema;
	}

	public void init() {
		// verify the directory exists
		File cacheDirectory = new File(cacheDirectoryLocation);
		logger.info("Cache directory is " + cacheDirectoryLocation);
		if (!cacheDirectory.exists()) {
			logger.info("Cache directory does not exist. "
					+ cacheDirectoryLocation);
			if (!cacheDirectory.mkdir()) {
				logger.info("Error occurred creating Cache directory. "
						+ cacheDirectoryLocation);
				throw new IllegalStateException(
						"Schema files cache location does not exist."
								+ cacheDirectoryLocation);
			}
		}
		// create the schema cache
		if (schemaCache == null) {
			schemaCache = new HashMap<String, Schema>();
		}
		if (schemaFactory == null) {
			// System.setProperty("javax.xml.validation.SchemaFactory:"+XMLConstants.W3C_XML_SCHEMA_NS_URI,
			// "com.sun.org.apache.xerces.internal.jaxp.validation.xs.SchemaFactoryImpl");
			// System.setProperty("javax.xml.validation.SchemaFactory:"+XMLConstants.W3C_XML_SCHEMA_NS_URI,
			// "org.apache.xerces.jaxp.validation.XMLSchemaFactory");
			schemaFactory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			logger.info("Schema Factory class:"
					+ schemaFactory.getClass().getName());
		}
	}

	public String getGMEGridServiceLocation() {
		return GMEGridServiceLocation;
	}

	public void setGMEGridServiceLocation(String url) {
		GMEGridServiceLocation = url;
	}

	public String getCacheDirectoryLocation() {
		return cacheDirectoryLocation;
	}

	public void setCacheDirectoryLocation(String cacheDirectoryLocation) {
		this.cacheDirectoryLocation = cacheDirectoryLocation;
	}

}

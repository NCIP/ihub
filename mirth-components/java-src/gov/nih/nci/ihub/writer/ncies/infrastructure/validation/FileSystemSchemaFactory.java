package gov.nih.nci.ihub.writer.ncies.infrastructure.validation;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class FileSystemSchemaFactory implements CaxchangeSchemaFactory {

	private String schemaDirectoryLocation;
	private static SchemaFactory schemaFactory = null;
	private static Map<String,Schema> schemaCache = null;
	private Logger logger = LogManager.getLogger(FileSystemSchemaFactory.class);
	
	public Schema getSchema(String serviceType, String namespaceName) throws SchemaFactoryException {
		Schema schema = null;
		try {
		   schema = schemaCache.get(namespaceName);
		   File schemaFile = null;
		   if (schema == null) {
		      schemaFile = new File(schemaDirectoryLocation+ File.separator +serviceType+".xsd");
		      if (!schemaFile.canRead()){
		    	 throw new SchemaFactoryException("Cannot read the schema file for namespace:"+namespaceName+" file name: "+serviceType+".xsd");	
		      }else {
		    	  //Parse and cache the schema
		    	  schema = schemaFactory.newSchema(schemaFile);
		    	  schemaCache.put(namespaceName, schema);
		      }
		   
		   }
		} catch (SAXException e2) {
			throw new SchemaFactoryException("Error parsing schema for namespace "+namespaceName, e2);
		} catch (Exception e3) {
			throw new SchemaFactoryException("Error while getting schema for message type "+serviceType, e3);
		}
		
		return schema;	
	}
	
	
	public void init() {
		//verify the directory exists
		File schemaDirectory = new File(schemaDirectoryLocation);
		logger.info("schema directory is "+schemaDirectoryLocation);
		if (!schemaDirectory.exists()) {
			logger.info("schema directory does not exist. "+schemaDirectoryLocation);
		   throw new IllegalStateException("Schema files cache location does not exist."+schemaDirectoryLocation);
		}
		//create the schema cache
		if (schemaCache == null) {
		   schemaCache = new HashMap<String, Schema>();
		}
		
		if (schemaFactory == null) {
			 //System.setProperty("javax.xml.validation.SchemaFactory:"+XMLConstants.W3C_XML_SCHEMA_NS_URI, "com.sun.org.apache.xerces.internal.jaxp.validation.xs.SchemaFactoryImpl");
			//System.setProperty("javax.xml.validation.SchemaFactory:"+XMLConstants.W3C_XML_SCHEMA_NS_URI, "org.apache.xerces.jaxp.validation.XMLSchemaFactory");
			 schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			 logger.info("Schema Factory class:"+schemaFactory.getClass().getName());
		}

	}

	public String getSchemaDirectoryLocation() {
		return schemaDirectoryLocation;
	}

	public void setSchemaDirectoryLocation(String schemaDirectoryLocation) {
		this.schemaDirectoryLocation = schemaDirectoryLocation;
	}

}

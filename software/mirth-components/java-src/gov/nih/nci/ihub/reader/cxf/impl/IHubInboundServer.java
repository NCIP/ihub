/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.reader.cxf.impl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class IHubInboundServer {

	private static final Logger logger = LogManager.getLogger(Logger.class);
	private static Properties properties;
	private Bus bus;

	static {
		URL propsUrl = IHubInboundServer.class.getClassLoader().getResource(
				"ihub.cxf.inbound.properties");
		properties = new Properties();
		if (propsUrl != null) {
			try {
				properties.load(propsUrl.openStream());
				logger.info("Properties for the ihub cxf inbound server loaded");
			} catch (IOException e) {
				logger.error(
						"Error loading ihub.cxf.inbound.properties file.Proceeding with default properties.",
						e);
			}
		} else { // set default properties
			properties.setProperty("ihub.cxf.hostname", "localhost");
			properties.setProperty("ihub.cxf.port", "8194");
		}
	}

	protected String getAddress() {
		String host = properties.getProperty("ihub.cxf.hostname");
		String port = properties.getProperty("ihub.cxf.port");
		return "https://" + host + ":" + port + "/CaXchangeRequestService";
	}

	public IHubInboundServer() throws Exception {
		try {
			logger.info("Starting the iHub inbound server ....");
			System.setProperty("javax.xml.soap.MessageFactory",
					"com.sun.xml.messaging.saaj.soap.ver1_1.SOAPMessageFactory1_1Impl");
			SpringBusFactory bf = new SpringBusFactory();
			URL busFile = IHubInboundServer.class.getClassLoader().getResource(
					"CXFInboundServer.xml");
			logger.info("Loading the CXF inbound bus from:" + busFile);
			bus = bf.createBus(busFile.toString());
			SpringBusFactory.setDefaultBus(bus);

			Object iHubCXFImplementer = new IhubCXFInboundImpl();
			String address = getAddress();
			logger.info("CXF inbound publish address:" + address);
			Endpoint.publish(address, iHubCXFImplementer);
			logger.info("Finished starting the iHub inbound server.");
		} catch (Throwable t) {
			logger.error("Error in starting the CXF inbound server", t);
			throw new Exception("Error in starting the CXF inbound server", t);
		}
	}
	

	public void shutdown(boolean wait) {
		bus.shutdown(wait);
	}

	public static void main(String[] args) {
		try {
			System.out.println();
			IHubInboundServer server = new IHubInboundServer();
			

			System.out.println("Server ready...");
			Thread.sleep(5 * 60 * 1000);
			System.out.println("Server exiting");
			server.shutdown(false);
			Thread.sleep(5 * 60 * 1000);
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

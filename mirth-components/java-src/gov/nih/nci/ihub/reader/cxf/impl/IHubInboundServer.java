package gov.nih.nci.ihub.reader.cxf.impl;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IHubInboundServer {

    private static final Logger logger = LogManager.getLogger(Logger.class);
    private String host = "localhost";
    private String port;
    private static Properties properties;
    
    static {
    	URL propsUrl = IHubInboundServer.class.getClassLoader().getResource("ihub.cxf.inbound.properties");
    	properties = new Properties();
    	if (propsUrl != null) {
    	       try {
				properties.load(propsUrl.openStream());
			} catch (IOException e) {
				logger.error("Error loading ihub.cxf.inbound.properties file.Proceeding with default properties.", e);
			}
    	}else { //set default properties
    		properties.setProperty("ihub.cxf.hostname","localhost");
    		properties.setProperty("ihub.cxf.port", "8194");
    	}
    }
 	
	protected String getAddress() {
		String host = properties.getProperty("ihub.cxf.hostname");
		String port = properties.getProperty("ihub.cxf.port");
		return "https://"+host+":"+port+"/CaXchangeRequestService";
	}


	protected IHubInboundServer() throws Exception {
    	logger.info("Starting the iHub inbound server ....");
        SpringBusFactory bf = new SpringBusFactory();
        URL busFile = IHubInboundServer.class.getClassLoader().getResource("CXFInboundServer.xml");
        Bus bus = bf.createBus(busFile.toString());
        SpringBusFactory.setDefaultBus(bus);

        Object iHubCXFImplementer = new IhubCXFInboundImpl();
        String address = getAddress();
        Endpoint.publish(address, iHubCXFImplementer);
    }


	public static void main(String[] args) {
		try {
			System.out.println();
			new IHubInboundServer();

	        System.out.println("Server ready...");
	        Thread.sleep(5 * 60 * 1000);
	        System.out.println("Server exiting");
	        System.exit(0);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}

package gov.nih.nci.integration.cacis.inbound;

import gov.nih.nci.ihub.cacis.inbound.DefaultAcceptMessage;
import gov.nih.nci.ihub.cacis.inbound.DefaultAcceptMessageService;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A client to submit request to DefaultAcceptMessage webservice of Mirth
 * @author vinodh.rc
 *
 */
public class DefaultAcceptMessageClient {

    public String acceptMessage(String wsdl, String requestStr) throws IntegrationException{
               
        URL wsdlUrl=null;
		try {
			wsdlUrl = new URL(wsdl);
		} catch (MalformedURLException e) {
			throw new IntegrationException(IntegrationError._1081);
		}
        final DefaultAcceptMessage service = new
                DefaultAcceptMessageService(wsdlUrl).getDefaultAcceptMessagePort();

        return service.acceptMessage(requestStr);
    }
    /**
     * @param args
     * @throws IntegrationException 
     */
    public static void main(String[] args) throws IntegrationException {
        DefaultAcceptMessageClient client = new DefaultAcceptMessageClient();
       
            String res = client.acceptMessage("http://localhost:12085/services/Mirth?wsdl", "<trim>testtrim</trim>");
            System.out.println("response is " + res);
       
    }

}

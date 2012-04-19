package gov.nih.nci.cacis.sa.transcend;

import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;

import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class TranscendSemanticAdapterClient {
	
	public String acceptSource(String wsdl, String requestStr) throws IntegrationException{
        
        URL wsdlUrl=null;
		try {
			wsdlUrl = new URL(wsdl);
		} catch (MalformedURLException e) {
			throw new IntegrationException(IntegrationError._1081);
		}
        final AcceptSourcePortType saClient = new
        			TranscendSemanticAdapterService(wsdlUrl).getAcceptSourcePortSoap11();
        
        try {
			CaCISResponse response = saClient.acceptSource(unmarshal(requestStr));
			return response.getStatus().toString();
		} catch (AcceptSourceFault e) {
			throw new IntegrationException(IntegrationError._1043, e, null);
		} catch (JAXBException e) {
			throw new IntegrationException(IntegrationError._1041, e, null);
		}
    }
	
	private CaCISRequest unmarshal(String requestStr) throws JAXBException {
		JAXBContext ctx = JAXBContext.newInstance(CaCISRequest.class);
		return (CaCISRequest) ctx.createUnmarshaller().unmarshal(new StringReader(requestStr));
	}
}

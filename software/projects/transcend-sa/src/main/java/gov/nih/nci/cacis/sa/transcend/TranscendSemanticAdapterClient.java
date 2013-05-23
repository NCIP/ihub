/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
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
			IntegrationException ie = new IntegrationException(IntegrationError._1081);
			return populateCaCISError(ie.getErrorType().name(), String.valueOf(ie.getErrorCode()), ie.getMessage());
		}
        final AcceptSourcePortType saClient = new
        			TranscendSemanticAdapterService(wsdlUrl).getAcceptSourcePortSoap11();
        
        try {
			CaCISResponse response = saClient.acceptSource(unmarshal(requestStr));
			return response.getStatus().toString();
		} catch (AcceptSourceFault e) {
			CaCISError ce = e.getFaultInfo().getCaCISError().get(0);
			return populateCaCISError(ce.getErrorType().name(), ce.getErrorCode(), ce.getErrorMessage());
		} catch (JAXBException e) {
			IntegrationException ie = new IntegrationException(IntegrationError._1041, e, e.getMessage());
			return populateCaCISError(ie.getErrorType().name(), String.valueOf(ie.getErrorCode()), ie.getMessage());
		} catch (Exception e) {
			IntegrationException ie = new IntegrationException(IntegrationError._1043, e, e.getMessage());
			return populateCaCISError(ie.getErrorType().name(), String.valueOf(ie.getErrorCode()), ie.getMessage());
		}
    }
	
	private CaCISRequest unmarshal(String requestStr) throws JAXBException {
		JAXBContext ctx = JAXBContext.newInstance(CaCISRequest.class);
		return (CaCISRequest) ctx.createUnmarshaller().unmarshal(new StringReader(requestStr));
	}
	
	private String populateCaCISError(String errorType, String errorCode, String message) {
		return "<caCISError errorType=\""+ errorType + "\" errorCode=\"" + errorCode + "\"" +
		" errorMessage=\"" + message + "\"" + " detail=\"\"/>";
	}
}

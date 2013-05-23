/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.cacis.sa.transcend.client;

import gov.nih.nci.cacis.sa.transcend.AcceptSourceFault;
import gov.nih.nci.cacis.sa.transcend.AcceptSourcePortType;
import gov.nih.nci.cacis.sa.transcend.CaCISError;
import gov.nih.nci.cacis.sa.transcend.CaCISRequest;
import gov.nih.nci.cacis.sa.transcend.CaCISResponse;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;

import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * This is the client class for TranscendSemanticAdapter Service.
 * 
 * @author Rohit Gupta
 * 
 */
public class TranscendSemanticAdapterClient {

    /**
     * acceptSource
     * 
     * @param wsdl - wsdl
     * @param requestStr - request in the form of string
     * @return response status
     * @throws IntegrationException - IntegrationException
     */
    public String acceptSource(String wsdl, String requestStr) throws IntegrationException {

        URL wsdlUrl = null;
        try {
            wsdlUrl = new URL(wsdl);
        } catch (MalformedURLException e) {
            final IntegrationException ie = new IntegrationException(IntegrationError._1055);
            return populateCaCISError(ie.getErrorType().name(), String.valueOf(ie.getErrorCode()), ie.getMessage(), "");
        }
        final AcceptSourcePortType saClient = new TranscendSemanticAdapterService(wsdlUrl).getAcceptSourcePortSoap11();

        try {
            final CaCISResponse response = saClient.acceptSource(unmarshal(requestStr));
            return response.getStatus().toString();
        } catch (AcceptSourceFault e) {
            final CaCISError ce = e.getFaultInfo().getCaCISError().get(0);
            return populateCaCISError(ce.getErrorType().name(), ce.getErrorCode(), ce.getErrorMessage(), ce.getDetail());
        } catch (JAXBException e) {
            final IntegrationException ie = new IntegrationException(IntegrationError._1041, e, e.getMessage());
            return populateCaCISError(ie.getErrorType().name(), String.valueOf(ie.getErrorCode()), ie.getMessage(), "");
        }
    }

    private CaCISRequest unmarshal(String requestStr) throws JAXBException {
        final JAXBContext ctx = JAXBContext.newInstance(CaCISRequest.class);
        return (CaCISRequest) ctx.createUnmarshaller().unmarshal(new StringReader(requestStr));
    }

    private String populateCaCISError(String errorType, String errorCode, String message, String detail) {
        return "<caCISError errorType=\"" + errorType + "\" errorCode=\"" + errorCode + "\"" + " errorMessage=\""
                + message + "\"" + " detail=\"" + detail + "\"/>";
    }
}

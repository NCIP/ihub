package gov.nih.nci.ihub.reader.cxf.impl;

import java.util.logging.Logger;

import gov.nih.nci.ihub.reader.cxf.CaXchangeRequestPortType;
import gov.nih.nci.ihub.reader.cxf.Message;
import gov.nih.nci.ihub.reader.cxf.ResponseMessage;

@javax.jws.WebService(
        serviceName = "CaXchangeRequestService",
        portName = "soap",
        targetNamespace = "http://caXchange.nci.nih.gov/caxchangerequest",
        wsdlLocation = "classpath:caXchangeRequest.wsdl",
        endpointInterface = "gov.nih.nci.ihub.reader.cxf.CaXchangeRequestPortType")
public class IhubCXFInboundImpl implements CaXchangeRequestPortType {


    private static final Logger LOG = Logger.getLogger(IhubCXFInboundImpl.class.getName());

	public ResponseMessage processRequest(Message parameters) {
        LOG.info("Executing operation processRequest");
        System.out.println(parameters);
        try {
        	gov.nih.nci.ihub.reader.cxf.ResponseMessage _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
	}

}

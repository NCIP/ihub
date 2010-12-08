package gov.nih.nci.ihub.writer.ncies.capability.cdm;

import org.apache.axiom.om.OMOutputFormat;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.transport.http.SOAPMessageFormatter;

public class RegisterSubjectMedidataMessageFormatter extends SOAPMessageFormatter {

	
	public String getContentType(MessageContext msgCtxt, OMOutputFormat format, String soapActionString)
    {
		return "application/soap+xml;x-format=PatientDataRequest2";
    }
}

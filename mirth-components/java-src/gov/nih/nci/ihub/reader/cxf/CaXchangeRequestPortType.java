package gov.nih.nci.ihub.reader.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.1.2
 * Tue Apr 19 17:26:39 EDT 2011
 * Generated source version: 2.1.2
 *
 */

@WebService(targetNamespace = "http://caXchange.nci.nih.gov/caxchangerequest", name = "CaXchangeRequestPortType")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface CaXchangeRequestPortType {

    @WebResult(name = "caXchangeResponseMessage", targetNamespace = "http://caXchange.nci.nih.gov/messaging", partName = "parameters")
    @WebMethod(operationName = "ProcessRequest")
    public ResponseMessage processRequest(
        @WebParam(partName = "parameters", name = "caXchangeRequestMessage", targetNamespace = "http://caXchange.nci.nih.gov/messaging")
        Message parameters
    );
}

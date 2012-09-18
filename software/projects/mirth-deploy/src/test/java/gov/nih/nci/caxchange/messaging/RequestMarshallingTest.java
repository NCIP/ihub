package gov.nih.nci.caxchange.messaging;

import gov.nih.nci.integration.catissue.CaTissueParticipantClientIntegrationTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Testcase for Marshaling/Unmarshaling the XML
 * 
 * @author Vinodh
 * 
 */
public class RequestMarshallingTest {

    private static final Logger LOG = LoggerFactory.getLogger(RequestMarshallingTest.class);

    /**
     * Testcase for marshaling the Message
     * 
     * @throws JAXBException - JAXBException
     */
    @Test
    public void marshallMessage() throws JAXBException {
        final Object obj = unmarshall(getMessage(), Message.class);

        final Message msg = ((JAXBElement<Message>) obj).getValue();
        // final JAXBMarshaller jm = new JAXBMarshaller();

        final QName qname = new QName("http://caXchange.nci.nih.gov/caxchangerequest", "caxchangerequest");
        final JAXBElement<Message> message = new JAXBElement<Message>(qname, Message.class, msg);
        marshal(message, Message.class);
        // final String s = marshal(message, Message.class);

    }

    /**
     * Testcase for marshaling the error
     * 
     * @throws JAXBException - JAXBException
     */
    @Test
    public void marshallError() throws JAXBException {
        final ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorCode("EC");
        errorDetails.setErrorDescription("ED");
        final Response response = new Response();
        response.setCaXchangeError(errorDetails);
        response.setResponseStatus(Statuses.FAILURE);

        final QName qname = new QName("http://caXchange.nci.nih.gov/messaging", "response");

        final JAXBElement<Response> respJ = new JAXBElement<Response>(qname, Response.class, response);

        String xml = marshal(respJ, Response.class);

        final QName ceQname = new QName("http://caXchange.nci.nih.gov/messaging", "caXchangeError");
        final JAXBElement<ErrorDetails> edJ = new JAXBElement<ErrorDetails>(ceQname, ErrorDetails.class, errorDetails);
        xml = marshal(edJ, ErrorDetails.class);
    }

    /**
     * Marshals Object to its xml format, based on its context
     * 
     * @param xmlstr - object to be marshalled
     * @param claz - class
     * @return marshalled string
     * @throws JAXBException exception thrown, if any
     */
    public Object unmarshall(String xmlstr, Class claz) throws JAXBException {
        final JAXBContext jc = JAXBContext.newInstance(claz);

        final Unmarshaller m = jc.createUnmarshaller();
        return m.unmarshal(new StringReader(xmlstr));
    }

    /**
     * marshal
     * 
     * @param obj - object to be marshal
     * @param claz - class
     * @return String after marshaling
     * @throws JAXBException - JAXBException
     */
    public String marshal(Object obj, Class claz) throws JAXBException {
        final JAXBContext jc = JAXBContext.newInstance(claz);

        final Marshaller m = jc.createMarshaller();
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        m.marshal(obj, pw);

        return sw.toString();
    }

    private String getMessage() {
        return getXMLString("MarshallingMsg.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaTissueParticipantClientIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads/participant/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }
}

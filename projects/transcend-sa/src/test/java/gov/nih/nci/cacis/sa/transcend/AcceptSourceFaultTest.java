package gov.nih.nci.cacis.sa.transcend;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

import org.junit.Assert;
import org.junit.Test;
/**
 * Test class for CaCISFault
 * @author Vinodh
 *
 */
public class AcceptSourceFaultTest {

    // CHECKSTYLE:OFF
    private final static QName _CaCISFault_QNAME = new QName(// NOPMD
            "http://cacis.nci.nih.gov", "caCISFault");
    // CHECKSTYLE:ON

    /**
     * Testcase for createFault
     * @throws JAXBException - JAXBException
     */
    @Test
    public void createFault() throws JAXBException {

        CaCISFault cf = new CaCISFault();
        CaCISError ce = new CaCISError();
        ce.setErrorCode("EC");
        ce.setErrorMessage("EM");
        ce.setErrorType(ErrorType.VALIDATION);
        ce.setDetail("detail");
        cf.getCaCISError().add(ce);

        String faultXml = getCaCISFaultxml(cf);
        Assert.assertEquals(getFaultString(), faultXml);

        CaCISFault newCf = getCaCISFaultFromXml(getFaultString());
        Assert.assertEquals("EC", newCf.getCaCISError().get(0).getErrorCode());
    }

    private String getCaCISFaultxml(final CaCISFault parameter) {
        try {
            StringWriter sw = new StringWriter();
            JAXBElement<CaCISFault> cfj = new JAXBElement<CaCISFault>(_CaCISFault_QNAME, CaCISFault.class, parameter);
            getMarshaller().marshal(cfj, sw);
            return sw.toString();
            // CHECKSTYLE:OFF
        } catch (Exception ex) {
            // CHECKSTYLE:ON
            ex.printStackTrace();
            return null;
        }
    }

    private CaCISFault getCaCISFaultFromXml(String faultXML) throws JAXBException {
        StreamSource ss = new StreamSource(new StringReader(faultXML));
        return ((JAXBElement<CaCISFault>) getUnmarshaller().unmarshal(ss, CaCISFault.class)).getValue();
    }

    private Marshaller getMarshaller() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(CaCISFault.class);
        return jc.createMarshaller();
    }

    private Unmarshaller getUnmarshaller() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(CaCISFault.class);
        return jc.createUnmarshaller();
    }

    // CHECKSTYLE:OFF
    private String getFaultString() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><caCISFault xmlns=\"http://cacis.nci.nih.gov\"><caCISError errorType=\"VALIDATION\" errorCode=\"EC\" errorMessage=\"EM\" detail=\"detail\"/></caCISFault>";
    }
}

/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.cacis.sa.transcend;

import java.io.IOException;
import java.io.InputStream;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class for CaCISFault
 * 
 * @author Vinodh
 * 
 */
public class AcceptSourceFaultTest {

    private final QName faultQame = new QName("http://cacis.nci.nih.gov", "caCISFault");
    private static final Logger LOG = LoggerFactory.getLogger(AcceptSourceFaultTest.class);

    /**
     * Testcase for createFault
     * 
     * @throws JAXBException - JAXBException
     */
    @Test
    public void createFault() throws JAXBException {

        final CaCISFault cf = new CaCISFault();
        final CaCISError ce = new CaCISError();
        ce.setErrorCode("EC");
        ce.setErrorMessage("EM");
        ce.setErrorType(ErrorType.VALIDATION);
        ce.setDetail("detail");
        cf.getCaCISError().add(ce);

        final String faultXml = getCaCISFaultxml(cf);
        Assert.assertEquals(getFaultString(), faultXml);

        final CaCISFault newCf = getCaCISFaultFromXml(getFaultString());
        Assert.assertEquals("EC", newCf.getCaCISError().get(0).getErrorCode());
    }

    private String getCaCISFaultxml(final CaCISFault parameter) {

        final StringWriter sw = new StringWriter();
        final JAXBElement<CaCISFault> cfj = new JAXBElement<CaCISFault>(faultQame, CaCISFault.class, parameter);
        try {
            getMarshaller().marshal(cfj, sw);
        } catch (JAXBException e) {
            LOG.error("JAXBException ", e);
        }
        return sw.toString();

    }

    private CaCISFault getCaCISFaultFromXml(String faultXML) throws JAXBException {
        final StreamSource ss = new StreamSource(new StringReader(faultXML));
        return ((JAXBElement<CaCISFault>) getUnmarshaller().unmarshal(ss, CaCISFault.class)).getValue();
    }

    private Marshaller getMarshaller() throws JAXBException {
        final JAXBContext jc = JAXBContext.newInstance(CaCISFault.class);
        return jc.createMarshaller();
    }

    private Unmarshaller getUnmarshaller() throws JAXBException {
        final JAXBContext jc = JAXBContext.newInstance(CaCISFault.class);
        return jc.createUnmarshaller();
    }

    private String getFaultString() {
        return getXMLString("FaultString.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = AcceptSourceFaultTest.class.getClassLoader().getResourceAsStream("payloads/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }
}

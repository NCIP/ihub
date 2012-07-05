package gov.nih.nci.integration.util;

import javax.xml.bind.JAXBException;

import org.junit.Test;

/**
 * Marshals any object to xml based on its context
 * 
 * @author vinodh.rc@semanticbits.com
 * 
 */
public class JAXBMarshallerTest {

    /**
     * @throws JAXBException - JAXBException
     */
    @Test
    public void marshalTest() throws JAXBException {
        final JAXBMarshaller marshaller = new JAXBMarshaller();
        final Address address = new Address();
        address.setCity("Hendon");
        address.setLine1("Park Center Way");
        address.setZipcode("20171");
        marshaller.marshal(Address.class, address);
    }

}

package gov.nih.nci.integration.caaers;

import gov.nih.nci.cabig.caaers.webservice.ParticipantType;
import gov.nih.nci.cabig.caaers.webservice.Response;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.cxf.helpers.IOUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author chandrasekaravr
 *
 */
public class CaAERSParticipantServiceClientTest {
	
	private CaAERSParticipantServiceClient caAERSParticipantServiceClient;
	
	
	public CaAERSParticipantServiceClientTest() throws JAXBException {
		super();
		caAERSParticipantServiceClient = new CaAERSParticipantServiceClient();		
	}
	
	@Test
	public void marshalParticipantType() throws JAXBException {
		ParticipantType pt = new ParticipantType();
		QName qname = new QName("http://webservice.caaers.cabig.nci.nih.gov/participant", "participant");
		JAXBElement<ParticipantType> ptJaxbEle = new JAXBElement<ParticipantType>(qname, ParticipantType.class, pt);
		StringWriter sw = new StringWriter();
		getMarshaller().marshal(ptJaxbEle, sw);
		Assert.assertNotNull(sw.toString());
	}


	@Test
	public void createParticipant() throws JAXBException, IOException {		
		
		String participantXMLStr = IOUtils.toString(new FileReader(new File("C:\\vin\\SUITE-iHub\\tmp\\sample\\smpl_caaers_participant.xml")));
		Response response = caAERSParticipantServiceClient.createParticipant(
				"https://dev.semanticbits.com/caaers/services/ParticipantService?wsdl", participantXMLStr);
		
		Assert.assertNotNull(response);
		System.out.println(response.getDescription());
		System.out.println(response.getResponsecode());
		System.out.println(response.getMessage());
	}
	
	private Marshaller getMarshaller() throws JAXBException {		
		JAXBContext jc = JAXBContext.newInstance(ParticipantType.class);		
		return jc.createMarshaller();		
	}
}

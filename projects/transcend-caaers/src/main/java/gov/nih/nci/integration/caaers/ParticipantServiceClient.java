package gov.nih.nci.integration.caaers;

import gov.nih.nci.cabig.caaers.webservice.CreateParticipant;
import gov.nih.nci.cabig.caaers.webservice.CreateParticipant.Participants;
import gov.nih.nci.cabig.caaers.webservice.CreateParticipantResponse.Return;
import gov.nih.nci.cabig.caaers.webservice.ParticipantService;
import gov.nih.nci.cabig.caaers.webservice.ParticipantServiceIntf;
import gov.nih.nci.cabig.caaers.webservice.ParticipantType;
import gov.nih.nci.cabig.caaers.webservice.Response;

import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;


public class ParticipantServiceClient {
	
	Unmarshaller unmarshaller = null;
	
	public ParticipantServiceClient() throws JAXBException {
		super();
		getUnmarshaller();
	}

	private Unmarshaller getUnmarshaller() throws JAXBException {
		if (unmarshaller == null) {
			JAXBContext jc = JAXBContext.newInstance(ParticipantType.class);		
			unmarshaller = jc.createUnmarshaller();
		}
		return unmarshaller;
	}

	/**
	 * Unmarshalls a ParticipantType object from an xml string
	 * 
	 * @param participantXMLStr
	 *            - the Participant xml string
	 * @return ParticipantType object
	 * @throws JAXBException
	 */
	public ParticipantType parseParticipant(String participantXMLStr) throws JAXBException {
		return (ParticipantType) getUnmarshaller().unmarshal(
				new StreamSource(new StringReader(participantXMLStr)));
	}
	
	public Response createParticipant(String wsdl, String participantXMLStr) throws JAXBException, MalformedURLException {		
		ParticipantType participant = parseParticipant(participantXMLStr);
		CreateParticipant createParticipant = new CreateParticipant();
		Participants participants = new Participants();
		participants.getParticipant().add(participant);
		
		final URL wsdlUrl = new URL(wsdl);
		ParticipantServiceIntf service = 
			new ParticipantService(wsdlUrl).getParticipantManagementWebServicePort();
		Return retValue = service.createParticipant(createParticipant.getParticipants());
		return retValue.getResponse();
	}
}

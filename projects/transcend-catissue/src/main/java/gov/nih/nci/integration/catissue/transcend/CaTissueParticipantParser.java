package gov.nih.nci.integration.catissue.transcend;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import edu.wustl.catissuecore.domain.Participant;

/**
 * This class parses the Participant xml message and unmarshalls it in to a Participant object
 * 
 * @author vinodh.rc
 */
public class CaTissueParticipantParser {
	
	private Unmarshaller getUnmarshaller() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Participant.class);		
		return jc.createUnmarshaller();
	}

	/**
	 * Unmarshalls a Participant object from an xml string
	 * 
	 * @param participantXMLStr
	 *            - the Participant xml string
	 * @return Participant object
	 * @throws JAXBException
	 */
	public Participant parseParticipant(String participantXMLStr) throws JAXBException {
		return (Participant) getUnmarshaller().unmarshal(
				new StreamSource(new StringReader(participantXMLStr)));
	}

}

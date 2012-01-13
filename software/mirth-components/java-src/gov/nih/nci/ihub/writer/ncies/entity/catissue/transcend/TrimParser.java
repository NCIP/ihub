package gov.nih.nci.ihub.writer.ncies.entity.catissue.transcend;

import java.io.StringReader;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;

import org.tolven.trim.Trim;
import org.tolven.xml.ParseXML;
import org.w3c.dom.Node;

/**
 * This class parses the HL7 Trim message and unmarshalls it in to a Trim object
 * 
 * @author syed added on 8/5/09 modified on 8/12/09
 */
public class TrimParser extends ParseXML {

	@Override
	protected String getParsePackageName() {
		return "org.tolven.trim";
	}

	/**
	 * Unmarshalls a Trim object from an xml string
	 * 
	 * @author syed added on 8/5/09 modified on 8/12/09
	 * @param trimString
	 *            - the HL7 trim message string
	 * @return Trim object
	 * @throws JAXBException
	 */
	public Trim parseTrim(String trimString) throws JAXBException {
		return (Trim) getUnmarshaller().unmarshal(
				new StreamSource(new StringReader(trimString)));
	}

	/**
	 * Unmarshalls a Trim object from a Node object
	 * 
	 * @author syed added on 8/5/09 modified on 8/12/09
	 * @param trimNode
	 *            - the HL7 trim node
	 * @return Trim object
	 * @throws JAXBException
	 */
	public Trim parseTrim(Node trimNode) throws JAXBException {
		return (Trim) getUnmarshaller().unmarshal(trimNode);
	}
}

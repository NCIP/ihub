/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.common;

import gov.nih.nci.caXchange.CaxchangeErrors;

import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This class builds the aggregated response after getting responses from all
 * the target grid services
 * 
 * @author hmarwaha
 * 
 */
public class AggregatedResponseBuilder {
	protected static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
			.newInstance();
	protected static final Logger LOGGER = LogManager
			.getLogger(AggregatedResponseBuilder.class);
	protected static String timeOutResponse = "  <targetResponse>\n"
			+ "                <targetServiceIdentifier>@targetServiceIdentifier@</targetServiceIdentifier>\n"
			+ "                <targetServiceOperation></targetServiceOperation>\n"
			+ "                <targetMessageStatus>ERROR</targetMessageStatus>\n"
			+ "                   <targetError>  "
			+ "                        <errorCode>"
			+ CaxchangeErrors.TIMEOUT
			+ "</errorCode>  "
			+ "                        <errorDescription>A timeout occurred waiting for response.</errorDescription> "
			+ "                    </targetError> "
			+ "            </targetResponse>";

	protected static Document timeOutDocument;

	String faultResponse = "            <businessResponse>\n"
			+ "                <targetServiceIdentifier>targetServiceIdentifier0</targetServiceIdentifier>\n"
			+ "                <targetServiceOperation>targetServiceOperation0</targetServiceOperation>\n"
			+ "                <messageStatus>RESPONSE</messageStatus>\n"
			+ "                <messagePayload>\n"
			+ "                    <xmlSchemaDefinition>http://www.oxygenxml.com/</xmlSchemaDefinition>\n"
			+ "                </messagePayload>\n"
			+ "            </businessResponse>";

	/**
	 * Default constructor
	 */
	public AggregatedResponseBuilder() {
	}

	/**
	 * This methods scans the list of messages form all the target grid services
	 * and builds the response document to send it to listener aggregator *
	 * 
	 * @param messages
	 * @param timeOut
	 * @return document
	 * @throws Exception
	 */
	public Document buildAggregatedDocument(List<Document> messages,
			boolean timeOut) throws Exception {
		DocumentBuilder documentBuilder = documentBuilderFactory
				.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		Element aggregateResponse = document.createElement("aggregateResponse");
		document.appendChild(aggregateResponse);
		if (messages != null) {
			Iterator<Document> iterator = messages.iterator();
			while (iterator.hasNext()) {
				Document message = iterator.next();
				Node importedNode = document.importNode(message.getDocumentElement(), true);
				aggregateResponse.appendChild(importedNode);
			}
		}
		// Create the timeout response for the services which have not yet
		// responded.
		/*
		 * if (timeOut && (exchangesToReceive != null)) { Iterator
		 * exchangesToReceiveIterator = exchangesToReceive .iterator(); while
		 * (exchangesToReceiveIterator.hasNext()) { String exchangeIdentifier =
		 * (String) exchangesToReceiveIterator .next(); LOGGER.debug("******" +
		 * exchangeIdentifier); String serviceTimeOutResponse =
		 * timeOutResponse.replace( "@targetServiceIdentifier@",
		 * exchangeIdentifier); LOGGER.debug("******" + serviceTimeOutResponse);
		 * timeOutDocument = documentBuilder.parse(new InputSource( new
		 * StringReader(serviceTimeOutResponse))); Node importedNode =
		 * document.importNode(timeOutDocument .getDocumentElement(), true);
		 * aggregateResponse.appendChild(importedNode); } }
		 */
		return document;
	}
}

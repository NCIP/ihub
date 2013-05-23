/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package org.cagrid.gaards.websso.authentication.helper;

import gov.nih.nci.cagrid.caxchange.listener.ResponseHandler;
import gov.nih.nci.caxchange.ResponseMessage;
import junit.framework.TestCase;

public class ResponseHandlerTest extends TestCase {

	public void testGetResponse() {
		//String responseString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><caXchangeResponseMessage xmlns=\"http://caXchange.nci.nih.gov/messaging\"><responseMetadata><externalIdentifier>myExternalIdentifier</externalIdentifier><caXchangeIdentifier>48acc510-f72f-11df-9468-b40e30b6a36f</caXchangeIdentifier></responseMetadata><response><responseStatus>SUCCESS</responseStatus><targetResponse><targetServiceIdentifier>PERSON</targetServiceIdentifier><targetServiceOperation>search</targetServiceOperation><targetMessageStatus>RESPONSE</targetMessageStatus><targetBusinessMessage><xmlSchemaDefinition>http://po.coppa.nci.nih.gov</xmlSchemaDefinition><soapenc:Array xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\"> <ns1:Person xmlns:ns1=\"http://po.coppa.nci.nih.gov\">  <ns1:identifier displayable=\"true\" extension=\"272810\" identifierName=\"NCI person entity identifier\" reliability=\"ISS\" root=\"2.16.840.1.113883.3.26.4.1\" scope=\"OBJ\"/>  <ns1:name>   <ns2:part xmlns:ns2=\"uri:iso.org:21090\" type=\"FAM\" value=\"Gaasch Smith\"/>   <ns3:part xmlns:ns3=\"uri:iso.org:21090\" type=\"GIV\" value=\"Adriana\"/>   <ns4:part xmlns:ns4=\"uri:iso.org:21090\" type=\"PFX\" value=\"Ms.\"/>  </ns1:name>  <ns1:postalAddress>   <ns5:part xmlns:ns5=\"uri:iso.org:21090\" type=\"AL\" value=\"9500 Gilman Drive\"/>   <ns6:part xmlns:ns6=\"uri:iso.org:21090\" type=\"ADL\" value=\"Clinical Trials Office MC 0698\"/>   <ns7:part xmlns:ns7=\"uri:iso.org:21090\" type=\"CTY\" value=\"La Jolla\"/>   <ns8:part xmlns:ns8=\"uri:iso.org:21090\" type=\"STA\" value=\"CA\"/>   <ns9:part xmlns:ns9=\"uri:iso.org:21090\" type=\"ZIP\" value=\"92037-0698\"/>   <ns10:part xmlns:ns10=\"uri:iso.org:21090\" code=\"USA\" codeSystem=\"ISO 3166-1 alpha-3 code\" type=\"CNT\" value=\"United States\"/>  </ns1:postalAddress>  <ns1:statusCode code=\"active\"/>  <ns1:telecomAddress>   <ns11:item xmlns:ns11=\"uri:iso.org:21090\" value=\"mailto:a8smith@ucsd.edu\"/>   <ns12:item xmlns:ns12=\"uri:iso.org:21090\" value=\"x-text-fax:(858)-657-7025\"/>   <ns13:item xmlns:ns13=\"uri:iso.org:21090\" value=\"tel:(858)-657-7019\"/>  </ns1:telecomAddress>  <ns1:raceCode nullFlavor=\"NI\"/>  <ns1:sexCode nullFlavor=\"NI\"/>  <ns1:ethnicGroupCode nullFlavor=\"NI\"/>  <ns1:birthDate nullFlavor=\"NI\"/> </ns1:Person></soapenc:Array></targetBusinessMessage></targetResponse></response></caXchangeResponseMessage>";
		String responseString = "<mes:caXchangeResponseMessage xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><mes:responseMetadata><mes:externalIdentifier>myExternalIdentifier</mes:externalIdentifier><mes:caXchangeIdentifier>a2b4a8c0-f72a-11df-9468-b40e30b6a36f</mes:caXchangeIdentifier></mes:responseMetadata><mes:response><mes:responseStatus>SUCCESS</mes:responseStatus><mes:targetResponse><mes:targetServiceIdentifier>PERSON</mes:targetServiceIdentifier><mes:targetServiceOperation>search</mes:targetServiceOperation><mes:targetMessageStatus>RESPONSE</mes:targetMessageStatus><mes:targetBusinessMessage><mes:xmlSchemaDefinition>http://po.coppa.nci.nih.gov</mes:xmlSchemaDefinition><soapenc:Array xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\"> <ns1:Person xmlns:ns1=\"http://po.coppa.nci.nih.gov\">  <ns1:identifier displayable=\"true\" extension=\"272810\" identifierName=\"NCI person entity identifier\" reliability=\"ISS\" root=\"2.16.840.1.113883.3.26.4.1\" scope=\"OBJ\"/>  <ns1:name>   <ns2:part type=\"FAM\" value=\"Gaasch Smith\" xmlns:ns2=\"uri:iso.org:21090\"/>   <ns3:part type=\"GIV\" value=\"Adriana\" xmlns:ns3=\"uri:iso.org:21090\"/>   <ns4:part type=\"PFX\" value=\"Ms.\" xmlns:ns4=\"uri:iso.org:21090\"/>  </ns1:name>  <ns1:postalAddress>   <ns5:part type=\"AL\" value=\"9500 Gilman Drive\" xmlns:ns5=\"uri:iso.org:21090\"/>   <ns6:part type=\"ADL\" value=\"Clinical Trials Office MC 0698\" xmlns:ns6=\"uri:iso.org:21090\"/>   <ns7:part type=\"CTY\" value=\"La Jolla\" xmlns:ns7=\"uri:iso.org:21090\"/>   <ns8:part type=\"STA\" value=\"CA\" xmlns:ns8=\"uri:iso.org:21090\"/>   <ns9:part type=\"ZIP\" value=\"92037-0698\" xmlns:ns9=\"uri:iso.org:21090\"/>   <ns10:part code=\"USA\" codeSystem=\"ISO 3166-1 alpha-3 code\" type=\"CNT\" value=\"United States\" xmlns:ns10=\"uri:iso.org:21090\"/>  </ns1:postalAddress>  <ns1:statusCode code=\"active\"/>  <ns1:telecomAddress>   <ns11:item value=\"mailto:a8smith@ucsd.edu\" xmlns:ns11=\"uri:iso.org:21090\"/>   <ns12:item value=\"x-text-fax:(858)-657-7025\" xmlns:ns12=\"uri:iso.org:21090\"/>   <ns13:item value=\"tel:(858)-657-7019\" xmlns:ns13=\"uri:iso.org:21090\"/>  </ns1:telecomAddress>  <ns1:raceCode nullFlavor=\"NI\"/>  <ns1:sexCode nullFlavor=\"NI\"/>  <ns1:ethnicGroupCode nullFlavor=\"NI\"/>  <ns1:birthDate nullFlavor=\"NI\"/> </ns1:Person></soapenc:Array></mes:targetBusinessMessage></mes:targetResponse></mes:response></mes:caXchangeResponseMessage>";
		ResponseHandler responseHandler = new ResponseHandler();
        try {
			responseHandler.setResponseText(responseString);
			ResponseMessage responseMessage = responseHandler.getResponse();
			System.out.println("Response Message: "+responseMessage.getResponse().getResponseStatus().getValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		//fail("Not yet implemented");
	}

}

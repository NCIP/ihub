package gov.nih.nci.ihub.test;

import static org.junit.Assert.*;
import gov.nih.nci.ihub.util.IntegrationHubUtil;
import gov.nih.nci.ihub.writer.ncies.common.GenerateResponseBean;

import org.junit.Test;
import org.w3c.dom.Document;

public class GenerateResponseBeanTest {

	@Test
	public void testGenerateResponseFromTargetResponse() {
		GenerateResponseBean generateResponseBean = new GenerateResponseBean();
		String compiledTargetResponse = "<targetResponse><targetServiceIdentifier>PO_PERSON_SERVICE</targetServiceIdentifier><targetServiceOperation>search</targetServiceOperation><targetMessageStatus>RESPONSE</targetMessageStatus><targetBusinessMessage><xmlSchemaDefinition>http://po.coppa.nci.nih.gov</xmlSchemaDefinition><soapenc:Array xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\"> <ns1:Person xmlns:ns1=\"http://po.coppa.nci.nih.gov\">  <ns1:identifier displayable=\"true\" extension=\"272810\" identifierName=\"NCI person entity identifier\" reliability=\"ISS\" root=\"2.16.840.1.113883.3.26.4.1\" scope=\"OBJ\"/>  <ns1:name>   <ns2:part xmlns:ns2=\"uri:iso.org:21090\" type=\"FAM\" value=\"Gaasch Smith\"/>   <ns3:part xmlns:ns3=\"uri:iso.org:21090\" type=\"GIV\" value=\"Adriana\"/>   <ns4:part xmlns:ns4=\"uri:iso.org:21090\" type=\"PFX\" value=\"Ms.\"/>  </ns1:name>  <ns1:postalAddress>   <ns5:part xmlns:ns5=\"uri:iso.org:21090\" type=\"AL\" value=\"9500 Gilman Drive\"/>   <ns6:part xmlns:ns6=\"uri:iso.org:21090\" type=\"ADL\" value=\"Clinical Trials Office MC 0698\"/>   <ns7:part xmlns:ns7=\"uri:iso.org:21090\" type=\"CTY\" value=\"La Jolla\"/>   <ns8:part xmlns:ns8=\"uri:iso.org:21090\" type=\"STA\" value=\"CA\"/>   <ns9:part xmlns:ns9=\"uri:iso.org:21090\" type=\"ZIP\" value=\"92037-0698\"/>   <ns10:part xmlns:ns10=\"uri:iso.org:21090\" code=\"USA\" codeSystem=\"ISO 3166-1 alpha-3 code\" type=\"CNT\" value=\"United States\"/>  </ns1:postalAddress>  <ns1:statusCode code=\"active\"/>  <ns1:telecomAddress>   <ns11:item xmlns:ns11=\"uri:iso.org:21090\" value=\"mailto:a8smith@ucsd.edu\"/>   <ns12:item xmlns:ns12=\"uri:iso.org:21090\" value=\"x-text-fax:(858)-657-7025\"/>   <ns13:item xmlns:ns13=\"uri:iso.org:21090\" value=\"tel:(858)-657-7019\"/>  </ns1:telecomAddress>  <ns1:raceCode nullFlavor=\"NI\"/>  <ns1:sexCode nullFlavor=\"NI\"/>  <ns1:ethnicGroupCode nullFlavor=\"NI\"/>  <ns1:birthDate nullFlavor=\"NI\"/> </ns1:Person></soapenc:Array></targetBusinessMessage></targetResponse>";
		Document compiledTargetDocument = IntegrationHubUtil.stringToDOMDocument(compiledTargetResponse);
		try {
			generateResponseBean.generateResponseFromTargetResponse("myExternalIdentifier", "d499943-3394330-3934830-399dddd", compiledTargetDocument);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		fail("Not yet implemented");
	}

}

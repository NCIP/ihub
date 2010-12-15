package gov.nih.nci.ihub.test;

import static org.junit.Assert.fail;
import gov.nih.nci.ihub.util.IntegrationHubUtil;
import gov.nih.nci.ihub.writer.ncies.capability.cdm.CCRegistrationInvocationStrategy;
import gov.nih.nci.ihub.writer.ncies.common.GenerateResponseBean;
import gov.nih.nci.ihub.writer.ncies.common.GridInvocationResult;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CCRegistrationInvocationStrategyTest {

	@Test
	public void testInvokeGridService() {
		
		try {
			CCRegistrationInvocationStrategy ccRegistrationInvocationStrategy = new CCRegistrationInvocationStrategy();
			ccRegistrationInvocationStrategy.setServiceType("REGISTER_SUBJECT");
			ccRegistrationInvocationStrategy.setServiceUrl(TestConstants.REGISTER_SUBJECT_MEDIDATA_URL);
			ccRegistrationInvocationStrategy.setUsername("ccts");
			ccRegistrationInvocationStrategy.setPassword("welcome");
			ccRegistrationInvocationStrategy.setConnectionTimeout(40000);
						
			Element registersubjectPayloadDocument = IntegrationHubUtil.
			stringToDOMDocument(TestConstants.REGISTER_SUBJECT_PAYLOAD).getDocumentElement();
			
			ccRegistrationInvocationStrategy.setServiceProviderName("MEDIDATA_RAVE");
			ccRegistrationInvocationStrategy.setPayload(registersubjectPayloadDocument);
			
			GridInvocationResult registerSubjectResponse = ccRegistrationInvocationStrategy.invokeGridService(false);			
			System.out.println(" MEDIDATA Transformed Response: "+IntegrationHubUtil.xmlToString(registerSubjectResponse.getResult()));
			
			GenerateResponseBean generateResponseBean = new GenerateResponseBean();
			Document gridInvocationResultDocument = null;
			if(registerSubjectResponse.isFault()){
				gridInvocationResultDocument = generateResponseBean.createErrorDocument("MEDIDATA_RAVE", "",registerSubjectResponse.getInvocationException());
			} else {
				gridInvocationResultDocument = generateResponseBean.createOutputDocument("MEDIDATA_RAVE", "",
						"gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain", registerSubjectResponse);
			}			
			
			String responseXML = IntegrationHubUtil.xmlToString(gridInvocationResultDocument);			
			System.out.println(" Response: "+responseXML);		
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}

package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.transformer.XSLTTransformer;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-transcend-catissue.xml")
public class XSLTTransformerTest {
	
	@Autowired
	private XSLTTransformer xsltTransformer;
	
	@Test
	public void intialize() throws IntegrationException {
		
		xsltTransformer.initTransformer("MsgBroadcasterParticipant-to-caTissueParticipant.xsl", "C:\\Users\\sb-admin-cp\\.integration\\ihub\\xsl\\");
		Assert.assertNotNull(xsltTransformer);
	}

}
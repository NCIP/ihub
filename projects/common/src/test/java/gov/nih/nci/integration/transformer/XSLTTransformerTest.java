package gov.nih.nci.integration.transformer;

import gov.nih.nci.integration.exception.IntegrationException;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-common-test.xml")
public class XSLTTransformerTest {
	
	@Autowired
	private XSLTTransformer xsltTransformer;
	
	@Test
	@Before
	public void intialize() throws IntegrationException {
		xsltTransformer.initTransformer("MsgBroadcasterParticipant-to-caTissueParticipant.xsl", "D:\\Workspace\\Suite-iHub\\software\\projects\\transcend-catissue\\src\\main\\resources\\xsl\\");
		Assert.assertNotNull(xsltTransformer);
	}
	
	
	@Test
	public void transform() throws IntegrationException , FileNotFoundException, IOException{
		
		InputStream in = XSLTTransformerTest.class.getResourceAsStream("MsgBroadcasterParticipant.xml"); 

		
//		BufferedReader br = new BufferedReader(new InputStreamReader(in));	 
//		String line=null;
//		while ((line = br.readLine()) != null) {
//			System.out.println(line);
//		}
//		br.close();
		
		ByteArrayOutputStream baos = null; 
		baos = new ByteArrayOutputStream();

		
		xsltTransformer.transform(null, in, baos);
		
		Assert.assertNotNull(xsltTransformer);
		
		
	}
}


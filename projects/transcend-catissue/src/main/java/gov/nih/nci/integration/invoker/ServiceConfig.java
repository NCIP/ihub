package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.catissue.CaTissueParticipantClient;
import gov.nih.nci.integration.transformer.XSLTTransformer;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ServiceConfig {
	
	@Autowired
	private CaTissueParticipantClient caTissueParticipantClient;
	
	@Autowired
	private XSLTTransformer xsltTransformer;
	
	@Bean
	@Scope("prototype")
	public ServiceInvocationStrategy caTissueServiceInvocationStrategy() throws TransformerConfigurationException, TransformerException {
		xsltTransformer.initTransformer("MsgBroadcasterParticipant-to-caTissueParticipant.xsl", "C:\\Users\\sb-admin-cp\\.integration\\ihub\\xsl\\");
		return new CaTissueServiceInvocationStrategy(2, caTissueParticipantClient, xsltTransformer);
	}
}

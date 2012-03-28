package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.catissue.CaTissueAPIClientWithRegularAuthentication;
import gov.nih.nci.integration.catissue.CaTissueParticipantClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaTissueConfig {
	
	@Value("${catissue.api.login.username}")	
	private String catissueApiLoginName;
	
	@Value("${catissue.api.login.password}")
	private String catissueApiPassword;
	
	@Bean
	public CaTissueAPIClientWithRegularAuthentication caTissueAPIClientWithRegularAuthentication() {
		return new CaTissueAPIClientWithRegularAuthentication(catissueApiLoginName, catissueApiPassword);
	}
	
	@Bean
	public CaTissueParticipantClient caTissueParticipantClient() {
		return new CaTissueParticipantClient(caTissueAPIClientWithRegularAuthentication());		
	}
	
}

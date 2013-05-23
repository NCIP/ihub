/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.catissue.CaTissueParticipantClient;
import gov.nih.nci.integration.exception.IntegrationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaTissueConfig {
	
	@Value("${catissue.custom.lib.location}")	
	private String caTissueLibLocation;
	
	@Value("${catissue.api.login.username}")	
	private String catissueApiLoginName;
	
	@Value("${catissue.api.login.password}")
	private String catissueApiPassword;
	
	@Bean
	public CaTissueParticipantClient caTissueParticipantClient() throws IntegrationException {
		return new CaTissueParticipantClient(caTissueLibLocation, catissueApiLoginName, catissueApiPassword);
	}
	
}

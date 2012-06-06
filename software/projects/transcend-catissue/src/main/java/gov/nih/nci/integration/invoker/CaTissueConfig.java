package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.catissue.CaTissueConsentClient;
import gov.nih.nci.integration.catissue.CaTissueParticipantClient;
import gov.nih.nci.integration.catissue.CaTissueSpecimenClient;
import gov.nih.nci.integration.exception.IntegrationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is used to read the configurations for caTissue
 * 
 * @author Vinodh
 */
@Configuration
public class CaTissueConfig {

    @Value("${catissue.custom.lib.location}")
    private String caTissueLibLocation;

    @Value("${catissue.api.login.username}")
    private String catissueApiLoginName;

    @Value("${catissue.api.login.password}")
    private String catissueApiPassword;

    /**
     * To get CaTissueParticipantClient
     * 
     * @return CaTissueParticipantClient
     * @throws IntegrationException - IntegrationException
     */
    @Bean
    public CaTissueParticipantClient caTissueParticipantClient() throws IntegrationException {
        return new CaTissueParticipantClient(caTissueLibLocation, catissueApiLoginName, catissueApiPassword);
    }

    /**
     * To get CaTissueSpecimenClient
     * 
     * @return CaTissueSpecimenClient
     * @throws IntegrationException - IntegrationException
     */
    @Bean
    public CaTissueSpecimenClient caTissueSpecimenClient() throws IntegrationException {
        return new CaTissueSpecimenClient(caTissueLibLocation, catissueApiLoginName, catissueApiPassword);
    }

    /**
     * To get CaTissueConsentClient
     * 
     * @return CaTissueConsentClient
     * @throws IntegrationException - IntegrationException
     */
    @Bean
    public CaTissueConsentClient caTissueConsentClient() throws IntegrationException {
        return new CaTissueConsentClient(caTissueLibLocation, catissueApiLoginName, catissueApiPassword);
    }

}

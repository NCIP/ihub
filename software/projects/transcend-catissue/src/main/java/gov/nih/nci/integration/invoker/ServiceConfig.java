package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.catissue.CaTissueConsentClient;
import gov.nih.nci.integration.catissue.CaTissueParticipantClient;
import gov.nih.nci.integration.catissue.CaTissueSpecimenClient;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.transformer.XSLTTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ServiceConfig {

    @Value("${catissue.api.service.retry}")
    private String retryCntStr;

    @Value("${integration.transformer.xsl.baseClassPath}")
    private String baseXSLPath;

    @Value("${catissue.api.participant.xsl}")
    private String catissueParticipantXsl;

    @Value("${catissue.api.specimen.xsl}")
    private String catissueSpecimenXsl;

    @Value("${catissue.api.consent.xsl}")
    private String catissueConsentXsl;

    @Autowired
    private CaTissueParticipantClient caTissueParticipantClient;

    @Autowired
    private CaTissueSpecimenClient caTissueSpecimenClient;

    @Autowired
    private CaTissueConsentClient caTissueConsentClient;

    @Autowired
    private XSLTTransformer xsltTransformer;

    @Autowired
    private XSLTTransformer xsltTransformerSpecimen;

    @Autowired
    private XSLTTransformer xsltTransformerConsent;

    @Bean
    @Scope("prototype")
    public ServiceInvocationStrategy caTissueRegistrationServiceInvocationStrategy() throws IntegrationException {
        xsltTransformer.initTransformer(catissueParticipantXsl, baseXSLPath);
        return new CaTissueRegistrationServiceInvocationStrategy(Integer.parseInt(retryCntStr),
                caTissueParticipantClient, xsltTransformer);
    }

    @Bean
    @Scope("prototype")
    public ServiceInvocationStrategy caTissueUpdateRegistrationServiceInvocationStrategy() throws IntegrationException {
        xsltTransformer.initTransformer(catissueParticipantXsl, baseXSLPath);
        return new CaTissueUpdateRegistrationServiceInvocationStrategy(Integer.parseInt(retryCntStr),
                caTissueParticipantClient, xsltTransformer);
    }

    @Bean
    @Scope("prototype")
    public ServiceInvocationStrategy caTissueSpecimenServiceInvocationStrategy() throws IntegrationException {
        xsltTransformerSpecimen.initTransformer(catissueSpecimenXsl, baseXSLPath);
        return new CaTissueSpecimenServiceInvocationStrategy(Integer.parseInt(retryCntStr), caTissueSpecimenClient,
                xsltTransformerSpecimen);
    }

    @Bean
    @Scope("prototype")
    public ServiceInvocationStrategy caTissueUpdateSpecimenServiceInvocationStrategy() throws IntegrationException {
        xsltTransformerSpecimen.initTransformer(catissueSpecimenXsl, baseXSLPath);
        return new CaTissueUpdateSpecimenServiceInvocationStrategy(Integer.parseInt(retryCntStr),
                caTissueSpecimenClient, xsltTransformerSpecimen);
    }

    @Bean
    @Scope("prototype")
    public ServiceInvocationStrategy caTissueConsentServiceInvocationStrategy() throws IntegrationException {
        xsltTransformerConsent.initTransformer(catissueConsentXsl, baseXSLPath);
        return new CaTissueConsentServiceInvocationStrategy(Integer.parseInt(retryCntStr), caTissueConsentClient,
                xsltTransformerConsent);
    }
}

package gov.nih.nci.integration.caaers.invoker;

import gov.nih.nci.integration.caaers.CaAERSAdverseEventServiceWSClient;
import gov.nih.nci.integration.caaers.CaAERSParticipantServiceWSClient;
import gov.nih.nci.integration.caaers.ClientPasswordCallback;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.transformer.XSLTTransformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * This class is used to read the caAERS configurations
 * 
 * @author Vinodh
 * 
 */
@Configuration
public class CaAERSConfig {

    @Value("${caaers.client.user}")
    private String userName;

    @Value("${caaers.client.psswd}")
    private String password;

    @Value("${caaers.registration.service.url}")
    private String registrationServiceUrl;

    @Value("${caaers.adverseevent.service.url}")
    private String adverseEventServiceUrl;

    @Value("${caaers.retry.count}")
    private String retryCountStr;

    @Value("${integration.transformer.xsl.baseClassPath}")
    private String baseXSLPath;

    @Value("${caaers.participant.xsl}")
    private String caaersParticipantXsl;

    @Value("${caaers.adverseevent.xsl}")
    private String caaersAdverseEventXsl;

    @Autowired
    private XSLTTransformer xsltTransformerParticipant;

    @Autowired
    private XSLTTransformer xsltTransformerAdverseEvent;

    private static final Logger LOG = LoggerFactory.getLogger(CaAERSConfig.class);
    
    private static final String PROTOTYPE = "prototype";

    /**
     * To get clientPasswordCallback
     * 
     * @return ClientPasswordCallback object
     */
    @Bean
    public ClientPasswordCallback clientPasswordCallback() {
        return new ClientPasswordCallback(userName, password);
    }

    /**
     * To get CaAERSParticipantServiceWSClient
     * 
     * @return CaAERSParticipantServiceWSClient object
     * @throws IntegrationException - IntegrationException
     */
    @Bean
    public CaAERSParticipantServiceWSClient caAERSParticipantServiceWSClient() throws IntegrationException {
        return new CaAERSParticipantServiceWSClient(registrationServiceUrl + "?wsdl", userName,
                clientPasswordCallback());
    }

    /**
     * To get CaAERSAdverseEventServiceWSClient
     * 
     * @return CaAERSAdverseEventServiceWSClient
     * @throws IntegrationException - IntegrationException
     */
    @Bean
    public CaAERSAdverseEventServiceWSClient caAERSAdverseEventServiceWSClient() throws IntegrationException {
        return new CaAERSAdverseEventServiceWSClient(adverseEventServiceUrl + "?wsdl", userName,
                clientPasswordCallback());
    }

    /**
     * To get CaAERSRegistrationServiceInvocationStrategy
     * 
     * @return CaAERSRegistrationServiceInvocationStrategy object
     * @throws IntegrationException - IntegrationException
     */
    @Bean
    @Scope(PROTOTYPE)
    public CaAERSRegistrationServiceInvocationStrategy caAersRegistrationServiceInvocationStrategy()
            throws IntegrationException {

        try {
            xsltTransformerParticipant.initTransformer(caaersParticipantXsl, baseXSLPath);
            return new CaAERSRegistrationServiceInvocationStrategy(xsltTransformerParticipant,
                    caAERSParticipantServiceWSClient(), Integer.parseInt(retryCountStr));
        } catch (NumberFormatException e) {
            LOG.error("CaAERSConfig..NumberFormatException inside caAersRegistrationServiceInvocationStrategy. ", e);
            throw new IntegrationException(IntegrationError._1051, e, (Object) null);
        }
    }

    /**
     * To get CaAERSUpdateRegistrationServiceInvocationStrategy
     * 
     * @return CaAERSUpdateRegistrationServiceInvocationStrategy obejct
     * @throws IntegrationException - IntegrationException
     */
    @Bean
    @Scope(PROTOTYPE)
    public CaAERSUpdateRegistrationServiceInvocationStrategy caAersUpdateRegistrationServiceInvocationStrategy()
            throws IntegrationException {

        try {
            xsltTransformerParticipant.initTransformer(caaersParticipantXsl, baseXSLPath);
            return new CaAERSUpdateRegistrationServiceInvocationStrategy(xsltTransformerParticipant,
                    caAERSParticipantServiceWSClient(), Integer.parseInt(retryCountStr));
        } catch (NumberFormatException e) {
            LOG.error("NumberFormatException inside caAersUpdateRegistrationServiceInvocationStrategy. ", e);
            throw new IntegrationException(IntegrationError._1051, e, (Object) null);
        }
    }

    /**
     * To get CaAERSAdverseEventServiceInvocationStrategy
     * 
     * @return CaAERSAdverseEventServiceInvocationStrategy object
     * @throws IntegrationException - IntegrationException
     */
    @Bean
    @Scope(PROTOTYPE)
    public CaAERSAdverseEventServiceInvocationStrategy caAersAdverseEventServiceInvocationStrategy()
            throws IntegrationException {

        try {
            xsltTransformerAdverseEvent.initTransformer(caaersAdverseEventXsl, baseXSLPath);
            return new CaAERSAdverseEventServiceInvocationStrategy(xsltTransformerAdverseEvent,
                    caAERSAdverseEventServiceWSClient(), Integer.parseInt(retryCountStr));
        } catch (NumberFormatException e) {
            LOG.error("NumberFormatException inside caAersAdverseEventServiceInvocationStrategy. ", e);
            throw new IntegrationException(IntegrationError._1051, e, (Object) null);
        }
    }

  }

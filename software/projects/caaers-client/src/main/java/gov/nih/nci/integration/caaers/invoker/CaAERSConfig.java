package gov.nih.nci.integration.caaers.invoker;

import javax.xml.bind.JAXBException;

import gov.nih.nci.integration.caaers.CaAERSParticipantServiceWSClient;
import gov.nih.nci.integration.caaers.ClientPasswordCallback;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.transformer.XSLTTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CaAERSConfig {

	@Value("${caaers.client.user}")
	private String userName;

	@Value("${caaers.client.psswd}")
	private String password;

	@Value("${caaers.registration.service.url}")
	private String serviceUrl;

	@Value("${caaers.retry.count}")
	private String retryCountStr;

	@Value("${integration.transformer.xsl.baseClassPath}")
	private String baseXSLPath;

	@Value("${caaers.participant.xsl}")
	private String caaersParticipantXsl;

	@Autowired
	private XSLTTransformer xsltTransformer;

	@Bean
	public ClientPasswordCallback clientPasswordCallback() {
		return new ClientPasswordCallback(userName, password);
	}

	@Bean
	public CaAERSParticipantServiceWSClient caAERSParticipantServiceWSClient()
			throws JAXBException {
		return new CaAERSParticipantServiceWSClient(serviceUrl + "?wsdl",
				userName, clientPasswordCallback());
	}

	@Bean
	@Scope("prototype")
	public CaAERSRegistrationServiceInvocationStrategy caAersRegistrationServiceInvocationStrategy()
			throws IntegrationException {
		
		try {
			xsltTransformer.initTransformer(caaersParticipantXsl, baseXSLPath);
			System.out.println(xsltTransformer);
			return new CaAERSRegistrationServiceInvocationStrategy(xsltTransformer,
					caAERSParticipantServiceWSClient(), Integer
							.parseInt(retryCountStr));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new IntegrationException(IntegrationError._1051, e, null);
		} catch (JAXBException e) {
			throw new IntegrationException(IntegrationError._1051, e, null);
		}
	}
	
	@Bean
	@Scope("prototype")
	public CaAERSUpdateRegistrationServiceInvocationStrategy caAersUpdateRegistrationServiceInvocationStrategy()
			throws IntegrationException {
		
		try {
			xsltTransformer.initTransformer(caaersParticipantXsl, baseXSLPath);
			System.out.println(xsltTransformer);
			return new CaAERSUpdateRegistrationServiceInvocationStrategy(xsltTransformer,
					caAERSParticipantServiceWSClient(), Integer
							.parseInt(retryCountStr));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new IntegrationException(IntegrationError._1051, e, null);
		} catch (JAXBException e) {
			throw new IntegrationException(IntegrationError._1051, e, null);
		}
	}
}

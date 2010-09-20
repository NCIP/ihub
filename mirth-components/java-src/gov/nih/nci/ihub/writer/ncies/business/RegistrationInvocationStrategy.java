package gov.nih.nci.ihub.writer.ncies.business;

import gov.nih.nci.cabig.ccts.domain.Registration;
import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.ccts.grid.client.RegistrationConsumerClient;
import gov.nih.nci.ihub.util.IntegrationHubUtil;
import gov.nih.nci.ihub.writer.ncies.exception.GridInvocationException;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.ConnectException;
import java.util.HashSet;
import java.util.Set;

import javax.security.auth.Subject;
import javax.xml.namespace.QName;

import org.apache.axis.AxisFault;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;
import org.apache.servicemix.jbi.jaxp.StringSource;
import org.globus.gsi.GlobusCredential;
import org.w3c.dom.Document;

public class RegistrationInvocationStrategy {

	public static Logger logger = LogManager
			.getLogger(RegistrationInvocationStrategy.class);

	private Subject subject;
	private String serviceURL;
	private String payload;

	public RegistrationInvocationStrategy(Subject subject, String serviceURL,
			String payload) {
		super();
		this.subject = subject;
		this.serviceURL = serviceURL;
		this.payload = payload;
	}

	public GridInvocationResult invokeGridService(boolean isRollback)
			throws GridInvocationException {
		try {
			payload = payload.replace('@', '"');
			System.out.println("Payload: " + payload);
			GlobusCredential globusCredential = null;
			Set<GlobusCredential> globusCredentialsSet = new HashSet<GlobusCredential>();
			if (subject != null) {
				globusCredentialsSet = subject
						.getPrivateCredentials(GlobusCredential.class);
			}

			if (globusCredentialsSet.size() > 0) {
				globusCredential = globusCredentialsSet.iterator().next();
				System.out
						.println("########### CREDENTIAL IN REGISTRATION STRATEGY ########: "
								+ globusCredential.toString());
			} else {
				throw new GridInvocationException("no credentials found");
			}

			RegistrationConsumerClient client = null;
			if (globusCredential != null) {

				client = new RegistrationConsumerClient(serviceURL,
						globusCredential);

			} else {
				client = new RegistrationConsumerClient(serviceURL);
			}

			InputStream deseralizeStream = client.getClass().getClassLoader()
					.getResourceAsStream("client-config.wsdd");

			// get payload from business payload step can be removed if mirth variable 
			// parsing with inner default namespaces issue is resolved
			Registration request = (Registration) Utils.deserializeObject(
					new StringReader(IntegrationHubUtil
							.getPayloadFromBusinessPayload(payload)),
					Registration.class, deseralizeStream);

			if (isRollback) {
				client.rollback(request);
				System.out.println("Registeration ROLLBACK called");
				return new GridInvocationResult(false, null, false);
			} else {
				Registration reply = client.register(request);
				InputStream serializeStream = client.getClass()
						.getResourceAsStream("client-config.wsdd");
				StringWriter writer = new StringWriter();
				Utils.serializeObject(reply, new QName(
						"gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain",
						"registration"), writer, serializeStream);
				String response = writer.getBuffer().toString();
				final Document resp = new SourceTransformer()
						.toDOMDocument(new StringSource(response));
				System.out.println("Registeration Response root tag: "
						+ resp.getDocumentElement().getNodeName());
				return new GridInvocationResult(false, resp
						.getDocumentElement(), false);
			}
		} catch (AxisFault af) {
			logger.error("Failed to invoke registration service.", af);
			if (af.getCause() instanceof ConnectException) {
				return new GridInvocationResult(true, null, true, af);
			} else {
				return new GridInvocationResult(true, null, false, af);
			}

		} catch (Throwable e) {
			logger.error("Failed to invoke registration service.", e);
			throw new GridInvocationException(e.getMessage(), e);
		}
	}

}

package gov.nih.nci.cacis.sa.transcend.client;

import gov.nih.nci.cacis.sa.transcend.AcceptSourcePortType;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by Apache CXF 2.4.1 2012-04-17T17:24:21.899-04:00
 * Generated source version: 2.4.1
 * 
 */
@WebServiceClient(name = "SemanticAdapter", wsdlLocation = "file:/C:/vin/SUITE-iHub/software/projects/transcend-sa/src/main/resources/wsdl/SemanticAdapter.wsdl", targetNamespace = "http://cacis.nci.nih.gov")
public class TranscendSemanticAdapterService extends Service {

	public final static URL WSDL_LOCATION;

	public final static QName SERVICE = new QName("http://cacis.nci.nih.gov",
			"TranscendSemanticAdapter");
	public final static QName AcceptSourcePortSoap11 = new QName(
			"http://cacis.nci.nih.gov", "AcceptSource_Port_Soap11");
	static {
		URL url = null;
		try {
			url = new URL(
					"file:/C:/vin/SUITE-iHub/software/projects/transcend-sa/src/main/resources/wsdl/TranscendSemanticAdapter.wsdl");
		} catch (MalformedURLException e) {
			java.util.logging.Logger
					.getLogger(TranscendSemanticAdapterService.class.getName())
					.log(
							java.util.logging.Level.INFO,
							"Can not initialize the default wsdl from {0}",
							"file:/C:/vin/SUITE-iHub/software/projects/transcend-sa/src/main/resources/wsdl/TranscendSemanticAdapter.wsdl");
		}
		WSDL_LOCATION = url;
	}

	public TranscendSemanticAdapterService(URL wsdlLocation) {
		super(wsdlLocation, SERVICE);
	}

	public TranscendSemanticAdapterService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public TranscendSemanticAdapterService() {
		super(WSDL_LOCATION, SERVICE);
	}

	/**
	 * 
	 * @return returns AcceptSourcePortType
	 */
	@WebEndpoint(name = "AcceptSource_Port_Soap11")
	public AcceptSourcePortType getAcceptSourcePortSoap11() {
		return super
				.getPort(AcceptSourcePortSoap11, AcceptSourcePortType.class);
	}

	/**
	 * 
	 * @param features
	 *            A list of {@link javax.xml.ws.WebServiceFeature} to configure
	 *            on the proxy. Supported features not in the
	 *            <code>features</code> parameter will have their default
	 *            values.
	 * @return returns AcceptSourcePortType
	 */
	@WebEndpoint(name = "AcceptSource_Port_Soap11")
	public AcceptSourcePortType getAcceptSourcePortSoap11(
			WebServiceFeature... features) {
		return super.getPort(AcceptSourcePortSoap11,
				AcceptSourcePortType.class, features);
	}

}

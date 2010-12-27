package gov.nih.nci.ihub.writer.ncies.core;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.ihub.writer.ncies.common.GenericInvocationStrategy;
import gov.nih.nci.ihub.writer.ncies.common.GridInvocationResult;
import gov.nih.nci.ihub.writer.ncies.common.GridMessage;
import gov.nih.nci.ihub.writer.ncies.exception.GridInvocationException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.configuration.FileProvider;
import org.apache.axis.encoding.TypeMapping;
import org.apache.axis.server.AxisServer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;
import org.globus.gsi.GlobusCredential;
import org.globus.wsrf.encoding.DeserializationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CoppaInvocationStrategy extends GenericInvocationStrategy {

	private static Map<String, TypeMapping> typeMappings = new HashMap<String, TypeMapping>(
			1);
	protected Class[] requestPayloadClassses;

	private static Logger logger = LogManager
			.getLogger(CoppaInvocationStrategy.class);

	public void init() {
		try {
			TypeMapping typeMapping = typeMappings
					.get(getGridClientClassName());
			if (typeMapping == null) {
				InputStream deseralizeStream = Class.forName(
						getGridClientClassName()).getResourceAsStream(
						"client-config.wsdd");
				org.apache.axis.EngineConfiguration engineConfig = new FileProvider(
						deseralizeStream);
				org.apache.axis.AxisEngine axisClient = new AxisServer(
						engineConfig);
				MessageContext messageContext = new MessageContext(axisClient);
				typeMapping = (TypeMapping) messageContext
						.getTypeMappingRegistry().getTypeMapping("");
				typeMappings.put(getGridClientClassName(), typeMapping);
			}
		} catch (Exception e) {
			logger.error("Error initializing coppa invocation bean.", e);
			throw new IllegalStateException(
					"Error initializing coppa invocation bean.", e);
		}

	}

	public GridInvocationResult invokeGridService(boolean isRollback)
			throws GridInvocationException {
		try {
			useCredentials = true;
			GlobusCredential cred = null;

			Set<GlobusCredential> globusCredentials = new HashSet<GlobusCredential>();
			if (subject != null) {
				globusCredentials = subject
						.getPrivateCredentials(GlobusCredential.class);
			}

			if (globusCredentials.size() > 0) {
				cred = globusCredentials.iterator().next();
			} else {
				throw new GridInvocationException("no credentials found");
			}

			Object client = getNewGridClient(serviceUrl, cred);
			requestPayloadClassses = getRequestPayloadClass(client);
			QName typeDesc = getReturnTypeDescription(client);
			if (typeDesc != null) {
				returnTypeNameSpace = typeDesc.getNamespaceURI();
				returnTypeElement = typeDesc.getLocalPart();
			}
			Object[] requestPayloads = getRequestPayload(client);
			logger.info("Invoking grid operation:" + new Date());
			Object returnObject = invokeGridOperation(client, requestPayloads);
			logger.info("After Invoking grid operation:" + new Date());
			// commented to remove commit
			// client.commit(request);
			GridInvocationResult result = null;
			if ((typeDesc != null) && (typeDesc.getNamespaceURI() != null)
					&& (typeDesc.getLocalPart() != null)) {
				QName returnType = new QName(typeDesc.getNamespaceURI(),
						typeDesc.getLocalPart());
				result = getServiceResponsePayload(client, returnObject,
						returnType);
			} else {
				result = getSuccessResult();
			}
			return result;
		} catch (AxisFault af) {
			logger.error("Failed to invoke registration service.", af);
			if (af.getCause() instanceof ConnectException) {
				return new GridInvocationResult(true, null, true, af);
			} else {
				return new GridInvocationResult(true, null, false, af);
			}
		} catch (Exception e) {
			logger.error("Failed to invoke grid service." + serviceUrl, e);
			return new GridInvocationResult(true, null, false, e);
		}
	}

	public Class[] getRequestPayloadClass(Object client)
			throws GridInvocationException {
		// String operationName = message.getOperationName();
		if ("".equals(operationName)) {
			throw new GridInvocationException(
					"No operation name specified for COPPA service:"
							+ serviceType);
		}
		Method invocationMethod = getClientMethod(client);
		Class[] parameterTypes = invocationMethod.getParameterTypes();
		logger.debug("Request payload class name:"
				+ parameterTypes[0].getName());
		return parameterTypes;

	}

	public Method getClientMethod(Object client) throws GridInvocationException {
		try {
			// String operationName = message.getOperationName();
			if ("".equals(operationName)) {
				throw new GridInvocationException(
						"No operation name specified for COPPA service:"
								+ serviceType);
			}
			Method[] methods = client.getClass().getMethods();
			Method invocationMethod = null;
			for (Method method : methods) {
				if (method.getName().equals(operationName)) {
					invocationMethod = method;
					break;
				}
			}
			if (invocationMethod == null) {
				throw new GridInvocationException("Not found operation name "
						+ operationName + " specified for COPPA service:"
						+ serviceType);
			}
			return invocationMethod;
		} catch (Exception e) {
			logger.error("Error occurred finding the client method.", e);
			throw new GridInvocationException(
					"Error occurred finding the client method.", e);
		}
	}

	public QName getReturnTypeDescription(Object client)
			throws GridInvocationException {
		try {
			Method invocationMethod = getClientMethod(client);
			Class returnType = invocationMethod.getReturnType();
			TypeMapping typeMapping = typeMappings
					.get(getGridClientClassName());
			QName typeDesc = typeMapping.getTypeQName(returnType);
			return typeDesc;
		} catch (Exception e) {
			logger.error("Error occurred finding the return type.", e);
			throw new GridInvocationException(
					"Error occurred finding the return type.", e);
		}
	}

	public Object[] getRequestPayload(Object client)
			throws GridInvocationException {
		try {
			Object[] requestPayloads = new Object[requestPayloadClassses.length];
			int parameterindex = 0;
			SourceTransformer transformer = new SourceTransformer();
			InputStream deseralizeStream = client.getClass()
					.getResourceAsStream("client-config.wsdd");
			ByteArrayInputStream bais = null;
			byte[] arr = new byte[1024];
			StringBuffer clientConfig = new StringBuffer();
			int bytesRead = deseralizeStream.read(arr);
			while (bytesRead > 0) {
				clientConfig.append(new String(arr, 0, bytesRead));
				bytesRead = deseralizeStream.read(arr);
			}
			byte[] clientConfigAsBytes = clientConfig.toString().getBytes();
			bais = new ByteArrayInputStream(clientConfigAsBytes);
			List<Element> payloads = getPayloads();
			//if (logger.isDebugEnabled()) {
				if (payloads == null) {
					logger.debug("Payload Elements list is null.");
				} else {
					logger.debug("Payload Elements size:" + payloads.size());
					Iterator<Element> payloadIterator = payloads.iterator();
					for (Element payloadElement; payloadIterator.hasNext();) {
						payloadElement = payloadIterator.next();
						logger.debug("Payload Element:"
								+ transformer.toString(payloadElement));
					}
				}
			//}
			int payloadsIndex = 0;
			for (Class requestPayloadClass : requestPayloadClassses) {
				if (payloadsIndex >= payloads.size()) {
					break;
				}
				bais = new ByteArrayInputStream(clientConfigAsBytes);
				Object requestPayload = null;
				if (requestPayloadClass.isArray()) {
					ArrayList<Object> paramPayloads = new ArrayList<Object>(
							payloads.size());
					Element arrayElement = payloads.get(payloadsIndex);
					logger.debug("The array payload is:"
							+ transformer.toString(arrayElement));
					logger.debug("ArrayElement Local Name: "+arrayElement.getNodeName());
					if ("Array".equals(arrayElement.getNodeName())) {
						// The element is an array.
						logger.debug("Looking for nodes:"
								+ requestPayloadClass.getSimpleName());
						NodeList nodeList = arrayElement.getChildNodes();
						if (nodeList != null) {
							requestPayload = Array.newInstance(
									requestPayloadClass.getComponentType(),
									nodeList.getLength());
							for (int k = 0; k < nodeList.getLength(); k++) {
								Node node = nodeList.item(k);
								logger.debug("Simple Name:"
										+ requestPayloadClass.getSimpleName()
										+ " local:" + node.getNodeName());
								if ((node.getNodeName() != null)
										&& (requestPayloadClass.getSimpleName()
												.startsWith(node.getNodeName()))) {
									String payload = transformer.toString(node);
									logger.debug("The message payload is:"
											+ payload);
									StringReader reader = new StringReader(
											payload);
									Object obj = Utils.deserializeObject(
											reader, requestPayloadClass, bais);
									Array.set(requestPayload, k, obj);
									bais = new ByteArrayInputStream(
											clientConfigAsBytes);
								}
							}
						} else {
							logger.debug("No nodes found setting empty array.");
							requestPayload = Array.newInstance(
									requestPayloadClass.getComponentType(), 0);
						}
						payloadsIndex++;
					} else {
						// Process a non array payload
						int i = 0;
						while ((payloadsIndex < payloads.size())) {
							Element element = payloads.get(payloadsIndex++);
							logger.debug("Array: Class:"
									+ requestPayloadClass.getName() + "SN:"
									+ requestPayloadClass.getSimpleName()
									+ " Element:" + element.getNodeName() + " "
									+ element.getNodeName());
							if (requestPayloadClass.getSimpleName().startsWith(
									element.getNodeName())) {
								String payload = transformer.toString(element);
								logger.debug("The message payload is:"
										+ payload);
								StringReader reader = new StringReader(payload);
								Object obj = Utils.deserializeObject(reader,
										requestPayloadClass, bais);
								paramPayloads.add(obj);
								i++;
								bais = new ByteArrayInputStream(
										clientConfigAsBytes);
							} else {
								payloadsIndex--;
								break;
							}
						}
						requestPayload = Array.newInstance(requestPayloadClass
								.getComponentType(), i);
						for (int index = 0; index < i; index++) {
							Array.set(requestPayload, index, paramPayloads
									.get(index));
						}
					}
				} else {
					Element element = payloads.get(payloadsIndex++);
					logger.debug("Class:" + requestPayloadClass.getName()
							+ " Element:" + element.getNodeName() + " "
							+ element.getNodeName());
					String payload = transformer.toString(element);
					logger.debug("The message payload is:" + payload);
					StringReader reader = new StringReader(payload);
					requestPayload = Utils.deserializeObject(reader,
							requestPayloadClass, bais);
				}

				requestPayloads[parameterindex++] = requestPayload;
			}
			return requestPayloads;
		} catch (IOException ie) {
			logger.error("Error reading client-config.wsdd.", ie);
			throw new GridInvocationException(
					"Error reading client-config.wsdd.", ie);
		} catch (TransformerException te) {
			logger.error("Error transforming payload to string.", te);
			throw new GridInvocationException(
					"Error transforming payload to string.", te);
		} catch (SAXException se) {
			logger.error("Error parsing request payload .", se);
			throw new GridInvocationException("Error parsing request payload.",
					se);
		} catch (DeserializationException de) {
			logger.error("Error parsing request payload .", de);
			throw new GridInvocationException("Error parsing request payload.",
					de);
		}
	}

	public Object invokeGridOperation(Object client, Object[] requestPayload)
			throws GridInvocationException {		
		try {
			Method gridOperation = getClientMethod(client);
			logger.debug("Operation Name:" + operationName);
			Object returnObject = gridOperation.invoke(client, requestPayload);
			return returnObject;
		} catch (IllegalAccessException iae) {
			logger.error("Illegal access to " + operationName, iae);
			throw new GridInvocationException("Illegal access to "
					+ operationName, iae);
		} catch (InvocationTargetException ite) {
			logger.error("Exception invoking " + operationName, ite);
			Throwable cause = ite.getCause();
			String errorDetails = ite.getMessage() + ":"
					+ ((cause == null) ? "" : cause.getMessage());
			throw new GridInvocationException("Exception invoking "
					+ operationName + " on " + gridClientClassName + "."
					+ errorDetails, ite);
		}
	}
}

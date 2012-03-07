package gov.nih.nci.integration.catissue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.ApplicationServiceProvider;
import gov.nih.nci.system.comm.client.ClientSession;


/**
 * CaTissueClient bean class to communicate with caTissue
 * 
 * @author vinodh.rc
 */
public abstract class CaTissueAPIClientBean implements CaTissueClient {

	protected static final ApplicationService appServiceCatissue = ApplicationServiceProvider
			.getRemoteInstance();
	protected static ClientSession cs;
	protected Logger logger = LoggerFactory.getLogger(CaTissueAPIClientBean.class);
	protected String userName;
	protected String password;

	/**
	 * Starts a caTissue client session
	 * 
	 * @author syed added on 8/5/09
	 * @throws ApplicationException
	 */
	public void startSession() throws ApplicationException {
		try{
			cs = ClientSession.getInstance();
			cs.startSession(userName, password);
		}catch(Exception e){
			throw new ApplicationException(e);
		}
	}

	/**
	 * Terminates a caTissue client session
	 * 
	 */
	public void terminateSession() {
		cs.terminateSession();
	}
	
	public void createObject(String xmlStr, String operationName) throws InvokeCaTissueException, PayloadParseException,Exception {
		try {
			startSession();
            createObjectImpl(xmlStr,  operationName);
		}catch(ApplicationException aE){
			logger.error("Error while creating caTissue client session", aE);
			aE.printStackTrace();
			throw aE;
		}catch(Exception e){
			logger.error("Error while creating caTissue object", e);
			e.printStackTrace();
			throw e;
		}
		finally {
			terminateSession();
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public abstract void createObjectImpl(String xmlStr, String operationName) throws InvokeCaTissueException, PayloadParseException, Exception; 
}

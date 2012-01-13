package gov.nih.nci.ihub.writer.ncies.entity.catissue;

import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.ApplicationServiceProvider;
import gov.nih.nci.system.comm.client.ClientSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Node;

/**
 * CaTissueClient bean class to communicate with caTissue
 * 
 * @author syed added on 8/5/09 modified on 8/12/09
 */
public abstract class CaTissueAPIClientBean implements CaTissueClient {

	protected static final ApplicationService appServiceCatissue = ApplicationServiceProvider
			.getRemoteInstance();
	protected static ClientSession cs;
	protected Logger logger = LogManager.getLogger(CaTissueAPIClientBean.class);
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
	 * @author syed added on 8/5/09
	 */
	public void terminateSession() {
		cs.terminateSession();
	}
	
	public void createObject(Node node, String operationName) throws InvokeCaTissueException, PayloadParseException,Exception {
		try {
			startSession();
            createObjectImpl(node,  operationName);
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


	public abstract void createObjectImpl(Node node, String operationName) throws InvokeCaTissueException, PayloadParseException, Exception; 
}

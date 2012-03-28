package gov.nih.nci.integration.catissue;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.wustl.catissuecore.cacore.CaTissueWritableAppService;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.ApplicationServiceProvider;
import gov.nih.nci.system.query.SDKQuery;
import gov.nih.nci.system.query.SDKQueryResult;
import gov.nih.nci.system.query.example.InsertExampleQuery;
import gov.nih.nci.system.query.example.UpdateExampleQuery;

/**
 * 
 * @author chandrasekaravr
 * 
 */
public class CaTissueAPIClientWithRegularAuthentication {

	protected String loginName;
	protected String password;

	private static Logger LOG = LoggerFactory
			.getLogger(CaTissueAPIClientWithRegularAuthentication.class);

	public CaTissueAPIClientWithRegularAuthentication(String loginName,
			String password) {
		super();
		this.loginName = loginName;
		this.password = password;
	}

	public final CaTissueWritableAppService getApplicationService()
			throws ApplicationException {		
		try {
			return getAppService(loginName, password);
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	public <T> T insert(T object) throws ApplicationException {
		SDKQuery query = new InsertExampleQuery(object);
		SDKQueryResult result = getApplicationService().executeQuery(query);
		return (T) result.getObjectResult();
	}

	public <T> T update(T object) throws ApplicationException {
		SDKQuery query = new UpdateExampleQuery(object);
		SDKQueryResult result = getApplicationService().executeQuery(query);
		return (T) result.getObjectResult();
	}

	public <T> List<T> searchByExample(Class<T> klass, T example)
			throws ApplicationException {
		return getApplicationService().search(klass, example);
	}

	public <T> T searchById(Class<T> klass, T example)
			throws ApplicationException {
		List<T> result = getApplicationService().search(klass, example);
		if (result != null && result.size() > 0)
			return result.get(0);
		else
			return null;
	}

	final protected CaTissueWritableAppService getAppService(String username,
			String password) throws Exception {
		return (CaTissueWritableAppService) ApplicationServiceProvider
				.getApplicationService(username, password);
	}
}

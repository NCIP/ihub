package gov.nih.nci.integration.catissue.client;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.BeansException;

import edu.wustl.catissuecore.cacore.CaTissueWritableAppService;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.ApplicationServiceProvider;
import gov.nih.nci.system.query.SDKQuery;
import gov.nih.nci.system.query.SDKQueryResult;
import gov.nih.nci.system.query.example.DeleteExampleQuery;
import gov.nih.nci.system.query.example.InsertExampleQuery;
import gov.nih.nci.system.query.example.UpdateExampleQuery;
import gov.nih.nci.system.query.hql.DeleteHQLQuery;

/**
 * 
 * @author chandrasekaravr
 * 
 */
public class CaTissueAPIClientWithRegularAuthentication {

	protected String loginName;
	protected String password;

	public CaTissueAPIClientWithRegularAuthentication(String loginName,
			String password) throws BeansException, MalformedURLException {
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
	
	public <T> T delete(T object) throws ApplicationException {
		SDKQuery query = new DeleteExampleQuery(object);		
		SDKQueryResult result = getApplicationService().executeQuery(query);
		return (T) result.getObjectResult();
	}
	
	public void delete(String hql) throws ApplicationException {
		SDKQuery query = new DeleteHQLQuery(hql);		
		SDKQueryResult result = getApplicationService().executeQuery(query);		
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

package gov.nih.nci.integration.catissue.client;

import edu.wustl.catissuecore.cacore.CaTissueWritableAppService;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.ApplicationServiceProvider;
import gov.nih.nci.system.query.SDKQuery;
import gov.nih.nci.system.query.SDKQueryResult;
import gov.nih.nci.system.query.example.DeleteExampleQuery;
import gov.nih.nci.system.query.example.InsertExampleQuery;
import gov.nih.nci.system.query.example.UpdateExampleQuery;
import gov.nih.nci.system.query.hql.DeleteHQLQuery;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.BeansException;

/**
 * CaTissueAPIClient
 * 
 * @author chandrasekaravr
 * 
 */
@SuppressWarnings( { "PMD.AvoidCatchingGenericException","PMD.SignatureDeclareThrowsException" } )
public class CaTissueAPIClientWithRegularAuthentication {

    private final String loginName;
    private final String password;

    /**
     * Constructor
     * 
     * @param loginName - loginName
     * @param password - password
     * @throws BeansException - BeansException
     * @throws MalformedURLException - MalformedURLException
     */
    public CaTissueAPIClientWithRegularAuthentication(String loginName, String password) throws BeansException,
            MalformedURLException {
        super();
        this.loginName = loginName;
        this.password = password;

    }

    /**
     * To get ApplicationService
     * 
     * @return CaTissueWritableAppService
     * @throws ApplicationException - ApplicationException
     */
    public final CaTissueWritableAppService getApplicationService() throws ApplicationException {
        try {
            return getAppService(loginName, password);
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            throw new ApplicationException(e);
        }
    }

    /**
     * To insert object into database
     * 
     * @param object - object to be inserted
     * @param <T> - <T>
     * @return returned object
     * @throws ApplicationException - ApplicationException
     */
    public <T> T insert(T object) throws ApplicationException {
        final SDKQuery query = new InsertExampleQuery(object);
        final SDKQueryResult result = getApplicationService().executeQuery(query);
        return (T) result.getObjectResult();
    }

    /**
     * To update object into database
     * 
     * @param object - object to be updated
     * @param <T> - <T>
     * @return returned object
     * @throws ApplicationException - ApplicationException
     */
    public <T> T update(T object) throws ApplicationException {
        final SDKQuery query = new UpdateExampleQuery(object);
        final SDKQueryResult result = getApplicationService().executeQuery(query);
        return (T) result.getObjectResult();
    }

    /**
     * To delete object from database
     * 
     * @param object - object to be deleted
     * @param <T> - <T>
     * @return returned object
     * @throws ApplicationException - ApplicationException
     */
    public <T> T delete(T object) throws ApplicationException {
        final SDKQuery query = new DeleteExampleQuery(object);
        final SDKQueryResult result = getApplicationService().executeQuery(query);
        return (T) result.getObjectResult();
    }

    /**
     * To delete object from database
     * 
     * @param hql - hql query
     * @throws ApplicationException - ApplicationException
     */
    public void delete(String hql) throws ApplicationException {
        final SDKQuery query = new DeleteHQLQuery(hql);
        getApplicationService().executeQuery(query);
    }

    /**
     * To search object by example
     * 
     * @param klass - class
     * @param example - object by example
     * @param <T> - <T>
     * @return - list of matched objects
     * @throws ApplicationException - ApplicationException
     */
    public <T> List<T> searchByExample(Class<T> klass, T example) throws ApplicationException {
        return getApplicationService().search(klass, example);
    }

    /**
     * To search object by ID
     * 
     * @param klass - class
     * @param example - object by Od
     * @param <T> - <T>
     * @return - list of matched objects
     * @throws ApplicationException - ApplicationException
     */
    public <T> T searchById(Class<T> klass, T example) throws ApplicationException {
        final List<T> result = getApplicationService().search(klass, example);
        if (result != null && result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }

    }

    // CHECKSTYLE:OFF
    final protected CaTissueWritableAppService getAppService(String username, String password) throws Exception {
        return (CaTissueWritableAppService) ApplicationServiceProvider.getApplicationService(username, password);
    }
    // CHECKSTYLE:ON
}

package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;

/**
 * Represents the logic necessary to invoke a single operation of a service AND also rollback the effects of that
 * operation. Implementations of this interface may contain logic to invoke more than one operation. 
 * @author Vinodh
 * 
 */
public interface ServiceInvocationStrategy {

    /**
     * getStrategyIdentifier
     * 
     * @return Strategy Identifier
     */
    StrategyIdentifier getStrategyIdentifier();

    /**
     * getRetryCount
     * 
     * @return Retry Count
     */
    int getRetryCount();

    /**
     * invoke
     * 
     * @param serviceInvocationMessage - serviceInvocationMessage
     * @return ServiceInvocationResult
     */
    ServiceInvocationResult invoke(ServiceInvocationMessage serviceInvocationMessage);

    /**
     * rollback
     * 
     * @param serviceInvocationMessage - ServiceInvocationMessage
     * @return ServiceInvocationResult containing rollback info
     */
    ServiceInvocationResult rollback(ServiceInvocationMessage serviceInvocationMessage);

}

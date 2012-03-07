package gov.nih.nci.integration.invoker;

public interface ServiceInvocationStrategy {
	
	ServiceInvocationResult invoke();
	ServiceInvocationResult rollback();

}

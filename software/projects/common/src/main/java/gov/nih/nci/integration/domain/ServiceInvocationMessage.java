package gov.nih.nci.integration.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ServiceInvocationMessage extends AbstractIdentity {
	
	String invocationException;
	String strategyIdentifier;
	
	IHubMessage message;
	
	public String getInvocationException() {
		return invocationException;
	}

	public void setInvocationException(String invocationException) {
		this.invocationException = invocationException;
	}
	
	@NotNull
	public String getStrategyIdentifier() {
		return strategyIdentifier;
	}

	public void setStrategyIdentifier(String strategyIdentifier) {
		this.strategyIdentifier = strategyIdentifier;
	}
	
	@ManyToOne
	@NotNull
	public IHubMessage getMessage() {
		return message;
	}

	public void setMessage(IHubMessage message) {
		this.message = message;
	}
	
	
}

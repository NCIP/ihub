package gov.nih.nci.integration.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ServiceInvocationMessage extends AbstractIdentity {

	Long referenceMessageId;
	String invocationException;
	StrategyIdentifier strategyIdentifier;

	IHubMessage message;

	@NotNull
	public Long getReferenceMessageId() {
		return referenceMessageId;
	}

	public void setReferenceMessageId(Long referenceMessageId) {
		this.referenceMessageId = referenceMessageId;
	}

	public String getInvocationException() {
		return invocationException;
	}

	public void setInvocationException(String invocationException) {
		this.invocationException = invocationException;
	}

	@Enumerated
	@NotNull
	public StrategyIdentifier getStrategyIdentifier() {
		return strategyIdentifier;
	}

	public void setStrategyIdentifier(StrategyIdentifier strategyIdentifier) {
		this.strategyIdentifier = strategyIdentifier;
	}

	@OneToOne(cascade=CascadeType.PERSIST)
	@NotNull
	public IHubMessage getMessage() {
		return message;
	}

	public void setMessage(IHubMessage message) {
		this.message = message;
	}

}

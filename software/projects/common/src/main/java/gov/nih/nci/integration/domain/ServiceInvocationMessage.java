package gov.nih.nci.integration.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ServiceInvocationMessage extends AbstractIdentity {

	Long referenceMessageId;
	String invocationException;
	StrategyIdentifier strategyIdentifier;
	
	boolean dataChanged = false;
	
	String originalData = null;

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

	@OneToOne(cascade=CascadeType.ALL)
	@NotNull
	public IHubMessage getMessage() {
		return message;
	}

	public void setMessage(IHubMessage message) {
		this.message = message;
	}

	public boolean isDataChanged() {
		return dataChanged;
	}

	public void setDataChanged(boolean dataChanged) {
		this.dataChanged = dataChanged;
	}
	
	@Column(length = 50000)
	public String getOriginalData() {
		return originalData;
	}

	public void setOriginalData(String originalData) {
		this.originalData = originalData;
	}
}

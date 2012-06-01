package gov.nih.nci.integration.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * ServiceInvocationMessage Entity Class
 * @author Vinodh
 *
 */
@Entity
public class ServiceInvocationMessage extends AbstractIdentity {

	private Long referenceMessageId;
	private String invocationException;
	private StrategyIdentifier strategyIdentifier;
	private boolean dataChanged = false;
	private String originalData = null;
	private IHubMessage message;

	/**
	 * Return referenceMessageId
	 * @return referenceMessageId
	 */
	@NotNull
	public Long getReferenceMessageId() {
		return referenceMessageId;
	}

	/**
	 * Set referenceMessageId
	 * @param referenceMessageId - referenceMessageId
	 */
	public void setReferenceMessageId(Long referenceMessageId) {
		this.referenceMessageId = referenceMessageId;
	}

	/**
	 * Return invocationException
	 * @return invocationException
	 */
	public String getInvocationException() {
		return invocationException;
	}

	/**
	 * Set invocationException
	 * @param invocationException - invocationException
	 */
	public void setInvocationException(String invocationException) {
		this.invocationException = invocationException;
	}

	
	/**
	 * Return strategyIdentifier
	 * @return strategyIdentifier
	 */
	@Enumerated
	@NotNull
	public StrategyIdentifier getStrategyIdentifier() {
		return strategyIdentifier;
	}

	/**
	 * Set strategyIdentifier
	 * @param strategyIdentifier - strategyIdentifier
	 */
	public void setStrategyIdentifier(StrategyIdentifier strategyIdentifier) {
		this.strategyIdentifier = strategyIdentifier;
	}

	
	/**
	 * Return message
	 * @return message
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@NotNull
	public IHubMessage getMessage() {
		return message;
	}

	/**
	 * Set message
	 * @param message - message
	 */
	public void setMessage(IHubMessage message) {
		this.message = message;
	}

	
	/**
	 * Return dataChanged
	 * @return dataChanged
	 */
	public boolean isDataChanged() {
		return dataChanged;
	}

	/**
	 * Set dataChanged
	 * @param dataChanged - dataChanged
	 */
	public void setDataChanged(boolean dataChanged) {
		this.dataChanged = dataChanged;
	}

	
	/**
	 * Return originalData
	 * @return - originalData
	 */
	@Column(length = 50000)
	public String getOriginalData() {
		return originalData;
	}

	
	/**
	 * Set originalData
	 * @param originalData - originalData
	 */
	public void setOriginalData(String originalData) {
		this.originalData = originalData;
	}
}

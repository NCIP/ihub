package gov.nih.nci.integration.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author chandrasekaravr
 * 
 */
@MappedSuperclass
public class AbstractIdentity {

    private Long id;
    
    private Long referenceMessageId;

    /**
     * getId
     * 
     * @return Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getId() {
        return id;
    }

    /**
     * setId
     * 
     * @param id - Id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Return referenceMessageId
     * 
     * @return referenceMessageId
     */
    @NotNull
    public Long getReferenceMessageId() {
        return referenceMessageId;
    }

    /**
     * Set referenceMessageId
     * 
     * @param referenceMessageId - referenceMessageId
     */
    public void setReferenceMessageId(Long referenceMessageId) {
        this.referenceMessageId = referenceMessageId;
    }

}

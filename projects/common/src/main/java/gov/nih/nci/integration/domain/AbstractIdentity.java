package gov.nih.nci.integration.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author chandrasekaravr
 * 
 */
@MappedSuperclass
public class AbstractIdentity {

    private Long id;

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

}

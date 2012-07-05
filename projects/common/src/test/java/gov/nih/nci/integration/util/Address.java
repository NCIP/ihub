package gov.nih.nci.integration.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class to be used for JAXBMarshller Test
 * 
 * @author Rohit Gupta
 * 
 */
@XmlRootElement
public class Address {

    private String city;
    private String line1;
    private String zipcode;

    /**
     * 
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city - city
     */
    @XmlElement
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return line1
     */
    public String getLine1() {
        return line1;
    }

    /**
     * 
     * @param line1 - line1
     */
    @XmlElement
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    /**
     * 
     * @return zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * 
     * @param zipcode - zipcode
     */
    @XmlElement
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}

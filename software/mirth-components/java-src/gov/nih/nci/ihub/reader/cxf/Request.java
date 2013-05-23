/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.reader.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for request complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="request">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="businessMessagePayload" type="{http://caXchange.nci.nih.gov/messaging}messagePayload"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "request", propOrder = {
    "businessMessagePayload"
})
public class Request {

    @XmlElement(required = true)
    protected MessagePayload businessMessagePayload;

    /**
     * Gets the value of the businessMessagePayload property.
     * 
     * @return
     *     possible object is
     *     {@link MessagePayload }
     *     
     */
    public MessagePayload getBusinessMessagePayload() {
        return businessMessagePayload;
    }

    /**
     * Sets the value of the businessMessagePayload property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessagePayload }
     *     
     */
    public void setBusinessMessagePayload(MessagePayload value) {
        this.businessMessagePayload = value;
    }

}

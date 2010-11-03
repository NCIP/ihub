/**
 * ResearchCenter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.metadata.common;

public class ResearchCenter  implements java.io.Serializable {
    private gov.nih.nci.cagrid.metadata.common.Address address;
    private gov.nih.nci.cagrid.metadata.common.ResearchCenterDescription researchCenterDescription;
    private gov.nih.nci.cagrid.metadata.common.ResearchCenterPointOfContactCollection pointOfContactCollection;
    private java.lang.String displayName;  // attribute
    private java.lang.String shortName;  // attribute

    public ResearchCenter() {
    }

    public ResearchCenter(
           gov.nih.nci.cagrid.metadata.common.Address address,
           java.lang.String displayName,
           gov.nih.nci.cagrid.metadata.common.ResearchCenterPointOfContactCollection pointOfContactCollection,
           gov.nih.nci.cagrid.metadata.common.ResearchCenterDescription researchCenterDescription,
           java.lang.String shortName) {
           this.address = address;
           this.researchCenterDescription = researchCenterDescription;
           this.pointOfContactCollection = pointOfContactCollection;
           this.displayName = displayName;
           this.shortName = shortName;
    }


    /**
     * Gets the address value for this ResearchCenter.
     * 
     * @return address
     */
    public gov.nih.nci.cagrid.metadata.common.Address getAddress() {
        return address;
    }


    /**
     * Sets the address value for this ResearchCenter.
     * 
     * @param address
     */
    public void setAddress(gov.nih.nci.cagrid.metadata.common.Address address) {
        this.address = address;
    }


    /**
     * Gets the researchCenterDescription value for this ResearchCenter.
     * 
     * @return researchCenterDescription
     */
    public gov.nih.nci.cagrid.metadata.common.ResearchCenterDescription getResearchCenterDescription() {
        return researchCenterDescription;
    }


    /**
     * Sets the researchCenterDescription value for this ResearchCenter.
     * 
     * @param researchCenterDescription
     */
    public void setResearchCenterDescription(gov.nih.nci.cagrid.metadata.common.ResearchCenterDescription researchCenterDescription) {
        this.researchCenterDescription = researchCenterDescription;
    }


    /**
     * Gets the pointOfContactCollection value for this ResearchCenter.
     * 
     * @return pointOfContactCollection
     */
    public gov.nih.nci.cagrid.metadata.common.ResearchCenterPointOfContactCollection getPointOfContactCollection() {
        return pointOfContactCollection;
    }


    /**
     * Sets the pointOfContactCollection value for this ResearchCenter.
     * 
     * @param pointOfContactCollection
     */
    public void setPointOfContactCollection(gov.nih.nci.cagrid.metadata.common.ResearchCenterPointOfContactCollection pointOfContactCollection) {
        this.pointOfContactCollection = pointOfContactCollection;
    }


    /**
     * Gets the displayName value for this ResearchCenter.
     * 
     * @return displayName
     */
    public java.lang.String getDisplayName() {
        return displayName;
    }


    /**
     * Sets the displayName value for this ResearchCenter.
     * 
     * @param displayName
     */
    public void setDisplayName(java.lang.String displayName) {
        this.displayName = displayName;
    }


    /**
     * Gets the shortName value for this ResearchCenter.
     * 
     * @return shortName
     */
    public java.lang.String getShortName() {
        return shortName;
    }


    /**
     * Sets the shortName value for this ResearchCenter.
     * 
     * @param shortName
     */
    public void setShortName(java.lang.String shortName) {
        this.shortName = shortName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResearchCenter)) return false;
        ResearchCenter other = (ResearchCenter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            ((this.researchCenterDescription==null && other.getResearchCenterDescription()==null) || 
             (this.researchCenterDescription!=null &&
              this.researchCenterDescription.equals(other.getResearchCenterDescription()))) &&
            ((this.pointOfContactCollection==null && other.getPointOfContactCollection()==null) || 
             (this.pointOfContactCollection!=null &&
              this.pointOfContactCollection.equals(other.getPointOfContactCollection()))) &&
            ((this.displayName==null && other.getDisplayName()==null) || 
             (this.displayName!=null &&
              this.displayName.equals(other.getDisplayName()))) &&
            ((this.shortName==null && other.getShortName()==null) || 
             (this.shortName!=null &&
              this.shortName.equals(other.getShortName())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        if (getResearchCenterDescription() != null) {
            _hashCode += getResearchCenterDescription().hashCode();
        }
        if (getPointOfContactCollection() != null) {
            _hashCode += getPointOfContactCollection().hashCode();
        }
        if (getDisplayName() != null) {
            _hashCode += getDisplayName().hashCode();
        }
        if (getShortName() != null) {
            _hashCode += getShortName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResearchCenter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "ResearchCenter"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("displayName");
        attrField.setXmlName(new javax.xml.namespace.QName("", "displayName"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("shortName");
        attrField.setXmlName(new javax.xml.namespace.QName("", "shortName"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "Address"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "Address"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("researchCenterDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "ResearchCenterDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "ResearchCenterDescription"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pointOfContactCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "pointOfContactCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", ">ResearchCenter>pointOfContactCollection"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

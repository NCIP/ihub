/**
 * Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.metadata.service;


/**
 * A service is a "conceptual" definition of a collection of functional
 * contexts.
 * This has no physical manifestation in the grid.
 */
public class Service  implements java.io.Serializable {
    private gov.nih.nci.cagrid.metadata.service.ServicePointOfContactCollection pointOfContactCollection;
    private gov.nih.nci.cagrid.metadata.service.ServiceServiceContextCollection serviceContextCollection;
    private gov.nih.nci.cagrid.metadata.service.CaDSRRegistration caDSRRegistration;
    private gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] semanticMetadata;
    private java.lang.String description;  // attribute
    private java.lang.String name;  // attribute
    private java.lang.String version;  // attribute

    public Service() {
    }

    public Service(
           gov.nih.nci.cagrid.metadata.service.CaDSRRegistration caDSRRegistration,
           java.lang.String description,
           java.lang.String name,
           gov.nih.nci.cagrid.metadata.service.ServicePointOfContactCollection pointOfContactCollection,
           gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] semanticMetadata,
           gov.nih.nci.cagrid.metadata.service.ServiceServiceContextCollection serviceContextCollection,
           java.lang.String version) {
           this.pointOfContactCollection = pointOfContactCollection;
           this.serviceContextCollection = serviceContextCollection;
           this.caDSRRegistration = caDSRRegistration;
           this.semanticMetadata = semanticMetadata;
           this.description = description;
           this.name = name;
           this.version = version;
    }


    /**
     * Gets the pointOfContactCollection value for this Service.
     * 
     * @return pointOfContactCollection
     */
    public gov.nih.nci.cagrid.metadata.service.ServicePointOfContactCollection getPointOfContactCollection() {
        return pointOfContactCollection;
    }


    /**
     * Sets the pointOfContactCollection value for this Service.
     * 
     * @param pointOfContactCollection
     */
    public void setPointOfContactCollection(gov.nih.nci.cagrid.metadata.service.ServicePointOfContactCollection pointOfContactCollection) {
        this.pointOfContactCollection = pointOfContactCollection;
    }


    /**
     * Gets the serviceContextCollection value for this Service.
     * 
     * @return serviceContextCollection
     */
    public gov.nih.nci.cagrid.metadata.service.ServiceServiceContextCollection getServiceContextCollection() {
        return serviceContextCollection;
    }


    /**
     * Sets the serviceContextCollection value for this Service.
     * 
     * @param serviceContextCollection
     */
    public void setServiceContextCollection(gov.nih.nci.cagrid.metadata.service.ServiceServiceContextCollection serviceContextCollection) {
        this.serviceContextCollection = serviceContextCollection;
    }


    /**
     * Gets the caDSRRegistration value for this Service.
     * 
     * @return caDSRRegistration
     */
    public gov.nih.nci.cagrid.metadata.service.CaDSRRegistration getCaDSRRegistration() {
        return caDSRRegistration;
    }


    /**
     * Sets the caDSRRegistration value for this Service.
     * 
     * @param caDSRRegistration
     */
    public void setCaDSRRegistration(gov.nih.nci.cagrid.metadata.service.CaDSRRegistration caDSRRegistration) {
        this.caDSRRegistration = caDSRRegistration;
    }


    /**
     * Gets the semanticMetadata value for this Service.
     * 
     * @return semanticMetadata
     */
    public gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] getSemanticMetadata() {
        return semanticMetadata;
    }


    /**
     * Sets the semanticMetadata value for this Service.
     * 
     * @param semanticMetadata
     */
    public void setSemanticMetadata(gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] semanticMetadata) {
        this.semanticMetadata = semanticMetadata;
    }

    public gov.nih.nci.cagrid.metadata.common.SemanticMetadata getSemanticMetadata(int i) {
        return this.semanticMetadata[i];
    }

    public void setSemanticMetadata(int i, gov.nih.nci.cagrid.metadata.common.SemanticMetadata _value) {
        this.semanticMetadata[i] = _value;
    }


    /**
     * Gets the description value for this Service.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this Service.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the name value for this Service.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this Service.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the version value for this Service.
     * 
     * @return version
     */
    public java.lang.String getVersion() {
        return version;
    }


    /**
     * Sets the version value for this Service.
     * 
     * @param version
     */
    public void setVersion(java.lang.String version) {
        this.version = version;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Service)) return false;
        Service other = (Service) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pointOfContactCollection==null && other.getPointOfContactCollection()==null) || 
             (this.pointOfContactCollection!=null &&
              this.pointOfContactCollection.equals(other.getPointOfContactCollection()))) &&
            ((this.serviceContextCollection==null && other.getServiceContextCollection()==null) || 
             (this.serviceContextCollection!=null &&
              this.serviceContextCollection.equals(other.getServiceContextCollection()))) &&
            ((this.caDSRRegistration==null && other.getCaDSRRegistration()==null) || 
             (this.caDSRRegistration!=null &&
              this.caDSRRegistration.equals(other.getCaDSRRegistration()))) &&
            ((this.semanticMetadata==null && other.getSemanticMetadata()==null) || 
             (this.semanticMetadata!=null &&
              java.util.Arrays.equals(this.semanticMetadata, other.getSemanticMetadata()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.version==null && other.getVersion()==null) || 
             (this.version!=null &&
              this.version.equals(other.getVersion())));
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
        if (getPointOfContactCollection() != null) {
            _hashCode += getPointOfContactCollection().hashCode();
        }
        if (getServiceContextCollection() != null) {
            _hashCode += getServiceContextCollection().hashCode();
        }
        if (getCaDSRRegistration() != null) {
            _hashCode += getCaDSRRegistration().hashCode();
        }
        if (getSemanticMetadata() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSemanticMetadata());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSemanticMetadata(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Service.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", "Service"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("description");
        attrField.setXmlName(new javax.xml.namespace.QName("", "description"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("name");
        attrField.setXmlName(new javax.xml.namespace.QName("", "name"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("version");
        attrField.setXmlName(new javax.xml.namespace.QName("", "version"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pointOfContactCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", "pointOfContactCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", ">Service>pointOfContactCollection"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceContextCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", "serviceContextCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", ">Service>serviceContextCollection"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("caDSRRegistration");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", "CaDSRRegistration"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", "CaDSRRegistration"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("semanticMetadata");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "SemanticMetadata"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "SemanticMetadata"));
        elemField.setMinOccurs(0);
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

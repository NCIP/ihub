/**
 * UMLAttribute.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.metadata.common;


/**
 * caDSR-related
 * 
 * Represents a UML attribute of the parent UML Class.  Indication of
 * isRequired=false means the operation will function without the existence
 * of this attribute.
 */
public class UMLAttribute  implements java.io.Serializable {
    private gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] semanticMetadata;
    private gov.nih.nci.cagrid.metadata.common.ValueDomain valueDomain;
    private java.lang.String dataTypeName;  // attribute
    private java.lang.String description;  // attribute
    private java.lang.String name;  // attribute
    private long publicID;  // attribute
    private float version;  // attribute

    public UMLAttribute() {
    }

    public UMLAttribute(
           java.lang.String dataTypeName,
           java.lang.String description,
           java.lang.String name,
           long publicID,
           gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] semanticMetadata,
           gov.nih.nci.cagrid.metadata.common.ValueDomain valueDomain,
           float version) {
           this.semanticMetadata = semanticMetadata;
           this.valueDomain = valueDomain;
           this.dataTypeName = dataTypeName;
           this.description = description;
           this.name = name;
           this.publicID = publicID;
           this.version = version;
    }


    /**
     * Gets the semanticMetadata value for this UMLAttribute.
     * 
     * @return semanticMetadata
     */
    public gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] getSemanticMetadata() {
        return semanticMetadata;
    }


    /**
     * Sets the semanticMetadata value for this UMLAttribute.
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
     * Gets the valueDomain value for this UMLAttribute.
     * 
     * @return valueDomain
     */
    public gov.nih.nci.cagrid.metadata.common.ValueDomain getValueDomain() {
        return valueDomain;
    }


    /**
     * Sets the valueDomain value for this UMLAttribute.
     * 
     * @param valueDomain
     */
    public void setValueDomain(gov.nih.nci.cagrid.metadata.common.ValueDomain valueDomain) {
        this.valueDomain = valueDomain;
    }


    /**
     * Gets the dataTypeName value for this UMLAttribute.
     * 
     * @return dataTypeName
     */
    public java.lang.String getDataTypeName() {
        return dataTypeName;
    }


    /**
     * Sets the dataTypeName value for this UMLAttribute.
     * 
     * @param dataTypeName
     */
    public void setDataTypeName(java.lang.String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }


    /**
     * Gets the description value for this UMLAttribute.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this UMLAttribute.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the name value for this UMLAttribute.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this UMLAttribute.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the publicID value for this UMLAttribute.
     * 
     * @return publicID
     */
    public long getPublicID() {
        return publicID;
    }


    /**
     * Sets the publicID value for this UMLAttribute.
     * 
     * @param publicID
     */
    public void setPublicID(long publicID) {
        this.publicID = publicID;
    }


    /**
     * Gets the version value for this UMLAttribute.
     * 
     * @return version
     */
    public float getVersion() {
        return version;
    }


    /**
     * Sets the version value for this UMLAttribute.
     * 
     * @param version
     */
    public void setVersion(float version) {
        this.version = version;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UMLAttribute)) return false;
        UMLAttribute other = (UMLAttribute) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.semanticMetadata==null && other.getSemanticMetadata()==null) || 
             (this.semanticMetadata!=null &&
              java.util.Arrays.equals(this.semanticMetadata, other.getSemanticMetadata()))) &&
            ((this.valueDomain==null && other.getValueDomain()==null) || 
             (this.valueDomain!=null &&
              this.valueDomain.equals(other.getValueDomain()))) &&
            ((this.dataTypeName==null && other.getDataTypeName()==null) || 
             (this.dataTypeName!=null &&
              this.dataTypeName.equals(other.getDataTypeName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            this.publicID == other.getPublicID() &&
            this.version == other.getVersion();
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
        if (getValueDomain() != null) {
            _hashCode += getValueDomain().hashCode();
        }
        if (getDataTypeName() != null) {
            _hashCode += getDataTypeName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        _hashCode += new Long(getPublicID()).hashCode();
        _hashCode += new Float(getVersion()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UMLAttribute.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "UMLAttribute"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("dataTypeName");
        attrField.setXmlName(new javax.xml.namespace.QName("", "dataTypeName"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
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
        attrField.setFieldName("publicID");
        attrField.setXmlName(new javax.xml.namespace.QName("", "publicID"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("version");
        attrField.setXmlName(new javax.xml.namespace.QName("", "version"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("semanticMetadata");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "SemanticMetadata"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "SemanticMetadata"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valueDomain");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "ValueDomain"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "ValueDomain"));
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

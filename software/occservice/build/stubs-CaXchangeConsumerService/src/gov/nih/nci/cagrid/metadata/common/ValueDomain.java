/**
 * ValueDomain.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.metadata.common;

public class ValueDomain  implements java.io.Serializable {
    private gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] semanticMetadata;
    private gov.nih.nci.cagrid.metadata.common.ValueDomainEnumerationCollection enumerationCollection;
    private java.lang.String longName;  // attribute
    private java.lang.String unitOfMeasure;  // attribute

    public ValueDomain() {
    }

    public ValueDomain(
           gov.nih.nci.cagrid.metadata.common.ValueDomainEnumerationCollection enumerationCollection,
           java.lang.String longName,
           gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] semanticMetadata,
           java.lang.String unitOfMeasure) {
           this.semanticMetadata = semanticMetadata;
           this.enumerationCollection = enumerationCollection;
           this.longName = longName;
           this.unitOfMeasure = unitOfMeasure;
    }


    /**
     * Gets the semanticMetadata value for this ValueDomain.
     * 
     * @return semanticMetadata
     */
    public gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] getSemanticMetadata() {
        return semanticMetadata;
    }


    /**
     * Sets the semanticMetadata value for this ValueDomain.
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
     * Gets the enumerationCollection value for this ValueDomain.
     * 
     * @return enumerationCollection
     */
    public gov.nih.nci.cagrid.metadata.common.ValueDomainEnumerationCollection getEnumerationCollection() {
        return enumerationCollection;
    }


    /**
     * Sets the enumerationCollection value for this ValueDomain.
     * 
     * @param enumerationCollection
     */
    public void setEnumerationCollection(gov.nih.nci.cagrid.metadata.common.ValueDomainEnumerationCollection enumerationCollection) {
        this.enumerationCollection = enumerationCollection;
    }


    /**
     * Gets the longName value for this ValueDomain.
     * 
     * @return longName
     */
    public java.lang.String getLongName() {
        return longName;
    }


    /**
     * Sets the longName value for this ValueDomain.
     * 
     * @param longName
     */
    public void setLongName(java.lang.String longName) {
        this.longName = longName;
    }


    /**
     * Gets the unitOfMeasure value for this ValueDomain.
     * 
     * @return unitOfMeasure
     */
    public java.lang.String getUnitOfMeasure() {
        return unitOfMeasure;
    }


    /**
     * Sets the unitOfMeasure value for this ValueDomain.
     * 
     * @param unitOfMeasure
     */
    public void setUnitOfMeasure(java.lang.String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValueDomain)) return false;
        ValueDomain other = (ValueDomain) obj;
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
            ((this.enumerationCollection==null && other.getEnumerationCollection()==null) || 
             (this.enumerationCollection!=null &&
              this.enumerationCollection.equals(other.getEnumerationCollection()))) &&
            ((this.longName==null && other.getLongName()==null) || 
             (this.longName!=null &&
              this.longName.equals(other.getLongName()))) &&
            ((this.unitOfMeasure==null && other.getUnitOfMeasure()==null) || 
             (this.unitOfMeasure!=null &&
              this.unitOfMeasure.equals(other.getUnitOfMeasure())));
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
        if (getEnumerationCollection() != null) {
            _hashCode += getEnumerationCollection().hashCode();
        }
        if (getLongName() != null) {
            _hashCode += getLongName().hashCode();
        }
        if (getUnitOfMeasure() != null) {
            _hashCode += getUnitOfMeasure().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValueDomain.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "ValueDomain"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("longName");
        attrField.setXmlName(new javax.xml.namespace.QName("", "longName"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("unitOfMeasure");
        attrField.setXmlName(new javax.xml.namespace.QName("", "unitOfMeasure"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("semanticMetadata");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "SemanticMetadata"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "SemanticMetadata"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enumerationCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "enumerationCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", ">ValueDomain>enumerationCollection"));
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

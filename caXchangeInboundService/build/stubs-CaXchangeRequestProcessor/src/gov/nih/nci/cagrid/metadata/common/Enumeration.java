/**
 * Enumeration.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.metadata.common;

public class Enumeration  implements java.io.Serializable {
    private gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] semanticMetadata;
    private java.lang.String permissibleValue;  // attribute
    private java.lang.String valueMeaning;  // attribute

    public Enumeration() {
    }

    public Enumeration(
           java.lang.String permissibleValue,
           gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] semanticMetadata,
           java.lang.String valueMeaning) {
           this.semanticMetadata = semanticMetadata;
           this.permissibleValue = permissibleValue;
           this.valueMeaning = valueMeaning;
    }


    /**
     * Gets the semanticMetadata value for this Enumeration.
     * 
     * @return semanticMetadata
     */
    public gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] getSemanticMetadata() {
        return semanticMetadata;
    }


    /**
     * Sets the semanticMetadata value for this Enumeration.
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
     * Gets the permissibleValue value for this Enumeration.
     * 
     * @return permissibleValue
     */
    public java.lang.String getPermissibleValue() {
        return permissibleValue;
    }


    /**
     * Sets the permissibleValue value for this Enumeration.
     * 
     * @param permissibleValue
     */
    public void setPermissibleValue(java.lang.String permissibleValue) {
        this.permissibleValue = permissibleValue;
    }


    /**
     * Gets the valueMeaning value for this Enumeration.
     * 
     * @return valueMeaning
     */
    public java.lang.String getValueMeaning() {
        return valueMeaning;
    }


    /**
     * Sets the valueMeaning value for this Enumeration.
     * 
     * @param valueMeaning
     */
    public void setValueMeaning(java.lang.String valueMeaning) {
        this.valueMeaning = valueMeaning;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Enumeration)) return false;
        Enumeration other = (Enumeration) obj;
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
            ((this.permissibleValue==null && other.getPermissibleValue()==null) || 
             (this.permissibleValue!=null &&
              this.permissibleValue.equals(other.getPermissibleValue()))) &&
            ((this.valueMeaning==null && other.getValueMeaning()==null) || 
             (this.valueMeaning!=null &&
              this.valueMeaning.equals(other.getValueMeaning())));
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
        if (getPermissibleValue() != null) {
            _hashCode += getPermissibleValue().hashCode();
        }
        if (getValueMeaning() != null) {
            _hashCode += getValueMeaning().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Enumeration.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "Enumeration"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("permissibleValue");
        attrField.setXmlName(new javax.xml.namespace.QName("", "permissibleValue"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("valueMeaning");
        attrField.setXmlName(new javax.xml.namespace.QName("", "valueMeaning"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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

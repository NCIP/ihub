/**
 * Output.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.metadata.service;


/**
 * Represents the result/output of an operation.  Its non-existence
 * represents the operation produces no result.
 * 
 * This is manifested as the value of an operation response in the grid.
 */
public class Output  implements java.io.Serializable {
    private gov.nih.nci.cagrid.metadata.common.UMLClass UMLClass;
    private int dimensionality;  // attribute
    private boolean isArray;  // attribute
    private javax.xml.namespace.QName qName;  // attribute

    public Output() {
    }

    public Output(
           gov.nih.nci.cagrid.metadata.common.UMLClass UMLClass,
           int dimensionality,
           boolean isArray,
           javax.xml.namespace.QName qName) {
           this.UMLClass = UMLClass;
           this.dimensionality = dimensionality;
           this.isArray = isArray;
           this.qName = qName;
    }


    /**
     * Gets the UMLClass value for this Output.
     * 
     * @return UMLClass
     */
    public gov.nih.nci.cagrid.metadata.common.UMLClass getUMLClass() {
        return UMLClass;
    }


    /**
     * Sets the UMLClass value for this Output.
     * 
     * @param UMLClass
     */
    public void setUMLClass(gov.nih.nci.cagrid.metadata.common.UMLClass UMLClass) {
        this.UMLClass = UMLClass;
    }


    /**
     * Gets the dimensionality value for this Output.
     * 
     * @return dimensionality
     */
    public int getDimensionality() {
        return dimensionality;
    }


    /**
     * Sets the dimensionality value for this Output.
     * 
     * @param dimensionality
     */
    public void setDimensionality(int dimensionality) {
        this.dimensionality = dimensionality;
    }


    /**
     * Gets the isArray value for this Output.
     * 
     * @return isArray
     */
    public boolean isIsArray() {
        return isArray;
    }


    /**
     * Sets the isArray value for this Output.
     * 
     * @param isArray
     */
    public void setIsArray(boolean isArray) {
        this.isArray = isArray;
    }


    /**
     * Gets the qName value for this Output.
     * 
     * @return qName
     */
    public javax.xml.namespace.QName getQName() {
        return qName;
    }


    /**
     * Sets the qName value for this Output.
     * 
     * @param qName
     */
    public void setQName(javax.xml.namespace.QName qName) {
        this.qName = qName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Output)) return false;
        Output other = (Output) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UMLClass==null && other.getUMLClass()==null) || 
             (this.UMLClass!=null &&
              this.UMLClass.equals(other.getUMLClass()))) &&
            this.dimensionality == other.getDimensionality() &&
            this.isArray == other.isIsArray() &&
            ((this.qName==null && other.getQName()==null) || 
             (this.qName!=null &&
              this.qName.equals(other.getQName())));
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
        if (getUMLClass() != null) {
            _hashCode += getUMLClass().hashCode();
        }
        _hashCode += getDimensionality();
        _hashCode += (isIsArray() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getQName() != null) {
            _hashCode += getQName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Output.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", "Output"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("dimensionality");
        attrField.setXmlName(new javax.xml.namespace.QName("", "dimensionality"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("isArray");
        attrField.setXmlName(new javax.xml.namespace.QName("", "isArray"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("QName");
        attrField.setXmlName(new javax.xml.namespace.QName("", "qName"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "QName"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UMLClass");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "UMLClass"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "UMLClass"));
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

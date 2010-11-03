/**
 * ValueDomainEnumerationCollection.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.metadata.common;

public class ValueDomainEnumerationCollection  implements java.io.Serializable {
    private gov.nih.nci.cagrid.metadata.common.Enumeration[] enumeration;

    public ValueDomainEnumerationCollection() {
    }

    public ValueDomainEnumerationCollection(
           gov.nih.nci.cagrid.metadata.common.Enumeration[] enumeration) {
           this.enumeration = enumeration;
    }


    /**
     * Gets the enumeration value for this ValueDomainEnumerationCollection.
     * 
     * @return enumeration
     */
    public gov.nih.nci.cagrid.metadata.common.Enumeration[] getEnumeration() {
        return enumeration;
    }


    /**
     * Sets the enumeration value for this ValueDomainEnumerationCollection.
     * 
     * @param enumeration
     */
    public void setEnumeration(gov.nih.nci.cagrid.metadata.common.Enumeration[] enumeration) {
        this.enumeration = enumeration;
    }

    public gov.nih.nci.cagrid.metadata.common.Enumeration getEnumeration(int i) {
        return this.enumeration[i];
    }

    public void setEnumeration(int i, gov.nih.nci.cagrid.metadata.common.Enumeration _value) {
        this.enumeration[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValueDomainEnumerationCollection)) return false;
        ValueDomainEnumerationCollection other = (ValueDomainEnumerationCollection) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.enumeration==null && other.getEnumeration()==null) || 
             (this.enumeration!=null &&
              java.util.Arrays.equals(this.enumeration, other.getEnumeration())));
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
        if (getEnumeration() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEnumeration());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEnumeration(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValueDomainEnumerationCollection.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", ">ValueDomain>enumerationCollection"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enumeration");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "Enumeration"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "Enumeration"));
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

/**
 * UMLClassUmlAttributeCollection.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.metadata.common;

public class UMLClassUmlAttributeCollection  implements java.io.Serializable {
    private gov.nih.nci.cagrid.metadata.common.UMLAttribute[] UMLAttribute;

    public UMLClassUmlAttributeCollection() {
    }

    public UMLClassUmlAttributeCollection(
           gov.nih.nci.cagrid.metadata.common.UMLAttribute[] UMLAttribute) {
           this.UMLAttribute = UMLAttribute;
    }


    /**
     * Gets the UMLAttribute value for this UMLClassUmlAttributeCollection.
     * 
     * @return UMLAttribute
     */
    public gov.nih.nci.cagrid.metadata.common.UMLAttribute[] getUMLAttribute() {
        return UMLAttribute;
    }


    /**
     * Sets the UMLAttribute value for this UMLClassUmlAttributeCollection.
     * 
     * @param UMLAttribute
     */
    public void setUMLAttribute(gov.nih.nci.cagrid.metadata.common.UMLAttribute[] UMLAttribute) {
        this.UMLAttribute = UMLAttribute;
    }

    public gov.nih.nci.cagrid.metadata.common.UMLAttribute getUMLAttribute(int i) {
        return this.UMLAttribute[i];
    }

    public void setUMLAttribute(int i, gov.nih.nci.cagrid.metadata.common.UMLAttribute _value) {
        this.UMLAttribute[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UMLClassUmlAttributeCollection)) return false;
        UMLClassUmlAttributeCollection other = (UMLClassUmlAttributeCollection) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UMLAttribute==null && other.getUMLAttribute()==null) || 
             (this.UMLAttribute!=null &&
              java.util.Arrays.equals(this.UMLAttribute, other.getUMLAttribute())));
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
        if (getUMLAttribute() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUMLAttribute());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUMLAttribute(), i);
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
        new org.apache.axis.description.TypeDesc(UMLClassUmlAttributeCollection.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", ">UMLClass>umlAttributeCollection"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UMLAttribute");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "UMLAttribute"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "UMLAttribute"));
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

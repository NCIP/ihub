/**
 * CaDSRRegistration.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.metadata.service;

public class CaDSRRegistration  implements java.io.Serializable {
    private java.lang.String registrationStatus;  // attribute
    private java.lang.String workflowStatus;  // attribute

    public CaDSRRegistration() {
    }

    public CaDSRRegistration(
           java.lang.String registrationStatus,
           java.lang.String workflowStatus) {
           this.registrationStatus = registrationStatus;
           this.workflowStatus = workflowStatus;
    }


    /**
     * Gets the registrationStatus value for this CaDSRRegistration.
     * 
     * @return registrationStatus
     */
    public java.lang.String getRegistrationStatus() {
        return registrationStatus;
    }


    /**
     * Sets the registrationStatus value for this CaDSRRegistration.
     * 
     * @param registrationStatus
     */
    public void setRegistrationStatus(java.lang.String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }


    /**
     * Gets the workflowStatus value for this CaDSRRegistration.
     * 
     * @return workflowStatus
     */
    public java.lang.String getWorkflowStatus() {
        return workflowStatus;
    }


    /**
     * Sets the workflowStatus value for this CaDSRRegistration.
     * 
     * @param workflowStatus
     */
    public void setWorkflowStatus(java.lang.String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CaDSRRegistration)) return false;
        CaDSRRegistration other = (CaDSRRegistration) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.registrationStatus==null && other.getRegistrationStatus()==null) || 
             (this.registrationStatus!=null &&
              this.registrationStatus.equals(other.getRegistrationStatus()))) &&
            ((this.workflowStatus==null && other.getWorkflowStatus()==null) || 
             (this.workflowStatus!=null &&
              this.workflowStatus.equals(other.getWorkflowStatus())));
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
        if (getRegistrationStatus() != null) {
            _hashCode += getRegistrationStatus().hashCode();
        }
        if (getWorkflowStatus() != null) {
            _hashCode += getWorkflowStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CaDSRRegistration.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", "CaDSRRegistration"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("registrationStatus");
        attrField.setXmlName(new javax.xml.namespace.QName("", "registrationStatus"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("workflowStatus");
        attrField.setXmlName(new javax.xml.namespace.QName("", "workflowStatus"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
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

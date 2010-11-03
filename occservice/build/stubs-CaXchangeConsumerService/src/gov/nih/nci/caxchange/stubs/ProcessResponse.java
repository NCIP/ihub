/**
 * ProcessResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.caxchange.stubs;

public class ProcessResponse  implements java.io.Serializable {
    private gov.nih.nci.caxchange.ConsumerResponseMessage caXchangeConsumerResponse;

    public ProcessResponse() {
    }

    public ProcessResponse(
           gov.nih.nci.caxchange.ConsumerResponseMessage caXchangeConsumerResponse) {
           this.caXchangeConsumerResponse = caXchangeConsumerResponse;
    }


    /**
     * Gets the caXchangeConsumerResponse value for this ProcessResponse.
     * 
     * @return caXchangeConsumerResponse
     */
    public gov.nih.nci.caxchange.ConsumerResponseMessage getCaXchangeConsumerResponse() {
        return caXchangeConsumerResponse;
    }


    /**
     * Sets the caXchangeConsumerResponse value for this ProcessResponse.
     * 
     * @param caXchangeConsumerResponse
     */
    public void setCaXchangeConsumerResponse(gov.nih.nci.caxchange.ConsumerResponseMessage caXchangeConsumerResponse) {
        this.caXchangeConsumerResponse = caXchangeConsumerResponse;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProcessResponse)) return false;
        ProcessResponse other = (ProcessResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.caXchangeConsumerResponse==null && other.getCaXchangeConsumerResponse()==null) || 
             (this.caXchangeConsumerResponse!=null &&
              this.caXchangeConsumerResponse.equals(other.getCaXchangeConsumerResponse())));
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
        if (getCaXchangeConsumerResponse() != null) {
            _hashCode += getCaXchangeConsumerResponse().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProcessResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://caxchange.nci.nih.gov/CaXchangeConsumerService", ">ProcessResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("caXchangeConsumerResponse");
        elemField.setXmlName(new javax.xml.namespace.QName("http://caXchange.nci.nih.gov/messaging", "caXchangeConsumerResponse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://caXchange.nci.nih.gov/messaging", "consumerResponseMessage"));
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

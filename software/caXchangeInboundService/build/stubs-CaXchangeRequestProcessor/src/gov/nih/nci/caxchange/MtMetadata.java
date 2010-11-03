/**
 * MtMetadata.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.caxchange;

public class MtMetadata  implements java.io.Serializable {
    private java.lang.String messageType;
    private org.apache.axis.types.URI payloadUri;
    private boolean reliableTransactions;

    public MtMetadata() {
    }

    public MtMetadata(
           java.lang.String messageType,
           org.apache.axis.types.URI payloadUri,
           boolean reliableTransactions) {
           this.messageType = messageType;
           this.payloadUri = payloadUri;
           this.reliableTransactions = reliableTransactions;
    }


    /**
     * Gets the messageType value for this MtMetadata.
     * 
     * @return messageType
     */
    public java.lang.String getMessageType() {
        return messageType;
    }


    /**
     * Sets the messageType value for this MtMetadata.
     * 
     * @param messageType
     */
    public void setMessageType(java.lang.String messageType) {
        this.messageType = messageType;
    }


    /**
     * Gets the payloadUri value for this MtMetadata.
     * 
     * @return payloadUri
     */
    public org.apache.axis.types.URI getPayloadUri() {
        return payloadUri;
    }


    /**
     * Sets the payloadUri value for this MtMetadata.
     * 
     * @param payloadUri
     */
    public void setPayloadUri(org.apache.axis.types.URI payloadUri) {
        this.payloadUri = payloadUri;
    }


    /**
     * Gets the reliableTransactions value for this MtMetadata.
     * 
     * @return reliableTransactions
     */
    public boolean isReliableTransactions() {
        return reliableTransactions;
    }


    /**
     * Sets the reliableTransactions value for this MtMetadata.
     * 
     * @param reliableTransactions
     */
    public void setReliableTransactions(boolean reliableTransactions) {
        this.reliableTransactions = reliableTransactions;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MtMetadata)) return false;
        MtMetadata other = (MtMetadata) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.messageType==null && other.getMessageType()==null) || 
             (this.messageType!=null &&
              this.messageType.equals(other.getMessageType()))) &&
            ((this.payloadUri==null && other.getPayloadUri()==null) || 
             (this.payloadUri!=null &&
              this.payloadUri.equals(other.getPayloadUri()))) &&
            this.reliableTransactions == other.isReliableTransactions();
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
        if (getMessageType() != null) {
            _hashCode += getMessageType().hashCode();
        }
        if (getPayloadUri() != null) {
            _hashCode += getPayloadUri().hashCode();
        }
        _hashCode += (isReliableTransactions() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MtMetadata.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://caXchange.nci.nih.gov/messaging", "mtMetadata"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://caXchange.nci.nih.gov/messaging", "messageType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payloadUri");
        elemField.setXmlName(new javax.xml.namespace.QName("http://caXchange.nci.nih.gov/messaging", "payloadUri"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reliableTransactions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://caXchange.nci.nih.gov/messaging", "reliableTransactions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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

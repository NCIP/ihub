/**
 * RoutingMetadata.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.caxchange;

public class RoutingMetadata  implements java.io.Serializable {
    private gov.nih.nci.caxchange.MtMetadata[] messageTypeMetadata;

    public RoutingMetadata() {
    }

    public RoutingMetadata(
           gov.nih.nci.caxchange.MtMetadata[] messageTypeMetadata) {
           this.messageTypeMetadata = messageTypeMetadata;
    }


    /**
     * Gets the messageTypeMetadata value for this RoutingMetadata.
     * 
     * @return messageTypeMetadata
     */
    public gov.nih.nci.caxchange.MtMetadata[] getMessageTypeMetadata() {
        return messageTypeMetadata;
    }


    /**
     * Sets the messageTypeMetadata value for this RoutingMetadata.
     * 
     * @param messageTypeMetadata
     */
    public void setMessageTypeMetadata(gov.nih.nci.caxchange.MtMetadata[] messageTypeMetadata) {
        this.messageTypeMetadata = messageTypeMetadata;
    }

    public gov.nih.nci.caxchange.MtMetadata getMessageTypeMetadata(int i) {
        return this.messageTypeMetadata[i];
    }

    public void setMessageTypeMetadata(int i, gov.nih.nci.caxchange.MtMetadata _value) {
        this.messageTypeMetadata[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RoutingMetadata)) return false;
        RoutingMetadata other = (RoutingMetadata) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.messageTypeMetadata==null && other.getMessageTypeMetadata()==null) || 
             (this.messageTypeMetadata!=null &&
              java.util.Arrays.equals(this.messageTypeMetadata, other.getMessageTypeMetadata())));
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
        if (getMessageTypeMetadata() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMessageTypeMetadata());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMessageTypeMetadata(), i);
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
        new org.apache.axis.description.TypeDesc(RoutingMetadata.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://caXchange.nci.nih.gov/messaging", "routingMetadata"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageTypeMetadata");
        elemField.setXmlName(new javax.xml.namespace.QName("http://caXchange.nci.nih.gov/messaging", "messageTypeMetadata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://caXchange.nci.nih.gov/messaging", "mtMetadata"));
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

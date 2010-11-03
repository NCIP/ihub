/**
 * ServiceServiceContextCollection.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.metadata.service;

public class ServiceServiceContextCollection  implements java.io.Serializable {
    private gov.nih.nci.cagrid.metadata.service.ServiceContext[] serviceContext;

    public ServiceServiceContextCollection() {
    }

    public ServiceServiceContextCollection(
           gov.nih.nci.cagrid.metadata.service.ServiceContext[] serviceContext) {
           this.serviceContext = serviceContext;
    }


    /**
     * Gets the serviceContext value for this ServiceServiceContextCollection.
     * 
     * @return serviceContext
     */
    public gov.nih.nci.cagrid.metadata.service.ServiceContext[] getServiceContext() {
        return serviceContext;
    }


    /**
     * Sets the serviceContext value for this ServiceServiceContextCollection.
     * 
     * @param serviceContext
     */
    public void setServiceContext(gov.nih.nci.cagrid.metadata.service.ServiceContext[] serviceContext) {
        this.serviceContext = serviceContext;
    }

    public gov.nih.nci.cagrid.metadata.service.ServiceContext getServiceContext(int i) {
        return this.serviceContext[i];
    }

    public void setServiceContext(int i, gov.nih.nci.cagrid.metadata.service.ServiceContext _value) {
        this.serviceContext[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceServiceContextCollection)) return false;
        ServiceServiceContextCollection other = (ServiceServiceContextCollection) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.serviceContext==null && other.getServiceContext()==null) || 
             (this.serviceContext!=null &&
              java.util.Arrays.equals(this.serviceContext, other.getServiceContext())));
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
        if (getServiceContext() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getServiceContext());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getServiceContext(), i);
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
        new org.apache.axis.description.TypeDesc(ServiceServiceContextCollection.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", ">Service>serviceContextCollection"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceContext");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", "ServiceContext"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", "ServiceContext"));
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

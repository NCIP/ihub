/**
 * ServiceContext.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.metadata.service;


/**
 * This is a functional collection of operations that work over a
 * common collection of stateful resources.
 * A service without stateful resources would have a single context.
 * 
 * This is manifested as an actual service in the grid.
 */
public class ServiceContext  implements java.io.Serializable {
    private gov.nih.nci.cagrid.metadata.service.ServiceContextOperationCollection operationCollection;
    private gov.nih.nci.cagrid.metadata.service.ServiceContextContextPropertyCollection contextPropertyCollection;
    private java.lang.String description;  // attribute
    private java.lang.String name;  // attribute

    public ServiceContext() {
    }

    public ServiceContext(
           gov.nih.nci.cagrid.metadata.service.ServiceContextContextPropertyCollection contextPropertyCollection,
           java.lang.String description,
           java.lang.String name,
           gov.nih.nci.cagrid.metadata.service.ServiceContextOperationCollection operationCollection) {
           this.operationCollection = operationCollection;
           this.contextPropertyCollection = contextPropertyCollection;
           this.description = description;
           this.name = name;
    }


    /**
     * Gets the operationCollection value for this ServiceContext.
     * 
     * @return operationCollection
     */
    public gov.nih.nci.cagrid.metadata.service.ServiceContextOperationCollection getOperationCollection() {
        return operationCollection;
    }


    /**
     * Sets the operationCollection value for this ServiceContext.
     * 
     * @param operationCollection
     */
    public void setOperationCollection(gov.nih.nci.cagrid.metadata.service.ServiceContextOperationCollection operationCollection) {
        this.operationCollection = operationCollection;
    }


    /**
     * Gets the contextPropertyCollection value for this ServiceContext.
     * 
     * @return contextPropertyCollection
     */
    public gov.nih.nci.cagrid.metadata.service.ServiceContextContextPropertyCollection getContextPropertyCollection() {
        return contextPropertyCollection;
    }


    /**
     * Sets the contextPropertyCollection value for this ServiceContext.
     * 
     * @param contextPropertyCollection
     */
    public void setContextPropertyCollection(gov.nih.nci.cagrid.metadata.service.ServiceContextContextPropertyCollection contextPropertyCollection) {
        this.contextPropertyCollection = contextPropertyCollection;
    }


    /**
     * Gets the description value for this ServiceContext.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this ServiceContext.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the name value for this ServiceContext.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ServiceContext.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceContext)) return false;
        ServiceContext other = (ServiceContext) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.operationCollection==null && other.getOperationCollection()==null) || 
             (this.operationCollection!=null &&
              this.operationCollection.equals(other.getOperationCollection()))) &&
            ((this.contextPropertyCollection==null && other.getContextPropertyCollection()==null) || 
             (this.contextPropertyCollection!=null &&
              this.contextPropertyCollection.equals(other.getContextPropertyCollection()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName())));
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
        if (getOperationCollection() != null) {
            _hashCode += getOperationCollection().hashCode();
        }
        if (getContextPropertyCollection() != null) {
            _hashCode += getContextPropertyCollection().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceContext.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", "ServiceContext"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("description");
        attrField.setXmlName(new javax.xml.namespace.QName("", "description"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("name");
        attrField.setXmlName(new javax.xml.namespace.QName("", "name"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operationCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", "operationCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", ">ServiceContext>operationCollection"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contextPropertyCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", "contextPropertyCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", ">ServiceContext>contextPropertyCollection"));
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

/**
 * Metadata.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.caxchange;

public class Metadata  implements java.io.Serializable {
    private gov.nih.nci.caxchange.TransactionControls transactionControl;
    private java.lang.String serviceType;
    private java.lang.String operationName;
    private java.lang.String externalIdentifier;
    /** Populated by caXchange components, any value provided by the client
 * will be overridden by the caXchange components */
    private java.lang.String caXchangeIdentifier;
    private gov.nih.nci.caxchange.Credentials credentials;

    public Metadata() {
    }

    public Metadata(
           java.lang.String caXchangeIdentifier,
           gov.nih.nci.caxchange.Credentials credentials,
           java.lang.String externalIdentifier,
           java.lang.String operationName,
           java.lang.String serviceType,
           gov.nih.nci.caxchange.TransactionControls transactionControl) {
           this.transactionControl = transactionControl;
           this.serviceType = serviceType;
           this.operationName = operationName;
           this.externalIdentifier = externalIdentifier;
           this.caXchangeIdentifier = caXchangeIdentifier;
           this.credentials = credentials;
    }


    /**
     * Gets the transactionControl value for this Metadata.
     * 
     * @return transactionControl
     */
    public gov.nih.nci.caxchange.TransactionControls getTransactionControl() {
        return transactionControl;
    }


    /**
     * Sets the transactionControl value for this Metadata.
     * 
     * @param transactionControl
     */
    public void setTransactionControl(gov.nih.nci.caxchange.TransactionControls transactionControl) {
        this.transactionControl = transactionControl;
    }


    /**
     * Gets the serviceType value for this Metadata.
     * 
     * @return serviceType
     */
    public java.lang.String getServiceType() {
        return serviceType;
    }


    /**
     * Sets the serviceType value for this Metadata.
     * 
     * @param serviceType
     */
    public void setServiceType(java.lang.String serviceType) {
        this.serviceType = serviceType;
    }


    /**
     * Gets the operationName value for this Metadata.
     * 
     * @return operationName
     */
    public java.lang.String getOperationName() {
        return operationName;
    }


    /**
     * Sets the operationName value for this Metadata.
     * 
     * @param operationName
     */
    public void setOperationName(java.lang.String operationName) {
        this.operationName = operationName;
    }


    /**
     * Gets the externalIdentifier value for this Metadata.
     * 
     * @return externalIdentifier
     */
    public java.lang.String getExternalIdentifier() {
        return externalIdentifier;
    }


    /**
     * Sets the externalIdentifier value for this Metadata.
     * 
     * @param externalIdentifier
     */
    public void setExternalIdentifier(java.lang.String externalIdentifier) {
        this.externalIdentifier = externalIdentifier;
    }


    /**
     * Gets the caXchangeIdentifier value for this Metadata.
     * 
     * @return caXchangeIdentifier Populated by caXchange components, any value provided by the client
 * will be overridden by the caXchange components
     */
    public java.lang.String getCaXchangeIdentifier() {
        return caXchangeIdentifier;
    }


    /**
     * Sets the caXchangeIdentifier value for this Metadata.
     * 
     * @param caXchangeIdentifier Populated by caXchange components, any value provided by the client
 * will be overridden by the caXchange components
     */
    public void setCaXchangeIdentifier(java.lang.String caXchangeIdentifier) {
        this.caXchangeIdentifier = caXchangeIdentifier;
    }


    /**
     * Gets the credentials value for this Metadata.
     * 
     * @return credentials
     */
    public gov.nih.nci.caxchange.Credentials getCredentials() {
        return credentials;
    }


    /**
     * Sets the credentials value for this Metadata.
     * 
     * @param credentials
     */
    public void setCredentials(gov.nih.nci.caxchange.Credentials credentials) {
        this.credentials = credentials;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Metadata)) return false;
        Metadata other = (Metadata) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.transactionControl==null && other.getTransactionControl()==null) || 
             (this.transactionControl!=null &&
              this.transactionControl.equals(other.getTransactionControl()))) &&
            ((this.serviceType==null && other.getServiceType()==null) || 
             (this.serviceType!=null &&
              this.serviceType.equals(other.getServiceType()))) &&
            ((this.operationName==null && other.getOperationName()==null) || 
             (this.operationName!=null &&
              this.operationName.equals(other.getOperationName()))) &&
            ((this.externalIdentifier==null && other.getExternalIdentifier()==null) || 
             (this.externalIdentifier!=null &&
              this.externalIdentifier.equals(other.getExternalIdentifier()))) &&
            ((this.caXchangeIdentifier==null && other.getCaXchangeIdentifier()==null) || 
             (this.caXchangeIdentifier!=null &&
              this.caXchangeIdentifier.equals(other.getCaXchangeIdentifier()))) &&
            ((this.credentials==null && other.getCredentials()==null) || 
             (this.credentials!=null &&
              this.credentials.equals(other.getCredentials())));
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
        if (getTransactionControl() != null) {
            _hashCode += getTransactionControl().hashCode();
        }
        if (getServiceType() != null) {
            _hashCode += getServiceType().hashCode();
        }
        if (getOperationName() != null) {
            _hashCode += getOperationName().hashCode();
        }
        if (getExternalIdentifier() != null) {
            _hashCode += getExternalIdentifier().hashCode();
        }
        if (getCaXchangeIdentifier() != null) {
            _hashCode += getCaXchangeIdentifier().hashCode();
        }
        if (getCredentials() != null) {
            _hashCode += getCredentials().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Metadata.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://caXchange.nci.nih.gov/messaging", "metadata"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionControl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://caXchange.nci.nih.gov/messaging", "transactionControl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://caXchange.nci.nih.gov/messaging", "transactionControls"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://caXchange.nci.nih.gov/messaging", "serviceType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operationName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://caXchange.nci.nih.gov/messaging", "operationName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("externalIdentifier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://caXchange.nci.nih.gov/messaging", "externalIdentifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("caXchangeIdentifier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://caXchange.nci.nih.gov/messaging", "caXchangeIdentifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("credentials");
        elemField.setXmlName(new javax.xml.namespace.QName("http://caXchange.nci.nih.gov/messaging", "credentials"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://caXchange.nci.nih.gov/messaging", "credentials"));
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

/**
 * SemanticMetadata.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.metadata.common;

public class SemanticMetadata  implements java.io.Serializable {
    private java.lang.String conceptCode;  // attribute
    private java.lang.String conceptDefinition;  // attribute
    private java.lang.String conceptName;  // attribute
    private java.lang.Integer order;  // attribute
    private java.lang.Integer orderLevel;  // attribute

    public SemanticMetadata() {
    }

    public SemanticMetadata(
           java.lang.String conceptCode,
           java.lang.String conceptDefinition,
           java.lang.String conceptName,
           java.lang.Integer order,
           java.lang.Integer orderLevel) {
           this.conceptCode = conceptCode;
           this.conceptDefinition = conceptDefinition;
           this.conceptName = conceptName;
           this.order = order;
           this.orderLevel = orderLevel;
    }


    /**
     * Gets the conceptCode value for this SemanticMetadata.
     * 
     * @return conceptCode
     */
    public java.lang.String getConceptCode() {
        return conceptCode;
    }


    /**
     * Sets the conceptCode value for this SemanticMetadata.
     * 
     * @param conceptCode
     */
    public void setConceptCode(java.lang.String conceptCode) {
        this.conceptCode = conceptCode;
    }


    /**
     * Gets the conceptDefinition value for this SemanticMetadata.
     * 
     * @return conceptDefinition
     */
    public java.lang.String getConceptDefinition() {
        return conceptDefinition;
    }


    /**
     * Sets the conceptDefinition value for this SemanticMetadata.
     * 
     * @param conceptDefinition
     */
    public void setConceptDefinition(java.lang.String conceptDefinition) {
        this.conceptDefinition = conceptDefinition;
    }


    /**
     * Gets the conceptName value for this SemanticMetadata.
     * 
     * @return conceptName
     */
    public java.lang.String getConceptName() {
        return conceptName;
    }


    /**
     * Sets the conceptName value for this SemanticMetadata.
     * 
     * @param conceptName
     */
    public void setConceptName(java.lang.String conceptName) {
        this.conceptName = conceptName;
    }


    /**
     * Gets the order value for this SemanticMetadata.
     * 
     * @return order
     */
    public java.lang.Integer getOrder() {
        return order;
    }


    /**
     * Sets the order value for this SemanticMetadata.
     * 
     * @param order
     */
    public void setOrder(java.lang.Integer order) {
        this.order = order;
    }


    /**
     * Gets the orderLevel value for this SemanticMetadata.
     * 
     * @return orderLevel
     */
    public java.lang.Integer getOrderLevel() {
        return orderLevel;
    }


    /**
     * Sets the orderLevel value for this SemanticMetadata.
     * 
     * @param orderLevel
     */
    public void setOrderLevel(java.lang.Integer orderLevel) {
        this.orderLevel = orderLevel;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SemanticMetadata)) return false;
        SemanticMetadata other = (SemanticMetadata) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.conceptCode==null && other.getConceptCode()==null) || 
             (this.conceptCode!=null &&
              this.conceptCode.equals(other.getConceptCode()))) &&
            ((this.conceptDefinition==null && other.getConceptDefinition()==null) || 
             (this.conceptDefinition!=null &&
              this.conceptDefinition.equals(other.getConceptDefinition()))) &&
            ((this.conceptName==null && other.getConceptName()==null) || 
             (this.conceptName!=null &&
              this.conceptName.equals(other.getConceptName()))) &&
            ((this.order==null && other.getOrder()==null) || 
             (this.order!=null &&
              this.order.equals(other.getOrder()))) &&
            ((this.orderLevel==null && other.getOrderLevel()==null) || 
             (this.orderLevel!=null &&
              this.orderLevel.equals(other.getOrderLevel())));
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
        if (getConceptCode() != null) {
            _hashCode += getConceptCode().hashCode();
        }
        if (getConceptDefinition() != null) {
            _hashCode += getConceptDefinition().hashCode();
        }
        if (getConceptName() != null) {
            _hashCode += getConceptName().hashCode();
        }
        if (getOrder() != null) {
            _hashCode += getOrder().hashCode();
        }
        if (getOrderLevel() != null) {
            _hashCode += getOrderLevel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SemanticMetadata.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "SemanticMetadata"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("conceptCode");
        attrField.setXmlName(new javax.xml.namespace.QName("", "conceptCode"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("conceptDefinition");
        attrField.setXmlName(new javax.xml.namespace.QName("", "conceptDefinition"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("conceptName");
        attrField.setXmlName(new javax.xml.namespace.QName("", "conceptName"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("order");
        attrField.setXmlName(new javax.xml.namespace.QName("", "order"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("orderLevel");
        attrField.setXmlName(new javax.xml.namespace.QName("", "orderLevel"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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

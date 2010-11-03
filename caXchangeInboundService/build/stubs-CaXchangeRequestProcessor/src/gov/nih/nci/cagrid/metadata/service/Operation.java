/**
 * Operation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.metadata.service;


/**
 * This represents a method/operation/function in a service context.
 * Its input parameters are described by its InputParameter associations,
 * its output by its Output association, and any errors it produces by
 * its Fault associations.
 * 
 * This is manifested as an operation of a service in the grid.
 */
public class Operation  implements java.io.Serializable {
    private gov.nih.nci.cagrid.metadata.service.OperationInputParameterCollection inputParameterCollection;
    private gov.nih.nci.cagrid.metadata.service.Output output;
    private gov.nih.nci.cagrid.metadata.service.OperationFaultCollection faultCollection;
    private gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] semanticMetadata;
    private java.lang.String description;  // attribute
    private java.lang.String name;  // attribute

    public Operation() {
    }

    public Operation(
           java.lang.String description,
           gov.nih.nci.cagrid.metadata.service.OperationFaultCollection faultCollection,
           gov.nih.nci.cagrid.metadata.service.OperationInputParameterCollection inputParameterCollection,
           java.lang.String name,
           gov.nih.nci.cagrid.metadata.service.Output output,
           gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] semanticMetadata) {
           this.inputParameterCollection = inputParameterCollection;
           this.output = output;
           this.faultCollection = faultCollection;
           this.semanticMetadata = semanticMetadata;
           this.description = description;
           this.name = name;
    }


    /**
     * Gets the inputParameterCollection value for this Operation.
     * 
     * @return inputParameterCollection
     */
    public gov.nih.nci.cagrid.metadata.service.OperationInputParameterCollection getInputParameterCollection() {
        return inputParameterCollection;
    }


    /**
     * Sets the inputParameterCollection value for this Operation.
     * 
     * @param inputParameterCollection
     */
    public void setInputParameterCollection(gov.nih.nci.cagrid.metadata.service.OperationInputParameterCollection inputParameterCollection) {
        this.inputParameterCollection = inputParameterCollection;
    }


    /**
     * Gets the output value for this Operation.
     * 
     * @return output
     */
    public gov.nih.nci.cagrid.metadata.service.Output getOutput() {
        return output;
    }


    /**
     * Sets the output value for this Operation.
     * 
     * @param output
     */
    public void setOutput(gov.nih.nci.cagrid.metadata.service.Output output) {
        this.output = output;
    }


    /**
     * Gets the faultCollection value for this Operation.
     * 
     * @return faultCollection
     */
    public gov.nih.nci.cagrid.metadata.service.OperationFaultCollection getFaultCollection() {
        return faultCollection;
    }


    /**
     * Sets the faultCollection value for this Operation.
     * 
     * @param faultCollection
     */
    public void setFaultCollection(gov.nih.nci.cagrid.metadata.service.OperationFaultCollection faultCollection) {
        this.faultCollection = faultCollection;
    }


    /**
     * Gets the semanticMetadata value for this Operation.
     * 
     * @return semanticMetadata
     */
    public gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] getSemanticMetadata() {
        return semanticMetadata;
    }


    /**
     * Sets the semanticMetadata value for this Operation.
     * 
     * @param semanticMetadata
     */
    public void setSemanticMetadata(gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] semanticMetadata) {
        this.semanticMetadata = semanticMetadata;
    }

    public gov.nih.nci.cagrid.metadata.common.SemanticMetadata getSemanticMetadata(int i) {
        return this.semanticMetadata[i];
    }

    public void setSemanticMetadata(int i, gov.nih.nci.cagrid.metadata.common.SemanticMetadata _value) {
        this.semanticMetadata[i] = _value;
    }


    /**
     * Gets the description value for this Operation.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this Operation.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the name value for this Operation.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this Operation.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Operation)) return false;
        Operation other = (Operation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.inputParameterCollection==null && other.getInputParameterCollection()==null) || 
             (this.inputParameterCollection!=null &&
              this.inputParameterCollection.equals(other.getInputParameterCollection()))) &&
            ((this.output==null && other.getOutput()==null) || 
             (this.output!=null &&
              this.output.equals(other.getOutput()))) &&
            ((this.faultCollection==null && other.getFaultCollection()==null) || 
             (this.faultCollection!=null &&
              this.faultCollection.equals(other.getFaultCollection()))) &&
            ((this.semanticMetadata==null && other.getSemanticMetadata()==null) || 
             (this.semanticMetadata!=null &&
              java.util.Arrays.equals(this.semanticMetadata, other.getSemanticMetadata()))) &&
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
        if (getInputParameterCollection() != null) {
            _hashCode += getInputParameterCollection().hashCode();
        }
        if (getOutput() != null) {
            _hashCode += getOutput().hashCode();
        }
        if (getFaultCollection() != null) {
            _hashCode += getFaultCollection().hashCode();
        }
        if (getSemanticMetadata() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSemanticMetadata());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSemanticMetadata(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        new org.apache.axis.description.TypeDesc(Operation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", "Operation"));
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
        elemField.setFieldName("inputParameterCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", "inputParameterCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", ">Operation>inputParameterCollection"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("output");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", "Output"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", "Output"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faultCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", "faultCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.service", ">Operation>faultCollection"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("semanticMetadata");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "SemanticMetadata"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "SemanticMetadata"));
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

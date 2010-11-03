/**
 * UMLClass.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.metadata.common;


/**
 * caDSR-related
 * 
 * Represents the UML Class of the given input or output.
 */
public class UMLClass  implements java.io.Serializable {
    private gov.nih.nci.cagrid.metadata.common.UMLClassUmlAttributeCollection umlAttributeCollection;
    private gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] semanticMetadata;
    private java.lang.String className;  // attribute
    private java.lang.String description;  // attribute
    private java.lang.String id;  // attribute
    private java.lang.String packageName;  // attribute
    private java.lang.String projectName;  // attribute
    private java.lang.String projectVersion;  // attribute

    public UMLClass() {
    }

    public UMLClass(
           java.lang.String className,
           java.lang.String description,
           java.lang.String id,
           java.lang.String packageName,
           java.lang.String projectName,
           java.lang.String projectVersion,
           gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] semanticMetadata,
           gov.nih.nci.cagrid.metadata.common.UMLClassUmlAttributeCollection umlAttributeCollection) {
           this.umlAttributeCollection = umlAttributeCollection;
           this.semanticMetadata = semanticMetadata;
           this.className = className;
           this.description = description;
           this.id = id;
           this.packageName = packageName;
           this.projectName = projectName;
           this.projectVersion = projectVersion;
    }


    /**
     * Gets the umlAttributeCollection value for this UMLClass.
     * 
     * @return umlAttributeCollection
     */
    public gov.nih.nci.cagrid.metadata.common.UMLClassUmlAttributeCollection getUmlAttributeCollection() {
        return umlAttributeCollection;
    }


    /**
     * Sets the umlAttributeCollection value for this UMLClass.
     * 
     * @param umlAttributeCollection
     */
    public void setUmlAttributeCollection(gov.nih.nci.cagrid.metadata.common.UMLClassUmlAttributeCollection umlAttributeCollection) {
        this.umlAttributeCollection = umlAttributeCollection;
    }


    /**
     * Gets the semanticMetadata value for this UMLClass.
     * 
     * @return semanticMetadata
     */
    public gov.nih.nci.cagrid.metadata.common.SemanticMetadata[] getSemanticMetadata() {
        return semanticMetadata;
    }


    /**
     * Sets the semanticMetadata value for this UMLClass.
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
     * Gets the className value for this UMLClass.
     * 
     * @return className
     */
    public java.lang.String getClassName() {
        return className;
    }


    /**
     * Sets the className value for this UMLClass.
     * 
     * @param className
     */
    public void setClassName(java.lang.String className) {
        this.className = className;
    }


    /**
     * Gets the description value for this UMLClass.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this UMLClass.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the id value for this UMLClass.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this UMLClass.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the packageName value for this UMLClass.
     * 
     * @return packageName
     */
    public java.lang.String getPackageName() {
        return packageName;
    }


    /**
     * Sets the packageName value for this UMLClass.
     * 
     * @param packageName
     */
    public void setPackageName(java.lang.String packageName) {
        this.packageName = packageName;
    }


    /**
     * Gets the projectName value for this UMLClass.
     * 
     * @return projectName
     */
    public java.lang.String getProjectName() {
        return projectName;
    }


    /**
     * Sets the projectName value for this UMLClass.
     * 
     * @param projectName
     */
    public void setProjectName(java.lang.String projectName) {
        this.projectName = projectName;
    }


    /**
     * Gets the projectVersion value for this UMLClass.
     * 
     * @return projectVersion
     */
    public java.lang.String getProjectVersion() {
        return projectVersion;
    }


    /**
     * Sets the projectVersion value for this UMLClass.
     * 
     * @param projectVersion
     */
    public void setProjectVersion(java.lang.String projectVersion) {
        this.projectVersion = projectVersion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UMLClass)) return false;
        UMLClass other = (UMLClass) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.umlAttributeCollection==null && other.getUmlAttributeCollection()==null) || 
             (this.umlAttributeCollection!=null &&
              this.umlAttributeCollection.equals(other.getUmlAttributeCollection()))) &&
            ((this.semanticMetadata==null && other.getSemanticMetadata()==null) || 
             (this.semanticMetadata!=null &&
              java.util.Arrays.equals(this.semanticMetadata, other.getSemanticMetadata()))) &&
            ((this.className==null && other.getClassName()==null) || 
             (this.className!=null &&
              this.className.equals(other.getClassName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.packageName==null && other.getPackageName()==null) || 
             (this.packageName!=null &&
              this.packageName.equals(other.getPackageName()))) &&
            ((this.projectName==null && other.getProjectName()==null) || 
             (this.projectName!=null &&
              this.projectName.equals(other.getProjectName()))) &&
            ((this.projectVersion==null && other.getProjectVersion()==null) || 
             (this.projectVersion!=null &&
              this.projectVersion.equals(other.getProjectVersion())));
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
        if (getUmlAttributeCollection() != null) {
            _hashCode += getUmlAttributeCollection().hashCode();
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
        if (getClassName() != null) {
            _hashCode += getClassName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getPackageName() != null) {
            _hashCode += getPackageName().hashCode();
        }
        if (getProjectName() != null) {
            _hashCode += getProjectName().hashCode();
        }
        if (getProjectVersion() != null) {
            _hashCode += getProjectVersion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UMLClass.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "UMLClass"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("className");
        attrField.setXmlName(new javax.xml.namespace.QName("", "className"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("description");
        attrField.setXmlName(new javax.xml.namespace.QName("", "description"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("id");
        attrField.setXmlName(new javax.xml.namespace.QName("", "id"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("packageName");
        attrField.setXmlName(new javax.xml.namespace.QName("", "packageName"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("projectName");
        attrField.setXmlName(new javax.xml.namespace.QName("", "projectName"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("projectVersion");
        attrField.setXmlName(new javax.xml.namespace.QName("", "projectVersion"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("umlAttributeCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", "umlAttributeCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.common", ">UMLClass>umlAttributeCollection"));
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


/**
 * ENXP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:47 EDT)
 */
            
                package _21090.org.iso;
            

            /**
            *  ENXP bean class
            */
        
        public  class ENXP
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = ENXP
                Namespace URI = uri:iso.org:21090
                Namespace Prefix = ns1
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("uri:iso.org:21090")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for Value
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localValue ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getValue(){
                               return localValue;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Value
                               */
                               public void setValue(java.lang.String param){
                            
                                            this.localValue=param;
                                    

                               }
                            

                        /**
                        * field for Code
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localCode ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCode(){
                               return localCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Code
                               */
                               public void setCode(java.lang.String param){
                            
                                            this.localCode=param;
                                    

                               }
                            

                        /**
                        * field for CodeSystem
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localCodeSystem ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCodeSystem(){
                               return localCodeSystem;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CodeSystem
                               */
                               public void setCodeSystem(java.lang.String param){
                            
                                            this.localCodeSystem=param;
                                    

                               }
                            

                        /**
                        * field for CodeSystemVersion
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localCodeSystemVersion ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCodeSystemVersion(){
                               return localCodeSystemVersion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CodeSystemVersion
                               */
                               public void setCodeSystemVersion(java.lang.String param){
                            
                                            this.localCodeSystemVersion=param;
                                    

                               }
                            

                        /**
                        * field for Type
                        * This was an Attribute!
                        */

                        
                                    protected _21090.org.iso.EntityNamePartType localType ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.EntityNamePartType
                           */
                           public  _21090.org.iso.EntityNamePartType getType(){
                               return localType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Type
                               */
                               public void setType(_21090.org.iso.EntityNamePartType param){
                            
                                            this.localType=param;
                                    

                               }
                            

                        /**
                        * field for Qualifier
                        * This was an Attribute!
                        */

                        
                                    protected _21090.org.iso.Set_EntityNamePartQualifier localQualifier ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Set_EntityNamePartQualifier
                           */
                           public  _21090.org.iso.Set_EntityNamePartQualifier getQualifier(){
                               return localQualifier;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Qualifier
                               */
                               public void setQualifier(_21090.org.iso.Set_EntityNamePartQualifier param){
                            
                                            this.localQualifier=param;
                                    

                               }
                            

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       ENXP.this.serialize(parentQName,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               parentQName,factory,dataSource);
            
       }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       final org.apache.axiom.om.OMFactory factory,
                                       org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,factory,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();

                    if ((namespace != null) && (namespace.trim().length() > 0)) {
                        java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
                        if (writerPrefix != null) {
                            xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
                        } else {
                            if (prefix == null) {
                                prefix = generatePrefix(namespace);
                            }

                            xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
                            xmlWriter.writeNamespace(prefix, namespace);
                            xmlWriter.setPrefix(prefix, namespace);
                        }
                    } else {
                        xmlWriter.writeStartElement(parentQName.getLocalPart());
                    }
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"uri:iso.org:21090");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":ENXP",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "ENXP",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localValue != null){
                                        
                                                writeAttribute("",
                                                         "value",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localValue), xmlWriter);

                                            
                                      }
                                    
                                            if (localCode != null){
                                        
                                                writeAttribute("",
                                                         "code",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCode), xmlWriter);

                                            
                                      }
                                    
                                            if (localCodeSystem != null){
                                        
                                                writeAttribute("",
                                                         "codeSystem",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCodeSystem), xmlWriter);

                                            
                                      }
                                    
                                            if (localCodeSystemVersion != null){
                                        
                                                writeAttribute("",
                                                         "codeSystemVersion",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCodeSystemVersion), xmlWriter);

                                            
                                      }
                                    
                                    
                                    if (localType != null){
                                        writeAttribute("",
                                           "type",
                                           localType.toString(), xmlWriter);
                                    }
                                    
                                    
                                    if (localQualifier != null){
                                        writeAttribute("",
                                           "qualifier",
                                           localQualifier.toString(), xmlWriter);
                                    }
                                    
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


         /**
         * Register a namespace prefix
         */
         private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
                java.lang.String prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                        prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                    }

                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }

                return prefix;
            }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                
                            attribList.add(
                            new javax.xml.namespace.QName("","value"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localValue));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","code"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCode));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","codeSystem"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCodeSystem));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","codeSystemVersion"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCodeSystemVersion));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","type"));
                            
                                      attribList.add(localType.toString());
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","qualifier"));
                            
                                      attribList.add(localQualifier.toString());
                                

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static ENXP parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ENXP object =
                new ENXP();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"ENXP".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (ENXP)gov.nih.nci.clinicalconnector.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    // handle attribute "value"
                    java.lang.String tempAttribValue =
                        
                                reader.getAttributeValue(null,"value");
                            
                   if (tempAttribValue!=null){
                         java.lang.String content = tempAttribValue;
                        
                                                 object.setValue(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribValue));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("value");
                    
                    // handle attribute "code"
                    java.lang.String tempAttribCode =
                        
                                reader.getAttributeValue(null,"code");
                            
                   if (tempAttribCode!=null){
                         java.lang.String content = tempAttribCode;
                        
                                                 object.setCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribCode));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("code");
                    
                    // handle attribute "codeSystem"
                    java.lang.String tempAttribCodeSystem =
                        
                                reader.getAttributeValue(null,"codeSystem");
                            
                   if (tempAttribCodeSystem!=null){
                         java.lang.String content = tempAttribCodeSystem;
                        
                                                 object.setCodeSystem(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribCodeSystem));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("codeSystem");
                    
                    // handle attribute "codeSystemVersion"
                    java.lang.String tempAttribCodeSystemVersion =
                        
                                reader.getAttributeValue(null,"codeSystemVersion");
                            
                   if (tempAttribCodeSystemVersion!=null){
                         java.lang.String content = tempAttribCodeSystemVersion;
                        
                                                 object.setCodeSystemVersion(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribCodeSystemVersion));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("codeSystemVersion");
                    
                    // handle attribute "type"
                    java.lang.String tempAttribType =
                        
                                reader.getAttributeValue(null,"type");
                            
                   if (tempAttribType!=null){
                         java.lang.String content = tempAttribType;
                        
                                                  object.setType(
                                                        _21090.org.iso.EntityNamePartType.Factory.fromString(reader,tempAttribType));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("type");
                    
                    // handle attribute "qualifier"
                    java.lang.String tempAttribQualifier =
                        
                                reader.getAttributeValue(null,"qualifier");
                            
                   if (tempAttribQualifier!=null){
                         java.lang.String content = tempAttribQualifier;
                        
                                                  object.setQualifier(
                                                        _21090.org.iso.Set_EntityNamePartQualifier.Factory.fromString(reader,tempAttribQualifier));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("qualifier");
                    
                    
                    reader.next();
                



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          
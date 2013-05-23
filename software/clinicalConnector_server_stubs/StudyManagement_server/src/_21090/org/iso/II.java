/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
                package _21090.org.iso;
            

            /**
            *  II bean class
            */
        
        public  class II extends _21090.org.iso.ANY
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = II
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
                        * field for Root
                        * This was an Attribute!
                        */

                        
                                    protected _21090.org.iso.Uid localRoot ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Uid
                           */
                           public  _21090.org.iso.Uid getRoot(){
                               return localRoot;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Root
                               */
                               public void setRoot(_21090.org.iso.Uid param){
                            
                                            this.localRoot=param;
                                    

                               }
                            

                        /**
                        * field for Extension
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localExtension ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getExtension(){
                               return localExtension;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Extension
                               */
                               public void setExtension(java.lang.String param){
                            
                                            this.localExtension=param;
                                    

                               }
                            

                        /**
                        * field for IdentifierName
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localIdentifierName ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getIdentifierName(){
                               return localIdentifierName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param IdentifierName
                               */
                               public void setIdentifierName(java.lang.String param){
                            
                                            this.localIdentifierName=param;
                                    

                               }
                            

                        /**
                        * field for Displayable
                        * This was an Attribute!
                        */

                        
                                    protected boolean localDisplayable ;
                                

                           /**
                           * Auto generated getter method
                           * @return boolean
                           */
                           public  boolean getDisplayable(){
                               return localDisplayable;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Displayable
                               */
                               public void setDisplayable(boolean param){
                            
                                            this.localDisplayable=param;
                                    

                               }
                            

                        /**
                        * field for Scope
                        * This was an Attribute!
                        */

                        
                                    protected _21090.org.iso.IdentifierScope localScope ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.IdentifierScope
                           */
                           public  _21090.org.iso.IdentifierScope getScope(){
                               return localScope;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Scope
                               */
                               public void setScope(_21090.org.iso.IdentifierScope param){
                            
                                            this.localScope=param;
                                    

                               }
                            

                        /**
                        * field for Reliability
                        * This was an Attribute!
                        */

                        
                                    protected _21090.org.iso.IdentifierReliability localReliability ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.IdentifierReliability
                           */
                           public  _21090.org.iso.IdentifierReliability getReliability(){
                               return localReliability;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Reliability
                               */
                               public void setReliability(_21090.org.iso.IdentifierReliability param){
                            
                                            this.localReliability=param;
                                    

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
                       II.this.serialize(parentQName,factory,xmlWriter);
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
                

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"uri:iso.org:21090");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":II",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "II",
                           xmlWriter);
                   }

               
                                            if (localValidTimeLow != null){
                                        
                                                writeAttribute("",
                                                         "validTimeLow",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localValidTimeLow), xmlWriter);

                                            
                                      }
                                    
                                            if (localValidTimeHigh != null){
                                        
                                                writeAttribute("",
                                                         "validTimeHigh",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localValidTimeHigh), xmlWriter);

                                            
                                      }
                                    
                                    
                                    if (localControlActRoot != null){
                                        writeAttribute("",
                                           "controlActRoot",
                                           localControlActRoot.toString(), xmlWriter);
                                    }
                                    
                                            if (localControlActExtension != null){
                                        
                                                writeAttribute("",
                                                         "controlActExtension",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localControlActExtension), xmlWriter);

                                            
                                      }
                                    
                                    
                                    if (localNullFlavor != null){
                                        writeAttribute("",
                                           "nullFlavor",
                                           localNullFlavor.toString(), xmlWriter);
                                    }
                                    
                                            if (localFlavorId != null){
                                        
                                                writeAttribute("",
                                                         "flavorId",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFlavorId), xmlWriter);

                                            
                                      }
                                    
                                    
                                    if (localUpdateMode != null){
                                        writeAttribute("",
                                           "updateMode",
                                           localUpdateMode.toString(), xmlWriter);
                                    }
                                    
                                    
                                    if (localRoot != null){
                                        writeAttribute("",
                                           "root",
                                           localRoot.toString(), xmlWriter);
                                    }
                                    
                                            if (localExtension != null){
                                        
                                                writeAttribute("",
                                                         "extension",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localExtension), xmlWriter);

                                            
                                      }
                                    
                                            if (localIdentifierName != null){
                                        
                                                writeAttribute("",
                                                         "identifierName",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdentifierName), xmlWriter);

                                            
                                      }
                                    
                                                   if (true) {
                                               
                                                writeAttribute("",
                                                         "displayable",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDisplayable), xmlWriter);

                                            
                                      }
                                    
                                    
                                    if (localScope != null){
                                        writeAttribute("",
                                           "scope",
                                           localScope.toString(), xmlWriter);
                                    }
                                    
                                    
                                    if (localReliability != null){
                                        writeAttribute("",
                                           "reliability",
                                           localReliability.toString(), xmlWriter);
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

                
                    attribList.add(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema-instance","type"));
                    attribList.add(new javax.xml.namespace.QName("uri:iso.org:21090","II"));
                
                            attribList.add(
                            new javax.xml.namespace.QName("","validTimeLow"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localValidTimeLow));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","validTimeHigh"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localValidTimeHigh));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","controlActRoot"));
                            
                                      attribList.add(localControlActRoot.toString());
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","controlActExtension"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localControlActExtension));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","nullFlavor"));
                            
                                      attribList.add(localNullFlavor.toString());
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","flavorId"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFlavorId));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","updateMode"));
                            
                                      attribList.add(localUpdateMode.toString());
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","root"));
                            
                                      attribList.add(localRoot.toString());
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","extension"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localExtension));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","identifierName"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdentifierName));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","displayable"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDisplayable));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","scope"));
                            
                                      attribList.add(localScope.toString());
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","reliability"));
                            
                                      attribList.add(localReliability.toString());
                                

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
        public static II parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            II object =
                new II();

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
                    
                            if (!"II".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (II)gov.nih.nci.clinicalconnector.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    // handle attribute "validTimeLow"
                    java.lang.String tempAttribValidTimeLow =
                        
                                reader.getAttributeValue(null,"validTimeLow");
                            
                   if (tempAttribValidTimeLow!=null){
                         java.lang.String content = tempAttribValidTimeLow;
                        
                                                 object.setValidTimeLow(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribValidTimeLow));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("validTimeLow");
                    
                    // handle attribute "validTimeHigh"
                    java.lang.String tempAttribValidTimeHigh =
                        
                                reader.getAttributeValue(null,"validTimeHigh");
                            
                   if (tempAttribValidTimeHigh!=null){
                         java.lang.String content = tempAttribValidTimeHigh;
                        
                                                 object.setValidTimeHigh(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribValidTimeHigh));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("validTimeHigh");
                    
                    // handle attribute "controlActRoot"
                    java.lang.String tempAttribControlActRoot =
                        
                                reader.getAttributeValue(null,"controlActRoot");
                            
                   if (tempAttribControlActRoot!=null){
                         java.lang.String content = tempAttribControlActRoot;
                        
                                                  object.setControlActRoot(
                                                        _21090.org.iso.Uid.Factory.fromString(reader,tempAttribControlActRoot));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("controlActRoot");
                    
                    // handle attribute "controlActExtension"
                    java.lang.String tempAttribControlActExtension =
                        
                                reader.getAttributeValue(null,"controlActExtension");
                            
                   if (tempAttribControlActExtension!=null){
                         java.lang.String content = tempAttribControlActExtension;
                        
                                                 object.setControlActExtension(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribControlActExtension));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("controlActExtension");
                    
                    // handle attribute "nullFlavor"
                    java.lang.String tempAttribNullFlavor =
                        
                                reader.getAttributeValue(null,"nullFlavor");
                            
                   if (tempAttribNullFlavor!=null){
                         java.lang.String content = tempAttribNullFlavor;
                        
                                                  object.setNullFlavor(
                                                        _21090.org.iso.NullFlavor.Factory.fromString(reader,tempAttribNullFlavor));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("nullFlavor");
                    
                    // handle attribute "flavorId"
                    java.lang.String tempAttribFlavorId =
                        
                                reader.getAttributeValue(null,"flavorId");
                            
                   if (tempAttribFlavorId!=null){
                         java.lang.String content = tempAttribFlavorId;
                        
                                                 object.setFlavorId(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribFlavorId));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("flavorId");
                    
                    // handle attribute "updateMode"
                    java.lang.String tempAttribUpdateMode =
                        
                                reader.getAttributeValue(null,"updateMode");
                            
                   if (tempAttribUpdateMode!=null){
                         java.lang.String content = tempAttribUpdateMode;
                        
                                                  object.setUpdateMode(
                                                        _21090.org.iso.UpdateMode.Factory.fromString(reader,tempAttribUpdateMode));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("updateMode");
                    
                    // handle attribute "root"
                    java.lang.String tempAttribRoot =
                        
                                reader.getAttributeValue(null,"root");
                            
                   if (tempAttribRoot!=null){
                         java.lang.String content = tempAttribRoot;
                        
                                                  object.setRoot(
                                                        _21090.org.iso.Uid.Factory.fromString(reader,tempAttribRoot));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("root");
                    
                    // handle attribute "extension"
                    java.lang.String tempAttribExtension =
                        
                                reader.getAttributeValue(null,"extension");
                            
                   if (tempAttribExtension!=null){
                         java.lang.String content = tempAttribExtension;
                        
                                                 object.setExtension(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribExtension));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("extension");
                    
                    // handle attribute "identifierName"
                    java.lang.String tempAttribIdentifierName =
                        
                                reader.getAttributeValue(null,"identifierName");
                            
                   if (tempAttribIdentifierName!=null){
                         java.lang.String content = tempAttribIdentifierName;
                        
                                                 object.setIdentifierName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribIdentifierName));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("identifierName");
                    
                    // handle attribute "displayable"
                    java.lang.String tempAttribDisplayable =
                        
                                reader.getAttributeValue(null,"displayable");
                            
                   if (tempAttribDisplayable!=null){
                         java.lang.String content = tempAttribDisplayable;
                        
                                                 object.setDisplayable(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(tempAttribDisplayable));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("displayable");
                    
                    // handle attribute "scope"
                    java.lang.String tempAttribScope =
                        
                                reader.getAttributeValue(null,"scope");
                            
                   if (tempAttribScope!=null){
                         java.lang.String content = tempAttribScope;
                        
                                                  object.setScope(
                                                        _21090.org.iso.IdentifierScope.Factory.fromString(reader,tempAttribScope));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("scope");
                    
                    // handle attribute "reliability"
                    java.lang.String tempAttribReliability =
                        
                                reader.getAttributeValue(null,"reliability");
                            
                   if (tempAttribReliability!=null){
                         java.lang.String content = tempAttribReliability;
                        
                                                  object.setReliability(
                                                        _21090.org.iso.IdentifierReliability.Factory.fromString(reader,tempAttribReliability));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("reliability");
                    
                    
                    reader.next();
                



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          

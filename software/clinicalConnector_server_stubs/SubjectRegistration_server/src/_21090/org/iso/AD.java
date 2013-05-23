/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
                package _21090.org.iso;
            

            /**
            *  AD bean class
            */
        
        public  class AD extends _21090.org.iso.ANY
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = AD
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
                        * field for Part
                        * This was an Array!
                        */

                        
                                    protected _21090.org.iso.ADXP[] localPart ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPartTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.ADXP[]
                           */
                           public  _21090.org.iso.ADXP[] getPart(){
                               return localPart;
                           }

                           
                        


                               
                              /**
                               * validate the array for Part
                               */
                              protected void validatePart(_21090.org.iso.ADXP[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Part
                              */
                              public void setPart(_21090.org.iso.ADXP[] param){
                              
                                   validatePart(param);

                               
                                          if (param != null){
                                             //update the setting tracker
                                             localPartTracker = true;
                                          } else {
                                             localPartTracker = false;
                                                 
                                          }
                                      
                                      this.localPart=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param _21090.org.iso.ADXP
                             */
                             public void addPart(_21090.org.iso.ADXP param){
                                   if (localPart == null){
                                   localPart = new _21090.org.iso.ADXP[]{};
                                   }

                            
                                 //update the setting tracker
                                localPartTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localPart);
                               list.add(param);
                               this.localPart =
                             (_21090.org.iso.ADXP[])list.toArray(
                            new _21090.org.iso.ADXP[list.size()]);

                             }
                             

                        /**
                        * field for UseablePeriod
                        */

                        
                                    protected _21090.org.iso.QSET_TS localUseablePeriod ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localUseablePeriodTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.QSET_TS
                           */
                           public  _21090.org.iso.QSET_TS getUseablePeriod(){
                               return localUseablePeriod;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param UseablePeriod
                               */
                               public void setUseablePeriod(_21090.org.iso.QSET_TS param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localUseablePeriodTracker = true;
                                       } else {
                                          localUseablePeriodTracker = false;
                                              
                                       }
                                   
                                            this.localUseablePeriod=param;
                                    

                               }
                            

                        /**
                        * field for Use
                        * This was an Attribute!
                        */

                        
                                    protected _21090.org.iso.Set_PostalAddressUse localUse ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Set_PostalAddressUse
                           */
                           public  _21090.org.iso.Set_PostalAddressUse getUse(){
                               return localUse;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Use
                               */
                               public void setUse(_21090.org.iso.Set_PostalAddressUse param){
                            
                                            this.localUse=param;
                                    

                               }
                            

                        /**
                        * field for IsNotOrdered
                        * This was an Attribute!
                        */

                        
                                    protected boolean localIsNotOrdered ;
                                

                           /**
                           * Auto generated getter method
                           * @return boolean
                           */
                           public  boolean getIsNotOrdered(){
                               return localIsNotOrdered;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param IsNotOrdered
                               */
                               public void setIsNotOrdered(boolean param){
                            
                                            this.localIsNotOrdered=param;
                                    

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
                       AD.this.serialize(parentQName,factory,xmlWriter);
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
                           namespacePrefix+":AD",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "AD",
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
                                    
                                    
                                    if (localUse != null){
                                        writeAttribute("",
                                           "use",
                                           localUse.toString(), xmlWriter);
                                    }
                                    
                                                   if (true) {
                                               
                                                writeAttribute("",
                                                         "isNotOrdered",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsNotOrdered), xmlWriter);

                                            
                                      }
                                     if (localPartTracker){
                                       if (localPart!=null){
                                            for (int i = 0;i < localPart.length;i++){
                                                if (localPart[i] != null){
                                                 localPart[i].serialize(new javax.xml.namespace.QName("uri:iso.org:21090","part"),
                                                           factory,xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("part cannot be null!!");
                                        
                                    }
                                 } if (localUseablePeriodTracker){
                                            if (localUseablePeriod==null){
                                                 throw new org.apache.axis2.databinding.ADBException("useablePeriod cannot be null!!");
                                            }
                                           localUseablePeriod.serialize(new javax.xml.namespace.QName("uri:iso.org:21090","useablePeriod"),
                                               factory,xmlWriter);
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
                    attribList.add(new javax.xml.namespace.QName("uri:iso.org:21090","AD"));
                 if (localPartTracker){
                             if (localPart!=null) {
                                 for (int i = 0;i < localPart.length;i++){

                                    if (localPart[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                          "part"));
                                         elementList.add(localPart[i]);
                                    } else {
                                        
                                                // nothing to do
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("part cannot be null!!");
                                    
                             }

                        } if (localUseablePeriodTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "useablePeriod"));
                            
                            
                                    if (localUseablePeriod==null){
                                         throw new org.apache.axis2.databinding.ADBException("useablePeriod cannot be null!!");
                                    }
                                    elementList.add(localUseablePeriod);
                                }
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
                            new javax.xml.namespace.QName("","use"));
                            
                                      attribList.add(localUse.toString());
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","isNotOrdered"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsNotOrdered));
                                

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
        public static AD parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            AD object =
                new AD();

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
                    
                            if (!"AD".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (AD)gov.nih.nci.clinicalconnector.ExtensionMapper.getTypeObject(
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
                    
                    // handle attribute "use"
                    java.lang.String tempAttribUse =
                        
                                reader.getAttributeValue(null,"use");
                            
                   if (tempAttribUse!=null){
                         java.lang.String content = tempAttribUse;
                        
                                                  object.setUse(
                                                        _21090.org.iso.Set_PostalAddressUse.Factory.fromString(reader,tempAttribUse));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("use");
                    
                    // handle attribute "isNotOrdered"
                    java.lang.String tempAttribIsNotOrdered =
                        
                                reader.getAttributeValue(null,"isNotOrdered");
                            
                   if (tempAttribIsNotOrdered!=null){
                         java.lang.String content = tempAttribIsNotOrdered;
                        
                                                 object.setIsNotOrdered(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(tempAttribIsNotOrdered));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("isNotOrdered");
                    
                    
                    reader.next();
                
                        java.util.ArrayList list1 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","part").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list1.add(_21090.org.iso.ADXP.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone1 = false;
                                                        while(!loopDone1){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone1 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("uri:iso.org:21090","part").equals(reader.getName())){
                                                                    list1.add(_21090.org.iso.ADXP.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone1 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setPart((_21090.org.iso.ADXP[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                _21090.org.iso.ADXP.class,
                                                                list1));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","useablePeriod").equals(reader.getName())){
                                
                                                object.setUseablePeriod(_21090.org.iso.QSET_TS.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          

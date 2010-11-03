
/**
 * ED.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:47 EDT)
 */
            
                package _21090.org.iso;
            

            /**
            *  ED bean class
            */
        
        public  class ED extends _21090.org.iso.ANY
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = ED
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
                        * field for Data
                        */

                        
                                    protected javax.activation.DataHandler localData ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDataTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return javax.activation.DataHandler
                           */
                           public  javax.activation.DataHandler getData(){
                               return localData;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Data
                               */
                               public void setData(javax.activation.DataHandler param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localDataTracker = true;
                                       } else {
                                          localDataTracker = false;
                                              
                                       }
                                   
                                            this.localData=param;
                                    

                               }
                            

                        /**
                        * field for Xml
                        */

                        
                                    protected java.lang.Object localXml ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localXmlTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.Object
                           */
                           public  java.lang.Object getXml(){
                               return localXml;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Xml
                               */
                               public void setXml(java.lang.Object param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localXmlTracker = true;
                                       } else {
                                          localXmlTracker = false;
                                              
                                       }
                                   
                                            this.localXml=param;
                                    

                               }
                            

                        /**
                        * field for Reference
                        */

                        
                                    protected _21090.org.iso.TEL localReference ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localReferenceTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.TEL
                           */
                           public  _21090.org.iso.TEL getReference(){
                               return localReference;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Reference
                               */
                               public void setReference(_21090.org.iso.TEL param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localReferenceTracker = true;
                                       } else {
                                          localReferenceTracker = false;
                                              
                                       }
                                   
                                            this.localReference=param;
                                    

                               }
                            

                        /**
                        * field for IntegrityCheck
                        */

                        
                                    protected javax.activation.DataHandler localIntegrityCheck ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localIntegrityCheckTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return javax.activation.DataHandler
                           */
                           public  javax.activation.DataHandler getIntegrityCheck(){
                               return localIntegrityCheck;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param IntegrityCheck
                               */
                               public void setIntegrityCheck(javax.activation.DataHandler param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localIntegrityCheckTracker = true;
                                       } else {
                                          localIntegrityCheckTracker = false;
                                              
                                       }
                                   
                                            this.localIntegrityCheck=param;
                                    

                               }
                            

                        /**
                        * field for Thumbnail
                        */

                        
                                    protected _21090.org.iso.ED localThumbnail ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localThumbnailTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.ED
                           */
                           public  _21090.org.iso.ED getThumbnail(){
                               return localThumbnail;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Thumbnail
                               */
                               public void setThumbnail(_21090.org.iso.ED param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localThumbnailTracker = true;
                                       } else {
                                          localThumbnailTracker = false;
                                              
                                       }
                                   
                                            this.localThumbnail=param;
                                    

                               }
                            

                        /**
                        * field for Translation
                        * This was an Array!
                        */

                        
                                    protected _21090.org.iso.ED[] localTranslation ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTranslationTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.ED[]
                           */
                           public  _21090.org.iso.ED[] getTranslation(){
                               return localTranslation;
                           }

                           
                        


                               
                              /**
                               * validate the array for Translation
                               */
                              protected void validateTranslation(_21090.org.iso.ED[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Translation
                              */
                              public void setTranslation(_21090.org.iso.ED[] param){
                              
                                   validateTranslation(param);

                               
                                          if (param != null){
                                             //update the setting tracker
                                             localTranslationTracker = true;
                                          } else {
                                             localTranslationTracker = false;
                                                 
                                          }
                                      
                                      this.localTranslation=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param _21090.org.iso.ED
                             */
                             public void addTranslation(_21090.org.iso.ED param){
                                   if (localTranslation == null){
                                   localTranslation = new _21090.org.iso.ED[]{};
                                   }

                            
                                 //update the setting tracker
                                localTranslationTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localTranslation);
                               list.add(param);
                               this.localTranslation =
                             (_21090.org.iso.ED[])list.toArray(
                            new _21090.org.iso.ED[list.size()]);

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
                        * field for MediaType
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localMediaType =
                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString("text/plain");
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMediaType(){
                               return localMediaType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MediaType
                               */
                               public void setMediaType(java.lang.String param){
                            
                                            this.localMediaType=param;
                                    

                               }
                            

                        /**
                        * field for Charset
                        * This was an Attribute!
                        */

                        
                                    protected _21090.org.iso.Code localCharset ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Code
                           */
                           public  _21090.org.iso.Code getCharset(){
                               return localCharset;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Charset
                               */
                               public void setCharset(_21090.org.iso.Code param){
                            
                                            this.localCharset=param;
                                    

                               }
                            

                        /**
                        * field for Language
                        * This was an Attribute!
                        */

                        
                                    protected _21090.org.iso.Code localLanguage ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Code
                           */
                           public  _21090.org.iso.Code getLanguage(){
                               return localLanguage;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Language
                               */
                               public void setLanguage(_21090.org.iso.Code param){
                            
                                            this.localLanguage=param;
                                    

                               }
                            

                        /**
                        * field for Compression
                        * This was an Attribute!
                        */

                        
                                    protected _21090.org.iso.Compression localCompression ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Compression
                           */
                           public  _21090.org.iso.Compression getCompression(){
                               return localCompression;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Compression
                               */
                               public void setCompression(_21090.org.iso.Compression param){
                            
                                            this.localCompression=param;
                                    

                               }
                            

                        /**
                        * field for IntegrityCheckAlgorithm
                        * This was an Attribute!
                        */

                        
                                    protected _21090.org.iso.IntegrityCheckAlgorithm localIntegrityCheckAlgorithm ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.IntegrityCheckAlgorithm
                           */
                           public  _21090.org.iso.IntegrityCheckAlgorithm getIntegrityCheckAlgorithm(){
                               return localIntegrityCheckAlgorithm;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param IntegrityCheckAlgorithm
                               */
                               public void setIntegrityCheckAlgorithm(_21090.org.iso.IntegrityCheckAlgorithm param){
                            
                                            this.localIntegrityCheckAlgorithm=param;
                                    

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
                       ED.this.serialize(parentQName,factory,xmlWriter);
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
                           namespacePrefix+":ED",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "ED",
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
                                    
                                            if (localValue != null){
                                        
                                                writeAttribute("",
                                                         "value",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localValue), xmlWriter);

                                            
                                      }
                                    
                                            if (localMediaType != null){
                                        
                                                writeAttribute("",
                                                         "mediaType",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMediaType), xmlWriter);

                                            
                                      }
                                    
                                    
                                    if (localCharset != null){
                                        writeAttribute("",
                                           "charset",
                                           localCharset.toString(), xmlWriter);
                                    }
                                    
                                    
                                    if (localLanguage != null){
                                        writeAttribute("",
                                           "language",
                                           localLanguage.toString(), xmlWriter);
                                    }
                                    
                                    
                                    if (localCompression != null){
                                        writeAttribute("",
                                           "compression",
                                           localCompression.toString(), xmlWriter);
                                    }
                                    
                                    
                                    if (localIntegrityCheckAlgorithm != null){
                                        writeAttribute("",
                                           "integrityCheckAlgorithm",
                                           localIntegrityCheckAlgorithm.toString(), xmlWriter);
                                    }
                                     if (localDataTracker){
                                    namespace = "uri:iso.org:21090";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"data", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"data");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("data");
                                    }
                                
                                        
                                    if (localData!=null)
                                    {
                                       xmlWriter.writeDataHandler(localData);
                                    }
                                 
                                   xmlWriter.writeEndElement();
                             } if (localXmlTracker){
                            
                            if (localXml!=null){
                                if (localXml instanceof org.apache.axis2.databinding.ADBBean){
                                    ((org.apache.axis2.databinding.ADBBean)localXml).serialize(
                                               new javax.xml.namespace.QName("uri:iso.org:21090","xml"),
                                               factory,xmlWriter,true);
                                 } else {
                                    java.lang.String namespace2 = "uri:iso.org:21090";
                                    if (! namespace2.equals("")) {
                                        java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                                        if (prefix2 == null) {
                                            prefix2 = generatePrefix(namespace2);

                                            xmlWriter.writeStartElement(prefix2,"xml", namespace2);
                                            xmlWriter.writeNamespace(prefix2, namespace2);
                                            xmlWriter.setPrefix(prefix2, namespace2);

                                        } else {
                                            xmlWriter.writeStartElement(namespace2,"xml");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("xml");
                                    }
                                    org.apache.axis2.databinding.utils.ConverterUtil.serializeAnyType(localXml, xmlWriter);
                                    xmlWriter.writeEndElement();
                                 }
                            } else {
                                
                                         throw new org.apache.axis2.databinding.ADBException("xml cannot be null!!");
                                    
                            }


                        } if (localReferenceTracker){
                                            if (localReference==null){
                                                 throw new org.apache.axis2.databinding.ADBException("reference cannot be null!!");
                                            }
                                           localReference.serialize(new javax.xml.namespace.QName("uri:iso.org:21090","reference"),
                                               factory,xmlWriter);
                                        } if (localIntegrityCheckTracker){
                                    namespace = "uri:iso.org:21090";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"integrityCheck", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"integrityCheck");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("integrityCheck");
                                    }
                                
                                        
                                    if (localIntegrityCheck!=null)
                                    {
                                       xmlWriter.writeDataHandler(localIntegrityCheck);
                                    }
                                 
                                   xmlWriter.writeEndElement();
                             } if (localThumbnailTracker){
                                            if (localThumbnail==null){
                                                 throw new org.apache.axis2.databinding.ADBException("thumbnail cannot be null!!");
                                            }
                                           localThumbnail.serialize(new javax.xml.namespace.QName("uri:iso.org:21090","thumbnail"),
                                               factory,xmlWriter);
                                        } if (localTranslationTracker){
                                       if (localTranslation!=null){
                                            for (int i = 0;i < localTranslation.length;i++){
                                                if (localTranslation[i] != null){
                                                 localTranslation[i].serialize(new javax.xml.namespace.QName("uri:iso.org:21090","translation"),
                                                           factory,xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("translation cannot be null!!");
                                        
                                    }
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
                    attribList.add(new javax.xml.namespace.QName("uri:iso.org:21090","ED"));
                 if (localDataTracker){
                                      elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                        "data"));
                                
                            elementList.add(localData);
                        } if (localXmlTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "xml"));
                            
                            
                                    if (localXml==null){
                                         throw new org.apache.axis2.databinding.ADBException("xml cannot be null!!");
                                    }
                                    elementList.add(localXml);
                                } if (localReferenceTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "reference"));
                            
                            
                                    if (localReference==null){
                                         throw new org.apache.axis2.databinding.ADBException("reference cannot be null!!");
                                    }
                                    elementList.add(localReference);
                                } if (localIntegrityCheckTracker){
                                      elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                        "integrityCheck"));
                                
                            elementList.add(localIntegrityCheck);
                        } if (localThumbnailTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "thumbnail"));
                            
                            
                                    if (localThumbnail==null){
                                         throw new org.apache.axis2.databinding.ADBException("thumbnail cannot be null!!");
                                    }
                                    elementList.add(localThumbnail);
                                } if (localTranslationTracker){
                             if (localTranslation!=null) {
                                 for (int i = 0;i < localTranslation.length;i++){

                                    if (localTranslation[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                          "translation"));
                                         elementList.add(localTranslation[i]);
                                    } else {
                                        
                                                // nothing to do
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("translation cannot be null!!");
                                    
                             }

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
                            new javax.xml.namespace.QName("","value"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localValue));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","mediaType"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMediaType));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","charset"));
                            
                                      attribList.add(localCharset.toString());
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","language"));
                            
                                      attribList.add(localLanguage.toString());
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","compression"));
                            
                                      attribList.add(localCompression.toString());
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","integrityCheckAlgorithm"));
                            
                                      attribList.add(localIntegrityCheckAlgorithm.toString());
                                

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
        public static ED parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ED object =
                new ED();

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
                    
                            if (!"ED".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (ED)gov.nih.nci.clinicalconnector.ExtensionMapper.getTypeObject(
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
                    
                    // handle attribute "mediaType"
                    java.lang.String tempAttribMediaType =
                        
                                reader.getAttributeValue(null,"mediaType");
                            
                   if (tempAttribMediaType!=null){
                         java.lang.String content = tempAttribMediaType;
                        
                                                 object.setMediaType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribMediaType));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("mediaType");
                    
                    // handle attribute "charset"
                    java.lang.String tempAttribCharset =
                        
                                reader.getAttributeValue(null,"charset");
                            
                   if (tempAttribCharset!=null){
                         java.lang.String content = tempAttribCharset;
                        
                                                  object.setCharset(
                                                        _21090.org.iso.Code.Factory.fromString(reader,tempAttribCharset));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("charset");
                    
                    // handle attribute "language"
                    java.lang.String tempAttribLanguage =
                        
                                reader.getAttributeValue(null,"language");
                            
                   if (tempAttribLanguage!=null){
                         java.lang.String content = tempAttribLanguage;
                        
                                                  object.setLanguage(
                                                        _21090.org.iso.Code.Factory.fromString(reader,tempAttribLanguage));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("language");
                    
                    // handle attribute "compression"
                    java.lang.String tempAttribCompression =
                        
                                reader.getAttributeValue(null,"compression");
                            
                   if (tempAttribCompression!=null){
                         java.lang.String content = tempAttribCompression;
                        
                                                  object.setCompression(
                                                        _21090.org.iso.Compression.Factory.fromString(reader,tempAttribCompression));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("compression");
                    
                    // handle attribute "integrityCheckAlgorithm"
                    java.lang.String tempAttribIntegrityCheckAlgorithm =
                        
                                reader.getAttributeValue(null,"integrityCheckAlgorithm");
                            
                   if (tempAttribIntegrityCheckAlgorithm!=null){
                         java.lang.String content = tempAttribIntegrityCheckAlgorithm;
                        
                                                  object.setIntegrityCheckAlgorithm(
                                                        _21090.org.iso.IntegrityCheckAlgorithm.Factory.fromString(reader,tempAttribIntegrityCheckAlgorithm));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("integrityCheckAlgorithm");
                    
                    
                    reader.next();
                
                        java.util.ArrayList list6 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","data").equals(reader.getName())){
                                reader.next();
                                    if (isReaderMTOMAware(reader)
                                            &&
                                            java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_BINARY)))
                                    {
                                        //MTOM aware reader - get the datahandler directly and put it in the object
                                        object.setData(
                                                (javax.activation.DataHandler) reader.getProperty(org.apache.axiom.om.OMConstants.DATA_HANDLER));
                                    } else {
                                        if (reader.getEventType() == javax.xml.stream.XMLStreamConstants.START_ELEMENT && reader.getName().equals(new javax.xml.namespace.QName(org.apache.axiom.om.impl.MTOMConstants.XOP_NAMESPACE_URI, org.apache.axiom.om.impl.MTOMConstants.XOP_INCLUDE)))
                                        {
                                            java.lang.String id = org.apache.axiom.om.util.ElementHelper.getContentID(reader, "UTF-8");
                                            object.setData(((org.apache.axiom.soap.impl.builder.MTOMStAXSOAPModelBuilder) ((org.apache.axiom.om.impl.llom.OMStAXWrapper) reader).getBuilder()).getDataHandler(id));
                                            reader.next();
                                            
                                                reader.next();
                                            
                                        } else if(reader.hasText()) {
                                            //Do the usual conversion
                                            java.lang.String content = reader.getText();
                                            object.setData(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToBase64Binary(content));
                                            
                                                reader.next();
                                            
                                        }
                                    }

                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","xml").equals(reader.getName())){
                                
                                     object.setXml(org.apache.axis2.databinding.utils.ConverterUtil.getAnyTypeObject(reader,
                                                gov.nih.nci.clinicalconnector.ExtensionMapper.class));
                                       
                                         reader.next();
                                     
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","reference").equals(reader.getName())){
                                
                                                object.setReference(_21090.org.iso.TEL.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","integrityCheck").equals(reader.getName())){
                                reader.next();
                                    if (isReaderMTOMAware(reader)
                                            &&
                                            java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_BINARY)))
                                    {
                                        //MTOM aware reader - get the datahandler directly and put it in the object
                                        object.setIntegrityCheck(
                                                (javax.activation.DataHandler) reader.getProperty(org.apache.axiom.om.OMConstants.DATA_HANDLER));
                                    } else {
                                        if (reader.getEventType() == javax.xml.stream.XMLStreamConstants.START_ELEMENT && reader.getName().equals(new javax.xml.namespace.QName(org.apache.axiom.om.impl.MTOMConstants.XOP_NAMESPACE_URI, org.apache.axiom.om.impl.MTOMConstants.XOP_INCLUDE)))
                                        {
                                            java.lang.String id = org.apache.axiom.om.util.ElementHelper.getContentID(reader, "UTF-8");
                                            object.setIntegrityCheck(((org.apache.axiom.soap.impl.builder.MTOMStAXSOAPModelBuilder) ((org.apache.axiom.om.impl.llom.OMStAXWrapper) reader).getBuilder()).getDataHandler(id));
                                            reader.next();
                                            
                                                reader.next();
                                            
                                        } else if(reader.hasText()) {
                                            //Do the usual conversion
                                            java.lang.String content = reader.getText();
                                            object.setIntegrityCheck(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToBase64Binary(content));
                                            
                                                reader.next();
                                            
                                        }
                                    }

                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","thumbnail").equals(reader.getName())){
                                
                                                object.setThumbnail(_21090.org.iso.ED.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","translation").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list6.add(_21090.org.iso.ED.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone6 = false;
                                                        while(!loopDone6){
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
                                                                loopDone6 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("uri:iso.org:21090","translation").equals(reader.getName())){
                                                                    list6.add(_21090.org.iso.ED.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone6 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setTranslation((_21090.org.iso.ED[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                _21090.org.iso.ED.class,
                                                                list6));
                                                            
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
           
          
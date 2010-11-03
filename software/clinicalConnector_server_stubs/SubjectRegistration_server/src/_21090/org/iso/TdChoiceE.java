
/**
 * TdChoiceE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:41 LKT)
 */
            
                package _21090.org.iso;
            

            /**
            *  TdChoiceE bean class
            */
        
        public  class TdChoiceE
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = tdChoice
                Namespace URI = uri:iso.org:21090
                Namespace Prefix = ns1
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("uri:iso.org:21090")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        
            /** Whenever a new property is set ensure all others are unset
             *  There can be only one choice and the last one wins
             */
            private void clearAllSettingTrackers() {
            
                   localContentTracker = false;
                
                   localLinkHtmlTracker = false;
                
                   localSubTracker = false;
                
                   localSupTracker = false;
                
                   localBrTracker = false;
                
                   localFootnoteTracker = false;
                
                   localFootnoteRefTracker = false;
                
                   localRenderMultiMediaTracker = false;
                
                   localParagraphTracker = false;
                
                   localListTracker = false;
                
            }
        

                        /**
                        * field for Content
                        */

                        
                                    protected _21090.org.iso.Content_type0 localContent ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localContentTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Content_type0
                           */
                           public  _21090.org.iso.Content_type0 getContent(){
                               return localContent;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Content
                               */
                               public void setContent(_21090.org.iso.Content_type0 param){
                            
                                clearAllSettingTrackers();
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localContentTracker = true;
                                       } else {
                                          localContentTracker = false;
                                              
                                       }
                                   
                                            this.localContent=param;
                                    

                               }
                            

                        /**
                        * field for LinkHtml
                        */

                        
                                    protected _21090.org.iso.LinkHtml_type0 localLinkHtml ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLinkHtmlTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.LinkHtml_type0
                           */
                           public  _21090.org.iso.LinkHtml_type0 getLinkHtml(){
                               return localLinkHtml;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param LinkHtml
                               */
                               public void setLinkHtml(_21090.org.iso.LinkHtml_type0 param){
                            
                                clearAllSettingTrackers();
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localLinkHtmlTracker = true;
                                       } else {
                                          localLinkHtmlTracker = false;
                                              
                                       }
                                   
                                            this.localLinkHtml=param;
                                    

                               }
                            

                        /**
                        * field for Sub
                        */

                        
                                    protected java.lang.String localSub ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSubTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSub(){
                               return localSub;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Sub
                               */
                               public void setSub(java.lang.String param){
                            
                                clearAllSettingTrackers();
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localSubTracker = true;
                                       } else {
                                          localSubTracker = false;
                                              
                                       }
                                   
                                            this.localSub=param;
                                    

                               }
                            

                        /**
                        * field for Sup
                        */

                        
                                    protected java.lang.String localSup ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSupTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSup(){
                               return localSup;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Sup
                               */
                               public void setSup(java.lang.String param){
                            
                                clearAllSettingTrackers();
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localSupTracker = true;
                                       } else {
                                          localSupTracker = false;
                                              
                                       }
                                   
                                            this.localSup=param;
                                    

                               }
                            

                        /**
                        * field for Br
                        */

                        
                                    protected _21090.org.iso.Br_type0 localBr ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBrTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Br_type0
                           */
                           public  _21090.org.iso.Br_type0 getBr(){
                               return localBr;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Br
                               */
                               public void setBr(_21090.org.iso.Br_type0 param){
                            
                                clearAllSettingTrackers();
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localBrTracker = true;
                                       } else {
                                          localBrTracker = false;
                                              
                                       }
                                   
                                            this.localBr=param;
                                    

                               }
                            

                        /**
                        * field for Footnote
                        */

                        
                                    protected _21090.org.iso.Footnote_type0 localFootnote ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFootnoteTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Footnote_type0
                           */
                           public  _21090.org.iso.Footnote_type0 getFootnote(){
                               return localFootnote;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Footnote
                               */
                               public void setFootnote(_21090.org.iso.Footnote_type0 param){
                            
                                clearAllSettingTrackers();
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localFootnoteTracker = true;
                                       } else {
                                          localFootnoteTracker = false;
                                              
                                       }
                                   
                                            this.localFootnote=param;
                                    

                               }
                            

                        /**
                        * field for FootnoteRef
                        */

                        
                                    protected _21090.org.iso.FootnoteRef_type0 localFootnoteRef ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFootnoteRefTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.FootnoteRef_type0
                           */
                           public  _21090.org.iso.FootnoteRef_type0 getFootnoteRef(){
                               return localFootnoteRef;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param FootnoteRef
                               */
                               public void setFootnoteRef(_21090.org.iso.FootnoteRef_type0 param){
                            
                                clearAllSettingTrackers();
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localFootnoteRefTracker = true;
                                       } else {
                                          localFootnoteRefTracker = false;
                                              
                                       }
                                   
                                            this.localFootnoteRef=param;
                                    

                               }
                            

                        /**
                        * field for RenderMultiMedia
                        */

                        
                                    protected _21090.org.iso.RenderMultiMedia_type0 localRenderMultiMedia ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localRenderMultiMediaTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.RenderMultiMedia_type0
                           */
                           public  _21090.org.iso.RenderMultiMedia_type0 getRenderMultiMedia(){
                               return localRenderMultiMedia;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param RenderMultiMedia
                               */
                               public void setRenderMultiMedia(_21090.org.iso.RenderMultiMedia_type0 param){
                            
                                clearAllSettingTrackers();
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localRenderMultiMediaTracker = true;
                                       } else {
                                          localRenderMultiMediaTracker = false;
                                              
                                       }
                                   
                                            this.localRenderMultiMedia=param;
                                    

                               }
                            

                        /**
                        * field for Paragraph
                        */

                        
                                    protected _21090.org.iso.Paragraph_type0 localParagraph ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localParagraphTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Paragraph_type0
                           */
                           public  _21090.org.iso.Paragraph_type0 getParagraph(){
                               return localParagraph;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Paragraph
                               */
                               public void setParagraph(_21090.org.iso.Paragraph_type0 param){
                            
                                clearAllSettingTrackers();
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localParagraphTracker = true;
                                       } else {
                                          localParagraphTracker = false;
                                              
                                       }
                                   
                                            this.localParagraph=param;
                                    

                               }
                            

                        /**
                        * field for List
                        */

                        
                                    protected _21090.org.iso.List_type0 localList ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localListTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.List_type0
                           */
                           public  _21090.org.iso.List_type0 getList(){
                               return localList;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param List
                               */
                               public void setList(_21090.org.iso.List_type0 param){
                            
                                clearAllSettingTrackers();
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localListTracker = true;
                                       } else {
                                          localListTracker = false;
                                              
                                       }
                                   
                                            this.localList=param;
                                    

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
                       TdChoiceE.this.serialize(parentQName,factory,xmlWriter);
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
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"uri:iso.org:21090");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":tdChoice",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "tdChoice",
                           xmlWriter);
                   }

               
                   }
                if (localContentTracker){
                                            if (localContent==null){
                                                 throw new org.apache.axis2.databinding.ADBException("content cannot be null!!");
                                            }
                                           localContent.serialize(new javax.xml.namespace.QName("uri:iso.org:21090","content"),
                                               factory,xmlWriter);
                                        } if (localLinkHtmlTracker){
                                            if (localLinkHtml==null){
                                                 throw new org.apache.axis2.databinding.ADBException("linkHtml cannot be null!!");
                                            }
                                           localLinkHtml.serialize(new javax.xml.namespace.QName("uri:iso.org:21090","linkHtml"),
                                               factory,xmlWriter);
                                        } if (localSubTracker){
                                    namespace = "uri:iso.org:21090";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"sub", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"sub");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("sub");
                                    }
                                

                                          if (localSub==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("sub cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localSub);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localSupTracker){
                                    namespace = "uri:iso.org:21090";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"sup", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"sup");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("sup");
                                    }
                                

                                          if (localSup==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("sup cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localSup);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localBrTracker){
                                            if (localBr==null){
                                                 throw new org.apache.axis2.databinding.ADBException("br cannot be null!!");
                                            }
                                           localBr.serialize(new javax.xml.namespace.QName("uri:iso.org:21090","br"),
                                               factory,xmlWriter);
                                        } if (localFootnoteTracker){
                                            if (localFootnote==null){
                                                 throw new org.apache.axis2.databinding.ADBException("footnote cannot be null!!");
                                            }
                                           localFootnote.serialize(new javax.xml.namespace.QName("uri:iso.org:21090","footnote"),
                                               factory,xmlWriter);
                                        } if (localFootnoteRefTracker){
                                            if (localFootnoteRef==null){
                                                 throw new org.apache.axis2.databinding.ADBException("footnoteRef cannot be null!!");
                                            }
                                           localFootnoteRef.serialize(new javax.xml.namespace.QName("uri:iso.org:21090","footnoteRef"),
                                               factory,xmlWriter);
                                        } if (localRenderMultiMediaTracker){
                                            if (localRenderMultiMedia==null){
                                                 throw new org.apache.axis2.databinding.ADBException("renderMultiMedia cannot be null!!");
                                            }
                                           localRenderMultiMedia.serialize(new javax.xml.namespace.QName("uri:iso.org:21090","renderMultiMedia"),
                                               factory,xmlWriter);
                                        } if (localParagraphTracker){
                                            if (localParagraph==null){
                                                 throw new org.apache.axis2.databinding.ADBException("paragraph cannot be null!!");
                                            }
                                           localParagraph.serialize(new javax.xml.namespace.QName("uri:iso.org:21090","paragraph"),
                                               factory,xmlWriter);
                                        } if (localListTracker){
                                            if (localList==null){
                                                 throw new org.apache.axis2.databinding.ADBException("list cannot be null!!");
                                            }
                                           localList.serialize(new javax.xml.namespace.QName("uri:iso.org:21090","list"),
                                               factory,xmlWriter);
                                        }

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

                 if (localContentTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "content"));
                            
                            
                                    if (localContent==null){
                                         throw new org.apache.axis2.databinding.ADBException("content cannot be null!!");
                                    }
                                    elementList.add(localContent);
                                } if (localLinkHtmlTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "linkHtml"));
                            
                            
                                    if (localLinkHtml==null){
                                         throw new org.apache.axis2.databinding.ADBException("linkHtml cannot be null!!");
                                    }
                                    elementList.add(localLinkHtml);
                                } if (localSubTracker){
                                      elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "sub"));
                                 
                                        if (localSub != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSub));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("sub cannot be null!!");
                                        }
                                    } if (localSupTracker){
                                      elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "sup"));
                                 
                                        if (localSup != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSup));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("sup cannot be null!!");
                                        }
                                    } if (localBrTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "br"));
                            
                            
                                    if (localBr==null){
                                         throw new org.apache.axis2.databinding.ADBException("br cannot be null!!");
                                    }
                                    elementList.add(localBr);
                                } if (localFootnoteTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "footnote"));
                            
                            
                                    if (localFootnote==null){
                                         throw new org.apache.axis2.databinding.ADBException("footnote cannot be null!!");
                                    }
                                    elementList.add(localFootnote);
                                } if (localFootnoteRefTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "footnoteRef"));
                            
                            
                                    if (localFootnoteRef==null){
                                         throw new org.apache.axis2.databinding.ADBException("footnoteRef cannot be null!!");
                                    }
                                    elementList.add(localFootnoteRef);
                                } if (localRenderMultiMediaTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "renderMultiMedia"));
                            
                            
                                    if (localRenderMultiMedia==null){
                                         throw new org.apache.axis2.databinding.ADBException("renderMultiMedia cannot be null!!");
                                    }
                                    elementList.add(localRenderMultiMedia);
                                } if (localParagraphTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "paragraph"));
                            
                            
                                    if (localParagraph==null){
                                         throw new org.apache.axis2.databinding.ADBException("paragraph cannot be null!!");
                                    }
                                    elementList.add(localParagraph);
                                } if (localListTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "list"));
                            
                            
                                    if (localList==null){
                                         throw new org.apache.axis2.databinding.ADBException("list cannot be null!!");
                                    }
                                    elementList.add(localList);
                                }

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
        public static TdChoiceE parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            TdChoiceE object =
                new TdChoiceE();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","content").equals(reader.getName())){
                                
                                                object.setContent(_21090.org.iso.Content_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                        else
                                    
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","linkHtml").equals(reader.getName())){
                                
                                                object.setLinkHtml(_21090.org.iso.LinkHtml_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                        else
                                    
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","sub").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSub(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                        else
                                    
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","sup").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSup(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                        else
                                    
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","br").equals(reader.getName())){
                                
                                                object.setBr(_21090.org.iso.Br_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                        else
                                    
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","footnote").equals(reader.getName())){
                                
                                                object.setFootnote(_21090.org.iso.Footnote_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                        else
                                    
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","footnoteRef").equals(reader.getName())){
                                
                                                object.setFootnoteRef(_21090.org.iso.FootnoteRef_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                        else
                                    
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","renderMultiMedia").equals(reader.getName())){
                                
                                                object.setRenderMultiMedia(_21090.org.iso.RenderMultiMedia_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                        else
                                    
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","paragraph").equals(reader.getName())){
                                
                                                object.setParagraph(_21090.org.iso.Paragraph_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                        else
                                    
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","list").equals(reader.getName())){
                                
                                                object.setList(_21090.org.iso.List_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          
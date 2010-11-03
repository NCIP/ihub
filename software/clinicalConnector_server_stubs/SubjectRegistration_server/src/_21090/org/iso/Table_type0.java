
/**
 * Table_type0.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:41 LKT)
 */
            
                package _21090.org.iso;
            

            /**
            *  Table_type0 bean class
            */
        
        public  class Table_type0
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = table_type0
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
                        * field for Caption
                        */

                        
                                    protected _21090.org.iso.Caption_type0 localCaption ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCaptionTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Caption_type0
                           */
                           public  _21090.org.iso.Caption_type0 getCaption(){
                               return localCaption;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Caption
                               */
                               public void setCaption(_21090.org.iso.Caption_type0 param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCaptionTracker = true;
                                       } else {
                                          localCaptionTracker = false;
                                              
                                       }
                                   
                                            this.localCaption=param;
                                    

                               }
                            

                        /**
                        * field for TableChoice_type0
                        */

                        
                                    protected _21090.org.iso.TableChoice_type0 localTableChoice_type0 ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.TableChoice_type0
                           */
                           public  _21090.org.iso.TableChoice_type0 getTableChoice_type0(){
                               return localTableChoice_type0;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TableChoice_type0
                               */
                               public void setTableChoice_type0(_21090.org.iso.TableChoice_type0 param){
                            
                                            this.localTableChoice_type0=param;
                                    

                               }
                            

                        /**
                        * field for Thead
                        */

                        
                                    protected _21090.org.iso.Thead_type0 localThead ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTheadTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Thead_type0
                           */
                           public  _21090.org.iso.Thead_type0 getThead(){
                               return localThead;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Thead
                               */
                               public void setThead(_21090.org.iso.Thead_type0 param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTheadTracker = true;
                                       } else {
                                          localTheadTracker = false;
                                              
                                       }
                                   
                                            this.localThead=param;
                                    

                               }
                            

                        /**
                        * field for Tfoot
                        */

                        
                                    protected _21090.org.iso.Tfoot_type0 localTfoot ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTfootTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Tfoot_type0
                           */
                           public  _21090.org.iso.Tfoot_type0 getTfoot(){
                               return localTfoot;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tfoot
                               */
                               public void setTfoot(_21090.org.iso.Tfoot_type0 param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTfootTracker = true;
                                       } else {
                                          localTfootTracker = false;
                                              
                                       }
                                   
                                            this.localTfoot=param;
                                    

                               }
                            

                        /**
                        * field for Tbody
                        * This was an Array!
                        */

                        
                                    protected _21090.org.iso.Tbody_type0[] localTbody ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Tbody_type0[]
                           */
                           public  _21090.org.iso.Tbody_type0[] getTbody(){
                               return localTbody;
                           }

                           
                        


                               
                              /**
                               * validate the array for Tbody
                               */
                              protected void validateTbody(_21090.org.iso.Tbody_type0[] param){
                             
                              if ((param != null) && (param.length < 1)){
                                throw new java.lang.RuntimeException();
                              }
                              
                              }


                             /**
                              * Auto generated setter method
                              * @param param Tbody
                              */
                              public void setTbody(_21090.org.iso.Tbody_type0[] param){
                              
                                   validateTbody(param);

                               
                                      this.localTbody=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param _21090.org.iso.Tbody_type0
                             */
                             public void addTbody(_21090.org.iso.Tbody_type0 param){
                                   if (localTbody == null){
                                   localTbody = new _21090.org.iso.Tbody_type0[]{};
                                   }

                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localTbody);
                               list.add(param);
                               this.localTbody =
                             (_21090.org.iso.Tbody_type0[])list.toArray(
                            new _21090.org.iso.Tbody_type0[list.size()]);

                             }
                             

                        /**
                        * field for ID
                        * This was an Attribute!
                        */

                        
                                    protected org.apache.axis2.databinding.types.Id localID ;
                                

                           /**
                           * Auto generated getter method
                           * @return org.apache.axis2.databinding.types.Id
                           */
                           public  org.apache.axis2.databinding.types.Id getID(){
                               return localID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ID
                               */
                               public void setID(org.apache.axis2.databinding.types.Id param){
                            
                                            this.localID=param;
                                    

                               }
                            

                        /**
                        * field for Language
                        * This was an Attribute!
                        */

                        
                                    protected org.apache.axis2.databinding.types.NMToken localLanguage ;
                                

                           /**
                           * Auto generated getter method
                           * @return org.apache.axis2.databinding.types.NMToken
                           */
                           public  org.apache.axis2.databinding.types.NMToken getLanguage(){
                               return localLanguage;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Language
                               */
                               public void setLanguage(org.apache.axis2.databinding.types.NMToken param){
                            
                                            this.localLanguage=param;
                                    

                               }
                            

                        /**
                        * field for StyleCode
                        * This was an Attribute!
                        */

                        
                                    protected org.apache.axis2.databinding.types.NMTokens localStyleCode ;
                                

                           /**
                           * Auto generated getter method
                           * @return org.apache.axis2.databinding.types.NMTokens
                           */
                           public  org.apache.axis2.databinding.types.NMTokens getStyleCode(){
                               return localStyleCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param StyleCode
                               */
                               public void setStyleCode(org.apache.axis2.databinding.types.NMTokens param){
                            
                                            this.localStyleCode=param;
                                    

                               }
                            

                        /**
                        * field for Summary
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localSummary ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSummary(){
                               return localSummary;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Summary
                               */
                               public void setSummary(java.lang.String param){
                            
                                            this.localSummary=param;
                                    

                               }
                            

                        /**
                        * field for Width
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localWidth ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getWidth(){
                               return localWidth;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Width
                               */
                               public void setWidth(java.lang.String param){
                            
                                            this.localWidth=param;
                                    

                               }
                            

                        /**
                        * field for Border
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localBorder ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBorder(){
                               return localBorder;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Border
                               */
                               public void setBorder(java.lang.String param){
                            
                                            this.localBorder=param;
                                    

                               }
                            

                        /**
                        * field for Frame
                        * This was an Attribute!
                        */

                        
                                    protected _21090.org.iso.Frame_type0 localFrame ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Frame_type0
                           */
                           public  _21090.org.iso.Frame_type0 getFrame(){
                               return localFrame;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Frame
                               */
                               public void setFrame(_21090.org.iso.Frame_type0 param){
                            
                                            this.localFrame=param;
                                    

                               }
                            

                        /**
                        * field for Rules
                        * This was an Attribute!
                        */

                        
                                    protected _21090.org.iso.Rules_type0 localRules ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Rules_type0
                           */
                           public  _21090.org.iso.Rules_type0 getRules(){
                               return localRules;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Rules
                               */
                               public void setRules(_21090.org.iso.Rules_type0 param){
                            
                                            this.localRules=param;
                                    

                               }
                            

                        /**
                        * field for Cellspacing
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localCellspacing ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCellspacing(){
                               return localCellspacing;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Cellspacing
                               */
                               public void setCellspacing(java.lang.String param){
                            
                                            this.localCellspacing=param;
                                    

                               }
                            

                        /**
                        * field for Cellpadding
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localCellpadding ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCellpadding(){
                               return localCellpadding;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Cellpadding
                               */
                               public void setCellpadding(java.lang.String param){
                            
                                            this.localCellpadding=param;
                                    

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
                       Table_type0.this.serialize(parentQName,factory,xmlWriter);
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
                           namespacePrefix+":table_type0",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "table_type0",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localID != null){
                                        
                                                writeAttribute("",
                                                         "ID",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localID), xmlWriter);

                                            
                                      }
                                    
                                            if (localLanguage != null){
                                        
                                                writeAttribute("",
                                                         "language",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLanguage), xmlWriter);

                                            
                                      }
                                    
                                            if (localStyleCode != null){
                                        
                                                writeAttribute("",
                                                         "styleCode",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localStyleCode), xmlWriter);

                                            
                                      }
                                    
                                            if (localSummary != null){
                                        
                                                writeAttribute("",
                                                         "summary",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSummary), xmlWriter);

                                            
                                      }
                                    
                                            if (localWidth != null){
                                        
                                                writeAttribute("",
                                                         "width",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localWidth), xmlWriter);

                                            
                                      }
                                    
                                            if (localBorder != null){
                                        
                                                writeAttribute("",
                                                         "border",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBorder), xmlWriter);

                                            
                                      }
                                    
                                    
                                    if (localFrame != null){
                                        writeAttribute("",
                                           "frame",
                                           localFrame.toString(), xmlWriter);
                                    }
                                    
                                    
                                    if (localRules != null){
                                        writeAttribute("",
                                           "rules",
                                           localRules.toString(), xmlWriter);
                                    }
                                    
                                            if (localCellspacing != null){
                                        
                                                writeAttribute("",
                                                         "cellspacing",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCellspacing), xmlWriter);

                                            
                                      }
                                    
                                            if (localCellpadding != null){
                                        
                                                writeAttribute("",
                                                         "cellpadding",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCellpadding), xmlWriter);

                                            
                                      }
                                     if (localCaptionTracker){
                                            if (localCaption==null){
                                                 throw new org.apache.axis2.databinding.ADBException("caption cannot be null!!");
                                            }
                                           localCaption.serialize(new javax.xml.namespace.QName("uri:iso.org:21090","caption"),
                                               factory,xmlWriter);
                                        }
                                            if (localTableChoice_type0==null){
                                                 throw new org.apache.axis2.databinding.ADBException("tableChoice_type0 cannot be null!!");
                                            }
                                           localTableChoice_type0.serialize(null,factory,xmlWriter);
                                         if (localTheadTracker){
                                            if (localThead==null){
                                                 throw new org.apache.axis2.databinding.ADBException("thead cannot be null!!");
                                            }
                                           localThead.serialize(new javax.xml.namespace.QName("uri:iso.org:21090","thead"),
                                               factory,xmlWriter);
                                        } if (localTfootTracker){
                                            if (localTfoot==null){
                                                 throw new org.apache.axis2.databinding.ADBException("tfoot cannot be null!!");
                                            }
                                           localTfoot.serialize(new javax.xml.namespace.QName("uri:iso.org:21090","tfoot"),
                                               factory,xmlWriter);
                                        }
                                       if (localTbody!=null){
                                            for (int i = 0;i < localTbody.length;i++){
                                                if (localTbody[i] != null){
                                                 localTbody[i].serialize(new javax.xml.namespace.QName("uri:iso.org:21090","tbody"),
                                                           factory,xmlWriter);
                                                } else {
                                                   
                                                           throw new org.apache.axis2.databinding.ADBException("tbody cannot be null!!");
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("tbody cannot be null!!");
                                        
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

                 if (localCaptionTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "caption"));
                            
                            
                                    if (localCaption==null){
                                         throw new org.apache.axis2.databinding.ADBException("caption cannot be null!!");
                                    }
                                    elementList.add(localCaption);
                                }
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "tableChoice_type0"));
                            
                            
                                    if (localTableChoice_type0==null){
                                         throw new org.apache.axis2.databinding.ADBException("tableChoice_type0 cannot be null!!");
                                    }
                                    elementList.add(localTableChoice_type0);
                                 if (localTheadTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "thead"));
                            
                            
                                    if (localThead==null){
                                         throw new org.apache.axis2.databinding.ADBException("thead cannot be null!!");
                                    }
                                    elementList.add(localThead);
                                } if (localTfootTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "tfoot"));
                            
                            
                                    if (localTfoot==null){
                                         throw new org.apache.axis2.databinding.ADBException("tfoot cannot be null!!");
                                    }
                                    elementList.add(localTfoot);
                                }
                             if (localTbody!=null) {
                                 for (int i = 0;i < localTbody.length;i++){

                                    if (localTbody[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                          "tbody"));
                                         elementList.add(localTbody[i]);
                                    } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("tbody cannot be null !!");
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("tbody cannot be null!!");
                                    
                             }

                        
                            attribList.add(
                            new javax.xml.namespace.QName("","ID"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localID));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","language"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLanguage));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","styleCode"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localStyleCode));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","summary"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSummary));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","width"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localWidth));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","border"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBorder));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","frame"));
                            
                                      attribList.add(localFrame.toString());
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","rules"));
                            
                                      attribList.add(localRules.toString());
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","cellspacing"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCellspacing));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","cellpadding"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCellpadding));
                                

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
        public static Table_type0 parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Table_type0 object =
                new Table_type0();

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
                    
                            if (!"table_type0".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Table_type0)gov.nih.nci.clinicalconnector.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    // handle attribute "ID"
                    java.lang.String tempAttribID =
                        
                                reader.getAttributeValue(null,"ID");
                            
                   if (tempAttribID!=null){
                         java.lang.String content = tempAttribID;
                        
                                                 object.setID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToID(tempAttribID));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("ID");
                    
                    // handle attribute "language"
                    java.lang.String tempAttribLanguage =
                        
                                reader.getAttributeValue(null,"language");
                            
                   if (tempAttribLanguage!=null){
                         java.lang.String content = tempAttribLanguage;
                        
                                                 object.setLanguage(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToNMTOKEN(tempAttribLanguage));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("language");
                    
                    // handle attribute "styleCode"
                    java.lang.String tempAttribStyleCode =
                        
                                reader.getAttributeValue(null,"styleCode");
                            
                   if (tempAttribStyleCode!=null){
                         java.lang.String content = tempAttribStyleCode;
                        
                                                 object.setStyleCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToNMTOKENS(tempAttribStyleCode));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("styleCode");
                    
                    // handle attribute "summary"
                    java.lang.String tempAttribSummary =
                        
                                reader.getAttributeValue(null,"summary");
                            
                   if (tempAttribSummary!=null){
                         java.lang.String content = tempAttribSummary;
                        
                                                 object.setSummary(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribSummary));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("summary");
                    
                    // handle attribute "width"
                    java.lang.String tempAttribWidth =
                        
                                reader.getAttributeValue(null,"width");
                            
                   if (tempAttribWidth!=null){
                         java.lang.String content = tempAttribWidth;
                        
                                                 object.setWidth(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribWidth));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("width");
                    
                    // handle attribute "border"
                    java.lang.String tempAttribBorder =
                        
                                reader.getAttributeValue(null,"border");
                            
                   if (tempAttribBorder!=null){
                         java.lang.String content = tempAttribBorder;
                        
                                                 object.setBorder(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribBorder));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("border");
                    
                    // handle attribute "frame"
                    java.lang.String tempAttribFrame =
                        
                                reader.getAttributeValue(null,"frame");
                            
                   if (tempAttribFrame!=null){
                         java.lang.String content = tempAttribFrame;
                        
                                                  object.setFrame(
                                                        _21090.org.iso.Frame_type0.Factory.fromString(reader,tempAttribFrame));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("frame");
                    
                    // handle attribute "rules"
                    java.lang.String tempAttribRules =
                        
                                reader.getAttributeValue(null,"rules");
                            
                   if (tempAttribRules!=null){
                         java.lang.String content = tempAttribRules;
                        
                                                  object.setRules(
                                                        _21090.org.iso.Rules_type0.Factory.fromString(reader,tempAttribRules));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("rules");
                    
                    // handle attribute "cellspacing"
                    java.lang.String tempAttribCellspacing =
                        
                                reader.getAttributeValue(null,"cellspacing");
                            
                   if (tempAttribCellspacing!=null){
                         java.lang.String content = tempAttribCellspacing;
                        
                                                 object.setCellspacing(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribCellspacing));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("cellspacing");
                    
                    // handle attribute "cellpadding"
                    java.lang.String tempAttribCellpadding =
                        
                                reader.getAttributeValue(null,"cellpadding");
                            
                   if (tempAttribCellpadding!=null){
                         java.lang.String content = tempAttribCellpadding;
                        
                                                 object.setCellpadding(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribCellpadding));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("cellpadding");
                    
                    
                    reader.next();
                
                        java.util.ArrayList list5 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","caption").equals(reader.getName())){
                                
                                                object.setCaption(_21090.org.iso.Caption_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() ){
                                
                                                object.setTableChoice_type0(_21090.org.iso.TableChoice_type0.Factory.parse(reader));
                                            
                              }  // End of if for expected property start element
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","thead").equals(reader.getName())){
                                
                                                object.setThead(_21090.org.iso.Thead_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","tfoot").equals(reader.getName())){
                                
                                                object.setTfoot(_21090.org.iso.Tfoot_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","tbody").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list5.add(_21090.org.iso.Tbody_type0.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone5 = false;
                                                        while(!loopDone5){
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
                                                                loopDone5 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("uri:iso.org:21090","tbody").equals(reader.getName())){
                                                                    list5.add(_21090.org.iso.Tbody_type0.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone5 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setTbody((_21090.org.iso.Tbody_type0[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                _21090.org.iso.Tbody_type0.class,
                                                                list5));
                                                            
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
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
           
          
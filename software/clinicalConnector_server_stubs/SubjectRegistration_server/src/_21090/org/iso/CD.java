/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
                package _21090.org.iso;
            

            /**
            *  CD bean class
            */
        
        public  class CD extends _21090.org.iso.ANY
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = CD
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
                        * field for DisplayName
                        */

                        
                                    protected _21090.org.iso.ST localDisplayName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDisplayNameTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.ST
                           */
                           public  _21090.org.iso.ST getDisplayName(){
                               return localDisplayName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DisplayName
                               */
                               public void setDisplayName(_21090.org.iso.ST param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localDisplayNameTracker = true;
                                       } else {
                                          localDisplayNameTracker = false;
                                              
                                       }
                                   
                                            this.localDisplayName=param;
                                    

                               }
                            

                        /**
                        * field for OriginalText
                        */

                        
                                    protected _21090.org.iso.EDText localOriginalText ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localOriginalTextTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.EDText
                           */
                           public  _21090.org.iso.EDText getOriginalText(){
                               return localOriginalText;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param OriginalText
                               */
                               public void setOriginalText(_21090.org.iso.EDText param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localOriginalTextTracker = true;
                                       } else {
                                          localOriginalTextTracker = false;
                                              
                                       }
                                   
                                            this.localOriginalText=param;
                                    

                               }
                            

                        /**
                        * field for Qualifier
                        * This was an Array!
                        */

                        
                                    protected _21090.org.iso.CR[] localQualifier ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localQualifierTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.CR[]
                           */
                           public  _21090.org.iso.CR[] getQualifier(){
                               return localQualifier;
                           }

                           
                        


                               
                              /**
                               * validate the array for Qualifier
                               */
                              protected void validateQualifier(_21090.org.iso.CR[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Qualifier
                              */
                              public void setQualifier(_21090.org.iso.CR[] param){
                              
                                   validateQualifier(param);

                               
                                          if (param != null){
                                             //update the setting tracker
                                             localQualifierTracker = true;
                                          } else {
                                             localQualifierTracker = false;
                                                 
                                          }
                                      
                                      this.localQualifier=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param _21090.org.iso.CR
                             */
                             public void addQualifier(_21090.org.iso.CR param){
                                   if (localQualifier == null){
                                   localQualifier = new _21090.org.iso.CR[]{};
                                   }

                            
                                 //update the setting tracker
                                localQualifierTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localQualifier);
                               list.add(param);
                               this.localQualifier =
                             (_21090.org.iso.CR[])list.toArray(
                            new _21090.org.iso.CR[list.size()]);

                             }
                             

                        /**
                        * field for Group
                        * This was an Array!
                        */

                        
                                    protected _21090.org.iso.CDGroup[] localGroup ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localGroupTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.CDGroup[]
                           */
                           public  _21090.org.iso.CDGroup[] getGroup(){
                               return localGroup;
                           }

                           
                        


                               
                              /**
                               * validate the array for Group
                               */
                              protected void validateGroup(_21090.org.iso.CDGroup[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Group
                              */
                              public void setGroup(_21090.org.iso.CDGroup[] param){
                              
                                   validateGroup(param);

                               
                                          if (param != null){
                                             //update the setting tracker
                                             localGroupTracker = true;
                                          } else {
                                             localGroupTracker = false;
                                                 
                                          }
                                      
                                      this.localGroup=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param _21090.org.iso.CDGroup
                             */
                             public void addGroup(_21090.org.iso.CDGroup param){
                                   if (localGroup == null){
                                   localGroup = new _21090.org.iso.CDGroup[]{};
                                   }

                            
                                 //update the setting tracker
                                localGroupTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localGroup);
                               list.add(param);
                               this.localGroup =
                             (_21090.org.iso.CDGroup[])list.toArray(
                            new _21090.org.iso.CDGroup[list.size()]);

                             }
                             

                        /**
                        * field for Translation
                        * This was an Array!
                        */

                        
                                    protected _21090.org.iso.CD[] localTranslation ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTranslationTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.CD[]
                           */
                           public  _21090.org.iso.CD[] getTranslation(){
                               return localTranslation;
                           }

                           
                        


                               
                              /**
                               * validate the array for Translation
                               */
                              protected void validateTranslation(_21090.org.iso.CD[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Translation
                              */
                              public void setTranslation(_21090.org.iso.CD[] param){
                              
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
                             * @param param _21090.org.iso.CD
                             */
                             public void addTranslation(_21090.org.iso.CD param){
                                   if (localTranslation == null){
                                   localTranslation = new _21090.org.iso.CD[]{};
                                   }

                            
                                 //update the setting tracker
                                localTranslationTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localTranslation);
                               list.add(param);
                               this.localTranslation =
                             (_21090.org.iso.CD[])list.toArray(
                            new _21090.org.iso.CD[list.size()]);

                             }
                             

                        /**
                        * field for Source
                        */

                        
                                    protected _21090.org.iso.XReference localSource ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSourceTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.XReference
                           */
                           public  _21090.org.iso.XReference getSource(){
                               return localSource;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Source
                               */
                               public void setSource(_21090.org.iso.XReference param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localSourceTracker = true;
                                       } else {
                                          localSourceTracker = false;
                                              
                                       }
                                   
                                            this.localSource=param;
                                    

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

                        
                                    protected _21090.org.iso.Uid localCodeSystem ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Uid
                           */
                           public  _21090.org.iso.Uid getCodeSystem(){
                               return localCodeSystem;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CodeSystem
                               */
                               public void setCodeSystem(_21090.org.iso.Uid param){
                            
                                            this.localCodeSystem=param;
                                    

                               }
                            

                        /**
                        * field for CodeSystemName
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localCodeSystemName ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCodeSystemName(){
                               return localCodeSystemName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CodeSystemName
                               */
                               public void setCodeSystemName(java.lang.String param){
                            
                                            this.localCodeSystemName=param;
                                    

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
                        * field for ValueSet
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localValueSet ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getValueSet(){
                               return localValueSet;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ValueSet
                               */
                               public void setValueSet(java.lang.String param){
                            
                                            this.localValueSet=param;
                                    

                               }
                            

                        /**
                        * field for ValueSetVersion
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localValueSetVersion ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getValueSetVersion(){
                               return localValueSetVersion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ValueSetVersion
                               */
                               public void setValueSetVersion(java.lang.String param){
                            
                                            this.localValueSetVersion=param;
                                    

                               }
                            

                        /**
                        * field for Id
                        * This was an Attribute!
                        */

                        
                                    protected org.apache.axis2.databinding.types.Id localId ;
                                

                           /**
                           * Auto generated getter method
                           * @return org.apache.axis2.databinding.types.Id
                           */
                           public  org.apache.axis2.databinding.types.Id getId(){
                               return localId;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Id
                               */
                               public void setId(org.apache.axis2.databinding.types.Id param){
                            
                                            this.localId=param;
                                    

                               }
                            

                        /**
                        * field for CodingRationale
                        * This was an Attribute!
                        */

                        
                                    protected _21090.org.iso.Set_CodingRationale localCodingRationale ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Set_CodingRationale
                           */
                           public  _21090.org.iso.Set_CodingRationale getCodingRationale(){
                               return localCodingRationale;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CodingRationale
                               */
                               public void setCodingRationale(_21090.org.iso.Set_CodingRationale param){
                            
                                            this.localCodingRationale=param;
                                    

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
                       CD.this.serialize(parentQName,factory,xmlWriter);
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
                           namespacePrefix+":CD",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "CD",
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
                                    
                                            if (localCode != null){
                                        
                                                writeAttribute("",
                                                         "code",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCode), xmlWriter);

                                            
                                      }
                                    
                                    
                                    if (localCodeSystem != null){
                                        writeAttribute("",
                                           "codeSystem",
                                           localCodeSystem.toString(), xmlWriter);
                                    }
                                    
                                            if (localCodeSystemName != null){
                                        
                                                writeAttribute("",
                                                         "codeSystemName",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCodeSystemName), xmlWriter);

                                            
                                      }
                                    
                                            if (localCodeSystemVersion != null){
                                        
                                                writeAttribute("",
                                                         "codeSystemVersion",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCodeSystemVersion), xmlWriter);

                                            
                                      }
                                    
                                            if (localValueSet != null){
                                        
                                                writeAttribute("",
                                                         "valueSet",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localValueSet), xmlWriter);

                                            
                                      }
                                    
                                            if (localValueSetVersion != null){
                                        
                                                writeAttribute("",
                                                         "valueSetVersion",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localValueSetVersion), xmlWriter);

                                            
                                      }
                                    
                                            if (localId != null){
                                        
                                                writeAttribute("",
                                                         "id",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localId), xmlWriter);

                                            
                                      }
                                    
                                    
                                    if (localCodingRationale != null){
                                        writeAttribute("",
                                           "codingRationale",
                                           localCodingRationale.toString(), xmlWriter);
                                    }
                                     if (localDisplayNameTracker){
                                            if (localDisplayName==null){
                                                 throw new org.apache.axis2.databinding.ADBException("displayName cannot be null!!");
                                            }
                                           localDisplayName.serialize(new javax.xml.namespace.QName("uri:iso.org:21090","displayName"),
                                               factory,xmlWriter);
                                        } if (localOriginalTextTracker){
                                            if (localOriginalText==null){
                                                 throw new org.apache.axis2.databinding.ADBException("originalText cannot be null!!");
                                            }
                                           localOriginalText.serialize(new javax.xml.namespace.QName("uri:iso.org:21090","originalText"),
                                               factory,xmlWriter);
                                        } if (localQualifierTracker){
                                       if (localQualifier!=null){
                                            for (int i = 0;i < localQualifier.length;i++){
                                                if (localQualifier[i] != null){
                                                 localQualifier[i].serialize(new javax.xml.namespace.QName("uri:iso.org:21090","qualifier"),
                                                           factory,xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("qualifier cannot be null!!");
                                        
                                    }
                                 } if (localGroupTracker){
                                       if (localGroup!=null){
                                            for (int i = 0;i < localGroup.length;i++){
                                                if (localGroup[i] != null){
                                                 localGroup[i].serialize(new javax.xml.namespace.QName("uri:iso.org:21090","group"),
                                                           factory,xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("group cannot be null!!");
                                        
                                    }
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
                                 } if (localSourceTracker){
                                            if (localSource==null){
                                                 throw new org.apache.axis2.databinding.ADBException("source cannot be null!!");
                                            }
                                           localSource.serialize(new javax.xml.namespace.QName("uri:iso.org:21090","source"),
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
                    attribList.add(new javax.xml.namespace.QName("uri:iso.org:21090","CD"));
                 if (localDisplayNameTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "displayName"));
                            
                            
                                    if (localDisplayName==null){
                                         throw new org.apache.axis2.databinding.ADBException("displayName cannot be null!!");
                                    }
                                    elementList.add(localDisplayName);
                                } if (localOriginalTextTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "originalText"));
                            
                            
                                    if (localOriginalText==null){
                                         throw new org.apache.axis2.databinding.ADBException("originalText cannot be null!!");
                                    }
                                    elementList.add(localOriginalText);
                                } if (localQualifierTracker){
                             if (localQualifier!=null) {
                                 for (int i = 0;i < localQualifier.length;i++){

                                    if (localQualifier[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                          "qualifier"));
                                         elementList.add(localQualifier[i]);
                                    } else {
                                        
                                                // nothing to do
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("qualifier cannot be null!!");
                                    
                             }

                        } if (localGroupTracker){
                             if (localGroup!=null) {
                                 for (int i = 0;i < localGroup.length;i++){

                                    if (localGroup[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                          "group"));
                                         elementList.add(localGroup[i]);
                                    } else {
                                        
                                                // nothing to do
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("group cannot be null!!");
                                    
                             }

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

                        } if (localSourceTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "source"));
                            
                            
                                    if (localSource==null){
                                         throw new org.apache.axis2.databinding.ADBException("source cannot be null!!");
                                    }
                                    elementList.add(localSource);
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
                            new javax.xml.namespace.QName("","code"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCode));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","codeSystem"));
                            
                                      attribList.add(localCodeSystem.toString());
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","codeSystemName"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCodeSystemName));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","codeSystemVersion"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCodeSystemVersion));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","valueSet"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localValueSet));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","valueSetVersion"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localValueSetVersion));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","id"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localId));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","codingRationale"));
                            
                                      attribList.add(localCodingRationale.toString());
                                

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
        public static CD parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            CD object =
                new CD();

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
                    
                            if (!"CD".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (CD)gov.nih.nci.clinicalconnector.ExtensionMapper.getTypeObject(
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
                                                        _21090.org.iso.Uid.Factory.fromString(reader,tempAttribCodeSystem));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("codeSystem");
                    
                    // handle attribute "codeSystemName"
                    java.lang.String tempAttribCodeSystemName =
                        
                                reader.getAttributeValue(null,"codeSystemName");
                            
                   if (tempAttribCodeSystemName!=null){
                         java.lang.String content = tempAttribCodeSystemName;
                        
                                                 object.setCodeSystemName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribCodeSystemName));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("codeSystemName");
                    
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
                    
                    // handle attribute "valueSet"
                    java.lang.String tempAttribValueSet =
                        
                                reader.getAttributeValue(null,"valueSet");
                            
                   if (tempAttribValueSet!=null){
                         java.lang.String content = tempAttribValueSet;
                        
                                                 object.setValueSet(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribValueSet));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("valueSet");
                    
                    // handle attribute "valueSetVersion"
                    java.lang.String tempAttribValueSetVersion =
                        
                                reader.getAttributeValue(null,"valueSetVersion");
                            
                   if (tempAttribValueSetVersion!=null){
                         java.lang.String content = tempAttribValueSetVersion;
                        
                                                 object.setValueSetVersion(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribValueSetVersion));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("valueSetVersion");
                    
                    // handle attribute "id"
                    java.lang.String tempAttribId =
                        
                                reader.getAttributeValue(null,"id");
                            
                   if (tempAttribId!=null){
                         java.lang.String content = tempAttribId;
                        
                                                 object.setId(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToID(tempAttribId));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("id");
                    
                    // handle attribute "codingRationale"
                    java.lang.String tempAttribCodingRationale =
                        
                                reader.getAttributeValue(null,"codingRationale");
                            
                   if (tempAttribCodingRationale!=null){
                         java.lang.String content = tempAttribCodingRationale;
                        
                                                  object.setCodingRationale(
                                                        _21090.org.iso.Set_CodingRationale.Factory.fromString(reader,tempAttribCodingRationale));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("codingRationale");
                    
                    
                    reader.next();
                
                        java.util.ArrayList list3 = new java.util.ArrayList();
                    
                        java.util.ArrayList list4 = new java.util.ArrayList();
                    
                        java.util.ArrayList list5 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","displayName").equals(reader.getName())){
                                
                                                object.setDisplayName(_21090.org.iso.ST.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","originalText").equals(reader.getName())){
                                
                                                object.setOriginalText(_21090.org.iso.EDText.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","qualifier").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list3.add(_21090.org.iso.CR.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone3 = false;
                                                        while(!loopDone3){
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
                                                                loopDone3 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("uri:iso.org:21090","qualifier").equals(reader.getName())){
                                                                    list3.add(_21090.org.iso.CR.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone3 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setQualifier((_21090.org.iso.CR[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                _21090.org.iso.CR.class,
                                                                list3));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","group").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list4.add(_21090.org.iso.CDGroup.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone4 = false;
                                                        while(!loopDone4){
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
                                                                loopDone4 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("uri:iso.org:21090","group").equals(reader.getName())){
                                                                    list4.add(_21090.org.iso.CDGroup.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone4 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setGroup((_21090.org.iso.CDGroup[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                _21090.org.iso.CDGroup.class,
                                                                list4));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","translation").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list5.add(_21090.org.iso.CD.Factory.parse(reader));
                                                                
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
                                                                if (new javax.xml.namespace.QName("uri:iso.org:21090","translation").equals(reader.getName())){
                                                                    list5.add(_21090.org.iso.CD.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone5 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setTranslation((_21090.org.iso.CD[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                _21090.org.iso.CD.class,
                                                                list5));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","source").equals(reader.getName())){
                                
                                                object.setSource(_21090.org.iso.XReference.Factory.parse(reader));
                                              
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
           
          

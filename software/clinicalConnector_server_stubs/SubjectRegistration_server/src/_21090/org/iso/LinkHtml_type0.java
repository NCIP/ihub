
/**
 * LinkHtml_type0.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:41 LKT)
 */
            
                package _21090.org.iso;
            

            /**
            *  LinkHtml_type0 bean class
            */
        
        public  class LinkHtml_type0
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = linkHtml_type0
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
                        * field for LinkHtmlChoice
                        * This was an Array!
                        */

                        
                                    protected _21090.org.iso.LinkHtmlChoice[] localLinkHtmlChoice ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLinkHtmlChoiceTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.LinkHtmlChoice[]
                           */
                           public  _21090.org.iso.LinkHtmlChoice[] getLinkHtmlChoice(){
                               return localLinkHtmlChoice;
                           }

                           
                        


                               
                              /**
                               * validate the array for LinkHtmlChoice
                               */
                              protected void validateLinkHtmlChoice(_21090.org.iso.LinkHtmlChoice[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param LinkHtmlChoice
                              */
                              public void setLinkHtmlChoice(_21090.org.iso.LinkHtmlChoice[] param){
                              
                                   validateLinkHtmlChoice(param);

                               
                                          if (param != null){
                                             //update the setting tracker
                                             localLinkHtmlChoiceTracker = true;
                                          } else {
                                             localLinkHtmlChoiceTracker = false;
                                                 
                                          }
                                      
                                      this.localLinkHtmlChoice=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param _21090.org.iso.LinkHtmlChoice
                             */
                             public void addLinkHtmlChoice(_21090.org.iso.LinkHtmlChoice param){
                                   if (localLinkHtmlChoice == null){
                                   localLinkHtmlChoice = new _21090.org.iso.LinkHtmlChoice[]{};
                                   }

                            
                                 //update the setting tracker
                                localLinkHtmlChoiceTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localLinkHtmlChoice);
                               list.add(param);
                               this.localLinkHtmlChoice =
                             (_21090.org.iso.LinkHtmlChoice[])list.toArray(
                            new _21090.org.iso.LinkHtmlChoice[list.size()]);

                             }
                             

                        /**
                        * field for Name
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localName ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getName(){
                               return localName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Name
                               */
                               public void setName(java.lang.String param){
                            
                                            this.localName=param;
                                    

                               }
                            

                        /**
                        * field for Href
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localHref ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHref(){
                               return localHref;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Href
                               */
                               public void setHref(java.lang.String param){
                            
                                            this.localHref=param;
                                    

                               }
                            

                        /**
                        * field for Rel
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localRel ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getRel(){
                               return localRel;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Rel
                               */
                               public void setRel(java.lang.String param){
                            
                                            this.localRel=param;
                                    

                               }
                            

                        /**
                        * field for Rev
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localRev ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getRev(){
                               return localRev;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Rev
                               */
                               public void setRev(java.lang.String param){
                            
                                            this.localRev=param;
                                    

                               }
                            

                        /**
                        * field for Title
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localTitle ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTitle(){
                               return localTitle;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Title
                               */
                               public void setTitle(java.lang.String param){
                            
                                            this.localTitle=param;
                                    

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
                       LinkHtml_type0.this.serialize(parentQName,factory,xmlWriter);
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
                           namespacePrefix+":linkHtml_type0",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "linkHtml_type0",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localName != null){
                                        
                                                writeAttribute("",
                                                         "name",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localName), xmlWriter);

                                            
                                      }
                                    
                                            if (localHref != null){
                                        
                                                writeAttribute("",
                                                         "href",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHref), xmlWriter);

                                            
                                      }
                                    
                                            if (localRel != null){
                                        
                                                writeAttribute("",
                                                         "rel",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRel), xmlWriter);

                                            
                                      }
                                    
                                            if (localRev != null){
                                        
                                                writeAttribute("",
                                                         "rev",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRev), xmlWriter);

                                            
                                      }
                                    
                                            if (localTitle != null){
                                        
                                                writeAttribute("",
                                                         "title",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTitle), xmlWriter);

                                            
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
                                     if (localLinkHtmlChoiceTracker){
                                     
                                      if (localLinkHtmlChoice!=null){
                                            for (int i = 0;i < localLinkHtmlChoice.length;i++){
                                                if (localLinkHtmlChoice[i] != null){
                                                 localLinkHtmlChoice[i].serialize(null,factory,xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        throw new org.apache.axis2.databinding.ADBException("linkHtmlChoice cannot be null!!");
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

                 if (localLinkHtmlChoiceTracker){
                             if (localLinkHtmlChoice!=null) {
                                 for (int i = 0;i < localLinkHtmlChoice.length;i++){

                                    if (localLinkHtmlChoice[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                          "linkHtmlChoice"));
                                         elementList.add(localLinkHtmlChoice[i]);
                                    } else {
                                        
                                                // nothing to do
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("linkHtmlChoice cannot be null!!");
                                    
                             }

                        }
                            attribList.add(
                            new javax.xml.namespace.QName("","name"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localName));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","href"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHref));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","rel"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRel));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","rev"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRev));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","title"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTitle));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","ID"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localID));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","language"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLanguage));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","styleCode"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localStyleCode));
                                

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
        public static LinkHtml_type0 parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            LinkHtml_type0 object =
                new LinkHtml_type0();

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
                    
                            if (!"linkHtml_type0".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (LinkHtml_type0)gov.nih.nci.clinicalconnector.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    // handle attribute "name"
                    java.lang.String tempAttribName =
                        
                                reader.getAttributeValue(null,"name");
                            
                   if (tempAttribName!=null){
                         java.lang.String content = tempAttribName;
                        
                                                 object.setName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribName));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("name");
                    
                    // handle attribute "href"
                    java.lang.String tempAttribHref =
                        
                                reader.getAttributeValue(null,"href");
                            
                   if (tempAttribHref!=null){
                         java.lang.String content = tempAttribHref;
                        
                                                 object.setHref(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribHref));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("href");
                    
                    // handle attribute "rel"
                    java.lang.String tempAttribRel =
                        
                                reader.getAttributeValue(null,"rel");
                            
                   if (tempAttribRel!=null){
                         java.lang.String content = tempAttribRel;
                        
                                                 object.setRel(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribRel));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("rel");
                    
                    // handle attribute "rev"
                    java.lang.String tempAttribRev =
                        
                                reader.getAttributeValue(null,"rev");
                            
                   if (tempAttribRev!=null){
                         java.lang.String content = tempAttribRev;
                        
                                                 object.setRev(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribRev));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("rev");
                    
                    // handle attribute "title"
                    java.lang.String tempAttribTitle =
                        
                                reader.getAttributeValue(null,"title");
                            
                   if (tempAttribTitle!=null){
                         java.lang.String content = tempAttribTitle;
                        
                                                 object.setTitle(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribTitle));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("title");
                    
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
                    
                    
                    reader.next();
                
                        java.util.ArrayList list1 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                        
                                         try{
                                    
                                    if (reader.isStartElement() ){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list1.add(_21090.org.iso.LinkHtmlChoice.Factory.parse(reader));
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone1 = false;
                                                        while(!loopDone1){

                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone1 = true;
                                                            } else {
                                                                list1.add(_21090.org.iso.LinkHtmlChoice.Factory.parse(reader));
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        object.setLinkHtmlChoice((_21090.org.iso.LinkHtmlChoice[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                _21090.org.iso.LinkHtmlChoice.class,
                                                                list1));

                                                 
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                
                                 } catch (java.lang.Exception e) {}
                              
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
           
          
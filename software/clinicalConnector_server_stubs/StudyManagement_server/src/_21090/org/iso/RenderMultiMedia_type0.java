
/**
 * RenderMultiMedia_type0.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:47 EDT)
 */
            
                package _21090.org.iso;
            

            /**
            *  RenderMultiMedia_type0 bean class
            */
        
        public  class RenderMultiMedia_type0
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = renderMultiMedia_type0
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
                        * field for ReferencedObject
                        * This was an Attribute!
                        */

                        
                                    protected org.apache.axis2.databinding.types.IDRefs localReferencedObject ;
                                

                           /**
                           * Auto generated getter method
                           * @return org.apache.axis2.databinding.types.IDRefs
                           */
                           public  org.apache.axis2.databinding.types.IDRefs getReferencedObject(){
                               return localReferencedObject;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReferencedObject
                               */
                               public void setReferencedObject(org.apache.axis2.databinding.types.IDRefs param){
                            
                                            this.localReferencedObject=param;
                                    

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
                       RenderMultiMedia_type0.this.serialize(parentQName,factory,xmlWriter);
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
                           namespacePrefix+":renderMultiMedia_type0",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "renderMultiMedia_type0",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localReferencedObject != null){
                                        
                                                writeAttribute("",
                                                         "referencedObject",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReferencedObject), xmlWriter);

                                            
                                      }
                                    
                                      else {
                                          throw new org.apache.axis2.databinding.ADBException("required attribute localReferencedObject is null");
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
                                     if (localCaptionTracker){
                                            if (localCaption==null){
                                                 throw new org.apache.axis2.databinding.ADBException("caption cannot be null!!");
                                            }
                                           localCaption.serialize(new javax.xml.namespace.QName("uri:iso.org:21090","caption"),
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

                 if (localCaptionTracker){
                            elementList.add(new javax.xml.namespace.QName("uri:iso.org:21090",
                                                                      "caption"));
                            
                            
                                    if (localCaption==null){
                                         throw new org.apache.axis2.databinding.ADBException("caption cannot be null!!");
                                    }
                                    elementList.add(localCaption);
                                }
                            attribList.add(
                            new javax.xml.namespace.QName("","referencedObject"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReferencedObject));
                                
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
        public static RenderMultiMedia_type0 parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            RenderMultiMedia_type0 object =
                new RenderMultiMedia_type0();

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
                    
                            if (!"renderMultiMedia_type0".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (RenderMultiMedia_type0)gov.nih.nci.clinicalconnector.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    // handle attribute "referencedObject"
                    java.lang.String tempAttribReferencedObject =
                        
                                reader.getAttributeValue(null,"referencedObject");
                            
                   if (tempAttribReferencedObject!=null){
                         java.lang.String content = tempAttribReferencedObject;
                        
                                                 object.setReferencedObject(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToIDREFS(tempAttribReferencedObject));
                                            
                    } else {
                       
                               throw new org.apache.axis2.databinding.ADBException("Required attribute referencedObject is missing");
                           
                    }
                    handledAttributes.add("referencedObject");
                    
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
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("uri:iso.org:21090","caption").equals(reader.getName())){
                                
                                                object.setCaption(_21090.org.iso.Caption_type0.Factory.parse(reader));
                                              
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
           
          
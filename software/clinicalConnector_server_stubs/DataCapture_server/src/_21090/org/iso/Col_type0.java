
/**
 * Col_type0.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:47 EDT)
 */
            
                package _21090.org.iso;
            

            /**
            *  Col_type0 bean class
            */
        
        public  class Col_type0
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = col_type0
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
                        * field for Span
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localSpan =
                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString("1");
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSpan(){
                               return localSpan;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Span
                               */
                               public void setSpan(java.lang.String param){
                            
                                            this.localSpan=param;
                                    

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
                        * field for Align
                        * This was an Attribute!
                        */

                        
                                    protected _21090.org.iso.Align_type0 localAlign ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Align_type0
                           */
                           public  _21090.org.iso.Align_type0 getAlign(){
                               return localAlign;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Align
                               */
                               public void setAlign(_21090.org.iso.Align_type0 param){
                            
                                            this.localAlign=param;
                                    

                               }
                            

                        /**
                        * field for _char
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String local_char ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String get_char(){
                               return local_char;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param _char
                               */
                               public void set_char(java.lang.String param){
                            
                                            this.local_char=param;
                                    

                               }
                            

                        /**
                        * field for Charoff
                        * This was an Attribute!
                        */

                        
                                    protected java.lang.String localCharoff ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCharoff(){
                               return localCharoff;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Charoff
                               */
                               public void setCharoff(java.lang.String param){
                            
                                            this.localCharoff=param;
                                    

                               }
                            

                        /**
                        * field for Valign
                        * This was an Attribute!
                        */

                        
                                    protected _21090.org.iso.Valign_type0 localValign ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.Valign_type0
                           */
                           public  _21090.org.iso.Valign_type0 getValign(){
                               return localValign;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Valign
                               */
                               public void setValign(_21090.org.iso.Valign_type0 param){
                            
                                            this.localValign=param;
                                    

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
                       Col_type0.this.serialize(parentQName,factory,xmlWriter);
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
                           namespacePrefix+":col_type0",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "col_type0",
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
                                    
                                            if (localSpan != null){
                                        
                                                writeAttribute("",
                                                         "span",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSpan), xmlWriter);

                                            
                                      }
                                    
                                            if (localWidth != null){
                                        
                                                writeAttribute("",
                                                         "width",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localWidth), xmlWriter);

                                            
                                      }
                                    
                                    
                                    if (localAlign != null){
                                        writeAttribute("",
                                           "align",
                                           localAlign.toString(), xmlWriter);
                                    }
                                    
                                            if (local_char != null){
                                        
                                                writeAttribute("",
                                                         "char",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(local_char), xmlWriter);

                                            
                                      }
                                    
                                            if (localCharoff != null){
                                        
                                                writeAttribute("",
                                                         "charoff",
                                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCharoff), xmlWriter);

                                            
                                      }
                                    
                                    
                                    if (localValign != null){
                                        writeAttribute("",
                                           "valign",
                                           localValign.toString(), xmlWriter);
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
                            new javax.xml.namespace.QName("","ID"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localID));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","language"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLanguage));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","styleCode"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localStyleCode));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","span"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSpan));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","width"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localWidth));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","align"));
                            
                                      attribList.add(localAlign.toString());
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","char"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(local_char));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","charoff"));
                            
                                      attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCharoff));
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","valign"));
                            
                                      attribList.add(localValign.toString());
                                

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
        public static Col_type0 parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Col_type0 object =
                new Col_type0();

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
                    
                            if (!"col_type0".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Col_type0)gov.nih.nci.clinicalconnector.ExtensionMapper.getTypeObject(
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
                    
                    // handle attribute "span"
                    java.lang.String tempAttribSpan =
                        
                                reader.getAttributeValue(null,"span");
                            
                   if (tempAttribSpan!=null){
                         java.lang.String content = tempAttribSpan;
                        
                                                 object.setSpan(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribSpan));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("span");
                    
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
                    
                    // handle attribute "align"
                    java.lang.String tempAttribAlign =
                        
                                reader.getAttributeValue(null,"align");
                            
                   if (tempAttribAlign!=null){
                         java.lang.String content = tempAttribAlign;
                        
                                                  object.setAlign(
                                                        _21090.org.iso.Align_type0.Factory.fromString(reader,tempAttribAlign));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("align");
                    
                    // handle attribute "char"
                    java.lang.String tempAttrib_char =
                        
                                reader.getAttributeValue(null,"char");
                            
                   if (tempAttrib_char!=null){
                         java.lang.String content = tempAttrib_char;
                        
                                                 object.set_char(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttrib_char));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("char");
                    
                    // handle attribute "charoff"
                    java.lang.String tempAttribCharoff =
                        
                                reader.getAttributeValue(null,"charoff");
                            
                   if (tempAttribCharoff!=null){
                         java.lang.String content = tempAttribCharoff;
                        
                                                 object.setCharoff(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribCharoff));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("charoff");
                    
                    // handle attribute "valign"
                    java.lang.String tempAttribValign =
                        
                                reader.getAttributeValue(null,"valign");
                            
                   if (tempAttribValign!=null){
                         java.lang.String content = tempAttribValign;
                        
                                                  object.setValign(
                                                        _21090.org.iso.Valign_type0.Factory.fromString(reader,tempAttribValign));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("valign");
                    
                    
                    reader.next();
                



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          
/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
                package org.openclinica.ws.ccts.subject.v1;
            

            /**
            *  SubjectType bean class
            */
        
        public  class SubjectType
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = SubjectType
                Namespace URI = http://openclinica.org/ws/ccts/subject/v1
                Namespace Prefix = ns1
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://openclinica.org/ws/ccts/subject/v1")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for PersonId
                        */

                        
                                    protected org.openclinica.ws.ccts.subject.v1.CustomStringType localPersonId ;
                                

                           /**
                           * Auto generated getter method
                           * @return org.openclinica.ws.ccts.subject.v1.CustomStringType
                           */
                           public  org.openclinica.ws.ccts.subject.v1.CustomStringType getPersonId(){
                               return localPersonId;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PersonId
                               */
                               public void setPersonId(org.openclinica.ws.ccts.subject.v1.CustomStringType param){
                            
                                            this.localPersonId=param;
                                    

                               }
                            

                        /**
                        * field for StudySubjectId
                        */

                        
                                    protected org.openclinica.ws.ccts.subject.v1.CustomStringType localStudySubjectId ;
                                

                           /**
                           * Auto generated getter method
                           * @return org.openclinica.ws.ccts.subject.v1.CustomStringType
                           */
                           public  org.openclinica.ws.ccts.subject.v1.CustomStringType getStudySubjectId(){
                               return localStudySubjectId;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param StudySubjectId
                               */
                               public void setStudySubjectId(org.openclinica.ws.ccts.subject.v1.CustomStringType param){
                            
                                            this.localStudySubjectId=param;
                                    

                               }
                            

                        /**
                        * field for SecondaryId
                        */

                        
                                    protected org.openclinica.ws.ccts.subject.v1.CustomStringType localSecondaryId ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSecondaryIdTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return org.openclinica.ws.ccts.subject.v1.CustomStringType
                           */
                           public  org.openclinica.ws.ccts.subject.v1.CustomStringType getSecondaryId(){
                               return localSecondaryId;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SecondaryId
                               */
                               public void setSecondaryId(org.openclinica.ws.ccts.subject.v1.CustomStringType param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localSecondaryIdTracker = true;
                                       } else {
                                          localSecondaryIdTracker = false;
                                              
                                       }
                                   
                                            this.localSecondaryId=param;
                                    

                               }
                            

                        /**
                        * field for EnrollmentDate
                        */

                        
                                    protected java.util.Date localEnrollmentDate ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.util.Date
                           */
                           public  java.util.Date getEnrollmentDate(){
                               return localEnrollmentDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EnrollmentDate
                               */
                               public void setEnrollmentDate(java.util.Date param){
                            
                                            this.localEnrollmentDate=param;
                                    

                               }
                            

                        /**
                        * field for Gender
                        */

                        
                                    protected org.openclinica.ws.ccts.subject.v1.GenderType localGender ;
                                

                           /**
                           * Auto generated getter method
                           * @return org.openclinica.ws.ccts.subject.v1.GenderType
                           */
                           public  org.openclinica.ws.ccts.subject.v1.GenderType getGender(){
                               return localGender;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Gender
                               */
                               public void setGender(org.openclinica.ws.ccts.subject.v1.GenderType param){
                            
                                            this.localGender=param;
                                    

                               }
                            

                        /**
                        * field for DateOfBirth
                        */

                        
                                    protected java.util.Date localDateOfBirth ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.util.Date
                           */
                           public  java.util.Date getDateOfBirth(){
                               return localDateOfBirth;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DateOfBirth
                               */
                               public void setDateOfBirth(java.util.Date param){
                            
                                            this.localDateOfBirth=param;
                                    

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
                       SubjectType.this.serialize(parentQName,factory,xmlWriter);
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
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://openclinica.org/ws/ccts/subject/v1");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":SubjectType",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "SubjectType",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localPersonId==null){
                                                 throw new org.apache.axis2.databinding.ADBException("personId cannot be null!!");
                                            }
                                           localPersonId.serialize(new javax.xml.namespace.QName("http://openclinica.org/ws/ccts/subject/v1","personId"),
                                               factory,xmlWriter);
                                        
                                            if (localStudySubjectId==null){
                                                 throw new org.apache.axis2.databinding.ADBException("studySubjectId cannot be null!!");
                                            }
                                           localStudySubjectId.serialize(new javax.xml.namespace.QName("http://openclinica.org/ws/ccts/subject/v1","studySubjectId"),
                                               factory,xmlWriter);
                                         if (localSecondaryIdTracker){
                                            if (localSecondaryId==null){
                                                 throw new org.apache.axis2.databinding.ADBException("secondaryId cannot be null!!");
                                            }
                                           localSecondaryId.serialize(new javax.xml.namespace.QName("http://openclinica.org/ws/ccts/subject/v1","secondaryId"),
                                               factory,xmlWriter);
                                        }
                                    namespace = "http://openclinica.org/ws/ccts/subject/v1";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"enrollmentDate", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"enrollmentDate");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("enrollmentDate");
                                    }
                                

                                          if (localEnrollmentDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("enrollmentDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEnrollmentDate));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                            if (localGender==null){
                                                 throw new org.apache.axis2.databinding.ADBException("gender cannot be null!!");
                                            }
                                           localGender.serialize(new javax.xml.namespace.QName("http://openclinica.org/ws/ccts/subject/v1","gender"),
                                               factory,xmlWriter);
                                        
                                    namespace = "http://openclinica.org/ws/ccts/subject/v1";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"dateOfBirth", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"dateOfBirth");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("dateOfBirth");
                                    }
                                

                                          if (localDateOfBirth==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("dateOfBirth cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDateOfBirth));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
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

                
                            elementList.add(new javax.xml.namespace.QName("http://openclinica.org/ws/ccts/subject/v1",
                                                                      "personId"));
                            
                            
                                    if (localPersonId==null){
                                         throw new org.apache.axis2.databinding.ADBException("personId cannot be null!!");
                                    }
                                    elementList.add(localPersonId);
                                
                            elementList.add(new javax.xml.namespace.QName("http://openclinica.org/ws/ccts/subject/v1",
                                                                      "studySubjectId"));
                            
                            
                                    if (localStudySubjectId==null){
                                         throw new org.apache.axis2.databinding.ADBException("studySubjectId cannot be null!!");
                                    }
                                    elementList.add(localStudySubjectId);
                                 if (localSecondaryIdTracker){
                            elementList.add(new javax.xml.namespace.QName("http://openclinica.org/ws/ccts/subject/v1",
                                                                      "secondaryId"));
                            
                            
                                    if (localSecondaryId==null){
                                         throw new org.apache.axis2.databinding.ADBException("secondaryId cannot be null!!");
                                    }
                                    elementList.add(localSecondaryId);
                                }
                                      elementList.add(new javax.xml.namespace.QName("http://openclinica.org/ws/ccts/subject/v1",
                                                                      "enrollmentDate"));
                                 
                                        if (localEnrollmentDate != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEnrollmentDate));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("enrollmentDate cannot be null!!");
                                        }
                                    
                            elementList.add(new javax.xml.namespace.QName("http://openclinica.org/ws/ccts/subject/v1",
                                                                      "gender"));
                            
                            
                                    if (localGender==null){
                                         throw new org.apache.axis2.databinding.ADBException("gender cannot be null!!");
                                    }
                                    elementList.add(localGender);
                                
                                      elementList.add(new javax.xml.namespace.QName("http://openclinica.org/ws/ccts/subject/v1",
                                                                      "dateOfBirth"));
                                 
                                        if (localDateOfBirth != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDateOfBirth));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("dateOfBirth cannot be null!!");
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
        public static SubjectType parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            SubjectType object =
                new SubjectType();

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
                    
                            if (!"SubjectType".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (SubjectType)org.openclinica.ws.ccts.subject.v1.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://openclinica.org/ws/ccts/subject/v1","personId").equals(reader.getName())){
                                
                                                object.setPersonId(org.openclinica.ws.ccts.subject.v1.CustomStringType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://openclinica.org/ws/ccts/subject/v1","studySubjectId").equals(reader.getName())){
                                
                                                object.setStudySubjectId(org.openclinica.ws.ccts.subject.v1.CustomStringType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://openclinica.org/ws/ccts/subject/v1","secondaryId").equals(reader.getName())){
                                
                                                object.setSecondaryId(org.openclinica.ws.ccts.subject.v1.CustomStringType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://openclinica.org/ws/ccts/subject/v1","enrollmentDate").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEnrollmentDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDate(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://openclinica.org/ws/ccts/subject/v1","gender").equals(reader.getName())){
                                
                                                object.setGender(org.openclinica.ws.ccts.subject.v1.GenderType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://openclinica.org/ws/ccts/subject/v1","dateOfBirth").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDateOfBirth(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDate(content));
                                              
                                        reader.next();
                                    
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
           
          

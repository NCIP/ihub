
/**
 * StudyProtocol.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:41 LKT)
 */
            
                package gov.nih.nci.clinicalconnector;
            

            /**
            *  StudyProtocol bean class
            */
        
        public  class StudyProtocol
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = StudyProtocol
                Namespace URI = http://clinicalconnector.nci.nih.gov
                Namespace Prefix = ns2
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://clinicalconnector.nci.nih.gov")){
                return "ns2";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for Identifier
                        */

                        
                                    protected _21090.org.iso.II localIdentifier ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.II
                           */
                           public  _21090.org.iso.II getIdentifier(){
                               return localIdentifier;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Identifier
                               */
                               public void setIdentifier(_21090.org.iso.II param){
                            
                                            this.localIdentifier=param;
                                    

                               }
                            

                        /**
                        * field for CoordinatingCenterStudyStatusCode
                        */

                        
                                    protected _21090.org.iso.CD localCoordinatingCenterStudyStatusCode ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.CD
                           */
                           public  _21090.org.iso.CD getCoordinatingCenterStudyStatusCode(){
                               return localCoordinatingCenterStudyStatusCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CoordinatingCenterStudyStatusCode
                               */
                               public void setCoordinatingCenterStudyStatusCode(_21090.org.iso.CD param){
                            
                                            this.localCoordinatingCenterStudyStatusCode=param;
                                    

                               }
                            

                        /**
                        * field for DataEntryStatusCode
                        */

                        
                                    protected _21090.org.iso.CD localDataEntryStatusCode ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.CD
                           */
                           public  _21090.org.iso.CD getDataEntryStatusCode(){
                               return localDataEntryStatusCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DataEntryStatusCode
                               */
                               public void setDataEntryStatusCode(_21090.org.iso.CD param){
                            
                                            this.localDataEntryStatusCode=param;
                                    

                               }
                            

                        /**
                        * field for MultiInstitutionIndicator
                        */

                        
                                    protected _21090.org.iso.BL localMultiInstitutionIndicator ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.BL
                           */
                           public  _21090.org.iso.BL getMultiInstitutionIndicator(){
                               return localMultiInstitutionIndicator;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MultiInstitutionIndicator
                               */
                               public void setMultiInstitutionIndicator(_21090.org.iso.BL param){
                            
                                            this.localMultiInstitutionIndicator=param;
                                    

                               }
                            

                        /**
                        * field for OfficialTitle
                        */

                        
                                    protected _21090.org.iso.ST localOfficialTitle ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.ST
                           */
                           public  _21090.org.iso.ST getOfficialTitle(){
                               return localOfficialTitle;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param OfficialTitle
                               */
                               public void setOfficialTitle(_21090.org.iso.ST param){
                            
                                            this.localOfficialTitle=param;
                                    

                               }
                            

                        /**
                        * field for PhaseCode
                        */

                        
                                    protected _21090.org.iso.CD localPhaseCode ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.CD
                           */
                           public  _21090.org.iso.CD getPhaseCode(){
                               return localPhaseCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PhaseCode
                               */
                               public void setPhaseCode(_21090.org.iso.CD param){
                            
                                            this.localPhaseCode=param;
                                    

                               }
                            

                        /**
                        * field for PrimaryPurposeCode
                        */

                        
                                    protected _21090.org.iso.CD localPrimaryPurposeCode ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.CD
                           */
                           public  _21090.org.iso.CD getPrimaryPurposeCode(){
                               return localPrimaryPurposeCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PrimaryPurposeCode
                               */
                               public void setPrimaryPurposeCode(_21090.org.iso.CD param){
                            
                                            this.localPrimaryPurposeCode=param;
                                    

                               }
                            

                        /**
                        * field for PublicDescription
                        */

                        
                                    protected _21090.org.iso.ST localPublicDescription ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.ST
                           */
                           public  _21090.org.iso.ST getPublicDescription(){
                               return localPublicDescription;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PublicDescription
                               */
                               public void setPublicDescription(_21090.org.iso.ST param){
                            
                                            this.localPublicDescription=param;
                                    

                               }
                            

                        /**
                        * field for PublicTitle
                        */

                        
                                    protected _21090.org.iso.ST localPublicTitle ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.ST
                           */
                           public  _21090.org.iso.ST getPublicTitle(){
                               return localPublicTitle;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PublicTitle
                               */
                               public void setPublicTitle(_21090.org.iso.ST param){
                            
                                            this.localPublicTitle=param;
                                    

                               }
                            

                        /**
                        * field for TargetAccrualNumberRange
                        */

                        
                                    protected _21090.org.iso.IVL_INT localTargetAccrualNumberRange ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.IVL_INT
                           */
                           public  _21090.org.iso.IVL_INT getTargetAccrualNumberRange(){
                               return localTargetAccrualNumberRange;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TargetAccrualNumberRange
                               */
                               public void setTargetAccrualNumberRange(_21090.org.iso.IVL_INT param){
                            
                                            this.localTargetAccrualNumberRange=param;
                                    

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
                       StudyProtocol.this.serialize(parentQName,factory,xmlWriter);
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
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://clinicalconnector.nci.nih.gov");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":StudyProtocol",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "StudyProtocol",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localIdentifier==null){
                                                 throw new org.apache.axis2.databinding.ADBException("identifier cannot be null!!");
                                            }
                                           localIdentifier.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","identifier"),
                                               factory,xmlWriter);
                                        
                                            if (localCoordinatingCenterStudyStatusCode==null){
                                                 throw new org.apache.axis2.databinding.ADBException("coordinatingCenterStudyStatusCode cannot be null!!");
                                            }
                                           localCoordinatingCenterStudyStatusCode.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","coordinatingCenterStudyStatusCode"),
                                               factory,xmlWriter);
                                        
                                            if (localDataEntryStatusCode==null){
                                                 throw new org.apache.axis2.databinding.ADBException("dataEntryStatusCode cannot be null!!");
                                            }
                                           localDataEntryStatusCode.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","dataEntryStatusCode"),
                                               factory,xmlWriter);
                                        
                                            if (localMultiInstitutionIndicator==null){
                                                 throw new org.apache.axis2.databinding.ADBException("multiInstitutionIndicator cannot be null!!");
                                            }
                                           localMultiInstitutionIndicator.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","multiInstitutionIndicator"),
                                               factory,xmlWriter);
                                        
                                            if (localOfficialTitle==null){
                                                 throw new org.apache.axis2.databinding.ADBException("officialTitle cannot be null!!");
                                            }
                                           localOfficialTitle.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","officialTitle"),
                                               factory,xmlWriter);
                                        
                                            if (localPhaseCode==null){
                                                 throw new org.apache.axis2.databinding.ADBException("phaseCode cannot be null!!");
                                            }
                                           localPhaseCode.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","phaseCode"),
                                               factory,xmlWriter);
                                        
                                            if (localPrimaryPurposeCode==null){
                                                 throw new org.apache.axis2.databinding.ADBException("primaryPurposeCode cannot be null!!");
                                            }
                                           localPrimaryPurposeCode.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","primaryPurposeCode"),
                                               factory,xmlWriter);
                                        
                                            if (localPublicDescription==null){
                                                 throw new org.apache.axis2.databinding.ADBException("publicDescription cannot be null!!");
                                            }
                                           localPublicDescription.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","publicDescription"),
                                               factory,xmlWriter);
                                        
                                            if (localPublicTitle==null){
                                                 throw new org.apache.axis2.databinding.ADBException("publicTitle cannot be null!!");
                                            }
                                           localPublicTitle.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","publicTitle"),
                                               factory,xmlWriter);
                                        
                                            if (localTargetAccrualNumberRange==null){
                                                 throw new org.apache.axis2.databinding.ADBException("targetAccrualNumberRange cannot be null!!");
                                            }
                                           localTargetAccrualNumberRange.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","targetAccrualNumberRange"),
                                               factory,xmlWriter);
                                        
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

                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "identifier"));
                            
                            
                                    if (localIdentifier==null){
                                         throw new org.apache.axis2.databinding.ADBException("identifier cannot be null!!");
                                    }
                                    elementList.add(localIdentifier);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "coordinatingCenterStudyStatusCode"));
                            
                            
                                    if (localCoordinatingCenterStudyStatusCode==null){
                                         throw new org.apache.axis2.databinding.ADBException("coordinatingCenterStudyStatusCode cannot be null!!");
                                    }
                                    elementList.add(localCoordinatingCenterStudyStatusCode);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "dataEntryStatusCode"));
                            
                            
                                    if (localDataEntryStatusCode==null){
                                         throw new org.apache.axis2.databinding.ADBException("dataEntryStatusCode cannot be null!!");
                                    }
                                    elementList.add(localDataEntryStatusCode);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "multiInstitutionIndicator"));
                            
                            
                                    if (localMultiInstitutionIndicator==null){
                                         throw new org.apache.axis2.databinding.ADBException("multiInstitutionIndicator cannot be null!!");
                                    }
                                    elementList.add(localMultiInstitutionIndicator);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "officialTitle"));
                            
                            
                                    if (localOfficialTitle==null){
                                         throw new org.apache.axis2.databinding.ADBException("officialTitle cannot be null!!");
                                    }
                                    elementList.add(localOfficialTitle);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "phaseCode"));
                            
                            
                                    if (localPhaseCode==null){
                                         throw new org.apache.axis2.databinding.ADBException("phaseCode cannot be null!!");
                                    }
                                    elementList.add(localPhaseCode);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "primaryPurposeCode"));
                            
                            
                                    if (localPrimaryPurposeCode==null){
                                         throw new org.apache.axis2.databinding.ADBException("primaryPurposeCode cannot be null!!");
                                    }
                                    elementList.add(localPrimaryPurposeCode);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "publicDescription"));
                            
                            
                                    if (localPublicDescription==null){
                                         throw new org.apache.axis2.databinding.ADBException("publicDescription cannot be null!!");
                                    }
                                    elementList.add(localPublicDescription);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "publicTitle"));
                            
                            
                                    if (localPublicTitle==null){
                                         throw new org.apache.axis2.databinding.ADBException("publicTitle cannot be null!!");
                                    }
                                    elementList.add(localPublicTitle);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "targetAccrualNumberRange"));
                            
                            
                                    if (localTargetAccrualNumberRange==null){
                                         throw new org.apache.axis2.databinding.ADBException("targetAccrualNumberRange cannot be null!!");
                                    }
                                    elementList.add(localTargetAccrualNumberRange);
                                

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
        public static StudyProtocol parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            StudyProtocol object =
                new StudyProtocol();

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
                    
                            if (!"StudyProtocol".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (StudyProtocol)gov.nih.nci.clinicalconnector.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","identifier").equals(reader.getName())){
                                
                                                object.setIdentifier(_21090.org.iso.II.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","coordinatingCenterStudyStatusCode").equals(reader.getName())){
                                
                                                object.setCoordinatingCenterStudyStatusCode(_21090.org.iso.CD.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","dataEntryStatusCode").equals(reader.getName())){
                                
                                                object.setDataEntryStatusCode(_21090.org.iso.CD.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","multiInstitutionIndicator").equals(reader.getName())){
                                
                                                object.setMultiInstitutionIndicator(_21090.org.iso.BL.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","officialTitle").equals(reader.getName())){
                                
                                                object.setOfficialTitle(_21090.org.iso.ST.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","phaseCode").equals(reader.getName())){
                                
                                                object.setPhaseCode(_21090.org.iso.CD.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","primaryPurposeCode").equals(reader.getName())){
                                
                                                object.setPrimaryPurposeCode(_21090.org.iso.CD.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","publicDescription").equals(reader.getName())){
                                
                                                object.setPublicDescription(_21090.org.iso.ST.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","publicTitle").equals(reader.getName())){
                                
                                                object.setPublicTitle(_21090.org.iso.ST.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","targetAccrualNumberRange").equals(reader.getName())){
                                
                                                object.setTargetAccrualNumberRange(_21090.org.iso.IVL_INT.Factory.parse(reader));
                                              
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
           
          

/**
 * StudySite.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:41 LKT)
 */
            
                package gov.nih.nci.clinicalconnector;
            

            /**
            *  StudySite bean class
            */
        
        public  class StudySite
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = StudySite
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
                        * field for DateRange
                        */

                        
                                    protected _21090.org.iso.IVL_TS localDateRange ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.IVL_TS
                           */
                           public  _21090.org.iso.IVL_TS getDateRange(){
                               return localDateRange;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DateRange
                               */
                               public void setDateRange(_21090.org.iso.IVL_TS param){
                            
                                            this.localDateRange=param;
                                    

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
                        * field for LeadOrganizationIndicator
                        */

                        
                                    protected _21090.org.iso.BL localLeadOrganizationIndicator ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.BL
                           */
                           public  _21090.org.iso.BL getLeadOrganizationIndicator(){
                               return localLeadOrganizationIndicator;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param LeadOrganizationIndicator
                               */
                               public void setLeadOrganizationIndicator(_21090.org.iso.BL param){
                            
                                            this.localLeadOrganizationIndicator=param;
                                    

                               }
                            

                        /**
                        * field for Name
                        */

                        
                                    protected _21090.org.iso.ST localName ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.ST
                           */
                           public  _21090.org.iso.ST getName(){
                               return localName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Name
                               */
                               public void setName(_21090.org.iso.ST param){
                            
                                            this.localName=param;
                                    

                               }
                            

                        /**
                        * field for PostalAddress
                        */

                        
                                    protected _21090.org.iso.AD localPostalAddress ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.AD
                           */
                           public  _21090.org.iso.AD getPostalAddress(){
                               return localPostalAddress;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PostalAddress
                               */
                               public void setPostalAddress(_21090.org.iso.AD param){
                            
                                            this.localPostalAddress=param;
                                    

                               }
                            

                        /**
                        * field for ReviewBoardApprovalDate
                        */

                        
                                    protected _21090.org.iso.TS localReviewBoardApprovalDate ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.TS
                           */
                           public  _21090.org.iso.TS getReviewBoardApprovalDate(){
                               return localReviewBoardApprovalDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReviewBoardApprovalDate
                               */
                               public void setReviewBoardApprovalDate(_21090.org.iso.TS param){
                            
                                            this.localReviewBoardApprovalDate=param;
                                    

                               }
                            

                        /**
                        * field for TargetAccrualNumber
                        */

                        
                                    protected _21090.org.iso.INT localTargetAccrualNumber ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.INT
                           */
                           public  _21090.org.iso.INT getTargetAccrualNumber(){
                               return localTargetAccrualNumber;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TargetAccrualNumber
                               */
                               public void setTargetAccrualNumber(_21090.org.iso.INT param){
                            
                                            this.localTargetAccrualNumber=param;
                                    

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
                       StudySite.this.serialize(parentQName,factory,xmlWriter);
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
                           namespacePrefix+":StudySite",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "StudySite",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localDateRange==null){
                                                 throw new org.apache.axis2.databinding.ADBException("dateRange cannot be null!!");
                                            }
                                           localDateRange.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","dateRange"),
                                               factory,xmlWriter);
                                        
                                            if (localIdentifier==null){
                                                 throw new org.apache.axis2.databinding.ADBException("identifier cannot be null!!");
                                            }
                                           localIdentifier.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","identifier"),
                                               factory,xmlWriter);
                                        
                                            if (localLeadOrganizationIndicator==null){
                                                 throw new org.apache.axis2.databinding.ADBException("leadOrganizationIndicator cannot be null!!");
                                            }
                                           localLeadOrganizationIndicator.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","leadOrganizationIndicator"),
                                               factory,xmlWriter);
                                        
                                            if (localName==null){
                                                 throw new org.apache.axis2.databinding.ADBException("name cannot be null!!");
                                            }
                                           localName.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","name"),
                                               factory,xmlWriter);
                                        
                                            if (localPostalAddress==null){
                                                 throw new org.apache.axis2.databinding.ADBException("postalAddress cannot be null!!");
                                            }
                                           localPostalAddress.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","postalAddress"),
                                               factory,xmlWriter);
                                        
                                            if (localReviewBoardApprovalDate==null){
                                                 throw new org.apache.axis2.databinding.ADBException("reviewBoardApprovalDate cannot be null!!");
                                            }
                                           localReviewBoardApprovalDate.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","reviewBoardApprovalDate"),
                                               factory,xmlWriter);
                                        
                                            if (localTargetAccrualNumber==null){
                                                 throw new org.apache.axis2.databinding.ADBException("targetAccrualNumber cannot be null!!");
                                            }
                                           localTargetAccrualNumber.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","targetAccrualNumber"),
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
                                                                      "dateRange"));
                            
                            
                                    if (localDateRange==null){
                                         throw new org.apache.axis2.databinding.ADBException("dateRange cannot be null!!");
                                    }
                                    elementList.add(localDateRange);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "identifier"));
                            
                            
                                    if (localIdentifier==null){
                                         throw new org.apache.axis2.databinding.ADBException("identifier cannot be null!!");
                                    }
                                    elementList.add(localIdentifier);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "leadOrganizationIndicator"));
                            
                            
                                    if (localLeadOrganizationIndicator==null){
                                         throw new org.apache.axis2.databinding.ADBException("leadOrganizationIndicator cannot be null!!");
                                    }
                                    elementList.add(localLeadOrganizationIndicator);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "name"));
                            
                            
                                    if (localName==null){
                                         throw new org.apache.axis2.databinding.ADBException("name cannot be null!!");
                                    }
                                    elementList.add(localName);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "postalAddress"));
                            
                            
                                    if (localPostalAddress==null){
                                         throw new org.apache.axis2.databinding.ADBException("postalAddress cannot be null!!");
                                    }
                                    elementList.add(localPostalAddress);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "reviewBoardApprovalDate"));
                            
                            
                                    if (localReviewBoardApprovalDate==null){
                                         throw new org.apache.axis2.databinding.ADBException("reviewBoardApprovalDate cannot be null!!");
                                    }
                                    elementList.add(localReviewBoardApprovalDate);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "targetAccrualNumber"));
                            
                            
                                    if (localTargetAccrualNumber==null){
                                         throw new org.apache.axis2.databinding.ADBException("targetAccrualNumber cannot be null!!");
                                    }
                                    elementList.add(localTargetAccrualNumber);
                                

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
        public static StudySite parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            StudySite object =
                new StudySite();

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
                    
                            if (!"StudySite".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (StudySite)gov.nih.nci.clinicalconnector.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","dateRange").equals(reader.getName())){
                                
                                                object.setDateRange(_21090.org.iso.IVL_TS.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","leadOrganizationIndicator").equals(reader.getName())){
                                
                                                object.setLeadOrganizationIndicator(_21090.org.iso.BL.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","name").equals(reader.getName())){
                                
                                                object.setName(_21090.org.iso.ST.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","postalAddress").equals(reader.getName())){
                                
                                                object.setPostalAddress(_21090.org.iso.AD.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","reviewBoardApprovalDate").equals(reader.getName())){
                                
                                                object.setReviewBoardApprovalDate(_21090.org.iso.TS.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","targetAccrualNumber").equals(reader.getName())){
                                
                                                object.setTargetAccrualNumber(_21090.org.iso.INT.Factory.parse(reader));
                                              
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
           
          
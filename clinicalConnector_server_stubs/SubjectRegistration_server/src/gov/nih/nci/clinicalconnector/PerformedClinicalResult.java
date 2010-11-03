
/**
 * PerformedClinicalResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:41 LKT)
 */
            
                package gov.nih.nci.clinicalconnector;
            

            /**
            *  PerformedClinicalResult bean class
            */
        
        public  class PerformedClinicalResult
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = PerformedClinicalResult
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
                        * field for AsCollectedIndicator
                        */

                        
                                    protected _21090.org.iso.BL localAsCollectedIndicator ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.BL
                           */
                           public  _21090.org.iso.BL getAsCollectedIndicator(){
                               return localAsCollectedIndicator;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AsCollectedIndicator
                               */
                               public void setAsCollectedIndicator(_21090.org.iso.BL param){
                            
                                            this.localAsCollectedIndicator=param;
                                    

                               }
                            

                        /**
                        * field for Comment
                        */

                        
                                    protected _21090.org.iso.SC localComment ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.SC
                           */
                           public  _21090.org.iso.SC getComment(){
                               return localComment;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Comment
                               */
                               public void setComment(_21090.org.iso.SC param){
                            
                                            this.localComment=param;
                                    

                               }
                            

                        /**
                        * field for ConfidentialityCode
                        */

                        
                                    protected _21090.org.iso.CD localConfidentialityCode ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.CD
                           */
                           public  _21090.org.iso.CD getConfidentialityCode(){
                               return localConfidentialityCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ConfidentialityCode
                               */
                               public void setConfidentialityCode(_21090.org.iso.CD param){
                            
                                            this.localConfidentialityCode=param;
                                    

                               }
                            

                        /**
                        * field for NumericalResult
                        */

                        
                                    protected _21090.org.iso.PQ localNumericalResult ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.PQ
                           */
                           public  _21090.org.iso.PQ getNumericalResult(){
                               return localNumericalResult;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param NumericalResult
                               */
                               public void setNumericalResult(_21090.org.iso.PQ param){
                            
                                            this.localNumericalResult=param;
                                    

                               }
                            

                        /**
                        * field for ReferenceRange
                        */

                        
                                    protected _21090.org.iso.IVL_PQ localReferenceRange ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.IVL_PQ
                           */
                           public  _21090.org.iso.IVL_PQ getReferenceRange(){
                               return localReferenceRange;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReferenceRange
                               */
                               public void setReferenceRange(_21090.org.iso.IVL_PQ param){
                            
                                            this.localReferenceRange=param;
                                    

                               }
                            

                        /**
                        * field for ReferenceRangeComment
                        */

                        
                                    protected _21090.org.iso.ST localReferenceRangeComment ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.ST
                           */
                           public  _21090.org.iso.ST getReferenceRangeComment(){
                               return localReferenceRangeComment;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReferenceRangeComment
                               */
                               public void setReferenceRangeComment(_21090.org.iso.ST param){
                            
                                            this.localReferenceRangeComment=param;
                                    

                               }
                            

                        /**
                        * field for ReportedDate
                        */

                        
                                    protected _21090.org.iso.TS localReportedDate ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.TS
                           */
                           public  _21090.org.iso.TS getReportedDate(){
                               return localReportedDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReportedDate
                               */
                               public void setReportedDate(_21090.org.iso.TS param){
                            
                                            this.localReportedDate=param;
                                    

                               }
                            

                        /**
                        * field for ReportedResultStatusCode
                        */

                        
                                    protected _21090.org.iso.CD localReportedResultStatusCode ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.CD
                           */
                           public  _21090.org.iso.CD getReportedResultStatusCode(){
                               return localReportedResultStatusCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReportedResultStatusCode
                               */
                               public void setReportedResultStatusCode(_21090.org.iso.CD param){
                            
                                            this.localReportedResultStatusCode=param;
                                    

                               }
                            

                        /**
                        * field for TextResult
                        */

                        
                                    protected _21090.org.iso.ST localTextResult ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.ST
                           */
                           public  _21090.org.iso.ST getTextResult(){
                               return localTextResult;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TextResult
                               */
                               public void setTextResult(_21090.org.iso.ST param){
                            
                                            this.localTextResult=param;
                                    

                               }
                            

                        /**
                        * field for UncertaintyCode
                        */

                        
                                    protected _21090.org.iso.CD localUncertaintyCode ;
                                

                           /**
                           * Auto generated getter method
                           * @return _21090.org.iso.CD
                           */
                           public  _21090.org.iso.CD getUncertaintyCode(){
                               return localUncertaintyCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param UncertaintyCode
                               */
                               public void setUncertaintyCode(_21090.org.iso.CD param){
                            
                                            this.localUncertaintyCode=param;
                                    

                               }
                            

                        /**
                        * field for PerformedObservation
                        */

                        
                                    protected gov.nih.nci.clinicalconnector.PerformedObservation localPerformedObservation ;
                                

                           /**
                           * Auto generated getter method
                           * @return gov.nih.nci.clinicalconnector.PerformedObservation
                           */
                           public  gov.nih.nci.clinicalconnector.PerformedObservation getPerformedObservation(){
                               return localPerformedObservation;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PerformedObservation
                               */
                               public void setPerformedObservation(gov.nih.nci.clinicalconnector.PerformedObservation param){
                            
                                            this.localPerformedObservation=param;
                                    

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
                       PerformedClinicalResult.this.serialize(parentQName,factory,xmlWriter);
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
                           namespacePrefix+":PerformedClinicalResult",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "PerformedClinicalResult",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localAsCollectedIndicator==null){
                                                 throw new org.apache.axis2.databinding.ADBException("asCollectedIndicator cannot be null!!");
                                            }
                                           localAsCollectedIndicator.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","asCollectedIndicator"),
                                               factory,xmlWriter);
                                        
                                            if (localComment==null){
                                                 throw new org.apache.axis2.databinding.ADBException("comment cannot be null!!");
                                            }
                                           localComment.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","comment"),
                                               factory,xmlWriter);
                                        
                                            if (localConfidentialityCode==null){
                                                 throw new org.apache.axis2.databinding.ADBException("confidentialityCode cannot be null!!");
                                            }
                                           localConfidentialityCode.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","confidentialityCode"),
                                               factory,xmlWriter);
                                        
                                            if (localNumericalResult==null){
                                                 throw new org.apache.axis2.databinding.ADBException("numericalResult cannot be null!!");
                                            }
                                           localNumericalResult.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","numericalResult"),
                                               factory,xmlWriter);
                                        
                                            if (localReferenceRange==null){
                                                 throw new org.apache.axis2.databinding.ADBException("referenceRange cannot be null!!");
                                            }
                                           localReferenceRange.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","referenceRange"),
                                               factory,xmlWriter);
                                        
                                            if (localReferenceRangeComment==null){
                                                 throw new org.apache.axis2.databinding.ADBException("referenceRangeComment cannot be null!!");
                                            }
                                           localReferenceRangeComment.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","referenceRangeComment"),
                                               factory,xmlWriter);
                                        
                                            if (localReportedDate==null){
                                                 throw new org.apache.axis2.databinding.ADBException("reportedDate cannot be null!!");
                                            }
                                           localReportedDate.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","reportedDate"),
                                               factory,xmlWriter);
                                        
                                            if (localReportedResultStatusCode==null){
                                                 throw new org.apache.axis2.databinding.ADBException("reportedResultStatusCode cannot be null!!");
                                            }
                                           localReportedResultStatusCode.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","reportedResultStatusCode"),
                                               factory,xmlWriter);
                                        
                                            if (localTextResult==null){
                                                 throw new org.apache.axis2.databinding.ADBException("textResult cannot be null!!");
                                            }
                                           localTextResult.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","textResult"),
                                               factory,xmlWriter);
                                        
                                            if (localUncertaintyCode==null){
                                                 throw new org.apache.axis2.databinding.ADBException("uncertaintyCode cannot be null!!");
                                            }
                                           localUncertaintyCode.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","uncertaintyCode"),
                                               factory,xmlWriter);
                                        
                                            if (localPerformedObservation==null){
                                                 throw new org.apache.axis2.databinding.ADBException("performedObservation cannot be null!!");
                                            }
                                           localPerformedObservation.serialize(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","performedObservation"),
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
                                                                      "asCollectedIndicator"));
                            
                            
                                    if (localAsCollectedIndicator==null){
                                         throw new org.apache.axis2.databinding.ADBException("asCollectedIndicator cannot be null!!");
                                    }
                                    elementList.add(localAsCollectedIndicator);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "comment"));
                            
                            
                                    if (localComment==null){
                                         throw new org.apache.axis2.databinding.ADBException("comment cannot be null!!");
                                    }
                                    elementList.add(localComment);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "confidentialityCode"));
                            
                            
                                    if (localConfidentialityCode==null){
                                         throw new org.apache.axis2.databinding.ADBException("confidentialityCode cannot be null!!");
                                    }
                                    elementList.add(localConfidentialityCode);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "numericalResult"));
                            
                            
                                    if (localNumericalResult==null){
                                         throw new org.apache.axis2.databinding.ADBException("numericalResult cannot be null!!");
                                    }
                                    elementList.add(localNumericalResult);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "referenceRange"));
                            
                            
                                    if (localReferenceRange==null){
                                         throw new org.apache.axis2.databinding.ADBException("referenceRange cannot be null!!");
                                    }
                                    elementList.add(localReferenceRange);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "referenceRangeComment"));
                            
                            
                                    if (localReferenceRangeComment==null){
                                         throw new org.apache.axis2.databinding.ADBException("referenceRangeComment cannot be null!!");
                                    }
                                    elementList.add(localReferenceRangeComment);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "reportedDate"));
                            
                            
                                    if (localReportedDate==null){
                                         throw new org.apache.axis2.databinding.ADBException("reportedDate cannot be null!!");
                                    }
                                    elementList.add(localReportedDate);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "reportedResultStatusCode"));
                            
                            
                                    if (localReportedResultStatusCode==null){
                                         throw new org.apache.axis2.databinding.ADBException("reportedResultStatusCode cannot be null!!");
                                    }
                                    elementList.add(localReportedResultStatusCode);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "textResult"));
                            
                            
                                    if (localTextResult==null){
                                         throw new org.apache.axis2.databinding.ADBException("textResult cannot be null!!");
                                    }
                                    elementList.add(localTextResult);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "uncertaintyCode"));
                            
                            
                                    if (localUncertaintyCode==null){
                                         throw new org.apache.axis2.databinding.ADBException("uncertaintyCode cannot be null!!");
                                    }
                                    elementList.add(localUncertaintyCode);
                                
                            elementList.add(new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov",
                                                                      "performedObservation"));
                            
                            
                                    if (localPerformedObservation==null){
                                         throw new org.apache.axis2.databinding.ADBException("performedObservation cannot be null!!");
                                    }
                                    elementList.add(localPerformedObservation);
                                

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
        public static PerformedClinicalResult parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            PerformedClinicalResult object =
                new PerformedClinicalResult();

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
                    
                            if (!"PerformedClinicalResult".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (PerformedClinicalResult)gov.nih.nci.clinicalconnector.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","asCollectedIndicator").equals(reader.getName())){
                                
                                                object.setAsCollectedIndicator(_21090.org.iso.BL.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","comment").equals(reader.getName())){
                                
                                                object.setComment(_21090.org.iso.SC.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","confidentialityCode").equals(reader.getName())){
                                
                                                object.setConfidentialityCode(_21090.org.iso.CD.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","numericalResult").equals(reader.getName())){
                                
                                                object.setNumericalResult(_21090.org.iso.PQ.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","referenceRange").equals(reader.getName())){
                                
                                                object.setReferenceRange(_21090.org.iso.IVL_PQ.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","referenceRangeComment").equals(reader.getName())){
                                
                                                object.setReferenceRangeComment(_21090.org.iso.ST.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","reportedDate").equals(reader.getName())){
                                
                                                object.setReportedDate(_21090.org.iso.TS.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","reportedResultStatusCode").equals(reader.getName())){
                                
                                                object.setReportedResultStatusCode(_21090.org.iso.CD.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","textResult").equals(reader.getName())){
                                
                                                object.setTextResult(_21090.org.iso.ST.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","uncertaintyCode").equals(reader.getName())){
                                
                                                object.setUncertaintyCode(_21090.org.iso.CD.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://clinicalconnector.nci.nih.gov","performedObservation").equals(reader.getName())){
                                
                                                object.setPerformedObservation(gov.nih.nci.clinicalconnector.PerformedObservation.Factory.parse(reader));
                                              
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
           
          
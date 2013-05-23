/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
            package org.openclinica.ws.ccts.subject.v1;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://openclinica.org/ws/ccts/subject/v1".equals(namespaceURI) &&
                  "StudyType".equals(typeName)){
                   
                            return  org.openclinica.ws.ccts.subject.v1.StudyType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://openclinica.org/ws/ccts/subject/v1".equals(namespaceURI) &&
                  "customStringType".equals(typeName)){
                   
                            return  org.openclinica.ws.ccts.subject.v1.CustomStringType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://openclinica.org/ws/ccts/subject/v1".equals(namespaceURI) &&
                  "genderType".equals(typeName)){
                   
                            return  org.openclinica.ws.ccts.subject.v1.GenderType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://openclinica.org/ws/ccts/subject/v1".equals(namespaceURI) &&
                  "SubjectType".equals(typeName)){
                   
                            return  org.openclinica.ws.ccts.subject.v1.SubjectType.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    

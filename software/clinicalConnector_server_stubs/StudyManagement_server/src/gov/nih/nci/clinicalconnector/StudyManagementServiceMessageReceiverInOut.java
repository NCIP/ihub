/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
        package gov.nih.nci.clinicalconnector;

        /**
        *  StudyManagementServiceMessageReceiverInOut message receiver
        */

        public class StudyManagementServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        StudyManagementServiceSkeleton skel = (StudyManagementServiceSkeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){

        

            if("rollbackCreateStudy".equals(methodName)){
                
                gov.nih.nci.clinicalconnector.RollbackCreateStudyResponse rollbackCreateStudyResponse1 = null;
	                        gov.nih.nci.clinicalconnector.RollbackCreateStudyRequest wrappedParam =
                                                             (gov.nih.nci.clinicalconnector.RollbackCreateStudyRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    gov.nih.nci.clinicalconnector.RollbackCreateStudyRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               rollbackCreateStudyResponse1 =
                                                   
                                                   
                                                         skel.rollbackCreateStudy(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), rollbackCreateStudyResponse1, false);
                                    } else 

            if("createStudy".equals(methodName)){
                
                gov.nih.nci.clinicalconnector.CreateStudyResponse createStudyResponse3 = null;
	                        gov.nih.nci.clinicalconnector.CreateStudyRequest wrappedParam =
                                                             (gov.nih.nci.clinicalconnector.CreateStudyRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    gov.nih.nci.clinicalconnector.CreateStudyRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               createStudyResponse3 =
                                                   
                                                   
                                                         skel.createStudy(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), createStudyResponse3, false);
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        }
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(gov.nih.nci.clinicalconnector.RollbackCreateStudyRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(gov.nih.nci.clinicalconnector.RollbackCreateStudyRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(gov.nih.nci.clinicalconnector.RollbackCreateStudyResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(gov.nih.nci.clinicalconnector.RollbackCreateStudyResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(gov.nih.nci.clinicalconnector.CreateStudyRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(gov.nih.nci.clinicalconnector.CreateStudyRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(gov.nih.nci.clinicalconnector.CreateStudyResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(gov.nih.nci.clinicalconnector.CreateStudyResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, gov.nih.nci.clinicalconnector.RollbackCreateStudyResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(gov.nih.nci.clinicalconnector.RollbackCreateStudyResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private gov.nih.nci.clinicalconnector.RollbackCreateStudyResponse wraprollbackCreateStudy(){
                                gov.nih.nci.clinicalconnector.RollbackCreateStudyResponse wrappedElement = new gov.nih.nci.clinicalconnector.RollbackCreateStudyResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, gov.nih.nci.clinicalconnector.CreateStudyResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(gov.nih.nci.clinicalconnector.CreateStudyResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private gov.nih.nci.clinicalconnector.CreateStudyResponse wrapcreateStudy(){
                                gov.nih.nci.clinicalconnector.CreateStudyResponse wrappedElement = new gov.nih.nci.clinicalconnector.CreateStudyResponse();
                                return wrappedElement;
                         }
                    


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (gov.nih.nci.clinicalconnector.RollbackCreateStudyRequest.class.equals(type)){
                
                           return gov.nih.nci.clinicalconnector.RollbackCreateStudyRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (gov.nih.nci.clinicalconnector.RollbackCreateStudyResponse.class.equals(type)){
                
                           return gov.nih.nci.clinicalconnector.RollbackCreateStudyResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (gov.nih.nci.clinicalconnector.CreateStudyRequest.class.equals(type)){
                
                           return gov.nih.nci.clinicalconnector.CreateStudyRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (gov.nih.nci.clinicalconnector.CreateStudyResponse.class.equals(type)){
                
                           return gov.nih.nci.clinicalconnector.CreateStudyResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    

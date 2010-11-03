
/**
 * SubjectRegistrationServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */
        package gov.nih.nci.clinicalconnector;

        /**
        *  SubjectRegistrationServiceMessageReceiverInOut message receiver
        */

        public class SubjectRegistrationServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        SubjectRegistrationServiceSkeleton skel = (SubjectRegistrationServiceSkeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJava(op.getName().getLocalPart())) != null)){

        

            if("rollbackRegisterSubject".equals(methodName)){
                
                gov.nih.nci.clinicalconnector.RegisterSubjectResponse registerSubjectResponse7 = null;
	                        gov.nih.nci.clinicalconnector.RegisterSubjectRequest wrappedParam =
                                                             (gov.nih.nci.clinicalconnector.RegisterSubjectRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    gov.nih.nci.clinicalconnector.RegisterSubjectRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               registerSubjectResponse7 =
                                                   
                                                   
                                                         skel.rollbackRegisterSubject(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), registerSubjectResponse7, false);
                                    } else 

            if("registerSubject".equals(methodName)){
                
                gov.nih.nci.clinicalconnector.RegisterSubjectResponse registerSubjectResponse9 = null;
	                        gov.nih.nci.clinicalconnector.RegisterSubjectRequest wrappedParam =
                                                             (gov.nih.nci.clinicalconnector.RegisterSubjectRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    gov.nih.nci.clinicalconnector.RegisterSubjectRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               registerSubjectResponse9 =
                                                   
                                                   
                                                         skel.registerSubject(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), registerSubjectResponse9, false);
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        } catch (CCDataValidationFaultMessage e) {

            msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,"CCDataValidationFault");
            org.apache.axis2.AxisFault f = createAxisFault(e);
            if (e.getFaultMessage() != null){
                f.setDetail(toOM(e.getFaultMessage(),false));
            }
            throw f;
            }
         catch (CCSystemFaultMessage e) {

            msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,"CCSystemFault");
            org.apache.axis2.AxisFault f = createAxisFault(e);
            if (e.getFaultMessage() != null){
                f.setDetail(toOM(e.getFaultMessage(),false));
            }
            throw f;
            }
         catch (CCBusinessFaultMessage e) {

            msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,"CCBusinessFault");
            org.apache.axis2.AxisFault f = createAxisFault(e);
            if (e.getFaultMessage() != null){
                f.setDetail(toOM(e.getFaultMessage(),false));
            }
            throw f;
            }
        
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(gov.nih.nci.clinicalconnector.RegisterSubjectRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(gov.nih.nci.clinicalconnector.RegisterSubjectRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(gov.nih.nci.clinicalconnector.RegisterSubjectResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(gov.nih.nci.clinicalconnector.RegisterSubjectResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(gov.nih.nci.clinicalconnector.CCBusinessFault param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(gov.nih.nci.clinicalconnector.CCBusinessFault.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(gov.nih.nci.clinicalconnector.CCDataValidationFault param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(gov.nih.nci.clinicalconnector.CCDataValidationFault.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(gov.nih.nci.clinicalconnector.CCSystemFault param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(gov.nih.nci.clinicalconnector.CCSystemFault.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, gov.nih.nci.clinicalconnector.RegisterSubjectResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(gov.nih.nci.clinicalconnector.RegisterSubjectResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private gov.nih.nci.clinicalconnector.RegisterSubjectResponse wraprollbackRegisterSubject(){
                                gov.nih.nci.clinicalconnector.RegisterSubjectResponse wrappedElement = new gov.nih.nci.clinicalconnector.RegisterSubjectResponse();
                                return wrappedElement;
                         }
                    
                         private gov.nih.nci.clinicalconnector.RegisterSubjectResponse wrapregisterSubject(){
                                gov.nih.nci.clinicalconnector.RegisterSubjectResponse wrappedElement = new gov.nih.nci.clinicalconnector.RegisterSubjectResponse();
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
        
                if (gov.nih.nci.clinicalconnector.RegisterSubjectRequest.class.equals(type)){
                
                           return gov.nih.nci.clinicalconnector.RegisterSubjectRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (gov.nih.nci.clinicalconnector.RegisterSubjectResponse.class.equals(type)){
                
                           return gov.nih.nci.clinicalconnector.RegisterSubjectResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (gov.nih.nci.clinicalconnector.CCBusinessFault.class.equals(type)){
                
                           return gov.nih.nci.clinicalconnector.CCBusinessFault.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (gov.nih.nci.clinicalconnector.CCDataValidationFault.class.equals(type)){
                
                           return gov.nih.nci.clinicalconnector.CCDataValidationFault.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (gov.nih.nci.clinicalconnector.CCSystemFault.class.equals(type)){
                
                           return gov.nih.nci.clinicalconnector.CCSystemFault.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (gov.nih.nci.clinicalconnector.RegisterSubjectRequest.class.equals(type)){
                
                           return gov.nih.nci.clinicalconnector.RegisterSubjectRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (gov.nih.nci.clinicalconnector.RegisterSubjectResponse.class.equals(type)){
                
                           return gov.nih.nci.clinicalconnector.RegisterSubjectResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (gov.nih.nci.clinicalconnector.CCBusinessFault.class.equals(type)){
                
                           return gov.nih.nci.clinicalconnector.CCBusinessFault.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (gov.nih.nci.clinicalconnector.CCDataValidationFault.class.equals(type)){
                
                           return gov.nih.nci.clinicalconnector.CCDataValidationFault.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (gov.nih.nci.clinicalconnector.CCSystemFault.class.equals(type)){
                
                           return gov.nih.nci.clinicalconnector.CCSystemFault.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

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
    
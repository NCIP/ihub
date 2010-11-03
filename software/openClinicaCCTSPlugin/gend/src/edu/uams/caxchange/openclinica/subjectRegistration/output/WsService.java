

/**
 * WsService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */

    package edu.uams.caxchange.openclinica.subjectRegistration.output;

    /*
     *  WsService java interface
     */

    public interface WsService {
          

        /**
          * Auto generated method signature
          * 
                    * @param rollbackRequest0
                
         */

         
                     public org.openclinica.ws.ccts.subject.v1.RollbackResponse rollback(

                        org.openclinica.ws.ccts.subject.v1.RollbackRequest rollbackRequest0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param rollbackRequest0
            
          */
        public void startrollback(

            org.openclinica.ws.ccts.subject.v1.RollbackRequest rollbackRequest0,

            final edu.uams.caxchange.openclinica.subjectRegistration.output.WsServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param commitRequest2
                
         */

         
                     public org.openclinica.ws.ccts.subject.v1.CommitResponse commit(

                        org.openclinica.ws.ccts.subject.v1.CommitRequest commitRequest2)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param commitRequest2
            
          */
        public void startcommit(

            org.openclinica.ws.ccts.subject.v1.CommitRequest commitRequest2,

            final edu.uams.caxchange.openclinica.subjectRegistration.output.WsServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    
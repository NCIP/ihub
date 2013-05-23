/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
    package edu.uams.caxchange.openclinica.subjectRegistration.output;

    /**
     *  WsServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class WsServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public WsServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public WsServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for rollback method
            * override this method for handling normal response from rollback operation
            */
           public void receiveResultrollback(
                    org.openclinica.ws.ccts.subject.v1.RollbackResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rollback operation
           */
            public void receiveErrorrollback(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for commit method
            * override this method for handling normal response from commit operation
            */
           public void receiveResultcommit(
                    org.openclinica.ws.ccts.subject.v1.CommitResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from commit operation
           */
            public void receiveErrorcommit(java.lang.Exception e) {
            }
                


    }
    

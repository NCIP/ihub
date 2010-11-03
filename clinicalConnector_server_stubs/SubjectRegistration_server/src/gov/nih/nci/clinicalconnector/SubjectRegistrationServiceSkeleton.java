
/**
 * SubjectRegistrationServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */
    package gov.nih.nci.clinicalconnector;
    /**
     *  SubjectRegistrationServiceSkeleton java skeleton for the axisService
     */
    public class SubjectRegistrationServiceSkeleton{


        /**
         * Auto generated method signature
         *
                                     * @param registerSubjectRequest
             * @throws CCBusinessFaultMessage :
             * @throws CCDataValidationFaultMessage :
             * @throws CCSystemFaultMessage :
         */

                 public gov.nih.nci.clinicalconnector.RegisterSubjectResponse rollbackRegisterSubject
                  (
                  gov.nih.nci.clinicalconnector.RegisterSubjectRequest registerSubjectRequest
                  )
            throws CCBusinessFaultMessage,CCDataValidationFaultMessage,CCSystemFaultMessage{
                //TODO : fill this with the necessary business logic
               try {
                 return (new SubjectRegistrationServiceServer()).rollbackRegisterSubject(registerSubjectRequest);
               }            
               catch(CCBusinessFaultMessage bfm){
               	throw bfm;
               }
               catch(CCSystemFaultMessage  sfm){
               	throw sfm;
               }
               catch(CCDataValidationFaultMessage dvfm){
               	throw dvfm;
               }
   			catch(Throwable t) {
   				throw getCCThrowable(t);

   		    }
        }


        /**
         * Auto generated method signature
         *
                                     * @param registerSubjectRequest0
             * @throws CCBusinessFaultMessage :
             * @throws CCDataValidationFaultMessage :
             * @throws CCSystemFaultMessage :
         */

                 public gov.nih.nci.clinicalconnector.RegisterSubjectResponse registerSubject
                  (
                  gov.nih.nci.clinicalconnector.RegisterSubjectRequest registerSubjectRequest0
                  )
            throws CCBusinessFaultMessage,CCDataValidationFaultMessage,CCSystemFaultMessage{
				try {
                 return (new SubjectRegistrationServiceServer()).registerSubject(registerSubjectRequest0);
			}
            catch(CCBusinessFaultMessage bfm){
            	throw bfm;
            }
            catch(CCSystemFaultMessage  sfm){
            	throw sfm;
            }
            catch(CCDataValidationFaultMessage dvfm){
            	throw dvfm;
            }
			catch(Throwable t) {
				throw getCCThrowable(t);

		    }
        }
                 
                 public CCSystemFaultMessage getCCThrowable(Throwable t){

     				CCSystemFault fault = new CCSystemFault();
    				CCSystemError systemError = new CCSystemError();
    				systemError.setMessage(t.getMessage());
    				systemError.setCode(SystemErrorCode.CC10000);
    				fault.setCCSystemFault(systemError);
    				CCSystemFaultMessage faultMessage = new CCSystemFaultMessage();
    				faultMessage.setFaultMessage(fault);
    				return faultMessage;
                 }

    }

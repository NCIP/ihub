/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.clinicalconnector;

public class CCDataValidationFaultMessage extends java.lang.Exception{
    
    private gov.nih.nci.clinicalconnector.CCDataValidationFault faultMessage;
    
    public CCDataValidationFaultMessage() {
        super("CCDataValidationFaultMessage");
    }
           
    public CCDataValidationFaultMessage(java.lang.String s) {
       super(s);
    }
    
    public CCDataValidationFaultMessage(java.lang.String s, java.lang.Throwable ex) {
      super(s, ex);
    }
    
    public void setFaultMessage(gov.nih.nci.clinicalconnector.CCDataValidationFault msg){
       faultMessage = msg;
    }
    
    public gov.nih.nci.clinicalconnector.CCDataValidationFault getFaultMessage(){
       return faultMessage;
    }
}
    

/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.clinicalconnector;

public class CCSystemFaultMessage extends java.lang.Exception{
    
    private gov.nih.nci.clinicalconnector.CCSystemFault faultMessage;
    
    public CCSystemFaultMessage() {
        super("CCSystemFaultMessage");
    }
           
    public CCSystemFaultMessage(java.lang.String s) {
       super(s);
    }
    
    public CCSystemFaultMessage(java.lang.String s, java.lang.Throwable ex) {
      super(s, ex);
    }
    
    public void setFaultMessage(gov.nih.nci.clinicalconnector.CCSystemFault msg){
       faultMessage = msg;
    }
    
    public gov.nih.nci.clinicalconnector.CCSystemFault getFaultMessage(){
       return faultMessage;
    }
}
    

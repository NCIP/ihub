
/**
 * CCDataValidationFaultMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
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
    
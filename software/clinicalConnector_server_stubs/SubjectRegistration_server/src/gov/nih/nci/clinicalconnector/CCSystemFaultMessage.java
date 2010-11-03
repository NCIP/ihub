
/**
 * CCSystemFaultMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
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
    

/**
 * CCBusinessFaultMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */

package gov.nih.nci.clinicalconnector;

public class CCBusinessFaultMessage extends java.lang.Exception{
    
    private gov.nih.nci.clinicalconnector.CCBusinessFault faultMessage;
    
    public CCBusinessFaultMessage() {
        super("CCBusinessFaultMessage");
    }
           
    public CCBusinessFaultMessage(java.lang.String s) {
       super(s);
    }
    
    public CCBusinessFaultMessage(java.lang.String s, java.lang.Throwable ex) {
      super(s, ex);
    }
    
    public void setFaultMessage(gov.nih.nci.clinicalconnector.CCBusinessFault msg){
       faultMessage = msg;
    }
    
    public gov.nih.nci.clinicalconnector.CCBusinessFault getFaultMessage(){
       return faultMessage;
    }
}
    
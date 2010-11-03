/**
 * CaXchangeRequestProcessorPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.caxchange.stubs;

public interface CaXchangeRequestProcessorPortType extends java.rmi.Remote {
    public gov.nih.nci.cagrid.caxchange.stubs.ProcessRequestAsynchronouslyResponse processRequestAsynchronously(gov.nih.nci.cagrid.caxchange.stubs.ProcessRequestAsynchronouslyRequest parameters) throws java.rmi.RemoteException, gov.nih.nci.cagrid.caxchange.stubs.types.CaXchangeFault;
    public gov.nih.nci.cagrid.caxchange.stubs.ProcessRequestSynchronouslyResponse processRequestSynchronously(gov.nih.nci.cagrid.caxchange.stubs.ProcessRequestSynchronouslyRequest parameters) throws java.rmi.RemoteException, gov.nih.nci.cagrid.caxchange.stubs.types.CaXchangeFault;
    public gov.nih.nci.cagrid.introduce.security.stubs.GetServiceSecurityMetadataResponse getServiceSecurityMetadata(gov.nih.nci.cagrid.introduce.security.stubs.GetServiceSecurityMetadataRequest parameters) throws java.rmi.RemoteException;
}

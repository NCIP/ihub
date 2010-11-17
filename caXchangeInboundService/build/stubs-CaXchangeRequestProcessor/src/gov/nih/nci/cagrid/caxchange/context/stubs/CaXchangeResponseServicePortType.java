/**
 * CaXchangeResponseServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.caxchange.context.stubs;

public interface CaXchangeResponseServicePortType extends java.rmi.Remote {
    public gov.nih.nci.cagrid.caxchange.context.stubs.GetResponseResponse getResponse(gov.nih.nci.cagrid.caxchange.context.stubs.GetResponseRequest parameters) throws java.rmi.RemoteException, gov.nih.nci.cagrid.caxchange.context.stubs.types.CaXchangeFault, gov.nih.nci.cagrid.caxchange.stubs.types.CaXchangeResponseNotReadyFault;
    public gov.nih.nci.cagrid.introduce.security.stubs.GetServiceSecurityMetadataResponse getServiceSecurityMetadata(gov.nih.nci.cagrid.introduce.security.stubs.GetServiceSecurityMetadataRequest parameters) throws java.rmi.RemoteException;
    public org.oasis.wsrf.lifetime.DestroyResponse destroy(org.oasis.wsrf.lifetime.Destroy destroyRequest) throws java.rmi.RemoteException, org.oasis.wsrf.lifetime.ResourceNotDestroyedFaultType, org.oasis.wsrf.lifetime.ResourceUnknownFaultType;
    public org.oasis.wsrf.lifetime.SetTerminationTimeResponse setTerminationTime(org.oasis.wsrf.lifetime.SetTerminationTime setTerminationTimeRequest) throws java.rmi.RemoteException, org.oasis.wsrf.lifetime.UnableToSetTerminationTimeFaultType, org.oasis.wsrf.lifetime.ResourceUnknownFaultType, org.oasis.wsrf.lifetime.TerminationTimeChangeRejectedFaultType;
}
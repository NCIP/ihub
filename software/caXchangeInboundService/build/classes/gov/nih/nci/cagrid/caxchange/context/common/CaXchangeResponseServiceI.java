package gov.nih.nci.cagrid.caxchange.context.common;

import java.rmi.RemoteException;

/** 
 * This class is autogenerated, DO NOT EDIT.
 * 
 * This interface represents the API which is accessable on the grid service from the client. 
 * 
 * @created by Introduce Toolkit version 1.1
 * 
 */
public interface CaXchangeResponseServiceI {

  /**
   * Get the response for the submitted request
   *
   * @throws CaXchangeFault
   *	
   * @throws CaXchangeResponseNotReadyFault
   *	
   */
  public gov.nih.nci.caxchange.ResponseMessage getResponse() throws RemoteException, gov.nih.nci.cagrid.caxchange.context.stubs.types.CaXchangeFault, gov.nih.nci.cagrid.caxchange.stubs.types.CaXchangeResponseNotReadyFault ;

  public org.oasis.wsrf.lifetime.DestroyResponse destroy(org.oasis.wsrf.lifetime.Destroy params) throws RemoteException ;

  public org.oasis.wsrf.lifetime.SetTerminationTimeResponse setTerminationTime(org.oasis.wsrf.lifetime.SetTerminationTime params) throws RemoteException ;

}


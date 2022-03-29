package org.neojc.buap.webservices;

public class HolaMundoProxy implements org.neojc.buap.webservices.HolaMundo {
  private String _endpoint = null;
  private org.neojc.buap.webservices.HolaMundo holaMundo = null;
  
  public HolaMundoProxy() {
    _initHolaMundoProxy();
  }
  
  public HolaMundoProxy(String endpoint) {
    _endpoint = endpoint;
    _initHolaMundoProxy();
  }
  
  private void _initHolaMundoProxy() {
    try {
      holaMundo = (new org.neojc.buap.webservices.HolaMundoServiceLocator()).getHolaMundo();
      if (holaMundo != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)holaMundo)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)holaMundo)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (holaMundo != null)
      ((javax.xml.rpc.Stub)holaMundo)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.neojc.buap.webservices.HolaMundo getHolaMundo() {
    if (holaMundo == null)
      _initHolaMundoProxy();
    return holaMundo;
  }
  
  public java.lang.String sayHelloWorld(java.lang.String name) throws java.rmi.RemoteException{
    if (holaMundo == null)
      _initHolaMundoProxy();
    return holaMundo.sayHelloWorld(name);
  }
  
  
}
package buap_api;

public class WebServicesBUAPPortTypeProxy implements buap_api.WebServicesBUAPPortType {
  private String _endpoint = null;
  private buap_api.WebServicesBUAPPortType webServicesBUAPPortType = null;
  
  public WebServicesBUAPPortTypeProxy() {
    _initWebServicesBUAPPortTypeProxy();
  }
  
  public WebServicesBUAPPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initWebServicesBUAPPortTypeProxy();
  }
  
  private void _initWebServicesBUAPPortTypeProxy() {
    try {
      webServicesBUAPPortType = (new buap_api.WebServicesBUAPLocator()).getWebServicesBUAPPort();
      if (webServicesBUAPPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)webServicesBUAPPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)webServicesBUAPPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (webServicesBUAPPortType != null)
      ((javax.xml.rpc.Stub)webServicesBUAPPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public buap_api.WebServicesBUAPPortType getWebServicesBUAPPortType() {
    if (webServicesBUAPPortType == null)
      _initWebServicesBUAPPortTypeProxy();
    return webServicesBUAPPortType;
  }
  
  public buap_api.RespProd getProd(java.lang.String user, java.lang.String password, java.lang.String categoria) throws java.rmi.RemoteException{
    if (webServicesBUAPPortType == null)
      _initWebServicesBUAPPortTypeProxy();
    return webServicesBUAPPortType.getProd(user, password, categoria);
  }
  
  public buap_api.RespDetail getDetails(java.lang.String user, java.lang.String password, java.lang.String producto) throws java.rmi.RemoteException{
    if (webServicesBUAPPortType == null)
      _initWebServicesBUAPPortTypeProxy();
    return webServicesBUAPPortType.getDetails(user, password, producto);
  }
  
  
}
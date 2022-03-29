package buap_api1;

public class WebServicesBUAP1PortTypeProxy implements buap_api1.WebServicesBUAP1PortType {
  private String _endpoint = null;
  private buap_api1.WebServicesBUAP1PortType webServicesBUAP1PortType = null;
  
  public WebServicesBUAP1PortTypeProxy() {
    _initWebServicesBUAP1PortTypeProxy();
  }
  
  public WebServicesBUAP1PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initWebServicesBUAP1PortTypeProxy();
  }
  
  private void _initWebServicesBUAP1PortTypeProxy() {
    try {
      webServicesBUAP1PortType = (new buap_api1.WebServicesBUAP1Locator()).getWebServicesBUAP1Port();
      if (webServicesBUAP1PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)webServicesBUAP1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)webServicesBUAP1PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (webServicesBUAP1PortType != null)
      ((javax.xml.rpc.Stub)webServicesBUAP1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public buap_api1.WebServicesBUAP1PortType getWebServicesBUAP1PortType() {
    if (webServicesBUAP1PortType == null)
      _initWebServicesBUAP1PortTypeProxy();
    return webServicesBUAP1PortType;
  }
  
  public buap_api1.RespuestaXML validaXML(int opcionArchivo) throws java.rmi.RemoteException{
    if (webServicesBUAP1PortType == null)
      _initWebServicesBUAP1PortTypeProxy();
    return webServicesBUAP1PortType.validaXML(opcionArchivo);
  }
  
  
}
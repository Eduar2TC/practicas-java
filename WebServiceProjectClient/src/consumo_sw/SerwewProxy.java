package consumo_sw;

public class SerwewProxy implements consumo_sw.Serwew {
  private String _endpoint = null;
  private consumo_sw.Serwew serwew = null;
  
  public SerwewProxy() {
    _initSerwewProxy();
  }
  
  public SerwewProxy(String endpoint) {
    _endpoint = endpoint;
    _initSerwewProxy();
  }
  
  private void _initSerwewProxy() {
    try {
      serwew = (new consumo_sw.SerwewServiceLocator()).getserwew();
      if (serwew != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)serwew)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)serwew)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (serwew != null)
      ((javax.xml.rpc.Stub)serwew)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public consumo_sw.Serwew getSerwew() {
    if (serwew == null)
      _initSerwewProxy();
    return serwew;
  }
  
  public int regresaId(java.lang.String qr, java.lang.String idCol) throws java.rmi.RemoteException{
    if (serwew == null)
      _initSerwewProxy();
    return serwew.regresaId(qr, idCol);
  }
  
  public java.lang.String registrarUsuario(java.lang.String noma, java.lang.String pasa, java.lang.String nom, java.lang.String pas, int niv) throws java.rmi.RemoteException{
    if (serwew == null)
      _initSerwewProxy();
    return serwew.registrarUsuario(noma, pasa, nom, pas, niv);
  }
  
  public java.lang.String cambiarPwd(java.lang.String noma, java.lang.String pasa, java.lang.String nom, java.lang.String pas, java.lang.String npas) throws java.rmi.RemoteException{
    if (serwew == null)
      _initSerwewProxy();
    return serwew.cambiarPwd(noma, pasa, nom, pas, npas);
  }
  
  public java.lang.String eliminarUsuario(java.lang.String noma, java.lang.String pasa, java.lang.String nom, java.lang.String pas) throws java.rmi.RemoteException{
    if (serwew == null)
      _initSerwewProxy();
    return serwew.eliminarUsuario(noma, pasa, nom, pas);
  }
  
  public boolean generaXML(java.lang.String clavp, java.lang.String nomp, java.lang.String catp, float precd, java.lang.String mard, java.lang.String desd) throws java.rmi.RemoteException{
    if (serwew == null)
      _initSerwewProxy();
    return serwew.generaXML(clavp, nomp, catp, precd, mard, desd);
  }
  
  public java.lang.String consultar() throws java.rmi.RemoteException{
    if (serwew == null)
      _initSerwewProxy();
    return serwew.consultar();
  }
  
  public java.lang.String registrarProd(java.lang.String nom, java.lang.String pas, java.lang.String clavp, java.lang.String nomp, java.lang.String catp, float precd, java.lang.String mard, java.lang.String desd) throws java.rmi.RemoteException{
    if (serwew == null)
      _initSerwewProxy();
    return serwew.registrarProd(nom, pas, clavp, nomp, catp, precd, mard, desd);
  }
  
  public java.lang.String borrarProd(java.lang.String nom, java.lang.String pas, java.lang.String clavp) throws java.rmi.RemoteException{
    if (serwew == null)
      _initSerwewProxy();
    return serwew.borrarProd(nom, pas, clavp);
  }
  
  public java.lang.String actualizarProd(java.lang.String nom, java.lang.String pas, java.lang.String clavp, java.lang.String nclavp, java.lang.String nomp, java.lang.String catp, float precd, java.lang.String mard, java.lang.String desd) throws java.rmi.RemoteException{
    if (serwew == null)
      _initSerwewProxy();
    return serwew.actualizarProd(nom, pas, clavp, nclavp, nomp, catp, precd, mard, desd);
  }
  
  public java.lang.String eliminarProd(java.lang.String nom, java.lang.String pas, java.lang.String clavp) throws java.rmi.RemoteException{
    if (serwew == null)
      _initSerwewProxy();
    return serwew.eliminarProd(nom, pas, clavp);
  }
  
  public java.lang.String obtenerProductos(java.lang.String nom, java.lang.String pas, java.lang.String cat) throws java.rmi.RemoteException{
    if (serwew == null)
      _initSerwewProxy();
    return serwew.obtenerProductos(nom, pas, cat);
  }
  
  public java.lang.String obtenerDetalles(java.lang.String nom, java.lang.String pas, java.lang.String clav) throws java.rmi.RemoteException{
    if (serwew == null)
      _initSerwewProxy();
    return serwew.obtenerDetalles(nom, pas, clav);
  }
  
  
}
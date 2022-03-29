/**
 * Serwew.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package consumo_sw;

public interface Serwew extends java.rmi.Remote {
    public int regresaId(java.lang.String qr, java.lang.String idCol) throws java.rmi.RemoteException;
    public java.lang.String registrarUsuario(java.lang.String noma, java.lang.String pasa, java.lang.String nom, java.lang.String pas, int niv) throws java.rmi.RemoteException;
    public java.lang.String cambiarPwd(java.lang.String noma, java.lang.String pasa, java.lang.String nom, java.lang.String pas, java.lang.String npas) throws java.rmi.RemoteException;
    public java.lang.String eliminarUsuario(java.lang.String noma, java.lang.String pasa, java.lang.String nom, java.lang.String pas) throws java.rmi.RemoteException;
    public boolean generaXML(java.lang.String clavp, java.lang.String nomp, java.lang.String catp, float precd, java.lang.String mard, java.lang.String desd) throws java.rmi.RemoteException;
    public java.lang.String consultar() throws java.rmi.RemoteException;
    public java.lang.String registrarProd(java.lang.String nom, java.lang.String pas, java.lang.String clavp, java.lang.String nomp, java.lang.String catp, float precd, java.lang.String mard, java.lang.String desd) throws java.rmi.RemoteException;
    public java.lang.String borrarProd(java.lang.String nom, java.lang.String pas, java.lang.String clavp) throws java.rmi.RemoteException;
    public java.lang.String actualizarProd(java.lang.String nom, java.lang.String pas, java.lang.String clavp, java.lang.String nclavp, java.lang.String nomp, java.lang.String catp, float precd, java.lang.String mard, java.lang.String desd) throws java.rmi.RemoteException;
    public java.lang.String eliminarProd(java.lang.String nom, java.lang.String pas, java.lang.String clavp) throws java.rmi.RemoteException;
    public java.lang.String obtenerProductos(java.lang.String nom, java.lang.String pas, java.lang.String cat) throws java.rmi.RemoteException;
    public java.lang.String obtenerDetalles(java.lang.String nom, java.lang.String pas, java.lang.String clav) throws java.rmi.RemoteException;
}

/**
 * WebServicesBUAPPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package buap_api;

public interface WebServicesBUAPPortType extends java.rmi.Remote {

    /**
     * Nos da una lista de productos de cada categoria.
     */
    public buap_api.RespProd getProd(java.lang.String user, java.lang.String password, java.lang.String categoria) throws java.rmi.RemoteException;

    /**
     * Detalles de un producto en particular
     */
    public buap_api.RespDetail getDetails(java.lang.String user, java.lang.String password, java.lang.String producto) throws java.rmi.RemoteException;
}

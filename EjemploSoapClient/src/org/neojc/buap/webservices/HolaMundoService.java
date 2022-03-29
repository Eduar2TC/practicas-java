/**
 * HolaMundoService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.neojc.buap.webservices;

public interface HolaMundoService extends javax.xml.rpc.Service {
    public java.lang.String getHolaMundoAddress();

    public org.neojc.buap.webservices.HolaMundo getHolaMundo() throws javax.xml.rpc.ServiceException;

    public org.neojc.buap.webservices.HolaMundo getHolaMundo(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}

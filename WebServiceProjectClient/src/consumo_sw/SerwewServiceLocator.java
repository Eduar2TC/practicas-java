/**
 * SerwewServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package consumo_sw;

public class SerwewServiceLocator extends org.apache.axis.client.Service implements consumo_sw.SerwewService {

    public SerwewServiceLocator() {
    }


    public SerwewServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SerwewServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for serwew
    private java.lang.String serwew_address = "http://localhost:8005/WebServiceProject/services/serwew";

    public java.lang.String getserwewAddress() {
        return serwew_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String serwewWSDDServiceName = "serwew";

    public java.lang.String getserwewWSDDServiceName() {
        return serwewWSDDServiceName;
    }

    public void setserwewWSDDServiceName(java.lang.String name) {
        serwewWSDDServiceName = name;
    }

    public consumo_sw.Serwew getserwew() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(serwew_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getserwew(endpoint);
    }

    public consumo_sw.Serwew getserwew(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            consumo_sw.SerwewSoapBindingStub _stub = new consumo_sw.SerwewSoapBindingStub(portAddress, this);
            _stub.setPortName(getserwewWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setserwewEndpointAddress(java.lang.String address) {
        serwew_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (consumo_sw.Serwew.class.isAssignableFrom(serviceEndpointInterface)) {
                consumo_sw.SerwewSoapBindingStub _stub = new consumo_sw.SerwewSoapBindingStub(new java.net.URL(serwew_address), this);
                _stub.setPortName(getserwewWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("serwew".equals(inputPortName)) {
            return getserwew();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://consumo_sw", "serwewService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://consumo_sw", "serwew"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("serwew".equals(portName)) {
            setserwewEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

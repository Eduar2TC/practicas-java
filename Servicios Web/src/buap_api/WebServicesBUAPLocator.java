/**
 * WebServicesBUAPLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package buap_api;

public class WebServicesBUAPLocator extends org.apache.axis.client.Service implements buap_api.WebServicesBUAP {

    public WebServicesBUAPLocator() {
    }


    public WebServicesBUAPLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WebServicesBUAPLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WebServicesBUAPPort
    private java.lang.String WebServicesBUAPPort_address = "http://localhost/webservices/test/productov4.php";

    public java.lang.String getWebServicesBUAPPortAddress() {
        return WebServicesBUAPPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WebServicesBUAPPortWSDDServiceName = "WebServicesBUAPPort";

    public java.lang.String getWebServicesBUAPPortWSDDServiceName() {
        return WebServicesBUAPPortWSDDServiceName;
    }

    public void setWebServicesBUAPPortWSDDServiceName(java.lang.String name) {
        WebServicesBUAPPortWSDDServiceName = name;
    }

    public buap_api.WebServicesBUAPPortType getWebServicesBUAPPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WebServicesBUAPPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWebServicesBUAPPort(endpoint);
    }

    public buap_api.WebServicesBUAPPortType getWebServicesBUAPPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            buap_api.WebServicesBUAPBindingStub _stub = new buap_api.WebServicesBUAPBindingStub(portAddress, this);
            _stub.setPortName(getWebServicesBUAPPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWebServicesBUAPPortEndpointAddress(java.lang.String address) {
        WebServicesBUAPPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (buap_api.WebServicesBUAPPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                buap_api.WebServicesBUAPBindingStub _stub = new buap_api.WebServicesBUAPBindingStub(new java.net.URL(WebServicesBUAPPort_address), this);
                _stub.setPortName(getWebServicesBUAPPortWSDDServiceName());
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
        if ("WebServicesBUAPPort".equals(inputPortName)) {
            return getWebServicesBUAPPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:buap_api", "WebServicesBUAP");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:buap_api", "WebServicesBUAPPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WebServicesBUAPPort".equals(portName)) {
            setWebServicesBUAPPortEndpointAddress(address);
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

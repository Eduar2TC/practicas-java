/**
 * WebServicesBUAP1Locator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package buap_api1;

public class WebServicesBUAP1Locator extends org.apache.axis.client.Service implements buap_api1.WebServicesBUAP1 {

    public WebServicesBUAP1Locator() {
    }


    public WebServicesBUAP1Locator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WebServicesBUAP1Locator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WebServicesBUAP1Port
    private java.lang.String WebServicesBUAP1Port_address = "http://localhost/webservices/test/validacionWS.php";

    public java.lang.String getWebServicesBUAP1PortAddress() {
        return WebServicesBUAP1Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WebServicesBUAP1PortWSDDServiceName = "WebServicesBUAP1Port";

    public java.lang.String getWebServicesBUAP1PortWSDDServiceName() {
        return WebServicesBUAP1PortWSDDServiceName;
    }

    public void setWebServicesBUAP1PortWSDDServiceName(java.lang.String name) {
        WebServicesBUAP1PortWSDDServiceName = name;
    }

    public buap_api1.WebServicesBUAP1PortType getWebServicesBUAP1Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WebServicesBUAP1Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWebServicesBUAP1Port(endpoint);
    }

    public buap_api1.WebServicesBUAP1PortType getWebServicesBUAP1Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            buap_api1.WebServicesBUAP1BindingStub _stub = new buap_api1.WebServicesBUAP1BindingStub(portAddress, this);
            _stub.setPortName(getWebServicesBUAP1PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWebServicesBUAP1PortEndpointAddress(java.lang.String address) {
        WebServicesBUAP1Port_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (buap_api1.WebServicesBUAP1PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                buap_api1.WebServicesBUAP1BindingStub _stub = new buap_api1.WebServicesBUAP1BindingStub(new java.net.URL(WebServicesBUAP1Port_address), this);
                _stub.setPortName(getWebServicesBUAP1PortWSDDServiceName());
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
        if ("WebServicesBUAP1Port".equals(inputPortName)) {
            return getWebServicesBUAP1Port();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:buap_api1", "WebServicesBUAP1");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:buap_api1", "WebServicesBUAP1Port"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WebServicesBUAP1Port".equals(portName)) {
            setWebServicesBUAP1PortEndpointAddress(address);
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

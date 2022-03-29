package WSTest;

import java.rmi.RemoteException;

import buap_api1.RespuestaXML;
import buap_api1.WebServicesBUAP1PortTypeProxy;
import buap_api.RespProd;
import buap_api.WebServicesBUAPPortTypeProxy;


public class WService {

	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		WebServicesBUAP1PortTypeProxy client = new WebServicesBUAP1PortTypeProxy();
		
		WebServicesBUAPPortTypeProxy client1 = new WebServicesBUAPPortTypeProxy();
		RespProd res = client1.getProd("eduardotc1", "qwerty1290", "libros");
		System.out.println(res.getLista());
		
		RespuestaXML respuestaXML;
			respuestaXML = client.validaXML(2);
			String codigoRespuesta = respuestaXML.getCodigo();
			String mensajeRespuesta = respuestaXML.getMensaje();
			System.out.println(codigoRespuesta + mensajeRespuesta);
	}

}

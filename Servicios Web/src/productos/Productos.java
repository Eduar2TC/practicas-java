package productos;

import java.rmi.RemoteException;
import java.util.ArrayList;

import buap_api.RespDetail;
import buap_api.RespProd;
import buap_api.WebServicesBUAPPortTypeProxy;

import buap_api1.RespuestaXML;
import buap_api1.WebServicesBUAP1PortTypeProxy;

public class Productos {
	private WebServicesBUAPPortTypeProxy client;  // Para obtener Datos del XML de los detalles o Lista Productos
	private WebServicesBUAP1PortTypeProxy client1; //Para validar los archivos XML
	
	private RespProd respuestaProd;
	private RespDetail respuestaDetalle;
	private RespuestaXML respuestaXML;
	
	private String usuario = "eduardotc";
	private String contrasenia = "qwerty1290";
	
	//peroductos datos
	private String categoria;
	private String claveProducto;
	//Constructor sin parametros
	public Productos() {}
	//Constructor con parametros
	public Productos(String usuario, String password, String categoria){
		this.client = new WebServicesBUAPPortTypeProxy();
		this.usuario = usuario;
		this.contrasenia = password;
		this.categoria = categoria;
		try {
			this.respuestaProd = this.client.getProd(this.usuario, this.contrasenia, this.categoria);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void setCategoria(String nuevaCategoria) {
		this.categoria = nuevaCategoria;
	}
	public String getCategoria() {
		return this.categoria;
	}
	public void setClave(String nuevaClave) {
		this.claveProducto = nuevaClave;
	}
	public String getClaveProducto() {
		return this.claveProducto;
	}
	public String getXmlDetalleProducto(String clave) {  //Obtiene el XML de los DETALLES DE UN PRODUCTO
		try {
			this.setClave(clave);
			this.client = new WebServicesBUAPPortTypeProxy();
			this.respuestaDetalle = this.client.getDetails(this.usuario, this.contrasenia, this.getClaveProducto());
			return this.respuestaDetalle.getProducto();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Error al retornar detalles del Producto Xml";
	}
	
	public String getListaProductos(String categoria) {
		ArrayList<String> lista = new ArrayList<String>();
		
		try {
			String listString = "";
			this.client = new WebServicesBUAPPortTypeProxy();
			this.respuestaProd = this.client.getProd(this.usuario, this.contrasenia, this.getCategoria());
			
			String XML = this.getXmlProductos(categoria);
			String fecha = this.respuestaProd.getFecha();
			int stock = this.respuestaProd.getStock();
			String code = this.respuestaProd.getCode();
			String message = this.respuestaProd.getMessage();
			
			lista.add(XML);
			lista.add(fecha);
			lista.add(Integer.toString(stock));
			lista.add(code);
			lista.add(message);
			
			for (String s : lista){
			    listString += s + "\n";
			}
			return listString;
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Error al tratar de obtener los datos";   //retornar error en caso de que no ocurra ningun de los eventos
	}

	
	public String getXmlProductos(String categoria) {  //Obtiene el Xml de La LISTA de los productos
		try {
			this.setCategoria(categoria);
			this.client = new WebServicesBUAPPortTypeProxy();
			this.respuestaProd = this.client.getProd(this.usuario, this.contrasenia, this.getCategoria());
			return this.respuestaProd.getLista();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Error al retornar Lista Xml";
		
	}
	public String getDetalleDeUnProducto(String claveProducto) {
		ArrayList<String> detalles = new ArrayList<String>();
		try {
			String detalle = "";
			this.client = new WebServicesBUAPPortTypeProxy();
			this.respuestaDetalle = this.client.getDetails(this.usuario, this.contrasenia, this.getClaveProducto());
			
			String XML = this.getXmlDetalleProducto(claveProducto);
			String fecha = this.respuestaDetalle.getFecha();
			String code = this.respuestaDetalle.getCode();
			String message = this.respuestaDetalle.getMessage();
			
			detalles.add(XML);
			detalles.add(fecha);
			detalles.add(code);
			detalles.add(message);
			
			for (String s : detalles){
			    detalle += s + "\n";
			}
			return detalle;
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Error al retornar Lista Xml";   //retornar error en caso de que no ocurra ningun de los eventos
	}
	
	public String validaXML(int opcionServicio) {
		this.client1 = new WebServicesBUAP1PortTypeProxy();
		if(opcionServicio == 1 || opcionServicio == 2) {  //lista de productos
			try {
				this.respuestaXML = client1.validaXML(opcionServicio);
				String codigoRespuesta = this.respuestaXML.getCodigo();
				String mensajeRespuesta = this.respuestaXML.getMensaje();
				return codigoRespuesta + " "+ mensajeRespuesta;
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "Error al tratar de validar el archivo XML";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Productos prod = new Productos("eduardotc", "qwerty1290", "libros" );
		//System.out.println(prod.getDetalleDeUnProducto("lib01"));
		Productos prod = new Productos();
		System.out.println(prod.validaXML(2));
		
		
	}
}

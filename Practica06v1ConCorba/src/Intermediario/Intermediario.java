package Intermediario;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;
import operacionesApp.*;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;

/*Aqui se recibiran las peticiones de la interfaz de usuario.
 * Inicializará el orb
 * Se enviaán las cadenas codificadas */
public class Intermediario {
	private String cadena1=null;
	private String cadena2=null;
	private String cadena3Operador=null;
	BufferedImage resultadoProceso=null;
	
	static procesamiento operacionesImplementacion;
	
	public Intermediario(String cadenaImagen1, String cadenaImagen2, String operador){
		this.cadena1 = cadenaImagen1;
		this.cadena2 = cadenaImagen2;
		this.cadena3Operador = operador;
	}
	public Intermediario() {
		
	}
	public void setCadena1(String nuevaCadena1) {
		this.cadena1 = nuevaCadena1;
	}
	public void setCadena2(String nuevaCadena2) {
		this.cadena2 = nuevaCadena2;
	}
	public void setCadenaOperador(String nuevaCadenaCadena) {
		this.cadena3Operador = nuevaCadenaCadena;
	}	
	
	public static  String codificarImagen(BufferedImage imagen, String type) {
        String imagenCadena = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
 
        try {
        	BASE64Encoder encoder = new BASE64Encoder();
        	
            ImageIO.write(imagen, type, bos);
            byte[] imagen1Bytes = bos.toByteArray();
            imagenCadena = encoder.encode(imagen1Bytes);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return imagenCadena;
	}	
	
	public static BufferedImage decodificarAImagen(String cadena) {
		 
	    BufferedImage imagen = null;
	    byte[] imagenEnBytes;
	    try {
	        BASE64Decoder decoder = new BASE64Decoder();
	        imagenEnBytes = decoder.decodeBuffer(cadena);
	        ByteArrayInputStream bis = new ByteArrayInputStream(imagenEnBytes);
	        imagen = ImageIO.read(bis);
	        bis.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return imagen;
	}
	public BufferedImage getResultado() {
		return resultadoProceso;
	}
	
	public void procedimiento(){
		try {
			java.lang.String[] args = new java.lang.String[0];
			Properties props = new Properties();
			props.put("org.omg.CORBA.ORBInitialHost","localhost"); //props.put("org.omg.CORBA.ORBInitialHost","HP-14"); --> Caso remoto
			props.put("org.omg.CORBA.ORBInitialPort","1050"); //props.put("org.omg.CORBA.ORBInitialPort","1080");	-->Puerto elegido
			ORB orb = ORB.init(args, props);
			//inicializar orb para enviar peticiones
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			String servicio = "ProcesamientoImagenes";  //Importante
			operacionesImplementacion = procesamientoHelper.narrow(ncRef.resolve_str(servicio));
			String resultadoCadena = operacionesImplementacion.procesarImagenes(cadena1, cadena2, cadena3Operador);
			this.resultadoProceso = Intermediario.decodificarAImagen(resultadoCadena);
			//operacionesImplementacion.shutdown();  //evitar que se cuelgue
		} catch (Exception e) {
			System.out.print("Error: " + e);
			e.printStackTrace(System.out);
		}
	}
	
}

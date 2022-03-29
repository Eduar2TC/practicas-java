package comunicacion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.script.ScriptException;

import calculadora.op.OpComplejas;
import calculadora.op.OpSimples;

public class DatagramaServer {
	
	private OpSimples operacionesSimples = null;
	
	float operadorUno;
	float operadorDos;
	String operador;
	
	float OperadorUnico;
	float resultado = 0;
	
	DatagramPacket datosPaquete;
	DatagramPacket retornaPackete;
	int port = 2018;
	int tamanio = 1024;
	
	
	public float Operaciones(String operador1, String operador2, String operacion) {		//a^n, suma, resta, div, multip, raiz cuadrada y al cuadrada hay que definirlas
		//operadorUno = Float.parseFloat(operador1);
		//operadorDos = Float.parseFloat(operador2);
		float resultado = 0;		
		char operacionChar = operacion.charAt(0);
		char raizCuadrado = operacion.charAt(2);
		
		if( ('+' == operacion.charAt(0) || '-' == operacion.charAt(0) || '*' == operacion.charAt(0) || '+' == operacion.charAt(0)  || '/' == operacion.charAt(0) || '%' == operacion.charAt(0) || '^' == operacion.charAt(0) || 's' == operacion.charAt(0) )) {
		
			operadorUno = Float.parseFloat(operador1);
			operadorDos = Float.parseFloat(operador2);
			
			switch(operacionChar) {
			case '+':{
				operacionesSimples = new OpSimples();
				resultado = operacionesSimples.suma(operadorUno, operadorDos);
				break;
			}
			case '-':{
				operacionesSimples = new OpSimples();
				resultado = operacionesSimples.resta(operadorUno, operadorDos);
				break;
			}
			case '*':{
				operacionesSimples = new OpSimples();
				resultado = operacionesSimples.multiplicacion(operadorUno, operadorDos);
				break;
			}
			case '/':{
				operacionesSimples = new OpSimples();
				resultado = operacionesSimples.division(operadorUno, operadorDos);
				break;	
			}
			case '%':{
				resultado = OpComplejas.Modulo(operadorUno, operadorDos);
				break;
			}
			case '^':{
				resultado = OpComplejas.aLaN(operadorUno,  operadorDos);
				break;
			}
			case 's' :{ //En el caso que el operador sea sqrt, se tomara el perimer operador
				resultado = OpComplejas.raizCudrada(operadorUno);
				break;
			}
		}
			System.out.println("me salte todo del primer if");
	 return resultado;
	 }
	else {
		switch(raizCuadrado) {
			case '2':{
				resultado = OpComplejas.alCuadrado(operadorUno);
				break;
			}
			case 'f':{
				System.out.println("Estoy en default");
				OpComplejas opc= new OpComplejas();
				try {
					resultado = (float) opc.CalculaDesdeCadena(operador1);
				} catch (ScriptException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		return resultado;
	}
}
	
	public void datagramaOperacion() throws IOException {
	
		DatagramSocket serverSocket = new DatagramSocket(2018);
		 byte[] receiveData = new byte[1024];
		 byte[] sendData = new byte[1024];
		 
		 while(true){
			 DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			 serverSocket.receive(receivePacket); 
			 
			 String sentence = new String(receivePacket.getData());
			 
			 String[] arregloDatos = extraeValores(sentence);
			 float resultadoOperacion = Operaciones(arregloDatos[0], arregloDatos[1], arregloDatos[2] );
			 System.out.println(resultadoOperacion);
			 InetAddress IPAddress = receivePacket.getAddress();
			 int port = receivePacket.getPort();
			 
			 /*String capitalizedSentence = sentence.toUpperCase();
			 sendData = capitalizedSentence.getBytes();*/
			 String resultadoString = Float.toString(resultadoOperacion);
			 sendData = resultadoString.getBytes();
			 DatagramPacket sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, port);
			 serverSocket.send(sendPacket); 
		 }
	
	}
	public String[] extraeValores(String extraer) { //Extraccion de los valores de las cadenas
		String[] arreglo = new String[extraer.split("\\s+").length];
        String[] cadena = extraer.split("\\s+");
        for(int i = 0; i< extraer.split("\\s+").length; i++) {
        	arreglo[i] = String.valueOf(cadena[i]);
        	System.out.println(arreglo[i]);
        }
        return arreglo;
/*		StringTokenizer tokenizer = new StringTokenizer(extraer);
		int numberOfTokens = tokenizer.countTokens();
		Object[] arreglo = new Object[numberOfTokens];
		 for (int i = 0; i < numberOfTokens; i++) {
			    arreglo[i] = tokenizer.nextToken();
		    }
		 return arreglo;*/
		  
		/*Object[] arreglo = new Object[3];
	        StringTokenizer tokens=new StringTokenizer(extraer," ");
	        int i =0;
	        while (tokens.hasMoreTokens()) {  
	             arreglo[i] = tokens.nextToken();  
	             i++;
	         }  
	        return arreglo;
	}*/
	/*    StringTokenizer cadena = new StringTokenizer(extraer);
	    int i = 0;
	    String arreglo[] = new String[2];
	    while (cadena.hasMoreTokens()) {
	        arreglo[i] = cadena.nextToken();
	        i++;
	    }
	    return arreglo;*/
	}

	public static void main(String args[]){
		DatagramaServer dts = new DatagramaServer();
		try {
			dts.datagramaOperacion();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

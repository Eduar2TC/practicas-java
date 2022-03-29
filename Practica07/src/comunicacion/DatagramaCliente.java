package comunicacion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class DatagramaCliente {
	
	String cadenaOperacion;
	
	String hostname ="localhost";
	int port = 2018;
	int tamanioDatagrama = 1024;
	
	public String resultado;
	
	public DatagramaCliente(String primerOperador, String segundoOperador, String operacion) {
		this.cadenaOperacion = primerOperador +" "+segundoOperador+" "+operacion;
	}
	
	public String opera() throws IOException {

		try{
			//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			InetAddress internetAdres = InetAddress.getByName(hostname); //Determina la dirección IP de un host, dado su nombre del host
			DatagramSocket datasocket = new DatagramSocket();
				
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName(hostname);
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			//String sentence = "100 66 +"; 
			String sentence = cadenaOperacion;
			sendData = sentence.getBytes();

			
			 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 2018);
			 clientSocket.send(sendPacket);
			 
			 DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			 clientSocket.receive(receivePacket);
			 String modifiedSentence = new String(receivePacket.getData());
			 resultado = modifiedSentence;
			 
			 System.out.println("RESPUESTA SERVIDOR:" + modifiedSentence);
			 clientSocket.close(); 
			
		}catch( UnknownHostException e ){
			System.err.println( e );
			}catch( SocketException se ){
			System.err.println( se );
			}
		return resultado;
		}
	
	public String getRes() {
		return resultado;
	}
/*public static void main(String[] args) {
		
		DatagramaCliente dtc = new DatagramaCliente("800.00/400.00*4", "5", "default");   //raiz (numero, "", "sqrt")
		try {
			dtc.opera();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/


}

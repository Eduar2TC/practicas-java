package PruebasSocketsUDP;

import java.net.*;
import java.io.*;

public class DatagramaServer
{
	public static void main(String args[])
	{
		DatagramPacket datapacket, returnpacket;
		
		int port = 2018;
		int len = 1024;
		
		try{
			DatagramSocket datasocket = new DatagramSocket(port); /*Construye un socket de datagramas y lo vincula con cualquier puerto
																	disponible en el localhost de la computadora.*/
			byte buf[]= new byte[len];
			
			while(true)
			{
				try{
					datapacket = new DatagramPacket(buf, buf.length);
					datasocket.receive(datapacket);
					
					returnpacket = new DatagramPacket(
							datapacket.getData(),
							datapacket.getLength(),
							datapacket.getAddress(),
							datapacket.getPort()
							);
					
					byte rbuffer[] = new byte[len];
					String retstring = new String( returnpacket.getData(), 0, returnpacket.getLength() );
					System.out.println( retstring );
										
					datasocket.send(returnpacket);
					
					
				}catch( IOException e )
				{
					System.err.println(e);
				}
			}
		}catch(SocketException se)
		{
			System.err.println(se);
		}
	}
}
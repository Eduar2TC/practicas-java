package Servidor;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import operacionesApp.procesamiento;
import operacionesApp.procesamientoHelper;

/*Iniciar Servidor local y remoto (1): en cmd ejecutar start orbd -ORBInitialPort 1050
 * 
 * Caso forma remota Servidor: 
 * 1) Exportar proyecto a jar
 * 2) ejecutar start orbd -ORBInitialPort 1080 -ORBInitialHost NombreComputadoraComoServidor en la PC servidor
 * 3) En cmd  java -jar NombreArchivo.jar -ORBInitialPort 1080
 * */
public class Servidor {
	
	public static void main(String args[]) {
		try {
			ORB orb = ORB.init(args, null);
			//inicializacion de conexion
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			//abrimos el root del POA
			rootpoa.the_POAManager().activate();
			//implementacionSuma impSuma = new implementacionSuma();
			ImageOperator imagenOperador = new ImageOperator();
			//implementamos referencia
			imagenOperador.setORB(orb);
			//seteamos ORB

			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(imagenOperador);
			procesamiento href = procesamientoHelper.narrow(ref);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			//le damos nombre al servicio
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			String name = "ProcesamientoImagenes";
			//se envia el nombre para que resiva la interface
			NameComponent path[] = ncRef.to_name(name);
			ncRef.rebind(path, href);
			System.out.println("Servidor en espera...");
			//confirmacion de servidor conectado
			orb.run();
		} catch (Exception e) {
			System.err.println("Error: " + e);
			e.printStackTrace(System.out);
		}

		System.out.println("Cerrando servidor");
	}

}

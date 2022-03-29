package clienteServer;
import java.util.Scanner;
import sumaApp.*;
//importar la interfaz idl
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class SumaClient {
	static suma sumaImpl;

	public static void main(String args[]) {
		Scanner sc = new Scanner (System.in);

		try {
			ORB orb = ORB.init(args, null);
			//inicializar orb para enviar peticiones
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			String name = "Suma";  //Importante
			sumaImpl = sumaHelper.narrow(ncRef.resolve_str(name));// System.out.println("Obteniendo las cabeceras del objeto servidor: // "+sumaImpl);
			System.out.print("1er numero : ");
			int a = sc.nextInt();
			System.out.print("2do numero : ");
			int b = sc.nextInt();
			System.out.println("Resultado :" + Integer.toString(sumaImpl.sumar(a, b)));
			//sumaImpl.shutdown();  //evitar que se cuelgue
		      while(true);
		} catch (Exception e) {
			System.out.print("Error: " + e);
			e.printStackTrace(System.out);
		}
	}
}
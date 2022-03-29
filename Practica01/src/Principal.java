import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Principal {
	
	public static int Menu() throws IOException {
		System.out.println(
				"Ejercicio 4 \n"
				+ "Ingresa alguna de las opciones.\n\n"
				+ "	1. Salida de texto mas opcion  de variables\n"
				+ "	2. Nombres validos de variables y operaciones\n"
				+ "	3. Salida de texto, variables y operaciones\n"
				+ "	4. Operaciones aritmeticas\n"
				+ "	5. Jerarquia de operadores\n"
				+ "	6. Tres formas distintas de imprimir en consola\n"
				+ "	7. Resolucion de problemas\n"
				+ "	8. Salir");	
				int Opcion = IngresarOpcion();
				return Opcion;
	}
	public static int IngresarOpcion() throws IOException{
		 BufferedReader lecturaDatos = new BufferedReader(new InputStreamReader(System.in));
		 System.out.print("Ingresar opcion :");
		 String temporal = lecturaDatos.readLine();
		 int Opcion = Integer.parseInt(temporal);
		 return Opcion;
	}
	public static float serieArmonica(int N) {
	      float Suma = 0;
	      for (int k = 1; k <= N; k++) {
	         Suma += 1.0 / k;
	      }
	      return Suma;
	}
	public static void Procesa(int Opcion) throws IOException {
		Scanner ingresaDato;
		switch(Opcion) {
		case 1:{
			ingresaDato = new Scanner(System.in);
			System.out.print("Escribe un flotante: ");
			float b = ingresaDato.nextFloat();
			System.out.print("Escribe otro flotante: ");
			float c = ingresaDato.nextFloat();
			float a = b*c;
			System.out.println("Resultado : " + a);
			break;
			}
		case 2:{
			System.out.print("2. Nombre valido de variables y operaciones\n"
					+ "a: falso"
					+"\n	Justificacion\n	Existen reglas de precedencia\n" +
					"	i. Primero las operaciones de incremento y decremento.\r\n" + 
					"	ii. Después, la multiplicación, división y módulo.\r\n" + 
					"	iii. Seguidamente, sumas y restas.\r\n" + 
					"	iv. Tras ello, las comparaciones.\r\n" + 
					"	v. El signo = se usa para establecer el valor de una variable");
			
			int _barra_inferior_, m928134, t5, j7, sus_ventas$, su_$cuenta_total, a, b$, c, z, z2;
			System.out.print("3. Salida de texto, variables y operaciones\n" +
					"	b: verdadero"+
					"	c: verdadero");
			
			break;
			}
		case 3:{
			int x=3;
			int y=2;
			System.out.printf( "x = %d \n", x );
			System.out.printf( "El valor de %d + %d es %d \n", x, x,(x+x) );
			System.out.printf( "x =" );
			System.out.printf( "%d = %d \n", (x+y), (y+x) );
			break;
			}
		case 4:{
			int y;
			int a=2;
			int x=3;
			y = a * x * x * x + 7;
			System.out.println("a: " + y);
			y = a * x * x * (x + 7);
			System.out.println("b: " + y);
			y = (a * x) * x * (x + 7);
			System.out.println("c: " + y);
			y = (a * x) * x * x + 7;
			System.out.println("d: " + y);
			y = a * (x * x * x) + 7;
			System.out.println("e: " + y);
			y = a * x * (x * x + 7);
			System.out.println("f: " + y);
			System.out.println("d y f son las correctas");
			break;
			}
		case 5:{
			int x;
			x = 7 + 3 * 6 / 2 - 1;
			System.out.println("Orden de evaluación\n"
					+ "	a)	"
					+ "	1.-> 6*3 = 18"
					+ "	2.-> 18/2 = 9"
					+ "	3.-> 7+9 = 16"
					+ "	4.-> 16-1 = 15"
					+ "\n	b)	"
					+ "	1.-> 2*2 = 4"
					+ "	2.-> 2/2 = 1"
					+ "	3.-> 2%2 = 0"
					+ "	4.-> 0 + 4 -1 = 3"
					+ "\n	c)	"
					+ "	1.-> 9*3 = 27"
					+ "	2.-> 27/3 = 9"
					+ "	3.-> 3 + 9 = 12"
					+ "	4.-> 3*9*12 = 324"
					);
			System.out.println("Resultados calculado");
			System.out.println("	a) x: " + x);
			x = 2 % 2 + 2 * 2 - 2 / 2;
			System.out.println("	b) x: " + x);
			x = ( 3 * 9 * ( 3 + ( 9 * 3 / ( 3 ) ) ) );
			System.out.println("	c) x: " + x);
			break;
			}
		case 6:{
			for(int i = 1; i<5; i++) {
				 System.out.println(i+ " ");
			}
			for(int i = 1; i<5; i++) {
				 System.out.print(i +" ");
			}
			System.out.println();
			for(int i = 1; i<5; i++) {
				 System.out.printf("%d ", i);
			}				
			break;
			
			}
		case 7:{
			int N = IngresarOpcion();
			float Resultado = serieArmonica(N);
			System.out.printf("R: %f", Resultado);
			break;
			}
		case 8:{
			System.out.println("Saliendo..."); break;
		}
		}	
	}	
	static String saludoUno = "Hola companiero";
	static String saludoDos = "Hola companiero de nuevo";
	
	public static void main(String[] args) throws IOException{
		System.out.println("2. Las version release son las actualizaciones del IDE Eclipse.\nY los packages son el conjunto de paquetes y herramientas necesarias para el desarrollo en un lenguaje de programación determinado.");
		//Verifica cuál es la diferencia entre System.out.print() y System.out.println()
		System.out.println("3. Diferencia entre print y println");
		System.out.println(saludoUno); //Imprime una cadena como argumento y crea una nueva linea
		System.out.print(saludoDos); // Imprime una cadena como argumento y no crea nueva linea
		
		System.out.println();System.out.println();
		boolean Salir = false;
		Scanner Respuesta;
		do {
			int Opcion = Menu();
			if(Salir == false && (Opcion <= 8 && Opcion >=1 )) {
				Procesa(Opcion);
				System.out.println(); ///BUG BUG BUG
				System.out.print("Continuar(y or n) : ");
				Respuesta = new Scanner(System.in);
				char Resp = Respuesta.next().charAt(0);  ///capturar cadena
				
				if(Resp == 'y') {
					Salir = false;
				}
				else {
					if(Resp == 'n') {
						System.out.println("Saliendo...");
						Salir = true;						
					}
				}
			}
			else {
			 System.out.println("	Opcion invalida!!");
			}
		}while(Salir == false);
			
	}
}

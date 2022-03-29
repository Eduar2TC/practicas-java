package parejas;

public class Mujer extends Persona {
	
	Hombre esposo;
	
	Mujer(String apellido, int edad) {
		super(apellido, edad);
	}
	
	/*Mujer(String apellido, int edad){ //constructor

		
	}*/
	
	public boolean soltero() { //prueba si es soltero
		
		if(this.esposo == null) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public Hombre getEsposo() { // regresa el esposo
	return this.esposo;
	}
	
	public void casar(Hombre hombre) { //casa si los dos son mayores
		
		if(this.edad > 17 && this.soltero() == true && hombre.edad > 17 && hombre.soltero() == true) {
			this.esposo = hombre;
			System.out.println("Soy mujer de apellido " + this.getApellido() + " y estoy casado con " + esposo.getApellido() );
		}
		else if(this.edad < 18) {
				System.out.println("No te puedes casar " + this.getApellido() + " alguien es menor de edad" );
			}
		else if(this.esposo != null) {
			System.out.println(this.getApellido() + " no te puedes casar dos veces");
		}
	}
}

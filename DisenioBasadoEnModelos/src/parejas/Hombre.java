package parejas;

public class Hombre extends Persona{
	
	Mujer esposa;
	
	Hombre(String apellido, int edad) {
		super(apellido, edad);
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean soltero() { //prueba si es soltero
		
		if(this.esposa == null) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public void casar(Mujer mujer) { //prueba si es soltero
		
		if(this.edad > 17 && this.soltero() == true && mujer.edad > 17 && mujer.soltero() == true) {
			this.esposa = mujer;
			System.out.println("Soy hombre de apellido : " + this.getApellido() + " y estoy casado con la mujer de apellido :" + esposa.getApellido() );
		}
		else if(this.edad < 18 || mujer.edad < 18) {
				System.out.println("No te puedes casar " + this.getApellido() + " alguien es menor de edad" );
			}
		else if(this.esposa != null) {
			System.out.println(this.getApellido() + " no te puedes casar dos veces");
		}
	}
	Mujer getEsposa() { //regresa esposa
		return this.esposa;
	}

}

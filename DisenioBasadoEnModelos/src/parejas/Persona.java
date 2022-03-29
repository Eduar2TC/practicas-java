package parejas;

public class Persona {
	
	String apellido;
	int edad;
	
	Persona(String apellido, int edad){  //constructor
		
		this.apellido = apellido;
		this.edad = edad;
		
	}
	public void envejecer() { //agregar un año a la edad
		
		this.edad++;
		
	}
	
	public String getApellido() {  //regresa el apellido de la persona
		return this.apellido;
	}
	
	public int getEdad() { // regresa la edad de la persona
		
		return this.edad;
		
	}
	

}

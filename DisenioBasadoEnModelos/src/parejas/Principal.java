package parejas;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Mujer fabiola = new Mujer("Reyes", 17);
		System.out.println("Apellido de la Chica " + fabiola.getApellido());
		Hombre donald = new Hombre("Trump", 27);
		System.out.println("Apellido del joven " +  donald.getApellido());
		
		
		fabiola.casar(donald);
		donald.casar(fabiola);		
		
		fabiola.envejecer();
		
		fabiola.casar(donald);
		
		Hombre yo = new Hombre("Tolentino", 28);
		
		fabiola.casar(yo);
		

	}

}

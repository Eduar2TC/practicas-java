package disenioUno;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Character eduardo;
		eduardo = new King();
		eduardo.display();
		eduardo.fight();
		
		eduardo.setWeaponBehavior(new BowBehavior());
		eduardo.fight();

	}

}

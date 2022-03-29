package disenioUno;

public class King extends Character{
	
	public King() {
		weapon = new SwordBehavior();
	}
	
	public void display() {
		System.out.println("Soy un rey");
	}

}

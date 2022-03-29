package disenioUno;

public abstract class Character {
	
	WeaponBehavior weapon;
	
	public Character() {}
	
	public void fight() {
		weapon.useWeapon();
	}
	
	public void setWeaponBehavior(WeaponBehavior wB) {
		this.weapon = wB;
	}
	
	public abstract void display();   // obliga a los hijos unicamente a hijos que hereden esta clase

}

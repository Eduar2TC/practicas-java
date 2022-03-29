package manipulacionVentana;

import javax.swing.JFrame;

public class Ventana extends JFrame{
private static final long serialVersionUID = 1L;

public Ventana() {
	this.setSize(200,200);
	this.setTitle("Mi primer ventana");
}
	public static void main(String[] args) {
		// Creacion de la nueva ventana
		Ventana nVentana = new Ventana();
		nVentana.setVisible(true);
	}

}

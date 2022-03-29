package Main;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ImageBackground;


public class Principal extends Game {
	
	Sprite oSprite;
	Sprite xSprite;
	
	Sprite humano;
	Sprite maquina;
	
	Sprite naranjaNegro;
	Sprite naranjaBlanco;
	
	Sprite azulBlanco;
	Sprite azulNegro;
	
	Sprite  moradoNegro;
	Sprite moradoBlanco;
	
	Sprite rosaNegro;
	Sprite rosaBlanco;
	
	Sprite amarilloNegro;
	Sprite amarilloBlanco;
	
	Sprite rojoNegro;
	Sprite rojoBlanco;
	
	Sprite verdeNegro;
	Sprite verdeBlanco;
	
	Sprite cafeNegro;
	Sprite cafeBlanco;
	
	
	Background fondo;
	
	int contador = -1;   // si contador es modulo cambia la imagen clickeada

 public void initResources() {
	 /*BufferedImage oImg = getImage("src/Main/imagenes/o.png");
	 int x =0;
	 int y =0;
	 oSprite = new Sprite(oImg, x, y);
	 xSprite = new Sprite(getImage("src/Main/imagenes/x.png"), 0, 100);   // imagen posicion (1,1)*/
	 
	 naranjaNegro = new Sprite(getImage("src/Main/imagenes/naranja-b.png"), 0, 0);
	 naranjaBlanco = new Sprite(getImage("src/Main/imagenes/naranja-w.png"), 350, 350);
	 
	 
	 azulNegro = new Sprite(getImage("src/Main/imagenes/azul-b.png"), 50, 0);
	 azulBlanco = new Sprite(getImage("src/Main/imagenes/azul-w.png"), 300, 350);
	 
	 moradoNegro = new Sprite(getImage("src/Main/imagenes/morado-b.png"), 100, 0);
	 moradoBlanco = new Sprite(getImage("src/Main/imagenes/morado-w.png"), 250, 350);
	 
	 rosaNegro = new Sprite(getImage("src/Main/imagenes/rosa-b.png"), 150, 0);
	 rosaBlanco = new Sprite(getImage("src/Main/imagenes/rosa-w.png"), 200, 350);
	 
	 amarilloNegro = new Sprite(getImage("src/Main/imagenes/amarillo-b.png"), 200, 0);
	 amarilloBlanco = new Sprite(getImage("src/Main/imagenes/amarillo-w.png"), 150, 350);
	 
	 rojoNegro = new Sprite(getImage("src/Main/imagenes/rojo-b.png"), 250, 0);
	 rojoBlanco = new Sprite(getImage("src/Main/imagenes/rojo-w.png"), 100, 350);
	 
	 verdeNegro = new Sprite(getImage("src/Main/imagenes/verde-b.png"), 300, 0);
	 verdeBlanco = new Sprite(getImage("src/Main/imagenes/verde-w.png"), 50, 350);
	 
	 cafeNegro = new Sprite(getImage("src/Main/imagenes/cafe-b.png"), 350, 0);
	 cafeBlanco = new Sprite(getImage("src/Main/imagenes/cafe-w.png"), 0, 350);
	 
	 
	 fondo = new ImageBackground(getImage("src/Main/imagenes/fondo-400x400.png"), 400, 400);
	 
	 
	 naranjaNegro.setBackground(fondo);
	 naranjaBlanco.setBackground(fondo);
	 
	 azulNegro.setBackground(fondo);
	 azulBlanco.setBackground(fondo);
	 
	 moradoNegro.setBackground(fondo);
	 moradoBlanco.setBackground(fondo);
	 
	 rosaNegro.setBackground(fondo);
	 rosaBlanco.setBackground(fondo);
	 
	 amarilloNegro.setBackground(fondo);
	 amarilloBlanco.setBackground(fondo);
	 
	 rojoNegro.setBackground(fondo);
	 rojoBlanco.setBackground(fondo);
	 
	 verdeNegro.setBackground(fondo);
	 verdeBlanco.setBackground(fondo);
	 
	 cafeNegro.setBackground(fondo);
	 cafeBlanco.setBackground(fondo);

 }

 public int getMouseX()   
 {   
     return bsInput.getMouseX();   
 }   

 public int getMouseY()   
 {   
     return bsInput.getMouseY();   
 } 
 
 
public boolean checkPosMouse(int x1, int y1, int x2, int y2) {
	return (this.getMouseX() >= x1 && this.getMouseY() >= y1 && 
			this.getMouseX() <= x2 && this.getMouseY() <= y2);
	}
 
public boolean click() {
		return this.bsInput.isMousePressed(MouseEvent.BUTTON1);
}

public boolean rightClick() {
	return this.bsInput.isMousePressed(MouseEvent.BUTTON3);
}


public void muevePieza(int xCoordenada, int yCoordenada, String pieza) {
	if(pieza == "Naranja") {
		
	}
}

public int convierteAMultiploDe50(int valorCoordenada) {
	
	if(valorCoordenada >= 0  && valorCoordenada < 50) {
		
		return valorCoordenada = 0;
	}

	if(valorCoordenada >= 50  && valorCoordenada < 100) {
		
		return valorCoordenada = 50;
	}

	if(valorCoordenada >= 100  && valorCoordenada < 150) {
		
		return valorCoordenada = 100;
	}

	if(valorCoordenada >= 150  && valorCoordenada < 200) {
		
		return valorCoordenada = 150;
	}
	
	if(valorCoordenada >= 200  && valorCoordenada < 250) {
		
		return valorCoordenada = 200;
	}

	if(valorCoordenada >= 250  && valorCoordenada < 300) {
		
		return valorCoordenada = 250;
	}

	if(valorCoordenada >= 300  && valorCoordenada < 350) {
		
		return valorCoordenada = 300;
	}

	if(valorCoordenada >= 350  && valorCoordenada < 400) {
		
		return valorCoordenada = 350;
	}



	return 0;
}

public void esMovimientoValido(String jugador, String pieza, String Estado) { 
	
	if(jugador == "Blanco" && (pieza == "Naranja" || pieza == "Cafe") && Estado == "Inicial") {
		
		int arregloY[] = {0, 50, 100, 150, 200, 350, 400};
		
		//Movimiento validos de las casillas a sus posibles casillas
		
		if(this.getMouseX() >= 350 && this.getMouseX() <= 400 && this.getMouseY() >= 0 && this.getMouseY() <= 400 ) {
			
			BufferedImage image;  // imagen temporal
	   		 naranjaBlanco = new Sprite(convierteAMultiploDe50(this.getMouseX()), convierteAMultiploDe50(this.getMouseY()) ); 
	   		 image = getImage("src/Main/imagenes/naranja-w.png");
	       	 naranjaBlanco.setImage(image);
	       	 contador++;
	       	 System.out.println(contador);
		}
		else if(this.convierteAMultiploDe50(this.getMouseX()) == this.convierteAMultiploDe50(this.getMouseY())) {
			
			 BufferedImage image;  // imagen temporal
	   		 naranjaBlanco = new Sprite(convierteAMultiploDe50(this.getMouseX()), convierteAMultiploDe50(this.getMouseY()) ); 
	   		 image = getImage("src/Main/imagenes/naranja-w.png");
	       	 naranjaBlanco.setImage(image);
	       	 contador++;
	       	 System.out.println(this.convierteAMultiploDe50(this.getMouseX())+ " "+ this.convierteAMultiploDe50(this.getMouseY()));
		}
	}
}

 public void update(long elapsedTime) {
	 
	 fondo.update(elapsedTime);
     
     naranjaNegro.update(elapsedTime);
     naranjaBlanco.update(elapsedTime);
     
     azulNegro.update(elapsedTime);
     azulBlanco.update(elapsedTime);
     
     moradoNegro.update(elapsedTime);
     moradoBlanco.update(elapsedTime);
     
     rosaNegro.update(elapsedTime);
     rosaBlanco.update(elapsedTime);
     
     amarilloNegro.update(elapsedTime);
     moradoBlanco.update(elapsedTime);
     
     rojoNegro.update(elapsedTime);
     rojoBlanco.update(elapsedTime);
     
     verdeNegro.update(elapsedTime);
     verdeBlanco.update(elapsedTime);
     
     cafeNegro.update(elapsedTime);
     cafeBlanco.update(elapsedTime);
      
     //actualiza matriz de imagenes
     /*
     actualizaImagen(0,       0,		50,		50);  //casilla (1,1)
     actualizaImagen(50,      0,		100,	50);  //casilla (1,2)
     actualizaImagen(100,     0,		150,	50);  //casilla (1,3)
     actualizaImagen(150,     0,		200,	50);  //casilla (1,4)
     actualizaImagen(200,     0,		250,	50);  //casilla (1,5)   
     actualizaImagen(200,     0,		300,	50);  //casilla (1,6)   
     actualizaImagen(250,     0,		350,	50);  //casilla (1,7)   
     actualizaImagen(350,     350,		400,	400);  //casilla (1,8)  */
     
     for(int y1 = 0; y1 <= 350; y1+=50) {
    	 
    	 int y2 = y1+50;
    	 
    	 for(int x1 = 0; x1 <= 350; x1+=50) {
    		 int x2 = x1+50;
    		 
    		 //System.out.println(x1 +",		" + y1 +"		"+ x2+"		"+y2 );
    		 
    	 }
     }
   

     
     if(click()) {
    	 
    	 System.out.println(this.getMouseX() + " " + this.getMouseY() );
    	 this.esMovimientoValido("Blanco", "Naranja", "Inicial");
     }
     
 }


 public void render(Graphics2D g) {
	 
	 fondo.render(g);
     
     naranjaBlanco.render(g);
     naranjaNegro.render(g);
     
     azulBlanco.render(g);
     azulNegro.render(g);
     
     moradoBlanco.render(g);
     moradoNegro.render(g);
     
     rosaBlanco.render(g);
     rosaNegro.render(g);
     
     amarilloBlanco.render(g);
     amarilloNegro.render(g);
     
     rojoBlanco.render(g);
     rojoNegro.render(g);
     
     verdeBlanco.render(g);
     verdeNegro.render(g);
     
     cafeBlanco.render(g);
     cafeNegro.render(g);
     
     }


/****************************************************************************/
/***************************** Inicio **********************************/
/****************************************************************************/

 public static void main(String[] args) {

     GameLoader game = new GameLoader();
     game.setup(new Principal(), new Dimension(400,400), false);
     game.start();
 }
 
}



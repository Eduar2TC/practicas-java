package Tablero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

//GTGE
import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ImageBackground;


public class Tablero extends Game {
	
	Sprite oSprite;
	Sprite xSprite;
	
	Sprite humano;
	Sprite maquina;
	
	Background fondo;
	
	int contador = -1;   // si contador es modulo cambia la imagen clickeada

 public void initResources() {
	 /*BufferedImage oImg = getImage("src/Tablero/imagenes/o.png");
	 int x =0;
	 int y =0;
	 oSprite = new Sprite(oImg, x, y);
	 xSprite = new Sprite(getImage("src/Tablero/imagenes/x.png"), 0, 100);   // imagen posicion (1,1)*/
	 oSprite = new Sprite();
	 xSprite = new Sprite();
	 humano = new Sprite(getImage("src/Tablero/imagenes/humano.png"), 50, 325);
	 maquina = new Sprite(getImage("src/Tablero/imagenes/maquina.png"), 150, 325);
	 
	 fondo = new ImageBackground(getImage("src/Tablero/imagenes/300x300a.png"), 300, 300);
	 
	 xSprite.setBackground(fondo);
	 oSprite.setBackground(fondo);


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

public void actualizaImagen(int x1, int y1, int x2, int y2) {
    
	BufferedImage image;  // imagen temporal
    
	if(click() && checkPosMouse(x1, y1, x2, y2) && contador % 2 == 0) {
    	
   		 oSprite = new Sprite(x1, y1); 
   		 image = getImage("src/Tablero/imagenes/x.png");
       	 oSprite.setImage(image);
       	 contador++;
       	 System.out.println("x");
       	 }

    else if(click() && checkPosMouse(x1, y1, x2, y2) && contador % 1 == 0) {
    	oSprite = new Sprite(x1, y1); 
		 image = getImage("src/Tablero/imagenes/o.png");
	   	 oSprite.setImage(image);
	   	 contador++;
	   	 System.out.println("o");
   	 }
    
}
 public void update(long elapsedTime) {
	 
     oSprite.update(elapsedTime);
     xSprite.update(elapsedTime);
     fondo.update(elapsedTime);
     humano.update(elapsedTime);
     maquina.update(elapsedTime);
     
     actualizaImagen(0,     0,		100,	100);  //casilla (1,1)
     actualizaImagen(100,	0,		200,	100);  //casilla (1,2)
     actualizaImagen(200,	0,		300,	100); //casilla (1,3)
     actualizaImagen(0,		100,	100,	200); //casilla (2,1)
     actualizaImagen(100,	100,	200,	200); // casilla (2,2)
     actualizaImagen(200,	100,	300,	200); //casilla (2,3)
     actualizaImagen(0,		200,	100,	300); //casilla (3,1)
     actualizaImagen(100,	200,	200,	300); //casilla (3,1)
     actualizaImagen(200,	200,	300,	300); //casilla (3,3)
    
     if(click() && checkPosMouse(50, 325, 50+90, 325+30) ) {  //Tamanio de imagen 90x30
    	 System.out.println("Soy humano");
     }
     
     if(click() && checkPosMouse(150, 325, 150+90, 325+30) ) {  //Tamanio de imagen 90x30
    	 System.out.println("Soy maquina");
     }
   

     /*
     if (keyPressed(KeyEvent.VK_SPACE)) {

    	 System.out.println("espacio");
    	 
     }
     else if (keyPressed(com.sun.glass.events.KeyEvent.VK_BACKSPACE)) {
    	 System.out.println("atras");
    	 
     }
     else if (keyPressed(com.sun.glass.events.KeyEvent.VK_CONTROL)) {
    	 System.out.println("actrl");
    	 
     }*/

 }


 public void render(Graphics2D g) {
	 g.setColor(Color.darkGray);
	 g.fillRect(0, 300, getWidth(), getHeight());
	 
	 fondo.render(g);
	 
	 g.drawLine(100, 0, 100, 300);  //lineas vert
	 g.drawLine(200, 0, 200, 300);
	 g.drawLine(0, 100, 300, 100);	 //lineas horiz
	 g.drawLine(0, 200, 300, 200);
	 
     oSprite.render(g);
     xSprite.render(g);
     humano.render(g);
     maquina.render(g);
     
     }


/****************************************************************************/
/***************************** START-POINT **********************************/
/****************************************************************************/

 public static void main(String[] args) {
     GameLoader game = new GameLoader();
     game.setup(new Tablero(), new Dimension(300,400), false);
     game.start();
 }
 
}



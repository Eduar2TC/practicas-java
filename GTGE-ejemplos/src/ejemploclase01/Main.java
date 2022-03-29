package ejemploclase01;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.object.background.ParallaxBackground;
import com.sun.glass.events.KeyEvent;

public class Main extends Game{
	static int dimXP = 800;
	static int dimYP = 500;
	static int dimXclip = 500;
	static int dimYclip = 500;
	
	Sprite sprite1;
	AnimatedSprite sprite2, sprite3;
	BufferedImage image;
	double posX, posY;
	double posX2, posY2;
	double x, y;
	
	ParallaxBackground fondo;
	Background f1, f2, f3, f4, f5;
	
	
	public void cargarImagenes() {
		image = getImage("imagenes/KOF03_Mai.png");
		posX = 200;
		posY = 300;
		posX2 = 400;
		posY2 = 400;
	sprite2 = new AnimatedSprite(getImages("imagenes/KOF03_Mai.ong", 10, 0 ));
	sprite2.setAnimate(true);
	sprite2.setLoopAnim(true);
	
	sprite3 = new AnimatedSprite(getImages("imagenes/KOF03_Mai.png", 10, 0));
	sprite3.setAnimate(true);
	}


	@Override
	public void initResources() {
		// TODO Auto-generated method stub
		f1 = new ImageBackground(getImage("imagenes/fondo01.png"));
		f2 = new ImageBackground(getImage("imagenes/fondo02.png"));
		f3 = new ImageBackground(getImage("imagenes/fondo03.png"));
		f4 = new ImageBackground(getImage("imagenes/fondo04.png"));
		fondo = new ParallaxBackground(new Background[] {f1, f2, f3, f4});
		
		cargarImagenes();
		sprite2.setBackground(fondo);
		sprite3.setBackground(fondo);
	}


	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub
		if(keyDown(KeyEvent.VK_RIGHT)) {
			if(sprite2.getX() + sprite2.getWidth()+2 < fondo.getWidth()) {
				sprite2.move(2,  0);
				System.out.println("x : " + sprite2.getX() + "Y : " + sprite2.getY());
			}
		}
		else if(keyDown(KeyEvent.VK_LEFT)) {
			if(sprite2.getX() - 2 > 0) {
				sprite2.move(-2, 0);
				System.out.println("x : " + sprite2.getX() + "Y : " + sprite2.getY());
			}
		}
		else if(keyDown(KeyEvent.VK_UP)) {
			if(sprite2.getY() - 2 > 0) {
				sprite2.move(0, -3);
				System.out.println("x : " + sprite2.getX() + "Y : " + sprite2.getY());
			}
		}
		else if(keyDown(KeyEvent.VK_DOWN)) {
			if(sprite2.getY() + sprite2.getHeight() + 2 < fondo.getHeight()) {
				sprite2.move(0, 3);
				System.out.println("x : " + sprite2.getX() + "Y : " + sprite2.getY());
			}
		}
		
		fondo.setToCenter(sprite2);
		sprite2.update(elapsedTime);
		sprite3.update(elapsedTime);
		fondo.update(elapsedTime);
		
	}
	
	@Override
	public void render(Graphics2D arg0) {
		// TODO Auto-generated method stub
		
	}

}

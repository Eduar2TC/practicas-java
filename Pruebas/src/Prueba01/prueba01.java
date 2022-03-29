package Prueba01;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
public class prueba01 {

	public static void main(String[] args) throws IOException {

			BufferedImage bigImg = ImageIO.read(new File("C://Users//HP 14//Desktop//ImagenSecciones//descarga.png"));
			// The above line throws an checked IOException which must be caught.

			final int width = 25;
			final int height = 25;
			final int rows = 4;
			final int cols = 4;
			BufferedImage[] sprites = new BufferedImage[rows * cols];  //procesarla, guardarla y mandar a visualizaar

			
			for (int i = 0; i < rows; i++)
			{
			    for (int j = 0; j < cols; j++)
			    {
			        sprites[(i * cols) + j] = bigImg.getSubimage(
			            j * width,
			            i * height,
			            width,
			            height
			        );
			    }
			}
			
			for(int i=0; i< sprites.length; i++) {
				try {
				    BufferedImage bi = sprites[i];  // retrieve image
				    File outputfile = new File("C://Users//HP 14//Desktop//ImagenSecciones//descarga"+i+".png");
				    ImageIO.write(bi, "png", outputfile);
				} catch (IOException e) {
				    // handle exception
				}	
			}


		    float f =  (float) 100.32434;
		    byte[] b = ByteBuffer.allocate(4).putFloat(f).array();  //[12, 24, 19, 17]
		    
		    float a = ByteBuffer.wrap(b).getFloat();
		        System.out.println(a);
			
	}

}

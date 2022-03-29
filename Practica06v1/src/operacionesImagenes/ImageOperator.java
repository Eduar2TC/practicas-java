package operacionesImagenes;

import java.awt.image.BufferedImage;

public class ImageOperator {
	private BufferedImage imagen1; // imagen1 a procesar
	private BufferedImage imagen2; // imagen2 dos a procesar
	private BufferedImage imagenResultado;  //resultado imagen
	
public ImageOperator(BufferedImage img1, BufferedImage img2){  // Inicializado por comboBox
	this.imagen1 = img1;
	this.imagen2 = img2;
	
}

public BufferedImage getResultadoImagen() {
	return imagenResultado;
}

public void multiplicaImagen() { 
	//Primero obtine el tamanio de la img mas grande
	
	int [] dimensiones = this.obtenerDimensionesNuevaImagen();
    int anchura = dimensiones[0];
	int altura = dimensiones[1];
    imagenResultado = new BufferedImage(anchura, altura, BufferedImage.TYPE_INT_RGB); //corregir el tamanio de la imagen con respecto a las 2! (corregido)
	
	
	for(int j = 0; j < altura; j++) {
		for(int i = 0; i < anchura; i++) {
			
			//multiplicacion de RGB componentes de las dos fuentes de imagenes
			

			int alpha1 = ((imagen1.getRGB(i, j)>> 24) & 0xFF);
			int red1 = ((imagen1.getRGB(i, j)>> 16) & 0xFF);
			int green1 = ((imagen1.getRGB(i, j)>> 8) & 0xFF);
			int blue1 = imagen1.getRGB(i, j) & 0xff;
			
			int alpha2 = ((imagen2.getRGB(i, j)>> 24) & 0xFF);
			int red2 = ((imagen2.getRGB(i, j)>> 16) & 0xFF);
			int green2 = ((imagen2.getRGB(i, j)>> 8) & 0xFF);
			int blue2 = imagen2.getRGB(i, j) & 0xff;
			
			int alpha = (alpha1 * alpha2)/255;
			int red = (red1 * red2)/255;
			int green = (green1 * green2)/255;
			int blue = (blue1 * blue2)/255;
			
			int rgb = ((alpha<<24) | (red << 16) | (green << 8) | blue);

			imagenResultado.setRGB(i, j, rgb);
        	
		}
	}
	
}

public void sumaImagen() {
	
	int [] dimensiones = this.obtenerDimensionesNuevaImagen();
    int anchura = dimensiones[0];
	int altura = dimensiones[1];
    imagenResultado = new BufferedImage(anchura, altura, BufferedImage.TYPE_INT_RGB); 
	
	
	for(int j = 0; j < altura; j++) {
		for(int i = 0; i < anchura; i++) {
			

			int alpha1 = ((imagen1.getRGB(i, j)>> 24) & 0xFF);
			int red1 = ((imagen1.getRGB(i, j)>> 16) & 0xFF);
			int green1 = ((imagen1.getRGB(i, j)>> 8) & 0xFF);
			int blue1 = imagen1.getRGB(i, j) & 0xff;
			
			int alpha2 = ((imagen2.getRGB(i, j)>> 24) & 0xFF);
			int red2 = ((imagen2.getRGB(i, j)>> 16) & 0xFF);
			int green2 = ((imagen2.getRGB(i, j)>> 8) & 0xFF);
			int blue2 = imagen2.getRGB(i, j) & 0xff;
			
			int alpha = (alpha1 + alpha2)/2;   //utilizacion del proc. f(x,y) = (x+y)/2
			int red = (red1 + red2)/2;
			int green = (green1 + green2)/2;
			int blue = (blue1 + blue2)/2;
			
			int rgb = ((alpha<<24) | (red << 16) | (green << 8) | blue);

			imagenResultado.setRGB(i, j, rgb);   //Cambia los valores rgb a la imagen resultado
			
        	
		}
	}
	
	
}

public void combinacionalImagen() {
	int [] dimensiones = this.obtenerDimensionesNuevaImagen();
    int anchura = dimensiones[0];
	int altura = dimensiones[1];
    imagenResultado = new BufferedImage(anchura, altura, BufferedImage.TYPE_INT_RGB); 
	
    float numAlfa = (float) Math.random();
    float numBeta = 1 - numAlfa;
	
	for(int j = 0; j < altura; j++) {
		for(int i = 0; i < anchura; i++) {
			

			int alpha1 = ((imagen1.getRGB(i, j)>> 24) & 0xFF);
			int red1 = ((imagen1.getRGB(i, j)>> 16) & 0xFF);
			int green1 = ((imagen1.getRGB(i, j)>> 8) & 0xFF);
			int blue1 = imagen1.getRGB(i, j) & 0xff;
			
			int alpha2 = ((imagen2.getRGB(i, j)>> 24) & 0xFF);
			int red2 = ((imagen2.getRGB(i, j)>> 16) & 0xFF);
			int green2 = ((imagen2.getRGB(i, j)>> 8) & 0xFF);
			int blue2 = imagen2.getRGB(i, j) & 0xff;
			
			int alpha = (int) ( numAlfa * alpha1 + numBeta * alpha2);
			int red = (int) (numAlfa * red1 + numBeta * red2);
			int green = (int) (numAlfa * green1 + numBeta * green2);
			int blue = (int) (numAlfa * blue1 + numBeta * blue2);
			
			int rgb = ((alpha<<24) | (red << 16) | (green << 8) | blue);

			imagenResultado.setRGB(i, j, rgb);  //Cambia los valores rgb a la imagen resultado
			
			//System.out.println("a" + alpha +  " r " + red + " g " + green + " b "+blue);
        	
		}
	}
		
}

public void restaImagen() {
	
	int [] dimensiones = this.obtenerDimensionesNuevaImagen();
    int anchura = dimensiones[0];
	int altura = dimensiones[1];
    imagenResultado = new BufferedImage(anchura, altura, BufferedImage.TYPE_INT_RGB); 
	
	
	for(int j = 0; j < altura; j++) {
		for(int i = 0; i < anchura; i++) {
			

			int alpha1 = ((imagen1.getRGB(i, j)>> 24) & 0xFF);
			int red1 = ((imagen1.getRGB(i, j)>> 16) & 0xFF);
			int green1 = ((imagen1.getRGB(i, j)>> 8) & 0xFF);
			int blue1 = imagen1.getRGB(i, j) & 0xff;
			
			int alpha2 = ((imagen2.getRGB(i, j)>> 24) & 0xFF);
			int red2 = ((imagen2.getRGB(i, j)>> 16) & 0xFF);
			int green2 = ((imagen2.getRGB(i, j)>> 8) & 0xFF);
			int blue2 = imagen2.getRGB(i, j) & 0xff;
			
			/*int alpha = 255/2 + (alpha1 - alpha2)/2;   //utilizacion del proc f(x,y) = 255 / 2 + (x-y)/2
			int red = 255/2 + (red1 - red2)/2;
			int green = 255/2 + (green1 - green2)/2;
			int blue = 255/2 + (blue1 - blue2)/2; */
			
			int alpha = Math.abs(alpha1 - alpha2);   //utilizacion de f(x,y)= |x-y|
			int red =  Math.abs(red1 - red2);
			int green = Math.abs(green1 - green2);
			int blue = Math.abs(blue1 - blue2);
			
			int rgb = ((alpha<<24) | (red << 16) | (green << 8) | blue);

			imagenResultado.setRGB(i, j, rgb);  //Cambia los valores rgb a la imagen resultado
			
			//System.out.println("a" + alpha +  " r " + red + " g " + green + " b "+blue);
        	
		}
	}
		
	
}

public int[] obtenerDimensionesNuevaImagen() { // funcion que decide cual imagen es mas grande para tomar su área como contenedora de la otra
	
	int anchuraImg1 = imagen1.getWidth();
	int alturaImg1 = imagen1.getHeight();
	
	int anchuraImg2 = imagen2.getWidth();
	int alturaImg2 = imagen2.getHeight();
	
	int anchura= 0;
	int altura = 0;
	
	
	if(anchuraImg1 < anchuraImg2) {
		anchura = anchuraImg1;
	}
	else if(anchuraImg2 < anchuraImg1) {
		anchura = anchuraImg2;
	}
	else { // caso que las dos anchura son iguales tomar por defecto a la anchura de una de los dos 
		
		anchura = (anchuraImg1 + anchuraImg2)/2; 		
	}
	
	if(alturaImg1 < alturaImg2) {
		altura = alturaImg1;
	}
	else if(alturaImg2 < alturaImg1) {
		altura = alturaImg2;
	}
	else { // caso que las dos anchura son iguales tomar por defecto a la anchura de una de los dos 
		
		altura = (alturaImg1 + alturaImg2)/2; 		
	}
	
	int[] dimensiones  = new int[] {anchura, altura};  //valores de altura y anchura minimos para la nueva imagen
	return dimensiones;

}

}

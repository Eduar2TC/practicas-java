package operacionesImagenes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import imageOperator.gui.imagenVisor;


@SuppressWarnings("unused")
public class ImageOperator implements Runnable {
	public BufferedImage imagen1; // imagen1 a procesar
	public BufferedImage imagen2; // imagen2 dos a procesar
	private BufferedImage imagenResultado;  //resultado imagen por ahora inutil
	
	volatile private BufferedImage imagenMinimizada; // Imagen que funciona como matriz y almacena los bloques
	private BufferedImage imagenUnoMin;
	private BufferedImage imagenDosMin;
	/*******************************************************************/
	//La imagen resultante se dividirá en bloques
	int numeroRenglones;  
	int numeroColumnas;
	int contadorBloques;  // mientras contadorBloque < Total bloques procesados repartir 1 bloque a los hilos
	int numeroBloque; //No inicializado ##Numero de Bloque a ser procesado
	boolean [] arregloDeBooleanos = null; // [0][1][0][1]  ejemplo de bloques activos posicion 1 y 3
	BufferedImage [] arregloDeBloques; // Util si queremos almacenar solo las imagenes
	Point[][] Coordenadas;
	/******************************************************************/
	
public ImageOperator(BufferedImage img1, BufferedImage img2, int nRenglones, int nColumnas){  // Inicializado por comboBox
	this.imagen1 = img1;
	this.imagen2 = img2;
	
	this.numeroRenglones = nRenglones;
	this.numeroColumnas = nColumnas;
	this.arregloDeBooleanos = this.generaArregloDeBloquesBooleanos(); 
	this.imagenMinimizada = minimizaImagen();  // Obtiene los datos de la nueva imagen
	//this.imagenUnoMin = obtenerSubImagen(numeroBloque, imagen1 );  // Sin inicializar funciona
	//this.imagenDosMin = obtenerSubImagen(numeroBloque, imagen2 );	// Sin inicializar funciona
}


public synchronized BufferedImage getResultadoImagen() {	// retorna el valor privado imagenResultado de la clase
	return imagenMinimizada;
}

public void setNumeroDeBloques(int numeroDeBloquesNuevo) {
	this.numeroBloque = numeroDeBloquesNuevo;
}

public void setImagen1(BufferedImage img1) {
	this.imagen1 = img1;
}

public void setImagen2(BufferedImage img2) {
	this.imagen2 = img2;
}

private synchronized void preSuma() throws InterruptedException { 
	
	//boolean[] arregloBooleanosDeBloques = this.generaArregloDeBloquesBooleanos();  //Genera el arreglo de boolenos nColumnas * nFilas
	int posicion = -1;
	
	for(int i=0; i< arregloDeBooleanos.length; i++) {	//Si encuentra un boolenano en n posicion procesa el numero de bloque
		if(arregloDeBooleanos[i] == false) {
			posicion = i;
			this.setNumeroDeBloques(posicion);                  //Esto es clave
			this.imagenUnoMin = obtenerSubImagen(posicion, imagen1 ); //Esto es clave
			this.imagenDosMin = obtenerSubImagen(posicion, imagen2 ); // Esto es clave
			BufferedImage r = sumaImagen(imagenUnoMin, imagenDosMin);
			try {
				recolocarImagen(r);
				this.arregloDeBooleanos[i] = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			Thread.sleep(500);
		}

	}
	for (int i =0; i< this.arregloDeBooleanos.length; i++) {
		System.out.println(this.arregloDeBooleanos[i]);
	}	

}

private synchronized BufferedImage minimizaImagen() {   //Sacar de esta imagen las coordenadas a operar para img1 e img2
	int [] dimensiones = this.obtenerDimensionesNuevaImagen();
    int anchura = dimensiones[0];
	int altura = dimensiones[1];
	BufferedImage imagenMinimizada = new BufferedImage(anchura, altura, BufferedImage.TYPE_INT_RGB);
	return imagenMinimizada;
}
//1 Minimizar image1 y 2
//2 Asignar a un bloque la seccion de imagen1 y otro bloque de la seccion2
private synchronized BufferedImage obtenerSubImagen(int numeroDeBloque, BufferedImage imagen ) {  //Buffered image = imagen1, imagen2
	
	Point[] Coordenadas = obtieneCoordenadas(numeroDeBloque);
	int anchoBloque = this.imagenMinimizada.getWidth() / this.numeroColumnas;
	int altoBloque = this.imagenMinimizada.getHeight() / this.numeroRenglones;
	BufferedImage subImagen = imagen.getSubimage((int)Coordenadas[0].getX(), (int)Coordenadas[0].getY(), anchoBloque, altoBloque);
	return subImagen;
}

private synchronized void recolocarImagen(BufferedImage imagenesResultado) throws IOException {  //Recibir de subimagen ya procesada por suma
	//0 Las imagenes imagenUnoMin, imagenDosMin ya inicializadas
	//1 Obtener el bloque de la suma  <--
	//2 Obtener las coordenadas
	//3 Aplicar la reecolocacion
	
	Point[] coordenada = obtieneCoordenadas(numeroBloque);

	BufferedImage target = new BufferedImage(imagenMinimizada.getWidth(), imagenMinimizada.getHeight(), BufferedImage.TYPE_INT_ARGB);
	Graphics2D g = (Graphics2D) target.getGraphics();
	g.drawImage(imagenMinimizada, 0, 0, null);
	g.drawImage(imagenResultado, (int)coordenada[0].getX(), (int)coordenada[0].getY(), null);
	imagenMinimizada = target;
	File outputfile = new File("C://Users//HP 14//Desktop//ImagenSecciones//targetimage.png");
	ImageIO.write(target, "png", outputfile);
}

private synchronized BufferedImage sumaImagen(BufferedImage imagen1, BufferedImage imagen2) {
	
    imagenResultado = new BufferedImage(imagenMinimizada.getWidth(), imagenMinimizada.getHeight(), BufferedImage.TYPE_INT_RGB); //corregir el tamanio de la imagen con respecto a las 2! (corregido)

	for(int j = 0; j < imagen1.getHeight(); j++) {   //Corregir!!!
		for(int i = 0; i < imagen1.getWidth(); i++) {
			

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
	
	return imagenResultado;	
}

private synchronized int[] obtenerDimensionesNuevaImagen() { // funcion que decide cual imagen es mas grande para tomar su área como contenedora de la otra
	
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
/* Funciones		de 			bloques*/

public int[] pixel2Matrix(BufferedImage ImagenNoProceda) throws IOException { //ARREGLO BYTES (BufferedImage) -> ARREGLO ENTEROS ó flotante
	
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(ImagenNoProceda, "PNG", baos);
	baos.flush();
	byte[] imagenEnBytes = baos.toByteArray();
	// byte sequence -> BufferedImage
		//ByteArrayInputStream bais = new ByteArrayInputStream(imagenEnAbytes);
	//BufferedImage img = ImageIO.read(bais);
		//imagenResultado = ImageIO.read(bais);
	baos.close();
	
	int[] arregloDeEnteros = new int[imagenEnBytes.length];
	for(int i =0; i < imagenEnBytes.length; i++) {
		arregloDeEnteros[i] = imagenEnBytes[i] & 0xff;   //Rango de 0 a 255, asignacion de valores positivos al arreglo
	}
	return arregloDeEnteros;
}

public byte[] matrix2Pixel(int [] arregloDeEnteros) { //ARREGLO ENTEROS -> ARREGLO BYTES 
	int tamanio = arregloDeEnteros.length;
	byte []destinoImagen = new byte[tamanio << 2];
	
    for (int i=0; i < tamanio; i++) {
        int x = arregloDeEnteros[i];
        int j = i << 2;
        destinoImagen[j++] = (byte) ((x >>> 0) & 0xff);           
        destinoImagen[j++] = (byte) ((x >>> 8) & 0xff);
        destinoImagen[j++] = (byte) ((x >>> 16) & 0xff);
        destinoImagen[j++] = (byte) ((x >>> 24) & 0xff);
    }
    return destinoImagen;
}

private synchronized boolean[] generaArregloDeBloquesBooleanos() {  // crea un arreglo de booleanos con respecto al numero de bloques o SECCIONES
	//Generar aleatoriamente (por el momento 0s y 1s)
	int numeroDeBloques = numeroRenglones * numeroColumnas;
	arregloDeBooleanos = new boolean[numeroDeBloques];
	
	//Inicializar el arreglo de bloques booleanos
	//Random random = new Random();
	Random random = new Random();
    for(int i = 0; i < numeroDeBloques; i++) {
        //arregloDeBooleanos[i] = random.nextBoolean();
    	arregloDeBooleanos[i] = false;
    }
	return arregloDeBooleanos;
	
}
private synchronized void LlenaArregloBloquesconSubimagenes() {   //Funcion inutil si se desea pasar SOLO LAS COORDENADAS
	
	int anchoBloque = this.imagenResultado.getWidth() / this.numeroColumnas;  // Anchura del Bloque
	int altoBloque = this.imagenResultado.getHeight() / this.numeroRenglones;	//Altura del bloque
	
	for (int i = 0; i < this.numeroRenglones; i++)
	{
	    for (int j = 0; j < this.numeroColumnas; j++)
	    {
	        arregloDeBloques[(i * this.numeroColumnas) + j] = imagenResultado.getSubimage(
	            j * anchoBloque,
	            i * altoBloque,
	            anchoBloque,
	            altoBloque
	        );
	
	    }
	}
}


private synchronized Point[] obtieneCoordenadas(int numeroBloque) {
	
	//Coordenadas = new Point[this.numeroRenglones][this.numeroColumnas];
	Point[][] Coordenadas = null;
	Point[] CoordenadaBloque = null;  //Almacena la coordenada de la subImagen a procesar
	int Contador =0;
	
	int anchoBloque = this.imagenMinimizada.getWidth() / this.numeroColumnas;  // Anchura del Bloque
	int altoBloque = this.imagenMinimizada.getHeight() / this.numeroRenglones;	//Altura del bloque
	
	Coordenadas = new Point[this.numeroRenglones][this.numeroColumnas];
	
	for (int i = 0; i < this.numeroRenglones; i++){
	    for (int j = 0; j < this.numeroColumnas; j++){
	    	Coordenadas[i][j] = new Point(j * anchoBloque, i * altoBloque);
	    }
	}
	
	for (int i = 0; i < this.numeroRenglones; i++){
	    for (int j = 0; j < this.numeroColumnas; j++){	    	
		    	if(Contador == numeroBloque) { // Si la posicion del contadoor es igual al numero de bloque hace
		    	  	CoordenadaBloque = new Point[] {Coordenadas[i][j]};
		    	  	}
		  	Contador++;
	    }
	}
	
	for(int i =0; i< CoordenadaBloque.length; i++) {
		System.out.println(CoordenadaBloque[i]);
	}
	return CoordenadaBloque;
}

public void Ejecutando(Object parameter) {
    
}

@Override
public void run() {
	try { 
		this.preSuma();	
		Thread.sleep(100);

	    System.out.println(Thread.currentThread().getName());
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}


}
package operacionesImagenes;

import java.awt.Point;

/*Obtener el ancho y alto de la imagen getwith y heigth.  
  Dividir el resultado Imagen entre el numero de seccion para obtener y procesar la seccion
  Obtener el ancho y alto con respecto al numero de renglones y el numero de columnas -que se desea dividir la imagenResultado
*/

public class Chunk { //Trozo o seccion de la imagen Resultado
	Point coordenadaPosicion; //Posicion actual de la seccion con respecto a la imagenResultado O NUMERO DE BLOQUE
	int renglonesBloque; // ancho de bloque
	int columnasBloque;	//alto de bloque

public Chunk(Point Coordenadas, int renglones, int columnas) {
	this.coordenadaPosicion = Coordenadas;
	this.renglonesBloque = renglones;
	this.columnasBloque = columnas;
	}	

}
		
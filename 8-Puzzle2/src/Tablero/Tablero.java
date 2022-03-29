package Tablero;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Tablero {
	
	Boolean esSolucionable() {
		
		return false;
	}
	
	public int[] getPosicionCasillaVacia(String arreglo[][]) {
		int posicion [] = null;
        for (int i=0; i < arreglo[0].length; i++) {
        	
            for (int j=0; j < arreglo[i].length; j++) {
				  
            	 if(arreglo[i][j].equals("0")) {
            		 //System.out.println("Casilla encontrada en la posicion: " + i + "," + j);
            		 posicion = new int []{i, j};  
            	 }
            }
         }
		return posicion;
	}
	
	public int obtenieneNumeroDeAyacentes(String arreglo[][]) {// indica el numero de hijos que se crearán del estdo padre 
		
		int nroAdyacentes = 4;
		
		for(int i =0; i < arreglo[0].length; i++) {
			
			for(int j = 0; j < arreglo[i].length; j++) {
				
				if(  	i == 0 && j == 0 && arreglo[i][j].equals("0") || 
						i == 0 && j == arreglo[i].length-1 && arreglo[i][j].equals("0") || 
						i == arreglo[0].length-1 && j == 0 && arreglo[i][j].equals("0") ||
						i == arreglo[0].length-1 && j == arreglo[i].length-1 && arreglo[i][j].equals("0") ) {
					 nroAdyacentes = 2;
					
				}		
				
				else if( (i < arreglo[0].length-1 && j ==0) && arreglo[i][j].equals("0") || (i < arreglo[0].length-1 && j == arreglo[i].length-1) && arreglo[i][j].equals("0") ) {
					
					nroAdyacentes = 3;
		
				}
					
			
				else if( (i == 0 && j < arreglo[i].length-1) && arreglo[i][j].equals("0") || (i == arreglo[0].length-1 && j < arreglo[i].length-1) && arreglo[i][j].equals("0")) {
					
					nroAdyacentes = 3;
		
				}

			}
		}
		
		return nroAdyacentes;
	}
	
	public String [][] copiaArreglo(String arregloOriginal[][]){
		String nuevoArreglo[][] = new String[arregloOriginal[0].length][arregloOriginal[0].length];
				for(int i = 0; i < arregloOriginal[0].length; i++) {
					for(int j = 0; j < arregloOriginal[i].length; j++) {
						nuevoArreglo[i][j] = arregloOriginal[i][j];
					}
				}
		return nuevoArreglo;
	}
	
	
	public void imprimeListaHijos(ArrayList<String[][]> hijosGenerados) {  // imprime de la lista de Arrays hijos
		
		System.out.println();
		for(Object[][] arreglos:  hijosGenerados){
		    for(Object[] i: arreglos) {
		    	for(Object j: i) {
		    		 System.out.print(j + " ");
		    		 }
		    	System.out.println();
		    	}
		    System.out.println();
		    }
	}
	
	public ArrayList<String[][]> generaHijos(String[][] padre ) {  //El padre se manda a close y los hijos generados a open
		
		int numeroHijos = obtenieneNumeroDeAyacentes(padre);
		System.out.println(numeroHijos);
		int cordenadaPosicion[] = getPosicionCasillaVacia(padre);
		
		int arregloTemp0[] = {0, 0}; // origen
		int arregloTemp1[] = {0, padre.length-1}; // sup-der
		
		
		int arregloTemp2[] = {padre[0].length-1, 0}; // inf-izq
		int arregloTemp3[] = {padre[0].length-1, padre[0].length-1}; //inf-der
		
		ArrayList<String[][]> hijosGenerados = new ArrayList<String[][]>();
		
		switch(numeroHijos) {
			case 2:{  // esquinas
				
				if(Arrays.equals(cordenadaPosicion, arregloTemp0) ) {  // sup-isq
					
					String hijo0[][] = copiaArreglo(padre);  // hijo 1
					String hijo1[][] = copiaArreglo(padre);	// hijo 2
					
					for(int i =0; i< padre[0].length; i++) {
						
						for(int j = 0; j < padre[i].length; j++) {
							
							if( hijo0[i][j].equals("0") && (i == 0 && j == 0) ) {
								String temp1 = hijo0[i+1][j];
								String casillaVacia = hijo0[i][j];
								
								hijo0[i+1][j] = casillaVacia;
								hijo0[i][j] = temp1;
								
								hijosGenerados.add( hijo0 );
								
							}
							if(hijo1[i][j].equals("0") && (i == 0 && j == 0)) {
								String casillaVacia = hijo1[i][j];
								hijo1[i][j] = hijo1[i][j+1];
								hijo1[i][j+1] = casillaVacia;
								
								hijosGenerados.add(hijo1);
							}
						}
					}
										

				}
				
				if(Arrays.equals(cordenadaPosicion, arregloTemp1) ) {  // sup-der
					
					String hijo0[][] = copiaArreglo(padre);  // hijo 1
					String hijo1[][] = copiaArreglo(padre);	// hijo 2
					
					for(int i =0; i< padre[0].length; i++) {
						
						for(int j = 0; j < padre[i].length; j++) {
							
							if( hijo0[i][j].equals("0") && (i == 0 && j == hijo0[0].length-1) ) {
									
									String casillaVacia = hijo0[i][j];
									hijo0[i][j]= hijo0[i+1][j];
									hijo0[i+1][j] = casillaVacia;
									
									hijosGenerados.add(hijo0);
									
								}
							if( hijo1[i][j].equals("0") && (i == 0 && j == hijo1[0].length-1) ) {
						
									String casillaVacia = hijo1[i][j];
									hijo1[i][j] = hijo1[i][j-1];
									hijo1[i][j-1] = casillaVacia;
									
									hijosGenerados.add(hijo1);
								}
						}
					}
										
				}

				if(Arrays.equals(cordenadaPosicion, arregloTemp2) ) {  // inf-izq
					
					String hijo0[][] = copiaArreglo(padre);  // hijo 1
					String hijo1[][] = copiaArreglo(padre);	// hijo 2
					
					for(int i =0; i< padre[0].length; i++) {
						
						for(int j = 0; j < padre[i].length; j++) {
							
							if( hijo0[i][j].equals("0") && (i == hijo0[0].length-1 && j == 0 ) ) {
									
									String casillaVacia = hijo0[i][j];
									hijo0[i][j]= hijo0[i-1][j];
									hijo0[i-1][j] = casillaVacia;
									
									hijosGenerados.add(hijo0);
									
								}
							if( hijo1[i][j].equals("0") && (i == hijo1[0].length-1 && j == 0 ) ) {
									
									String casillaVacia = hijo1[i][j];
									hijo1[i][j] = hijo1[i][j+1];
									hijo1[i][j+1] = casillaVacia;
									
									hijosGenerados.add(hijo1);
								
								}
						}
					}
										
				}

				if(Arrays.equals(cordenadaPosicion, arregloTemp3) ) {  // inf-der
					
					String hijo0[][] = copiaArreglo(padre);  // hijo 1
					String hijo1[][] = copiaArreglo(padre);	// hijo 2
					
					for(int i =0; i< padre[0].length; i++) {
						
						for(int j = 0; j < padre[i].length; j++) {
							
							if( hijo0[i][j].equals("0") && (i == hijo0[0].length-1 && j == hijo0[0].length-1 ) ) {
									
									String casillaVacia = hijo0[i][j];
									hijo0[i][j]= hijo0[i-1][j];
									hijo0[i-1][j] = casillaVacia;
									
									hijosGenerados.add(hijo0);
									
								}
							if( hijo1[i][j].equals("0") && (i == hijo1[0].length-1 && j == hijo0[0].length-1 ) ) {
									
									String casillaVacia = hijo1[i][j];
									hijo1[i][j] = hijo1[i][j-1];
									hijo1[i][j-1] = casillaVacia;
									
									hijosGenerados.add(hijo1);
								
								}
						}
					}
										
				}
				
				break;
			}
			case 3:{ // casillas en las orillas
		
				if(cordenadaPosicion[0] < padre[0].length && cordenadaPosicion[1] == 0 ) {  //lateral izq
					
					String hijo0[][] = copiaArreglo(padre);  // hijo 1
					String hijo1[][] = copiaArreglo(padre);	// hijo 2
					String hijo2[][] = copiaArreglo(padre);	// hijo 3
					
					for(int i = 0; i < padre[0].length; i++) {
						
						for(int j = 0; j < padre[i].length; j++) {
							
							if(padre[i][j].equals("0")) {
								
								String casillaVacia = hijo0[i][j];
								
								hijo0[i][j] = hijo0[i-1][j];
								hijo0[i-1][j] = casillaVacia;
								
								hijo1[i][j] = hijo1[i+1][j];
								hijo1[i+1][j] = casillaVacia;
								
								hijo2[i][j] = hijo2[i][j+1];
								hijo2[i][j+1] = casillaVacia;
								
								hijosGenerados.add(hijo0);
								hijosGenerados.add(hijo1);
								hijosGenerados.add(hijo2);
		
							}
						}
					}
					
				}

				if(cordenadaPosicion[0] < padre[0].length && cordenadaPosicion[1] ==  padre[0].length-1 ) {  //lateral derecha

					String hijo0[][] = copiaArreglo(padre);  // hijo 1
					String hijo1[][] = copiaArreglo(padre);	// hijo 2
					String hijo2[][] = copiaArreglo(padre);	// hijo 3
					
					for(int i = 0; i < padre[0].length; i++) {
						
						for(int j = 0; j < padre[i].length; j++) {
							
							if(padre[i][j].equals("0")) {
								
								String casillaVacia = hijo0[i][j];
								
								hijo0[i][j] = hijo0[i-1][j];
								hijo0[i-1][j] = casillaVacia;
								
								hijo1[i][j] = hijo1[i+1][j];
								hijo1[i+1][j] = casillaVacia;
								
								hijo2[i][j] = hijo2[i][j-1];
								hijo2[i][j-1] = casillaVacia;
								
								hijosGenerados.add(hijo0);
								hijosGenerados.add(hijo1);
								hijosGenerados.add(hijo2);
								
							}
						}
					}
					
				}

				if(cordenadaPosicion[0] == 0 && cordenadaPosicion[1] <  padre[0].length ) {  // superior

					String hijo0[][] = copiaArreglo(padre);  // hijo 1
					String hijo1[][] = copiaArreglo(padre);	// hijo 2
					String hijo2[][] = copiaArreglo(padre);	// hijo 3
					
					for(int i = 0; i < padre[0].length; i++) {
						
						for(int j = 0; j < padre[i].length; j++) {
							
							if(padre[i][j].equals("0")) {
								
								String casillaVacia = hijo0[i][j];
								
								hijo0[i][j] = hijo0[i][j-1];
								hijo0[j-1][j] = casillaVacia;
								
								hijo1[i][j] = hijo1[i][j+1];
								hijo1[i][j+1] = casillaVacia;
								
								hijo2[i][j] = hijo2[i+1][j];
								hijo2[i+1][j] = casillaVacia;
								
								hijosGenerados.add(hijo0);
								hijosGenerados.add(hijo1);
								hijosGenerados.add(hijo2);
								
							}
						}
					}
					
				}

				if(cordenadaPosicion[0] == padre[0].length-1 && cordenadaPosicion[1] <  padre[0].length ) {  // inferior

					String hijo0[][] = copiaArreglo(padre);  // hijo 1
					String hijo1[][] = copiaArreglo(padre);	// hijo 2
					String hijo2[][] = copiaArreglo(padre);	// hijo 3
					
					for(int i = 0; i < padre[0].length; i++) {
						
						for(int j = 0; j < padre[i].length; j++) {
							
							if(padre[i][j].equals("0")) {
								
								String casillaVacia = hijo0[i][j];
								
								hijo0[i][j] = hijo0[i][j-1];
								hijo0[i][j-1] = casillaVacia;
								
								hijo1[i][j] = hijo1[i][j+1];
								hijo1[i][j+1] = casillaVacia;
								
								hijo2[i][j] = hijo2[i-1][j];
								hijo2[i-1][j] = casillaVacia;
								
								hijosGenerados.add(hijo0);
								hijosGenerados.add(hijo1);
								hijosGenerados.add(hijo2);
								
							}
						}
					}
				
				}
				
				break;
			}
			case 4:{ // casillas interiores
				
				if(cordenadaPosicion[0] > 0 && cordenadaPosicion[1] >0) {

					String hijo0[][] = copiaArreglo(padre);  // hijo 1
					String hijo1[][] = copiaArreglo(padre);	// hijo 2
					String hijo2[][] = copiaArreglo(padre);	// hijo 3
					String hijo3[][] = copiaArreglo(padre);	// hijo 4
					
					for(int i = 0; i < padre[0].length; i++) {
						
						for(int j = 0; j < padre[i].length; j++) {
							
							if(padre[i][j].equals("0")) {
								
								String casillaVacia = hijo0[i][j];
								
								hijo0[i][j] = hijo0[i-1][j];
								hijo0[i-1][j] = casillaVacia;
								
								hijo1[i][j] = hijo1[i+1][j];
								hijo1[i+1][j] = casillaVacia;
								
								hijo2[i][j] = hijo2[i][j-1];
								hijo2[i][j-1] = casillaVacia;
								
								hijo3[i][j] = hijo2[i][j+1];
								hijo3[i][j+1] = casillaVacia;
								
								hijosGenerados.add(hijo0);
								hijosGenerados.add(hijo1);
								hijosGenerados.add(hijo2);
								hijosGenerados.add(hijo3);
								
							}
						}
					}
					
				}
				
				break;
			}
		}
		
		return hijosGenerados;	
	}
	
	
	
	public void solucionH_G() {
		
	}
	
	public int[] obtieneValorCasillaManhatan(int valorBuscado, String[][] matrizObjetivo) { //Obtiene la coordenada de una casilla en especifico
		int coordenadas[] = null;
 		for(int i = 0; i <matrizObjetivo[0].length; i++) {
			for(int j = 0; j < matrizObjetivo[i].length; j++) {
				if( valorBuscado == Integer.parseInt(matrizObjetivo[i][j]) ) {
					coordenadas = new int[]{i,j}; 
				}
			}
		}
		return coordenadas;
	}
	
	public int heuristicaManhatan(String estadoActual[][], String [][] solucion) { // Suma las distancias minimas de cada casilla del estado actual comparandola con la matriz solución
		int sumaDistancia = 0;
		
		for(int i = 0; i < estadoActual[0].length; i++ ) {
			
			for(int j = 0; j < estadoActual[i].length; j++ ) {
				
				int valorCasillaEstadoActual = Integer.parseInt( estadoActual[i][j] );
				
				
					if( valorCasillaEstadoActual != 0 ) {
						
						int[] valorCasillaSolucion = obtieneValorCasillaManhatan(valorCasillaEstadoActual, solucion);
						sumaDistancia += Math.abs( j -  valorCasillaSolucion[1])
	                            	    + Math.abs( i - valorCasillaSolucion[0]);
						//System.out.print(sumaDistancia + " ");
					}
			}
		}
		return sumaDistancia;
	}
	
	public int heuristicaCasillasFueraDeLugar(String estadoActual[][], String [][] solucion) {  // Heuristica casillas fuera de lugar
		int sumaCasillasFueraDeLugar = 0;
		
		for(int i = 0; i < estadoActual[0].length; i++) {
		
			for(int j = 0; j < estadoActual[i].length; j++) {
				
				if( !estadoActual[i][j].equals("0") && !estadoActual[i][j].equals(solucion[i][j]) ) {
					sumaCasillasFueraDeLugar += 1;
				}
			}
		}
		return sumaCasillasFueraDeLugar;
	}
	
	public void imprimeTablero(String [][]tableroActual) {

		for (int i=0; i < tableroActual[0].length; i++) {
        	
            for (int j=0; j < tableroActual[i].length; j++) {
				   System.out.print(tableroActual[i][j]+" ");
            }

			System.out.print("\n");
         }
	}
	
	public static void main(String[] args) throws IOException {
		
		//Tablero t = new Tablero();
		//t.imprimeTablero();
		//t.getPosicionCasillaVacia(t.estado);
		//System.out.println(t.obtenieneNumeroDeAyacentes(t.estado));
		//t.generaHijos(t.estado);
		
		String [][] soluc = {{"1","2","3"},{"4","5","6"},{"7","8","0"}};
		String [][] garka = {{"1","2","3"},{"4","5","6"},{"8","7","0"}};
		Tablero tab = new Tablero();
		System.out.print(tab.heuristicaManhatan(garka, soluc));
		
	}
	
}

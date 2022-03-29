class MatrizColores{

	private String[][] matriz;
	private int filacambiada,columnacambiada,adyacentes,tamano;
	
	public MatrizColores(int tam){
		matriz = new String[tam][tam];
		adyacentes = 0;
		tamano = tam;
	}
	
	public void setCeldaMatriz(int f, int c, String color){
		matriz[f][c] = color;	
	}
	
	public String getCeldaMatriz(int f, int c){
		return matriz[f][c];
	}
	
	public void CeldaCambiada(int f, int c){
		filacambiada = f;
		columnacambiada = c;
	}
	
	public int getFilaCambiada(){
		return filacambiada;
	}
	
	public int getColumnaCambiada(){
		return columnacambiada;
	}
	
	public void calcularAdyacentes(){
		for(int i=0; i<tamano; i++){
			for(int j=0; j<tamano; j++){
				try{
					if( matriz[i][j].equals(matriz[i][j-1]) ){ // revisa a la izquierda
						adyacentes++;
					}
				}catch(Exception e1){}
				
				try{
					if( matriz[i][j].equals(matriz[i][j+1]) ){ // revisa a la derecha
						adyacentes++;
					}
				}catch(Exception e1){}
				
				try{
					if( matriz[i][j].equals(matriz[i-1][j]) ){ // revisa arriba
						adyacentes++;
					}
				}catch(Exception e1){}
				
				try{
					if( matriz[i][j].equals(matriz[i+1][j]) ){ // revisa abajo
						adyacentes++;
					}
				}catch(Exception e1){}
			}			
		}
	}
	
	public int getAdyacentes(){
		return adyacentes;
	}

}
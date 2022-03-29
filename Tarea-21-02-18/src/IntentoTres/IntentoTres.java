package IntentoTres;

public class IntentoTres implements Lock{
	int bandera = 0;
	public void requestCR( int i ){
		while( bandera == 1-i );
	}
	public void releaseCR( int i ){
		bandera = 1-i;
	}

}

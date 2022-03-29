package IntentoCuatro;

public class IntentoCuatro implements Lock {
    volatile boolean pedirCR[] = {false, false};
    volatile int bandera = 1;
    
    public void requestCR(int i) {     
    	int j = 1 - i;
        pedirCR[i] = true;
        
        while (pedirCR[j]) {
            if (bandera == j) {
                pedirCR[i] = false;
                while (bandera == j);// tiempo de espera
                pedirCR[i] = true;
            }
        }
    }
    
    public void releaseCR(int i) { // salir de regionCR.
        bandera = 1 - i;
        pedirCR[i] = false;
    }
}
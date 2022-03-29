package IntentoDos;

class IntentoDos implements Lock {
	
    boolean pedirCR[] = {false, false};
    
    public void requestCR(int i) { // entry protocol @\label{want}@
        pedirCR[i] = true;   //declare intent @\label{busy}@
        while (pedirCR[1 - i]) ; // busy wait
    }
    public void releaseCR(int i) {
        pedirCR[i] = false;
    }
}
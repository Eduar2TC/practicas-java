package Intento;
import java.util.Random;

public class MyThread extends Thread {
    int myId;
    Lock lock;
    Random r = new Random();
    public MyThread(int id, Lock lock) {
        myId = id;
        this.lock = lock;
    }
    void nonCriticalRegion() throws InterruptedException {
        System.out.println("\t" + myId + " no esta en la RC " + " abrePuerta : " + lock.getPuerta() );
        sleep(r.nextInt(2000));
    }
    void CriticalRegion() throws InterruptedException {
        System.out.println("\t" + myId + " esta en la RC " + " abrePuerta : " + lock.getPuerta());
        // codigo de region critica
        //sleep(r.nextInt(5000));  //problemas de sincronizacion
    }
    public void run() {
    	int cont  = 1;
    	

        while (true) {
        	
        	System.out.println("Iteracion : " + cont +" id : " + this.getId());
            try {
            	lock.requestCR(myId);
				this.CriticalRegion();
            	this.lock.releaseCR(myId);
				this.nonCriticalRegion();
				cont++;
				//Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
       
      
    }
    public static void main(String[] args) throws Throwable {
        MyThread t[];
        int N = Integer.parseInt("2");
        t = new MyThread[N];
        Lock lock [] = new Lock[N];
        
        for (int i = 0; i < N; i++) {
        	lock[i] = new IntentoUno();//cualquier otro algoritmo
            t[i] =new MyThread(i, lock[i]);
            t[i].start();
            t[i].join(2000);
        }

        

        
    }
}




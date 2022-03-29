package threads.ejemplo1;

public class HelloWorldThread extends Thread{
	
	public void run() {
		System.out.println("Hola soy un hilo");
	}

	public static void main(String[] args) {
		HelloWorldThread t = new HelloWorldThread();
		t.start();
	}

}

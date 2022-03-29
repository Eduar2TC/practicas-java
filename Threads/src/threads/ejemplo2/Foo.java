package threads.ejemplo2;

class Foo{
	String name;
	
	public Foo( String iniName ){
		name = iniName; 
		}
	public void setName( String newName ){
		name = newName; 
		}
	public String getName(){
		return name; 
		}
	
}

class FooBar extends Foo implements Runnable{

	public FooBar( String name ){
	super(name);  //contructor clase principal padre
	}
	
	public void run(){	//Metodo heredado y sobrecargado de Runnable
		System.out.println();
		for( int i = 0; i < 10; i++ ) {
			System.out.println( getName() + ": Hola te saludo por  ("+ i + " ) vez");	
			}
	}
	
	public static void main( String[] args ){
		
		FooBar f1 = new FooBar( "Romeo" );
		Thread t1 = new Thread( f1 );
		
		FooBar f2 = new FooBar( "Julieta" );
		Thread t2 = new Thread( f2 );
		
		t1.start();
		t2.start();
		}
	}
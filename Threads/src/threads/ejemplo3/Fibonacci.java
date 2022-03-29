package threads.ejemplo3;

public class Fibonacci extends Thread
{
int n;
int result;
public Fibonacci( int n ){ 
	this.n = n; 
	}
public void run(){
	if( n == 0 || n == 1 ) {
		result = 1;
	}
	else
	{
		Fibonacci f1 = new Fibonacci( n-1 );
		Fibonacci f2 = new Fibonacci( n-2 );
		f1.start();
		f2.start();
		System.out.println("f1 Name: "+ f1.getName()+" f1 id "+ f1.getId() + " n = " +f1.n + " f2 Name: "+ f2.getName() + " f2 id "+ f2.getId() + " n = "+f2.n);
	
	try{
		
		f1.join();
		f2.join();
		//System.out.println("f1 Name : "+ f1.getName() +" f2 Name :"+ f2.getName());
		}catch( InterruptedException e ){};
		result = f1.getResult() + f2.getResult();
		}
	}
public int getResult()
{
return result;
}
public static void main( String[] args ){
	Fibonacci f1 = new Fibonacci( Integer.parseInt( "7" ) );
	f1.start();
	try{
		f1.join();
		System.out.println("fEspera Name: "+ f1.getName()+" fEspera id "+ f1.getId() + " n = " +f1.n);
		}catch( InterruptedException e ){};
		System.out.println( "Answer is " + f1.getResult() );
	}
}
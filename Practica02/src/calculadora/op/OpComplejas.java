package calculadora.op;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class OpComplejas {
	public static float raizCudrada(float numero) {
		return (float) Math.sqrt(numero);
	}
	public static float alCuadrado(float numero) {
		return (float)Math.pow(numero, 2);
	}
	public static float aLaN(float numero, float n) {
		return (float) Math.pow(numero, n);
	}
	public static float Modulo(float numero, float n) {
		return (float) numero % n;
	}
	
	public Object CalculaDesdeCadena(String cadena) throws ScriptException {
	    String Operacion = cadena;
	    ScriptEngineManager factory = new ScriptEngineManager();
	    ScriptEngine aux = factory.getEngineByName("JavaScript");
	    // evaluate JavaScript code from String
	    Object cadenaObjeto = aux.eval(Operacion);
	    return cadenaObjeto;
	}
}

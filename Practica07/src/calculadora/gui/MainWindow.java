package calculadora.gui;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import calculadora.op.OpSimples;
import comunicacion.DatagramaCliente;


public class MainWindow {

	protected Shell var_CalculadoraBsicaBugueada;
	private Text textoPantalla;
	/*Varibles globales*/
	float primerOperador;
	float segundoOperador;
	float resultado;
	String Operacion;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		var_CalculadoraBsicaBugueada.open();
		var_CalculadoraBsicaBugueada.layout();
		while (!var_CalculadoraBsicaBugueada.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		var_CalculadoraBsicaBugueada = new Shell();
		var_CalculadoraBsicaBugueada.setMinimumSize(new Point(296, 284));
		var_CalculadoraBsicaBugueada.setBackground(SWTResourceManager.getColor(255, 255, 255));
		var_CalculadoraBsicaBugueada.setSize(296, 284);
		var_CalculadoraBsicaBugueada.setText("CalcuBug");
		var_CalculadoraBsicaBugueada.setLayout(null);
		
		textoPantalla = new Text(var_CalculadoraBsicaBugueada, SWT.BORDER | SWT.RIGHT);
		textoPantalla.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		textoPantalla.setBackground(SWTResourceManager.getColor(255, 255, 255));
		textoPantalla.setText("0.00");
		textoPantalla.setToolTipText("");
		textoPantalla.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		textoPantalla.setBounds(10, 10, 262, 38);
		
		Button button_cero = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_cero.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(textoPantalla.getText().equals("0.00")) {
					textoPantalla.setText("");
				}
				String numeroIngresado = textoPantalla.getText() + button_cero.getText();
				textoPantalla.setText(numeroIngresado);
			}
		});
		button_cero.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_cero.setBounds(10, 209, 61, 25);
		button_cero.setText("0");
		
		Button button_uno = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_uno.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(textoPantalla.getText().equals("0.00")) {
					textoPantalla.setText("");
				}
				String numeroIngresado = textoPantalla.getText() + button_uno.getText();
				textoPantalla.setText(numeroIngresado);
				
			}
		});
		button_uno.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_uno.setBounds(10, 178, 61, 25);
		button_uno.setText("1");
		
		Button button_dos = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_dos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(textoPantalla.getText().equals("0.00")) {
					textoPantalla.setText("");
				}
				String numeroIngresado = textoPantalla.getText() + button_dos.getText();
				textoPantalla.setText(numeroIngresado);
			}
		});
		button_dos.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_dos.setBounds(77, 178, 61, 25);
		button_dos.setText("2");
		
		Button button_tres = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_tres.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(textoPantalla.getText().equals("0.00")) {
					textoPantalla.setText("");
				}
				String numeroIngresado = textoPantalla.getText() + button_tres.getText();
				textoPantalla.setText(numeroIngresado);
			}
		});
		button_tres.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_tres.setBounds(144, 179, 61, 25);
		button_tres.setText("3");
		
		Button button_cuatro = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_cuatro.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(textoPantalla.getText().equals("0.00")) {
					textoPantalla.setText("");
				}
				String numeroIngresado = textoPantalla.getText() + button_cuatro.getText();
				textoPantalla.setText(numeroIngresado);	
			}
		});
		button_cuatro.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_cuatro.setBounds(10, 148, 61, 25);
		button_cuatro.setText("4");
		
		Button button_cinco = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_cinco.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(textoPantalla.getText().equals("0.00")) {
					textoPantalla.setText("");
				}
				String numeroIngresado = textoPantalla.getText() + button_cinco.getText();
				textoPantalla.setText(numeroIngresado);
			}
		});
		button_cinco.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_cinco.setBounds(77, 147, 61, 25);
		button_cinco.setText("5");
		
		Button button_seis = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_seis.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(textoPantalla.getText().equals("0.00")) {
					textoPantalla.setText("");
				}
				String numeroIngresado = textoPantalla.getText() + button_seis.getText();
				textoPantalla.setText(numeroIngresado);
			}
		});
		button_seis.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_seis.setBounds(144, 147, 61, 25);
		button_seis.setText("6");
		
		Button button_siete = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_siete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(textoPantalla.getText().equals("0.00")) {
					textoPantalla.setText("");
				}
				String numeroIngresado = textoPantalla.getText() + button_siete.getText();
				textoPantalla.setText(numeroIngresado);
			}
		});
		button_siete.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_siete.setBounds(10, 117, 61, 25);
		button_siete.setText("7");
		
		Button button_ocho = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_ocho.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(textoPantalla.getText().equals("0.00")) {
					textoPantalla.setText("");
				}
				String numeroIngresado = textoPantalla.getText() + button_ocho.getText();
				textoPantalla.setText(numeroIngresado);
			}
		});
		button_ocho.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_ocho.setBounds(77, 117, 61, 25);
		button_ocho.setText("8");
		
		Button button_nueve = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_nueve.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(textoPantalla.getText().equals("0.00")) {
					textoPantalla.setText("");
				}
				String numeroIngresado = textoPantalla.getText() + button_nueve.getText();
				textoPantalla.setText(numeroIngresado);				
			}
		});
		button_nueve.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_nueve.setBounds(144, 116, 61, 25);
		button_nueve.setText("9");
		
		Button button_diez = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_diez.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				textoPantalla.setText(textoPantalla.getText() + "+");
				/*Un cagadero (corregir)*/
				int cuentaOcurrencias0=StringUtils.countMatches(textoPantalla.getText(),"+");
				/*Se verifica si la cadena despues del operador es un numero*/

				if(cuentaOcurrencias0 == 1 && StringUtils.isNumeric(StringUtils.substringBefore(textoPantalla.getText(),"+"))) {
					primerOperador = Float.parseFloat(StringUtils.substringBefore(textoPantalla.getText(),"+"));
					Operacion = "+";
				}
				else {
					    textoPantalla.setText(textoPantalla.getText());
					    Operacion = "OperacionPlus";
				}
								
			}
		});
		button_diez.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_diez.setBounds(144, 209, 61, 25);
		button_diez.setText("+");
		
		Button button_punto = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_punto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String numeroIngresado = textoPantalla.getText() + button_punto.getText();
				textoPantalla.setText(numeroIngresado);
			}
		});
		button_punto.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_punto.setBounds(77, 209, 61, 25);
		button_punto.setText(".");
		
		Button button_igual = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_igual.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String resultadoTotal;
				OpSimples operacionesSimples = new OpSimples();
				switch(Operacion) {
					case "+": {	
						segundoOperador = Float.parseFloat(StringUtils.substringAfter(textoPantalla.getText(),"+"));
						
						//AQUI HACER LAS OPERACIONES DE SERVIDOR
						//llamar a datagrama cliente
						//datagrama cliente recibe los datos
						//datagramacliente envia los datos hechos a datagramas al servidor
						//elservidor rsponde datagramacliente responde a la interfaz
						
						/*Crear un objtto Datagrama Cliente, y en datagrama cliente crear el objeto Servidor 
						  El servidor retorna el resultado a DatagramaServer y DatagramaServer a la interfáz*/
						
						try {
							DatagramaCliente dtc = new DatagramaCliente(String.format("%.0f", primerOperador), String.format("%.0f", segundoOperador), "+");
							float resultado = Float.parseFloat(dtc.opera());
							resultadoTotal = String.format("%.2f", resultado); 
							textoPantalla.setText(resultadoTotal);
							
						} catch (IOException e1) {
							e1.printStackTrace();
							}
						break;
					}
					case "-": {
						segundoOperador = Float.parseFloat(StringUtils.substringAfter(textoPantalla.getText(),"-"));
						try {
							DatagramaCliente dtc = new DatagramaCliente(String.format("%.0f", primerOperador), String.format("%.0f", segundoOperador), "-");
							float resultado = Float.parseFloat(dtc.opera());
							resultadoTotal = String.format("%.2f", resultado); 
							textoPantalla.setText(resultadoTotal);
							
						} catch (IOException e1) {
							e1.printStackTrace();
							}
						break;
					}
					case "*": {
						segundoOperador = Float.parseFloat(StringUtils.substringAfter(textoPantalla.getText(),"*"));
						try {
							DatagramaCliente dtc = new DatagramaCliente(String.format("%.0f", primerOperador), String.format("%.0f", segundoOperador), "*");
							float resultado = Float.parseFloat(dtc.opera());
							resultadoTotal = String.format("%.2f", resultado); 
							textoPantalla.setText(resultadoTotal);
							
						} catch (IOException e1) {
							e1.printStackTrace();
							}
						break;
					}
					case "/": {
						segundoOperador = Float.parseFloat(StringUtils.substringAfter(textoPantalla.getText(),"/"));
						try {
							DatagramaCliente dtc = new DatagramaCliente(String.format("%.0f", primerOperador), String.format("%.0f", segundoOperador), "/");
							float resultado = Float.parseFloat(dtc.opera());
							resultadoTotal = String.format("%.2f", resultado); 
							textoPantalla.setText(resultadoTotal);
							
						} catch (IOException e1) {
							e1.printStackTrace();
							}
						break;
					}
					case "mod":{
							segundoOperador = Float.parseFloat(StringUtils.substringAfter(textoPantalla.getText(),"%"));
							try {
								DatagramaCliente dtc = new DatagramaCliente(String.format("%.0f", primerOperador), String.format("%.0f", segundoOperador), "%");
								float resultado = Float.parseFloat(dtc.opera());
								resultadoTotal = String.format("%.2f", resultado); 
								textoPantalla.setText(resultadoTotal);
								
							} catch (IOException e1) {
								e1.printStackTrace();
								}
							break;
						}
					case "x^n": {
						segundoOperador = Float.parseFloat(StringUtils.substringAfter(textoPantalla.getText(),"^"));
						try {
							DatagramaCliente dtc = new DatagramaCliente(String.format("%.0f", primerOperador), String.format("%.0f", segundoOperador), "^");
							float resultado = Float.parseFloat(dtc.opera());
							resultadoTotal = String.format("%.2f", resultado); 
							textoPantalla.setText(resultadoTotal);
							
						} catch (IOException e1) {
							e1.printStackTrace();
							}
						break;
					}
					case "x^2":{
						
						try {
							DatagramaCliente dtc = new DatagramaCliente(String.format("%.0f", primerOperador), String.format("%.0f", segundoOperador), "x^2");
							float resultado = Float.parseFloat(dtc.opera());
							resultadoTotal = String.format("%.2f", resultado); 
							textoPantalla.setText(resultadoTotal);
							
						} catch (IOException e1) {
							e1.printStackTrace();
							}
						break;
					}
					case "sqrt":{
						primerOperador = Float.parseFloat(textoPantalla.getText().replace("sqrt(", ""));
						
						try {
							DatagramaCliente dtc = new DatagramaCliente(String.format("%.1f", primerOperador), String.format("%.1f", segundoOperador), "sqrt");
							float resultado = Float.parseFloat(dtc.opera());
							resultadoTotal = String.format("%.2f", resultado); 
							textoPantalla.setText(resultadoTotal);
							
						} catch (IOException e1) {
							e1.printStackTrace();
							}
						break;
					
					}
					case "OperacionPlus":{
						//primerOperador = Float.parseFloat(textoPantalla.getText());
						DatagramaCliente dtc = new DatagramaCliente(textoPantalla.getText(), Float.toString(segundoOperador),"default"); //(str[2] ==f)
						try {
							resultado = Float.parseFloat(dtc.opera());
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						resultadoTotal = String.format("%.2f", resultado); 
						textoPantalla.setText(resultadoTotal);
						
						break;
					}
					default:{
						textoPantalla.setText("Error");
						break;
					}
				}
							
			}
		});
		button_igual.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_igual.setBounds(211, 209, 61, 25);
		button_igual.setText("=");
		
		Button button_menos = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_menos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				textoPantalla.setText(textoPantalla.getText() + "-");
				/*Un cagadero (corregir)*/
				int cuentaOcurrencias0=StringUtils.countMatches(textoPantalla.getText(),"-");
				/*Se verifica si la cadena despues del operador es un numero*/
				if(cuentaOcurrencias0 == 1 && StringUtils.isNumeric(StringUtils.substringBefore(textoPantalla.getText(),"-"))) {
					primerOperador = Float.parseFloat(StringUtils.substringBefore(textoPantalla.getText(),"-"));
					Operacion = "-";
				}
				else {
				    textoPantalla.setText(textoPantalla.getText());
				    Operacion = "OperacionPlus";
				}			
			
			}
		});
		button_menos.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_menos.setBounds(211, 178, 61, 25);
		button_menos.setText("-");
		
		Button button_multi = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_multi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
						
				textoPantalla.setText(textoPantalla.getText() + "*");
				/*Un cagadero (corregir)*/
				int cuentaOcurrencias0=StringUtils.countMatches(textoPantalla.getText(),"*");
				/*Se verifica si la cadena de numeros despues del operador es un numero*/
				if(cuentaOcurrencias0 == 1 && StringUtils.isNumeric(StringUtils.substringBefore(textoPantalla.getText(),"*"))) {
					primerOperador = Float.parseFloat(StringUtils.substringBefore(textoPantalla.getText(),"*"));
					Operacion = "*";
				}
				else {
				    textoPantalla.setText(textoPantalla.getText());
				    Operacion = "OperacionPlus";
				}	
			
			}
		});
		button_multi.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_multi.setBounds(211, 148, 61, 25);
		button_multi.setText("x");
		
		Button button_div = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_div.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				textoPantalla.setText(textoPantalla.getText() + "/");
				/*Un cagadero (corregir)*/
				int cuentaOcurrencias0=StringUtils.countMatches(textoPantalla.getText(),"/");
				/*Se verifica si la cadena despues del operador es un numero*/
				if(cuentaOcurrencias0 == 1 && StringUtils.isNumeric(StringUtils.substringBefore(textoPantalla.getText(),"/"))) {
					primerOperador = Float.parseFloat(StringUtils.substringBefore(textoPantalla.getText(),"/"));
					Operacion = "/";
				}
				else {
				    textoPantalla.setText(textoPantalla.getText());
				    Operacion = "OperacionPlus";
				}	
			}
		});
		button_div.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_div.setBounds(211, 117, 61, 25);
		button_div.setText("/");
		
		Button button_mod = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_mod.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			
				textoPantalla.setText(textoPantalla.getText() + "%");
				primerOperador = Float.parseFloat(StringUtils.substringBefore(textoPantalla.getText(),"%"));
				
				Operacion = "mod";				
			
			}
		});
		button_mod.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_mod.setBounds(211, 54, 61, 25);
		button_mod.setText("mod");
		
		Button button_xAlaN = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_xAlaN.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				textoPantalla.setText(textoPantalla.getText() + " ^ ");
				primerOperador = Float.parseFloat(StringUtils.substringBefore(textoPantalla.getText()," ^ "));
				Operacion = "x^n";
			
			}
		});
		button_xAlaN.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_xAlaN.setBounds(144, 54, 61, 25);
		button_xAlaN.setText("x^n");
		
		Button button_xAlCuadrado = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_xAlCuadrado.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textoPantalla.setText(textoPantalla.getText() + "^2");
				primerOperador = Float.parseFloat(textoPantalla.getText().replace("^2", ""));
				Operacion = "x^2";
			}
		});
		button_xAlCuadrado.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_xAlCuadrado.setBounds(77, 54, 61, 25);
		button_xAlCuadrado.setText("x^2");
		
		Button button_raizCuadrada = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_raizCuadrada.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textoPantalla.setText("sqrt( ");
				Operacion = "sqrt";
				
			
			}
		});
		button_raizCuadrada.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_raizCuadrada.setBounds(10, 54, 61, 25);
		button_raizCuadrada.setText("sqrt(x)");
		
		Button button_HacerCero = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_HacerCero.setForeground(SWTResourceManager.getColor(0, 0, 0));
		button_HacerCero.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textoPantalla.setText("0.00");  ///Buscar la manera de crear un alias "0" para ""
			}
		});
		button_HacerCero.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_HacerCero.setBounds(10, 85, 61, 25);
		button_HacerCero.setText("CE");
		
		Button button_Atras = new Button(var_CalculadoraBsicaBugueada, SWT.NONE);
		button_Atras.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		button_Atras.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String Retroceso = null;
					if(textoPantalla.getText().length() > 1) {
						StringBuilder cadenaBuilder = new StringBuilder(textoPantalla.getText());
						cadenaBuilder.deleteCharAt(textoPantalla.getText().length() - 1);
						Retroceso = cadenaBuilder.toString();
						textoPantalla.setText(Retroceso);
					}
					else {
						if(textoPantalla.getText().length() == 1 || textoPantalla.getText() == "Error"){
							textoPantalla.setText("0.00");
						}
					}
			}
		});
		button_Atras.setBounds(77, 85, 61, 25);
		button_Atras.setText("Back");

	}
}

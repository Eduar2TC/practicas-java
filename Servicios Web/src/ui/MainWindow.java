package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import productos.Productos;

public class MainWindow {

	private JFrame ventanaPrincipal;
	private JTextField cajaUsuario;
	private JPasswordField cajaPassword;
	private JTextField cajaCategoriaProductos;
	private JTextField cajaClaveProducto;
	
	private String resultadoStringXML;  //Almacena la cadena del String XML para poder guardarlo
	private String operacion;	//Guardar la operacion actual obtener lista productos ó obtener detalle producto
	
	public void cambiaIcono() {
		java.awt.Image icono = new ImageIcon(this.getClass().getResource("../resources/productos_logo16x16.png")).getImage();
		this.ventanaPrincipal.setIconImage(icono);
	}
	
	public void addlogoEmpresa() {
		
	}
	
	public String getXMLrespuesta() {
		return resultadoStringXML;
	}
	
	public void setXMLrespuesta(String nuevaRespuesta) {
		resultadoStringXML = nuevaRespuesta;
	}
	
	public void guardarXML(int opcionGuardado) { //1) lista de productos, 2) detalles de producto
		/*1) verificar bandera
			--Si la opcionGuardado es lista ó detalles de un producto
				--llamar a la funcion obtenerXML correspondiente
				--almacenar el resultado xml en una variable String
				--convertir el resultado a xml y guardarlo a una ruta
		*/	
			//creacion del objeto para poder traer los resultados
			Productos prods = new Productos();
			if(opcionGuardado == 1) {
				//almacenamos los resultados
				this.resultadoStringXML = prods.getXmlProductos(cajaCategoriaProductos.getText());
			}
			else if(opcionGuardado == 2) {
				this.resultadoStringXML = prods.getXmlDetalleProducto(cajaClaveProducto.getText());
			}
    		//Obtiene y almacena el String XML para ser manipulado y guardado como un xml
    		String xmlStr = this.getXMLrespuesta();
    		JFrame parentFrame = new JFrame();
    		JFileChooser fileChooser = new JFileChooser();
    		fileChooser.setDialogTitle("Guardar archivo XML:");   
    		int userSelection = fileChooser.showSaveDialog(parentFrame);
    		if (userSelection == JFileChooser.APPROVE_OPTION) {
    			File fileToSave = fileChooser.getSelectedFile();
    			String archivoName = fileToSave.getAbsolutePath();
    			if(!archivoName.endsWith(".xml")) {
    				fileToSave = new File(archivoName + ".xml");
    				BufferedWriter writer;
						try {
							writer = new BufferedWriter(new FileWriter(fileToSave));
		    				writer.write(xmlStr);
		    				writer.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    				}
    			}
	}
	//Obtiene operacion que se esta realizando
	public String getOperacion() {
		return this.operacion;
	}
	public void setOperacion(String nuevaOperacion) {
		this.operacion = nuevaOperacion;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.ventanaPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {  //Creacion de los elementos de la interfáz grágfica
		
		ventanaPrincipal = new JFrame();
		ventanaPrincipal.setResizable(false);
		ventanaPrincipal.setTitle("Productos");
		ventanaPrincipal.setBounds(100, 100, 481, 432);
		
		//Agregando icono a la ventana principal
		this.cambiaIcono();
		
		ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaPrincipal.getContentPane().setLayout(null);
	
		//Panel de logo
		JPanel panel = new JPanel();
	    BufferedImage image;  //creacionde la variable imagen
	    
		panel.setBounds(93, 11, 253, 110);  //posicion del panel contenedor de la imagen
		ventanaPrincipal.getContentPane().add(panel);
	
		try {
			image = ImageIO.read(new File("C:\\Users\\eduar\\eclipse-workspace\\Servicios Web\\src\\resources\\products_market_logo.png"));
		    JLabel label = new JLabel(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(panel.getWidth(), panel.getHeight(), java.awt.Image.SCALE_DEFAULT)));
		    panel.add(label);
		    
		    Label labelUsuario = new Label("Usuario:");
		    labelUsuario.setBounds(35, 146, 51, 22);
		    ventanaPrincipal.getContentPane().add(labelUsuario);
		    
		    Label labelContrasenia = new Label("Contrase\u00F1a:");
		    labelContrasenia.setBounds(15, 174, 71, 22);
		    ventanaPrincipal.getContentPane().add(labelContrasenia);
		    
		    cajaUsuario = new JTextField();
		    cajaUsuario.setBounds(93, 146, 86, 20);
		    ventanaPrincipal.getContentPane().add(cajaUsuario);
		    cajaUsuario.setColumns(10);
		    
		    cajaPassword = new JPasswordField();
		    cajaPassword.setBounds(93, 174, 86, 20);
		    ventanaPrincipal.getContentPane().add(cajaPassword);
		    
		    JLabel labelCategoriaProductos = new JLabel("Categoria:");
		    labelCategoriaProductos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		    labelCategoriaProductos.setBounds(31, 229, 62, 14);
		    ventanaPrincipal.getContentPane().add(labelCategoriaProductos);
		    
		    //Área de texto
		    cajaCategoriaProductos = new JTextField();
		    cajaCategoriaProductos.setBounds(90, 226, 89, 20);
		    ventanaPrincipal.getContentPane().add(cajaCategoriaProductos);
		    cajaCategoriaProductos.setColumns(10);
		    
		    TextArea textArea = new TextArea();
		    textArea.setBounds(202, 216, 253, 156);
		    ventanaPrincipal.getContentPane().add(textArea);
		    
		    //Boton guardar XML
		    JButton btnGuardarXml = new JButton("Guardar XML");
		    btnGuardarXml.setBackground(new Color(204, 204, 255));
		    btnGuardarXml.addActionListener(new ActionListener() {
		    	
		    	public void actionPerformed(ActionEvent arg0) {
		    		if( MainWindow.this.getOperacion() == "obtenerListaDeProductos" ) {
		    			MainWindow.this.guardarXML(1);
		    		}
		    		else if(MainWindow.this.getOperacion() == "obtenerDetalleDeProducto") {
		    			MainWindow.this.guardarXML(2);
		    		}
		    		else {
		    			
		    			System.out.println("caca" + MainWindow.this.getOperacion());
		    		}
		   
		    	}
		    });
		    btnGuardarXml.setEnabled(false);
		    btnGuardarXml.setBounds(341, 378, 114, 23);
		    ventanaPrincipal.getContentPane().add(btnGuardarXml);
		    
		    //Boton Validar XML
		    JButton btnValidarXML = new JButton("Validar XML");
		    btnValidarXML.setEnabled(false);
		    btnValidarXML.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		/*Proceso para llegar hasta Guardar XML
		    		 * 1) Inicializar los servicios
		    		 * 2) Validar el archivo generado
		    		 * 		Si es correcto: mostrar un popup del mensaje de exito + codigo 
		    		 * 		y los resultados en el textArea activar el boton guardar
		    		 * 		Si no mostrar un pop up de error + codigo de mensaje de error*/
		    		if(MainWindow.this.getOperacion() == "obtenerListaDeProductos") {
		    			Productos prod = new Productos();
		    			String resultadoRespuesta = prod.validaXML(1);
		    			if(resultadoRespuesta.equals("201 exito")) {
		    				//Mensahe de exito
		    			    JFrame parent = new JFrame();
		    			    JOptionPane.showMessageDialog(parent, resultadoRespuesta);
				    		//Activar Button guardar XML
				    		btnGuardarXml.setEnabled(true);
		    			}
		    			else {
		    			    String msg = "<html>"+resultadoRespuesta;

		    			    JOptionPane optionPane = new NarrowOptionPane();
		    			    optionPane.setMessage(msg);
		    			    optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		    			    JDialog dialog = optionPane.createDialog(null, "No se puede guardar el XML");
		    			    dialog.setVisible(true);
		    			    btnGuardarXml.setEnabled(false);
		    			}
		    			
		    		}
		    		else if(MainWindow.this.getOperacion() == "obtenerDetalleDeProducto") {
		    			Productos prod = new Productos();
		    			String resultadoRespuesta = prod.validaXML(2);
		    			System.out.println(resultadoRespuesta);
		    			if(resultadoRespuesta.equals("201 exito")) {
		    				//Mensahe de exito
		    			    JFrame parent = new JFrame();
		    			    JOptionPane.showMessageDialog(parent, resultadoRespuesta);
				    		//Activar Button guardar XML
				    		btnGuardarXml.setEnabled(true);
		    			}
		    			else {
		    			    String msg = "<html>"+resultadoRespuesta;

		    			    JOptionPane optionPane = new NarrowOptionPane();
		    			    optionPane.setMessage(msg);
		    			    optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		    			    JDialog dialog = optionPane.createDialog(null, "No se puede guardar el XML");
		    			    dialog.setVisible(true);
		    			    btnGuardarXml.setEnabled(false);
		    			}
		    		}
		    		
		    	}
		    });
		    btnValidarXML.setBackground(new Color(204, 204, 255));
		    btnValidarXML.setBounds(202, 378, 104, 23);
		    ventanaPrincipal.getContentPane().add(btnValidarXML);

		    //Boton Consultar Lista de productos
		    JButton btnConsultarProductos = new JButton("Consultar");
		    btnConsultarProductos.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		//Obtener las cadenas de textp de las cajas de Usuario y Contraseña
		    		String usuario = cajaUsuario.getText();
		    		String password = String.valueOf(cajaPassword.getPassword());
		    		String categoria = cajaCategoriaProductos.getText();
		    		//String cajaTexto = textArea.getText();
		    		//Creacion de instancia de Productos
		    		Productos prods = new Productos(usuario, password, categoria);
		    		
		    		textArea.setText(prods.getListaProductos(categoria));
		    		//Activar el boton validar XML
		    		btnValidarXML.setEnabled(true);   		
		    		//Cambiar la operacion que se esta realizando ahora "Colsulta lista de productos"
		    		MainWindow.this.setOperacion("obtenerListaDeProductos");
		    	}
		    });
		    btnConsultarProductos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		    btnConsultarProductos.setBackground(new Color(204, 204, 255));
		    btnConsultarProductos.setBounds(90, 253, 86, 23);
		    ventanaPrincipal.getContentPane().add(btnConsultarProductos);
		    
		    JLabel labelProducto = new JLabel("Clave producto:");
		    labelProducto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		    labelProducto.setBounds(15, 317, 78, 14);
		    ventanaPrincipal.getContentPane().add(labelProducto);
		    
		    cajaClaveProducto = new JTextField();
		    cajaClaveProducto.setColumns(10);
		    cajaClaveProducto.setBounds(93, 314, 86, 20);
		    ventanaPrincipal.getContentPane().add(cajaClaveProducto);
		    
		    JButton btnObtenerDetalles = new JButton("Obtener");
		    btnObtenerDetalles.setBackground(new Color(204, 204, 255));
		    btnObtenerDetalles.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    	
		    		//Obtener las cadenas de textp de las cajas de Usuario y Contraseña
		    		String usuario = cajaUsuario.getText();
		    		String password = String.valueOf(cajaPassword.getPassword());
		    		String clave =  cajaClaveProducto.getText();
		    		//String cajaTexto = textArea.getText();
		    		//Creacion de instancia de Productos
		    		Productos prods = new Productos(usuario, password, "");
		    		textArea.setText(prods.getDetalleDeUnProducto(clave));
		    		//Activar el boton validar XML
		    		btnValidarXML.setEnabled(true);   	
		    		//Cambiar la operacion que se esta realizando ahora "Colsulta lista de productos"
		    		MainWindow.this.setOperacion("obtenerDetalleDeProducto");
		    	}
		    });
		    btnObtenerDetalles.setBounds(93, 340, 86, 23);
		    ventanaPrincipal.getContentPane().add(btnObtenerDetalles);
		    
		    JPanel panelAcceso = new JPanel();
		    panelAcceso.setBorder(new TitledBorder(null, "Acceso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		    panelAcceso.setBounds(0, 122, 197, 81);
		    ventanaPrincipal.getContentPane().add(panelAcceso);
		    
		    JPanel panelListaDeProductos = new JPanel();
		    panelListaDeProductos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Obtener Lista de productos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		    panelListaDeProductos.setBounds(0, 202, 197, 82);
		    ventanaPrincipal.getContentPane().add(panelListaDeProductos);
		    
		    JPanel panelDetallesProducto = new JPanel();
		    panelDetallesProducto.setBorder(new TitledBorder(null, "Obtener detalles de un producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		    panelDetallesProducto.setBounds(0, 286, 196, 86);
		    ventanaPrincipal.getContentPane().add(panelDetallesProducto);
		    
		    JTextPane txtpnParaPoderCon = new JTextPane();
		    txtpnParaPoderCon.setEditable(false);
		    txtpnParaPoderCon.setText("Para poder consultar la lista de productos o los detalles de un producto Toma en cuenta haber llenado los campos de usuario y contrase\u00F1a.");
		    txtpnParaPoderCon.setBounds(202, 132, 253, 81);
		    ventanaPrincipal.getContentPane().add(txtpnParaPoderCon);
		    		    
		} catch (IOException e) {
			String mensajeError = "Algo paso :v";
			 JOptionPane.showMessageDialog(new JFrame(), mensajeError, "Dialog",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	
	}
	
}
//Para poder redimensionar el mensaje de error
class NarrowOptionPane extends JOptionPane {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	NarrowOptionPane() {
	  }

	  public int getMaxCharactersPerLineCount() {
	    return 100;
	  }
	}

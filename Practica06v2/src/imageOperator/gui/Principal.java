package imageOperator.gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import operacionesImagenes.ImageOperator;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;


public class Principal extends JFrame {
	//Variables globales
	private static final String[] opciones = {"+", "-", "*","CL" };
	String itemSeleccionado;  //operacion definida en el comboBox
	public BufferedImage imagen1 = null; //imag1 a procesar
	public BufferedImage imagen2 = null; //imag2 a procesar
	
	private JPanel contentPane;
	private JComboBox comboBox;
	private JTextField caja1;
	private JTextField caja2;
	JFileChooser temp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setResizable(false);
		setTitle("Manipulacion de im\u00E1genes");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label1 = new JLabel("");
		label1.setOpaque(true);
		label1.setBackground(Color.white);
		label1.setBounds(0, 11, 300, 245);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("");
		label2.setOpaque(true);
		label2.setBackground(Color.white);
		label2.setBounds(389, 11, 300, 245);
		contentPane.add(label2);
		
		//Combobox
		comboBox = new JComboBox(opciones);
		comboBox.setSelectedItem(null);
		comboBox.setBackground(Color.white);
		comboBox.setBounds(320, 117, 50, 20);
		contentPane.add(comboBox);
		
		
		JButton btnCargar1 = new JButton("cargar");
		btnCargar1.setBackground(Color.white);
		btnCargar1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser selecciona = new JFileChooser();
				
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("imagenes :v","JPG","PNG", "GIF");
				selecciona.addChoosableFileFilter(filtro);			
				
				selecciona.showOpenDialog(null);				
				File archivo = selecciona.getSelectedFile();
				String nombreArchivo = archivo.getAbsolutePath();
				
				//ImageIcon icon = new ImageIcon(nombreArchivo);  //MUESTRA LA IMAGEN SIN ESCALA
				
				ImageIcon imageIcon = new ImageIcon(new ImageIcon(nombreArchivo).getImage().getScaledInstance(label1.getWidth(), label1.getHeight(), Image.SCALE_DEFAULT));
								
				label1.setIcon(imageIcon);
				
		
				try {
					imagen1 = ImageIO.read(new File(nombreArchivo)); // Asignacion de la ruta a la variable imagen1 original para ser operada
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
					
			}

		});
		btnCargar1.setBounds(69, 267, 89, 23);
		contentPane.add(btnCargar1);
		
		JButton btnCargar2 = new JButton("cargar");
		btnCargar2.setBackground(Color.white);
		btnCargar2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("imagenes :v","JPG","PNG", "gif");
				chooser.addChoosableFileFilter(filtro);			
				
				chooser.showOpenDialog(null);				
				File archivo = chooser.getSelectedFile();
				
				String nombreArchivo = archivo.getAbsolutePath();
				
				//ImageIcon icon = new ImageIcon(nombreArchivo);  //MUESTRA LA IMAGEN SIN ESCALA
				
				ImageIcon imageIcon = new ImageIcon(new ImageIcon(nombreArchivo).getImage().getScaledInstance(label2.getWidth(), label2.getHeight(), Image.SCALE_DEFAULT));
								
				label2.setIcon(imageIcon);
				
		
				try {
					imagen2 = ImageIO.read(new File(nombreArchivo)); // Asignacion de la ruta a la variable imagen2 original para ser operada
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
			}
		});
		btnCargar2.setBounds(467, 267, 89, 23);
		contentPane.add(btnCargar2);
		
		JButton btnProcesar = new JButton("procesar");
		btnProcesar.setBackground(Color.white);
		btnProcesar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
								
				String operacionSeleccionada = (String) comboBox.getSelectedItem();
				
				if(imagen1 != null && imagen2 != null && comboBox.getSelectedItem() != null) {
					
					//Delegacion del control al objeto de la clase ImageOperator       <=========
					ImageOperator imgOperacion= new ImageOperator(imagen1, imagen2, Integer.parseInt(caja1.getText()), Integer.parseInt(caja2.getText())); 
					
						switch(operacionSeleccionada) {
						case "+":{
							imgOperacion.procesamientoImagen(operacionSeleccionada);
							imagenVisor ventana = new imagenVisor(imgOperacion); // Paso del objeto para visualizarlo en nueva Ventana
							JOptionPane.showMessageDialog(null,"suma completada! ");
							ventana.setVisible(true);					
							break;
						}
						case "-":{
							imgOperacion.procesamientoImagen(operacionSeleccionada);
							imagenVisor ventana = new imagenVisor(imgOperacion); // Paso del objeto para visualizarlo en nueva Ventana
							JOptionPane.showMessageDialog(null,"resta completada! ");
							ventana.setVisible(true);	
							break;
						}
						case "*":{
							imgOperacion.procesamientoImagen(operacionSeleccionada);
							imagenVisor ventana = new imagenVisor(imgOperacion); // Paso del objeto para visualizarlo en nueva Ventana
							JOptionPane.showMessageDialog(null,"multiplicacion completada! ");
							ventana.setVisible(true);	
							break;
						}
						case "CL":{
							imgOperacion.procesamientoImagen(operacionSeleccionada);
							imagenVisor ventana = new imagenVisor(imgOperacion); // Paso del objeto para visualizarlo en nueva Ventana
							JOptionPane.showMessageDialog(null,"operacion combinacional completada! ");
							ventana.setVisible(true);	
							break;
						}
						default:{
							JOptionPane.showMessageDialog(null,"Error! ");

						}
					}
				}
				else {
					
					JOptionPane.showMessageDialog(null,"Error! ");
				}
					
			}
		});
		btnProcesar.setBounds(291, 291, 89, 23);
		contentPane.add(btnProcesar);
		
		caja1 = new JTextField();
		caja1.setText("4");
		caja1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evento) { // Valida la entrada de enteros <=2 para  renglones y columnas
				char numero = evento.getKeyChar();
				if(caja1.getText().length() >= 2  || !(Character.isDigit(numero) || (numero == KeyEvent.VK_BACK_SPACE) || (numero == KeyEvent.VK_DELETE))){
					getToolkit().beep();
					evento.consume();
				}
			}
		});
		caja1.setBounds(275, 267, 25, 20);
		contentPane.add(caja1);
		caja1.setColumns(10);
		
		caja2 = new JTextField();
		caja2.setText("4");
		caja2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evento) {	 // Valida la entrada de enteros <=2 para  renglones y columnas
				char numero = evento.getKeyChar();
				if(caja2.getText().length() >= 2  || !(Character.isDigit(numero) || (numero == KeyEvent.VK_BACK_SPACE) || (numero == KeyEvent.VK_DELETE))){
					getToolkit().beep();
					evento.consume();
				}
			}
		});
		caja2.setBounds(389, 267, 25, 20);
		contentPane.add(caja2);
		caja2.setColumns(10);
		
		JLabel lblFilas = new JLabel("filas");
		lblFilas.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFilas.setBounds(248, 271, 29, 14);
		contentPane.add(lblFilas);
		
		JLabel lblColumnas = new JLabel("columnas");
		lblColumnas.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblColumnas.setBounds(340, 271, 50, 14);
		contentPane.add(lblColumnas);
		
		
		
	}
}

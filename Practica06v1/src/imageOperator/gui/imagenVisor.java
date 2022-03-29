package imageOperator.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import operacionesImagenes.ImageOperator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class imagenVisor extends JFrame {

	private JPanel contentPane;
	private BufferedImage imagenResultado; // variable para almacenar la imagen resultante
	/**
	 * Create the frame.
	 */
	public imagenVisor(ImageOperator imgR) {   //paramtero : objeto imagen resultante de ImageOperator
		
		this.imagenResultado = imgR.getResultadoImagen();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel contenidoImagen = new JLabel("");
		contenidoImagen.setBounds(0, 0, 486, 280);
		contentPane.add(contenidoImagen);
		
		JButton guardarImagen = new JButton("Guardar");
		guardarImagen.setFont(new Font("Tahoma", Font.PLAIN, 9));
		guardarImagen.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser saveFileChooser = new JFileChooser();
				saveFileChooser.setDialogTitle("Guardar imagen");
				saveFileChooser.setFileFilter(new FileNameExtensionFilter("imagenes: png, jpg, gif", "png", "jpg", "gif"));
				int respuesta = saveFileChooser.showSaveDialog(imagenVisor.this);
				
				if(respuesta == JFileChooser.APPROVE_OPTION) {
					try {
						//Guardar imagenes con extension
						File archivoConExtension = saveFileChooser.getSelectedFile();
						String filePath = archivoConExtension.getAbsolutePath();
						if(!filePath.endsWith(".jpg")) {
							archivoConExtension = new File(filePath + ".jpg");
						    ImageIO.write(imagenResultado, "jpg" , archivoConExtension.getAbsoluteFile());
						}
						else if(!filePath.endsWith(".png")) {
							archivoConExtension = new File(filePath + ".png");
						    ImageIO.write(imagenResultado, "jpg" , archivoConExtension.getAbsoluteFile());	
						}
						else if(!filePath.endsWith(".gif")) {
							archivoConExtension = new File(filePath + ".gif");
						    ImageIO.write(imagenResultado, "gif" , archivoConExtension.getAbsoluteFile());	
						}						
						
					}catch(IOException ioe){
						JOptionPane.showMessageDialog(null,"Error!");
					}
				}
				
			}
		});
		guardarImagen.setBounds(10, 291, 71, 23);
		contentPane.add(guardarImagen);
		
		ImageIcon muestraImg = new ImageIcon(imagenResultado);
		contenidoImagen.setIcon(muestraImg);
		
		JButton cerrarImagen = new JButton("Cerrar");
		cerrarImagen.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cerrarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				//setVisible(false); //oculta el frame
			}
		});
		cerrarImagen.setBounds(413, 291, 63, 23);
		contentPane.add(cerrarImagen);
		
	}
}

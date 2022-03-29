package imageOperator.gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;


public class MainWindow {
	//variable archivo
	File archivo;
	
    final Display display = new Display();
    final Shell shell = new Shell(display, SWT.SHELL_TRIM);
    
	
	protected Shell shlOperacionesEnImagenes;
	private static final String[] OperadorImagen = { "opcion","+", "-", "*"};
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
		shlOperacionesEnImagenes.open();
		shlOperacionesEnImagenes.layout();
		while (!shlOperacionesEnImagenes.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {

		
		shlOperacionesEnImagenes = new Shell();
		shlOperacionesEnImagenes.setSize(746, 397);
		shlOperacionesEnImagenes.setText("Operaciones en Imagenes");
		shlOperacionesEnImagenes.setLayout(null);
		
		Label label1 = new Label(shlOperacionesEnImagenes, SWT.CENTER);
		label1.setBounds(10, 10, 315, 290);
		label1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label label2 = new Label(shlOperacionesEnImagenes, SWT.NONE);
		label2.setBounds(405, 10, 315, 290);
		label2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Combo combo = new Combo(shlOperacionesEnImagenes, SWT.DROP_DOWN); //
		combo.setBounds(331, 249, 55, 23);
		combo.setToolTipText("");
		combo.setItems(OperadorImagen);
		combo.select(0);
		
		
		Button btnOperar = new Button(shlOperacionesEnImagenes, SWT.NONE);
		btnOperar.setBounds(324, 306, 75, 25);
		btnOperar.setText("Operar");
		
		Button btnCargarImagen1 = new Button(shlOperacionesEnImagenes, SWT.NONE);
		btnCargarImagen1.setBounds(118, 306, 75, 25);
		btnCargarImagen1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.showDialog(null, null);
				File archivo = chooser.getSelectedFile(); 
				String nombreArchivo = archivo.getAbsolutePath();
				//label1.setText(nombreArchivo);
				ImageIcon icon = new ImageIcon(nombreArchivo);
				label1.setRegion(icon);
				
				
			}
		});
		btnCargarImagen1.setText("Nueva");
		
		Button btnCargarImagen2 = new Button(shlOperacionesEnImagenes, SWT.NONE);
		btnCargarImagen2.setBounds(547, 306, 75, 25);
		btnCargarImagen2.setText("Nueva");

	}
}

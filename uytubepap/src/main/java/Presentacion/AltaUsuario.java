package Presentacion;

import java.awt.Image;
import java.io.File;
import java.sql.Date;
import javax.swing.JInternalFrame;

import interfaces.IControlador;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import datatypes.DtCanal;

import java.awt.Choice;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class AltaUsuario extends JInternalFrame {
	private JTextField textFieldNickname;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldCorreo;
	private JLabel lblNickname;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblCorreo;
	private JLabel lblFecha;
	private Button btnCancel;
	private Button btnAceptar;
	private JButton btnSubirImagen;
	private JCheckBox chckbxIngresarDatosCanal;
	private JTextField textFieldNombreCanal;
	private JLabel lblNombreCanal; 
	private JLabel lblDescripcion;
	private JTextArea textAreaDescripcion;
	private JCheckBox chckbxPrivado; 
	
	private Choice dia;
	private Choice mes;
	private Choice anio;
	
	private String imagen;
	public Image foto1=null;
	private JLabel lblImagen;
	private IControlador controller;
	
		

	/**
	 * Create the frame.
	 */
	public AltaUsuario(IControlador ctrl) {
		controller = ctrl;
		setClosable(true);
		setBounds(100, 100, 530, 430);
		setTitle("Alta Usuario");
		getContentPane().setLayout(null);
		
		lblNickname = new JLabel("NickName");
		lblNickname.setBounds(50, 26, 70, 14);
		getContentPane().add(lblNickname);
		
		textFieldNickname = new JTextField();
		textFieldNickname.setBounds(163, 23, 200, 20);
		getContentPane().add(textFieldNickname);
		textFieldNickname.setColumns(10);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(50, 57, 46, 14);
		getContentPane().add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(163, 54, 200, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(50, 88, 46, 14);
		getContentPane().add(lblApellido);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(163, 85, 200, 20);
		getContentPane().add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(50, 119, 46, 14);
		getContentPane().add(lblCorreo);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setBounds(163, 116, 200, 20);
		getContentPane().add(textFieldCorreo);
		textFieldCorreo.setColumns(10);
		
		dia = new Choice();
		dia.setBounds(163, 152, 43, 20);
		getContentPane().add(dia);
		
		mes = new Choice();
		mes.setBounds(240, 152, 40, 20);
		getContentPane().add(mes);
		
		anio = new Choice();
		anio.setBounds(310, 152, 53, 20);
		getContentPane().add(anio);
		
		fillDiaMesAnio();
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(50, 152, 46, 14);
		getContentPane().add(lblFecha);
		
		btnCancel = new Button("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				finCasoUso();
			}
		});
		btnCancel.setBounds(163, 357, 70, 22);
		getContentPane().add(btnCancel);
		
		btnAceptar = new Button("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registroUsuario();
			}
		});
		btnAceptar.setBounds(293, 357, 70, 22);
		getContentPane().add(btnAceptar);		
		
		lblImagen = new JLabel("Imagen");
		lblImagen.setBounds(384, 26, 108, 107);
		getContentPane().add(lblImagen);
		
		btnSubirImagen = new JButton("Subir Imagen");
		btnSubirImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SubirImagen();
			}
		});
		btnSubirImagen.setBounds(50, 197, 129, 23);
		getContentPane().add(btnSubirImagen);
		
		chckbxIngresarDatosCanal = new JCheckBox("Ingresar Datos Canal");
		chckbxIngresarDatosCanal.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				mostrarInputsCanal(arg0);
			}
		});
		chckbxIngresarDatosCanal.setBounds(319, 197, 141, 23);		
		getContentPane().add(chckbxIngresarDatosCanal);
		
		chckbxPrivado = new JCheckBox("Privado?");
		chckbxPrivado.setBounds(163, 236, 97, 23);
		chckbxPrivado.setVisible(false);
		getContentPane().add(chckbxPrivado);
		
		textFieldNombreCanal = new JTextField();
		textFieldNombreCanal.setBounds(163, 266, 200, 20);
		textFieldNombreCanal.setVisible(false);
		getContentPane().add(textFieldNombreCanal);
		textFieldNombreCanal.setColumns(10);
		
		lblNombreCanal = new JLabel("Nombre Canal");
		lblNombreCanal.setBounds(50, 269, 103, 14);
		lblNombreCanal.setVisible(false);
		getContentPane().add(lblNombreCanal);
		
		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(50, 294, 103, 14);
		lblDescripcion.setVisible(false);
		getContentPane().add(lblDescripcion);
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setBounds(163, 297, 200, 38);
		textAreaDescripcion.setVisible(false);
		getContentPane().add(textAreaDescripcion);

	}
	
	public void finCasoUso() {
		controller.finCasoUso();
	}
	
	
	public void mostrarInputsCanal(ChangeEvent arg0) {
		if(chckbxIngresarDatosCanal.isSelected()) {
			lblNombreCanal.setVisible(true);
			lblDescripcion.setVisible(true);
			textFieldNombreCanal.setVisible(true);
			textAreaDescripcion.setVisible(true);
			chckbxPrivado.setVisible(true);
		}
		else {
			lblNombreCanal.setVisible(false);
			lblDescripcion.setVisible(false);
			textFieldNombreCanal.setVisible(false);
			textAreaDescripcion.setVisible(false);
			chckbxPrivado.setVisible(false);
		}
		
	}
	
	public void SubirImagen() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Imagen","jpg","png");
		File Directorio = fileChooser.getCurrentDirectory();	
		
		fileChooser.setFileFilter(filter);	
	    fileChooser.setCurrentDirectory(Directorio);
	    int result = fileChooser.showOpenDialog(null);
	    
	    if ( result == JFileChooser.APPROVE_OPTION ){
	    	 try
             {
	    		 //Crear propiedades para ser utilizadas por fileChooser
	    		 File archivoElegido = fileChooser.getSelectedFile();
	    		 //Obteniendo la direccion del archivo
	    		 String direccion = archivoElegido.getPath();
	    		 
	    		 imagen = archivoElegido.getPath();
				
	    		 JOptionPane.showMessageDialog(null, "Imagen cargada con exito");
	    		 
				 foto1 = new ImageIcon(direccion).getImage();
				 ImageIcon img2=new ImageIcon(foto1.getScaledInstance(157, 157, Image.SCALE_AREA_AVERAGING));
				 lblImagen.setIcon(img2);
             }
	    	 catch(Exception es)
             {
                     JOptionPane.showMessageDialog(null, "Error al abrir imagen "+ es);
             }
	    }

	}
		
	
	public void fillDiaMesAnio(){
		for (Integer i = 1; i < 32; i++) {
			dia.add(i.toString());
		}
		
		for (Integer i = 1; i < 13; i++) {
			mes.add(i.toString());
		}
		
		for (Integer i = 1; i < 2017; i++) {
			anio.add(i.toString());
		}
	}
	
	public boolean checkFormulario() {
		String nick = this.textFieldNickname.getText();
		String nombre = this.textFieldNombre.getText();
		String apellido = this.textFieldApellido.getText();
		String correo = this.textFieldCorreo.getText();
		String fecha = dia.getSelectedItem() + mes.getSelectedItem() + anio.getSelectedItem();		
		if (nick.equals("") || nombre.equals("")  || apellido.equals("") || correo.equals("") || fecha.equals("")) {
			JOptionPane.showMessageDialog(null, "Quedan campos sin rellenar.");		
			return false;
		}
		else return true;
			
		
		
	}
	
	public void registroUsuario() {
		
		String nick = this.textFieldNickname.getText();
		String nombre = this.textFieldNombre.getText();
		String apellido = this.textFieldApellido.getText();
		String correo = this.textFieldCorreo.getText();
		DtCanal datosCanal; 
		if (this.chckbxIngresarDatosCanal.isSelected()) 
		{
			String nombreCanal = this.textFieldNombreCanal.getText();
			String descripcion = this.textAreaDescripcion.getText();
			boolean privado = this.chckbxPrivado.isSelected();
			datosCanal = new DtCanal(nombreCanal, descripcion, nick, privado);
		}
		else datosCanal = new DtCanal(nick, "", "", true);		
		
		if (checkFormulario()) {
			try {
				@SuppressWarnings("deprecation")
				Date fechaNac = new Date(Integer.parseInt(dia.getSelectedItem()),Integer.parseInt(mes.getSelectedItem()),Integer.parseInt(anio.getSelectedItem()));
				if (controller.ingresarUsuario(nick, apellido, nombre, correo, fechaNac, imagen, datosCanal)) {
					JOptionPane.showMessageDialog(this, "Se creó el usuario exitosamente.", "Alta Usuario", JOptionPane.INFORMATION_MESSAGE);
					finCasoUso();
					dispose();
				}					
				else {
					JOptionPane.showMessageDialog(this, "Hubo un error al crear el usuario, por favor ingrese otro mail y/o nickname");
				}
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(this, "Error inesperado.");
				
			}
			
		}
		
		
		
	}
}

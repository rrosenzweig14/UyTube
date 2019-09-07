package Presentacion;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import com.toedter.calendar.JDateChooser;

import datatypes.DtCanal;
import interfaces.Fabrica;
import interfaces.IControlador;

public class AddUser extends JPanel {
	private JTextField textFieldNickname;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldCorreo;
	private JLabel lblNickname;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblCorreo;
	private JLabel lblFecha;
	private JButton btnCancel;
	private JButton btnAceptar;
	private JButton btnSubirImagen;
	private JCheckBox chckbxIngresarDatosCanal;
	private JTextField textFieldNombreCanal;
	private JLabel lblNombreCanal; 
	private JLabel lblDescripcion;
	private JTextArea textAreaDescripcion;
	private JCheckBox chckbxPrivado; 
	private JDateChooser dateChooserFechaNac;
	
	private String imagen;
	public Image foto1=null;
	private JLabel lblImagen;
	
	protected IControlador controller = Fabrica.getIControlador();
		

	/**
	 * Create the frame.
	 */
	public AddUser() {
		this.setBounds(140,25,1000,800);
		this.setBorder(new LineBorder(UIManager.getColor("List.dropLineColor"), 2, true));
		setLayout(null);
		
		JLabel lblAltaDeUsuario = new JLabel("Alta de Usuario");
		lblAltaDeUsuario.setLabelFor(this);
		lblAltaDeUsuario.setBounds(295, 44, 410, 73);
		lblAltaDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		Font font = new Font("Courier", Font.BOLD,32);
		lblAltaDeUsuario.setFont(font);
		add(lblAltaDeUsuario);
		
		lblNickname = new JLabel("NickName");
		lblNickname.setBounds(243, 177, 153, 20);
		lblNickname.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNickname);
		
		textFieldNickname = new JTextField();
		textFieldNickname.setBounds(400, 177, 200, 20);
		add(textFieldNickname);
		textFieldNickname.setColumns(10);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(243, 208, 153, 20);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(400, 208, 200, 20);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(243, 236, 153, 20);
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblApellido);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(400, 236, 200, 20);
		add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(243, 267, 153, 20);
		lblCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCorreo);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setBounds(400, 267, 200, 20);
		add(textFieldCorreo);
		textFieldCorreo.setColumns(10);
		
		lblFecha = new JLabel("Fecha de \nNacimiento");
		lblFecha.setBounds(243, 298, 153, 20);
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblFecha);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(845, 24, 129, 22);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//dispose();
				finCasoUso();
			}
		});
		add(btnCancel);
		
		btnAceptar = new JButton("Agregar");
		btnAceptar.setBounds(435, 628, 129, 22);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registroUsuario();
			}
		});
		add(btnAceptar);		
		
		lblImagen = new JLabel("Imagen");
		lblImagen.setBorder(new LineBorder(UIManager.getColor("MenuItem.acceleratorForeground"), 1, true));
		lblImagen.setBounds(632, 177, 103, 107);
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblImagen);
		
		btnSubirImagen = new JButton("Subir Imagen");
		btnSubirImagen.setBounds(623, 298, 127, 20);
		btnSubirImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SubirImagen();
			}
		});
		add(btnSubirImagen);
		
		chckbxIngresarDatosCanal = new JCheckBox("Ingresar Datos Canal");
		chckbxIngresarDatosCanal.setBounds(400, 345, 200, 23);
		chckbxIngresarDatosCanal.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				mostrarInputsCanal(arg0);
			}
		});
		add(chckbxIngresarDatosCanal);
		
		chckbxPrivado = new JCheckBox("Privado?");
		chckbxPrivado.setBounds(400, 494, 200, 23);
		chckbxPrivado.setVisible(false);
		add(chckbxPrivado);
		
		textFieldNombreCanal = new JTextField();
		textFieldNombreCanal.setBounds(400, 417, 200, 20);
		textFieldNombreCanal.setVisible(false);
		add(textFieldNombreCanal);
		textFieldNombreCanal.setColumns(10);
		
		lblNombreCanal = new JLabel("Nombre Canal");
		lblNombreCanal.setBounds(243, 417, 153, 20);
		lblNombreCanal.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreCanal.setVisible(false);
		add(lblNombreCanal);
		
		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(243, 448, 153, 19);
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setVisible(false);
		add(lblDescripcion);
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setBounds(400, 448, 200, 38);
		textAreaDescripcion.setVisible(false);
		add(textAreaDescripcion);
		
		dateChooserFechaNac = new JDateChooser();
		dateChooserFechaNac.setBounds(400, 298, 200, 20);
		add(dateChooserFechaNac);

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
		
	
	public boolean checkFormulario() {
		String nick = this.textFieldNickname.getText();
		String nombre = this.textFieldNombre.getText();
		String apellido = this.textFieldApellido.getText();
		String correo = this.textFieldCorreo.getText();
		if(dateChooserFechaNac.getDate() != null) {
			if (nick.equals("") || nombre.equals("")  || apellido.equals("") || correo.equals("")) {
				JOptionPane.showMessageDialog(null, "Quedan campos sin rellenar.");		
				return false;
			}
			else return true;
		}else {
			JOptionPane.showMessageDialog(null, "Falta llenar la fecha.");		
			return false;			
		}		
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

				Date fechaNac = dateChooserFechaNac.getDate();				
				if (controller.ingresarUsuario(nick, correo, nombre, apellido, fechaNac, imagen, datosCanal)) {

					JOptionPane.showMessageDialog(this, "Se creó el usuario exitosamente.", "Alta Usuario", JOptionPane.INFORMATION_MESSAGE);
					finCasoUso();
					//dispose();
				}					
				else {
					JOptionPane.showMessageDialog(this, "Hubo un error al crear el usuario, por favor ingrese otro mail y/o nickname");
				}
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(this, "Error inesperado.");
				finCasoUso();
				//dispose();
			}
			
		}
		
		
		
	}
}

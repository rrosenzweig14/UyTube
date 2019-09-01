package Presentacion;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import interfaces.IControlador;

@SuppressWarnings("serial")
public class AltaVideo extends JInternalFrame {
	private JTextField textFieldNombre;
	private JTextField textFieldUrl;
	private JFormattedTextField textFieldDuracion;
	private JTextArea textAreaDescripcion;
	private JLabel lblNombre;
	private JLabel lblUrl;
	private JLabel lblDescripcion;
	private JLabel lblDuracion;
	private Button btnCancel;
	private Button btnAceptar;
	private JLabel lblUsuario;
	private JComboBox<String> comboBoxUsuarios;
	private JLabel lblCategoria;
	private JComboBox<String> comboBoxCategorias;
	
	private IControlador controller;
		

	/**
	 * Create the frame.
	 */
	public AltaVideo(IControlador ctrl) {
		controller = ctrl;
		setClosable(true);
		setBounds(100, 100, 530, 430);
		setTitle("Alta Video");
		getContentPane().setLayout(null);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(50, 26, 70, 14);
		getContentPane().add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(163, 23, 200, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		lblUrl= new JLabel("URL");
		lblUrl.setBounds(50, 57, 46, 14);
		getContentPane().add(lblUrl);
		
		textFieldUrl= new JTextField();
		textFieldUrl.setBounds(163, 54, 200, 20);
		getContentPane().add(textFieldUrl);
		textFieldUrl.setColumns(10);
		
		lblDuracion= new JLabel("Duracion");
		lblDuracion.setBounds(50, 88, 75, 14);
		getContentPane().add(lblDuracion);
		
		textFieldDuracion= new JFormattedTextField();
		textFieldDuracion.setBounds(163, 85, 200, 20);
		getContentPane().add(textFieldDuracion);
		textFieldDuracion.setColumns(10);
		
		lblUsuario= new JLabel("Usuario");
		lblUsuario.setBounds(50, 119, 75, 14);
		getContentPane().add(lblUsuario);
		
		comboBoxUsuarios = new JComboBox<String>();
		comboBoxUsuarios.setBounds(163, 119, 200, 20);
		getContentPane().add(comboBoxUsuarios);
		
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(50, 152, 75, 14);
		getContentPane().add(lblCategoria);
		
		comboBoxCategorias = new JComboBox<String>();
		comboBoxCategorias.setBounds(163, 152, 200, 20);
		getContentPane().add(comboBoxCategorias);
		
		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(50, 192, 103, 14);
		getContentPane().add(lblDescripcion);
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setBounds(163, 192, 200, 38);
		getContentPane().add(textAreaDescripcion);
		
		btnCancel = new Button("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				controller.finCasoUso();
			}
		});
		btnCancel.setBounds(163, 250, 70, 22);
		getContentPane().add(btnCancel);
		
		btnAceptar = new Button("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registroVideo();
			}
		});
		btnAceptar.setBounds(293, 250, 70, 22);
		getContentPane().add(btnAceptar);		
	}
	
	public boolean checkFormulario() {
		String nombre = this.textFieldNombre.getText();
		String url = this.textFieldUrl.getText();
		String duracion = this.textFieldDuracion.getText();
		String usuario = (String) this.comboBoxUsuarios.getSelectedItem();
		
		if (nombre == "" || url == "" || duracion == "" || usuario == "") {
			JOptionPane.showMessageDialog(null, "Quedan campos sin rellenar.");		
			return false;
		}
		else return true;
	}
	
	public void fillUsers() {
		comboBoxUsuarios.addItem(" ");
		ArrayList<String> users = controller.listarUsuarios();
		for (String s : users) {
			comboBoxUsuarios.addItem(s);
		}
	}

	public void fillCategories() {
		comboBoxCategorias.addItem(" ");
		ArrayList<String> categorias = controller.listarCategorias();
		for (String c : categorias) {
			comboBoxCategorias.addItem(c);
		}
	}
	
	public void registroVideo() {
		String nombre = this.textFieldNombre.getText();
		String url = this.textFieldUrl.getText();
		Integer duracion = (Integer) this.textFieldDuracion.getValue();
		String descripcion = this.textAreaDescripcion.getText();
		
		if (checkFormulario()) {
			try {
				java.util.Date fechaPub= new java.util.Date();
				controller.seleccionarUsuario((String) this.comboBoxUsuarios.getSelectedItem());
				controller.seleccionarCategoria((String) this.comboBoxCategorias.getSelectedItem());
				if (controller.ingresarVideo(nombre, duracion, url, descripcion, fechaPub)) {
					JOptionPane.showMessageDialog(this, "Se creó el video exitosamente.", "Alta Video", JOptionPane.INFORMATION_MESSAGE);
					controller.finCasoUso();
					dispose();
				}					
				else {
					JOptionPane.showMessageDialog(this, "Hubo un error al crear el video, por favor intente nuevamente");
				}
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(this, "Error inesperado.");
				
			}
		}
	}
}

package Presentacion;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JInternalFrame;

import interfaces.IControlador;
import logica.Handler;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;

import datatypes.DtLista;
import datatypes.DtUsuario;

import javax.swing.JScrollBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;


public class ConsultaListas extends JInternalFrame {

	private IControlador cr;
	private JTextField nombre;
	private JTextField privacidad;
	private JTextField tipo;
	private JComboBox cbUsuario;
	private JComboBox cbLista;


	/**
	 * Create the frame.
	 */
	public ConsultaListas(IControlador ctrl) {
		cr = ctrl;
		setBounds(100, 100, 386, 303);
		setClosable(true);
		setTitle("Consulta de Listas");
		getContentPane().setLayout(null);
		
		JLabel lblSeleccioneUsuario = new JLabel("Seleccione Usuario");
		lblSeleccioneUsuario.setBounds(12, 24, 151, 15);
		getContentPane().add(lblSeleccioneUsuario);
		
		JLabel lblSeleccioneLista = new JLabel("Seleccione Lista");
		lblSeleccioneLista.setBounds(12, 51, 151, 15);
		getContentPane().add(lblSeleccioneLista);
		
		JLabel lblDatosDeLa = new JLabel("Datos de la Lista Seleccionada");
		lblDatosDeLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosDeLa.setBounds(12, 78, 352, 15);
		getContentPane().add(lblDatosDeLa);
		
		cbUsuario = new JComboBox();
		cbUsuario.setBounds(182, 24, 166, 15);
		cbUsuario.setVisible(true);
		fillUsers();
		getContentPane().add(cbUsuario);
		
		cbLista = new JComboBox();
		cbLista.setBounds(182, 51, 166, 15);
		//fillListas((String) cbUsuario.getSelectedItem());
		getContentPane().add(cbLista);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(39, 105, 70, 15);
		getContentPane().add(lblNombre);
		
		JLabel lblPrivacidad = new JLabel("Privacidad");
		lblPrivacidad.setBounds(39, 135, 87, 15);
		getContentPane().add(lblPrivacidad);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(39, 162, 70, 15);
		getContentPane().add(lblTipo);
		
		JLabel lblVideos = new JLabel("Videos");
		lblVideos.setBounds(39, 189, 70, 15);
		getContentPane().add(lblVideos);
		
		nombre = new JTextField();
		nombre.setBounds(209, 105, 114, 19);
		getContentPane().add(nombre);
		nombre.setColumns(10);
		
		privacidad = new JTextField();
		privacidad.setBounds(209, 133, 114, 19);
		getContentPane().add(privacidad);
		privacidad.setColumns(10);
		
		tipo = new JTextField();
		tipo.setBounds(209, 160, 114, 19);
		getContentPane().add(tipo);
		tipo.setColumns(10);
		
		JComboBox cbVideos = new JComboBox();
		cbVideos.setBounds(209, 189, 114, 15);
		getContentPane().add(cbVideos);
		
		JButton btnConsultarVideo = new JButton("Consultar Video");
		btnConsultarVideo.setBounds(115, 234, 159, 25);
		getContentPane().add(btnConsultarVideo);
	}
	
	
	public void fillUsers() {
		cbUsuario.addItem(" ");
		ArrayList<String> users = cr.listarUsuarios();
		for (String s : users) {
			cbUsuario.addItem(s);
		}
	}
	
	public void fillListas(String usuario) {
		cbLista.addItem(" ");
		DtUsuario us = cr.seleccionarUsuario(usuario);
		ArrayList<DtLista> listas = cr.listarListasReproduccion(us);
		for(DtLista dtl : listas)
			cbLista.addItem(dtl.getNombre());
	}
	
}

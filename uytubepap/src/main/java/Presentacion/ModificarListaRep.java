package Presentacion;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

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
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;

import datatypes.DtLista;
import datatypes.DtUsuario;
import datatypes.DtVideo;

import javax.swing.JScrollBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;


public class ModificarListaRep extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IControlador cr;
	private JTextField categoria;
	private JTextField privacidad;
	private JComboBox cbUsuario;
	private JComboBox cbLista;
	private JComboBox cbNueva_cat;
	private JComboBox cbNueva_priv;

	/**
	 * Create the frame.
	 */
	public ModificarListaRep(IControlador ctrl) {
		cr = ctrl;
		setBounds(100, 100, 386, 303);
		setClosable(true);
		setTitle("Modificar Lista Reproduccion");
		getContentPane().setLayout(null);
		
		JLabel lblSeleccioneUsuario = new JLabel("Seleccione Usuario");
		lblSeleccioneUsuario.setBounds(12, 24, 151, 15);
		getContentPane().add(lblSeleccioneUsuario);
		
		JLabel lblSeleccioneLista = new JLabel("Seleccione Lista");
		lblSeleccioneLista.setBounds(12, 51, 151, 15);
		getContentPane().add(lblSeleccioneLista);
		
		JLabel lblDatosDeLa = new JLabel("Datos Modificables de la Lista Seleccionada");
		lblDatosDeLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosDeLa.setBounds(12, 78, 352, 15);
		getContentPane().add(lblDatosDeLa);
		
		cbUsuario = new JComboBox();
		cbUsuario.setBounds(182, 24, 166, 18);
		cbUsuario.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(" " != (String) cbUsuario.getSelectedItem() && cbUsuario.getSelectedItem() != null) {
					cbLista.removeAllItems();
					limpiarCampos();
					fillListas((String) cbUsuario.getSelectedItem()); 
				}
			}
		});
		cbUsuario.setVisible(true);
		getContentPane().add(cbUsuario);
		
		cbLista = new JComboBox();
		cbLista.setBounds(182, 51, 166, 18);
		cbLista.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(" " != (String) cbLista.getSelectedItem()) {
					limpiarCampos();
					cargarLista();
				}
			}
		});
		getContentPane().add(cbLista);
		
		JLabel lblNombre = new JLabel("Categoria");
		lblNombre.setBounds(39, 105, 87, 15);
		getContentPane().add(lblNombre);
		
		JLabel lblPrivacidad = new JLabel("Privacidad");
		lblPrivacidad.setBounds(39, 135, 97, 15);
		getContentPane().add(lblPrivacidad);
		
		categoria = new JTextField();
		categoria.setBounds(209, 105, 114, 19);

		getContentPane().add(categoria);
		categoria.setColumns(10);
		
		privacidad = new JTextField();
		privacidad.setBounds(209, 133, 114, 19);
		getContentPane().add(privacidad);
		privacidad.setColumns(10);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(128, 223, 117, 25);
		btnModificar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modificarLista();			
			}
		});
		getContentPane().add(btnModificar);
		
		JLabel lblNewLabel = new JLabel("Nueva Categoria");
		lblNewLabel.setBounds(39, 162, 135, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNuevaPrivacidad = new JLabel("Nueva Privacidad");
		lblNuevaPrivacidad.setBounds(39, 189, 135, 15);
		getContentPane().add(lblNuevaPrivacidad);
		
		cbNueva_cat = new JComboBox();
		cbNueva_cat.setBounds(209, 164, 114, 15);
		getContentPane().add(cbNueva_cat);
		
		cbNueva_priv = new JComboBox();
		cbNueva_priv.setBounds(209, 189, 114, 15);
		getContentPane().add(cbNueva_priv);
	}
	
	
	public void fillUsers() {
		cr.finCasoUso();
		cbUsuario.addItem(" ");
		ArrayList<String> users = cr.listarUsuarios();
		for (String s : users) {
			cbUsuario.addItem(s);
		}
	}
	
	public void fillListas(String usuario) {
		cbLista.addItem(" ");
		DtUsuario us = cr.seleccionarUsuario(usuario);
		List<DtLista> listas = cr.listarListasParticulares(us);
		for(DtLista dtl : listas){
			cbLista.addItem(dtl.getNombre());
		}
	}
	
	public void fillCategories() {
		cbNueva_cat.addItem(" ");
		ArrayList<String> categorias = cr.listarCategorias();
		for (String c : categorias) {
			cbNueva_cat.addItem(c);
		}
	}
	
	public void fillPrivacidad(){
		cbNueva_priv.addItem("Privado");
		cbNueva_priv.addItem("Publico");
	}
	
	public void modificarLista(){
		Boolean privado = false;
		String aux = (String) cbNueva_priv.getSelectedItem();
		if(aux.equals("Privado"))
			privado = true;
		DtLista dtl = cr.seleccionarLista((String) cbLista.getSelectedItem());
		DtLista ndtl = new DtLista(dtl.getId(), dtl.getNombre(), privado, false, (String) cbNueva_cat.getSelectedItem()); 
		cr.modificarListaParticular(dtl, ndtl);
		fin();
	}
	

	
	public void cargarLista() {
		if(" " != (String) cbLista.getSelectedItem() && cbLista.getSelectedItem() != null ) {
			System.out.println((String) cbLista.getSelectedItem());
			DtLista lst = cr.seleccionarLista((String) cbLista.getSelectedItem());
			categoria.setText(lst.getCategoria());
			if(lst.isPrivado())
				privacidad.setText("Privado");
			else
				privacidad.setText("Publico");
		}
	}
	
	public void nuevoCaso(){
		cbLista.removeAllItems();
		cbUsuario.removeAllItems();
		cbNueva_cat.removeAllItems();
		cbNueva_priv.removeAllItems();
		privacidad.setText("");
		categoria.setText("");
	}
	
	public void limpiarCampos() {
		privacidad.setText("");
		categoria.setText("");
	}
	
		public void fin() {
		setVisible(false);
		JOptionPane.showMessageDialog(this, "Lista modificada", "Modificar Lista Reproduccion", JOptionPane.INFORMATION_MESSAGE);
		cr.finCasoUso();
		setVisible(false);
		dispose();		
}
}

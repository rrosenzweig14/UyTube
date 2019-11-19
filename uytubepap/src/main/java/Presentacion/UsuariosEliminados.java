package Presentacion;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import datatypes.DtUsuarioEliminado;
import interfaces.IControlador;

public class UsuariosEliminados extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private IControlador controller;
	private JLabel lblUsuarios;
	private Choice cmbUser1;
	private JPanel panelUsuario;
	private JLabel lblNombre;
	private JLabel lblNickname;
	private JTextField textFieldNickName;
	private JTextField textFieldNombre;
	private JLabel lblApellido;
	private JTextField textFieldApellido;
	private JLabel lblCorreo;
	private JTextField textFieldCorreo;
	private JLabel lblFechaDeNacimiento;
	private JTextField textFieldFecha;
	private JLabel lblNombreCanal;
	private JTextField textFieldNombreCanal;
	private JComboBox<String> comboBoxVideosCanal;
	private JLabel lblVideos;
	private JComboBox<String> comboBoxListasCanal;
	private JLabel lblListas;
	private JButton btnSalir;

	/**
	 * Create the frame.
	 */
	public UsuariosEliminados(IControlador ctrl) {
		setBounds(100, 100, 796, 329);
		controller = ctrl;
		setClosable(true);
		setTitle("Usuarios Eliminados");
		getContentPane().setLayout(null);

		cmbUser1 = new Choice();
		cmbUser1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				seleccionarUsuario();
			}
		});
		cmbUser1.setBounds(176, 11, 220, 20);
		getContentPane().add(cmbUser1);

		lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(56, 14, 75, 14);
		getContentPane().add(lblUsuarios);

		panelUsuario = new JPanel();
		panelUsuario.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelUsuario.setBounds(56, 72, 374, 198);
		panelUsuario.setLayout(null);
		getContentPane().add(panelUsuario);

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(25, 36, 87, 14);
		panelUsuario.add(lblNombre);

		lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(25, 11, 87, 14);
		panelUsuario.add(lblNickname);

		textFieldNickName = new JTextField();
		textFieldNickName.setEditable(false);
		textFieldNickName.setBounds(159, 8, 183, 20);
		panelUsuario.add(textFieldNickName);
		textFieldNickName.setColumns(10);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setBounds(159, 33, 183, 20);
		panelUsuario.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(25, 61, 87, 14);
		panelUsuario.add(lblApellido);

		textFieldApellido = new JTextField();
		textFieldApellido.setEditable(false);
		textFieldApellido.setBounds(159, 58, 183, 20);
		panelUsuario.add(textFieldApellido);
		textFieldApellido.setColumns(10);

		lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(25, 86, 87, 14);
		panelUsuario.add(lblCorreo);

		textFieldCorreo = new JTextField();
		textFieldCorreo.setEditable(false);
		textFieldCorreo.setBounds(159, 83, 183, 20);
		panelUsuario.add(textFieldCorreo);
		textFieldCorreo.setColumns(10);

		lblFechaDeNacimiento = new JLabel("Fecha de nac.");
		lblFechaDeNacimiento.setBounds(25, 111, 144, 14);
		panelUsuario.add(lblFechaDeNacimiento);

		textFieldFecha = new JTextField();
		textFieldFecha.setEditable(false);
		textFieldFecha.setBounds(159, 108, 183, 20);
		panelUsuario.add(textFieldFecha);
		textFieldFecha.setColumns(10);

		lblNombreCanal = new JLabel("Nombre Canal");
		lblNombreCanal.setBounds(25, 136, 107, 14);
		panelUsuario.add(lblNombreCanal);

		textFieldNombreCanal = new JTextField();
		textFieldNombreCanal.setBounds(159, 133, 183, 20);
		panelUsuario.add(textFieldNombreCanal);
		textFieldNombreCanal.setEditable(false);
		textFieldNombreCanal.setColumns(10);

		comboBoxVideosCanal = new JComboBox<String>();
		comboBoxVideosCanal.setEnabled(true);
		comboBoxVideosCanal.setBounds(558, 92, 189, 20);
		getContentPane().add(comboBoxVideosCanal);

		lblVideos = new JLabel("Videos");
		lblVideos.setBounds(471, 92, 81, 14);
		getContentPane().add(lblVideos);

		comboBoxListasCanal = new JComboBox<String>();
		comboBoxListasCanal.setEnabled(true);
		comboBoxListasCanal.setBounds(558, 137, 189, 20);
		getContentPane().add(comboBoxListasCanal);

		lblListas = new JLabel("Listas");
		lblListas.setBounds(471, 137, 46, 14);
		getContentPane().add(lblListas);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finCasoUso();
				cleanOutputData();
				dispose();
			}
		});
		btnSalir.setBounds(658, 11, 89, 23);
		getContentPane().add(btnSalir);
	}
	
	public void fillUsers() {
		cmbUser1.removeAll();
		cmbUser1.addItem(" ");
		ArrayList<String> users = controller.listarEliminados();
		for (String s : users) {
			cmbUser1.addItem(s);
		}
	}

	public void finCasoUso() {
		controller.finCasoUso();
	}

	public void cleanOutputData() {
		textFieldNickName.removeAll();
		textFieldNombre.removeAll();
		textFieldApellido.removeAll();
		textFieldCorreo.removeAll();
		textFieldFecha.removeAll();
		textFieldNombreCanal.removeAll();
		comboBoxVideosCanal.removeAllItems();
		comboBoxListasCanal.removeAllItems();
		finCasoUso();
	}

	public void fillDataUser(DtUsuarioEliminado user) {
		textFieldNickName.setText(user.getNickname());
		textFieldNombre.setText(user.getNombre());
		textFieldApellido.setText(user.getApellido());
		textFieldCorreo.setText(user.getEmail());
		textFieldFecha.setText(user.getFechaNac().toString());
		textFieldNombreCanal.setText(user.getNombreCanal());
		String[] videos = user.getVideos();
		comboBoxVideosCanal.removeAllItems();
		comboBoxVideosCanal.addItem(" ");
		if (videos != null) {
			for (String v : videos) {
				comboBoxVideosCanal.addItem(v);
			}
		}
		String[] listas = user.getListas();
		comboBoxListasCanal.removeAllItems();
		comboBoxListasCanal.addItem(" ");
		if (listas != null) {
			for (String s : listas) {
				comboBoxListasCanal.addItem(s);
			}
		}
	}
	
	public void seleccionarUsuario() {
		finCasoUso();
		String user = cmbUser1.getSelectedItem();
		fillUsers();
		if(user.contentEquals("")) {
			cleanOutputData();
		}else {
			DtUsuarioEliminado usr = controller.findEliminado(user);
			fillDataUser(usr);
			}			
		}
		
}

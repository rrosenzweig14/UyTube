package Presentacion;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JInternalFrame;

import interfaces.IControlador;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import javax.swing.border.LineBorder;

import datatypes.DtCanal;
import datatypes.DtLista;
import datatypes.DtUsuario;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ConsultaUsuario extends JInternalFrame {
	private IControlador controller;
	private JLabel lblUsuarios;
	private JComboBox comboBoxUsuarios;
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
	private JLabel lblImage;
	private JLabel lblNombreCanal;
	private JTextField textFieldNombreCanal;
	private JLabel lblDescripcion;
	private JTextField textFieldDescripcion;
	private JCheckBox chckbxPrivado;
	private JComboBox comboBoxVideosCanal;
	private JLabel lblVideos;
	private JComboBox comboBoxListasCanal;
	private JLabel lblListas;
	public Image imagen = null;

	/**
	 * Create the frame.
	 */
	public ConsultaUsuario(IControlador ctrl) {
		setBounds(100, 100, 450, 300);
		controller = ctrl;
		setBounds(100, 100, 700, 600);
		setClosable(true);
		setTitle("Consulta Usuario");
		getContentPane().setLayout(null);

		comboBoxUsuarios = new JComboBox();
		comboBoxUsuarios.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cleanOutputData();
				if (comboBoxUsuarios.getSelectedItem().toString().equals(" ")) {
					
				} else {
					Map<DtUsuario, DtCanal> datos = controller
							.listarDatosUsuario(comboBoxUsuarios.getSelectedItem().toString());
					Iterator<Entry<DtUsuario, DtCanal>> it = datos.entrySet().iterator();
					DtUsuario user = null;
					DtCanal canal = null;
					while (it.hasNext()) {
						Entry<DtUsuario, DtCanal> entry = it.next();
						user = entry.getKey();
						canal = entry.getValue();
					}
					if (user != null && canal != null)
						fillDataUser(user, canal);
				}
			}
		});
		comboBoxUsuarios.setBounds(176, 11, 220, 20);
		getContentPane().add(comboBoxUsuarios);

		lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(56, 14, 75, 14);
		getContentPane().add(lblUsuarios);

		panelUsuario = new JPanel();
		panelUsuario.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelUsuario.setBounds(56, 72, 570, 153);
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
		textFieldNickName.setBounds(132, 8, 183, 20);
		panelUsuario.add(textFieldNickName);
		textFieldNickName.setColumns(10);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setBounds(132, 33, 183, 20);
		panelUsuario.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(25, 61, 87, 14);
		panelUsuario.add(lblApellido);

		textFieldApellido = new JTextField();
		textFieldApellido.setEditable(false);
		textFieldApellido.setBounds(132, 58, 183, 20);
		panelUsuario.add(textFieldApellido);
		textFieldApellido.setColumns(10);

		lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(25, 86, 87, 14);
		panelUsuario.add(lblCorreo);

		textFieldCorreo = new JTextField();
		textFieldCorreo.setEditable(false);
		textFieldCorreo.setBounds(132, 83, 183, 20);
		panelUsuario.add(textFieldCorreo);
		textFieldCorreo.setColumns(10);

		lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setBounds(25, 111, 144, 14);
		panelUsuario.add(lblFechaDeNacimiento);

		textFieldFecha = new JTextField();
		textFieldFecha.setEditable(false);
		textFieldFecha.setBounds(132, 108, 183, 20);
		panelUsuario.add(textFieldFecha);
		textFieldFecha.setColumns(10);

		lblImage = new JLabel("Imagen");
		lblImage.setBounds(366, 11, 114, 114);
		panelUsuario.add(lblImage);

		lblNombreCanal = new JLabel("Nombre Canal");
		lblNombreCanal.setBounds(82, 246, 107, 14);
		getContentPane().add(lblNombreCanal);

		textFieldNombreCanal = new JTextField();
		textFieldNombreCanal.setEditable(false);
		textFieldNombreCanal.setBounds(188, 243, 180, 20);
		getContentPane().add(textFieldNombreCanal);
		textFieldNombreCanal.setColumns(10);

		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(82, 271, 100, 14);
		getContentPane().add(lblDescripcion);

		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setEditable(false);
		textFieldDescripcion.setBounds(188, 268, 180, 20);
		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);

		chckbxPrivado = new JCheckBox("Privado");
		chckbxPrivado.setEnabled(false);
		chckbxPrivado.setBounds(418, 242, 97, 23);
		getContentPane().add(chckbxPrivado);

		comboBoxVideosCanal = new JComboBox();
		comboBoxVideosCanal.setBounds(147, 314, 166, 20);
		getContentPane().add(comboBoxVideosCanal);

		lblVideos = new JLabel("Videos");
		lblVideos.setBounds(56, 317, 81, 14);
		getContentPane().add(lblVideos);

		comboBoxListasCanal = new JComboBox();
		comboBoxListasCanal.setBounds(437, 314, 189, 20);
		getContentPane().add(comboBoxListasCanal);

		lblListas = new JLabel("Listas");
		lblListas.setBounds(346, 317, 46, 14);
		getContentPane().add(lblListas);

	}

	public void fillUsers() {
		comboBoxUsuarios.addItem(" ");
		ArrayList<String> users = controller.listarUsuarios();
		for (String s : users) {
			comboBoxUsuarios.addItem(s);
		}
	}
	
	public void cleanOutputData() {		
		textFieldNickName.removeAll();		
		textFieldNombre.removeAll();
		textFieldApellido.removeAll();
		textFieldCorreo.removeAll();
		textFieldFecha.removeAll();
		textFieldNombreCanal.removeAll();
		textFieldDescripcion.removeAll();
		chckbxPrivado.removeAll();
		lblImage.removeAll();
		comboBoxVideosCanal.removeAllItems();	
		comboBoxListasCanal.removeAllItems();		
	}

	public void fillDataUser(DtUsuario user, DtCanal canal) {
		textFieldNickName.setText(user.getNickname());
		textFieldNombre.setText(user.getNombre());
		textFieldApellido.setText(user.getApellido());
		textFieldCorreo.setText(user.getEmail());
		textFieldFecha.setText(user.getFechaNac().toString());
		if (user.getImg() != null) {
			imagen = new ImageIcon(user.getImg()).getImage();
			ImageIcon img2 = new ImageIcon(imagen.getScaledInstance(157, 157, Image.SCALE_AREA_AVERAGING));
			lblImage.setIcon(img2);
		}
		textFieldNombreCanal.setText(canal.getNombre());
		textFieldDescripcion.setText(canal.getDescripcion());
		chckbxPrivado.setSelected(canal.isPrivado());

		ArrayList<String> videos = controller.listarVideos();
		comboBoxVideosCanal.addItem(" ");
		if (videos != null) {
			for (String v : videos) {
				comboBoxVideosCanal.addItem(v);
			}
		}

		ArrayList<DtLista> listas = controller.listarListasReproduccion(user);
		comboBoxListasCanal.addItem(" ");
		if (listas != null) {
			for (DtLista lst : listas) {
				comboBoxListasCanal.addItem(lst.getNombre());
			}
		}

	}

}

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
import datatypes.DtVideo;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JList;

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
	private JTextField textFieldNombreVideo;
	private JTextField textFieldDuracionVideo;
	private JTextField textFieldFechaPub;
	private JTextField textFieldURL;
	private JTree treeComentarios;
	private JLabel lblComentarios;
	private JLabel lblNombreVideo;
	private JLabel lblDuracion;
	private JLabel lblDescripcionVideo;
	private JTextArea textAreaDescripcionVideo;
	private JLabel lblFechaPublicado;
	private JCheckBox chckbxVideoPrivado;
	private JLabel lblUrl;
	private boolean userLoaded = false;

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
		panelUsuario.setBounds(56, 72, 570, 198);
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

		lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setBounds(25, 111, 144, 14);
		panelUsuario.add(lblFechaDeNacimiento);

		textFieldFecha = new JTextField();
		textFieldFecha.setEditable(false);
		textFieldFecha.setBounds(159, 108, 183, 20);
		panelUsuario.add(textFieldFecha);
		textFieldFecha.setColumns(10);

		lblImage = new JLabel("Imagen");
		lblImage.setBounds(378, 11, 114, 114);
		panelUsuario.add(lblImage);

		lblNombreCanal = new JLabel("Nombre Canal");
		lblNombreCanal.setBounds(25, 136, 107, 14);
		panelUsuario.add(lblNombreCanal);

		textFieldNombreCanal = new JTextField();
		textFieldNombreCanal.setBounds(159, 133, 183, 20);
		panelUsuario.add(textFieldNombreCanal);
		textFieldNombreCanal.setEditable(false);
		textFieldNombreCanal.setColumns(10);
		
		chckbxPrivado = new JCheckBox("Privado");
		chckbxPrivado.setBounds(388, 132, 97, 23);
		panelUsuario.add(chckbxPrivado);
		chckbxPrivado.setEnabled(false);

		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(25, 161, 100, 14);
		panelUsuario.add(lblDescripcion);

		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(159, 158, 183, 20);
		panelUsuario.add(textFieldDescripcion);
		textFieldDescripcion.setEditable(false);
		textFieldDescripcion.setColumns(10);

		comboBoxVideosCanal = new JComboBox();
		comboBoxVideosCanal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cleanVideoData();
				if(userLoaded && comboBoxVideosCanal.getSelectedItem() != null && !comboBoxVideosCanal.getSelectedItem().toString().equals(" ")) {
					DtVideo video = controller.seleccionarVideo(comboBoxVideosCanal.getSelectedItem().toString());
					fillVideoData(video);
				}				
			}
		});
		comboBoxVideosCanal.setBounds(155, 281, 166, 20);
		getContentPane().add(comboBoxVideosCanal);

		lblVideos = new JLabel("Videos");
		lblVideos.setBounds(56, 281, 81, 14);
		getContentPane().add(lblVideos);

		comboBoxListasCanal = new JComboBox();
		comboBoxListasCanal.setBounds(437, 281, 189, 20);
		getContentPane().add(comboBoxListasCanal);

		lblListas = new JLabel("Listas");
		lblListas.setBounds(350, 281, 46, 14);
		getContentPane().add(lblListas);
		
		treeComentarios = null;
//		treeComentarios.setBounds(374, 332, 252, 185);
//		treeComentarios.removeAll();
//		getContentPane().add(treeComentarios);
		
		lblComentarios = new JLabel("Comentarios");
		lblComentarios.setBounds(374, 312, 137, 14);
		getContentPane().add(lblComentarios);
		
		lblNombreVideo = new JLabel("Nombre Video");
		lblNombreVideo.setBounds(10, 315, 113, 14);
		getContentPane().add(lblNombreVideo);
		
		textFieldNombreVideo = new JTextField();
		textFieldNombreVideo.setEditable(false);
		textFieldNombreVideo.setBounds(133, 312, 221, 20);
		getContentPane().add(textFieldNombreVideo);
		textFieldNombreVideo.setColumns(10);
		
		lblDuracion = new JLabel("Duracion");
		lblDuracion.setBounds(10, 402, 107, 14);
		getContentPane().add(lblDuracion);
		
		lblDescripcionVideo = new JLabel("Descripcion");
		lblDescripcionVideo.setBounds(10, 351, 121, 14);
		getContentPane().add(lblDescripcionVideo);
		
		textAreaDescripcionVideo = new JTextArea();
		textAreaDescripcionVideo.setEditable(false);
		textAreaDescripcionVideo.setBounds(133, 346, 221, 35);
		getContentPane().add(textAreaDescripcionVideo);
		
		textFieldDuracionVideo = new JTextField();
		textFieldDuracionVideo.setEditable(false);
		textFieldDuracionVideo.setBounds(133, 399, 80, 20);
		getContentPane().add(textFieldDuracionVideo);
		textFieldDuracionVideo.setColumns(10);
		
		lblFechaPublicado = new JLabel("Fecha publicado");
		lblFechaPublicado.setBounds(10, 427, 113, 14);
		getContentPane().add(lblFechaPublicado);
		
		textFieldFechaPub = new JTextField();
		textFieldFechaPub.setEditable(false);
		textFieldFechaPub.setBounds(133, 430, 80, 20);
		getContentPane().add(textFieldFechaPub);
		textFieldFechaPub.setColumns(10);
		
		chckbxVideoPrivado = new JCheckBox("Privado");
		chckbxVideoPrivado.setEnabled(false);
		chckbxVideoPrivado.setBounds(271, 398, 97, 23);
		getContentPane().add(chckbxVideoPrivado);
		
		lblUrl = new JLabel("URL");
		lblUrl.setBounds(10, 464, 46, 14);
		getContentPane().add(lblUrl);
		
		textFieldURL = new JTextField();
		textFieldURL.setEditable(false);
		textFieldURL.setBounds(133, 461, 221, 20);
		getContentPane().add(textFieldURL);
		textFieldURL.setColumns(10);

	}

	public void fillUsers() {
		comboBoxUsuarios.addItem(" ");
		ArrayList<String> users = controller.listarUsuarios();
		for (String s : users) {
			comboBoxUsuarios.addItem(s);
		}
	}
	
	public void fillVideoData(DtVideo video) {
		textFieldNombreVideo.setText(video.getNombre());
		textFieldDescripcion.setText(video.getDescripcion());
		textFieldFecha.setText(video.getFechaPub().toString());
		textFieldURL.setText(video.getUrl());
		if (video.getComentarios() != null) {
			treeComentarios = video.getComentarios();
			treeComentarios.setBounds(374, 332, 300, 185);
			getContentPane().add(treeComentarios);
		}
		//treeComentarios.add(video.getComentarios());
		chckbxVideoPrivado.setSelected(video.getPrivado());
	}
	
	public void cleanVideoData() {
		textFieldNombreVideo.removeAll();
		textFieldDescripcion.removeAll();
		textFieldFecha.removeAll();
		textFieldURL.removeAll();
		//treeComentarios.removeAll();
		chckbxVideoPrivado.removeAll();
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
		userLoaded = true;

	}
}

package Presentacion;

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
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ConsultaUsuario extends JInternalFrame {
	private IControlador controller;
	private JLabel lblUsuarios;
	private JComboBox<String> comboBoxUsuarios;
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
	private JComboBox<String> comboBoxVideosCanal;
	private JLabel lblVideos;
	private JComboBox<String> comboBoxListasCanal;
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
	private JScrollPane scrollPane;
	private JComboBox comboBoxSeguidos;
	private JComboBox comboBoxSeguidores;
	private JLabel lblSeguidores;
	private JLabel lblSeguidos;
	private JLabel lblLikes;
	private JComboBox comboBoxLikes;
	private JLabel lblDislikes;
	private JComboBox comboBoxDislikes;
	private JLabel lblVideosEnLista;
	private JComboBox comboBoxVideosEnLista;
	private JButton btnSalir;
	private JLabel lblCategoria;
	private JTextField textFieldCategoria;
	private JTextField textFieldNombreLista;
	private JLabel lblNombreLista;

	/**
	 * Create the frame.
	 */
	public ConsultaUsuario(IControlador ctrl) {
		setBounds(100, 100, 850, 700);
		controller = ctrl;
		setClosable(true);
		setTitle("Consulta Usuario");
		getContentPane().setLayout(null);

		comboBoxUsuarios = new JComboBox<String>();
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
		panelUsuario.setBounds(56, 72, 743, 198);
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

		comboBoxSeguidos = new JComboBox();
		comboBoxSeguidos.setBounds(569, 33, 132, 20);
		panelUsuario.add(comboBoxSeguidos);

		comboBoxSeguidores = new JComboBox();
		comboBoxSeguidores.setBounds(569, 108, 132, 20);
		panelUsuario.add(comboBoxSeguidores);

		lblSeguidores = new JLabel("Seguidores");
		lblSeguidores.setBounds(569, 86, 97, 14);
		panelUsuario.add(lblSeguidores);

		lblSeguidos = new JLabel("Seguidos");
		lblSeguidos.setBounds(569, 11, 97, 14);
		panelUsuario.add(lblSeguidos);

		comboBoxVideosCanal = new JComboBox<String>();

		comboBoxVideosCanal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cleanVideoData();
				if (comboBoxVideosCanal.getSelectedItem() != null
						&& comboBoxVideosCanal.getSelectedItem().toString().equals(" ")) {
					cleanVideoData();
					comboBoxListasCanal.setEnabled(true);
				}
				else
				comboBoxListasCanal.setEnabled(false);
				if (userLoaded && comboBoxVideosCanal.getSelectedItem() != null
						&& !comboBoxVideosCanal.getSelectedItem().toString().equals(" ")) {
					DtVideo video = controller.seleccionarVideo(comboBoxVideosCanal.getSelectedItem().toString());
					fillVideoData(video);
				}
			}
		});
		comboBoxVideosCanal.setEnabled(true);
		comboBoxVideosCanal.setBounds(155, 281, 166, 20);
		getContentPane().add(comboBoxVideosCanal);

		lblVideos = new JLabel("Videos");
		lblVideos.setBounds(56, 281, 81, 14);
		getContentPane().add(lblVideos);

		comboBoxListasCanal = new JComboBox<String>();
		comboBoxListasCanal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBoxListasCanal.getSelectedItem() != null
						&& comboBoxListasCanal.getSelectedItem().toString().equals(" ")) {
					cleanListData();
					comboBoxVideosCanal.setEnabled(true);
					comboBoxVideosEnLista.setEnabled(false);
					comboBoxVideosEnLista.setVisible(false);
					lblVideosEnLista.setVisible(false);
				} else {
					if (userLoaded && comboBoxListasCanal.getSelectedItem() != null
							&& !comboBoxListasCanal.getSelectedItem().toString().equals("")) {
						comboBoxVideosCanal.setEnabled(false);
						comboBoxVideosEnLista.setEnabled(true);
						comboBoxVideosEnLista.setVisible(true);
						lblCategoria.setVisible(true);
						textFieldCategoria.setVisible(true);
						lblVideosEnLista.setVisible(true);
						lblNombreLista.setVisible(true);
						textFieldNombreLista.setVisible(true);
						fillListData();
					}
				}
			}
		});
		comboBoxListasCanal.setEnabled(true);
		comboBoxListasCanal.setBounds(437, 281, 189, 20);
		getContentPane().add(comboBoxListasCanal);

		lblListas = new JLabel("Listas");
		lblListas.setBounds(350, 281, 46, 14);
		getContentPane().add(lblListas);

		treeComentarios = null;

		lblComentarios = new JLabel("Comentarios");
		lblComentarios.setBounds(374, 312, 137, 14);
		lblComentarios.setVisible(false);
		getContentPane().add(lblComentarios);

		lblNombreVideo = new JLabel("Nombre Video");
		lblNombreVideo.setBounds(10, 368, 113, 14);
		lblNombreVideo.setVisible(false);
		getContentPane().add(lblNombreVideo);

		textFieldNombreVideo = new JTextField();
		textFieldNombreVideo.setEditable(false);
		textFieldNombreVideo.setBounds(133, 365, 221, 20);
		textFieldNombreVideo.setVisible(false);
		getContentPane().add(textFieldNombreVideo);
		textFieldNombreVideo.setColumns(10);

		lblDuracion = new JLabel("Duracion");
		lblDuracion.setBounds(10, 445, 107, 14);
		lblDuracion.setVisible(false);
		getContentPane().add(lblDuracion);

		lblDescripcionVideo = new JLabel("Descripcion");
		lblDescripcionVideo.setBounds(10, 615, 121, 14);
		lblDescripcionVideo.setVisible(false);
		getContentPane().add(lblDescripcionVideo);

		textAreaDescripcionVideo = new JTextArea();
		textAreaDescripcionVideo.setEditable(false);
		textAreaDescripcionVideo.setVisible(false);
		textAreaDescripcionVideo.setBounds(133, 618, 221, 35);
		getContentPane().add(textAreaDescripcionVideo);

		textFieldDuracionVideo = new JTextField();
		textFieldDuracionVideo.setEditable(false);
		textFieldDuracionVideo.setVisible(false);
		textFieldDuracionVideo.setBounds(133, 442, 97, 20);
		getContentPane().add(textFieldDuracionVideo);
		textFieldDuracionVideo.setColumns(10);

		lblFechaPublicado = new JLabel("Fecha publicado");
		lblFechaPublicado.setBounds(10, 481, 113, 14);
		lblFechaPublicado.setVisible(false);
		getContentPane().add(lblFechaPublicado);

		textFieldFechaPub = new JTextField();
		textFieldFechaPub.setEditable(false);
		textFieldFechaPub.setVisible(false);
		textFieldFechaPub.setBounds(133, 478, 97, 20);
		getContentPane().add(textFieldFechaPub);
		textFieldFechaPub.setColumns(10);

		chckbxVideoPrivado = new JCheckBox("Privado");
		chckbxVideoPrivado.setEnabled(false);
		chckbxVideoPrivado.setVisible(false);
		chckbxVideoPrivado.setBounds(262, 401, 97, 23);
		getContentPane().add(chckbxVideoPrivado);

		lblUrl = new JLabel("URL");
		lblUrl.setBounds(10, 518, 46, 14);
		lblUrl.setVisible(false);
		getContentPane().add(lblUrl);

		textFieldURL = new JTextField();
		textFieldURL.setEditable(false);
		textFieldURL.setVisible(false);
		textFieldURL.setBounds(133, 515, 221, 20);
		getContentPane().add(textFieldURL);
		textFieldURL.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(384, 337, 415, 291);
		scrollPane.setVisible(false);
		getContentPane().add(scrollPane);

		lblLikes = new JLabel("Likes");
		lblLikes.setBounds(10, 553, 46, 14);
		lblLikes.setVisible(false);
		getContentPane().add(lblLikes);

		comboBoxLikes = new JComboBox();
		comboBoxLikes.setBounds(133, 550, 221, 20);
		comboBoxLikes.setVisible(false);
		getContentPane().add(comboBoxLikes);

		lblDislikes = new JLabel("Dislikes");
		lblDislikes.setBounds(10, 590, 46, 14);
		lblDislikes.setVisible(false);
		getContentPane().add(lblDislikes);

		comboBoxDislikes = new JComboBox();
		comboBoxDislikes.setBounds(133, 587, 221, 20);
		comboBoxDislikes.setVisible(false);
		getContentPane().add(comboBoxDislikes);

		lblVideosEnLista = new JLabel("Videos en lista");
		lblVideosEnLista.setBounds(10, 325, 121, 14);
		lblVideosEnLista.setVisible(false);
		getContentPane().add(lblVideosEnLista);

		comboBoxVideosEnLista = new JComboBox();
		comboBoxVideosEnLista.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});
		comboBoxVideosEnLista.setBounds(155, 322, 166, 20);
		comboBoxVideosEnLista.setVisible(false);
		getContentPane().add(comboBoxVideosEnLista);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finCasoUso();
				cleanOutputData();
				cleanVideoData();
				dispose();
			}
		});
		btnSalir.setBounds(710, 10, 89, 23);
		getContentPane().add(btnSalir);
		
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(10, 405, 107, 14);
		lblCategoria.setVisible(false);
		getContentPane().add(lblCategoria);
		
		textFieldCategoria = new JTextField();
		textFieldCategoria.setEditable(false);
		textFieldCategoria.setBounds(133, 402, 113, 20);
		textFieldCategoria.setVisible(false);
		getContentPane().add(textFieldCategoria);
		textFieldCategoria.setColumns(10);
		
		lblNombreLista = new JLabel("Nombre Lista");
		lblNombreLista.setBounds(10, 368, 113, 14);
		lblNombreLista.setVisible(false);
		getContentPane().add(lblNombreLista);
		
		textFieldNombreLista = new JTextField();
		textFieldNombreLista.setEditable(false);
		textFieldNombreLista.setBounds(133, 365, 221, 20);
		textFieldNombreLista.setVisible(false);
		getContentPane().add(textFieldNombreLista);
		textFieldNombreLista.setColumns(10);

	}
	
	public void cleanListData() {
		textFieldNombreLista.removeAll();
		textFieldCategoria.removeAll();
		lblCategoria.setVisible(false);
		textFieldCategoria.setVisible(false);
		chckbxPrivado.setSelected(false);
		chckbxPrivado.setVisible(false);
		lblNombreLista.setVisible(false);
		textFieldNombreLista.setVisible(false);
		comboBoxVideosEnLista.removeAllItems();
	}
	
	public void fillListData() {
		DtLista lst = controller.seleccionarLista(comboBoxListasCanal.getSelectedItem().toString());	
		
		ArrayList<DtVideo> videos = controller.videosEnLista(lst);
		for (DtVideo dtVideo : videos) {
			comboBoxVideosEnLista.addItem(dtVideo.getNombre());
		}
		textFieldCategoria.setText(lst.getCategoria());
		textFieldNombreLista.setText(lst.getNombre());
		chckbxVideoPrivado.setSelected(lst.isPrivado());
	}
	

	public void fillUsers() {
		comboBoxUsuarios.removeAllItems();
		comboBoxUsuarios.addItem(" ");
		ArrayList<String> users = controller.listarUsuarios();
		for (String s : users) {
			comboBoxUsuarios.addItem(s);
		}
	}

	public void fillVideoData(DtVideo video) {
		textFieldNombreVideo.setVisible(true);
		textAreaDescripcionVideo.setVisible(true);
		textFieldFechaPub.setVisible(true);
		textFieldURL.setVisible(true);
		textFieldDuracionVideo.setVisible(true);
		chckbxVideoPrivado.setVisible(true);
		lblComentarios.setVisible(true);
		lblDescripcionVideo.setVisible(true);
		lblNombreVideo.setVisible(true);
		lblFechaPublicado.setVisible(true);
		lblUrl.setVisible(true);
		lblDuracion.setVisible(true);
		lblLikes.setVisible(true);
		comboBoxLikes.setVisible(true);
		lblDislikes.setVisible(true);
		comboBoxDislikes.setVisible(true);
		textFieldCategoria.setVisible(true);
		lblCategoria.setVisible(true);
		
		if (video.getCategoria() != null) textFieldCategoria.setText(video.getCategoria());
		textFieldNombreVideo.setText(video.getNombre());
		textAreaDescripcionVideo.setText(video.getDescripcion());
		textFieldDuracionVideo.setText(video.getDuracion().toString());
		textFieldFechaPub.setText(video.getFechaPub().toString());
		textFieldURL.setText(video.getUrl());
		if (video.getComentarios() != null) {
			treeComentarios = video.getComentarios();
			treeComentarios.setBounds(374, 332, 415, 291);
			getContentPane().add(treeComentarios);
		}
		chckbxVideoPrivado.setSelected(video.getPrivado());

		if (video.getValoracionesPositivas() != null) {
			for (String like : video.getValoracionesPositivas()) {
				comboBoxLikes.addItem(like);
			}
		}
		if (video.getValoracionesNegativas() != null) {
			for (String dislike : video.getValoracionesNegativas()) {
				comboBoxDislikes.addItem(dislike);
			}
		}

	}

	public void cleanVideoData() {
		textFieldNombreVideo.removeAll();
		textFieldDescripcion.removeAll();
		textFieldURL.removeAll();
		if (treeComentarios != null) {
			treeComentarios.removeAll();
			getContentPane().remove(treeComentarios);
			scrollPane.setVisible(false);
		}
		chckbxVideoPrivado.removeAll();
		textFieldNombreVideo.setVisible(false);
		textAreaDescripcionVideo.setVisible(false);
		textFieldFechaPub.setVisible(false);
		textFieldURL.setVisible(false);
		textFieldDuracionVideo.setVisible(false);
		textFieldFechaPub.setVisible(false);
		chckbxVideoPrivado.setVisible(false);
		lblComentarios.setVisible(false);
		lblFechaPublicado.setVisible(false);
		lblDescripcionVideo.setVisible(false);
		lblNombreVideo.setVisible(false);
		lblUrl.setVisible(false);
		lblDuracion.setVisible(false);
		lblLikes.setVisible(false);
		comboBoxLikes.setVisible(false);
		lblDislikes.setVisible(false);
		comboBoxDislikes.setVisible(false);
		textFieldCategoria.setVisible(false);
		lblCategoria.setVisible(false);
		treeComentarios = null;
		comboBoxLikes.removeAllItems();
		comboBoxDislikes.removeAllItems();

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
		textFieldDescripcion.removeAll();
		chckbxPrivado.removeAll();
		lblImage.removeAll();
		comboBoxVideosCanal.removeAllItems();
		comboBoxListasCanal.removeAllItems();
		comboBoxSeguidores.removeAllItems();
		comboBoxSeguidos.removeAllItems();
		finCasoUso();
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

		Map<String, DtUsuario> seguidores = user.getSeguidores();
		if (seguidores != null) {
			Iterator<Entry<String, DtUsuario>> it = seguidores.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, DtUsuario> entry = it.next();
				comboBoxSeguidores.addItem(entry.getKey());
			}
		}

		Map<String, DtUsuario> seguidos = user.getSeguidos();
		if (seguidos != null) {
			Iterator<Entry<String, DtUsuario>> it2 = seguidos.entrySet().iterator();
			while (it2.hasNext()) {
				Entry<String, DtUsuario> entry2 = it2.next();
				comboBoxSeguidos.addItem(entry2.getKey());
			}
		}

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

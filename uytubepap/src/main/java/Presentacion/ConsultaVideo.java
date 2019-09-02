package Presentacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JInternalFrame;

import interfaces.IControlador;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import datatypes.DtCanal;
import datatypes.DtUsuario;
import datatypes.DtVideo;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ConsultaVideo extends JInternalFrame {
	private IControlador controller;
	private JLabel lblUsuarios;
	private JComboBox<String> comboBoxUsuarios;
	private JComboBox<String> comboBoxVideosCanal;
	private JLabel lblVideos;
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
	private JLabel lblLikes;
	private JComboBox<String> comboBoxLikes;
	private JLabel lblDislikes;
	private JComboBox<String> comboBoxDislikes;
	/**
	 * Create the frame.
	 */
	public ConsultaVideo(IControlador ctrl) {
		setBounds(100, 100, 850, 700);
		controller = ctrl;
		setClosable(true);
		setTitle("Consulta Video");
		getContentPane().setLayout(null);

		comboBoxUsuarios = new JComboBox<String>();
		comboBoxUsuarios.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cleanOutputData();
				if(comboBoxUsuarios.getSelectedIndex() > -1) {
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
			}
		});
		comboBoxUsuarios.setBounds(176, 11, 220, 20);
		getContentPane().add(comboBoxUsuarios);

		lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(56, 14, 75, 14);
		getContentPane().add(lblUsuarios);

		comboBoxVideosCanal = new JComboBox<String>();

		comboBoxVideosCanal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cleanVideoData();
				if (userLoaded && comboBoxVideosCanal.getSelectedItem() != null
						&& !comboBoxVideosCanal.getSelectedItem().toString().equals(" ")) {
					DtVideo video = controller.seleccionarVideo(comboBoxVideosCanal.getSelectedItem().toString());
					fillVideoData(video);
				}
			}
		});
		comboBoxVideosCanal.setBounds(176, 44, 220, 20);
		getContentPane().add(comboBoxVideosCanal);

		lblVideos = new JLabel("Videos");
		lblVideos.setBounds(56, 47, 81, 14);
		getContentPane().add(lblVideos);

		treeComentarios = null;

		lblComentarios = new JLabel("Comentarios");
		lblComentarios.setBounds(450, 14, 100, 14);
		lblComentarios.setVisible(false);
		getContentPane().add(lblComentarios);

		lblNombreVideo = new JLabel("Nombre Video");
		lblNombreVideo.setVisible(false);
		lblNombreVideo.setBounds(56, 80, 81, 14);
		getContentPane().add(lblNombreVideo);

		textFieldNombreVideo = new JTextField();
		textFieldNombreVideo.setBounds(176, 80, 220, 20);
		textFieldNombreVideo.setEditable(false);
		textFieldNombreVideo.setVisible(false);
		getContentPane().add(textFieldNombreVideo);
		textFieldNombreVideo.setColumns(10);

		lblDuracion = new JLabel("Duracion");
		lblDuracion.setVisible(false);
		lblDuracion.setBounds(56, 146, 81, 14);
		getContentPane().add(lblDuracion);

		lblDescripcionVideo = new JLabel("Descripcion");
		lblDescripcionVideo.setVisible(false);
		lblDescripcionVideo.setBounds(56, 179, 81, 14);
		getContentPane().add(lblDescripcionVideo);

		textAreaDescripcionVideo = new JTextArea();
		textAreaDescripcionVideo.setBounds(176, 179, 220, 20);
		textAreaDescripcionVideo.setEditable(false);
		textAreaDescripcionVideo.setVisible(false);
		getContentPane().add(textAreaDescripcionVideo);

		textFieldDuracionVideo = new JTextField();
		textFieldDuracionVideo.setBounds(176, 146, 220, 20);
		textFieldDuracionVideo.setEditable(false);
		textFieldDuracionVideo.setVisible(false);
		getContentPane().add(textFieldDuracionVideo);
		textFieldDuracionVideo.setColumns(10);

		lblFechaPublicado = new JLabel("Fecha publicado");
		lblFechaPublicado.setBounds(56, 212, 81, 14);
		lblFechaPublicado.setVisible(false);
		getContentPane().add(lblFechaPublicado);

		textFieldFechaPub = new JTextField();
		textFieldFechaPub.setEditable(false);
		textFieldFechaPub.setVisible(false);
		textFieldFechaPub.setBounds(176, 212, 220, 20);
		getContentPane().add(textFieldFechaPub);
		textFieldFechaPub.setColumns(10);

		chckbxVideoPrivado = new JCheckBox("Privado");
		chckbxVideoPrivado.setEnabled(false);
		chckbxVideoPrivado.setVisible(false);
		chckbxVideoPrivado.setBounds(50, 311, 81, 14);
		getContentPane().add(chckbxVideoPrivado);

		lblUrl = new JLabel("URL");
		lblUrl.setVisible(false);
		lblUrl.setBounds(56, 113, 81, 14);
		getContentPane().add(lblUrl);

		textFieldURL = new JTextField();
		textFieldURL.setEditable(false);
		textFieldURL.setVisible(false);
		textFieldURL.setBounds(176, 113, 220, 20);
		getContentPane().add(textFieldURL);
		textFieldURL.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(384, 337, 415, 291);
		scrollPane.setVisible(false);
		getContentPane().add(scrollPane);

		lblLikes = new JLabel("Likes");
		lblLikes.setBounds(56, 245, 81, 14);
		lblLikes.setVisible(false);
		getContentPane().add(lblLikes);

		comboBoxLikes = new JComboBox<String>();
		comboBoxLikes.setBounds(176, 245, 220, 20);
		comboBoxLikes.setVisible(false);
		getContentPane().add(comboBoxLikes);

		lblDislikes = new JLabel("Dislikes");
		lblDislikes.setBounds(56, 278, 81, 14);
		lblDislikes.setVisible(false);
		getContentPane().add(lblDislikes);

		comboBoxDislikes = new JComboBox<String>();
		comboBoxDislikes.setBounds(176, 278, 220, 20);
		comboBoxDislikes.setVisible(false);
		getContentPane().add(comboBoxDislikes);

	}

	public void fillUsers() {
		comboBoxUsuarios.addItem(" ");
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
		textFieldURL.setVisible(true);
		textFieldDuracionVideo.setVisible(true);
		textFieldFechaPub.setVisible(true);
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

		textFieldNombreVideo.setText(video.getNombre());
		textAreaDescripcionVideo.setText(video.getDescripcion());
		textFieldDuracionVideo.setText(video.getDuracion().toString());
		textFieldFechaPub.setText(video.getFechaPub().toString());
		textFieldURL.setText(video.getUrl());
		if (video.getComentarios() != null) {
			treeComentarios = video.getComentarios();
			treeComentarios.setBounds(450, 47, 350, 315);
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
		textFieldURL.removeAll();
		if (treeComentarios != null) {
			treeComentarios.removeAll();
			scrollPane.setVisible(false);
		}
		chckbxVideoPrivado.removeAll();
		textFieldNombreVideo.setVisible(false);
		textAreaDescripcionVideo.setVisible(false);
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
	}

	public void finCasoUso() {
		controller.finCasoUso();
	}

	public void cleanOutputData() {
		comboBoxVideosCanal.removeAllItems();
		finCasoUso();
	}

	public void fillDataUser(DtUsuario user, DtCanal canal) {
		ArrayList<String> videos = controller.listarVideos();
		comboBoxVideosCanal.removeAllItems();
		comboBoxVideosCanal.addItem(" ");
		if (videos != null) {
			for (String v : videos) {
				comboBoxVideosCanal.addItem(v);
			}
		}
		userLoaded = true;
	}
	
	public void desdeLista(String usuario, String video) {
		comboBoxUsuarios.addItem(usuario);
		comboBoxVideosCanal.removeAllItems();
		comboBoxVideosCanal.addItem(video);
	}
	
}

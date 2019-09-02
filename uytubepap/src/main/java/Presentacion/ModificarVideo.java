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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ModificarVideo extends JInternalFrame {
	private IControlador controller;
	private JLabel lblUsuarios;
	private JComboBox<String> comboBoxUsuarios;
	private JComboBox<String> comboBoxVideosCanal;
	private JLabel lblVideos;
	//Datos Del Video
	private JTextField textFieldNombreVideo;
	private JTextField textFieldDuracionVideo;
	private JTextField textFieldFechaPub;
	private JTextField textFieldURL;
	private JLabel lblNombreVideo;
	private JLabel lblDuracion;
	private JLabel lblDescripcionVideo;
	private JTextArea textAreaDescripcionVideo;
	//Nuevos Datos
	private JTextField textFieldNombreVideo2;
	private JTextField textFieldDuracionVideo2;
	private JTextField textFieldURL2;
	private JLabel lblNombreVideo2;
	private JLabel lblDuracion2;
	private JLabel lblDescripcionVideo2;
	private JTextArea textAreaDescripcionVideo2;
	//
	private JLabel lblFechaPublicado;
	private JCheckBox chckbxVideoPrivado;
	private JCheckBox chckbxVideoPrivado2;
	private JLabel lblUrl;
	private JLabel lblUrl2;
	private boolean userLoaded = false;
	private JButton btnModificar;
	private JLabel lblCategoria;
	private JTextField textFieldCategoria;
	private JLabel lblCategoria2;
	private JComboBox<String> comboBoxCategoria2;
	private Object cat;
	private JLabel lblNewFields;
	/**
	 * Create the frame.
	 */
	public ModificarVideo(IControlador ctrl) {
		setBounds(100, 100, 850, 700);
		controller = ctrl;
		setClosable(true);
		setTitle("Modificar Video");
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

		//Datos Originales del Video
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
		
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setVisible(false);
		lblCategoria.setBounds(56, 245, 81, 14);
		getContentPane().add(lblCategoria);

		textFieldCategoria= new JTextField();
		textFieldCategoria.setEditable(false);
		textFieldCategoria.setVisible(false);
		textFieldCategoria.setBounds(176, 245, 220, 20);
		getContentPane().add(textFieldCategoria);
		textFieldCategoria.setColumns(10);

		//A Modificar
		lblNombreVideo2 = new JLabel("Nombre Video");
		lblNombreVideo2.setVisible(false);
		lblNombreVideo2.setBounds(456, 80, 81, 14);
		getContentPane().add(lblNombreVideo);

		textFieldNombreVideo2 = new JTextField();
		textFieldNombreVideo2.setBounds(576, 80, 220, 20);
		textFieldNombreVideo2.setVisible(false);
		getContentPane().add(textFieldNombreVideo2);
		textFieldNombreVideo2.setColumns(10);

		lblDuracion2 = new JLabel("Duracion");
		lblDuracion2.setVisible(false);
		lblDuracion2.setBounds(456, 146, 81, 14);
		getContentPane().add(lblDuracion2);

		lblDescripcionVideo2 = new JLabel("Descripcion");
		lblDescripcionVideo2.setVisible(false);
		lblDescripcionVideo2.setBounds(456, 179, 81, 14);
		getContentPane().add(lblDescripcionVideo2);

		textAreaDescripcionVideo2 = new JTextArea();
		textAreaDescripcionVideo2.setBounds(576, 179, 220, 20);
		textAreaDescripcionVideo2.setVisible(false);
		getContentPane().add(textAreaDescripcionVideo2);

		textFieldDuracionVideo2 = new JTextField();
		textFieldDuracionVideo2.setBounds(576, 146, 220, 20);
		textFieldDuracionVideo2.setVisible(false);
		getContentPane().add(textFieldDuracionVideo2);
		textFieldDuracionVideo2.setColumns(10);

		chckbxVideoPrivado2 = new JCheckBox("Privado");
		chckbxVideoPrivado2.setVisible(false);
		chckbxVideoPrivado2.setBounds(450, 311, 81, 14);
		getContentPane().add(chckbxVideoPrivado2);

		lblUrl2 = new JLabel("URL");
		lblUrl2.setVisible(false);
		lblUrl2.setBounds(456, 113, 81, 14);
		getContentPane().add(lblUrl);

		textFieldURL2 = new JTextField();
		textFieldURL2.setVisible(false);
		textFieldURL2.setBounds(576, 113, 220, 20);
		getContentPane().add(textFieldURL2);
		textFieldURL2.setColumns(10);
		
		lblCategoria2 = new JLabel("Categoria");
		lblCategoria2.setVisible(false);
		lblCategoria2.setBounds(456, 245, 81, 14);
		getContentPane().add(lblCategoria2);

		comboBoxCategoria2 = new JComboBox<String>();
		comboBoxCategoria2.setVisible(false);
		comboBoxCategoria2.setBounds(576, 245, 220, 20);
		getContentPane().add(comboBoxCategoria2);
		
		lblNewFields = new JLabel("Nuevos Campos");
		lblNewFields.setVisible(false);
		lblNewFields.setBounds(456, 47, 150, 14);
		getContentPane().add(lblNewFields);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(340, 400, 120, 30);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
			}
		});
		getContentPane().add(btnModificar);
	}

	public void fillUsers() {
		System.out.print("ENTROOOO");
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
		lblDescripcionVideo.setVisible(true);
		lblNombreVideo.setVisible(true);
		lblFechaPublicado.setVisible(true);
		lblUrl.setVisible(true);
		lblDuracion.setVisible(true);
		lblCategoria.setVisible(true);
		textFieldCategoria.setVisible(true);
		lblNewFields.setVisible(true);
		
		textFieldNombreVideo2.setVisible(true);
		textAreaDescripcionVideo2.setVisible(true);
		textFieldURL2.setVisible(true);
		textFieldDuracionVideo2.setVisible(true);
		chckbxVideoPrivado2.setVisible(true);
		lblDescripcionVideo2.setVisible(true);
		lblNombreVideo2.setVisible(true);
		lblUrl2.setVisible(true);
		lblDuracion2.setVisible(true);
		lblCategoria2.setVisible(true);
		comboBoxCategoria2.setVisible(true);

		textFieldNombreVideo.setText(video.getNombre());
		textAreaDescripcionVideo.setText(video.getDescripcion());
		textFieldDuracionVideo.setText(video.getDuracion().toString());
		textFieldFechaPub.setText(video.getFechaPub().toString());
		textFieldURL.setText(video.getUrl());
		textFieldCategoria.setText(video.getCategoria()); //TODO Fix getcategoria
		chckbxVideoPrivado.setSelected(video.getPrivado());
	}

	public void cleanVideoData() {
		textFieldNombreVideo.removeAll();
		textFieldURL.removeAll();
		chckbxVideoPrivado.removeAll();
		textFieldNombreVideo.setVisible(false);
		textAreaDescripcionVideo.setVisible(false);
		textFieldURL.setVisible(false);
		textFieldDuracionVideo.setVisible(false);
		textFieldFechaPub.setVisible(false);
		chckbxVideoPrivado.setVisible(false);
		lblFechaPublicado.setVisible(false);
		lblDescripcionVideo.setVisible(false);
		lblNombreVideo.setVisible(false);
		lblUrl.setVisible(false);
		lblDuracion.setVisible(false);
		lblCategoria.setVisible(false);
		textFieldCategoria.setVisible(false);
		lblNewFields.setVisible(false);
		
		textFieldNombreVideo2.removeAll();
		textFieldURL2.removeAll();
		chckbxVideoPrivado2.removeAll();
		textFieldNombreVideo2.setVisible(false);
		textAreaDescripcionVideo2.setVisible(false);
		textFieldURL2.setVisible(false);
		textFieldDuracionVideo2.setVisible(false);
		chckbxVideoPrivado2.setVisible(false);
		lblDescripcionVideo2.setVisible(false);
		lblNombreVideo2.setVisible(false);
		lblUrl2.setVisible(false);
		lblDuracion2.setVisible(false);
		lblCategoria2.setVisible(false);
		comboBoxCategoria2.setVisible(false);
	}

	public void finCasoUso() {
		controller.finCasoUso();
	}

	public void cleanOutputData() {
		comboBoxVideosCanal.removeAllItems();
		finCasoUso();
	}

	public void modificar() {
		String vid = comboBoxVideosCanal.getSelectedItem().toString();
		DtVideo video = controller.seleccionarVideo(vid);
		if (comboBoxCategoria2.getSelectedItem().toString() != "") {
			video.setCategoria(comboBoxCategoria2.getSelectedItem().toString());
		}
		if (textAreaDescripcionVideo2.getText() != "") {
			video.setDescripcion(textAreaDescripcionVideo2.getText());
		}
		if (textFieldDuracionVideo2.getText() != ""){
			video.setDuracion(Integer.getInteger(textFieldDuracionVideo2.getText()));
		}
		if (textFieldNombreVideo2.getText() != "") {
			video.setNombre(textFieldNombreVideo2.getText());
		}
		if (chckbxVideoPrivado2.isSelected() != chckbxVideoPrivado2.isSelected()) {
			video.setPrivado(chckbxVideoPrivado2.isSelected());
		}
		if (textFieldURL2.getText() != "") {
			video.setUrl(textFieldURL2.getText());
		}
		controller.editarVideo(video);
	}
	
	public void fillCategories() {
		comboBoxCategoria2.removeAllItems();
		comboBoxCategoria2.addItem(" ");
		ArrayList<String> categorias = controller.listarCategorias();
		for (String c : categorias) {
			comboBoxCategoria2.addItem(c);
		}
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
}

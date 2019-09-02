package Presentacion;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JInternalFrame;

import interfaces.IControlador;
import logica.Usuario;
import net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor.ForAttachment;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import datatypes.DtCanal;
import datatypes.DtLista;
import datatypes.DtUsuario;
import datatypes.DtVideo;

import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.awt.event.ItemEvent;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class ModificarDatosUsuario extends JInternalFrame {

	private IControlador controller;

	private JLabel lblUsuarios;
	private JComboBox comboBoxUsuarios;
	private JTabbedPane tabbedPane;
	private JPanel panelUsuarios;
	private JPanel panelListas;
	private JPanel panelVideo;
	private JLabel lblNombre;
	private JTextField textFieldNombre;
	private JLabel lblNickname;
	private JTextField textFieldNick;
	private JLabel lblApellido;
	private JTextField textFieldApellido;
	private JLabel lblCorreo;
	private JTextField textFieldCorreo;
	private JLabel lblFechaNacimiento;
	private JLabel lblImagen;
	private JButton btnSubirOtraImagen;
	private JLabel lblCanal;
	private JTextField textFieldNombreCanal;
	private JLabel lblDescripcion;
	private JButton btnModificarUsuario;
	private JButton btnSalir;
	private JLabel lblNombreLista;
	private JTextField textFieldNombreLista;
	private JLabel lblCategoriaLista;
	private JTextField textFieldCategoriaLista;
	private JLabel lblNombreVideo;
	private JTextField textFieldNombreVideo;
	private JLabel lblDescripcionVideo;
	private JLabel lblDuracion;
	private JTextArea textAreaDescripcionVideo;
	private JTextField textFieldDuracion;
	private JLabel lblFechaPublicacion;
	private JCheckBox chckbxPrivadoVideo;
	private JLabel lblUrl;
	private JTextField textFieldURL;
	private JLabel lblCategoriaVideo;
	private JTextArea textAreaDescripcion;
	private JTextField textFieldCategoriaVideo;
	private JCheckBox chckbxPrivado;
	private JLabel lblListas;
	private JComboBox comboBoxListas;
	private JLabel lblVideos;
	private JComboBox comboBoxVideos;
	private Image imagen;
	private JCheckBox chckbxPrivadoLista;
	private boolean userLoaded = false;
	private JLabel lblCategoriaNueva;
	private JComboBox comboBoxCategoriaListaNueva;
	private JLabel lblCategoriaNueva_1;
	private JComboBox comboBoxCategoriaNuevaVideo;
	private JLabel lblFechaNueva;
	private JLabel lblFechaNacimientoNueva;
	private JCheckBox chckbxEditarFecha;
	private boolean editarFechaNac = false;
	private boolean editarFechaPub = false;
	private JCheckBox chckbxEditarFechaPub;
	private String imgPath;
	private boolean videoSeleccionado = false;
	private boolean listaSeleccionada = false;
	private DtLista lstAModificar;
	private DtVideo vidAModificar;
	private DtUsuario usuarioAModificar;
	private DtCanal canalAModificar;
	private JDateChooser dateChooserVieja;
	private JDateChooser dateChooserNueva;
	private JDateChooser dateChooserVideoNueva;
	private JDateChooser dateChooserVideoVieja;

	/**
	 * Create the frame.
	 */
	public ModificarDatosUsuario(IControlador ctrl) {

		controller = ctrl;
		setBounds(100, 100, 894, 698);
		setTitle("Modificar Usuario");
		getContentPane().setLayout(null);

		lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(26, 11, 65, 14);
		getContentPane().add(lblUsuarios);

		comboBoxUsuarios = new JComboBox();
		comboBoxUsuarios.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cleanData();
				cleanVideoData();
				cleanListData();
				userLoaded = false;
				if (comboBoxUsuarios.getSelectedItem() != null
						&& !comboBoxUsuarios.getSelectedItem().toString().equals(" ")) {
					UserSelected(comboBoxUsuarios.getSelectedItem().toString());
				}

			}
		});
		comboBoxUsuarios.setBounds(112, 8, 174, 20);
		getContentPane().add(comboBoxUsuarios);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 60, 800, 530);
		getContentPane().add(tabbedPane);

		panelUsuarios = new JPanel();
		tabbedPane.addTab("Usuario", null, panelUsuarios, null);
		panelUsuarios.setLayout(null);

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(43, 54, 58, 14);
		panelUsuarios.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(122, 51, 194, 20);
		panelUsuarios.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		lblNickname = new JLabel("NickName");
		lblNickname.setBounds(43, 29, 84, 14);
		panelUsuarios.add(lblNickname);

		textFieldNick = new JTextField();
		textFieldNick.setEditable(false);
		textFieldNick.setBounds(122, 26, 194, 20);
		panelUsuarios.add(textFieldNick);
		textFieldNick.setColumns(10);

		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(43, 79, 66, 14);
		panelUsuarios.add(lblApellido);

		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(122, 76, 194, 20);
		panelUsuarios.add(textFieldApellido);
		textFieldApellido.setColumns(10);

		lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(347, 29, 46, 14);
		panelUsuarios.add(lblCorreo);

		textFieldCorreo = new JTextField();
		textFieldCorreo.setEditable(false);
		textFieldCorreo.setBounds(489, 26, 184, 20);
		panelUsuarios.add(textFieldCorreo);
		textFieldCorreo.setColumns(10);

		lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(345, 57, 102, 14);
		panelUsuarios.add(lblFechaNacimiento);

		lblImagen = new JLabel("Imagen");
		lblImagen.setBounds(132, 107, 125, 115);
		panelUsuarios.add(lblImagen);

		btnSubirOtraImagen = new JButton("Subir otra imagen");
		btnSubirOtraImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SubirImagen();
			}
		});
		btnSubirOtraImagen.setBounds(326, 153, 150, 23);
		panelUsuarios.add(btnSubirOtraImagen);

		lblCanal = new JLabel("Canal");
		lblCanal.setBounds(54, 239, 50, 14);
		panelUsuarios.add(lblCanal);

		textFieldNombreCanal = new JTextField();
		textFieldNombreCanal.setBounds(136, 236, 194, 20);
		panelUsuarios.add(textFieldNombreCanal);
		textFieldNombreCanal.setColumns(10);

		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(54, 281, 102, 14);
		panelUsuarios.add(lblDescripcion);

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setBounds(136, 281, 194, 44);
		panelUsuarios.add(textAreaDescripcion);

		chckbxPrivado = new JCheckBox("Privado");
		chckbxPrivado.setBounds(412, 235, 97, 23);
		panelUsuarios.add(chckbxPrivado);

		lblListas = new JLabel("Listas");
		lblListas.setBounds(54, 366, 46, 14);
		panelUsuarios.add(lblListas);

		comboBoxListas = new JComboBox();
		comboBoxListas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cleanListData();
				if (userLoaded && comboBoxListas.getSelectedItem() != null
						&& !comboBoxListas.getSelectedItem().toString().equals(" ")) {
					fillDataLista(comboBoxListas.getSelectedItem().toString());
					listaSeleccionada = true;
				}

			}
		});
		comboBoxListas.setBounds(136, 363, 194, 20);
		panelUsuarios.add(comboBoxListas);

		lblVideos = new JLabel("Videos");
		lblVideos.setBounds(373, 366, 46, 14);
		panelUsuarios.add(lblVideos);

		comboBoxVideos = new JComboBox();
		comboBoxVideos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cleanVideoData();
				if (userLoaded && comboBoxVideos.getSelectedItem() != null
						&& !comboBoxVideos.getSelectedItem().toString().equals(" ")) {
					fillDataVideo(comboBoxVideos.getSelectedItem().toString());
					videoSeleccionado = true;
				}
			}
		});
		comboBoxVideos.setBounds(457, 363, 194, 20);
		panelUsuarios.add(comboBoxVideos);

		lblFechaNacimientoNueva = new JLabel("Fecha Nacimiento Nueva");
		lblFechaNacimientoNueva.setBounds(347, 94, 143, 14);
		panelUsuarios.add(lblFechaNacimientoNueva);

		chckbxEditarFecha = new JCheckBox("Editar fecha");
		chckbxEditarFecha.setBounds(677, 50, 97, 23);
		panelUsuarios.add(chckbxEditarFecha);

		dateChooserVieja = new JDateChooser();
		dateChooserVieja.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		dateChooserVieja.setBounds(489, 54, 145, 20);
		panelUsuarios.add(dateChooserVieja);

		dateChooserNueva = new JDateChooser();
		dateChooserNueva.setBounds(489, 88, 145, 20);
		panelUsuarios.add(dateChooserNueva);

		panelListas = new JPanel();
		tabbedPane.addTab("Listas", null, panelListas, null);
		panelListas.setVisible(false);
		panelListas.setLayout(null);

		lblNombreLista = new JLabel("Nombre Lista");
		lblNombreLista.setBounds(115, 84, 96, 14);
		panelListas.add(lblNombreLista);

		textFieldNombreLista = new JTextField();
		textFieldNombreLista.setBounds(305, 81, 218, 20);
		panelListas.add(textFieldNombreLista);
		textFieldNombreLista.setColumns(10);

		lblCategoriaLista = new JLabel("Categoria");
		lblCategoriaLista.setBounds(115, 158, 96, 14);
		panelListas.add(lblCategoriaLista);

		textFieldCategoriaLista = new JTextField();
		textFieldCategoriaLista.setEditable(false);
		textFieldCategoriaLista.setBounds(305, 155, 218, 20);
		panelListas.add(textFieldCategoriaLista);
		textFieldCategoriaLista.setColumns(10);

		chckbxPrivadoLista = new JCheckBox("Privado");
		chckbxPrivadoLista.setBounds(582, 80, 97, 23);
		panelListas.add(chckbxPrivadoLista);

		lblCategoriaNueva = new JLabel("Categoria nueva");
		lblCategoriaNueva.setBounds(115, 207, 154, 14);
		panelListas.add(lblCategoriaNueva);

		comboBoxCategoriaListaNueva = new JComboBox();
		comboBoxCategoriaListaNueva.setBounds(305, 204, 218, 20);
		panelListas.add(comboBoxCategoriaListaNueva);

		panelVideo = new JPanel();
		tabbedPane.addTab("Videos", null, panelVideo, null);
		panelVideo.setVisible(false);
		panelVideo.setLayout(null);

		lblNombreVideo = new JLabel("Nombre Video");
		lblNombreVideo.setBounds(89, 37, 113, 14);
		panelVideo.add(lblNombreVideo);

		textFieldNombreVideo = new JTextField();
		textFieldNombreVideo.setBounds(212, 34, 199, 20);
		panelVideo.add(textFieldNombreVideo);
		textFieldNombreVideo.setColumns(10);

		lblDescripcionVideo = new JLabel("Descripcion");
		lblDescripcionVideo.setBounds(89, 88, 113, 14);
		panelVideo.add(lblDescripcionVideo);

		lblDuracion = new JLabel("Duracion");
		lblDuracion.setBounds(89, 141, 63, 14);
		panelVideo.add(lblDuracion);

		textAreaDescripcionVideo = new JTextArea();
		textAreaDescripcionVideo.setBounds(212, 83, 199, 42);
		panelVideo.add(textAreaDescripcionVideo);

		textFieldDuracion = new JTextField();
		textFieldDuracion.setBounds(212, 138, 50, 20);
		panelVideo.add(textFieldDuracion);
		textFieldDuracion.setColumns(10);

		lblFechaPublicacion = new JLabel("Fecha publicacion");
		lblFechaPublicacion.setBounds(421, 37, 113, 14);
		panelVideo.add(lblFechaPublicacion);

		chckbxPrivadoVideo = new JCheckBox("Privado");
		chckbxPrivadoVideo.setBounds(421, 137, 97, 23);
		panelVideo.add(chckbxPrivadoVideo);

		lblUrl = new JLabel("URL");
		lblUrl.setBounds(89, 188, 46, 14);
		panelVideo.add(lblUrl);

		textFieldURL = new JTextField();
		textFieldURL.setBounds(212, 185, 199, 20);
		panelVideo.add(textFieldURL);
		textFieldURL.setColumns(10);

		lblCategoriaVideo = new JLabel("Categoria");
		lblCategoriaVideo.setBounds(89, 236, 86, 14);
		panelVideo.add(lblCategoriaVideo);

		textFieldCategoriaVideo = new JTextField();
		textFieldCategoriaVideo.setEditable(false);
		textFieldCategoriaVideo.setBounds(212, 236, 199, 20);
		panelVideo.add(textFieldCategoriaVideo);
		textFieldCategoriaVideo.setColumns(10);

		lblCategoriaNueva_1 = new JLabel("Categoria Nueva");
		lblCategoriaNueva_1.setBounds(89, 282, 113, 14);
		panelVideo.add(lblCategoriaNueva_1);

		comboBoxCategoriaNuevaVideo = new JComboBox();
		comboBoxCategoriaNuevaVideo.setBounds(212, 279, 199, 20);
		panelVideo.add(comboBoxCategoriaNuevaVideo);

		lblFechaNueva = new JLabel("Fecha nueva");
		lblFechaNueva.setBounds(423, 62, 86, 14);
		panelVideo.add(lblFechaNueva);

		chckbxEditarFechaPub = new JCheckBox("Editar Fecha");
		chckbxEditarFechaPub.setBounds(552, 102, 97, 23);
		panelVideo.add(chckbxEditarFechaPub);

		dateChooserVideoVieja = new JDateChooser();
		dateChooserVideoVieja.setBounds(552, 31, 169, 20);
		panelVideo.add(dateChooserVideoVieja);

		dateChooserVideoNueva = new JDateChooser();
		dateChooserVideoNueva.setBounds(552, 62, 169, 20);
		panelVideo.add(dateChooserVideoNueva);

		btnSalir = new JButton("Cancelar");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finCasoUso();
				dispose();
			}
		});
		btnSalir.setBounds(721, 26, 89, 23);
		getContentPane().add(btnSalir);

		btnModificarUsuario = new JButton("Modificar Datos");
		btnModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificarUsuario();
				finCasoUso();
				dispose();
			}
		});
		btnModificarUsuario.setBounds(305, 601, 152, 23);
		getContentPane().add(btnModificarUsuario);
	}

	public void SubirImagen() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Imagen", "jpg", "png");
		File Directorio = fileChooser.getCurrentDirectory();

		fileChooser.setFileFilter(filter);
		fileChooser.setCurrentDirectory(Directorio);
		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			try {
				// Crear propiedades para ser utilizadas por fileChooser
				File archivoElegido = fileChooser.getSelectedFile();
				// Obteniendo la direccion del archivo
				String direccion = archivoElegido.getPath();

				imgPath = archivoElegido.getPath();

				JOptionPane.showMessageDialog(null, "Imagen cargada con exito");

				Image foto1 = new ImageIcon(direccion).getImage();
				ImageIcon img2 = new ImageIcon(foto1.getScaledInstance(157, 157, Image.SCALE_AREA_AVERAGING));
				lblImagen.setIcon(img2);
			} catch (Exception es) {
				JOptionPane.showMessageDialog(null, "Error al abrir imagen " + es);
			}
		}

	}

	public void modificarUsuario() {
		try {
			if (userLoaded) {
				Date fechaNueva = usuarioAModificar.getFechaNac();
				if (chckbxEditarFecha.isSelected()) {
					fechaNueva = dateChooserNueva.getDate();
				}
				DtUsuario usuarioNuevo = new DtUsuario(textFieldNick.getText(), textFieldCorreo.getText(),
						textFieldNombre.getText(), textFieldApellido.getText(), fechaNueva, imgPath);

				DtCanal canalNuevo = new DtCanal(textFieldNombreCanal.getText(), textAreaDescripcion.getText(),
						textFieldNick.getText(), chckbxPrivado.isSelected());
				if (usuarioNuevo != usuarioAModificar || canalNuevo != canalAModificar)
					controller.modificarUsuarioCanal(usuarioNuevo, canalNuevo);

				if (videoSeleccionado) {
					String categoriaVideo = vidAModificar.getCategoria();
					Date fechaPub = vidAModificar.getFechaPub();
					controller.seleccionarVideo(vidAModificar.getNombre());
					if (chckbxEditarFechaPub.isSelected())
						fechaPub = dateChooserVideoNueva.getDate();
					if (!comboBoxCategoriaNuevaVideo.getSelectedItem().toString().equals("")
							&& categoriaVideo != null && !categoriaVideo.equals(comboBoxCategoriaNuevaVideo.getSelectedItem().toString()))
						categoriaVideo = comboBoxCategoriaNuevaVideo.getSelectedItem().toString();
					DtVideo video = new DtVideo(0, textFieldNombreVideo.getText(), chckbxPrivadoVideo.isSelected(),
							canalAModificar.getNombre(), textAreaDescripcionVideo.getText(),
							Integer.parseInt(textFieldDuracion.getText()), categoriaVideo, fechaPub,
							textFieldURL.getText());

					if (vidAModificar != video)
						controller.editarVideo(video);
				}

				if (listaSeleccionada) {
					String categoriaLista = lstAModificar.getCategoria();
					if (!comboBoxCategoriaListaNueva.getSelectedItem().toString().equals("")
							&& !categoriaLista.equals(comboBoxCategoriaListaNueva.getSelectedItem().toString()))
						;
					DtLista listaNueva = new DtLista(0, textFieldNombreLista.getText(), chckbxPrivadoLista.isSelected(),
							false, categoriaLista);
					if (listaNueva != lstAModificar)
						controller.modificarListaParticular(lstAModificar, listaNueva);
				}
				JOptionPane.showMessageDialog(this, "Se realizaron las modificaciones exitosamente", "Modificar Datos Usuario", JOptionPane.INFORMATION_MESSAGE);
				finCasoUso();
				dispose();

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Hubo un error al modificar el usuario.", "Modificar Datos Usuario", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void FillUsers() {
		comboBoxUsuarios.removeAllItems();
		comboBoxUsuarios.addItem(" ");
		ArrayList<String> users = controller.listarUsuarios();
		for (String s : users) {
			comboBoxUsuarios.addItem(s);
		}
	}

	public void fillCategories() {
		ArrayList<String> categorias = controller.listarCategorias();
		comboBoxCategoriaNuevaVideo.addItem(" ");
		comboBoxCategoriaListaNueva.addItem(" ");
		for (String string : categorias) {
			comboBoxCategoriaNuevaVideo.addItem(string);
			comboBoxCategoriaListaNueva.addItem(string);
		}
	}

	public void fillDataLista(String lista) {
		DtLista lst = controller.seleccionarLista(lista);
		lstAModificar = lst;
		if (lst.getCategoria() != null)
			textFieldCategoriaLista.setText(lst.getCategoria());
		textFieldNombreLista.setText(lst.getNombre());
		chckbxPrivadoLista.setSelected(lst.isPrivado());
	}

	public void fillDataVideo(String video) {
		DtVideo vid = controller.seleccionarVideo(video);
		vidAModificar = vid;
		textFieldNombreVideo.setText(vid.getNombre());
		textFieldDuracion.setText(vid.getDuracion().toString());
		textFieldURL.setText(vid.getUrl());
		textFieldCategoriaVideo.setText(vid.getCategoria());
		dateChooserVideoVieja.setDate(vid.getFechaPub());
	}
	
	public void finCasoUso() {
		controller.finCasoUso();
		cleanVideoData();
		cleanData();
		cleanListData();
	}

	public void cleanListData() {
		textFieldCategoriaLista.removeAll();
		textFieldNombreLista.removeAll();
		chckbxPrivadoLista.setSelected(false);
		lstAModificar = null;
	}

	public void cleanVideoData() {
		textFieldNombreVideo.removeAll();
		textFieldDuracion.removeAll();
		textFieldURL.removeAll();
		textFieldCategoriaVideo.removeAll();
		vidAModificar = null;
		chckbxEditarFechaPub.setSelected(false);
	}

	public void cleanData() {
		comboBoxVideos.removeAllItems();
		comboBoxListas.removeAllItems();
		textFieldNick.removeAll();
		textFieldNombre.removeAll();
		textFieldApellido.removeAll();
		textFieldCorreo.removeAll();
		chckbxPrivado.setSelected(false);
		imgPath = null;
		imagen = null;
		textFieldNombreCanal.removeAll();
		textAreaDescripcion.removeAll();
		chckbxPrivado.setSelected(false);
		usuarioAModificar = null;
		canalAModificar = null;
	}

	@SuppressWarnings("deprecation")
	public void UserSelected(String user) {
		Map<DtUsuario, DtCanal> datosUser = controller.listarDatosUsuario(user);
		DtUsuario usuario = null;
		DtCanal canal = null;
		Iterator<Entry<DtUsuario, DtCanal>> it = datosUser.entrySet().iterator();
		while (it.hasNext()) {
			Entry<DtUsuario, DtCanal> entry = it.next();
			usuario = entry.getKey();
			canal = entry.getValue();
		}
		usuarioAModificar = usuario;
		canalAModificar = canal;
		textFieldNick.setText(usuario.getNickname());
		textFieldNombre.setText(usuario.getNombre());
		textFieldApellido.setText(usuario.getApellido());
		textFieldCorreo.setText(usuario.getEmail());
		chckbxPrivado.setSelected(canal.isPrivado());
		imgPath = usuario.getImg();

		dateChooserVieja.setDate(usuario.getFechaNac());

		if (usuario.getImg() != null) {
			imagen = new ImageIcon(usuario.getImg()).getImage();
			ImageIcon img2 = new ImageIcon(imagen.getScaledInstance(157, 157, Image.SCALE_AREA_AVERAGING));
			lblImagen.setIcon(img2);
		}

		textFieldNombreCanal.setText(canal.getNombre());
		textAreaDescripcion.setText(canal.getDescripcion());
		chckbxPrivado.setSelected(canal.isPrivado());

		ArrayList<String> videos = controller.listarVideos();
		comboBoxVideos.addItem(" ");
		if (videos != null) {
			for (String v : videos) {
				comboBoxVideos.addItem(v);
			}
		}

		ArrayList<DtLista> listas = controller.listarListasReproduccion(usuario);
		comboBoxListas.addItem(" ");
		if (listas != null) {
			for (DtLista lst : listas) {
				comboBoxListas.addItem(lst.getNombre());
			}
		}
		userLoaded = true;

	}
}

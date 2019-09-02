package Presentacion;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JInternalFrame;

import interfaces.IControlador;
import logica.Usuario;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import datatypes.DtCanal;
import datatypes.DtLista;
import datatypes.DtUsuario;

import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

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
	private JComboBox comboBoxDia;
	private JComboBox comboBoxMes;
	private JComboBox comboBoxAnio;
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
	private JComboBox comboBoxDiaPub;
	private JComboBox comboBoxMesPub;
	private JComboBox comboBoxAnioPub;
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
				if (comboBoxUsuarios.getSelectedItem() != null && !comboBoxUsuarios.getSelectedItem().toString().equals(" ")){
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
		lblNombre.setBounds(54, 54, 58, 14);
		panelUsuarios.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(122, 51, 194, 20);
		panelUsuarios.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		lblNickname = new JLabel("NickName");
		lblNickname.setBounds(54, 29, 46, 14);
		panelUsuarios.add(lblNickname);
		
		textFieldNick = new JTextField();
		textFieldNick.setEditable(false);
		textFieldNick.setBounds(122, 26, 194, 20);
		panelUsuarios.add(textFieldNick);
		textFieldNick.setColumns(10);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(54, 79, 66, 14);
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
		textFieldCorreo.setBounds(438, 26, 194, 20);
		panelUsuarios.add(textFieldCorreo);
		textFieldCorreo.setColumns(10);
		
		lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(326, 54, 102, 14);
		panelUsuarios.add(lblFechaNacimiento);
		
		lblImagen = new JLabel("Imagen");
		lblImagen.setBounds(417, 82, 125, 115);
		panelUsuarios.add(lblImagen);
		
		btnSubirOtraImagen = new JButton("Subir otra imagen");
		btnSubirOtraImagen.setBounds(609, 116, 150, 23);
		panelUsuarios.add(btnSubirOtraImagen);
		
		lblCanal = new JLabel("Nombre del canal");
		lblCanal.setBounds(23, 239, 90, 14);
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
				if (comboBoxListas.getSelectedItem() != null && !comboBoxListas.getSelectedItem().toString().equals(""))
				fillDataLista(comboBoxListas.getSelectedItem().toString());
			}
		});
		comboBoxListas.setBounds(136, 363, 194, 20);
		panelUsuarios.add(comboBoxListas);
		
		lblVideos = new JLabel("Videos");
		lblVideos.setBounds(373, 366, 46, 14);
		panelUsuarios.add(lblVideos);
		
		comboBoxVideos = new JComboBox();
		comboBoxVideos.setBounds(457, 363, 194, 20);
		panelUsuarios.add(comboBoxVideos);
		
		comboBoxDia = new JComboBox();
		comboBoxDia.setBounds(438, 51, 50, 20);
		panelUsuarios.add(comboBoxDia);
		
		comboBoxMes = new JComboBox();
		comboBoxMes.setBounds(523, 51, 46, 20);
		panelUsuarios.add(comboBoxMes);
		
		comboBoxAnio = new JComboBox();
		comboBoxAnio.setBounds(592, 51, 59, 20);
		panelUsuarios.add(comboBoxAnio);
		
		panelListas = new JPanel();
		tabbedPane.addTab("Listas", null, panelListas, null);
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
		textFieldCategoriaLista.setBounds(305, 155, 218, 20);
		panelListas.add(textFieldCategoriaLista);
		textFieldCategoriaLista.setColumns(10);
		
		chckbxPrivadoLista = new JCheckBox("Privado");
		chckbxPrivadoLista.setBounds(582, 80, 97, 23);
		panelListas.add(chckbxPrivadoLista);
		
		panelVideo = new JPanel();
		tabbedPane.addTab("Videos", null, panelVideo, null);
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
		lblFechaPublicacion.setBounds(495, 37, 113, 14);
		panelVideo.add(lblFechaPublicacion);
		
		comboBoxDiaPub = new JComboBox();
		comboBoxDiaPub.setBounds(474, 62, 50, 20);
		panelVideo.add(comboBoxDiaPub);
		
		comboBoxMesPub = new JComboBox();
		comboBoxMesPub.setBounds(546, 62, 50, 20);
		panelVideo.add(comboBoxMesPub);
		
		comboBoxAnioPub = new JComboBox();
		comboBoxAnioPub.setBounds(622, 62, 63, 20);
		panelVideo.add(comboBoxAnioPub);
		
		chckbxPrivadoVideo = new JCheckBox("Privado");
		chckbxPrivadoVideo.setBounds(474, 104, 97, 23);
		panelVideo.add(chckbxPrivadoVideo);
		
		lblUrl = new JLabel("URL");
		lblUrl.setBounds(89, 188, 46, 14);
		panelVideo.add(lblUrl);
		
		textFieldURL = new JTextField();
		textFieldURL.setBounds(212, 185, 192, 20);
		panelVideo.add(textFieldURL);
		textFieldURL.setColumns(10);
		
		lblCategoriaVideo = new JLabel("Categoria");
		lblCategoriaVideo.setBounds(89, 236, 86, 14);
		panelVideo.add(lblCategoriaVideo);
		
		textFieldCategoriaVideo = new JTextField();
		textFieldCategoriaVideo.setBounds(212, 236, 199, 20);
		panelVideo.add(textFieldCategoriaVideo);
		textFieldCategoriaVideo.setColumns(10);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(721, 26, 89, 23);
		getContentPane().add(btnSalir);
		
		btnModificarUsuario = new JButton("Modificar Datos");
		btnModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnModificarUsuario.setBounds(305, 601, 152, 23);
		getContentPane().add(btnModificarUsuario);	
	}
	
	public void FillUsers() {
		comboBoxUsuarios.removeAllItems();
		comboBoxUsuarios.addItem(" ");
		ArrayList<String> users = controller.listarUsuarios();
		for (String s : users) {
			comboBoxUsuarios.addItem(s);
		}
	}
	
	public void fillDiaMesAnio(){
		for (Integer i = 1; i < 32; i++) {
			comboBoxDia.addItem(i.toString());
		}
		
		for (Integer i = 1; i < 13; i++) {
			comboBoxMes.addItem(i.toString());
		}
		
		for (Integer i = 1; i < 2017; i++) {
			comboBoxAnio.addItem(i.toString());
		}
	}
	
	public void fillDataLista(String lista) {
		DtLista lst = controller.seleccionarLista(lista);
		
		if (lst.getCategoria() != null) textFieldCategoriaLista.setText(lst.getCategoria());
		textFieldNombreLista.setText(lst.getNombre());
		chckbxPrivadoLista.setSelected(lst.isPrivado());
		
	}
	
	public void cleanListData() {
		textFieldCategoriaLista.removeAll();
		textFieldNombreLista.removeAll();
		chckbxPrivadoLista.setSelected(false);
	}
	
	public void cleanVideoData() {
		textFieldNombreVideo.removeAll();
		textFieldDuracion.removeAll();
		textFieldURL.removeAll();
		textFieldCategoriaVideo.removeAll();
		
	}
	
	
	public void cleanData() {
		comboBoxVideos.removeAllItems();
		comboBoxListas.removeAllItems();
		textFieldNick.removeAll();
		textFieldNombre.removeAll();
		textFieldApellido.removeAll();
		textFieldCorreo.removeAll();
		chckbxPrivado.setSelected(false);
		textFieldNombreCanal.removeAll();
		textAreaDescripcion.removeAll();
		chckbxPrivado.setSelected(false);
		
	}
	
	@SuppressWarnings("deprecation")
	public void UserSelected(String user)
	{
		Map<DtUsuario,DtCanal> datosUser = controller.listarDatosUsuario(user);
		DtUsuario usuario = null;
		DtCanal canal = null;
		Iterator<Entry<DtUsuario, DtCanal>> it = datosUser.entrySet().iterator();
		while (it.hasNext()) {
				Entry<DtUsuario, DtCanal> entry = it.next();
				usuario = entry.getKey();
				canal = entry.getValue();
		}
		
		textFieldNick.setText(usuario.getNickname());
		textFieldNombre.setText(usuario.getNombre());
		textFieldApellido.setText(usuario.getApellido());
		textFieldCorreo.setText(usuario.getEmail());
		chckbxPrivado.setSelected(canal.isPrivado());
		
		comboBoxDia.setSelectedItem(usuario.getFechaNac().getDay());
		comboBoxMes.setSelectedItem(usuario.getFechaNac().getMonth());
		comboBoxAnio.setSelectedItem(usuario.getFechaNac().getYear());
		
		
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

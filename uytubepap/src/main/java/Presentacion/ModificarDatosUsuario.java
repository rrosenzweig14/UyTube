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
	private JLabel lblCategoriaNueva;
	private JComboBox comboBoxCategoriaListaNueva;
	private JLabel lblCategoriaNueva_1;
	private JComboBox comboBoxCategoriaNuevaVideo;
	private JTextField textFieldFechaPub;
	private JLabel lblFechaNueva;
	private JLabel lblFechaNacimientoNueva;
	private JTextField textFieldFechaNacVieja;
	private JCheckBox chckbxEditarFecha;
	private boolean editarFechaNac = false;
	private boolean editarFechaPub = false;
	private JCheckBox chckbxEditarFechaPub;
	private String imgPath;
	private boolean videoSeleccionado = false;
	private boolean listaSeleccionada = false;
	
	
	
	

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
		textFieldCorreo.setBounds(438, 26, 194, 20);
		panelUsuarios.add(textFieldCorreo);
		textFieldCorreo.setColumns(10);
		
		lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(326, 54, 102, 14);
		panelUsuarios.add(lblFechaNacimiento);
		
		lblImagen = new JLabel("Imagen");
		lblImagen.setBounds(469, 113, 125, 115);
		panelUsuarios.add(lblImagen);
		
		btnSubirOtraImagen = new JButton("Subir otra imagen");
		btnSubirOtraImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SubirImagen();
			}
		});
		btnSubirOtraImagen.setBounds(609, 116, 150, 23);
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
				if (userLoaded && comboBoxListas.getSelectedItem() != null && !comboBoxListas.getSelectedItem().toString().equals(" ")) {
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
				if (userLoaded && comboBoxVideos.getSelectedItem() != null && !comboBoxVideos.getSelectedItem().toString().equals(" ")) {
					fillDataVideo(comboBoxVideos.getSelectedItem().toString());
					videoSeleccionado = true;
				}
			}
		});
		comboBoxVideos.setBounds(457, 363, 194, 20);
		panelUsuarios.add(comboBoxVideos);
		
		comboBoxDia = new JComboBox();
		comboBoxDia.setEditable(true);
		comboBoxDia.setBounds(474, 76, 50, 20);
		panelUsuarios.add(comboBoxDia);
		
		comboBoxMes = new JComboBox();
		comboBoxMes.setEditable(true);
		comboBoxMes.setBounds(548, 76, 46, 20);
		panelUsuarios.add(comboBoxMes);
		
		comboBoxAnio = new JComboBox();
		comboBoxAnio.setEditable(true);
		comboBoxAnio.setBounds(610, 76, 59, 20);
		panelUsuarios.add(comboBoxAnio);
		
		lblFechaNacimientoNueva = new JLabel("Fecha Nacimiento Nueva");
		lblFechaNacimientoNueva.setBounds(326, 79, 143, 14);
		panelUsuarios.add(lblFechaNacimientoNueva);
		
		textFieldFechaNacVieja = new JTextField();
		textFieldFechaNacVieja.setBounds(438, 51, 194, 20);
		panelUsuarios.add(textFieldFechaNacVieja);
		textFieldFechaNacVieja.setColumns(10);
		
		chckbxEditarFecha = new JCheckBox("Editar fecha");
		chckbxEditarFecha.setBounds(662, 50, 97, 23);
		panelUsuarios.add(chckbxEditarFecha);
		
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
		
		comboBoxDiaPub = new JComboBox();
		comboBoxDiaPub.setBounds(536, 62, 50, 20);
		panelVideo.add(comboBoxDiaPub);
		
		comboBoxMesPub = new JComboBox();
		comboBoxMesPub.setBounds(618, 62, 50, 20);
		panelVideo.add(comboBoxMesPub);
		
		comboBoxAnioPub = new JComboBox();
		comboBoxAnioPub.setBounds(691, 62, 63, 20);
		panelVideo.add(comboBoxAnioPub);
		
		chckbxPrivadoVideo = new JCheckBox("Privado");
		chckbxPrivadoVideo.setBounds(536, 102, 97, 23);
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
		
		textFieldFechaPub = new JTextField();
		textFieldFechaPub.setEditable(false);
		textFieldFechaPub.setBounds(536, 34, 182, 20);
		panelVideo.add(textFieldFechaPub);
		textFieldFechaPub.setColumns(10);
		
		lblFechaNueva = new JLabel("Fecha nueva");
		lblFechaNueva.setBounds(423, 62, 86, 14);
		panelVideo.add(lblFechaNueva);
		
		chckbxEditarFechaPub = new JCheckBox("Editar Fecha");
		chckbxEditarFechaPub.setBounds(635, 102, 97, 23);
		panelVideo.add(chckbxEditarFechaPub);
		
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
	
	public void SubirImagen() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Imagen","jpg","png");
		File Directorio = fileChooser.getCurrentDirectory();	
		
		fileChooser.setFileFilter(filter);	
	    fileChooser.setCurrentDirectory(Directorio);
	    int result = fileChooser.showOpenDialog(null);
	    
	    if ( result == JFileChooser.APPROVE_OPTION ){
	    	 try
             {
	    		 //Crear propiedades para ser utilizadas por fileChooser
	    		 File archivoElegido = fileChooser.getSelectedFile();
	    		 //Obteniendo la direccion del archivo
	    		 String direccion = archivoElegido.getPath();
	    		 
	    		 imgPath = archivoElegido.getPath();
				
	    		 JOptionPane.showMessageDialog(null, "Imagen cargada con exito");
	    		 
				 Image foto1 = new ImageIcon(direccion).getImage();
				 ImageIcon img2=new ImageIcon(foto1.getScaledInstance(157, 157, Image.SCALE_AREA_AVERAGING));
				 lblImagen.setIcon(img2);
             }
	    	 catch(Exception es)
             {
                     JOptionPane.showMessageDialog(null, "Error al abrir imagen "+ es);
             }
	    }

	}
	
	
	
	public void modificarUsuario() {
		if (userLoaded) {
			Date fechaNueva = new Date();
			if (chckbxEditarFecha.isSelected()) {
				fechaNueva = new Date();				
			}
			DtUsuario usuario = new DtUsuario(textFieldNick.getText(), textFieldCorreo.getText(), textFieldNombre.getText(), textFieldApellido.getText(), fechaNueva, imgPath);
					
			DtCanal canal = new DtCanal(textFieldNombreCanal.getText(), textAreaDescripcion.getText(), textFieldNick.getText(), chckbxPrivado.isSelected());
			controller.modificarUsuarioCanal(usuario, canal);
			
			if (videoSeleccionado) {
				String categoriaVideo = null;
				Date fechaPub = new Date();
				if (!comboBoxCategoriaNuevaVideo.getSelectedItem().toString().equals("")) categoriaVideo = comboBoxCategoriaNuevaVideo.getSelectedItem().toString();
				DtVideo video = new DtVideo(0, textFieldNombreVideo.getText(), chckbxPrivadoVideo.isSelected(), canal.getNombre(), textAreaDescripcionVideo.getText(), Integer.parseInt(textFieldDuracion.getText()), categoriaVideo, fechaPub, textFieldURL.getText());				
				controller.editarVideo(video);
			}
			
			if (listaSeleccionada) {
				
				controller.modificarListaParticular(listaSeleccionada, datosNuevos);
				
			}
			
			
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
	
	public void fillDiaMesAnio(){
		for (Integer i = 1; i < 32; i++) {
			comboBoxDia.addItem(i.toString());
			comboBoxDiaPub.addItem(i.toString());
		}
		
		for (Integer i = 1; i < 13; i++) {
			comboBoxMes.addItem(i.toString());
			comboBoxMesPub.addItem(i.toString());
		}
		
		for (Integer i = 1; i < 2017; i++) {
			comboBoxAnio.addItem(i.toString());
			comboBoxAnioPub.addItem(i.toString());
		}
	}
	
	public void fillDataLista(String lista) {
		DtLista lst = controller.seleccionarLista(lista);
		
		if (lst.getCategoria() != null) textFieldCategoriaLista.setText(lst.getCategoria());
		textFieldNombreLista.setText(lst.getNombre());
		chckbxPrivadoLista.setSelected(lst.isPrivado());		
	}
	
	public void fillDataVideo(String video) {
		DtVideo vid = controller.consultarVideo(video);
		textFieldNombreVideo.setText(vid.getNombre());
		textFieldDuracion.setText(vid.getDuracion().toString());
		textFieldURL.setText(vid.getUrl());
		textFieldCategoriaVideo.setText(vid.getCategoria());
		textFieldFechaPub.setText(vid.getFechaPub().toString());
		
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
		imgPath = usuario.getImg();
		
		textFieldFechaNacVieja.setText(usuario.getFechaNac().toString());
		
		
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

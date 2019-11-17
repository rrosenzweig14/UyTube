package Presentacion;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import interfaces.Fabrica;
import interfaces.IControlador;
import logica.Conexion;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import WS.WebServices;

@SuppressWarnings("serial")
public class Principal extends JFrame {

	public static JFrame frame;

	// Menu principal
	private JMenuBar menuBar;

	private JMenu mnAltaMenu;
	private JMenuItem mntmAltaUsuario;
	private JMenuItem mntmAltaCategoria;
	private JMenuItem mntmAltaVideo;
	private JMenuItem mntmAltaLista;

	private JMenu mnConsultas;
	private JMenuItem mntmConsultaUsuario;
	private JMenuItem mntmConsultaVideo;
	private JMenuItem mntmConsultaDeLista;
	private JMenuItem mntmConsultaDeCategoria;

	private JMenu mnListados;
	private JMenuItem mntmListarUsuariosExistentes;
	private JMenuItem mntmListarCategoriasExistentes;

	private JMenu mnModificaciones;
	private JMenuItem mntmModificarDatosUsuario;
	private JMenuItem mntmModificarDatosVideo;
	private JMenuItem mntmModificarLista;

	private JMenu mnAccionesVideo;
	private JMenuItem mntmAgregarVideoLista;
	private JMenuItem mntmComentarUnVideo;
	private JMenuItem mntmValorarUnVideo;
	private JMenuItem mntmQuitarVideoLista;

	private JMenu mnAccionesUsuario;
	private JMenuItem mntmSeguirUsuario;
	private JMenuItem mntmDejarDeSeguir;
	
	private Image img;
	private Image icon;
	private JLabel lblNewImage;

	private IControlador ctrl = Fabrica.getIControlador();
	// Internal frames por Caso de Uso
	private AltaUsuario altaUsuarioInternalFrame;
	private AltaCategoria altaCategoriaInternalFrame;
	private SeguirUsuario seguirUsuarioInternalFrame;
	private noSeguirUsuario DejarDeSeguirInternalFrame;
	private AltaVideo altaVideoInternalFrame;
	private AltaLista altaListaInternalFrame;
	private ConsultaUsuario consultaUsuarioInternalFrame;
	private ConsultaListas consultaListasInternalFrame;
	private ConsultaVideo consultaVideoInternalFrame;
	private ListarUsuarios listarUsuariosExistentesInternalFrame;
	private ListarCategorias listarCategoriasExistentesInternalFrame;
	private ComentarVideo comentarVideoInternalFrame;
	private ConsultaCategoria consultaCategoriaInternalFrame;
	private AgregarVideoLista agregarVideoListaInternalFrame;
	private ModificarListaRep modificarListaRepInternalFrame;
	private ModificarDatosUsuario modificarDatosUsuarioFrame;
	private QuitarVideoLista quitarVideoListaInternalFrame;
	private ModificarVideo modificarDatosVideoInternalFrame;
	private ValorarVideo ValorarVideoInternalFrame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		WebServices ws = new WebServices();
		ws.publicar();
		
		lblNewImage = new JLabel("");
		lblNewImage.setEnabled(false);
		lblNewImage.setBounds(350,340,727,400);		
		img = new ImageIcon("././img//UyTube.png").getImage();
		icon = new ImageIcon("././img//logo.png").getImage();
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("././img//UyTube.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(727, 400, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		lblNewImage.setIcon(imageIcon);
		
		ImageIcon img2=new ImageIcon(img);
		lblNewImage.setVisible(true);
		lblNewImage.setIcon(img2);
		initialize();		
		//frame.getContentPane().add(lblNewImage);	
		IControlador ctrl = Fabrica.getIControlador();
		Conexion.open();		
		frame.getContentPane().setLayout(null);
		frame.setIconImage(icon);
		frame.setContentPane(lblNewImage);
		ctrl.crearListaHistorial();
	}
	/*
	 * 
	 * Initialize the content
	 * 
	 */

	private void initialize() {

		frame = new JFrame();
		frame.setTitle("UyTube");
		frame.setBounds(100, 100, 1024, 800);		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		{
			menuBar = new JMenuBar();
			frame.setJMenuBar(menuBar);

			mnAltaMenu = new JMenu("Altas");
			menuBar.add(mnAltaMenu);

			mntmAltaUsuario = new JMenuItem("Alta Usuario");
			mntmAltaUsuario.setIcon(new ImageIcon("././img/Icons/add-user-button.png"));
			mntmAltaUsuario.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmAltaUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					altaUsuarioInternalFrame = new AltaUsuario(ctrl);		
					altaUsuarioInternalFrame.setVisible(true);
					altaUsuarioInternalFrame.setBounds(100, 100, 530, 430);
					frame.getContentPane().add(altaUsuarioInternalFrame);					
				}
			});
			mnAltaMenu.add(mntmAltaUsuario);

			mntmAltaCategoria = new JMenuItem("Alta Categoria");
			mntmAltaCategoria.setIcon(new ImageIcon("././img/Icons/category-add-button.png"));
			mntmAltaCategoria.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmAltaCategoria.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					altaCategoriaInternalFrame = new AltaCategoria(ctrl);
					altaCategoriaInternalFrame.setBounds(100, 100, 362, 150);
					altaCategoriaInternalFrame.setVisible(true);
					frame.getContentPane().add(altaCategoriaInternalFrame);
					
				}
			});
			mnAltaMenu.add(mntmAltaCategoria);

			mntmAltaVideo = new JMenuItem("Alta Video");
			mntmAltaVideo.setIcon(new ImageIcon("././img/Icons/video-file.png"));
			mntmAltaVideo.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmAltaVideo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent a) {
					altaVideoInternalFrame = new AltaVideo(ctrl);
					altaVideoInternalFrame.setBounds(100, 100, 400, 315);					
					altaVideoInternalFrame.fillCategories();
					altaVideoInternalFrame.fillUsers();
					altaVideoInternalFrame.setVisible(true);
					frame.getContentPane().add(altaVideoInternalFrame);
				}
			});
			mnAltaMenu.add(mntmAltaVideo);

			mntmAltaLista = new JMenuItem("Alta Lista");
			mntmAltaLista.setIcon(new ImageIcon("././img/Icons/clipboard.png"));
			mntmAltaLista.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmAltaLista.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					altaListaInternalFrame = new AltaLista(ctrl);
					altaListaInternalFrame.setBounds(100, 100, 700, 600);					
					altaListaInternalFrame.fillCategories();
					altaListaInternalFrame.fillUsers();
					altaListaInternalFrame.setVisible(true);
					frame.getContentPane().add(altaListaInternalFrame);
				}
			});
			mnAltaMenu.add(mntmAltaLista);

			mnConsultas = new JMenu("Consultas");
			menuBar.add(mnConsultas);

			mntmConsultaUsuario = new JMenuItem("Consulta Usuario");
			mntmConsultaUsuario.setIcon(new ImageIcon("././img/Icons/search.png"));
			mntmConsultaUsuario.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmConsultaUsuario.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					consultaUsuarioInternalFrame = new ConsultaUsuario(ctrl);
					consultaUsuarioInternalFrame.setBounds(100, 100, 850, 700);					
					consultaUsuarioInternalFrame.fillUsers();
					consultaUsuarioInternalFrame.setVisible(true);
					frame.getContentPane().add(consultaUsuarioInternalFrame);
				}
			});			
			mnConsultas.add(mntmConsultaUsuario);

			mntmConsultaVideo = new JMenuItem("Consulta Video");
			mntmConsultaVideo.setIcon(new ImageIcon("././img/Icons/video-search.png"));
			mntmConsultaVideo.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmConsultaVideo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					consultaVideoInternalFrame = new ConsultaVideo(ctrl);
					consultaVideoInternalFrame.setBounds(100, 100, 850, 700);
					consultaVideoInternalFrame.fillUsers();
					consultaVideoInternalFrame.setVisible(true);
					frame.getContentPane().add(consultaVideoInternalFrame);
					
				}
			});	
			mnConsultas.add(mntmConsultaVideo);

			mntmConsultaDeLista = new JMenuItem("Consulta de Lista");
			mntmConsultaDeLista.setIcon(new ImageIcon("././img/Icons/clipboard-search-symbol.png"));
			mntmConsultaDeLista.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmConsultaDeLista.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					consultaListasInternalFrame = new ConsultaListas(ctrl);
					consultaListasInternalFrame.setBounds(100, 100, 386, 303);					
					consultaListasInternalFrame.fillUsers();
					consultaListasInternalFrame.setVisible(true);
					frame.getContentPane().add(consultaListasInternalFrame);					
				}
			});
			mnConsultas.add(mntmConsultaDeLista);

			mntmConsultaDeCategoria = new JMenuItem("Consulta de Categoria");
			mntmConsultaDeCategoria.setIcon(new ImageIcon("././img/Icons/search_category.png"));
			mntmConsultaDeCategoria.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmConsultaDeCategoria.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					consultaCategoriaInternalFrame = new ConsultaCategoria(ctrl);
					consultaCategoriaInternalFrame.setVisible(false);
					consultaCategoriaInternalFrame.setBounds(100, 100, 465, 488);
					consultaCategoriaInternalFrame.fillCategories();
					consultaCategoriaInternalFrame.setVisible(true);
					frame.getContentPane().add(consultaCategoriaInternalFrame);  
					
				}
			});
			mnConsultas.add(mntmConsultaDeCategoria);

			mnListados = new JMenu("Listados");
			menuBar.add(mnListados);

			mntmListarUsuariosExistentes = new JMenuItem("Listar Usuarios Existentes");
			mntmListarUsuariosExistentes.setIcon(new ImageIcon("././img/Icons/list_users.png"));
			mntmListarUsuariosExistentes.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmListarUsuariosExistentes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					listarUsuariosExistentesInternalFrame = new ListarUsuarios(ctrl);
					listarUsuariosExistentesInternalFrame.setBounds(100, 100, 530, 430);					
					listarUsuariosExistentesInternalFrame.addUsuarios();
					listarUsuariosExistentesInternalFrame.setVisible(true);
					frame.getContentPane().add(listarUsuariosExistentesInternalFrame);
					
				}
			});
			mnListados.add(mntmListarUsuariosExistentes);

			mntmListarCategoriasExistentes = new JMenuItem("Listar Categorias Existentes");
			mntmListarCategoriasExistentes.setIcon(new ImageIcon("././img/Icons/list_category.png"));
			mntmListarCategoriasExistentes.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmListarCategoriasExistentes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					listarCategoriasExistentesInternalFrame = new ListarCategorias(ctrl);
					listarCategoriasExistentesInternalFrame.setBounds(100, 100, 530, 430);					
					listarCategoriasExistentesInternalFrame.addCategorias();
					listarCategoriasExistentesInternalFrame.setVisible(true);
					frame.getContentPane().add(listarCategoriasExistentesInternalFrame);
				}
			});
			mnListados.add(mntmListarCategoriasExistentes);

			mnModificaciones = new JMenu("Modificaciones");
			menuBar.add(mnModificaciones);

			mntmModificarDatosUsuario = new JMenuItem("Modificar Datos de Usuario");
			mntmModificarDatosUsuario.setIcon(new ImageIcon("././img/Icons/user.png"));
			mntmModificarDatosUsuario.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmModificarDatosUsuario.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					modificarDatosUsuarioFrame = new ModificarDatosUsuario(ctrl);
					modificarDatosUsuarioFrame.setVisible(false);
					modificarDatosUsuarioFrame.setBounds(100, 100, 894, 698);					
					modificarDatosUsuarioFrame.FillUsers();
					modificarDatosUsuarioFrame.fillCategories();
					modificarDatosUsuarioFrame.setVisible(true);
					frame.getContentPane().add(modificarDatosUsuarioFrame);
					
				}
			});
			
			mnModificaciones.add(mntmModificarDatosUsuario);

			mntmModificarDatosVideo = new JMenuItem("Modificar Datos de Video");
			mntmModificarDatosVideo.setIcon(new ImageIcon("././img/Icons/video.png"));
			mntmModificarDatosVideo.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmModificarDatosVideo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					modificarDatosVideoInternalFrame = new ModificarVideo(ctrl);
					modificarDatosVideoInternalFrame.setBounds(100, 100, 850, 550);					
					modificarDatosVideoInternalFrame.fillUsers();
					modificarDatosVideoInternalFrame.fillCategories();
					frame.getContentPane().add(modificarDatosVideoInternalFrame);
					modificarDatosVideoInternalFrame.setVisible(true);
				}
			});
			mnModificaciones.add(mntmModificarDatosVideo);

			mntmModificarLista = new JMenuItem("Modificar Lista de Reproduccion");
			mntmModificarLista.setIcon(new ImageIcon("././img/Icons/icon.png"));
			mntmModificarLista.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmModificarLista.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					modificarListaRepInternalFrame = new ModificarListaRep(ctrl);
					modificarListaRepInternalFrame.setBounds(100, 100, 386, 303);						
					modificarListaRepInternalFrame.nuevoCaso();
					modificarListaRepInternalFrame.fillUsers();
					modificarListaRepInternalFrame.fillCategories();
					modificarListaRepInternalFrame.fillPrivacidad();					
					modificarListaRepInternalFrame.setVisible(true);
					frame.getContentPane().add(modificarListaRepInternalFrame);	
				}
			});
			mnModificaciones.add(mntmModificarLista);

			mnAccionesVideo = new JMenu("Acciones Video");
			menuBar.add(mnAccionesVideo);

			mntmAgregarVideoLista = new JMenuItem("Agregar Video a Lista");
			mntmAgregarVideoLista.setIcon(new ImageIcon("././img/Icons/addListItem.png"));
			mntmAgregarVideoLista.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmAgregarVideoLista.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					agregarVideoListaInternalFrame = new AgregarVideoLista(ctrl);
					agregarVideoListaInternalFrame.setBounds(100, 100, 530, 333);					
					agregarVideoListaInternalFrame.fillUsers();
					agregarVideoListaInternalFrame.setVisible(true);
					frame.getContentPane().add(agregarVideoListaInternalFrame);
				}
			});
			mnAccionesVideo.add(mntmAgregarVideoLista);

			mntmComentarUnVideo = new JMenuItem("Comentar un Video");
			mntmComentarUnVideo.setIcon(new ImageIcon("././img/Icons/commentVideo.png"));
			mntmComentarUnVideo.setFont(new Font("Dialog", Font.BOLD, 15));	
			mntmComentarUnVideo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					comentarVideoInternalFrame = new ComentarVideo(ctrl);
					comentarVideoInternalFrame.setBounds(100, 100, 530, 466);
					comentarVideoInternalFrame.fillUsers();					
					comentarVideoInternalFrame.setVisible(true);
					frame.getContentPane().add(comentarVideoInternalFrame);	
				}
			});
			mnAccionesVideo.add(mntmComentarUnVideo);

			mntmValorarUnVideo = new JMenuItem("Valorar un Video");
			mntmValorarUnVideo.setIcon(new ImageIcon("././img/Icons/rateVideo.png"));
			mntmValorarUnVideo.addActionListener(new ActionListener() {
				   public void actionPerformed(ActionEvent arg0) {
					ValorarVideoInternalFrame = new ValorarVideo(ctrl);
					ValorarVideoInternalFrame.setBounds(100, 100, 627, 237);
					frame.getContentPane().add(ValorarVideoInternalFrame);
				    ValorarVideoInternalFrame.fillUsers();
				    ValorarVideoInternalFrame.setVisible(true);
				   }
				  });
			mntmValorarUnVideo.setFont(new Font("Dialog", Font.BOLD, 15));
			mnAccionesVideo.add(mntmValorarUnVideo);

			mntmQuitarVideoLista = new JMenuItem("Quitar Video de Lista");
			mntmQuitarVideoLista.setIcon(new ImageIcon("././img/Icons/remove-video.png"));
			mntmQuitarVideoLista.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmQuitarVideoLista.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					quitarVideoListaInternalFrame = new QuitarVideoLista(ctrl);
					quitarVideoListaInternalFrame.setClosable(true);
					quitarVideoListaInternalFrame.setBounds(100, 100, 530, 239);					
					quitarVideoListaInternalFrame.fillUsers();					
					quitarVideoListaInternalFrame.setVisible(true);
					frame.getContentPane().add(quitarVideoListaInternalFrame);
					
				}
			});
			mnAccionesVideo.add(mntmQuitarVideoLista);			
		
			mnAccionesUsuario = new JMenu("Acciones Usuario");
			menuBar.add(mnAccionesUsuario);

			mntmSeguirUsuario = new JMenuItem("Seguir Usuario");
			mntmSeguirUsuario.setIcon(new ImageIcon("././img/Icons/follow.png"));
			mntmSeguirUsuario.setFont(new Font("Dialog", Font.BOLD, 15));	
			mntmSeguirUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					seguirUsuarioInternalFrame = new SeguirUsuario(ctrl);
					seguirUsuarioInternalFrame.setBounds(100, 100, 530, 430);					
					seguirUsuarioInternalFrame.fillUsers();
					seguirUsuarioInternalFrame.setVisible(true);
					frame.getContentPane().add(seguirUsuarioInternalFrame);	
				}
			});
			mnAccionesUsuario.add(mntmSeguirUsuario);

			mntmDejarDeSeguir = new JMenuItem("Dejar de Seguir Usuario");
			mntmDejarDeSeguir.setIcon(new ImageIcon("././img/Icons/unfollow.png"));
			mntmDejarDeSeguir.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmDejarDeSeguir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DejarDeSeguirInternalFrame = new noSeguirUsuario(ctrl);
					DejarDeSeguirInternalFrame.setBounds(100, 100, 530, 430);
					DejarDeSeguirInternalFrame.fillUsers();
					frame.getContentPane().add(DejarDeSeguirInternalFrame);	
					DejarDeSeguirInternalFrame.setVisible(true);
				}
			});
			mnAccionesUsuario.add(mntmDejarDeSeguir);
			
			

		}
	}
}

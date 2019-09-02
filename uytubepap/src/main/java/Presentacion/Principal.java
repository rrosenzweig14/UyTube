package Presentacion;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private AgregarVideoLista agregagVideoListaInternalFrame;
	private ModificarListaRep modificarListaRepInternalFrame;
	private ModificarDatosUsuario modificarDatosUsuarioFrame;
	private QuitarVideoLista quitarVideoListaInternalFrame;
	private ModificarVideo modificarDatosVideoInternalFrame;


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
		initialize();
		//IControlador ctrl = Fabrica.getIControlador();
		Conexion.open();
		
		
		frame.getContentPane().setLayout(null);
		// Aqui se inicializan las InternalJFrame de los Casos
		
		altaUsuarioInternalFrame = new AltaUsuario(ctrl);
		altaUsuarioInternalFrame.setVisible(false);
		altaUsuarioInternalFrame.setBounds(100, 100, 530, 430);
		frame.getContentPane().add(altaUsuarioInternalFrame);	
		
		altaCategoriaInternalFrame = new AltaCategoria(ctrl);
		altaCategoriaInternalFrame.setVisible(false);
		altaCategoriaInternalFrame.setBounds(100, 100, 362, 150);
		frame.getContentPane().add(altaCategoriaInternalFrame);
		
		seguirUsuarioInternalFrame = new SeguirUsuario(ctrl);
		seguirUsuarioInternalFrame.setVisible(false);
		seguirUsuarioInternalFrame.setBounds(100, 100, 530, 430);
		frame.getContentPane().add(seguirUsuarioInternalFrame);	
		
		altaListaInternalFrame = new AltaLista(ctrl);
		altaListaInternalFrame.setVisible(false);
		altaListaInternalFrame.setBounds(100, 100, 700, 600);
		frame.getContentPane().add(altaListaInternalFrame);
		
		consultaUsuarioInternalFrame = new ConsultaUsuario(ctrl);
		consultaUsuarioInternalFrame.setVisible(false);
		consultaUsuarioInternalFrame.setBounds(100, 100, 850, 700);
		frame.getContentPane().add(consultaUsuarioInternalFrame);

		altaVideoInternalFrame = new AltaVideo(ctrl);
		altaVideoInternalFrame.setVisible(false);
		altaVideoInternalFrame.setBounds(100, 100, 400, 315);
		frame.getContentPane().add(altaVideoInternalFrame);

		listarUsuariosExistentesInternalFrame = new ListarUsuarios(ctrl);
		listarUsuariosExistentesInternalFrame.setVisible(false);
		listarUsuariosExistentesInternalFrame.setBounds(100, 100, 530, 430);
		frame.getContentPane().add(listarUsuariosExistentesInternalFrame);
		
		listarCategoriasExistentesInternalFrame = new ListarCategorias(ctrl);
		listarCategoriasExistentesInternalFrame.setVisible(false);
		listarCategoriasExistentesInternalFrame.setBounds(100, 100, 530, 430);
		frame.getContentPane().add(listarCategoriasExistentesInternalFrame);
		
		consultaListasInternalFrame = new ConsultaListas(ctrl);
		consultaListasInternalFrame.setVisible(false);
		consultaListasInternalFrame.setBounds(100, 100, 386, 303);
		frame.getContentPane().add(consultaListasInternalFrame);

		DejarDeSeguirInternalFrame = new noSeguirUsuario(ctrl);
		DejarDeSeguirInternalFrame.setVisible(false);
		DejarDeSeguirInternalFrame.setBounds(100, 100, 530, 430);
		frame.getContentPane().add(DejarDeSeguirInternalFrame);	

		comentarVideoInternalFrame = new ComentarVideo(ctrl);
		comentarVideoInternalFrame.setVisible(false);
		comentarVideoInternalFrame.setBounds(100, 100, 530, 466);
		frame.getContentPane().add(comentarVideoInternalFrame);	


		consultaVideoInternalFrame = new ConsultaVideo(ctrl);
		consultaVideoInternalFrame.setVisible(false);
		consultaVideoInternalFrame.setBounds(100, 100, 850, 700);
		frame.getContentPane().add(consultaVideoInternalFrame);
		
		consultaCategoriaInternalFrame = new ConsultaCategoria(ctrl);
		consultaCategoriaInternalFrame.setVisible(false);
		consultaCategoriaInternalFrame.setBounds(100, 100, 465, 488);
		frame.getContentPane().add(consultaCategoriaInternalFrame);
		
		agregagVideoListaInternalFrame = new AgregarVideoLista(ctrl);
		agregagVideoListaInternalFrame.setVisible(false);
		agregagVideoListaInternalFrame.setBounds(100, 100, 530, 333);
		frame.getContentPane().add(agregagVideoListaInternalFrame);

		modificarListaRepInternalFrame = new ModificarListaRep(ctrl);
		modificarListaRepInternalFrame.setVisible(false);
		modificarListaRepInternalFrame.setBounds(100, 100, 386, 303);
		frame.getContentPane().add(modificarListaRepInternalFrame);		
		modificarDatosUsuarioFrame = new ModificarDatosUsuario(ctrl);
		modificarDatosUsuarioFrame.setVisible(false);
		modificarDatosUsuarioFrame.setBounds(100, 100, 894, 698);
		frame.getContentPane().add(modificarDatosUsuarioFrame);
		
		lblNewImage = new JLabel("");
		lblNewImage.setBounds(400,400,800,400);		
		img = new ImageIcon("././img//UyTube.png").getImage();
		ImageIcon img2=new ImageIcon(img);
		lblNewImage.setIcon(img2);
		lblNewImage.setVisible(true);
		frame.getContentPane().add(lblNewImage);		

		modificarDatosVideoInternalFrame = new ModificarVideo(ctrl);
		modificarDatosVideoInternalFrame.setVisible(false);
		modificarDatosVideoInternalFrame.setBounds(100, 100, 850, 550);
		frame.getContentPane().add(modificarDatosVideoInternalFrame);

	}
	/*
	 * 
	 * Initialize the content
	 * 
	 */

	private void initialize() {

		frame = new JFrame();
		frame.setTitle("UyTube");
		frame.setBounds(100, 100, 800, 600);		
		
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
					altaUsuarioInternalFrame.setVisible(true);
				}
			});
			mnAltaMenu.add(mntmAltaUsuario);

			mntmAltaCategoria = new JMenuItem("Alta Categoria");
			mntmAltaCategoria.setIcon(new ImageIcon("././img/Icons/category-add-button.png"));
			mntmAltaCategoria.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmAltaCategoria.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					altaCategoriaInternalFrame.setVisible(true);
					
				}
			});
			mnAltaMenu.add(mntmAltaCategoria);

			mntmAltaVideo = new JMenuItem("Alta Video");
			mntmAltaVideo.setIcon(new ImageIcon("././img/Icons/video-file.png"));
			mntmAltaVideo.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmAltaVideo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent a) {
					altaVideoInternalFrame.fillCategories();
					altaVideoInternalFrame.fillUsers();
					altaVideoInternalFrame.setVisible(true);
				}
			});
			mnAltaMenu.add(mntmAltaVideo);

			mntmAltaLista = new JMenuItem("Alta Lista");
			mntmAltaLista.setIcon(new ImageIcon("././img/Icons/clipboard.png"));
			mntmAltaLista.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmAltaLista.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					altaListaInternalFrame.fillCategories();
					altaListaInternalFrame.fillUsers();
					altaListaInternalFrame.setVisible(true);
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
					consultaUsuarioInternalFrame.fillUsers();
					consultaUsuarioInternalFrame.setVisible(true);
				}
			});			
			mnConsultas.add(mntmConsultaUsuario);

			mntmConsultaVideo = new JMenuItem("Consulta Video");
			mntmConsultaVideo.setIcon(new ImageIcon("././img/Icons/video-search.png"));
			mntmConsultaVideo.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmConsultaVideo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					consultaVideoInternalFrame.fillUsers();
					consultaVideoInternalFrame.setVisible(true);
				}
			});	
			mnConsultas.add(mntmConsultaVideo);

			mntmConsultaDeLista = new JMenuItem("Consulta de Lista");
			mntmConsultaDeLista.setIcon(new ImageIcon("././img/Icons/clipboard-search-symbol.png"));
			mntmConsultaDeLista.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmConsultaDeLista.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					consultaListasInternalFrame.fillUsers();
					consultaListasInternalFrame.setVisible(true);
					
				}
			});
			mnConsultas.add(mntmConsultaDeLista);

			mntmConsultaDeCategoria = new JMenuItem("Consulta de Categoria");
			mntmConsultaDeCategoria.setIcon(new ImageIcon("././img/Icons/search_category.png"));
			mntmConsultaDeCategoria.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmConsultaDeCategoria.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					consultaCategoriaInternalFrame.fillCategories();
					consultaCategoriaInternalFrame.setVisible(true);
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
					listarUsuariosExistentesInternalFrame.addUsuarios();
					listarUsuariosExistentesInternalFrame.setVisible(true);
				}
			});
			mnListados.add(mntmListarUsuariosExistentes);

			mntmListarCategoriasExistentes = new JMenuItem("Listar Categorias Existentes");
			mntmListarCategoriasExistentes.setIcon(new ImageIcon("././img/Icons/list_category.png"));
			mntmListarCategoriasExistentes.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmListarCategoriasExistentes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					listarCategoriasExistentesInternalFrame.addCategorias();
					listarCategoriasExistentesInternalFrame.setVisible(true);
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
					modificarDatosUsuarioFrame.FillUsers();
					modificarDatosUsuarioFrame.fillCategories();
					modificarDatosUsuarioFrame.fillDiaMesAnio();
					modificarDatosUsuarioFrame.setVisible(true);
					
				}
			});
			
			mnModificaciones.add(mntmModificarDatosUsuario);

			mntmModificarDatosVideo = new JMenuItem("Modificar Datos de Video");
			mntmModificarDatosVideo.setIcon(new ImageIcon("././img/Icons/video.png"));
			mntmModificarDatosVideo.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmModificarDatosVideo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					modificarDatosVideoInternalFrame.fillUsers();
					modificarDatosVideoInternalFrame.fillCategories();
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
					modificarListaRepInternalFrame.fillUsers();
					modificarListaRepInternalFrame.setVisible(true);					
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
					agregagVideoListaInternalFrame.fillUsers();
					agregagVideoListaInternalFrame.setVisible(true);
				}
			});
			mnAccionesVideo.add(mntmAgregarVideoLista);

			mntmComentarUnVideo = new JMenuItem("Comentar un Video");
			mntmComentarUnVideo.setIcon(new ImageIcon("././img/Icons/commentVideo.png"));
			mntmComentarUnVideo.setFont(new Font("Dialog", Font.BOLD, 15));	
			mntmComentarUnVideo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					comentarVideoInternalFrame.fillUsers();
					comentarVideoInternalFrame.setVisible(true);
				}
			});
			mnAccionesVideo.add(mntmComentarUnVideo);

			mntmValorarUnVideo = new JMenuItem("Valorar un Video");
			mntmValorarUnVideo.setIcon(new ImageIcon("././img/Icons/rateVideo.png"));
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
					quitarVideoListaInternalFrame.setVisible(true);
					frame.getContentPane().add(quitarVideoListaInternalFrame);				
					comentarVideoInternalFrame.fillUsers();
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
					seguirUsuarioInternalFrame.fillUsers();
					seguirUsuarioInternalFrame.setVisible(true);
				}
			});
			mnAccionesUsuario.add(mntmSeguirUsuario);

			mntmDejarDeSeguir = new JMenuItem("Dejar de Seguir Usuario");
			mntmDejarDeSeguir.setIcon(new ImageIcon("././img/Icons/unfollow.png"));
			mntmDejarDeSeguir.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmDejarDeSeguir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DejarDeSeguirInternalFrame.fillUsers();
					DejarDeSeguirInternalFrame.setVisible(true);
				}
			});
			mnAccionesUsuario.add(mntmDejarDeSeguir);

		}
	}
}

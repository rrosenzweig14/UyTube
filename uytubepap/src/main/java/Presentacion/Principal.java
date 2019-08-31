package Presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interfaces.Fabrica;
import interfaces.IControlador;
import logica.Conexion;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ImageIcon;

public class Principal extends JFrame {

	public static JFrame frame;
	private JPanel contentPane;

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

	// Internal frames por Caso de Uso
	private AltaUsuario altaUsuarioInternalFrame;
	private AltaCategoria altaCategoriaInternalFrame;
	private SeguirUsuario seguirUsuarioInternalFrame;
	private noSeguirUsuario DejarDeSeguirInternalFrame;
	private AltaVideo altaVideoInternalFrame;
	private AltaLista altaListaInternalFrame;
	private ConsultaUsuario consultaUsuarioInternalFrame;
	//private ConsultaListas consultaListasInternalFrame;


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
		IControlador ctrl = Fabrica.getIControlador();
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
		consultaUsuarioInternalFrame.setBounds(100, 100, 700, 600);
		frame.getContentPane().add(consultaUsuarioInternalFrame);
		

		altaVideoInternalFrame = new AltaVideo(ctrl);
		altaVideoInternalFrame.setVisible(false);
		altaVideoInternalFrame.setBounds(100, 100, 400, 315);
		frame.getContentPane().add(altaVideoInternalFrame);
		
		//consultaListasInternalFrame = new ConsultaListas(ctrl);
		//consultaListasInternalFrame.setVisible(false);
		//consultaListasInternalFrame.setBounds(100, 100, 386, 303);
		//frame.getContentPane().add(consultaListasInternalFrame);

		DejarDeSeguirInternalFrame = new noSeguirUsuario(ctrl);
		DejarDeSeguirInternalFrame.setVisible(false);
		DejarDeSeguirInternalFrame.setBounds(100, 100, 530, 430);
		frame.getContentPane().add(DejarDeSeguirInternalFrame);	
		
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
			mnConsultas.add(mntmConsultaVideo);

			mntmConsultaDeLista = new JMenuItem("Consulta de Lista");
			mntmConsultaDeLista.setIcon(new ImageIcon("././img/Icons/clipboard-search-symbol.png"));
			mntmConsultaDeLista.setFont(new Font("Dialog", Font.BOLD, 15));
			mntmConsultaDeLista.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//consultaListasInternalFrame.setVisible(true);
					
				}
			});
			mnConsultas.add(mntmConsultaDeLista);

			mntmConsultaDeCategoria = new JMenuItem("Consulta de Categoria");
			mntmConsultaDeCategoria.setIcon(new ImageIcon("././img/Icons/search_category.png"));
			mntmConsultaDeCategoria.setFont(new Font("Dialog", Font.BOLD, 15));
			mnConsultas.add(mntmConsultaDeCategoria);

			mnListados = new JMenu("Listados");
			menuBar.add(mnListados);

			mntmListarUsuariosExistentes = new JMenuItem("Listar Usuarios Existentes");
			mntmListarUsuariosExistentes.setIcon(new ImageIcon("././img/Icons/list_users.png"));
			mntmListarUsuariosExistentes.setFont(new Font("Dialog", Font.BOLD, 15));
			mnListados.add(mntmListarUsuariosExistentes);

			mntmListarCategoriasExistentes = new JMenuItem("Listar Categorias Existentes");
			mntmListarCategoriasExistentes.setIcon(new ImageIcon("././img/Icons/list_category.png"));
			mntmListarCategoriasExistentes.setFont(new Font("Dialog", Font.BOLD, 15));
			mnListados.add(mntmListarCategoriasExistentes);

			mnModificaciones = new JMenu("Modificaciones");
			menuBar.add(mnModificaciones);

			mntmModificarDatosUsuario = new JMenuItem("Modificar Datos de Usuario");
			mntmModificarDatosUsuario.setIcon(new ImageIcon("././img/Icons/user.png"));
			mntmModificarDatosUsuario.setFont(new Font("Dialog", Font.BOLD, 15));
			mnModificaciones.add(mntmModificarDatosUsuario);

			mntmModificarDatosVideo = new JMenuItem("Modificar Datos de Video");
			mntmModificarDatosVideo.setIcon(new ImageIcon("././img/Icons/video.png"));
			mntmModificarDatosVideo.setFont(new Font("Dialog", Font.BOLD, 15));
			mnModificaciones.add(mntmModificarDatosVideo);

			mntmModificarLista = new JMenuItem("Modificar Lista de Reproduccion");
			mntmModificarLista.setIcon(new ImageIcon("././img/Icons/icon.png"));
			mntmModificarLista.setFont(new Font("Dialog", Font.BOLD, 15));
			mnModificaciones.add(mntmModificarLista);

			mnAccionesVideo = new JMenu("Acciones Video");
			menuBar.add(mnAccionesVideo);

			mntmAgregarVideoLista = new JMenuItem("Agregar Video a Lista");
			mntmAgregarVideoLista.setIcon(new ImageIcon("././img/Icons/addListItem.png"));
			mntmAgregarVideoLista.setFont(new Font("Dialog", Font.BOLD, 15));
			mnAccionesVideo.add(mntmAgregarVideoLista);

			mntmComentarUnVideo = new JMenuItem("Comentar un Video");
			mntmComentarUnVideo.setIcon(new ImageIcon("././img/Icons/commentVideo.png"));
			mntmComentarUnVideo.setFont(new Font("Dialog", Font.BOLD, 15));
			mnAccionesVideo.add(mntmComentarUnVideo);

			mntmValorarUnVideo = new JMenuItem("Valorar un Video");
			mntmValorarUnVideo.setIcon(new ImageIcon("././img/Icons/rateVideo.png"));
			mntmValorarUnVideo.setFont(new Font("Dialog", Font.BOLD, 15));
			mnAccionesVideo.add(mntmValorarUnVideo);

			mntmQuitarVideoLista = new JMenuItem("Quitar Video de Lista");
			mntmQuitarVideoLista.setIcon(new ImageIcon("././img/Icons/remove-video.png"));
			mntmQuitarVideoLista.setFont(new Font("Dialog", Font.BOLD, 15));
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
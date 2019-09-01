package Presentacion;

import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import datatypes.DtComentario;
import datatypes.DtLista;
import datatypes.DtUsuario;
import datatypes.DtVideo;
import interfaces.IControlador;

public class AgregarVideoLista extends JInternalFrame {
	private IControlador ctrl;
	private JLabel lblUser1;
	private Choice cmbUser1;
	private JLabel lblVideo;
	private Choice cmbVideo;
	private JLabel lblUser2;
	private Choice cmbUser2;
	private JLabel lblLista;
	private Choice cmbLista;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private ArrayList<String> users;
	private ArrayList<String> videos;
	private String user1 = null;
	private String video = null;
	private String user2 = null;
	private ArrayList<DtLista> lists;
	private String list = null;


	public AgregarVideoLista(IControlador ctrl2) {
		ctrl = ctrl2;
		setClosable(true);
		setBounds(100, 100, 530, 333);
		setTitle("Agregar Video a Lista de Reproduccion");
		getContentPane().setLayout(null);
		
		lblUser1 = new JLabel("Usuario:");
		lblUser1.setBounds(112, 25, 69, 24);
		getContentPane().add(lblUser1);		

		cmbUser1 = new Choice();
		cmbUser1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				selectUser();
			}
		});
		cmbUser1.setBounds(193, 25, 193, 24);
		getContentPane().add(cmbUser1);
		fillUsers();
		
		lblVideo = new JLabel("Video:");
		lblVideo.setVisible(false);
		lblVideo.setBounds(112, 75, 69, 24);
		getContentPane().add(lblVideo);
		
		cmbVideo = new Choice();
		cmbVideo.setVisible(false);
		cmbVideo.setEnabled(false);
		cmbVideo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				selectVideo();
			}
		});
		cmbVideo.setBounds(193, 75, 193, 24);
		getContentPane().add(cmbVideo);
		
		lblUser2 = new JLabel("Usuario:");
		lblUser2.setVisible(false);
		lblUser2.setBounds(112, 147, 69, 24);
		getContentPane().add(lblUser2);
		
		cmbUser2 = new Choice();
		cmbUser2.setVisible(false);
		cmbUser2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				selectUser2();
			}
		});
		cmbUser2.setBounds(193, 147, 193, 24);
		getContentPane().add(cmbUser2);
		
		lblLista = new JLabel("Lista:");
		lblLista.setVisible(false);
		lblLista.setBounds(112, 194, 69, 15);
		getContentPane().add(lblLista);
		
		cmbLista = new Choice();
		cmbLista.setVisible(false);
		cmbLista.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				selectList();
			}
		});
		cmbLista.setBounds(193, 194, 193, 24);
		getContentPane().add(cmbLista);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(95, 262, 117, 25);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fin();
			}
		});
		getContentPane().add(btnCancelar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setEnabled(false);
		btnAceptar.setBounds(307, 262, 117, 25);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregar();
			}
		});
		getContentPane().add(btnAceptar);
		
		
	}
	
	public void fillUsers() {
		users = ctrl.listarUsuarios();
		cmbUser1.removeAll();
		cmbUser1.add("");
		for(String s: users) {
			cmbUser1.add(s);
		}		
	}
	private void selectUser() {
		ctrl.finCasoUso();   //por si cambia la eleccion.
		user1 = cmbUser1.getSelectedItem();
		users = ctrl.listarUsuarios();
		if(user1.contentEquals("")) {
			hide1();
		}else {
			lblVideo.setVisible(true);
			cmbVideo.setVisible(true);
			cmbVideo.setEnabled(true);
			ctrl.seleccionarUsuario(user1);
			videos = ctrl.listarVideos();
			cmbVideo.removeAll();
			cmbVideo.add("");
			for(String s: videos) {
				cmbVideo.add(s);
			}
			hide2();
		}
		
	}
	private void selectVideo() {
		video = cmbVideo.getSelectedItem();
		if(video.equals("")){
			hide2();
		}else {
			ctrl.seleccionarVideo(video);
			lblUser2.setVisible(true);
			users = ctrl.listarUsuarios();
			cmbUser2.add("");
			for(String s: users) {
				cmbUser2.add(s);
			}			
			cmbUser2.setVisible(true);
			hide3();
		}
	}
	private void selectUser2() {
		user2 = cmbUser2.getSelectedItem();
		users = ctrl.listarUsuarios();
		if(user2.equals("")) {
			hide3();
		}else {
			DtUsuario dtu = ctrl.seleccionarUsuario(user2);
			if(dtu != null) {
				lblLista.setVisible(true);
				cmbLista.removeAll();
				cmbLista.setVisible(true);
				cmbLista.add("");
				lists = ctrl.listarListasReproduccion(dtu);
				for(DtLista l: lists) {
					cmbLista.add(l.getNombre());
				}
			}else {
				hide3();
			}
		}
	}
	private void selectList() {
		list = cmbLista.getSelectedItem();
		if(list.equals("")) {
			btnAceptar.setEnabled(false);
			list = null;
		}else {
			btnAceptar.setEnabled(true);
		}
		
	}

	private void hide1() {
		cmbVideo.removeAll();
		lblVideo.setVisible(false);
		cmbVideo.setVisible(false);
		cmbVideo.setEnabled(false);
		user1 = null;
		hide2();
	}
	private void hide2() {
		lblUser2.setVisible(false);
		cmbUser2.removeAll();
		cmbUser2.setVisible(false);
		video = null;
		hide3();
	}
	private void hide3() {
		lblLista.setVisible(false);
		cmbLista.removeAll();
		cmbLista.setVisible(false);
		btnAceptar.setEnabled(false);
		user2 = null;
		list = null;		
	}

	private void agregar() {
		ctrl.seleccionarVideo(video);		
		DtLista aux = null;
		String s = null;
		for(DtLista l: lists) {
			if(list.equals(l.getNombre())) {
				aux= l;
			}
		}if(aux != null) {
			boolean b = ctrl.agregarVideo(video, aux);
			if(b) {
				s = new String("Video: "+video+" fue agregado con exito a la lista: "+list+".");
			}else {
				s = new String("Error agragando el video.");
			}
		}else {
			s = new String("Error seleccionado la lista.");
		}			
		JOptionPane.showMessageDialog(this, s, "Agregar Video a Lista de Reproduccion", JOptionPane.INFORMATION_MESSAGE);
		fin();
		
	}
	public void fin() {
		setVisible(false);
		dispose();
		ctrl.finCasoUso();		
	}
}

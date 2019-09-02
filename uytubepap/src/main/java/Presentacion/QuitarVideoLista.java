package Presentacion;

import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import datatypes.DtLista;
import datatypes.DtUsuario;
import datatypes.DtVideo;
import interfaces.IControlador;

public class QuitarVideoLista extends JInternalFrame {
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
	private String user1 = null;
	private ArrayList<DtVideo> videos;
	private String video = null;
	private ArrayList<DtLista> lists;
	private String list = null;


	public QuitarVideoLista(IControlador ctrl2) {
		ctrl = ctrl2;
		setClosable(true);
		setBounds(100, 100, 530, 239);
		setTitle("Quitar Video de Lista de Reproduccion");
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
		lblVideo.setBounds(112, 113, 69, 24);
		getContentPane().add(lblVideo);
		
		cmbVideo = new Choice();
		cmbVideo.setVisible(false);
		cmbVideo.setEnabled(false);
		cmbVideo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				selectVideo();
			}
		});
		cmbVideo.setBounds(193, 113, 193, 24);
		getContentPane().add(cmbVideo);
		
		lblLista = new JLabel("Lista:");
		lblLista.setVisible(false);
		lblLista.setBounds(112, 69, 69, 15);
		getContentPane().add(lblLista);
		
		cmbLista = new Choice();
		cmbLista.setVisible(false);
		cmbLista.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				selectList();
			}
		});
		cmbLista.setBounds(193, 69, 193, 24);
		getContentPane().add(cmbLista);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(95, 167, 117, 25);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fin();
			}
		});
		getContentPane().add(btnCancelar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setEnabled(false);
		btnAceptar.setBounds(307, 167, 117, 25);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quitar();
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
			DtUsuario dtu = ctrl.seleccionarUsuario(user1);
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
				hide2();
			}
		}
		
	}
	private void selectList() {
		list = cmbLista.getSelectedItem();
		if(list.equals("")) {
			hide2();
		}else {	
			DtLista aux = null;
			for(DtLista l: lists) {
				if(list.equals(l.getNombre())) {
					aux= l;
				}
			}
			lblVideo.setVisible(true);
			cmbVideo.setVisible(true);
			cmbVideo.setEnabled(true);
			ctrl.seleccionarUsuario(user1);
			videos = ctrl.videosEnLista(aux);
			cmbVideo.removeAll();
			cmbVideo.add("");
			for(DtVideo dtv: videos) {
				cmbVideo.add(dtv.getNombre());
			}
		}
		
	}
	private void selectVideo() {
		video = cmbVideo.getSelectedItem();
		if(video.equals("")){
			btnAceptar.setEnabled(false);
			video = null;
		}else {
			btnAceptar.setEnabled(true);
		}
	}
	private void hide1() {
		lblLista.setVisible(false);
		cmbLista.removeAll();
		cmbLista.setVisible(false);
		btnAceptar.setEnabled(false);
		user1 = null;
		hide2();
	}
	private void hide2() {
		cmbVideo.removeAll();
		lblVideo.setVisible(false);
		cmbVideo.setVisible(false);
		cmbVideo.setEnabled(false);
		btnAceptar.setEnabled(false);
		list = null;
		video = null;
	}

	private void quitar() {
		//ctrl.seleccionarVideo(video);
		DtVideo aux = null;
		for(DtVideo dtv: videos) {
			if(video.equals(dtv.getNombre())) {
				aux = dtv;
			}
		}
		ctrl.quitarVideo(aux);
		JOptionPane.showMessageDialog(this, "Video: "+video+" fue quitado con exito a la lista: "+list+".", "Agregar Video a Lista de Reproduccion", JOptionPane.INFORMATION_MESSAGE);
		fin();		
	}
	public void fin() {
		setVisible(false);
		ctrl.finCasoUso();
		this.dispose();
	}
}

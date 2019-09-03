package Presentacion;

import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import interfaces.Fabrica;
import interfaces.IControlador;
import javax.swing.JTree;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.toedter.calendar.JDateChooser;

import datatypes.DtComentario;
import datatypes.DtVideo;

import java.awt.Color;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class ComentarVideo extends JInternalFrame {
	private IControlador ctrl;
	private JLabel lblUser1;
	private Choice cmbUser1;
	private JLabel lblVideo;
	private Choice cmbVideo;
	private JDateChooser dateChooser;
	//private Choice cmbDia;
	//private Choice cmbMes;
	//private Choice cmbAnho;
	private JLabel lblFecha;
	private JLabel lblComentario;
	private JTree theMagicTree;
	private JScrollPane scrollArea;
	private JLabel lblUser2;
	private Choice cmbUser2;
	private JTextPane txtComment;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private ArrayList<String> users;
	private ArrayList<String> videos;
	private String user1 = null;
	private String video = null;
	private String comentador = null;
	private String txt = null;
	private DtComentario comment = null;
	private DtVideo dtv = null;

	public ComentarVideo(IControlador ctrl2) {
		//getContentPane().setVisible(false);
		ctrl = ctrl2;
		setClosable(true);
		setBounds(100, 100, 530, 466);
		setTitle("Comentar Video");
		getContentPane().setLayout(null);
		
		lblUser1 = new JLabel("Usuario:");
		lblUser1.setBounds(95, 25, 69, 24);
		getContentPane().add(lblUser1);		

		cmbUser1 = new Choice();
		cmbUser1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				selectUser();
			}
		});
		cmbUser1.setBounds(176, 25, 193, 24);
		getContentPane().add(cmbUser1);
		fillUsers();
		
		lblVideo = new JLabel("Video:");
		lblVideo.setVisible(false);
		lblVideo.setBounds(95, 78, 69, 24);
		getContentPane().add(lblVideo);
		
		cmbVideo = new Choice();
		cmbVideo.setVisible(false);
		cmbVideo.setEnabled(false);
		cmbVideo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				selectVideo();
			}
		});
		cmbVideo.setBounds(176, 78, 193, 24);
		getContentPane().add(cmbVideo);
		
		theMagicTree = new JTree();
		theMagicTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				tree();
			}
		});
		theMagicTree.setVisible(false);
		theMagicTree.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		theMagicTree.setBounds(12, 114, 218, 272);
		theMagicTree.setToggleClickCount(1);
		getContentPane().add(theMagicTree);
		
		lblUser2 = new JLabel("Usuario:");
		lblUser2.setVisible(false);
		lblUser2.setBounds(236, 114, 69, 24);
		getContentPane().add(lblUser2);
		
		cmbUser2 = new Choice();
		cmbUser2.setVisible(false);
		cmbUser2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				selectComentador();
			}
		});
		cmbUser2.setBounds(317, 114, 193, 24);
		getContentPane().add(cmbUser2);
		
		lblFecha = new JLabel("Fecha:");
		lblFecha.setVisible(false);
		lblFecha.setBounds(236, 162, 69, 24);
		getContentPane().add(lblFecha);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(317, 162, 193, 20);
		dateChooser.setVisible(false);
		getContentPane().add(dateChooser);
		
		lblComentario = new JLabel("Comentario:");
		lblComentario.setVisible(false);
		lblComentario.setBounds(236, 202, 87, 24);
		getContentPane().add(lblComentario);
		
		txtComment = new JTextPane();
		txtComment.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
			}
		});
		txtComment.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				filledTxt();
			}
		});
		txtComment.setVisible(false);
		txtComment.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		txtComment.setBounds(236, 222, 274, 164);
		getContentPane().add(txtComment);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(95, 398, 117, 25);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fin();
			}
		});
		getContentPane().add(btnCancelar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setEnabled(false);
		btnAceptar.setBounds(307, 398, 117, 25);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comentar();
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
		hide2();
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
		}
		
	}
	private void selectVideo() {
		video = cmbVideo.getSelectedItem();
		hide2();
		if(!(video.equals(""))) {
			dtv = ctrl.seleccionarVideo(video);
			if(dtv.getComentarios() != null) {
				theMagicTree = dtv.getComentarios();
				theMagicTree.addTreeSelectionListener(new TreeSelectionListener() {
					public void valueChanged(TreeSelectionEvent arg0) {
						tree();
					}
				});
				theMagicTree.setVisible(true);
				theMagicTree.setToggleClickCount(1);
				scrollArea = new JScrollPane(theMagicTree);
				scrollArea.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
				scrollArea.setBounds(12, 114, 218, 272);  
				scrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
				scrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
				getContentPane().add(scrollArea);
			}
			lblUser2.setVisible(true);
			users = ctrl.listarUsuarios();
			cmbUser2.add("");
			for(String s: users) {
				cmbUser2.add(s);
			}			
			cmbUser2.setVisible(true);
			lblFecha.setVisible(true);
			dateChooser.setVisible(true);
			lblComentario.setVisible(true);
			txtComment.setVisible(true);
		}
	}
	private void selectComentador() {
		comentador = cmbUser2.getSelectedItem();
		if(comentador.equals("")) {
			comentador = null;
		}else {
			if(txt != null) {
				btnAceptar.setEnabled(true);				
			}
		}
	}
	private void filledTxt() {
		txt = txtComment.getText();
		if(txt.equals("")) {
			txt = null;
		}else {
			if(comentador != null) {
				btnAceptar.setEnabled(true);					
			}
		}
	}
	private void tree() {
		Object[] path = theMagicTree.getSelectionPath().getPath();
		String c1 = (String)((DefaultMutableTreeNode)path[0]).getUserObject();
		Object c2 = ((DefaultMutableTreeNode) path[path.length - 1]).getUserObject();
		if(c2 instanceof String) {
			comment = null;
		}else if(c2 instanceof DtComentario) {
			comment = (DtComentario) c2;
			ctrl.seleccionarComentario(comment);
		}else {
			System.out.println("que_mierda_es?####################################################################################################################################");
		}
	}
	private void comentar() {
		ctrl.seleccionarUsuario(comentador);
//		if(comment != null) {
//			ctrl.seleccionarComentario(comment);
//		}
		Date fecha = dateChooser.getDate();
		if(fecha != null) {
			DtComentario aux = new DtComentario(comentador, txt, fecha);
			ctrl.ingresarComentario(aux);
			JOptionPane.showMessageDialog(this, "Comentario realizado con exito!!!", "Comentar Video", JOptionPane.INFORMATION_MESSAGE);
			fin();
		}else {
			JOptionPane.showMessageDialog(this, "Falta llenar la fecha.", "Comentar Video", JOptionPane.INFORMATION_MESSAGE);			
		}
		
	}
	private void hide1() {
		cmbVideo.removeAll();
		lblVideo.setVisible(false);
		cmbVideo.setVisible(false);
		cmbVideo.setEnabled(false);
		user1 = null;	
	}
	private void hide2() {
		theMagicTree.setVisible(false);
		lblUser2.setVisible(false);
		cmbUser2.removeAll();
		cmbUser2.setVisible(false);
		lblFecha.setVisible(false);
		dateChooser.setVisible(false);
		lblComentario.setVisible(false);
		txtComment.setText("");
		txtComment.setVisible(false);
		btnAceptar.setEnabled(false);
		comentador = null;
		txt = null;
		dtv = null;
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++Hide2++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	public void fin() {
		setVisible(false);
		dispose();
		ctrl.finCasoUso();		
	}
}

package Presentacion;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JInternalFrame;

import interfaces.IControlador;
import logica.Handler;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;

import datatypes.DtLista;
import datatypes.DtUsuario;
import datatypes.DtVideo;

import javax.swing.JScrollBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;


public class ConsultaListas extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IControlador cr;
	private JTextField nombre;
	private JTextField privacidad;
	private JTextField tipo;
	private JComboBox cbUsuario;
	private JComboBox cbLista;
	private JComboBox cbVideos;
	private ConsultaVideo consultaVideoInternalFrame;
	private ArrayList<DtVideo> dtvs;


	/**
	 * Create the frame.
	 */
	public ConsultaListas(IControlador ctrl) {
		setResizable(true);
		cr = ctrl;
		setBounds(100, 100, 386,303);
		setClosable(true);
		setTitle("Consulta de Listas");
		getContentPane().setLayout(null);
		
		consultaVideoInternalFrame = new ConsultaVideo(ctrl);
		consultaVideoInternalFrame.setResizable(true);
		consultaVideoInternalFrame.setBounds(0, 0, 679, 469);
		getContentPane().add(consultaVideoInternalFrame);
		
		JLabel lblSeleccioneUsuario = new JLabel("Seleccione Usuario");
		lblSeleccioneUsuario.setBounds(12, 24, 151, 15);
		getContentPane().add(lblSeleccioneUsuario);
		
		JLabel lblSeleccioneLista = new JLabel("Seleccione Lista");
		lblSeleccioneLista.setBounds(12, 51, 151, 15);
		getContentPane().add(lblSeleccioneLista);
		
		JLabel lblDatosDeLa = new JLabel("Datos de la Lista Seleccionada");
		lblDatosDeLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosDeLa.setBounds(12, 78, 352, 15);
		getContentPane().add(lblDatosDeLa);
		
		cbUsuario = new JComboBox();
		cbUsuario.setBounds(182, 24, 166, 18);
		cbUsuario.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(" " != (String) cbUsuario.getSelectedItem() && cbUsuario.getSelectedItem() != null) {
					cbLista.removeAllItems();
					cbVideos.removeAllItems();
					limpiarCampos();
					fillVideos();
					fillListas((String) cbUsuario.getSelectedItem()); 
				}
			}
		});
		cbUsuario.setVisible(true);
		getContentPane().add(cbUsuario);
		
		cbLista = new JComboBox();
		cbLista.setBounds(182, 51, 166, 18);
		cbLista.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(" " != (String) cbLista.getSelectedItem()) {
					limpiarCampos();
					cbVideos.removeAllItems();
					cargarLista();
					fillVideos();
				}
			}
		});
		getContentPane().add(cbLista);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(39, 105, 70, 15);
		getContentPane().add(lblNombre);
		
		JLabel lblPrivacidad = new JLabel("Privacidad");
		lblPrivacidad.setBounds(39, 135, 87, 15);
		getContentPane().add(lblPrivacidad);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(39, 162, 70, 15);
		getContentPane().add(lblTipo);
		
		JLabel lblVideos = new JLabel("Videos");
		lblVideos.setBounds(39, 189, 70, 15);
		getContentPane().add(lblVideos);
		
		nombre = new JTextField();
		nombre.setBounds(209, 105, 114, 19);
		nombre.addInputMethodListener(new InputMethodListener() {
			
			@Override
			public void inputMethodTextChanged(InputMethodEvent event) {
				if(nombre.getSelectedText() != " ");
					fillVideos();

				
			}
			
			@Override
			public void caretPositionChanged(InputMethodEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		getContentPane().add(nombre);
		nombre.setColumns(10);
		
		privacidad = new JTextField();
		privacidad.setBounds(209, 133, 114, 19);
		getContentPane().add(privacidad);
		privacidad.setColumns(10);
		
		tipo = new JTextField();
		tipo.setBounds(209, 160, 114, 19);
		getContentPane().add(tipo);
		tipo.setColumns(10);
		
		cbVideos = new JComboBox();
		cbVideos.setBounds(209, 189, 114, 18);
		getContentPane().add(cbVideos);
		
		JButton btnConsultarVideo = new JButton("Consultar Video");
		btnConsultarVideo.setBounds(115, 234, 159, 25);
		btnConsultarVideo.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				DtVideo video = null;
				for(DtVideo dts : dtvs)
					if(dts.getNombre().equals(cbVideos.getSelectedItem().toString()) )
						video = dts;
				if(video != null) {
					consultaVideoInternalFrame.desdeLista((String) cbUsuario.getSelectedItem(),(String) cbVideos.getSelectedItem(),video);
					consultaVideoInternalFrame.setVisible(true);
					setBounds(100, 100, 855, 705);
					try {
						consultaVideoInternalFrame.setSelected(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}
			}
		});
		getContentPane().add(btnConsultarVideo);
	}
	
	
	public void fillUsers() {
		//cr.finCasoUso();
		cbUsuario.addItem(" ");
		ArrayList<String> users = cr.listarUsuarios();
		for (String s : users) {
			cbUsuario.addItem(s);
		}
	}
	
	public void fillListas(String usuario) {
		cbLista.addItem(" ");
		DtUsuario us = cr.seleccionarUsuario(usuario);
		cr.listarListasParticulares(us);
		ArrayList<DtLista> listas = (ArrayList<DtLista>) cr.listarListasReproduccion(us);
		for(DtLista dtl : listas)
			cbLista.addItem(dtl.getNombre());
	}
	
	public void fillVideos() {
		if(" " != (String) cbLista.getSelectedItem() && cbLista.getSelectedItem() != null ) {
			//cr.seleccionarUsuario((String) cbUsuario.getSelectedItem());
			DtLista lst = cr.seleccionarLista((String) cbLista.getSelectedItem());
			ArrayList<DtVideo> videos = cr.videosEnLista(lst);
			dtvs = videos;
			if(videos != null)
			for(DtVideo dtv : videos) {
				if(dtv != null) {
					System.out.println(dtv.getNombre());
					cbVideos.addItem(dtv.getNombre());
				}
			}
		}	
	}
	
	public void cargarLista() {
		//cr.seleccionarUsuario((String) cbUsuario.getSelectedItem());
		if(" " != (String) cbLista.getSelectedItem() && cbLista.getSelectedItem() != null ) {
			System.out.println((String) cbLista.getSelectedItem());
			DtLista lst = cr.seleccionarLista((String) cbLista.getSelectedItem());
			nombre.setText(lst.getNombre());
			if(lst.isPrivado())
				privacidad.setText("Privado");
			else
				privacidad.setText("Publico");
			if(lst.isDefecto())
				tipo.setText("Defecto");
			else
				tipo.setText("Particular");
		}
	}
	
	public void limpiarCampos() {
		privacidad.setText("");
		tipo.setText("");
		nombre.setText("");
	}
	
}

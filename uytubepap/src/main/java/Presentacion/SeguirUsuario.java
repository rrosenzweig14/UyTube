package Presentacion;

import javax.swing.JInternalFrame;
import datatypes.DtUsuario;
import interfaces.Fabrica;
import interfaces.IControlador;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class SeguirUsuario extends JInternalFrame {
	private JLabel lblSeguidor;
	private Choice cmbSeguidor;
	private JLabel lblSeguido;
	private Choice cmbSeguido;
	private Button btnAceptar;
	private Button btnCAncelar;
	private IControlador ctrl = Fabrica.getIControlador();
	private String elElegido = null;
	private String seguido = null;
	private ArrayList<String> users = ctrl.listarUsuarios();
	
	public SeguirUsuario(IControlador ctrl2) {
		setClosable(true);
		setBounds(100, 100, 530, 430);
		setTitle("Seguir Usuario");
		getContentPane().setLayout(null);
		
		lblSeguidor = new JLabel("Seguidor:");
		lblSeguidor.setBounds(93, 68, 69, 24);
		getContentPane().add(lblSeguidor);		

		cmbSeguidor = new Choice();
		cmbSeguidor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				selectSeguidor();
			}
		});
		cmbSeguidor.setBounds(174, 68, 193, 24);
		getContentPane().add(cmbSeguidor);
		
		lblSeguido = new JLabel("Seguido:");
		lblSeguido.setBounds(93, 121, 69, 24);
		getContentPane().add(lblSeguido);
		
		cmbSeguido = new Choice();
		cmbSeguido.setEnabled(false);
		cmbSeguido.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				selectSeguido();
			}
		});
		cmbSeguido.setBounds(174, 121, 193, 24);
		getContentPane().add(cmbSeguido);
		
		btnAceptar = new Button("Aceptar");
		btnAceptar.setEnabled(false);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seguirUsuario();
			}
		});
		btnAceptar.setBounds(271, 205, 96, 25);
		getContentPane().add(btnAceptar);
		
		btnCAncelar = new Button("Cancelar");
		btnCAncelar.setBounds(93, 205, 96, 25);
		btnCAncelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fin();
			}
		});
		getContentPane().add(btnCAncelar);	
	}
	
	
	public void fin() {
		setVisible(false);
		dispose();
		ctrl.finCasoUso();		
	}
	
	public void fillUsers() {
		users = ctrl.listarUsuarios();
		cmbSeguidor.removeAll();
		cmbSeguidor.add("");
		for(String s: users) {
			cmbSeguidor.add(s);
		}		
	}
	
	public void selectSeguidor() {
		ctrl.finCasoUso();   //por si cambia la eleccion.
		elElegido = cmbSeguidor.getSelectedItem();
		users = ctrl.listarUsuarios();
		seguido = null;
		if(elElegido.contentEquals("")) {
			cmbSeguido.removeAll();
			cmbSeguido.setEnabled(false);
			elElegido = null;			
		}else {
			DtUsuario dtu = ctrl.seleccionarUsuario(elElegido);
			cmbSeguido.removeAll();
			cmbSeguido.add("");
			for(String s: users) {
				if(!s.equals(elElegido)) {	//no se pueda seguir a si mismo
					boolean b = true;
					for(String seg: dtu.getSeguidos().keySet()) {	//no pueda seguir a alguien que ya siga
						if(seg.equals(s)) {
							b = false;
						}
					}
					System.out.println("88888888888888888888888888888888888888888888888888888888888888888888888888");
					if(b) {
						cmbSeguido.add(s);
					}					
				}
			}
			if(cmbSeguido.getItemCount() > 0) {
				cmbSeguido.setEnabled(true);
			}
		}
	}

	public void selectSeguido() {
		seguido = cmbSeguido.getSelectedItem();
		if(seguido.equals("")) {
			btnAceptar.setEnabled(false);
		}else {
			btnAceptar.setEnabled(true);			
		}
	}
	
	public void seguirUsuario() {
		ctrl.seleccionarUsuario(seguido);		
		ctrl.seguirUsuario();
		JOptionPane.showMessageDialog(this, elElegido+" sigue a "+seguido+" exitosamente!!!", "Seguir Usuario", JOptionPane.INFORMATION_MESSAGE);
		fin();
	}


}



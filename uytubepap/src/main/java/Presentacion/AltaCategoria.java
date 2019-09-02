package Presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import interfaces.IControlador;

public class AltaCategoria extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private IControlador ctrl;
	private JLabel lblInfo;
	private JLabel lblNombre;
	
	

	public AltaCategoria(IControlador cr) {
		ctrl = cr;
		setBounds(100, 100, 362, 190);
		setClosable(true);
		setTitle("Alta Categoria");
		getContentPane().setLayout(null);
		{
			lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(67, 29, 70, 15);
			getContentPane().add(lblNombre);
		}
		{
			lblInfo = new JLabel();
			lblInfo.setBounds(67, 55, 200, 15);
			getContentPane().add(lblInfo);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(199, 27, 114, 19);
			getContentPane().add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JButton btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					if(ctrl.altaCategoria(txtNombre.getText())) {
						fin(true);
					}
					else {
						existe();
					}
				}
			});
			btnAceptar.setBounds(32, 82, 117, 25);
			getContentPane().add(btnAceptar);
		}
		{
			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fin(false);
				}
			});
			btnCancelar.setBounds(199, 82, 117, 25);
			getContentPane().add(btnCancelar);
		}
		

		

	}
	public void existe() {
		JOptionPane.showMessageDialog(this, "La categoria ingresada ya existe!", "Alta Categoria", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void fin(Boolean f) {
		if(f)
		JOptionPane.showMessageDialog(this, "Categoria agregada correctamente.", "Alta Categoria", JOptionPane.INFORMATION_MESSAGE);
		setVisible(false);
		dispose();
		ctrl.finCasoUso();		
	}
}

package Presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jboss.jandex.Main;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import interfaces.IControlador;

public class AltaCategoria extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private IControlador ctrl;
	private JLabel lblInfo;
	private JLabel lblNombre;
	
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Principal window = new Principal();
//					window.AltaCategoria.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}


	/**
	 * Create the frame.
	 */
	public AltaCategoria(IControlador cr) {
		ctrl = cr;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(116, 125, 70, 15);
			getContentPane().add(lblNombre);
		}
		{
			JLabel lblInfo = new JLabel("Nombre");
			lblInfo.setBounds(116, 200, 140, 15);
			getContentPane().add(lblInfo);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(241, 123, 114, 19);
			getContentPane().add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JButton btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ctrl.altaCategoria(txtNombre.getText());
					
//					if(ctrl.altaCategoria(txtNombre.getText()))
//						lblInfo.setText("Categoria agregada!");
//					else
//						lblInfo.setText("Categoria existente!");
				}
			});
			btnAceptar.setBounds(59, 233, 117, 25);
			getContentPane().add(btnAceptar);
		}
		{
			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					txtNombre.setText("");
				}
			});
			btnCancelar.setBounds(241, 233, 117, 25);
			getContentPane().add(btnCancelar);
		}
		

		

	}
}

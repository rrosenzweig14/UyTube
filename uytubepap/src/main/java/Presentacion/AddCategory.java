package Presentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import interfaces.Fabrica;
import interfaces.IControlador;

public class AddCategory extends JPanel{
	protected IControlador controller = Fabrica.getIControlador();
	//private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JLabel lblInfo;
	private JLabel lblNombre;

	
	public AddCategory() {
		this.setBounds(140,25,1000,800);
		this.setBorder(new LineBorder(UIManager.getColor("List.dropLineColor"), 2, true));
		setLayout(null);
		
		JLabel lblAltaDeUsuario = new JLabel("Alta de Categoria");
		lblAltaDeUsuario.setLabelFor(this);
		lblAltaDeUsuario.setBounds(295, 44, 410, 73);
		lblAltaDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		Font font = new Font("Courier", Font.BOLD,32);
		lblAltaDeUsuario.setFont(font);
		add(lblAltaDeUsuario);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(243, 177, 153, 20);
		add(lblNombre);
	
		txtNombre = new JTextField();
		txtNombre.setBounds(400, 177, 200, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		lblInfo = new JLabel();
		lblInfo.setBounds(400, 225, 200, 15);
		add(lblInfo);

		JButton btnAceptar = new JButton("Agregar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {					
				if(controller.altaCategoria(txtNombre.getText())) {
					fin(true);
				}
				else {
					existe();
				}
			}
		});
		btnAceptar.setBounds(435, 628, 129, 22);
		add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fin(false);
			}
		});
		btnCancelar.setBounds(845, 24, 129, 22);
		add(btnCancelar);

	}
	
	public void existe() {
		JOptionPane.showMessageDialog(this, "La categoria ingresada ya existe!", "Alta Categoria", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void fin(Boolean f) {
		if(f)
		JOptionPane.showMessageDialog(this, "Categoria agregada correctamente.", "Alta Categoria", JOptionPane.INFORMATION_MESSAGE);
		setVisible(false);
		//dispose();
		controller.finCasoUso();		
	}

}

package Presentacion;

import java.util.ArrayList;

import javax.swing.JInternalFrame;

import interfaces.IControlador;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import datatypes.DtUsuario;

@SuppressWarnings("serial")
public class AltaLista extends JInternalFrame {

	private IControlador controller;
	private JTextField textFieldNombreLista;
	private JRadioButton rdbtnParticular;
	private JRadioButton rdbtnDefecto;
	private JLabel lblNombreLista;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JComboBox<String> comboBoxUsuarios;
	private JLabel lblNombreUsuario;
	private JCheckBox chckbxPrivada;
	private JComboBox<String> comboBoxCategorias;
	private JLabel lblCategoria;

	/**
	 * Create the frame.
	 */
	public AltaLista(IControlador ctrl) {
		controller = ctrl;
		setBounds(100, 100, 700, 600);
		setClosable(true);
		setTitle("Alta Lista");
		getContentPane().setLayout(null);
		{

			rdbtnParticular = new JRadioButton("Particular");
			rdbtnParticular.setBounds(200, 29, 109, 23);
			rdbtnParticular.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					showParticularInput();

				}
			});
			getContentPane().add(rdbtnParticular);

			rdbtnDefecto = new JRadioButton("Defecto", true);
			rdbtnDefecto.setBounds(389, 29, 109, 23);
			rdbtnDefecto.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					showDefectoInput();

				}
			});
			getContentPane().add(rdbtnDefecto);

			ButtonGroup bgroup = new ButtonGroup();
			bgroup.add(rdbtnParticular);
			bgroup.add(rdbtnDefecto);

			lblNombreLista = new JLabel("Nombre Lista");
			lblNombreLista.setBounds(66, 183, 102, 14);
			lblNombreLista.setVisible(true);
			getContentPane().add(lblNombreLista);

			textFieldNombreLista = new JTextField();
			textFieldNombreLista.setBounds(200, 180, 255, 20);
			getContentPane().add(textFieldNombreLista);
			textFieldNombreLista.setVisible(true);
			textFieldNombreLista.setColumns(10);

			btnAceptar = new JButton("Aceptar");
			btnAceptar.setBounds(409, 448, 89, 23);
			btnAceptar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					altaLista();
				}
			});
			getContentPane().add(btnAceptar);

			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(200, 448, 89, 23);
			btnCancelar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					finCasoUso();
					dispose();
				}
			});
			getContentPane().add(btnCancelar);

			comboBoxUsuarios = new JComboBox<String>();
			comboBoxUsuarios.setBounds(200, 134, 255, 20);
			comboBoxUsuarios.setVisible(false);
			getContentPane().add(comboBoxUsuarios);

			lblNombreUsuario = new JLabel("Nombre Usuario");
			lblNombreUsuario.setBounds(66, 137, 102, 14);
			lblNombreUsuario.setVisible(false);
			getContentPane().add(lblNombreUsuario);

			chckbxPrivada = new JCheckBox("Privada");
			chckbxPrivada.setBounds(499, 133, 97, 23);
			chckbxPrivada.setVisible(false);
			getContentPane().add(chckbxPrivada);

			comboBoxCategorias = new JComboBox<String>();
			comboBoxCategorias.setBounds(200, 225, 255, 20);
			comboBoxCategorias.setVisible(false);
			getContentPane().add(comboBoxCategorias);

			lblCategoria = new JLabel("Categoria");
			lblCategoria.setBounds(66, 228, 102, 14);
			lblCategoria.setVisible(false);
			getContentPane().add(lblCategoria);
		}
	}

	public void finCasoUso() {
		controller.finCasoUso();
	}
	
	
	//TODO clean fields
	public void cleanFields() {
		
	}
	

	public void fillUsers() {
		comboBoxUsuarios.addItem(" ");
		ArrayList<String> users = controller.listarUsuarios();
		for (String s : users) {
			comboBoxUsuarios.addItem(s);
		}
	}

	public void fillCategories() {
		comboBoxCategorias.addItem(" ");
		ArrayList<String> categorias = controller.listarCategorias();
		for (String c : categorias) {
			comboBoxCategorias.addItem(c);
		}
	}

	public void altaLista() {
		if (rdbtnDefecto.isSelected()) {
			String nombreLista = textFieldNombreLista.getText();
			if (nombreLista.equals("")) {
				JOptionPane.showMessageDialog(null, "Ingrese el nombre de la lista.");
			} else {
				controller.ingresarTipoLista(true);
				try {

					if (controller.crearLista(null, nombreLista, true, null)) {
						JOptionPane.showMessageDialog(this, "Se creó la lista exitosamente.", "Alta Lista",
								JOptionPane.INFORMATION_MESSAGE);
						finCasoUso();
						dispose();
					} else {
						JOptionPane.showMessageDialog(this, "Error ya existe una lista con éste nombre.", "Alta Lista",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Error inesperado.", "Alta Lista", JOptionPane.ERROR_MESSAGE);
				}
			}

		} else {

			String nombreUsuario = comboBoxUsuarios.getSelectedItem().toString();
			String nombreCategoria = comboBoxCategorias.getSelectedItem().toString();
			String nombreLista = textFieldNombreLista.getText();
			boolean privado = chckbxPrivada.isSelected();
			if (nombreUsuario.equals("") || nombreLista.equals("")) {
				JOptionPane.showMessageDialog(null, "Quedan campos sin completar.");
			} else {
				controller.ingresarTipoLista(false);
				DtUsuario user = new DtUsuario(nombreUsuario);

				if (nombreCategoria.equals("")) {
					if (controller.crearLista(user, nombreLista, privado, null)) {
						JOptionPane.showMessageDialog(this, "Se creó la lista exitosamente.", "Alta Lista",
								JOptionPane.INFORMATION_MESSAGE);
						finCasoUso();
						dispose();
					} else {
						JOptionPane.showMessageDialog(this, "Error ya existe una lista con éste nombre.", "Alta Lista",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					if (controller.crearLista(user, nombreLista, privado, nombreCategoria)) {
						JOptionPane.showMessageDialog(this, "Se creó la lista exitosamente.", "Alta Lista",
								JOptionPane.INFORMATION_MESSAGE);
						finCasoUso();
						dispose();
					} else {
						JOptionPane.showMessageDialog(this, "Error ya existe una lista con éste nombre.", "Alta Lista",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		}
	}

	public void showDefectoInput() {
		lblNombreUsuario.setVisible(false);
		lblCategoria.setVisible(false);
		comboBoxCategorias.setVisible(false);
		comboBoxUsuarios.setVisible(false);
		lblNombreLista.setVisible(true);
		textFieldNombreLista.setVisible(true);
	}

	public void showParticularInput() {
		lblNombreUsuario.setVisible(true);
		lblCategoria.setVisible(true);
		comboBoxCategorias.setVisible(true);
		comboBoxUsuarios.setVisible(true);
		lblNombreLista.setVisible(true);
		textFieldNombreLista.setVisible(true);
	}
}

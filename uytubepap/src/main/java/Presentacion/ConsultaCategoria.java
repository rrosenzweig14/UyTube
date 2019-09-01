package Presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import interfaces.IControlador;

import javax.swing.border.LineBorder;
import java.awt.Color;

public class ConsultaCategoria extends JInternalFrame {


	private static final long serialVersionUID = 1L;
	private IControlador cr;
	private JTable listas;
	private JTable videos;
	private JComboBox cbCategoria; 
	DefaultTableModel listasModel;
	DefaultTableModel videosModel;




	public ConsultaCategoria(IControlador ctrl) {
		cr = ctrl;
		setClosable(true);
		setTitle("Consulta de Categoria");
		setBounds(100, 100, 465, 488);
		getContentPane().setLayout(null);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(101, 32, 70, 15);
		getContentPane().add(lblCategoria);
		
		cbCategoria = new JComboBox();
		cbCategoria.setBounds(203, 29, 160, 20);
		fillCategories();
		cbCategoria.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fillListas((String) cbCategoria.getSelectedItem());
				fillVideos((String) cbCategoria.getSelectedItem());
			}
		});
		getContentPane().add(cbCategoria);
		
		listas = new JTable();
		listas.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Listas", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, new Color(51, 51, 51)));
		listas.setBounds(12, 104, 200, 325);
		getContentPane().add(listas);
		
		videos = new JTable();
		videos.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Videos", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, new Color(51, 51, 51)));
		videos.setBounds(243, 104, 200, 325);
		getContentPane().add(videos);

	}
	
	public void fillCategories() {
		cbCategoria.addItem(" ");
		ArrayList<String> categorias = cr.listarCategorias();
		for (String c : categorias) {
			cbCategoria.addItem(c);
		}
	}
	
	public void fillListas(String cat) {
		HashMap<String, String> list = (HashMap<String, String>) cr.listasXCat(cat);
		String[]rows = {"Nombre","Propietario"};
		String[][] cols = {};
		listasModel = new DefaultTableModel(cols, rows);
		Object[] first = {" "," "};
		listasModel.addRow(first);
		if(list != null)
		for (Entry<String, String> entry : list.entrySet() ) {
			Object[] data = {entry.getKey(),entry.getValue()};
			listasModel.addRow(data);
		}
		listas.setModel(listasModel);
	}
	
	public void fillVideos(String cat) {
		HashMap<String, String> vids = (HashMap<String, String>) cr.videosXCat(cat);
		String[]rows = {"Nombre","Propietario"};
		String[][] cols = {};
		videosModel = new DefaultTableModel(cols, rows);
		Object[] first = {" "," "};
		videosModel.addRow(first);
		System.out.println("########"+vids.toString());
		if(!vids.isEmpty())
		for (Entry<String, String> videos : vids.entrySet() ) {
			System.out.println(videos.getKey()+"  @@@@@@@@@@@");
			Object[] data = {videos.getKey(),videos.getValue()};
			videosModel.addRow(data);
		}
		videos.setModel(videosModel);
	}
}

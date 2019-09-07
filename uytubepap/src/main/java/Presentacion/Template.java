package Presentacion;

import java.awt.*;
import javax.swing.*;

import interfaces.Fabrica;
import interfaces.IControlador;

public class Template extends JScrollPane{	
	protected JLabel lblTitulo;
	
	public Template(Color color) {
		super();
		this.setBounds(0,0,1280,850);
		this.setBackground(color);
		//this.setLayout(null);
	    this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}
	
}

package Presentacion;
//probando
import javax.swing.JInternalFrame;
import datatypes.DtCanal;
import datatypes.DtUsuario;
import interfaces.IControlador;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ValorarVideo extends JInternalFrame {
private JComboBox<String> cbVideos;
private JComboBox<String> cbUsuario1;
private JComboBox<String> cbUsuario2;
private JPanel pnlLike;
private JRadioButton rdbtnDislike;
private JRadioButton rdbtnLike;
private JButton btnAceptar;
private IControlador cr;

/**
 * Create the frame.
 */
public ValorarVideo(IControlador ctrl) {
 setClosable(true);
 cr = ctrl;
 setBounds(100, 100, 627, 237);
 getContentPane().setLayout(null);

 cbUsuario1 = new JComboBox<String>();
 cbUsuario1.addItemListener(new ItemListener() {
  public void itemStateChanged(ItemEvent e) {
   if (cbUsuario1.getSelectedItem().toString().equals(" ")) {

   } else {
    Map<DtUsuario, DtCanal> datos = cr.listarDatosUsuario(cbUsuario1.getSelectedItem().toString());
    Iterator<Entry<DtUsuario, DtCanal>> it = datos.entrySet().iterator();
    DtUsuario user = null;
    DtCanal canal = null;
    while (it.hasNext()) {
     Entry<DtUsuario, DtCanal> entry = it.next();
     user = entry.getKey();
     canal = entry.getValue();
    }
    if (user != null && canal != null)
     fillDataUser(user, canal);
   }
  }
 });
 cbUsuario1.setBounds(20, 26, 286, 37);
 getContentPane().add(cbUsuario1);

 cbUsuario2 = new JComboBox<String>();
 cbUsuario2.setBounds(20, 94, 286, 37);
 getContentPane().add(cbUsuario2);

 cbVideos = new JComboBox<String>();
 cbVideos.setBounds(316, 26, 286, 37);
 getContentPane().add(cbVideos);

 pnlLike = new JPanel();
 pnlLike.setBounds(342, 94, 230, 37);
 getContentPane().add(pnlLike);
 pnlLike.setLayout(null);

 rdbtnLike = new JRadioButton("Me gusta");
 rdbtnLike.setBounds(6, 7, 104, 23);
 pnlLike.add(rdbtnLike);

 rdbtnDislike = new JRadioButton("No me gusta");
 rdbtnDislike.setBounds(120, 7, 104, 23);
 pnlLike.add(rdbtnDislike);

 ButtonGroup bgroup = new ButtonGroup();
 bgroup.add(rdbtnDislike);
 bgroup.add(rdbtnLike);

 JLabel lblUsuario1_2 = new JLabel("Usuario que contiene el video :");
 lblUsuario1_2.setBounds(20, 11, 286, 14);
 getContentPane().add(lblUsuario1_2);

 JLabel lblUsuario2_2 = new JLabel("Usuario a realizar la acción :");
 lblUsuario2_2.setBounds(20, 74, 286, 14);
 getContentPane().add(lblUsuario2_2);

 JLabel lblVideo = new JLabel("Video sobre el que realizar la accion :");
 lblVideo.setBounds(316, 11, 269, 14);
 getContentPane().add(lblVideo);

 btnAceptar = new JButton("Aceptar");
 btnAceptar.addActionListener(new ActionListener() {
  public void actionPerformed(ActionEvent e) {
   ValoracionVideo();
  }
 });
 btnAceptar.setBounds(268, 160, 89, 23);
 getContentPane().add(btnAceptar);

}

public void ValoracionVideo(){
 cr.seleccionarUsuario(cbUsuario1.getSelectedItem().toString());
 cr.seleccionarUsuario(cbUsuario2.getSelectedItem().toString());
 cr.seleccionarVideo(cbVideos.getSelectedItem().toString());
 try {
  if (rdbtnDislike.isSelected()) {
   cr.valorarVideo(cbUsuario2.getSelectedItem().toString(), false);
   JOptionPane.showMessageDialog(null, "Vídeo valorado.");
   finCasoUso();
   dispose();
  }
  if (rdbtnLike.isSelected()) {
   cr.valorarVideo(cbUsuario2.getSelectedItem().toString(), true);
   JOptionPane.showMessageDialog(null, "Vídeo valorado.");
   finCasoUso();
   dispose();
  }
 } catch (Exception e) {
  JOptionPane.showMessageDialog(null, "No se ha logrado valorar vídeo.");
 }
 finCasoUso();
}

public void fillUsers() {
 cbUsuario1.removeAllItems();
 cbUsuario1.addItem(" ");
 cbUsuario2.removeAllItems();
 cbUsuario2.addItem(" ");
 ArrayList<String> users = cr.listarUsuarios();
 for (String s : users) {
  cbUsuario1.addItem(s);
  cbUsuario2.addItem(s);
 }

}

public void fillDataUser(DtUsuario user, DtCanal canal) {
 ArrayList<String> videos = cr.listarVideos();
 cbVideos.removeAllItems();
 cbVideos.addItem(" ");
 if (videos != null) {
  for (String v : videos) {
   cbVideos.addItem(v);
  }
 }
}

public void finCasoUso() {
 cr.finCasoUso();
}
}
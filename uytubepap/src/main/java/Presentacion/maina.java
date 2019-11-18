package Presentacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import java.awt.EventQueue;
//import java.util.logging.Handler;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import interfaces.Fabrica;
import interfaces.IControlador;
import logica.*;

public class maina {
	public static EntityManager em = Conexion.getEm();
	
	public static void eCom(Set<Comentario> res) {
		if(res != null) {
			for(Comentario c: res) {
				eCom(c.getRespuestas());
				c.getRespuestas().clear();
				em.remove(c);
			}
		}
	}
	
	/*
	 * 
			Conexion.persist(u);	
			Conexion.commit();
			Conexion.beginTransaction();
	 */
	
	public static void main(String[] args) {
		Conexion.open();
		Usuario u = Handler.findUsuario("trabuco");
		Canal c = u.getCanal();
		if(c != null) {
			Map<String,Video> videos = c.getListaVideos();
			if(videos != null) {
				for(Video v: videos.values()) {
					List<Usuario_Video> val = v.getValoraciones();
					if(val != null) {
						for(Usuario_Video uv: val) {
							Conexion.beginTransaction();
							Usuario_VideoID key = new Usuario_VideoID();
							key.setIdVideo(v.getId());
							key.setNombreUsuario((uv.getNombreUsuario()).getNickname());
							Usuario_Video aux = em.find(Usuario_Video.class, key);
							em.remove(aux);
							Conexion.commit();						
						}
					}
					List<Comentario> com = v.getComentarios();
					if(com != null) {
						for(Comentario co: com) {
							Conexion.beginTransaction();
							Comentario aux = em.find(Comentario.class, co.getId());
							em.remove(aux);
							Conexion.commit();						
						}
					}
					Conexion.beginTransaction();
					Video v2 = em.find(Video.class, v.getId());
					em.remove(v2);
					Conexion.commit();				
				}
			}
			Map<String,Lista> listas = c.getListasReproduccion();
			if(listas != null) {
				for(Lista l: listas.values()) {
					Conexion.beginTransaction();
					Lista aux = em.find(Lista.class, l.getId());
					em.remove(aux);
					Conexion.commit();						
				}
			}
			Conexion.beginTransaction();
			Canal aux = em.find(Canal.class, u.getNickname());
			em.remove(aux);
			Conexion.commit();
		}
		Conexion.beginTransaction();
		Usuario aux = em.find(Usuario.class, u.getNickname());
		em.remove(aux);
		Conexion.commit();
		Conexion.close();
	}

}

package WS;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jws.WebMethod;
import javax.xml.ws.Endpoint;

import datatypes.DtCanal;
import datatypes.DtComentario;
import datatypes.DtLista;
import datatypes.DtUsuario;
import datatypes.DtVideo;
import interfaces.Fabrica;
import interfaces.IControlador;

public class ControladorWS {
    private IControlador icon = Fabrica.getIControlador();
    

    public String[] listarVideos(){
    	return (String[])(icon.listarVideos()).toArray();
    }    

    public void valorarVideoPublico(String nick, boolean valor) {
    	icon.valorarVideo(nick, valor);    	
    }

    public boolean agregarVideo(String video, DtLista lista) {
    	return icon.agregarVideo(video, lista);    	
    }

    public DtVideoWS findVideo(int id) {
    	DtVideo dtv = icon.findVideo(id);
    	if(dtv != null) {
    		return new DtVideoWS(dtv.getId(),dtv.getNombre(),dtv.getPrivado(),dtv.getCanal(),dtv.getDescripcion(),dtv.getDuracion(),dtv.getCategoria(),dtv.getFechaPub(),dtv.getUrl(),(DtComentario[])(dtv.getCom()).toArray(),(String[])(dtv.getValoracionesPositivas()).toArray(),(String[])(dtv.getValoracionesNegativas()).toArray());
    	}else {
    		return null;
    	}
    }

    public Boolean ingresarVideo(String nombre, Integer duracion, String url, String descripcion, Date fecha) {
    	return icon.ingresarVideo(nombre, duracion, url, descripcion, fecha);
    }

    public DtVideoWS seleccionarVideo(String video) {
    	DtVideo dtv = icon.seleccionarVideo(video);
    	if(dtv != null) {
    		return new DtVideoWS(dtv.getId(),dtv.getNombre(),dtv.getPrivado(),dtv.getCanal(),dtv.getDescripcion(),dtv.getDuracion(),dtv.getCategoria(),dtv.getFechaPub(),dtv.getUrl(),(DtComentario[])(dtv.getCom()).toArray(),(String[])(dtv.getValoracionesPositivas()).toArray(),(String[])(dtv.getValoracionesNegativas()).toArray());
    	}else {
    		return null;
    	}
	}

    public String findDuenioVideo(int id) {
    	return icon.findDuenioVideo(id);
    }

    public Object[] listarVideosPrivados(String nick) {
    	HashMap<Integer,String> aux = icon.listarVideosPrivados(nick);
    	int i = 0;
    	Object[] retorno = new Object[aux.size() * 2];
    	for(Integer j: aux.keySet()) {
    		retorno[i++] = j;
    		retorno[i++] = aux.get(j);
    	}
    	return retorno;
    }

    public Object[] listarVideosPublicos() {
    	HashMap<Integer,String> aux = icon.listarVideosPublicos();
    	int i = 0;
    	Object[] retorno = new Object[aux.size() * 2];
    	for(Integer j: aux.keySet()) {
    		retorno[i++] = j;
    		retorno[i++] = aux.get(j);
    	}
    	return retorno;
    }

    public void editarVideo(DtVideoWS v) {
    	DtVideo dtv = new DtVideo(v.id,v.nombre,v.privado,v.canal,v.descripcion,v.duracion,v.categoria,v.fechaPub,v.url);
    	icon.editarVideo(dtv);
    }

    public DtUsuarioWS seleccionarUsuario(String usuario) {
    	DtUsuario dtu =  icon.seleccionarUsuario(usuario);
    	if(dtu != null) {
    		HashMap<String,DtUsuario> aux = dtu.getSeguidores();
        	int i = 0;
        	String[] seguidores = new String[aux.size()];
        	for(String s: aux.keySet()) {
        		seguidores[i++] = s;
        	}
        	aux = dtu.getSeguidos();
        	i = 0;
        	String[] seguidos = new String[aux.size()];
        	for(String s: aux.keySet()) {
        		seguidos[i++] = s;
        	}
    		return new DtUsuarioWS(dtu.getNickname(), dtu.getEmail(), dtu.getPassword(), dtu.getNombre(), dtu.getApellido(), dtu.getFechaNac(), dtu.getImg(), seguidores, seguidos);
    	}else {
    		return null;
    	}
    }

    public Boolean ingresarUsuario(String nickname, String email,String password, String nombre, String apellido, Date fechaNac, String img, DtCanalWS c) {
    	DtCanal canal = new DtCanal(c.nombre,c.descripcion,c.nick,c.privado.booleanValue());
    	return icon.ingresarUsuario(nickname, email, password, nombre, apellido, fechaNac, img, canal);
    }

    public Object[] listarDatosUsuario(String nick){
    	Map<DtUsuario, DtCanal> aux = icon.listarDatosUsuario(nick);
    	int i = 0;
    	Object[] retorno = new Object[aux.size() * 2];
    	for(DtUsuario j: aux.keySet()) {
    		HashMap<String,DtUsuario> seg = j.getSeguidores();
        	int k = 0;
        	String[] seguidores = new String[seg.size()];
        	for(String s: seg.keySet()) {
        		seguidores[k++] = s;
        	}
        	seg = j.getSeguidos();
        	k = 0;
        	String[] seguidos = new String[seg.size()];
        	for(String s: seg.keySet()) {
        		seguidos[k++] = s;
        	}
        	retorno[i++] = new DtUsuarioWS(j.getNickname(), j.getEmail(), j.getPassword(), j.getNombre(), j.getApellido(), j.getFechaNac(), j.getImg(), seguidores, seguidos);
        	DtCanal dtc = aux.get(j);
        	HashMap<Integer,DtVideo> lv = dtc.getListaVideos();
        	k = 0;
        	DtVideoWS[] v = new DtVideoWS[lv.size()];
        	for(DtVideo l: lv.values()) {
        		v[k++] = new DtVideoWS(l.getId(),l.getNombre(),l.getPrivado(),l.getCanal(),l.getDescripcion(),l.getDuracion(),l.getCategoria(),l.getFechaPub(),l.getUrl(),(DtComentario[])(l.getCom()).toArray(),(String[])(l.getValoracionesPositivas()).toArray(),(String[])(l.getValoracionesNegativas()).toArray());
        	}
        	HashMap<Integer,DtLista> lr = dtc.getListasReproduccion();
        	k = 0;
        	DtLista[] a = new DtLista[lv.size()];
        	for(DtLista l: lr.values()) {
        		a[k++] = l;
        	}
    		retorno[i++] = new DtCanalWS(dtc.getNombre(),dtc.getDescripcion(),dtc.getNick(),dtc.isPrivado(),a,v);
    	}
    	return retorno;    	
    }

    public void seguirUsuario() {
    	icon.seguirUsuario();
    }

    public void dejarSeguir() {
    	icon.dejarSeguir();
    }

    public void modificarUsuarioCanal(DtUsuarioWS u, DtCanalWS c) {
    	DtUsuario usr = new DtUsuario(u.nickname,u.email,u.password,u.nombre,u.apellido,u.fechaNac,u.img);
    	DtCanal canal = new DtCanal(c.nombre,c.descripcion,c.nick,c.privado.booleanValue());
    	icon.modificarUsuarioCanal(usr, canal);
    }

    public Object[] listarCanalesPublicos(){
    	HashMap<String,String> aux = icon.listarCanalesPublicos();
    	int i = 0;
    	Object[] retorno = new Object[aux.size() * 2];
    	for(String j: aux.keySet()) {
    		retorno[i++] = j;
    		retorno[i++] = aux.get(j);
    	}
    	return retorno;       	
    }

    public Boolean login(String usuario, String password) {
    	return icon.login(usuario, password);
    }

    public void seleccionarComentario(DtComentario comment) {
    	icon.seleccionarComentario(comment);
    }

    public boolean ingresarComentario(DtComentario comment) {
    	return icon.ingresarComentario(comment);
    }

    public DtLista seleccionarLista(String lista) {
    	return seleccionarLista(lista);
    }

    public void ingresarTipoLista(boolean defecto) {
    	icon.ingresarTipoLista(defecto);
    }

    public boolean crearLista(DtUsuarioWS u, String nombre, boolean privada, String categoria) {
    	DtUsuario usuario = new DtUsuario(u.nickname,u.email,u.password,u.nombre,u.apellido,u.fechaNac,u.img);
    	return icon.crearLista(usuario, nombre, privada, categoria);
    }

    public DtLista findLista(int id) {
    	return findLista(id);
    }

    public String findDuenioLista(int id) {
    	return icon.findDuenioLista(id);
    }

    public DtVideoWS[] videosEnLista(DtLista lst){
    	DtVideo[] videos = (DtVideo[])(icon.videosEnLista(lst)).toArray();
    	DtVideoWS[] v = new DtVideoWS[videos.length];
    	int i = 0;
    	for(DtVideo a: videos) {
    		v[i]= new DtVideoWS(a.getId(),a.getNombre(),a.getPrivado(),a.getCanal(),a.getDescripcion(),a.getDuracion(),a.getCategoria(),a.getFechaPub(),a.getUrl(),(DtComentario[])(a.getCom()).toArray(),(String[])(a.getValoracionesNegativas()).toArray(),(String[])(a.getValoracionesPositivas()).toArray());
    	}
    	return v;
    }

    public void quitarVideo(DtVideoWS v) {
    	DtVideo video = new DtVideo(v.id,v.nombre,v.privado,v.canal,v.descripcion,v.duracion,v.categoria,v.fechaPub,v.url);
    	icon.quitarVideo(video);
    }

    public DtVideoWS[] videosEnListaPublica(DtLista lst){
    	DtVideo[] vid = (DtVideo[])(icon.videosEnListaPublica(lst)).toArray();
    	DtVideoWS[] v = new DtVideoWS[vid.length];
    	int i = 0;
    	for(DtVideo a: vid) {
    		v[i]= new DtVideoWS(a.getId(),a.getNombre(),a.getPrivado(),a.getCanal(),a.getDescripcion(),a.getDuracion(),a.getCategoria(),a.getFechaPub(),a.getUrl(),(DtComentario[])(a.getCom()).toArray(),(String[])(a.getValoracionesNegativas()).toArray(),(String[])(a.getValoracionesPositivas()).toArray());
    	}
    	return v;    	
    }

    public DtLista[] listarListasParticulares(DtUsuarioWS u){
    	DtUsuario user = new DtUsuario(u.nickname,u.email,u.password,u.nombre,u.apellido,u.fechaNac,u.img);
    	return (DtLista[])(icon.listarListasParticulares(user)).toArray(); 
    }

    public Object[] listarListasPublicas(){
    	HashMap<Integer,String> aux = icon.listarListasPublicas();
    	int i = 0;
    	Object[] retorno = new Object[aux.size() * 2];
    	for(Integer j: aux.keySet()) {
    		retorno[i++] = j;
    		retorno[i++] = aux.get(j);
    	}
    	return retorno; 
    }

    public void modificarListaParticular(DtLista listaSeleccionada,DtLista datosNuevos) {
    	icon.modificarListaParticular(listaSeleccionada, datosNuevos);
    }

    public Boolean altaCategoria(String nombre) {
    	return icon.altaCategoria(nombre);
    }

    public String[] listarCategorias(){
    	return (String[])(icon.listarCategorias()).toArray();
    }

    public void seleccionarCategoria(String cat) {
    	icon.seleccionarCategoria(cat);
    }

    public Object[] videosXCatPublicos(String categoria){
    	Map<String,String> aux = icon.videosXCatPublicos(categoria);
    	int i = 0;
    	Object[] retorno = new Object[aux.size() * 2];
    	for(String j: aux.keySet()) {
    		retorno[i++] = j;
    		retorno[i++] = aux.get(j);
    	}
    	return retorno; 
    }

    public Object[] listasXCatPublicas(String categoria){
    	Map<String,String> aux = icon.listasXCatPublicas(categoria);
    	int i = 0;
    	Object[] retorno = new Object[aux.size() * 2];
    	for(String j: aux.keySet()) {
    		retorno[i++] = j;
    		retorno[i++] = aux.get(j);
    	}
    	return retorno; 
    }

    public DtVideoWS[] buscarVideosPublicos(String dato){
    	DtVideo[] vid = (DtVideo[])(icon.buscarVideosPublicos(dato)).toArray();
    	DtVideoWS[] v = new DtVideoWS[vid.length];
    	int i = 0;
    	for(DtVideo a: vid) {
    		v[i]= new DtVideoWS(a.getId(),a.getNombre(),a.getPrivado(),a.getCanal(),a.getDescripcion(),a.getDuracion(),a.getCategoria(),a.getFechaPub(),a.getUrl(),(DtComentario[])(a.getCom()).toArray(),(String[])(a.getValoracionesNegativas()).toArray(),(String[])(a.getValoracionesPositivas()).toArray());
    	}
    	return v;    	
    }

    public DtCanalWS[] buscarCanalesPublicos(String dato){
    	DtCanal[] can = (DtCanal[])(icon.buscarCanalesPublicos(dato)).toArray();
    	DtCanalWS[] c = new DtCanalWS[can.length];
    	int i = 0;
    	for(DtCanal dtc: can) {
    		int k = 0;
        	HashMap<Integer,DtVideo> lv = dtc.getListaVideos();
        	DtVideoWS[] v = new DtVideoWS[lv.size()];
        	for(DtVideo l: lv.values()) {
        		v[k++] = new DtVideoWS(l.getId(),l.getNombre(),l.getPrivado(),l.getCanal(),l.getDescripcion(),l.getDuracion(),l.getCategoria(),l.getFechaPub(),l.getUrl(),(DtComentario[])(l.getCom()).toArray(),(String[])(l.getValoracionesPositivas()).toArray(),(String[])(l.getValoracionesNegativas()).toArray());
        	}
        	HashMap<Integer,DtLista> lr = dtc.getListasReproduccion();
        	k = 0;
        	DtLista[] a = new DtLista[lv.size()];
        	for(DtLista l: lr.values()) {
        		a[k++] = l;
        	}
    		c[i++] = new DtCanalWS(dtc.getNombre(),dtc.getDescripcion(),dtc.getNick(),dtc.isPrivado(),a,v);
    	}    	
    	return c;  
    }

    public DtLista[] buscarListasPublicas(String dato){
    	return (DtLista[])(icon.buscarListasPublicas(dato)).toArray();      	
    }

    public void finCasoUso() {
    	icon.finCasoUso();
    }

}

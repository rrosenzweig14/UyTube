package WS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import datatypes.DtCanal;
import datatypes.DtComentario;
import datatypes.DtLista;
import datatypes.DtUsuario;
import datatypes.DtVideo;
import interfaces.Fabrica;
import interfaces.IControlador;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WebServices {
    private Endpoint endpoint = null;
    private IControlador icon = Fabrica.getIControlador();
    
    public WebServices(){}

    @WebMethod(exclude = true)
    public void publicar(){
         endpoint = Endpoint.publish("http://localhost:9293/ws/uytube", this);
    }
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    @WebMethod
    public String[] listarUsuarios(){
    	ArrayList<String> aux = icon.listarUsuarios();
    	String[] r = new String[aux.size()];
    	int i = 0;
    	for(String s: aux) {
    		r[i++] = s;
    	}
    	return r;
    }   
    @WebMethod
    public String[] listarVideos(){
    	ArrayList<String> aux = icon.listarVideos();
    	String[] r = new String[aux.size()];
    	int i = 0;
    	for(String s: aux) {
    		r[i++] = s;
    	}
    	return r;
    }    
    @WebMethod
    public Boolean valorarVideoPublico(Integer id,String nick, Boolean valor) {
    	return icon.valorarVideoPublico(id,nick, valor);    	
    }
    @WebMethod
    public boolean agregarVideo(String video, DtLista lista) {
    	return icon.agregarVideo(video, lista);    	
    }
    @WebMethod
    public DtVideo findVideo(int id) {
    	return icon.findVideo(id);    	
    }
    @WebMethod
    public Boolean ingresarVideo(String nombre, Integer duracion, String url, String descripcion, Date fecha) {
    	return icon.ingresarVideo(nombre, duracion, url, descripcion, fecha);
    }
    @WebMethod
    public DtVideo seleccionarVideo(String video) {
    	return icon.seleccionarVideo(video);
	}
    @WebMethod
    public String findDuenioVideo(int id) {
    	return icon.findDuenioVideo(id);
    }
    @WebMethod
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
    @WebMethod
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
    @WebMethod
    public void editarVideo(DtVideo video) {
    	icon.editarVideo(video);
    }
    @WebMethod
    public DtUsuario seleccionarUsuario(String usuario) {
    	return icon.seleccionarUsuario(usuario);
    }
    @WebMethod
    public Boolean ingresarUsuario(String nickname, String email,String password, String nombre, String apellido, Date fechaNac, String img, DtCanal canal) {
    	return icon.ingresarUsuario(nickname, email, password, nombre, apellido, fechaNac, img, canal);
    }
    @WebMethod
    public Object[] listarDatosUsuario(String nick){
    	Map<DtUsuario, DtCanal> aux = icon.listarDatosUsuario(nick);
    	int i = 0;
    	Object[] retorno = new Object[aux.size() * 2];
    	for(DtUsuario j: aux.keySet()) {
    		retorno[i++] = j;
    		retorno[i++] = aux.get(j);
    	}
    	return retorno;    	
    }
    @WebMethod
    public void seguirUsuario() {
    	icon.seguirUsuario();
    }
    @WebMethod
    public void dejarSeguir() {
    	icon.dejarSeguir();
    }
    @WebMethod
    public void modificarUsuarioCanal(DtUsuario usr, DtCanal canal) {
    	icon.modificarUsuarioCanal(usr, canal);
    }
    @WebMethod
    public String[] listarCanalesPublicos(){
    	HashMap<String,String> aux = icon.listarCanalesPublicos();
    	int i = 0;
    	String[] retorno = new String[aux.size() * 2];
    	for(String j: aux.keySet()) {
    		retorno[i++] = j;
    		retorno[i++] = aux.get(j);
    	}
    	return retorno;       	
    }
    @WebMethod
    public Boolean login(String usuario, String password) {
    	return icon.login(usuario, password);
    }
    @WebMethod
    public void seleccionarComentario(DtComentario comment) {
    	icon.seleccionarComentario(comment);
    }
    @WebMethod
    public boolean ingresarComentario(DtComentario comment) {
    	return icon.ingresarComentario(comment);
    }
    @WebMethod
    public DtLista seleccionarLista(String lista) {
    	return icon.seleccionarLista(lista);
    }
    @WebMethod
    public void ingresarTipoLista(boolean defecto) {
    	icon.ingresarTipoLista(defecto);
    }
    @WebMethod
    public boolean crearLista(DtUsuario usuario, String nombre, boolean privada, String categoria) {
    	return icon.crearLista(usuario, nombre, privada, categoria);
    }
    @WebMethod
    public DtLista findLista(int id) {
    	return icon.findLista(id);
    }
    @WebMethod
    public String findDuenioLista(int id) {
    	return icon.findDuenioLista(id);
    }
    @WebMethod
    public DtVideo[] videosEnLista(DtLista lst){
    	ArrayList<DtVideo> aux = icon.videosEnLista(lst);
    	DtVideo[] r = new DtVideo[aux.size()];
    	int i = 0;
    	for(DtVideo s: aux) {
    		r[i++] = s;
    	}
    	return r;
    }
    @WebMethod
    public void quitarVideo(DtVideo video) {
    	icon.quitarVideo(video);
    }
    @WebMethod
    public DtVideo[] videosEnListaPublica(DtLista lst){
    	ArrayList<DtVideo> aux = icon.videosEnListaPublica(lst);
    	DtVideo[] r = new DtVideo[aux.size()];
    	int i = 0;
    	for(DtVideo s: aux) {
    		r[i++] = s;
    	}
    	return r;	
    }
    @WebMethod
    public DtLista[] listarListasParticulares(DtUsuario user){
    	List<DtLista> aux = icon.listarListasParticulares(user);
    	DtLista[] r = new DtLista[aux.size()];
    	int i = 0;
    	for(DtLista s: aux) {
    		r[i++] = s;
    	}
    	return r;	
    }
    @WebMethod
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
    @WebMethod
    public void modificarListaParticular(DtLista listaSeleccionada,DtLista datosNuevos) {
    	icon.modificarListaParticular(listaSeleccionada, datosNuevos);
    }
    @WebMethod
    public Boolean altaCategoria(String nombre) {
    	return icon.altaCategoria(nombre);
    }
    @WebMethod
    public String[] listarCategorias(){
    	ArrayList<String> aux = icon.listarCategorias();
    	String[] c = new String[aux.size()];
    	int i = 0;
    	for(String s: aux) {
    		c[i++] = s;
    	}
    	return c;
    }
    @WebMethod
    public void seleccionarCategoria(String cat) {
    	icon.seleccionarCategoria(cat);
    }
    @WebMethod
    public String[] videosXCatPublicos(String categoria){
    	Map<String,String> aux = icon.videosXCatPublicos(categoria);
    	int i = 0;
    	String[] retorno = new String[aux.size() * 2];
    	for(String j: aux.keySet()) {
    		retorno[i++] = j;
    		retorno[i++] = aux.get(j);
    	}
    	return retorno; 
    }
    @WebMethod
    public String[] listasXCatPublicas(String categoria){
    	Map<String,String> aux = icon.listasXCatPublicas(categoria);
    	int i = 0;
    	String[] retorno = new String[aux.size() * 2];
    	for(String j: aux.keySet()) {
    		retorno[i++] = j;
    		retorno[i++] = aux.get(j);
    	}
    	return retorno; 
    }
    @WebMethod
    public DtVideo[] buscarVideosPublicos(String dato){
    	ArrayList<DtVideo> aux = icon.buscarVideosPublicos(dato);
    	DtVideo[] r = new DtVideo[aux.size()];
    	int i = 0;
    	for(DtVideo s: aux) {
    		r[i++] = s;
    	}
    	return r;	
    	
    }
    @WebMethod
    public DtCanal[] buscarCanalesPublicos(String dato){
    	ArrayList<DtCanal> aux = icon.buscarCanalesPublicos(dato);
    	DtCanal[] r = new DtCanal[aux.size()];
    	int i = 0;
    	for(DtCanal s: aux) {
    		r[i++] = s;
    	}
    	return r;
    }
    @WebMethod
    public DtLista[] buscarListasPublicas(String dato){
    	List<DtLista> aux = icon.buscarListasPublicas(dato);
    	DtLista[] r = new DtLista[aux.size()];
    	int i = 0;
    	for(DtLista s: aux) {
    		r[i++] = s;
    	}
    	return r; 	
    }
    @WebMethod
    public void finCasoUso() {
    	icon.finCasoUso();
    }
}
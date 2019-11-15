        
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import datatypes.DtCanal;
import datatypes.DtLista;
import datatypes.DtUsuario;
import datatypes.DtVideo;
import logica.Categoria;
import logica.Controlador;
import logica.Video;
import interfaces.Fabrica;
import interfaces.IControlador;

public class AssertTests {

	private IControlador ctrl = Fabrica.getIControlador();
	DtCanal canal1;
	DtCanal canal2;
	DtVideo videoBase;
	DtUsuario usuarioBase;

	@Before
	public void inicializarTest() {
			Date date = new Date();
			canal1 = new DtCanal("U1canal", "desc", "user1", true);
			canal2 = new DtCanal("U2canal", "desc", "user2", true);
			//Crear Usuario
			ctrl.ingresarUsuario("user1", "user1@gmail.com", "123", "user", "1", date, "img" ,canal1);
			ctrl.ingresarUsuario("user2", "user2@gmail.com", "123", "user", "2", date, "img" ,canal2);
			usuarioBase = ctrl.seleccionarUsuario("user1");
			//Crear Categoria
			ctrl.altaCategoria("cat1");
			ctrl.altaCategoria("cat2");
			//Crear Video
			ctrl.seleccionarCategoria("cat1");
			ctrl.seleccionarUsuario("user1");
			Date fecha = new Date();
			ctrl.ingresarVideo("base", 123, "url", "desc", fecha);
			ctrl.seleccionarUsuario("user1");
			videoBase = ctrl.seleccionarVideo("base");
			//Lista Base
			ctrl.crearLista(usuarioBase, "listaPrivada", true, "cat1");
			ctrl.crearLista(usuarioBase, "listaPublica", true, "cat1");

			ctrl.login("user2", "123");
			ctrl.finCasoUso();
	}
	
	//TESTS DE VIDEO
	@Test
	public void ingresarVideo() {
			ctrl.altaCategoria("cat");
			ctrl.seleccionarCategoria("cat");
			ctrl.seleccionarUsuario("user1");
			Date fecha = new Date();
			boolean vid = ctrl.ingresarVideo("ingresar", 123, "url", "desc", fecha);
			assertTrue(vid);
	}

	@Test
	public void valorarVideo() {
			ctrl.seleccionarUsuario("user1");
			DtVideo video = ctrl.seleccionarVideo("base");
			//Valorarlo
			ctrl.valorarVideoPublico(video.getId(), "user2", true);
			DtVideo video2 = ctrl.seleccionarVideo("base");
			//valoraciones positivos esta dando vacio
			ArrayList<String> valoraciones = video2.getValoracionesPositivas();
			ArrayList<String> esperadas = new ArrayList<String>();
			esperadas.add("user2");
			assertEquals(valoraciones, esperadas);
	}
	
	@Test
	public void videosEnLista() {
//		ctrl.seleccionarUsuario("user1");
//		ctrl.seleccionarUsuario("user1");
//		DtLista lista = ctrl.seleccionarLista("listaPrivada");
//		ctrl.agregarVideo("base", lista);
//		ArrayList<DtVideo> videos = ctrl.videosEnLista(lista);
//		ArrayList<DtVideo> esperados = new ArrayList<DtVideo>();
//		esperados.add(videoBase);
//		assertEquals(videos, esperados);
	}
	@Test
	public void videosEnListaPublica() {
		
	}
	@Test
	public void quitarVideo() {
		//Agrego un video para quitarlo
		//Seleccionar Dueño de Video
		ctrl.seleccionarUsuario("user1");
		//Seleccionar Dueño de Lista
		ctrl.seleccionarUsuario("user1");
		DtLista l = ctrl.seleccionarLista("listaPublica");
		ctrl.agregarVideo("base", l);

		//Lo Quito
		//Seleccionar Dueño de Video
		ctrl.seleccionarUsuario("user1");
		//Seleccionar Dueño de Lista
		ctrl.seleccionarUsuario("user1");
		ctrl.seleccionarLista("listaPublica");
		DtVideo v = ctrl.seleccionarVideo("base");
		ctrl.quitarVideo(v);
		
		//TODO
		//El assert necesita checkear los videos en lista, queda pendiente el arreglo de videosEnLista()
	}
	@Test
	public void seleccionarVideo() {
		ctrl.seleccionarUsuario("user1");
		DtVideo v = ctrl.seleccionarVideo("base");
		assertEquals(v, videoBase);
	}
	@Test
	public void listarVideos() {
		ctrl.seleccionarUsuario("user1");
		ArrayList<String> videos = ctrl.listarVideos();
		ArrayList<String> esperados = new ArrayList<String>();
		esperados.add("base");
		assertEquals(videos, esperados);
	}
	@Test
	public void editarVideo() {
		ctrl.seleccionarUsuario("user1");
		DtVideo v1 = ctrl.seleccionarVideo("base");
		DtVideo v2 = v1;
		//Categoria y Fecha Pal Cambio
		ctrl.altaCategoria("catNew");
		Categoria cat = new Categoria();
		cat.setNombre("catNew");
		Date newDate = new Date();
		//Nuevo Dt Con Datos Nuevos
		DtVideo cambio = new DtVideo(90, "nuevoNombre", !v2.getPrivado(), canal2.getNombre(), "nuevaDesc", v2.getDuracion()+1, "catNew",
			newDate, "nuevoUrl");
		//Hago el cambio
		ctrl.editarVideo(cambio);
		//Checkeo que todo cambie
		//FIXME esto es dependiente de que el nombre cambie bien
		DtVideo v3 = ctrl.seleccionarVideo(cambio.getNombre());
		boolean sigue = false;
		if (v3.getNombre() == v2.getNombre() ||
			v3.getPrivado() == v2.getPrivado() ||
			v3.getUrl() == v2.getUrl() ||
			v3.getFechaPub() == v2.getFechaPub() ||
			v3.getDescripcion() == v2.getDescripcion() ||
			v3.getDuracion() == v2.getDuracion() ||
			v3.getCategoria() == v2.getCategoria()) 
		{
			sigue = true;
		}
		assertTrue(!sigue);
	}
	@Test
	public void agregarVideo() {
		//Seleccionar Dueño de Video
		ctrl.seleccionarUsuario("user1");
		//Seleccionar Dueño de Lista
		ctrl.seleccionarUsuario("user1");
		DtLista l = ctrl.seleccionarLista("listaPublica");
		assertTrue(ctrl.agregarVideo("base", l));
	}
	@Test
	public void consultarVideo() {
		
	}
	@Test
	public void listarVideosPublicos() {
		HashMap<Integer, String> vs = ctrl.listarVideosPublicos();
		ArrayList<String> esperados = new ArrayList<String>();
		esperados.add("base");
//		assertEquals(videos, esperados);	
	}
	@Test
	public void buscarVideosPublicos() {
		
	}
	@Test
	public void listarVideosPrivados() {
		HashMap<Integer, String> vs = ctrl.listarVideosPrivados("user1");
		HashMap<Integer, String> esperados = new HashMap<Integer, String>();
		esperados.put(1, "base");
		assertEquals(vs, esperados);
	}
	@Test
	public void findVideo() {
		
	}
	@Test
	public void valorarVideoPublico() {
		ctrl.seleccionarUsuario("user1");
		DtVideo video = ctrl.seleccionarVideo("base");
		//Valorarlo
		ctrl.valorarVideoPublico(video.getId(), "user2", true);
		DtVideo video2 = ctrl.seleccionarVideo("base");
		//valoraciones positivos esta dando vacio
		ArrayList<String> valoraciones = video2.getValoracionesPositivas();
		ArrayList<String> esperadas = new ArrayList<String>();
		esperadas.add("user2");
		assertEquals(valoraciones, esperadas);
	}
	@Test
	public void findDuenioVideo() {
		
	}
	@Test
	public void seleccionarCategoria() {
		
	}
	@Test
	public void altaCategoria() {
		assertTrue(ctrl.altaCategoria("nuevaCat"));
//		ctrl.seleccionarCategoria("nuevaCat");
//		ctrl.seleccionarUsuario("user1");
//		Date fecha = new Date();
//		ctrl.ingresarVideo("categoria", 123, "url", "desc", fecha);
//		ctrl.seleccionarUsuario("user1");
//		DtVideo v = ctrl.seleccionarVideo("categoria");
//		assertEquals("nuevaCat", v.getNombre());
	}
	@Test
	public void listarCategorias() {
		ArrayList<String> cates = ctrl.listarCategorias();
		ArrayList<String> esperadas = new ArrayList<String>();
		esperadas.add("cat1");
		esperadas.add("cat2");
		assertEquals(cates, esperadas);
	}
	@Test
	public void videosXCat() {
		
	}
	@Test
	public void videosXCatPublicos() {
		
	}
	@Test
	public void seleccionarComentario() {
		
	}
	@Test
	public void ingresarComentario() {
		
	}
	@Test
	public void mostrarComentarios() {
		
	}
}

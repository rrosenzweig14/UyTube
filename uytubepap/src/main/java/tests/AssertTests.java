
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import datatypes.DtCanal;
import datatypes.DtComentario;
import datatypes.DtLista;
import datatypes.DtUsuario;
import datatypes.DtVideo;
import logica.Categoria;
import interfaces.Fabrica;
import interfaces.IControlador;

public class AssertTests {

	private IControlador ctrl = Fabrica.getIControlador();
	DtCanal canal1;
	DtCanal canal2;
	DtVideo videoBase;
	DtVideo basePub;
	DtUsuario usuarioBase;
	DtLista listaPrivada;
	DtLista listaPublica;

	@Before
	public void inicializarTest() {
		Date date = new Date();
		//Caso de uso para cuando no hay usuarios y buscas una lista
		ctrl.findDuenioLista(58);
		// Un Par de Canales
		canal1 = new DtCanal("U1canal", "desc", "user1", true);
		canal2 = new DtCanal("U2canal", "desc", "user2", false);
		// Crear Usuario
		if (ctrl.seleccionarUsuario("user1") == null) {
			ctrl.ingresarUsuario("user1", "user1@gmail.com", "123", "user1", "1", date, "img", canal1);
		}
		if (ctrl.seleccionarUsuario("user2") == null) {
			ctrl.ingresarUsuario("user2", "user2@gmail.com", "123", "user2", "2", date, "img", canal2);
		}
		usuarioBase = ctrl.seleccionarUsuario("user1");
		// Crear Categoria
		ctrl.altaCategoria("cat1");
		ctrl.altaCategoria("cat2");
		// Crear Video Privado
		if (ctrl.seleccionarVideo("base") == null) {
			ctrl.seleccionarCategoria("cat1");
			ctrl.seleccionarUsuario("user1");
			Date fecha = new Date();
			ctrl.ingresarVideo("base", 123, "url", "desc", fecha);
			ctrl.seleccionarUsuario("user1");
		}
		videoBase = ctrl.seleccionarVideo("base");
		// Crear Video Publico
		if (ctrl.seleccionarVideo("basePub") == null) {
			ctrl.seleccionarCategoria("cat1");
			ctrl.seleccionarUsuario("user1");
			Date fecha2 = new Date();
			ctrl.ingresarVideo("basePub", 123, "url", "desc", fecha2);
			ctrl.seleccionarUsuario("user1");
			DtVideo basePubAux = new DtVideo(videoBase.getId(), "basePub", false, canal1.getNombre(), videoBase.getDescripcion(),
					videoBase.getDuracion(), videoBase.getCategoria(), videoBase.getFechaPub(), videoBase.getUrl(), 0, date);
			basePub = ctrl.seleccionarVideo("basePub");
			ctrl.editarVideo(basePubAux);
		}
		basePub = ctrl.seleccionarVideo("basePub");
		// Lista Base
		ctrl.crearLista(usuarioBase, "listaPrivada", true, "cat1");
		ctrl.crearLista(usuarioBase, "listaPublica", true, "cat1");
		listaPrivada = ctrl.seleccionarLista("listaPrivada");
		listaPublica = ctrl.seleccionarLista("listaPublica");

		ctrl.login("user2", "123");
		ctrl.finCasoUso();
	}

	@After
	public void cleanAll() {
	}

	// TESTS DE VIDEO
	@Test
	public void ingresarVideo() {
		ctrl.altaCategoria("cat");
		ctrl.seleccionarCategoria("cat");
		ctrl.seleccionarUsuario("user1");
		Date fecha = new Date();
		boolean added = ctrl.ingresarVideo("videoIngresar", 123, "url", "desc", fecha);
		ctrl.seleccionarUsuario("user1");
		DtVideo vid = ctrl.seleccionarVideo("videoIngresar");
		assertEquals(vid.getNombre(), "videoIngresar");
	}

	@Test
	public void valorarVideo() {
		Date fecha = new Date();
		DtCanal canalValorar = new DtCanal("canalValorar", "desc", "user2", false);
		ctrl.ingresarUsuario("usuarioValorar", "mailValorar@gmail.com", "123", "usuario", "valorar", fecha, null, canalValorar);
		ctrl.seleccionarUsuario("usuarioValorar");
		ctrl.ingresarVideo("videoValorar", 123, "url", "desc", fecha);
		ctrl.seleccionarUsuario("usuarioValorar");
		ctrl.seleccionarVideo("videoValorar");
		// Valorarlo
		ctrl.valorarVideo("user1", true);
		ctrl.valorarVideo("user1", true);
		DtVideo video = ctrl.seleccionarVideo("videoValorar");
		// valoraciones positivos esta dando vacio
		ArrayList<String> valoraciones = video.getValoracionesPositivas();
		ArrayList<String> esperadas = new ArrayList<String>();
		esperadas.add("user1");
		assertEquals(valoraciones, esperadas);
	}

	@Test
	public void videosEnLista() {
		ctrl.seleccionarUsuario("user1");
		ctrl.seleccionarUsuario("user1");
		DtLista lista = ctrl.seleccionarLista("listaPrivada");
		ctrl.agregarVideo("base", lista);
		ctrl.seleccionarUsuario("user1");
		ArrayList<DtVideo> videos = ctrl.videosEnLista(lista);
		ArrayList<DtVideo> esperados = new ArrayList<DtVideo>();
		esperados.add(videoBase);
		assertEquals(videos, esperados);
	}

	@Test
	public void videosEnListaPublica() {
		ctrl.seleccionarUsuario("user1");
		ctrl.seleccionarUsuario("user1");
		DtLista lista = ctrl.seleccionarLista("listaPublica");
		ctrl.agregarVideo("base", lista);
		ctrl.seleccionarUsuario("user1");
		ArrayList<DtVideo> videos = ctrl.videosEnListaPublica(lista);
		ArrayList<DtVideo> esperados = new ArrayList<DtVideo>();
		esperados.add(basePub);
		esperados.add(videoBase);
		assertEquals(videos, esperados);
	}

	@Test
	public void quitarVideo() {
		// Agrego un video para quitarlo
		// Seleccionar Dueño de Video
		ctrl.seleccionarUsuario("user1");
		// Seleccionar Dueño de Lista
		ctrl.seleccionarUsuario("user1");
		DtLista l = ctrl.seleccionarLista("listaPublica");
		ctrl.agregarVideo("base", l);

		// Lo Quito
		// Seleccionar Dueño de Video
		ctrl.seleccionarUsuario("user1");
		// Seleccionar Dueño de Lista
		ctrl.seleccionarUsuario("user1");
		ctrl.seleccionarLista("listaPublica");
		DtVideo v = ctrl.seleccionarVideo("base");
		ctrl.quitarVideo(v);

		// TODO
		// El assert necesita checkear los videos en lista, queda pendiente el arreglo
		// de videosEnLista()
	}

	@Test
	public void seleccionarVideo() {
		ctrl.seleccionarUsuario("user1");
		// fallo
		DtVideo v1 = ctrl.seleccionarVideo("ninguno");
		// acierto
		DtVideo v = ctrl.seleccionarVideo("base");
		assertEquals(v, videoBase);
	}

	@Test
	public void listarVideos() {
		ctrl.seleccionarUsuario("user1");
		ArrayList<String> videos = ctrl.listarVideos();
		ArrayList<String> esperados = new ArrayList<String>();
		esperados.add("base");
		esperados.add("basePub");
		assertEquals(videos, esperados);
	}

	@Test
	public void editarVideo() {
		ctrl.seleccionarUsuario("user1");
		DtVideo v1 = ctrl.seleccionarVideo("base");
		DtVideo v2 = v1;
		// Categoria y Fecha Pal Cambio
		ctrl.altaCategoria("catNew");
		Categoria cat = new Categoria();
		cat.setNombre("catNew");
		Date newDate = new Date();
		// Nuevo Dt Con Datos Nuevos
		DtVideo cambioFallo = new DtVideo(90, "base2", !v2.getPrivado(), canal1.getNombre(), "nuevaDesc",
				v2.getDuracion() + 1, "catNew", newDate, "nuevoUrl",0,newDate);
		// FIXME no logro hacer que falle
		DtVideo cambio = new DtVideo(90, "nuevoNombre", !v2.getPrivado(), canal2.getNombre(), "nuevaDesc",
				v2.getDuracion() + 1, "catNew", newDate, "nuevoUrl", 0, newDate);
		// Fallo el cambio
		ctrl.editarVideo(cambioFallo);
		// Hago el cambio
		ctrl.editarVideo(cambio);
		// Checkeo que todo cambie
		// FIXME esto es dependiente de que el nombre cambie bien
		DtVideo v3 = ctrl.seleccionarVideo(cambio.getNombre());
		boolean sigue = false;
		if (v3.getNombre() == v2.getNombre() || v3.getPrivado() == v2.getPrivado() || v3.getUrl() == v2.getUrl()
				|| v3.getFechaPub() == v2.getFechaPub() || v3.getDescripcion() == v2.getDescripcion()
				|| v3.getDuracion() == v2.getDuracion() || v3.getCategoria() == v2.getCategoria()) {
			sigue = true;
		}
		assertTrue(!sigue);
	}

	@Test
	public void agregarVideo() {
		// Seleccionar Dueño de Video
		ctrl.seleccionarUsuario("user1");
		// Seleccionar Dueño de Lista
		ctrl.seleccionarUsuario("user1");
		DtLista l = ctrl.seleccionarLista("listaPublica");
		assertTrue(ctrl.agregarVideo("base", l));
	}

	@Test
	public void consultarVideo() {
		// CASO DE USO CON LISTA
		// Seleccionar Dueño de Video
		ctrl.seleccionarUsuario("user1");
		// Seleccionar Dueño de Lista
		ctrl.seleccionarUsuario("user1");
		DtLista l = ctrl.seleccionarLista("listaPublica");
		ctrl.agregarVideo(basePub.getNombre(), l);
		ctrl.finCasoUso();
		ctrl.seleccionarUsuario("user1");
		ctrl.seleccionarLista("listaPublica");
		ctrl.consultarVideo(basePub.getNombre());
		ctrl.finCasoUso();

		// CASO DE USO SIN LISTA
		ctrl.seleccionarUsuario("user1");
		DtVideo res = ctrl.consultarVideo("base");

		assertEquals(videoBase, res);
	}

	@Test
	public void listarVideosPublicos() {
		HashMap<Integer, String> vs = ctrl.listarVideosPublicos();
		Map<Integer, String> esperados = new HashMap<Integer, String>();
		esperados.put(basePub.getId(), basePub.getNombre());
		// FIXME Considera videos agregados en otros test
		assertEquals(vs, esperados);
	}

	@Test
	public void buscarVideosPublicos() {
		ArrayList<DtVideo> videos = ctrl.buscarVideosPublicos(basePub.getNombre());
		ArrayList<DtVideo> esperados = new ArrayList<DtVideo>();
		esperados.add(basePub);
		// FIXME Considera videos agregados en otros test
		assertEquals(videos, esperados);
	}

	@Test
	public void listarVideosPrivados() {
		HashMap<Integer, String> vs = ctrl.listarVideosPrivados("user1");
		HashMap<Integer, String> esperados = new HashMap<Integer, String>();
		esperados.put(5, "videoIngresar");
		esperados.put(6, "base");
		assertEquals(vs, esperados);
	}

	@Test
	public void findVideo() {
		// Caso fallido
		ctrl.findVideo(9235);
		// Caso acertado
		DtVideo v = ctrl.findVideo(videoBase.getId());
		assertEquals(v, videoBase);
	}

	@Test
	public void valorarVideoPublico() {
		ctrl.seleccionarUsuario("user1");
		DtVideo video = ctrl.seleccionarVideo("base");
		// Valorarlo
		// Este falla por nombre e id
		ctrl.valorarVideoPublico(1235125, "userasjghaskdjbg", true);
		// Este acierta
		ctrl.valorarVideoPublico(video.getId(), "user2", true);
		// Este encuentra uno ya creado
		ctrl.valorarVideoPublico(video.getId(), "user2", true);
		DtVideo video2 = ctrl.seleccionarVideo("base");
		// valoraciones positivos esta dando vacio
		ArrayList<String> valoraciones = video2.getValoracionesPositivas();
		ArrayList<String> esperadas = new ArrayList<String>();
		esperadas.add("user2");
		assertEquals(valoraciones, esperadas);
	}

	@Test
	public void findDuenioVideo() {
		// Caso Fallido
		ctrl.findDuenioVideo(123123);
		// Caso Fructifero
		String duenio = ctrl.findDuenioVideo(videoBase.getId());
		assertEquals(duenio, "user1");
	}

	@Test
	public void seleccionarCategoria() {
		ctrl.seleccionarCategoria("noexiste");
		ctrl.seleccionarCategoria("cat1");
	}

	@Test
	public void altaCategoria() {
		ctrl.altaCategoria("nuevaCat");
		ctrl.seleccionarCategoria("nuevaCat");
		ctrl.seleccionarUsuario("user1");
		Date fecha = new Date();
		ctrl.ingresarVideo("categoria", 123, "url", "desc", fecha);
		ctrl.seleccionarUsuario("user1");
		DtVideo v = ctrl.seleccionarVideo("categoria");
		assertEquals("nuevaCat", v.getCategoria());
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
		Map<String, String> videos = ctrl.videosXCat("cat1");
		Map<String, String> esperados = new HashMap<String, String>();
		esperados.put("base", "user1");
		esperados.put("basePub", "user1");
		// FIXME Considera videos agregados en otros test
		assertEquals(videos, esperados);
	}

	@Test
	public void videosXCatPublicos() {
		Map<String, String> videos = ctrl.videosXCatPublicos(basePub.getCategoria());
		Map<String, String> esperados = new HashMap<String, String>();
		esperados.put(basePub.getId() + ";" + basePub.getNombre(), "user1");
		assertEquals(videos, esperados);
	}

	@Test
	public void seleccionarComentario() {
		// CHECK ingresarComentario
	}

	@Test
	public void ingresarComentario() {
		// ESTE TEST INCLUYE EL TESTEO DE SELECCIONAR COMETARIO
		Date fecha = new Date();
		// Caso Comentario Sin Nick
		DtComentario aux0 = new DtComentario("noexiste", "comentario", fecha);
		ctrl.ingresarComentario(aux0);
		// Caso Ingresar E Ingresar A Comentario Seleccionado
		DtComentario aux = new DtComentario(usuarioBase.getNombre(), "comentario", fecha);
		ctrl.seleccionarUsuario("user1");
		ctrl.seleccionarVideo("base");
		ctrl.ingresarComentario(aux);
		// Ingresar A Seleccionado
		DtComentario subAux = new DtComentario(usuarioBase.getNombre(), "subcomentario", fecha);
		ctrl.seleccionarUsuario("user1");
		ctrl.seleccionarVideo("base");
		ctrl.seleccionarComentario(aux);
		ctrl.ingresarComentario(subAux);
		//FIXME - no está entrando al else del "if cometnario seleccionado==null)
	}

	@Test
	public void mostrarComentarios() {
		// TODO Falta ver como checkear el JTree
		// Se agregar un comentario para mostrar
//		Date fecha = new Date();
//		DtComentario aux = new DtComentario(usuarioBase.getNombre(), "comentario", fecha);
//		ctrl.seleccionarUsuario("user1");
//		ctrl.seleccionarVideo("base");
//		ctrl.ingresarComentario(aux);
//		//Se Muestan los Comentarios
//		ctrl.seleccionarVideo("base");
//		JTree comentarios = ctrl.mostrarComentarios();
//		JTree esperados = new JTree();
	}

	@Test
	public void seguirUsuario() {
		DtUsuario user1 = ctrl.seleccionarUsuario("user1");
		ctrl.seleccionarUsuario("user2");
		ctrl.seguirUsuario();
		DtUsuario user2 = ctrl.seleccionarUsuario("user2");
		HashMap<String, DtUsuario> seguidores = user2.getSeguidores();
		Map<String, DtUsuario> esperados = new HashMap<String, DtUsuario>();
		esperados.put("user1", user1); 
		assertEquals(esperados, seguidores);
	}

	@Test
	public void dejarSeguir() {
		//Primero Seguimos un Usuario
		ctrl.seleccionarUsuario("user1");
		ctrl.seleccionarUsuario("user2");
		ctrl.seguirUsuario();
		ctrl.finCasoUso();
		ctrl.seleccionarUsuario("user1");
		ctrl.seleccionarUsuario("user2");
		ctrl.dejarSeguir();
		DtUsuario user2 = ctrl.seleccionarUsuario("user2");
		HashMap<String, DtUsuario> seguidores = user2.getSeguidores();
		Map<String, DtUsuario> esperados = new HashMap<String, DtUsuario>();
		//Ni idea porque no funciona
		assertEquals(seguidores, esperados);
	}

	@Test
	public void seleccionarUsuario() {
		//Fallo
		ctrl.seleccionarUsuario("fallo");
		//Acierto
		DtUsuario user1 = ctrl.seleccionarUsuario("user1");
		assertEquals(user1,usuarioBase);
	}

	@Test
	public void listarUsuarios() {
		ArrayList<String> listaEsperada = new ArrayList<String>();
		listaEsperada.add("user1");
		listaEsperada.add("user2");
		listaEsperada.add("ingresarUsuarioUsuario");
		ArrayList<String> listaObtenida = ctrl.listarUsuarios();
		assertEquals(listaObtenida, listaEsperada);
	}

	@Test
	public void listarDatosUsuario() {
		//Creo canal y usuario para el test
		Date fecha = new Date();
		DtCanal canalListarDatos= new DtCanal("canalListarDatos", "descListar", "listarDatosUsuario", true);
		ctrl.ingresarUsuario("listarDatosUsuario", "listarUsuario@gmail.com", "456", "list", "ar", fecha, null, canalListarDatos);	
		//Hago el cambio
		DtUsuario listarDatos = ctrl.seleccionarUsuario("listarDatosUsuario");
		Map<DtUsuario, DtCanal> datos1 = new HashMap<DtUsuario, DtCanal>();
		datos1.put(listarDatos, canalListarDatos);
		Map<DtUsuario, DtCanal> datos = ctrl.listarDatosUsuario("listarDatosUsuario");
		//FIXME Trae mismo datos pero id diferente ??
		assertEquals(datos1, datos);
	}

	@Test
	public void ingresarUsuario() {
		Date fecha = new Date();
		DtCanal canalIngresarUsuario = new DtCanal("Ucanal", "desc", "user2", true);
		ctrl.ingresarUsuario("ingresarUsuarioUsuario", "pr@gmail.com", "456", "Pru", "eba", fecha, null, canalIngresarUsuario);
		DtUsuario res = ctrl.seleccionarUsuario("ingresarUsuarioUsuario");
		assertEquals(res.getNickname(), "ingresarUsuarioUsuario");
	}

	@Test
	public void modificarUsuarioCanal() {
		Date fecha = new Date();
		DtCanal canalModificarCanal = new DtCanal("modCanal", "desc", "modificarCanalUsuario", true);
		DtCanal canalModificarCanal2 = new DtCanal("modCanal2", "desc", "modificarCanalUsuario", true);
		ctrl.ingresarUsuario("modificarCanalUsuario", "modificaralgo@gmail.com", "456", "algo", "algo", fecha, null, canalModificarCanal);
		DtUsuario modificarCanalUsuario2 = new DtUsuario("modificarCanalUsuario", "modificar@gmail.com", "123", "modificar", "canal", fecha, null);
		ctrl.seleccionarUsuario("modificarCanalUsuario");
		ctrl.modificarUsuarioCanal(modificarCanalUsuario2, canalModificarCanal2);
		DtUsuario res = ctrl.seleccionarUsuario("modificarCanalUsuario");
		//TODO faltaria hacer un checkeo extenso de que todos los campos cambien
		assertEquals(res.getNombre(),"modificar");	
	}

	@Test
	public void listarCanalesPublicos() {
		HashMap<String,String> obtenidos = ctrl.listarCanalesPublicos();
		Map<String,String> esperados = new HashMap<String,String>();
		esperados.put("user2", "U2canal");
	}

	@Test
	public void buscarCanalesPublicos() {
		ArrayList<DtCanal> obtenidos = ctrl.buscarCanalesPublicos("canal2");
		ArrayList<DtCanal> esperados = new ArrayList<DtCanal>();
		assertEquals(esperados, obtenidos);
	}

	@Test
	public void crearLista() {
		boolean resObtenido;
		ctrl.ingresarTipoLista(true);
		resObtenido=ctrl.crearLista(usuarioBase, "ListaDefecto", false, "cat3");
		resObtenido=resObtenido && !ctrl.crearLista(usuarioBase, "ListaDefecto", false, "cat3");
		resObtenido=resObtenido && ctrl.crearLista(usuarioBase, "ListaDefecto2", false, "cat1");
		ctrl.ingresarTipoLista(false);
		resObtenido=resObtenido && ctrl.crearLista(usuarioBase, "ListaDefecto3", false, "cat1");
		resObtenido=resObtenido && !ctrl.crearLista(usuarioBase, "ListaDefecto3", false, "cat1");
	}

	@Test
	public void seleccionarLista() {
		ctrl.seleccionarUsuario("user1");
		DtLista listaO = ctrl.seleccionarLista("listaPublica");
		assertEquals(listaPublica, listaO);
	}

	@Test
	public void ingresarTipoLista() {
		//Included in crearLista
	}

	@Test
	public void listarListasReproduccion() {
		ArrayList<DtLista> obtenidas= ctrl.listarListasReproduccion(usuarioBase);
		ArrayList<DtLista> esperadas= new ArrayList<DtLista>();
		//Remuevo 0 porque la lista Historial es inseleccionable
		obtenidas.remove(0);
		ctrl.seleccionarUsuario("user1");
		esperadas.add(ctrl.seleccionarLista("listaPrivada"));
		ctrl.seleccionarUsuario("user1");
		esperadas.add(ctrl.seleccionarLista("listaPublica"));
		ctrl.seleccionarUsuario("user1");
		esperadas.add(ctrl.seleccionarLista("listaListarPub"));
		assertEquals(obtenidas, esperadas);
	}

	@Test
	public void listarListasParticulares() {
		List<DtLista> resObtenido = ctrl.listarListasParticulares(usuarioBase);
		List<DtLista> resEsperado = new ArrayList<DtLista>();
		resEsperado.add(listaPrivada);
		resEsperado.add(listaPublica);
		ctrl.seleccionarUsuario("user1");
		resEsperado.add(ctrl.seleccionarLista("listaListarPub"));
		assertEquals(resEsperado, resObtenido);
	}

	@Test
	public void modificarListaParticular() {
		ctrl.altaCategoria("modificarListaCat");
		DtLista listaModificar = new DtLista(listaPublica.getId(), "listaPublica",false, false, "modificarListaCat");
		ctrl.seleccionarUsuario("user1");
		ctrl.modificarListaParticular(listaPublica, listaModificar);
		ctrl.seleccionarUsuario("user1");
		DtLista res = ctrl.seleccionarLista("listaPublica");
		assertEquals(res.getCategoria(), listaModificar.getCategoria());
	}

	@Test
	public void listasXCat() {
		Map<String, String> obtenidas = ctrl.listasXCat("cat1");
		Map<String, String> esperadas = new HashMap<String,String>();
		esperadas.put("listaPrivada", "user1");
		esperadas.put("listaListarPub", "user1");
		assertEquals(obtenidas, esperadas);
	}

	@Test
	public void listasXCatPublicas() {
		ctrl.crearLista(usuarioBase, "listaListasXPub", false, "cat1");
		Map<String, String> obtenidos = ctrl.listasXCatPublicas("cat1");
		Map<String, String> esperados = new HashMap<String, String>();
		esperados.put("17;ListaDefecto3", "user1");
		esperados.put("24;listaListasXPub", "user1");
		esperados.put("7;listaListarPub", "user1");
		assertEquals(obtenidos, esperados);
	}

	@Test
	public void listarListasPublicas() {
		ctrl.crearLista(usuarioBase, "listaListarPub", false, "cat1");
		Map<Integer, String> obtenidas = ctrl.listarListasPublicas();
		Map<Integer, String> esperadas = new HashMap<Integer, String>();
		esperadas.put(7, "listaListarPub");
		assertEquals(obtenidas, esperadas);
	}

	@Test
	public void buscarListasPublicas() {
		 ArrayList<DtLista> obtenidas = ctrl.buscarListasPublicas("cat1");
		 ArrayList<DtLista> esperadas = new ArrayList<DtLista>();
		 assertEquals(obtenidas, esperadas);
		//FIXME no encuentro porque no trae nada
	}
	
	@Test
	public void crearListaHistorial() {
		 ctrl.crearListaHistorial();
		//FIXME - revisar como hacer la comparación
	}
	
	@Test
	public void findLista() {
		DtLista resObtenido = ctrl.findLista(listaPublica.getId());
		assertEquals(listaPublica, resObtenido);
		resObtenido = ctrl.findLista(5652);
		assertEquals(null, resObtenido);
	}

	@Test
	public void findDuenioLista() {
		String resObtenido = ctrl.findDuenioLista(listaPublica.getId());
		assertEquals(usuarioBase.getNickname(), resObtenido);
	}

	@Test
	public void finCasoUso() {
		// FIXME Ver como hacer que funcione
	}
	
	@Test
	public void bajaUsuario() {
		Date fecha = new Date();
		DtCanal canal = new DtCanal("bajaCanal", "desc", "user1", true);
		ctrl.ingresarUsuario("bajaUsuario", "bajaEmail@gmail.com", "123", "baja", "usuario", fecha, null, canal);
		ctrl.bajaUsuario("bajaUsuario");
		DtUsuario resObtenido = ctrl.seleccionarUsuario("bajaUsuario");
		assertNotNull(resObtenido);
	}

	@Test
	public void login() {
		Boolean res = ctrl.login("user1@gmail.com", "123");
		assertTrue(res);
	}

}

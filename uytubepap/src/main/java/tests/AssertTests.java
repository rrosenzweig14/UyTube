
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
		// Un Par de Canales
		canal1 = new DtCanal("U1canal", "desc", "user1", true);
		canal2 = new DtCanal("U2canal", "desc", "user2", true);
		// Un Par de Usuarios
//		userG = new DtUsuario("Giu", "gm@gmail.com", "123", "Giu", "Es", date, null);
//		userR = new DtUsuario("Ro", "rr@gmail.com", "123", "Ro", "Ros", date, null);
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
		boolean vid = ctrl.ingresarVideo("ingresar", 123, "url", "desc", fecha);
		assertTrue(vid);
	}

	@Test
	public void valorarVideo() {
		ctrl.seleccionarUsuario("user1");
		DtVideo video = ctrl.seleccionarVideo("base");
		// Valorarlo
		ctrl.valorarVideo("user2", true);
		ctrl.valorarVideo("user2", true);
		DtVideo video2 = ctrl.seleccionarVideo("base");
		// valoraciones positivos esta dando vacio
		ArrayList<String> valoraciones = video2.getValoracionesPositivas();
		ArrayList<String> esperadas = new ArrayList<String>();
		esperadas.add("user2");
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
		esperados.add(videoBase);
		// FIXME Considera videos agregados en otros test
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
		esperados.put(1, "base");
		// FIXME Considera videos agregados en otros test
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
		assertTrue(!ctrl.altaCategoria("nuevaCat"));
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
		Map<String, String> videos = ctrl.videosXCat("cat1");
		Map<String, String> esperados = new HashMap<String, String>();
		esperados.put("base", "user1");
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

	// TESTS DE GEMA

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
		assertEquals(seguidores, esperados);
	}

	@Test
	public void seleccionarUsuario() {
		// FIXME ver como funciona no veo el sentido
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
		ArrayList<String> listaObtenida = ctrl.listarUsuarios();
		assertEquals(listaObtenida, listaEsperada);
	}

	@Test
	public void listarDatosUsuario() {
		Map<DtUsuario, DtCanal> datos1 = new HashMap<DtUsuario, DtCanal>();
		datos1.put(usuarioBase, canal1);
		Map<DtUsuario, DtCanal> datos = ctrl.listarDatosUsuario(usuarioBase.getNombre());
		//FIXME Agarra uno de los tantos user1
		assertEquals(datos1, datos);
	}

	@Test
	public void ingresarUsuario() {
//		boolean resObtenido;
//		Date fecha = new Date();
//		resObtenido = ctrl.ingresarUsuario("Prueba", "pr@gmail.com", "456", "Pru", "eba", fecha, null, null);
//		resObtenido = resObtenido && ctrl.ingresarUsuario("Giu", "gm@gmail.com", "123", "Giu", "Es", fecha, null, null);
//		assertEquals(true, resObtenido);
	}

	@Test
	public void modificarUsuarioCanal() {
//		CanalR.setNick("Rodrigo");
//		ctrl.modificarUsuarioCanal(userR, CanalR);
		DtUsuario user2 = ctrl.seleccionarUsuario("Ro");
		// FIXME ver como conseguir un DtCanal
//		DtCanal cnl2= user2.get
//		boolean resObtenido = cnl2.equals(CanalR) && UserR.equals(user2);
//		assertEquals(true,resObtenido);	
	}

	@Test
	public void listarCanalesPublicos() {
		// generar al menos dos canales publicos
		// veriificar que devuelva esos canales
	}

	@Test
	public void buscarCanalesPublicos() {
		ArrayList<DtCanal> resEsperadoA = new ArrayList<DtCanal>();
//		resEsperadoA.add(CanalR);
		ArrayList<DtCanal> resObtenidoA = ctrl.buscarCanalesPublicos("Ro");
		assertEquals(resEsperadoA, resObtenidoA);
	}

	@Test
	public void crearLista() {
		// Â¿this.defecto? | usr.agregarListaDefecto(nombre)
		// Â¿this.defecto? | else
		// else | lst != null (con categoria)
		// else | else
	}

	@Test
	public void seleccionarLista() {
		// crear DtLista con datos de una lista existente
		ctrl.seleccionarUsuario("user1");
		DtLista listaO = ctrl.seleccionarLista("listaPublica");
		assertEquals(listaPublica, listaO);
	}

	@Test
	public void ingresarTipoLista() {
		// FIXME ver como tiene que funcionar
		ctrl.ingresarTipoLista(true);
//		boolean resObtenido=ctrl.defecto;
//		assertEquals(true,resObtenido);
	}

	@Test
	public void listarListasReproduccion() {
		// crear al menos dos listas de reproduccion (necesito 2 publicas y 2 privadas,
		// asÃ­ que ya estarÃ­an)
		// Verificar que devuelva el arraylist que se espera
	}

	@Test
	public void listarListasParticulares() {
		//// Crear al menos dos listas de produccion particualares
		// verificar que devuelva la lista correspondiente
	}

	@Test
	public void modificarListaParticular() {
		DtLista ListaS = listaPublica;
		ListaS.setCategoria("Musical");
		ctrl.modificarListaParticular(listaPublica, ListaS);
		DtLista res = ctrl.seleccionarLista("listaPublica");
		assertEquals(res, ListaS);
	}

	@Test
	public void listasXCat() {
		// verificar que devuelva el mapa esperado
	}

	@Test
	public void listasXCatPulicas() {
		// verificar que devuelva el mapa esperado
	}

	@Test
	public void listarListasPublicas() {
		// crear al menos dos listas publicas
		// verificar que la devuelve
	}

	@Test
	public void buscarListasPublicas() {
		ArrayList<DtLista> obtenida = ctrl.buscarListasPublicas("listaPublica");
		ArrayList<DtLista> esperada = new ArrayList<DtLista>();
		esperada.add(listaPublica);
		assertEquals(esperada, obtenida);
	}

	@Test
	public void findLista() {
		// lista que existe
		// y lista que no existe

	}

	@Test
	public void findDuenioLista() {
		// revisar que devuelva el string esperado
	}

	@Test
	public void finCasoUso() {
		// FIXME Ver como hacer que funcione
//		ctrl.finCasoUso();
//		boolean resObtenido = Controlador.user1.equals(Contorlador.user2);
//		resObtenido= resObtenido && (ctrl.video==null);
//		resObtenido= resObtenido && (ctrl.comentarioSeleccionado==null);
//		resObtenido= resObtenido && (ctrl.lista==null);
//		resObtenido= resObtenido && (ctrl.canal==null);
//		resObtenido= resObtenido && (ctrl.categoria1==null);
//		assertEquals(true,resObtenido);	
	}

	@Test
	public void login() {
		// FIXME Revisar el checkeo
		Boolean resObtenido = ctrl.login("gm@gmail", "123");
		resObtenido = resObtenido && ctrl.login("Giu", "123");
		assertEquals(true, resObtenido);
	}

//MÃ©todo que se ejecuta despues de cada test unitario
	@After
	public void finalizar() {
		// borrar usuario
		// borrar usuario 2
		// borrar videos para usuario 1
		// borrar videos para usuario 2
		// borrar comentario
		// borrar subcomentario
	}

//MÃ©todo despues de todos los tests
//	@AfterClass
//	public void despuesDeTodo() {
//		
//	}
}

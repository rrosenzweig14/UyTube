package tests;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datatypes.DtCanal;
import datatypes.DtLista;
import datatypes.DtUsuario;
import logica.Controlador;

public class AutoTest {
	DtUsuario userG;
	DtUsuario userR;
	DtCanal CanalR;
	DtLista ListaR;
	
	
//	@BeforeClass
//Metodo que se ejecuta antes de todos los before
//	public void antesDeTodo() {
//			
//	Â¿conexion a BD?
// ingresar los datos a la bd
//	
//	}
	
//MÃ©todo que se ejecuta antes de realizarse los tests	
//Se ejecuta antes de cualquier test unitario
	@Before
	public void inicializarTest() {
		userG = new DtUsuario('Giu', 'gm@gmail.com', '123', 'Giu', 'Es', '21/02/1995', null);
		userR = new DtUsuario('Ro', 'rr@gmail.com', '123', 'Ro', 'Ros', '14/03/1995', null);
		CanalR = new DtCanal('Roro', 'des', 'Ro', true);
		
		// crear usuario
		// crear usuario 2
		// crear videos para usuario 1
		// crear videos para usuario 2
		// crear comentario 
		// crear subcomentario
	}
	
	//TENGO DUDA DE SI EL assertEquals SE PUEDE USAR DIRECTAMENTE, POR AHORA USO UN BOOLEAN PARA COMAPRAR, PERO SI SE PUEDE
	//USAR DIRECTAMENTE EL assertEquals SE QUITA EL BOOL.
	@Test
	public void seguirUsuario() {
		UserG.addSeguido('Ro');
		UserR.addSeguidor('Giu');
		
		seleccionarUsuario('Giu');
		seleccionarUsuario('Ro');
		Controlador.seguirUsuario();
		DtUsuario user1=Handler.findUsuario('Giu').getDtUsuario();
		DtUsuario user2=Handler.findUsuario('Ro').getDtUsuario();
		
		boolean resObtenido = UserG.equals(user1) && UserR.equals(user2);
		
		assertEquals(true,resObtenido);
	}

	@Test
	public void dejarSeguir() {
		seleccionarUsuario('Giu');
		seleccionarUsuario('Ro');
		Controlador.seguirUsuario();
		Controlador.dejarSefuir();
		DtUsuario user1=Handler.findUsuario('Giu').getDtUsuario();
		DtUsuario user2=Handler.findUsuario('Ro').getDtUsuario();
		
		boolean resObtenido = UserG.equals(user1) && UserR.equals(user2);
		boolean resEsperado = true;
		
		assertEquals(true,resObtenido);
	}
	
	@Test	
	public void seleccionarUsuario() {
		seleccionarUsuario('Giu');
		seleccionarUsuario('Ro');
		
		DtUsuario user1=Controlador.user1.getDtUsuario();
		DtUsuario user2=Controlador.user2.getDtUsuario();
		
		boolean resObtenido = UserG.equals(user1) && UserR.equals(user2);
		
		assertEquals(true,resObtenido);
	}
	
	@Test
	public void listarUsuarios() {
		ArrayList<String> listaEsperada = new ArrayList<String>();
		resEsperado.add('Giu');
		resEsperado.add('Ro');
		
		ArrayList<String> listaObtenida =Controlador.listarUsuarios();
		
		resObtenido=listaEsperada.equals(listaObtenida);
		
		assertEquals(true,resObtenido);
	}
	
	@Test
	public void listarDatosUsuario() {
		Map<DtUsuario, DtCanal> datosEG = new HashMap<DtUsuario, DtCanal>();
		datosEG.put(userG, null);
		
		boolean resObtenido;
		
		Map<DtUsuario, DtCanal> datosOG = Controlador.listarDatosUsuario('Giu');
		
		DtCanal cnl = new DtCanal('Roro', 'des', 'Ro', true);
		Map<DtUsuario, DtCanal> datosER = new HashMap<DtUsuario, DtCanal>();
		datosER.put(userR, cnl);
		
		Map<DtUsuario, DtCanal> datosOR = Controlador.listarDatosUsuario('Ro);
		
		// Verificar lo que se obtiene
	}
	
	@Test
	public void ingresarUsuario() {
		boolean resObtenido;
		
		resObtenido=Controlador.ingresarUsuario('Prueba', 'pr@gmail.com', '456', 'Pru', 'eba', '01/01/1990', null, null);
		resObtenido= resObtenido && Controlador.ingresarUsuario('Giu', 'gm@gmail.com', '123', 'Giu', 'Es', '21/02/1995', null, null);
		
		assertEquals(true,resObtenido);
	}
	
	@Test
	public void modificarUsuarioCanal() {
		CanalR.setNick('Rodrigo');
	
		Controlador.modificarUsuarioCanal(UserR, CanalR);
		DtUsuario user2=Handler.findUsuario('Ro').getDtUsuario();
		DtCanal cnl2=Handler.findUsuario('Ro').getDtCanal();
			
		boolean resObtenido = cnl2.equals(CanalR) && UserR.equals(user2);
		
		assertEquals(true,resObtenido);	
	}
	
	@Test
	public void listarCanalesPublicos() {
		//generar al menos dos canales publicos
		//veriificar que devuelva esos canales
	}
	
	@Test
	public void buscarCanalesPublicos() {
		ArrayList<DtCanal> resEsperadoA = new ArrayList<DtCanal>();
		resEsperadoA.add(CanalR);
		
		ArratList<DtCanal> resObtenidoA= Controlador.buscarCanalesPublicos('Ro');
		
		boolean resObtenido = resEsperadoA.equals(resObtenidoA);
		
		assertEquals(true,resObtenido);	
	}
	
	@Test
	public void crearLista() {
		//Â¿this.defecto? | usr.agregarListaDefecto(nombre)
		//Â¿this.defecto? | else
		//else | lst != null (con categoria)
		//else | else	
	}
	
	@Test
	public void seleccionarLista() {
		//crear DtLista con datos de una lista existente
		DtLista listaO = Controlador.seleccionarLista('Videos');
		
		boolean resObtenido=listaO.equals(ListaR);
		
		assertEquals(true,resObtenido);	
	}
	
	@Test
	public void ingresarTipoLista() {
		inresarTipoLista(true);
		boolean resObtenido=Controlador.defecto;
		
		assertEquals(true,resObtenido);
	}
	
	@Test
	public void listarListasReproduccion() {
		//crear al menos dos listas de reproduccion (necesito 2 publicas y 2 privadas, asÃ­ que ya estarÃ­an)
		//Verificar que devuelva el arraylist que se espera
	}
	
	@Test
	public void listarListasParticulares() {
		////Crear al menos dos listas de produccion particualares
		//verificar que devuelva la lista correspondiente
	}
	
	@Test
	public void modificarListaParticular() {
		DtLista ListaS=ListaR;
		ListaS.setCategoria('Musical');
		Controlador.modificarListaParticular(ListaR, ListaS);
		boolean resObtenido findLista().equals(ListasS);
		
		assertEquals(true,resObtenido);	
	}
	
	@Test
	public void listasXCat() {
		//verificar que devuelva el mapa esperado
	}
	
	@Test
	public void listasXCatPulicas() {
		//verificar que devuelva el mapa esperado
	}
	
	@Test
	public void listarListasPublicas() {
		//crear al menos dos listas publicas
		//verificar que la devuelve
	}
	
	@Test
	public void buscarListasPublicas() {
		//buscar una de las listas publicas
		//verificar que devuelve esa lista publica
	}
	
	@Test
	public void findLista() {
		//lista que existe
		//y lista que no existe
		
	}
	
	@Test
	public void findDuenioLista() {
		//revisar que devuelva el string esperado
	}
	
	@Test
	public void finCasoUso() {
		Controlador.finCasoUso();
		boolean resObtenido = Constrolador.user1.equals(Contorlador.user2);
		resObtenido= resObtenido && (Controlador.video==null);
		resObtenido= resObtenido && (Controlador.comentarioSeleccionado==null);
		resObtenido= resObtenido && (Controlador.lista==null);
		resObtenido= resObtenido && (Controlador.canal==null);
		resObtenido= resObtenido && (Controlador.categoria1==null)
		assertEquals(true,resObtenido);	
	}
	
	@Test
	public void login() {
		Boolean resObtenido=Controlador.login('gm@gmail', '123');
		resObtenido=resObtenido && Controlador.login('Giu', "123');
		
		assertEquals(true,resObtenido);
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

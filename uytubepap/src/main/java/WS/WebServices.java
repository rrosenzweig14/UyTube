package WS;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import interfaces.Fabrica;
import interfaces.IControlador;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WebServices {
    private Endpoint endpoint = null;
    private IControlador icon = Fabrica.getIControlador();
    //Constructor
    public WebServices(){}

    //Operaciones las cuales quiero publicar

    @WebMethod(exclude = true)
    public void publicar(){
         endpoint = Endpoint.publish("http://localhost:9128/webservices", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }

    @WebMethod
    public String[] listarVideos(){
    	ArrayList<String> lst=icon.listarVideos();
    	String[] aux= (String[]) lst.toArray();
    	return aux;
    }
    
    /*valorarVideoPublico
     *agregarVideo
     *findVideo
     *ingresarVideo
     *seleccionarVideo
     *findDuenioVideo
     *listarVideosPrivados
     *listarVideosPublicos
     *editarVideo
     *
     *seleccionarUsuario
     *ingresarUsuario
     *listarDatosUsuario
     *seguirUsuario
     *dejarSeguir
     *modificarUsuarioCanal
     *listarCanalesPublicos
     *login
     *
     *seleccionarComentario
     *ingresarComentario
     *
     *seleccionarLista
     *ingresarTipoLista
     *crearLista
     *findLista
     *findDuenioLista
     *videosEnLista
     *quitarVideo
     *videosEnListaPublica
     *listarListasParticulares
     *listarListasPublicas
     *modificarListaParticular
     *
     *altaCategoria
     *listarCategorias
     *seleccionarCategoria
     *videosXCatPublicos
     *listasXCatPublicas
     *
     *buscarVideosPublicos
     *buscarCanalesPublicos
     *buscarListasPublicas
     *finCasoUso
     * */
}
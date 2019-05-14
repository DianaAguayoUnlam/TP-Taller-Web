package ar.edu.unlam.tallerweb1.controladores;

import java.awt.List;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Persona;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class ControladorLogin {

	// La anotacion @Inject indica a Spring que en este atributo se debe setear (inyeccion de dependencias)
	// un objeto de una clase que implemente la interface ServicioLogin, dicha clase debe estar anotada como
	// @Service o @Repository y debe estar en un paquete de los indicados en applicationContext.xml
	@Inject
	private ServicioLogin servicioLogin;

	// Este metodo escucha la URL localhost:8080/NOMBRE_APP/login si la misma es invocada por metodo http GET
	@RequestMapping("/login") //login lo asocia con el metodo iralogin
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		// Se agrega al modelo un objeto del tipo Usuario con key 'usuario' para que el mismo sea asociado
		// al model attribute del form que esta definido en la vista 'login'
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		// Se va a la vista login (el nombre completo de la lista se resuelve utilizando el view resolver definido en el archivo spring-servlet.xml)
		// y se envian los datos a la misma  dentro del modelo
		return new ModelAndView("login", modelo);
	}
	
	
	

	
	// Este metodo escucha la URL validar-login siempre y cuando se invoque con metodo http POST
	// El método recibe un objeto Usuario el que tiene los datos ingresados en el form correspondiente y se corresponde con el modelAttribute definido en el
	// tag form:form
	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		// invoca el metodo consultarUsuario del servicio y hace un redirect a la URL /home, esto es, en lugar de enviar a una vista
		// hace una llamada a otro action a través de la URL correspondiente a ésta
		Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario);
		if (usuarioBuscado != null) {
			request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
			return new ModelAndView("redirect:/home");
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo.
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login", model);
	}

	// Escucha la URL /home por GET, y redirige a una vista.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		return new ModelAndView("home");
	}

	// Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la url /login directamente.
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/login");
	}

	
	@RequestMapping("/Hola")
		public ModelAndView hola() {
			return new ModelAndView("elnombrequeyoquiera");
		}
	
	
	
	
	
	
	
	
	
	//***************************    PUNTO 6 DEL TP HIBERNATE    **************************************************

	@RequestMapping("/Muestro/{NombreOperacion}/{texto}")
	public ModelAndView metodoA(@PathVariable("NombreOperacion") String Operacion, @PathVariable ("texto") String texto)
	{

		ModelMap modelo = new ModelMap();
		String var="";
		int var2 = 0;
		
	switch(Operacion)
	{
		case "PasarAMayuscula":
			
			var = texto.toUpperCase();
			
			break;
		
		case "PasarAMinuscula":
			
			var = texto.toLowerCase();
			break;
			
		case "InvertirOrden":
			
			for (int i = texto.length()-1; i>=0; i--){
	           var = var + texto.charAt(i);
			}
	           break;
		
			
		case "CantidadDeCaracteres":
			
			//Sacarle los espacios
			texto.trim();
			texto.replaceAll(" ", "");
			var2 = texto.length();
			var = Integer.toString(var2); 
			
	}


	modelo.put("Operacion", Operacion);
	modelo.put("texto", texto);
	modelo.put("resultado", var);
	return new ModelAndView("MostrarMetodo",modelo);
	}


	//*****************************************************
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 9-5-19*********************************************************************
	//Nombre del URL
	@RequestMapping("/Hola2")
	public ModelAndView hola4() {
		return new ModelAndView("Hola"); //HOLA SE LLAMA LA VISTA 
	}

	
	
	@RequestMapping("/irAgregarPersona")
	public ModelAndView hola3() {
		return new ModelAndView("Hola");
	}
	
	
	
	
	
	
	//otro metodo
	@RequestMapping("/MostrarNombre") //cuando cambian los parametros es el mismo nombre
	public ModelAndView MuestroOtroNombre(@RequestParam("nombre") String Firstname,
			@RequestParam ("ape") String Ape)
	{
		
		ModelMap modelo = new ModelMap();
		
		//Muestro solamente el nombre
		//modelo.put("nombre", Firstname);
		
		//Muestro nombre y apellido
		modelo.put("nombre", Firstname);
		modelo.put("ape", Ape);
									
									//vista 		//Obj
		return new ModelAndView("elnombrequeyoquiera",modelo);
	}
	
	
	
	
	//Uso Path Variable
	
	
	@RequestMapping("/Path/{nombre}/{ape}") //cuando cambian los parametros es el mismo nombre
	public ModelAndView MuestroNombreyApellido(@PathVariable("nombre") String Firstname,
												@PathVariable ("ape") String Ape)
	{
		
		ModelMap modelo = new ModelMap();
		
		modelo.put("nombre", Firstname);
		modelo.put("ape", Ape);
									
									//vista 		//Obj
		return new ModelAndView("elnombrequeyoquiera",modelo);
	}
	
	
	
	
	
	//For repetir palabra
	
	@RequestMapping("/Path2/{nombre}/{num}") //cuando cambian los parametros es el mismo nombre
	public ModelAndView MuestroRepetido(@PathVariable("nombre") String Firstname,
												@PathVariable ("num") int num)
	{
		ArrayList<String> palabras = new ArrayList<String>();
		
		for(int i=0 ; i< num ; i++ )
		{
			palabras.add(Firstname);
		}
		
		ModelMap modelo = new ModelMap();
		modelo.put("listaPalabras", palabras);
		modelo.put("nombre", Firstname);
		modelo.put("num", num);
		
									//vista 		//Obj
		return new ModelAndView("elnombrequeyoquiera",modelo);	
	}
	
	
	
	
	
//Formulario ****************************************************************************************

	
	private ArrayList<Persona> misdatos = new ArrayList<Persona>();
//mostrar form sin objeto persona
/*	
@RequestMapping("/form")
public ModelAndView mostrarFormulario()
{
	//ModelMap modelo = new ModelMap();
	
	return new ModelAndView("Formulario");//modelo
	
}
	
	*/
	
	//mostrar form creando objeto persona.. para que?)
	@RequestMapping("/form")
	public ModelAndView mostrarFormulario()
	{
		ModelMap modelo = new ModelMap();
		Persona persona = new Persona();
		modelo.put("persona", persona);
		return new ModelAndView("Formulario", modelo);//modelo
		
	}
	
	

	
@RequestMapping(value = "/GuardarDatos", method = RequestMethod.POST)
public ModelAndView datosFormulario(@ModelAttribute("PasarFormulario") Persona mip, HttpServletRequest request) //Requerido )
		
{
	
	ModelMap modelo = new ModelMap();
	
		misdatos.add(mip);

	return new ModelAndView("Formulario",modelo);
	
}


@RequestMapping("/Mostrar")
public ModelAndView mostrar()
{
	ModelMap modelo = new ModelMap();
	
	
	modelo.put("Coleccion", misdatos);
	
	return new ModelAndView("Mostrar",modelo);
}
	

	
	
	
//MostrarNombre
		
	@RequestMapping("/Holanombre")
	public ModelAndView mostrarnombre(){
		
		ModelMap model = new ModelMap();
		model.put("nombre", "Diana");
		return new ModelAndView("holanombre",model);
	}
	
	
	@RequestMapping("/Dosobjetos")
	public ModelAndView dosobjetos(){
		
		Usuario user1 = new Usuario();
		user1.setEmail("Pepisima@hotmail.com");
		user1.setRol("admin");
		
		Usuario user2 = new Usuario();
		user2.setEmail("PepisimoTeBulas@hotmail.com");
		user2.setRol("usuario");
		
		ModelMap model = new ModelMap();
		model.put("usuario1", user1);
		model.put("usuario2", user2);
		return new ModelAndView("dosobjetos",model);
	}
	

//Repitiendo ejercicios*******************************************************************	
	
	
@RequestMapping("/formularioCant")
public ModelAndView mostrarDatosyCantidades()
{
	ModelMap modelo = new ModelMap();

	return new ModelAndView("Probando",modelo);
	
}


@RequestMapping("/Completo")
public ModelAndView guardarDatosdelFormularioconCantidades(@ModelAttribute("Datos") PersonaDatos mip)
{
	ModelMap modelo = new ModelMap();
	
	ArrayList<PersonaDatos> mic = new ArrayList<PersonaDatos>();
	
//	for(int i=0; i<m; i++)
//	{
//	mic.add(mip);
//	}
	modelo.put("Coleccion",mic);
	
	return new ModelAndView("Probando",modelo);
}


@RequestMapping("/Mostrando")
public ModelAndView mostrandoLosDatosQueEnvie()
{
	ModelMap modelo = new ModelMap();
	
	ArrayList<PersonaDatos> mic = new ArrayList<PersonaDatos>();
	
	modelo.put("Coleccion", mic);
	
	return new ModelAndView("ViendoProbando",modelo);
	
}
	
	
}

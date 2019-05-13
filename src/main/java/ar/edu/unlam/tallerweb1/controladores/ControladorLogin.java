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

	
//Punto del tp ********************************************************

@RequestMapping("/MostrarCadena/{NombreOperacion}/{texto}")
public ModelAndView metodoA(@PathVariable("NombreOperacion") String operacion, @PathVariable ("texto") String texto)
{

	ModelMap modelo = new ModelMap();
	String var="";
	int var2 = 0;
	
switch(operacion)
{
	case "PasarAMayuscula":
		
		var = texto.toUpperCase();
		
		break;
	
	case "PasarAMinuscula":
		
		var = texto.toLowerCase();

		break;
		
	case "InvertirOrden":
		
		for (int i = texto.length()-1; i>=0; i--){
           	var = texto + texto.charAt(i);
		}

           break;
	
		
	case "CantidadDeCaracteres":
		
	//Sacarle los espacios
		texto.trim();
		texto.replaceAll(" ", "");
		var2 = texto.length();
		var = Integer.toString(var2); 

		break;
}


modelo.put("metodo", metodo);
modelo.put("texto", texto);
modelo.put("resultado", var);
return new ModelAndView("MostrarCadena",modelo);
}






	
	
}

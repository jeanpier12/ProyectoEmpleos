package com.job.job.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.job.job.model.Vacante;
import com.job.job.service.IVacantesService;



@Controller
public class homeControlador {
	
	@Autowired
	//creando un objeto de la interfaz
	private IVacantesService serviceVacantes;
 	@GetMapping("/tabla")
	public 	String mostrarTabla(Model model){
 		//aca estamos utilizando el objeto servicevacantes para obtener la lista de vacantes 
 		//y lo estamos guardando en una lista 
		List<Vacante> lista = serviceVacantes.buscarTodas();
		
		//aca estamos agragando esa lista al modelo 
		//todos los valores de la lista de objetos 
		// se estan guardando en el parametro vacantes 
		//y con este parametro en nuestro html podemos llamar los valores de nuestra lista 
		model.addAttribute("vacantes",lista);
		
		return "tabla";
		
		
	}
	
	@GetMapping("/detalle")
	//en spring mvc para agregar datos al controlador o modelo 
	//debemos agregar al metodo del controlador un una variable de tipo Model 
	//en los metodos de los controladores podemos agregar como parametros varios tipos de datos 
	//y entre ellos esta una variable tipo Model 
	public String mostrarDetalle(Model model) {
	 //de la clase Vacante se instancio el objeto model
		Vacante vacante =new Vacante();
		//Aca le estamos asignando valores al objeto vacante
		vacante.setNombre("Ingeniero de comunicación");
		vacante.setDescripcion("Se solicita ingeniero para dar soporte a intranet");
		vacante.setFecha(new Date());
		vacante.setSalario(9700.0);
		
		//model.addAttribute me permite agregar cualquier tipo de dato a una variable en este caso esa variable se llama vacante
		//este metodo debe 2 parametros  una variable o atributo y un valor 
		//el primer argumento que recibe de este metodo me servira como contenedor 
		//para guardar lo que contenga el segundo argumento el segundo argumento puede tener cualquier tipo de dato
		//en este caso nosotros en el segundo argumento estamos pasando un objeto
		//para mostrar los datos de objeto vacante en la vista  se tiene que utilizar el nombre del primer argumento en la vista
		model.addAttribute("vacante",vacante);
		return "detalle";
	} 

	
	
	@GetMapping("/listado")
	//variable de tipo model con el podemos pasar como parametro cualquier tipo de dato como argumento a este metodo 
	//en resumen el model es un objeto donde podemos guardar todo tipo de datos 
	public String mostrarListado(Model model){
		
        List<String> lista= new LinkedList<String>();
        //agregando valores a la lista
        lista.add("Ingeniero de Sistemas");
        lista.add("Auxiliar de contabilidad");
        lista.add("Vededor");
        lista.add("Arquitecto");
       
        //el  objeto model guarda cualquier tipo de dato 
        //.con el metodo addStribute le estamos agregando al objeto model una lista esta lista y se pueden acceder a los valores de esta lista 
        //"utilizando la variable empleos "
        //elprimer valor que guarda este objeto model 
        //es un nombre generico el caul podemos utilizarlo en la vista  
        // este objeto generico contiene o guarda el valor del segundo parametro 
        // en este caso el segundo parametro es una lista de objetos 
        model.addAttribute("empleos", lista);

        
        //model me permite  crear una variable y a esa variable mandarle cualquier tipo de dato.
return "listado";
	}

	
	
	
	@GetMapping ("/")
	public String mostrarHome(Model model) {
		//con esta lista estamos opteniendo la lista de objetos vacantes utilizando nuestra clase servicevacantes
		List<Vacante> lista = serviceVacantes.buscarTodas();
		//luego agregamos la lista a la varible "vacantes" utilizado  el metodo model.addttribute()
		model.addAttribute("vacantes",lista);
			// por lo tanto en el archivo home.html
		//ya debe estar disponible los datos de la lista
		return "home";
		
		
	}
	
	
	
	
	
	
	

	
		
		
		
	}
	
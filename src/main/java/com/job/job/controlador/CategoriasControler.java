package com.job.job.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.job.job.model.Categoria;
import com.job.job.service.ICategoriasService;

@Controller
public class CategoriasControler {
	
	
	//inclectando la clase servico en categorias
	@Autowired
	//aca tenemos la instacia de nuestra clase de servicio 
	//en este caso se llama serviceVacantes
	private ICategoriasService serviceCategorias;
 
	@GetMapping("/index")
  	public String mostrarIndex(Model model) {
		//aca e creado una lista de objetos que contendran atributos de la clase categoria 
		//esta lista guardara los objetos que contenga el metodo buscarTodas
		List<Categoria> lista=serviceCategorias.buscarTodas();
		 model.addAttribute("categorias", lista);
		return "categorias/listaCategorias";
	}
	
	
	@GetMapping("/create")
	//el objeto categoria de este metodo se esta usando para vincular el objeto categoria con el html
	//para poder mostrar errores atraves de la de vista 
  	public String crear( Categoria categoria) {
		
		//me muestra la vista del formulario para crear una nueva categoria 
		return "categorias/formCategoria";
	}
	
	@PostMapping("/save")
	//este metodo guarda el valor que se ingresa en el imput y el text area
	//loa valores que se inglesan se guardan en el valor del atributo name de las respectivas etiquetas html
	
  	public String guardar(Categoria categoria,BindingResult result,RedirectAttributes attributes) {
	
		
	if(result.hasErrors()) {
			
			//for para ver los errores por consola 
			for(ObjectError error:result.getAllErrors()) {
				
				System.out.println("Ocurrio un error:" + error.getDefaultMessage());
			}
			
			//es caso hubo un error que me devuelva la vista del formulrio
			return "categorias/formCategoria";
		}
		//aca mandamos a llamar nuestra clase de servicio
		//y mandamos a llamar a nuestro metodo guardar 
		//entonces de est forma cuando llegue los valoresz al  objeto categoria
		// se guardan en el objet categoria Vacante  luego en el CategoriasService de agrega este objeto a la lista 
	 serviceCategorias.guardar(categoria);
		//teneos el atribito "msg" para poder desplegarlo
		attributes.addFlashAttribute("msg", "Resgistro Guardado");
		System.out.println("Categorias:" + categoria);
		//redirect me redireciona osea puedo llamar otras url 
		return "redirect:/index";
		
	}
		
		
		
		
		
	}
	
	
	
	


package com.job.job.controlador;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.job.job.model.Vacante;
import com.job.job.service.ICategoriasService;
import com.job.job.service.IVacantesService;
import com.job.job.util.Utileria;
//controlador de la vacantes 
@Controller
public class VacantesController {
	
	//value me permite invocar la propiedad de apllication.prpertis
	//y guardar su valor enla variable ruta 
	//@Value("${empleossapp.ruta.imagenes}")
	//private String ruta;

	@Autowired
	//aca tenemos la instacia de nuestra clase de servicio 
	//en este caso se llama serviceVacantes
	private IVacantesService serviceVacantes;
	//con esta anotación se va a inyectar la instancia de nuestra clase de servico
	@Autowired
	private ICategoriasService serviceCategorias; 
	
	@GetMapping("/vacantes/index")
	public String mostrarIndex(Model model) {
		
		//se utilizo la clase service para obtener la lista de vacantes y la estamos guardando 
		//en un lista 
		List<Vacante> lista = serviceVacantes.buscarTodas();
		//con esta linea estamos agragando las lista de vacantes al modelo 
		//los valores de la lista se pueden encontrar en la interfaz VacanteServicelmpl.java
		model.addAttribute("vacantes", lista);
		return "vacantes/listVacantes";
		
		
	}
	
	
	
	
	
	
	
	
	//formulario de las vacantes
	@GetMapping("/vacantes/create")
	//se coloco como parameto un objeto de tipo bacante para poder  
	//mostrar el mensaje de error por html 
	public String crear(Vacante vacante, Model model) {
		
		//aca le estamos dando a la variable categorias la lista de categorias 
		//que me esta trayendo el metodo buscarTodas()
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
		return "vacantes/formVacante";
	}
	
	//cuando alguien ingresa a esta url se guardan todos los datos del formulrio 
	//al objeto vacante
	@PostMapping("/vacantes/save")
	//como parametro le estamos pasando un objeto de tipo vacante
	//con el fin de que todos los datos del formulario se guarden en el nuevo objeto
	// de tipo vacante 
	public String guardar(Vacante vacante,BindingResult result,RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart ) {
		if(result.hasErrors()) {
			
			
			//for para ver los errores por consola 
			for(ObjectError error:result.getAllErrors()) {
				
				System.out.println("Ocurrio un error:" + error.getDefaultMessage());
			}
			
			return "vacantes/formVacante";
			
		}
		
		if (!multiPart.isEmpty()) {
		// Windows
			String ruta = "c:/empleos/img-vacantes/";
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
			// Procesamos la variable nombreImagen
			vacante.setImagen(nombreImagen); 
			}
			}
		//aca mandamos a llamar nuestra clase de servicio
		//y mandamos a llamar a nuestro metodo guardar 
		//entonces de est forma cuando llegue los valoresz al  objeto vacante 
		// se guardan en el objeto Vacante  luego en VacantesService de agrega este pbjeto a la lista 
		serviceVacantes.guardar(vacante);
		//teneos el atribito "msg" para poder desplegarlo
		attributes.addFlashAttribute("msg", "Resgistro Guardado");
		System.out.println("Vacante:" + vacante);
		//redirect me redireciona osea puedo llamar otras url 
		return "redirect:/vacantes/index";
		
	}
	

	
	
	
	//url dinamica
@GetMapping("/view/{id}")
public 	String verDetalle(@PathVariable("id")   int idVacante, 	Model model){
	
	Vacante vacante = serviceVacantes.buscarPorId(idVacante);
	
	System.out.println("vacante: " + vacante);
	model.addAttribute("vacante", vacante);
	return "detalle";
	
	//buscar el detalle de las variables en la BD
}

@GetMapping("/delete")
public String eliminar(@RequestParam("id") int idVacante , Model model) {
	System.out.println("Borrando vacante con id :" + idVacante)	;
	model.addAttribute("id",idVacante);

	return "mensaje";
}

//con esto estoy corriguiendo los herrores de formato de fecha 
@InitBinder
public void initBinder(WebDataBinder webDataBinder) {
	
	SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
	webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
	
}

}

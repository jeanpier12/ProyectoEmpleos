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
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	//lo que contiene la propiedad empleosapp.ruta.imagenes
	//lo guarmos en la variable ruta 
	@Value("${empleosapp.ruta.imagenes}")
	private String ruta;
	
	//value me permite invocar la propiedad de apllication.prpertis
	//y guardar su valor enla variable ruta 
	//@Value("${empleossapp.ruta.imagenes}")
	//private String ruta;

	@Autowired
	//aca tenemos la instacia de nuestra clase de servicio   VacantesServiceJpa atraves de su interfaz IvacantesService
	//en este caso el objeto se llama serviceVacantes
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

	
	//todas  las URLS  llaman un metodo
	//formulario de las vacantes
	@GetMapping("/vacantes/create")
	//la primera al llmar al objeto vacante este guardara  el valor  de la imagen por defecto de la clase y 
	//sus demas propiedades solo de llenan con null,
	//tambien vacante se utiliza para vincular el formulario html con la clase vacante atravez del objeto vacante 
	//que esta como parametro en mi metodo 
	
	//el parametrp model de utilixa en un metodo gloval de avajo linea 169
	//El formulario envia la imegen del puesto de trabajo al objeto vacante
	public String crear(Vacante vacante, Model model ) {
		
		System.out.println(vacante);
		return "vacantes/formVacante";
	}
	
	//cuando alguien ingresa a esta url se guardan todos los datos del formulrio 
	//al objeto vacante
	@PostMapping("/vacantes/save")
	//como parametro le estamos pasando un objeto de tipo vacante
	//sus valores son los que ingresamos en la paguina de formulrio 
	//los cuales se guardan en el objeto vacante que tenemos como parmetro 
	//con el fin de que todos los datos del formulario se guarden en el nuevo objeto
	// de tipo vacante 
	public String guardar(Vacante vacante,BindingResult result,RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart ) {
		//@RequestParam debe tener el nombre del input de tipo file en este caso es name=archivoImagen
		//lo que hace es recibir la imagen que le enviamos  y lo guarda a la variable multiPart
		if(result.hasErrors()) {
			
			
			//for para ver los errores por consola 
			for(ObjectError error:result.getAllErrors()) {
				
				System.out.println("Ocurrio un error:" + error.getDefaultMessage());
			}
			
			return "vacantes/formVacante";
			
		}
		
		//si la imagen es distinto a vacio o se se seleciono una imagen 
		if (!multiPart.isEmpty()) {
		// lo manejamos de la siguiente manera 
		
			   //Utileria es una clase utlileria lo usamos para llamar el  metodo guardsrArchivo() como este 
			//es un metodo estativo lo puedo llamar sin instanciar un objeto sino que defrente usando su clase 
			//el cual nos retornara el nombre del archivo
			//String ruta2="c:/empleos/img-vacantes/";
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
			// Guardamos el nomde la imagen en el obejto vacante
			vacante.setImagen(nombreImagen); 
			}
			}
		//aca mandamos a llamar nuestra clase de servicio
		//y mandamos a llamar a nuestro metodo guardar 
		//entonces de est forma cuando llegue los valores al  objeto vacante 
		// se guardan en el objeto Vacante  luego en VacantesService de agrega este objeto a la base de datos 
		serviceVacantes.guardar(vacante);
		//teneos el atribito "msg" para poder desplegar un mensaje en el formulatiohtml
		attributes.addFlashAttribute("msg", "Resgistro Guardado");
		System.out.println("Vacante:" + vacante);
		//redirect me redireciona osea puedo llamar otras url 
		return "redirect:/vacantes/index";
		
	}
	
//metodo encargado de recibir el id de la vacante
//y buscarlo en la base de datos 
	
	@GetMapping("/vacantes/edit/{id}")
	public String editar(@PathVariable("id") int idVacante,Model model) {
		
			Vacante vacante =serviceVacantes.buscarPorId(idVacante);
	
			model.addAttribute("vacante", vacante);
			
		return "vacantes/formVacante";
	}
	
	
	
	
	//url dinamica
@GetMapping("/view/{id}")
//el objeto model me permite mostrar informacion en mi html atraves de su primer parametro 
public 	String verDetalle(@PathVariable("id")   int idVacante, 	Model model){
	//este metodo buscarPorId(idVacante) obtine el registro de la base de datos atraves del parametro idvacantes 
	//y lo guarmos en el objeto vacante
	Vacante vacante = serviceVacantes.buscarPorId(idVacante);
	
	System.out.println("vacante: " + vacante);

	model.addAttribute("vacante", vacante);
	return "detalle";
	
	//buscar el detalle de las variables en la BD
}

//para eliminar una vacante
@GetMapping("vacantes/delete/{id}")
public String eliminar( @PathVariable("id")  int idVacante , Model model,RedirectAttributes attributes) {
	System.out.println("Borrando vacante con id :" + idVacante)	;
	attributes.addFlashAttribute("msg", "eliminacion exitosa");
	serviceVacantes.eliminar(idVacante);
	

	return "redirect:/vacantes/index";
}

 //vamos a declarar un metodo para agregar datos al modelo 
//que son comunes en todo el controlador 

@ModelAttribute
//Esta anotación hacen que los atributos del modelo tengan un alcanze global 
//por lo que todos los metodos del controlador ya lo tienen por defecto
public void setGenericos(Model model) {
	
	model.addAttribute("categorias", serviceCategorias.buscarTodas());
	
}

//con esto estoy corriguiendo los herrores de formato de fecha 
@InitBinder
public void initBinder(WebDataBinder webDataBinder) {
	
	SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
	webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
	
}

}

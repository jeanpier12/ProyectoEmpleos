package com.job.job.controlador;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.job.job.model.Perfil;
import com.job.job.model.Usuario;
import com.job.job.model.Vacante;
import com.job.job.service.ICategoriasService;
import com.job.job.service.IUsuarioService;
import com.job.job.service.IVacantesService;




@Controller
public class homeControlador {
	
	
	@Autowired
	//creando un objeto de la interfaz
	private ICategoriasService serviceCategorias;
	
	@Autowired
	//creando un objeto de la interfaz
	private IVacantesService serviceVacantes;
	
	@Autowired
	private IUsuarioService serviceUsuarios;
	//encriptarcontra
	 @Autowired
	    private PasswordEncoder passwordEncoder;
	
	
	
	//cuando alguien hace una peticion a esta url 
	//esta URL  llama al metodo que le sigue en este caso mostrarHome 
	@GetMapping ("/")
	public String mostrarHome(Model model) {
	
		return "home";
		
		
	}
	

	/**
	 * Método que esta mapeado al botón Ingresar en el menú
	 * @param authentication
	 * @param session
	 * @return
	 */
	@GetMapping("/index")
	public String mostrarIndex(Authentication authentication, HttpSession session) {		
		
		// Como el usuario ya ingreso, ya podemos agregar a la session el objeto usuario.
		String username = authentication.getName();		
		
		for(GrantedAuthority rol: authentication.getAuthorities()) {
			System.out.println("ROL: " + rol.getAuthority());
		}
		
		if (session.getAttribute("usuario") == null){
			Usuario usuario = serviceUsuarios.buscarPorUsername(username);	
			//System.out.println("Usuario: " + usuario);
			session.setAttribute("usuario", usuario);
		}
		
		return "redirect:/";
	}
	
	//el obteto usuario me sirve para vincular mi html con el objeto usuario
	//metodo que me devuelve la paguina formulario de usuarios 
	@GetMapping("/registro")
	public String registroUsuario( Usuario usuario,Model model) {
		
		return "formRegistro";
		
		
	}
	
	
	//una vez que ya complete el formulario de registro y le de guardar se 
	//llamara esta funcion
	@PostMapping("/registro")
	//como parametro le estamos pasando un objeto de tipo Usuario
	//sus valores son los que ingresamos en la paguina de formulrio 
	//los cuales se guardan en el objeto Usuario que tenemos como parmetro 
	//con el fin de que todos los datos del formulario se guarden en el nuevo objeto
	// de tipo vacante 
	public String guardar(Usuario usuario,RedirectAttributes attributes ) {
		// Recuperamos el password en texto plano
				String pwdPlano = usuario.getPassword();
				// Encriptamos el pwd BCryptPasswordEncoder
				String pwdEncriptado = passwordEncoder.encode(pwdPlano); 
				// Hacemos un set al atributo password (ya viene encriptado)
		usuario.setPassword(pwdEncriptado);
		usuario.setEstatus(1); // Activado por defecto
		usuario.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor
		
		// Creamos el Perfil que le asignaremos al usuario nuevo
		Perfil perfil = new Perfil();
		//por defecta se guardara como un usuario
		perfil.setId(2); // Perfil USUARIO
		usuario.agregar(perfil);
		
		/**
		 * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
		 */
		serviceUsuarios.guardar(usuario);				
		attributes.addFlashAttribute("msg", "El registro fue guardado correctamente!");
		
		//una vez que te registraste te pedira logearte 
		
		return "redirect:/login";
			
		}
	
	/**
	 * Metodo que muestra la vista de la pagina de Acerca
	 * @return
	 */
	@GetMapping("/about")
	public String mostrarAcerca() {			
		return "acerca";
	}
	
	
	      @GetMapping("/search")
	      //el paremtro search me sirve para vuncular mi html con el objeto de la clase  
         public String buscar(@ModelAttribute("search") Vacante vacante,Model model) {
	    	  System.out.println("buscar por : " + vacante);
	    	  
	    	  ExampleMatcher matcher = ExampleMatcher.
	  				// where descripcion like '%?%'
	  				matching().withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
	  		
	  		Example<Vacante> example = Example.of(vacante,matcher);
	    	  //el resutado lo guardamos en una lista de vacantes filtradas
	    	 List<Vacante> lista=serviceVacantes.buscarByExample(example);
	    		model.addAttribute("vacantes", lista);	  
	    		
			return "home";
        	 
        	 
         }
	      
	    //cuando agregamos esta anotacion a nivel 
	  	// de un metodo del controlador 
	  	//con esta sintaxis podemos agregar al modelo todos los atributos que queramos 
	  	//La ventaja es todos los atributos van a estar disponibles  el la plantilla html y
	  	//para todos los metodos declarados en el controlador 
	  	@ModelAttribute
	  	public void setGenericos( Model model ) {
	  		
	  		Vacante vacanteBuscar = new Vacante();
	  		//al ejecutarse este metdo cambiamis la imagen a nulo
	  		vacanteBuscar.reset();
	  		//el atributo buscar se conecta nuestra clase modelo con el 
	  		//formulario html
	  		model.addAttribute("search", vacanteBuscar);
	  		
	  		//el atributo vacantes guarda la lista de vacantes destacadas y aprobadas
	  		model.addAttribute("vacantes",serviceVacantes.buscarDestacadas());
	  		model.addAttribute("categorias",serviceCategorias.buscarTodas());
	  		
	  	}
	      
	      
	      
	      
	      
	      
	      /** InitiBinder si detecta cadenas vacias en el string los convierte a null    */
		@InitBinder
		public void initBinder(WebDataBinder binder) {
			
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));	
			
		}
		
		/**
	     * Utileria para encriptar texto con el algorito BCrypt
	     * @param texto
	     * @return
	     */
	    @GetMapping("/bcrypt/{texto}")
	    @ResponseBody
	   	public String encriptar(@PathVariable("texto") String texto) {    	
	   		return texto + " Encriptado en Bcrypt: " + passwordEncoder.encode(texto);
	   	}
		
	
	//este metodo se utiliza para que ya no me salga una ventana emergente para 
		//cerrar sesion sino qie defrente te manda ala la paguina de inicio 
	/**	@GetMapping("/logout")
		public String logout(HttpServletRequest request) {
			SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
			logoutHandler.logout(request, null, null);
			return "redirect:/";
		}
	 */
	  
	
	
	
		
	}
	
	
	
	
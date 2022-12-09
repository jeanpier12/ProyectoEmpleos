package com.job.job.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.job.job.model.Usuario;
import com.job.job.service.IUsuarioService;






@Controller
public class UsuariosControler {

	@Autowired
	private IUsuarioService serviceUsuarios;
	
	//metodo de mostrar toda la lista de usuarios 
	  @GetMapping("/usuarios/index")
	  public String listaUsuarios(Usuario usuarios ,Model model) {
		   
		List <Usuario> lista =serviceUsuarios.buscarTodos();
		  
		  model.addAttribute("usuarios",lista);
		  
		return "usuarios/listUsuarios";
		   }
	  
	  
	  
	  //metodo de eliminar un usurio
	  @GetMapping("/usuarios/delete/{id}")
	  public String listaUsuarios( @PathVariable("id")  int idVacante , Model model,RedirectAttributes attributes) {
		  System.out.println("Borrando vacante con id :" + idVacante)	;
		   //este codigo me permite mostrar un mensaje en mi archvo utlizando la variable msg
			attributes.addFlashAttribute("msg", "eliminacion exitosa");
			serviceUsuarios.eliminar(idVacante);
			

			return "redirect:/usuarios/index";
		   }
	  
	  
}

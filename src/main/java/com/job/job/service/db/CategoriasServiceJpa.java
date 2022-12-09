package com.job.job.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.job.job.model.Categoria;
import com.job.job.repository.CategoriasRepository;
import com.job.job.service.ICategoriasService;

@Service
//con esta anotación le estamos diciendo a spring que en nuestro controlador 
//cuando se inyecte una instancia a una variable como va a encontrar 2 
//le estamos diciendo utiliza esta 
@Primary
public class CategoriasServiceJpa implements ICategoriasService {

	//pata la implementación de estos metodos en la clase de servico debemos 
	// traer una instancia de nuestro repositorio categorias repository
	
	
	//para crear una instancia en este atributo de utiliza la siguiente anotación 
	@Autowired
	private CategoriasRepository categoriasRepo;
	
	@Override
	//con este metodo el obejto categoria que nos llegue como parametro se 
	//guardara en la bse de datos 
	public void guardar(Categoria categoria) {
		categoriasRepo.save(categoria);
	}

	@Override
	//este metodo me muestra la lista de categorias que se encuentra en bd
	public List<Categoria> buscarTodas() {
		return categoriasRepo.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
	Optional<Categoria> optional=	categoriasRepo.findById(idCategoria);
	if(optional.isPresent()) {
		
		return optional.get();
		
		
	}
		return null;
	}

	@Override
	public void eliminar(Integer idCategoria) {
		categoriasRepo.deleteById(idCategoria);
		
	}

}

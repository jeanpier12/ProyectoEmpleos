package com.job.job.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.job.job.model.Vacante;
import com.job.job.repository.VacantesRepository;
import com.job.job.service.IVacantesService;

@Service
@Primary
public class VacantesServiceJpa implements IVacantesService {
  
	//inyectando una instancia de  la interfaz vacantes repositori
	
	@Autowired
	//traigo una instancia de este repositorio  para poder usa lo metodos de las interfaces de spring jpa  osea GRUD
	private VacantesRepository vacantesRepo;
	 
	
	@Override
	//este metodo me devuelve toda la lista de vacantes de mi base de datos 
	public List<Vacante> buscarTodas() {
		return vacantesRepo.findAll();
	
	}

	
	@Override
	public Vacante buscarPorId(Integer idVacante) {
		//aca emos deckarado optinal como un objeto de tipo Vacante
	Optional<Vacante> optional=	vacantesRepo.findById(idVacante);
	//si existen elementos esta colección se ejecuta la siguiente linea de codigo 
	if(optional.isPresent()) {
		//retornando lo que nos regrese el metodo get
		return optional.get();
	}
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		//el metodo save sirve para hacer un insert y un update 
		//¿como save si va hacer  un insert o un update 
		//esto lo sabe atrevez del id del obtejo 
		//si existe un id igual hace un update 
		// si no existe le id hace un insect
		vacantesRepo.save(vacante);

	}

	@Override
	//metodo que me muestra la lista de los destacados y aprobados 
	public List<Vacante> buscarDestacadas() {
		
		return vacantesRepo.findByDestacadoAndEstatusOrderByIdDesc(1,"Aprobada");
	}

	
	
	//metodo que me permite eliminar una vacante por su id
	@Override
	public void eliminar(Integer idVacante) {
		vacantesRepo.deleteById(idVacante);
		
	}


	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {
	 
		return vacantesRepo.findAll(example);
	}

}

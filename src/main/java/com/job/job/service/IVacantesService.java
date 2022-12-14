package com.job.job.service;

import java.util.List;

import org.springframework.data.domain.Example;

//se importa este paguete y su clase para que se puede utilizar la clase Vacante
import com.job.job.model.Vacante;

//se creo una interfas en una interfaz simplemente se declaran los metos que se pretende utilizar 
//y estos mtodos lo hereda otra clase o la otra clase inicializa el metodo o lo modifica 
public interface IVacantesService {
      //declarando metodo
	// metodo que llama la lista de objetos 
	List<Vacante> buscarTodas();
	
	//metodo que me pesmite buscar un vacante por id 
	// y luego mostrar información de esa bacante
	Vacante buscarPorId(Integer idVacante);
	
	//crearemos un metodo en la clase de servicio para  uardar un objeto 
	//de la bacante
	//este metodo recibira como parametro un objeto de la clase vacante
	void guardar(Vacante vacante);
	
	//este metodo devolvera una lista de objetos de tipo vacante
	List<Vacante> buscarDestacadas(); 
	//este metodo elimina un usuario por su id
	void eliminar(Integer idVacante);
	//metodo que se encarga de hacer el filtro en la base de datos  
	List<Vacante> buscarByExample(Example<Vacante> example);
	
}

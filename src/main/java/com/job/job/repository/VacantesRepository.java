package com.job.job.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.job.model.Vacante;


public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

	
	//creando un metodo para consultar
	//List<vacantes> = es el tipo de dato que es y que debuelve 
	//findBy = nombre del metodo
	//Estatus = es atributo de la clase 
	// en el parametro = podemos definir cualquier tipo de dato y cualquier nombre
	List<Vacante> findByEstatus(String estatus);
	
}

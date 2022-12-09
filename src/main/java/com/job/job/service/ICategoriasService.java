package com.job.job.service;

import java.util.List;

import com.job.job.model.Categoria;

public interface ICategoriasService {
	//tenemos que implementar estos 3 metodos 
	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);
	
	//metodo eliminar categoria
	void eliminar(Integer idCategoria);
}

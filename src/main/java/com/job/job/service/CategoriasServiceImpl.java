package com.job.job.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.job.job.model.Categoria;
@Service
public class CategoriasServiceImpl implements ICategoriasService {
 
	//se esta guardando en una lista las categoprias 
	private List<Categoria> lista = null;
	
	public CategoriasServiceImpl() {
		lista = new LinkedList<Categoria>();
		
		// Creamos algunas Categorias para poblar la lista ...
		
		// creación de objeto Cat 1
		Categoria cat1 = new Categoria();
		//agregandole valores al objeto 
		cat1.setId(1);
		cat1.setNombre("Contabilidad");
		cat1.setDescripcion("Descripcion de la categoria Contabilidad");
		
		//  creación de objeto Cat 2
		Categoria cat2 = new Categoria();
		//agregandole valores al objeto 
		cat2.setId(2);
		cat2.setNombre("Ventas");
		cat2.setDescripcion("Trabajos relacionados con Ventas");
		
					
		//  creación de objeto Cat 3
		Categoria cat3 = new Categoria();
		//agregandole valores al objeto 
		cat3.setId(3);
		cat3.setNombre("Comunicaciones");
		cat3.setDescripcion("Trabajos relacionados con Comunicaciones");
		
		//  creación de objeto Cat 4
		Categoria cat4 = new Categoria();
		//agregandole valores al objeto 
		cat4.setId(4);
		cat4.setNombre("Arquitectura");
		cat4.setDescripcion("Trabajos de Arquitectura");
		
		//  creación de objeto Cat 5
		Categoria cat5 = new Categoria();
		//agregandole valores al objeto 
		cat5.setId(5);
		cat5.setNombre("Educacion");
		cat5.setDescripcion("Maestros, tutores, etc");
		
	//  creación de objeto Cat 6
			Categoria cat6 = new Categoria();
			//agregandole valores al objeto 
			cat6.setId(6);
			cat6.setNombre("desarrollo de software");
			cat6.setDescripcion("Trabajo para programadores");
			
		
		/**
		 * Agregamos los 5 objetos de tipo Categoria a la lista ...
		 */
		lista.add(cat1);			
		lista.add(cat2);
		lista.add(cat3);
		lista.add(cat4);
		lista.add(cat5);
		lista.add(cat6);

	}
	


	
	
	
	@Override
	//Se esta inicializando el metodo que espera como parametro un bjeto categoria
	public void guardar(Categoria categoria) {
		//lo datos valores de este objeto se obtienen de  la clase CategoriasController
				// luego  vamos a agregar nuestro objeto categoria a la  lista
		lista.add(categoria);
	}

	@Override
	//este metodo me retorna una lista de  categorias 
	public List<Categoria> buscarTodas() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	//recibira un id de una categoria y regresara la categoria en caso aya sido encontrada 
	public Categoria buscarPorId(Integer idCategoria) {
		for (Categoria cat : lista) {
			if (cat.getId()==idCategoria) {
				return cat;
			}
		}		
		return null;	
	}

	
}

package com.job.job.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.job.job.model.Vacante;

//La clase vacabtesServiceIml esta obligado a heredad todos los metodos de la interfaz IVacantesService
@Service
public class VacantesServiceImpl implements IVacantesService{
// es este caso se esta utilizando como almacen de datos una lista
	//pero mas adelante usaremos una base de datos 
	//este es una lista de objetos Vacante
	//en resumen todos los objetos se estan almacenando en una lista
	private List<Vacante> lista =null;

	public VacantesServiceImpl() {
		
		//metodo para formatear el tiempo
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				//creado la lista de objetos Vacante
				lista = new LinkedList<Vacante>();
				try {
					// Creamos la oferta de Trabajo 1.
					//se esta creando el objeto vacante1 de la clase Vacante
					//este objeto vacante1 hereda todos los metodos y propiedades de la clase Vacante
					Vacante vacante1 = new Vacante();
					//agregandole valores a los atributos del objeto
					vacante1.setId(1);
					vacante1.setNombre("Ingeniero Civil"); // Titulo de la vacante
					vacante1.setDescripcion("Solicitamos Ing. Civil para diseñar puente peatonal.");
					vacante1.setFecha(sdf.parse("08-02-2022"));
					vacante1.setSalario(8050.0);
					vacante1.setDestacado(1);
					vacante1.setImagen("empresa1.png");
				
								
					// Creamos la oferta de Trabajo 2.
					Vacante vacante2 = new Vacante();
					vacante2.setId(2);
					vacante2.setNombre("Contador Publico");
					vacante2.setDescripcion("Empresa importante solicita contador con 5 años de experiencia titulado.");
					vacante2.setFecha(sdf.parse("09-02-2022"));
					vacante2.setSalario(12000.0);
					vacante2.setDestacado(0);
					vacante2.setImagen("empresa2.png");
					
					// Creamos la oferta de Trabajo 3.
					Vacante vacante3 = new Vacante();
					vacante3.setId(3);
					vacante3.setNombre("Ingeniero Eléctrico");
					vacante3.setDescripcion("Empresa internacional solicita Ingeniero mecánico para mantenimiento de la instalación eléctrica.");
					vacante3.setFecha(sdf.parse("10-02-2022"));
					vacante3.setSalario(10500.0);
					vacante3.setDestacado(0);
					
					
					// Creamos la oferta de Trabajo 4.
					Vacante vacante4 = new Vacante();
					vacante4.setId(4);
					vacante4.setNombre("Diseñador Gráfico");
					vacante4.setDescripcion("Solicitamos Diseñador Gráfico titulado para diseñar publicidad de la empresa.");
					vacante4.setFecha(sdf.parse("11-02-2022"));
					vacante4.setSalario(7500.0);
					vacante4.setDestacado(1);
					vacante4.setImagen("empresa3.png");
					
					/**
					 * Agregamos a la lista los objetos vacante 1,2, 3
					 */
					lista.add(vacante1);			
					lista.add(vacante2);
					lista.add(vacante3);
					lista.add(vacante4);

				} catch (ParseException e) {
					System.out.println("Error: " + e.getMessage());
				}
			
		
	}

	@Override
	//se esta inicializando el metodo 
	//este metodo contien toda la lista de tipo vacante
	public List<Vacante> buscarTodas() {
		return lista;
	}

	@Override
	//se esta inicializando el metodo
	//metodo que me pesmite buscar un bacante por id 
		// y luego mostrar información de esa bacante
	public Vacante buscarPorId(Integer idVacante) {
		// vamos a recoder cada lista y comparar la vacante que estamos pasando como parametro devolvera el objeto
		for (Vacante v : lista) {
			
			if(v.getId() == idVacante) {
				
				return v;
			}
		}
		
		return null;
	}
//este metodo recible como parametro un objeto de tipo  vacante
	public void guardar(Vacante vacante) {

	
		
		//lo datos valores de este objeto se obtienen de  la clase VacantesController
		// luego  vamos a agregar nuestro objeto vacante a la  lista
		lista.add(vacante);
		
	}
	
	

	
}

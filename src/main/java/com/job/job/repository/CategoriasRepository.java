package com.job.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.job.model.Categoria;
//creando repositorio de spring data jpa
//un repisotorio es una interfaz que extiende de la  interfaz crud repository 
//esta interfaz ya tine definida por defecto todos los metodos del grud
//en esta ocación la implementación de los metodos de ghacer por defecto si necesidad 
//sin necesidad de traerlos 

public interface CategoriasRepository extends JpaRepository <Categoria, Integer> {

}

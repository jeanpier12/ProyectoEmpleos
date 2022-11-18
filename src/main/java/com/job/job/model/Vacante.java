package com.job.job.model;

import java.util.Date;

//Se creo la clase Vacante 
//una clse es una platilla que me permite crear objetos 
public class Vacante {

   private Integer id;
   private String nombre;
   private String descripcion;
   private Date fecha;
   private Double salario;
   private Integer destacado;
   private String imagen="no-image.png";
   private String estatus;
   private String detalles ;
   private Categoria categoria;
   
   
   
   
public String getEstatus() {
	return estatus;
}
public void setEstatus(String estatus) {
	this.estatus = estatus;
}
public String getDetalles() {
	return detalles;
}
public void setDetalles(String detalles) {
	this.detalles = detalles;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public Date getFecha() {
	return fecha;
}
public void setFecha(Date fecha) {
	this.fecha = fecha;
}
public Double getSalario() {
	return salario;
}
public void setSalario(Double salario) {
	this.salario = salario;
}


public Integer getDestacado() {
	return destacado;
}
public void setDestacado(Integer destacado) {
	this.destacado = destacado;
}

public String getImagen() {
	return imagen;
}
public void setImagen(String imagen) {
	this.imagen = imagen;
}

public Categoria getCategoria() {
	return categoria;
}
public void setCategoria(Categoria categoria) {
	this.categoria = categoria;
}
@Override
public String toString() {
	return "Vacante [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha
			+ ", salario=" + salario + ", destacado=" + destacado + ", imagen=" + imagen + ", estatus=" + estatus
			+ ", detalles=" + detalles + ", categoria=" + categoria + "]";
}

   
   
   

}
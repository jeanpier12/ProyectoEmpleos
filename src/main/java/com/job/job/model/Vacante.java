package com.job.job.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


//entity sirve para crear el repositorio de esta clase
@Entity 
//aca le indicamos con que tabla de la base de datos va a tener concencion esta clase del modelo
@Table(name= "Vacantes")
//Se creo la clase Vacante 
//una clse es una platilla que me permite crear objetos 
public class Vacante {

	//configurando cual sera la llave primaria
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
   private Integer id;
   private String nombre;
   private String descripcion;
   private Date fecha;
   private Double salario;
   private Integer destacado;
   private String imagen="no-image.png";
   private String estatus;
   private String detalles ;
   
   //con esta anotación le estamos diciendo que se ignore el mapeo  esta propiedad 
   //@Transient
   
   //con esta anotacion estamos indicando una relacion de 1 a 1 
   //entre nuestra clase de modelo vacante y la clase de modelo categorias 
   @OneToOne
   
   //con esta anotación vamos a indicar la columna con la que se maraca la relacion entre las 2 tablas 
   //como parametro de esta anotacion debe estar el nombre de la columna que relaciona estas 2 tabalas 
   @JoinColumn(name ="idCategoria")
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

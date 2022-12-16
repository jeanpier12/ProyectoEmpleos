package com.job.job.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
 public class WebConfig implements WebMvcConfigurer{
		
		//la anotacio value esta tomando el valor de la propiedad que viene
		//del archivo aplication.propertis 
		//e inyecta el valor en la variable rutasImagenes
		@Value("${empleosapp.ruta.imagenes}")
		private String rutaImagenes;
		@Value("${empleosapp.ruta.cv}")
		private String rutaCv;

		//este metodo sirve oara agregar directorios recursos a nuestro proyecto
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			
			//registry.addResourceHandler("/logos/**").addResourceLocations("file:/c:/empleos/img-vacantes/"); // Windows
			
			//usamos registry para registrar nuestro directorio como un recurso estatica
			//logo el alias de nuestra ruta  osea al apuntar a logos estamos apuntando a la ruta donde 
			 ///se guardan las imagenes 
			registry.addResourceHandler("/logos/**").addResourceLocations("file:" + rutaImagenes); 
			
			
			// Configuración de los recursos estáticos (archivos de los CV)
			//registry.addResourceHandler("/cv/**").addResourceLocations("file:c:/empleos/files-cv/"); // Windows
			//registry.addResourceHandler("/cv/**").addResourceLocations("file:/empleos/files-cv/");
			registry.addResourceHandler("/cv/**").addResourceLocations("file:"+rutaCv); // Linux
		}
	}
	


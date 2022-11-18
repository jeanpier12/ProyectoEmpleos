package com.job.job.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Utileria {

	public static String guardarArchivo(MultipartFile multiPart, String ruta) {
		// Obtenemos el nombre original del archivo.
		String nombreOriginal = multiPart.getOriginalFilename();
		//metodo replace me permite tomar los espacios y cambiarlos por un caracter
		nombreOriginal=nombreOriginal.replace(" ", "-");
		//guarmaos en merodo randowAlphanumer + el nombre en una variable
		String nombreFinal = randomAlphaNumber(8)+ nombreOriginal;
				try {
		// Formamos el nombre del archivo para guardarlo en el disco duro.
		File imageFile = new File(ruta + nombreFinal);
		System.out.println("Archivo: " + imageFile.getAbsolutePath());
		//Guardamos fisicamente el archivo en HD.
		multiPart.transferTo(imageFile);
		return nombreFinal;
		} catch (IOException e) {
		System.out.println("Error " + e.getMessage());
		return null;
		}
		}
	
	/* Metodo para generar una cadena aleatoria de longitud N
	 * la longuitud de la cadena sera del nuemero que le paemos como argumento 
	 * al parameto de este metodo
	 *   
	 * */
	
	public static String randomAlphaNumber(int count) {
		
		String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder= new StringBuilder();
		while(count-- != 0) {
			
			int character =(int) (Math.random() * CARACTERES.length());
			builder.append(CARACTERES.charAt(character));
		}
		return builder.toString();
	}
	
	
}

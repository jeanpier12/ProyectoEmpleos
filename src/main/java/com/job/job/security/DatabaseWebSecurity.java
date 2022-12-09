package com.job.job.security;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {
@Autowired
private DataSource dataSource;
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
	auth.jdbcAuthentication().dataSource(dataSource)
	.usersByUsernameQuery("select username, password, estatus from Usuarios where username=?")
	.authoritiesByUsernameQuery("select u.username, p.perfil from UsuarioPerfil up " + 
		"inner join Usuarios u on u.id = up.idUsuario " + 
		"inner join Perfiles p on p.id = up.idPerfil " + 
		"where u.username = ?");

}



@Override
protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests()
	// Los recursos estáticos no requieren autenticación
	.antMatchers(
		"/bootstrap/**",
		"/images/**",
		"/tinymce/**",
		"/logos/**").permitAll()
	// Las vistas públicas no requieren autenticación
	.antMatchers("/",
		 "/login",
		 "/about",
	     "/bcrypt/**",
		"/registro",
		"/search",
		"/view/{id}/**").permitAll()
	
	// Asignar permisos a URLs por ROLES estas Url NO se llamaran a menos que se lo llame teniendo los siguientes roles 
	.antMatchers("/vacantes/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR","USUARIO")
	.antMatchers("/categorias/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR","USUARIO") 
	.antMatchers("/usuarios/**").hasAnyAuthority("ADMINISTRADOR","USUARIO")

		
	// Todas las demás URLs de la Aplicación requieren autenticación
	.anyRequest().authenticated()
	// El formulario de Login no requiere autenticacion
	.and().formLogin().permitAll();
}
/**
 *  Implementación de Spring Security que encripta passwords con el algoritmo Bcrypt
 * @return
 */
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}




}

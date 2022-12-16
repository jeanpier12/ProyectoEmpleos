package com.job.job.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.job.job.model.Usuario;
import com.job.job.repository.UsuarioRepository;
import com.job.job.service.IUsuarioService;

@Service
@Primary
public class UsuariosServiceJpa implements IUsuarioService {

	
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Override
	public void guardar(Usuario usuario) {

		usuarioRepo.save(usuario);
		
	}

	
	@Override
	public void eliminar(Integer idUsuario) {
		usuarioRepo.deleteById(idUsuario);
	}

	
	//este metodo me devuelve toda la lista de Usuarios de mi base de datos
	@Override
	public List<Usuario> buscarTodos() {
		// TODO Auto-generated method stub
		return usuarioRepo.findAll();
	}


	@Override
	public Usuario buscarPorUsername(String username) {
		
		return usuarioRepo.findByUsername(username);
	}
	@Transactional
	@Override
	public int bloquear(int idUsuario) {
		int rows = usuarioRepo.lock(idUsuario);
		return rows;
	}

	@Transactional
	@Override
	public int activar(int idUsuario) {
		int rows = usuarioRepo.unlock(idUsuario);
		return rows;
	}

}

 

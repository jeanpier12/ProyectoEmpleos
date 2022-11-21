package com.job.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.job.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}

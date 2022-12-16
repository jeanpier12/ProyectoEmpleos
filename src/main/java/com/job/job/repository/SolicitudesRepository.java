package com.job.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.job.model.Solicitud;

public interface SolicitudesRepository extends JpaRepository<Solicitud, Integer> {

}

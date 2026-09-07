package com.superperu.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.superperu.backend.model.Pasaje;
import com.superperu.backend.model.Viaje;

public interface PasajeRepository extends JpaRepository<Pasaje, Long> {

    long countByViajeAndEstadoNot(Viaje viaje, String estado);
}
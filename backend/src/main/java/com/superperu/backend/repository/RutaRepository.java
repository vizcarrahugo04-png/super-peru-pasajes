package com.superperu.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.superperu.backend.model.Ruta;

public interface RutaRepository extends JpaRepository<Ruta, Long> {
    java.util.Optional<Ruta> findByOrigenAndDestino(String origen, String destino);
}
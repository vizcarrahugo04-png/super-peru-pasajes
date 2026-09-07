package com.superperu.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.superperu.backend.model.Ruta;
import com.superperu.backend.model.Viaje;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    boolean existsByRutaAndFechaHoraSalida(Ruta ruta, LocalDateTime fechaHoraSalida);

    List<Viaje> findByRutaAndFechaHoraSalidaBetween(
        Ruta ruta, LocalDateTime desde, LocalDateTime hasta);
}
package com.superperu.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.superperu.backend.model.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
}
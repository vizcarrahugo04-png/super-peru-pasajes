package com.superperu.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.superperu.backend.model.Asiento;

public interface AsientoRepository extends JpaRepository<Asiento, Long> {
}
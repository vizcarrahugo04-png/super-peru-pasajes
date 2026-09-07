package com.superperu.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.superperu.backend.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
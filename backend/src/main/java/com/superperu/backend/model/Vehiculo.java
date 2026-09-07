package com.superperu.backend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "vehiculo")
@Data
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;

    private String tipo; // ej: "Minibus"

    private Integer capacidad; // 15 pasajeros, sin contar al conductor

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<Asiento> asientos = new ArrayList<>();
}
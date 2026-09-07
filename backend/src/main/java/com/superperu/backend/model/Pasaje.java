package com.superperu.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pasaje")
@Data
public class Pasaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Viaje viaje;

    @ManyToOne
    private Asiento asiento;

    private String nombrePasajero;

    private String dniPasajero;

    private String estado; // "RESERVADO", "PAGADO", "CANCELADO"
}
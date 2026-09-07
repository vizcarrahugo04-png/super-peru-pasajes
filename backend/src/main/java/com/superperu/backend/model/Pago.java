package com.superperu.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pago")
@Data
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Pasaje pasaje;

    private Double monto;

    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    private String estado;

    private LocalDateTime fechaPago;

    public enum MetodoPago {
        TARJETA, YAPE, PLIN, EFECTIVO
    }
}
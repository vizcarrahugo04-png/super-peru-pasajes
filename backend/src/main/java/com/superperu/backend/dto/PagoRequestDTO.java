package com.superperu.backend.dto;

import lombok.Data;

@Data
public class PagoRequestDTO {

    private Long pasajeId;
    private String metodoPago; // "TARJETA", "YAPE", "PLIN", "EFECTIVO"
    private Double monto;
}
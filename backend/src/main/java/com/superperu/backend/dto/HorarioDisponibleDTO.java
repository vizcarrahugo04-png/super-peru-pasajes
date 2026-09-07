package com.superperu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HorarioDisponibleDTO {

    private Long viajeId;
    private String horaSalida;      // ej: "14:00"
    private Integer asientosDisponibles;
    private boolean disponible;     // false si ya está completo
}
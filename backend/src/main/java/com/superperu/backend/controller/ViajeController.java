package com.superperu.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.superperu.backend.dto.HorarioDisponibleDTO;
import com.superperu.backend.service.ViajeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/viajes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // permite que Angular (otro puerto) consuma esta API
public class ViajeController {

    private final ViajeService viajeService;

    @GetMapping("/horarios")
    public List<HorarioDisponibleDTO> getHorarios(
            @RequestParam String origen,
            @RequestParam String destino,
            @RequestParam String fecha) {
        return viajeService.obtenerHorariosConDisponibilidad(origen, destino, fecha);
    }
}
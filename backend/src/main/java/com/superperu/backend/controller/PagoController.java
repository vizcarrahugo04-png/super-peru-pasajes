package com.superperu.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.superperu.backend.dto.PagoRequestDTO;
import com.superperu.backend.model.Pago;
import com.superperu.backend.service.PagoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PagoController {

    private final PagoService pagoService;

    @PostMapping
    public Pago crearPago(@RequestBody PagoRequestDTO request) {
        return pagoService.procesarPago(request);
    }
}
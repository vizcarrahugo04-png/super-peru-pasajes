package com.superperu.backend.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.superperu.backend.dto.PagoRequestDTO;
import com.superperu.backend.model.Pago;
import com.superperu.backend.model.Pasaje;
import com.superperu.backend.repository.PagoRepository;
import com.superperu.backend.repository.PasajeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;
    private final PasajeRepository pasajeRepository;

    public Pago procesarPago(PagoRequestDTO request) {
        Pasaje pasaje = pasajeRepository.findById(request.getPasajeId())
            .orElseThrow(() -> new EntityNotFoundException("Pasaje no encontrado"));

        Pago pago = new Pago();
        pago.setPasaje(pasaje);
        pago.setMonto(request.getMonto());
        pago.setMetodoPago(Pago.MetodoPago.valueOf(request.getMetodoPago()));
        pago.setFechaPago(LocalDateTime.now());

        // Simulación: efectivo se confirma directo, los demás quedan "CONFIRMADO" también
        // por ahora (más adelante aquí se conecta con Culqi/Niubiz si decides integrar pago real)
        pago.setEstado("CONFIRMADO");

        Pago pagoGuardado = pagoRepository.save(pago);

        // Si el pago se confirma, el pasaje pasa de RESERVADO a PAGADO
        pasaje.setEstado("PAGADO");
        pasajeRepository.save(pasaje);

        return pagoGuardado;
    }
}
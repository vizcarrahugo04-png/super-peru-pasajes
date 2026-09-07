package com.superperu.backend.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.superperu.backend.dto.HorarioDisponibleDTO;
import com.superperu.backend.model.Ruta;
import com.superperu.backend.model.Vehiculo;
import com.superperu.backend.model.Viaje;
import com.superperu.backend.repository.PasajeRepository;
import com.superperu.backend.repository.RutaRepository;
import com.superperu.backend.repository.VehiculoRepository;
import com.superperu.backend.repository.ViajeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ViajeService {

    private final ViajeRepository viajeRepository;
    private final RutaRepository rutaRepository;
    private final VehiculoRepository vehiculoRepository;
    private final PasajeRepository pasajeRepository;

    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

    // Genera los 17 viajes del día (4am a 8pm) para una ruta, si todavía no existen
    public void generarViajesDelDia(Ruta ruta, Vehiculo vehiculo, LocalDate fecha) {
        for (Integer hora : HorarioConfig.HORAS_SALIDA) {
            LocalDateTime salida = fecha.atTime(hora, 0);
            boolean yaExiste = viajeRepository.existsByRutaAndFechaHoraSalida(ruta, salida);
            if (!yaExiste) {
                Viaje viaje = new Viaje();
                viaje.setRuta(ruta);
                viaje.setVehiculo(vehiculo);
                viaje.setFechaHoraSalida(salida);
                viaje.setEstado("PROGRAMADO");
                viajeRepository.save(viaje);
            }
        }
    }

    // Devuelve las 17 horas del día con su disponibilidad, para un origen/destino/fecha dados
    public List<HorarioDisponibleDTO> obtenerHorariosConDisponibilidad(
            String origen, String destino, String fechaTexto) {

        Optional<Ruta> rutaOpt = rutaRepository.findByOrigenAndDestino(origen, destino);
        if (rutaOpt.isEmpty()) {
            return new ArrayList<>(); // no existe esa ruta, devolvemos lista vacía
        }
        Ruta ruta = rutaOpt.get();
        LocalDate fecha = LocalDate.parse(fechaTexto);

        // Aseguramos que los viajes de ese día ya existan (se generan si faltan)
        Vehiculo vehiculo = vehiculoRepository.findAll().get(0); // por ahora, el primer vehículo registrado
        generarViajesDelDia(ruta, vehiculo, fecha);

        LocalDateTime desde = fecha.atStartOfDay();
        LocalDateTime hasta = fecha.atTime(23, 59);
        List<Viaje> viajesDelDia = viajeRepository.findByRutaAndFechaHoraSalidaBetween(ruta, desde, hasta);

        List<HorarioDisponibleDTO> resultado = new ArrayList<>();
        for (Viaje viaje : viajesDelDia) {
            long ocupados = pasajeRepository.countByViajeAndEstadoNot(viaje, "CANCELADO");
            int capacidad = viaje.getVehiculo().getCapacidad();
            int disponibles = (int) (capacidad - ocupados);

            resultado.add(new HorarioDisponibleDTO(
                viaje.getId(),
                viaje.getFechaHoraSalida().format(FORMATO_HORA),
                disponibles,
                disponibles > 0
            ));
        }
        return resultado;
    }
}
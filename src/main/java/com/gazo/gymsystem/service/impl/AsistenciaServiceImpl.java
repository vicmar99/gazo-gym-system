package com.gazo.gymsystem.service.impl;

import com.gazo.gymsystem.entity.Asistencia;
import com.gazo.gymsystem.entity.Cliente;
import com.gazo.gymsystem.repository.AsistenciaRepository;
import com.gazo.gymsystem.repository.ClienteRepository;
import com.gazo.gymsystem.service.AsistenciaService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    private final ClienteRepository clienteRepository;
    private final AsistenciaRepository asistenciaRepository;

    public AsistenciaServiceImpl(
            ClienteRepository clienteRepository,
            AsistenciaRepository asistenciaRepository
    ) {
        this.clienteRepository = clienteRepository;
        this.asistenciaRepository = asistenciaRepository;
    }

    @Override
    public String registrarAsistencia(String codigoQR) {

        String idCliente = extraerId(codigoQR);

        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElse(null);

        if (cliente == null) {

            return "CLIENTE NO ENCONTRADO";

        }

        if (cliente.getFechaVencimiento() == null ||
                cliente.getFechaVencimiento()
                        .isBefore(LocalDateTime.now())) {

            cliente.setEstatus("VENCIDO");
            cliente.setActivo(false);

            clienteRepository.save(cliente);

            return "ACCESO DENEGADO - MEMBRESÍA VENCIDA";

        }

        Asistencia asistencia = new Asistencia();

        asistencia.setCliente(cliente);

        asistencia.setFechaHora(LocalDateTime.now());

        asistenciaRepository.save(asistencia);

        return "ACCESO PERMITIDO - " +
                cliente.getNombre();
    }

    private String extraerId(String qr) {

        if (qr.contains("?id=")) {

            return qr.substring(
                    qr.indexOf("?id=") + 4
            );

        }

        return qr.trim();
    }

}
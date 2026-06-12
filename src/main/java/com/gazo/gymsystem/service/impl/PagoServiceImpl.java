package com.gazo.gymsystem.service.impl;

import com.gazo.gymsystem.entity.*;
import com.gazo.gymsystem.repository.PagoRepository;
import com.gazo.gymsystem.service.PagoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.gazo.gymsystem.repository.ClienteRepository;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final ClienteRepository clienteRepository;

    public PagoServiceImpl(
            PagoRepository pagoRepository,
            ClienteRepository clienteRepository
    ) {
        this.pagoRepository = pagoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Pago registrarPago(
            Cliente cliente,
            Membresia membresia,
            Integer periodos,
            BigDecimal descuento,
            String metodoPago,
            Usuario usuario
    ) {

        LocalDate fechaInicio =
                calcularFechaInicio(cliente);

        LocalDateTime fechaVencimiento =
                calcularFechaVencimiento(
                        fechaInicio,
                        membresia,
                        periodos
                );

        BigDecimal precioBase =
                membresia.getPrecio()
                        .multiply(
                                BigDecimal.valueOf(periodos)
                        );

        BigDecimal montoPagado =
                precioBase.subtract(descuento);

        Pago pago = new Pago();

        pago.setFechaPago(LocalDateTime.now());

        pago.setCliente(cliente);

        pago.setMembresia(membresia);

        pago.setPeriodos(periodos);

        pago.setPrecioBase(precioBase);

        pago.setDescuento(descuento);

        pago.setMontoPagado(montoPagado);

        pago.setMetodoPago(metodoPago);

        pago.setFechaInicio(fechaInicio);

        pago.setFechaVencimiento(fechaVencimiento);

        pago.setUsuario(usuario);

        Pago pagoGuardado = pagoRepository.save(pago);

// Actualizar información actual del cliente
        cliente.setTipoPago(
                membresia.getNombre()
        );

        cliente.setFechaInicio(fechaInicio);

        cliente.setFechaVencimiento(fechaVencimiento);

        cliente.setMonto(montoPagado);

        cliente.setEstatus("ACTIVO");

        cliente.setActivo(true);

        clienteRepository.save(cliente);

        return pagoGuardado;
    }

    private LocalDate calcularFechaInicio(
            Cliente cliente
    ) {

        return pagoRepository
                .findTopByClienteIdClienteOrderByFechaVencimientoDesc(
                        cliente.getIdCliente()
                )
                .map(pago -> {

                    LocalDate ultimoVencimiento =
                            pago.getFechaVencimiento()
                                    .toLocalDate();

                    if (ultimoVencimiento.isAfter(LocalDate.now())) {

                        return ultimoVencimiento;

                    }

                    return LocalDate.now();

                })
                .orElse(LocalDate.now());

    }

    private LocalDateTime calcularFechaVencimiento(
            LocalDate fechaInicio,
            Membresia membresia,
            Integer periodos
    ) {

        if ("MESES".equals(
                membresia.getUnidad()
        )) {

            return fechaInicio
                    .plusMonths(periodos)
                    .atTime(22, 59, 59);

        }

        return fechaInicio
                .plusDays(
                        (long) membresia.getDias()
                                * periodos
                )
                .atTime(22, 59, 59);

    }

}
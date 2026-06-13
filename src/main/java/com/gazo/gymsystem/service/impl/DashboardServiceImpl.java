package com.gazo.gymsystem.service.impl;

import com.gazo.gymsystem.repository.AsistenciaRepository;
import com.gazo.gymsystem.repository.ClienteRepository;
import com.gazo.gymsystem.repository.PagoRepository;
import com.gazo.gymsystem.service.DashboardService;
import org.springframework.stereotype.Service;
import com.gazo.gymsystem.entity.Asistencia;
import java.util.List;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.gazo.gymsystem.entity.Cliente;
import java.util.List;
import com.gazo.gymsystem.entity.Pago;
import java.util.List;
@Service
public class DashboardServiceImpl
        implements DashboardService {

    private final ClienteRepository clienteRepository;
    private final AsistenciaRepository asistenciaRepository;
    private final PagoRepository pagoRepository;

    public DashboardServiceImpl(
            ClienteRepository clienteRepository,
            AsistenciaRepository asistenciaRepository,
            PagoRepository pagoRepository
    ) {
        this.clienteRepository = clienteRepository;
        this.asistenciaRepository = asistenciaRepository;
        this.pagoRepository = pagoRepository;
    }

    @Override
    public Long obtenerClientesActivos() {

        return clienteRepository
                .countByFechaVencimientoAfter(
                        LocalDateTime.now()
                );

    }

    @Override
    public Long obtenerAsistenciasHoy() {

        return asistenciaRepository
                .countByFechaHoraAfter(
                        LocalDate.now()
                                .atStartOfDay()
                );

    }

    @Override
    public BigDecimal obtenerIngresosHoy() {

        return pagoRepository
                .obtenerIngresosDesde(
                        LocalDate.now()
                                .atStartOfDay()
                );

    }

    @Override
    public BigDecimal obtenerIngresosMes() {

        return pagoRepository
                .obtenerIngresosDesde(
                        LocalDate.now()
                                .withDayOfMonth(1)
                                .atStartOfDay()
                );

    }

    @Override
    public List<Cliente> obtenerProximosVencimientos() {

        return clienteRepository
                .findTop10ByFechaVencimientoBetweenOrderByFechaVencimientoAsc(
                        LocalDateTime.now(),
                        LocalDateTime.now().plusDays(3)
                );

    }

    @Override
    public List<Pago> obtenerUltimosPagos() {

        return pagoRepository
                .findTop5ByOrderByFechaPagoDesc();

    }

    @Override
    public List<Asistencia> obtenerUltimasAsistencias() {

        return asistenciaRepository
                .findTop5ByOrderByFechaHoraDesc();

    }

}
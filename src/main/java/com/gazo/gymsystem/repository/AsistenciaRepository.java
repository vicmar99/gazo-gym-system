package com.gazo.gymsystem.repository;

import com.gazo.gymsystem.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AsistenciaRepository
        extends JpaRepository<Asistencia, Integer> {

    Long countByFechaHoraAfter(
            LocalDateTime fechaHora
    );

    List<Asistencia>
    findTop5ByOrderByFechaHoraDesc();

    Optional<Asistencia>
    findTopByClienteIdClienteOrderByFechaHoraDesc(
            String idCliente
    );

    List<Asistencia>
    findByClienteIdClienteOrderByFechaHoraDesc(
            String idCliente
    );

}
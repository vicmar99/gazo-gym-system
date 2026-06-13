package com.gazo.gymsystem.repository;

import com.gazo.gymsystem.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AsistenciaRepository
        extends JpaRepository<Asistencia, Integer> {

    long countByFechaHoraAfter(
            LocalDateTime fecha
    );

    List<Asistencia> findTop5ByOrderByFechaHoraDesc();

}
package com.gazo.gymsystem.repository;

import com.gazo.gymsystem.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsistenciaRepository
        extends JpaRepository<Asistencia, Integer> {
}
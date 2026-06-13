package com.gazo.gymsystem.repository;

import com.gazo.gymsystem.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

import java.time.LocalDateTime;
import java.util.List;

public interface ClienteRepository
        extends JpaRepository<Cliente, String> {

    Long countByFechaVencimientoAfter(
            LocalDateTime fecha
    );

    List<Cliente> findTop10ByFechaVencimientoBetweenOrderByFechaVencimientoAsc(
            LocalDateTime inicio,
            LocalDateTime fin
    );
}
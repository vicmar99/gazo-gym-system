package com.gazo.gymsystem.repository;

import com.gazo.gymsystem.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagoRepository
        extends JpaRepository<Pago, Integer> {

    Optional<Pago>
    findTopByClienteIdClienteOrderByFechaVencimientoDesc(
            String idCliente
    );
}
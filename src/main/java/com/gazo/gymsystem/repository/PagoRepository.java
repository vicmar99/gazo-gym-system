package com.gazo.gymsystem.repository;

import com.gazo.gymsystem.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PagoRepository
        extends JpaRepository<Pago, Integer> {

    Optional<Pago>
    findTopByClienteIdClienteOrderByFechaVencimientoDesc(
            String idCliente
    );

    @Query("""
           SELECT COALESCE(SUM(p.montoPagado), 0)
           FROM Pago p
           WHERE p.fechaPago >= :inicio
           """)
    BigDecimal obtenerIngresosDesde(
            LocalDateTime inicio
    );

    List<Pago> findTop10ByOrderByFechaPagoDesc();

}
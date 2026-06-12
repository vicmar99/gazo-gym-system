package com.gazo.gymsystem.service;

import com.gazo.gymsystem.entity.Cliente;
import com.gazo.gymsystem.entity.Membresia;
import com.gazo.gymsystem.entity.Pago;
import com.gazo.gymsystem.entity.Usuario;

import java.math.BigDecimal;

public interface PagoService {

    Pago registrarPago(
            Cliente cliente,
            Membresia membresia,
            Integer periodos,
            BigDecimal descuento,
            String metodoPago,
            Usuario usuario
    );

}
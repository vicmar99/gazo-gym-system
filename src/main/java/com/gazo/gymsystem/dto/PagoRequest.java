package com.gazo.gymsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PagoRequest {

    private String idCliente;

    private Integer idMembresia;

    private Integer periodos = 1;

    private BigDecimal descuento = BigDecimal.ZERO;

    private String metodoPago;

}
package com.gazo.gymsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Getter
@Setter
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Integer idPago;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_membresia")
    private Membresia membresia;

    private Integer periodos;

    @Column(name = "precio_base")
    private BigDecimal precioBase;

    private BigDecimal descuento;

    @Column(name = "monto_pagado")
    private BigDecimal montoPagado;

    @Column(name = "metodo_pago")
    private String metodoPago;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_vencimiento")
    private LocalDateTime fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
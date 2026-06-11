package com.gazo.gymsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "membresias")
@Getter
@Setter
public class Membresia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_membresia")
    private Integer idMembresia;

    private String nombre;

    private Integer dias;

    private BigDecimal precio;

    private Boolean activa;

    private String unidad;
}
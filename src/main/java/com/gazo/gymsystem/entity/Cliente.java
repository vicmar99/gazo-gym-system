package com.gazo.gymsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class Cliente {

    @Id
    @Column(name = "id_cliente")
    private String idCliente;

    @Column(nullable = false)
    private String nombre;

    private String telefono;

    private String qr;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    private Boolean activo;
}
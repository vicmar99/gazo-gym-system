package com.gazo.gymsystem.repository;

import com.gazo.gymsystem.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository
        extends JpaRepository<Cliente, String> {

    List<Cliente> findByNombreContainingIgnoreCase(String nombre);

}
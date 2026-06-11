package com.gazo.gymsystem.repository;

import com.gazo.gymsystem.entity.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembresiaRepository
        extends JpaRepository<Membresia, Integer> {
}
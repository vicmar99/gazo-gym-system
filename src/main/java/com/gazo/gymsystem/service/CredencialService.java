package com.gazo.gymsystem.service;

import com.gazo.gymsystem.entity.Cliente;

public interface CredencialService {

    byte[] generarCredencial(Cliente cliente) throws Exception;

}
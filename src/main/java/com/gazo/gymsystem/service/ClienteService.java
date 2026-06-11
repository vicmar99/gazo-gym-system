package com.gazo.gymsystem.service;

import com.gazo.gymsystem.entity.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> listarTodos();

    Cliente guardar(Cliente cliente);

}
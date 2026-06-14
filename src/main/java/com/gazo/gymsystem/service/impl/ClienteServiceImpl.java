package com.gazo.gymsystem.service.impl;

import com.gazo.gymsystem.entity.Cliente;
import com.gazo.gymsystem.repository.ClienteRepository;
import com.gazo.gymsystem.service.ClienteService;
import com.gazo.gymsystem.util.IdGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }
    
    @Override
    public Cliente guardar(Cliente cliente) {

        if (cliente.getIdCliente() == null
                || cliente.getIdCliente().isBlank()) {

            cliente.setIdCliente(
                    IdGenerator.generarIdCliente()
            );

            cliente.setFechaRegistro(
                    LocalDateTime.now()
            );

            cliente.setActivo(true);
        }

        return clienteRepository.save(cliente);
    }
}
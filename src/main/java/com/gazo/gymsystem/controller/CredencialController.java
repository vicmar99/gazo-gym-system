package com.gazo.gymsystem.controller;

import com.gazo.gymsystem.entity.Cliente;
import com.gazo.gymsystem.repository.ClienteRepository;
import com.gazo.gymsystem.service.CredencialService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credenciales")
public class CredencialController {

    private final CredencialService credencialService;
    private final ClienteRepository clienteRepository;

    public CredencialController(
            CredencialService credencialService,
            ClienteRepository clienteRepository
    ) {
        this.credencialService = credencialService;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<byte[]> generarQr(
            @PathVariable String idCliente
    ) throws Exception {

        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(() ->
                        new RuntimeException("Cliente no encontrado"));

        byte[] pdf =
                credencialService.generarCredencial(cliente);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=credencial_"
                                + cliente.getIdCliente()
                                + ".pdf"
                )
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);

    }

}
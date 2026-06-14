package com.gazo.gymsystem.controller;

import com.gazo.gymsystem.entity.Cliente;
import com.gazo.gymsystem.repository.AsistenciaRepository;
import com.gazo.gymsystem.repository.ClienteRepository;
import com.gazo.gymsystem.repository.PagoRepository;
import com.gazo.gymsystem.service.ClienteService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    private final ClienteRepository clienteRepository;

    private final PagoRepository pagoRepository;

    private final AsistenciaRepository asistenciaRepository;

    public ClienteController(
            ClienteService clienteService,
            ClienteRepository clienteRepository,
            PagoRepository pagoRepository,
            AsistenciaRepository asistenciaRepository
    ) {
        this.clienteService = clienteService;
        this.clienteRepository = clienteRepository;
        this.pagoRepository = pagoRepository;
        this.asistenciaRepository = asistenciaRepository;
    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "clientes",
                clienteService.listarTodos()
        );

        return "clientes";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute(
                "cliente",
                new Cliente()
        );

        return "cliente-form";
    }
    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable String id,
            Model model
    ) {

        Cliente cliente = clienteRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cliente no encontrado"
                        ));

        model.addAttribute(
                "cliente",
                cliente
        );

        return "cliente-form";
    }
    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute Cliente cliente) {

        clienteService.guardar(cliente);

        return "redirect:/clientes";
    }

    @GetMapping("/{id}")
    public String detalleCliente(
            @PathVariable String id,
            Model model
    ) {

        Cliente cliente = clienteRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cliente no encontrado"
                        ));

        model.addAttribute(
                "cliente",
                cliente
        );

        model.addAttribute(
                "pagos",
                pagoRepository
                        .findByClienteIdClienteOrderByFechaPagoDesc(
                                id
                        )
        );

        model.addAttribute(
                "asistencias",
                asistenciaRepository
                        .findByClienteIdClienteOrderByFechaHoraDesc(
                                id
                        )
        );

        return "clientes/detalle";
    }
}
package com.gazo.gymsystem.controller;

import com.gazo.gymsystem.entity.Cliente;
import com.gazo.gymsystem.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(
            ClienteService clienteService) {

        this.clienteService = clienteService;
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

    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute Cliente cliente) {

        clienteService.guardar(cliente);

        return "redirect:/clientes";
    }
}
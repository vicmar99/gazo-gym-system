package com.gazo.gymsystem.controller;

import com.gazo.gymsystem.dto.PagoRequest;
import com.gazo.gymsystem.entity.Cliente;
import com.gazo.gymsystem.entity.Membresia;
import com.gazo.gymsystem.entity.Usuario;
import com.gazo.gymsystem.repository.ClienteRepository;
import com.gazo.gymsystem.repository.MembresiaRepository;
import com.gazo.gymsystem.repository.UsuarioRepository;
import com.gazo.gymsystem.service.PagoService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@Controller
public class PagoController {

    private final ClienteRepository clienteRepository;
    private final MembresiaRepository membresiaRepository;
    private final PagoService pagoService;
    private final UsuarioRepository usuarioRepository;

    public PagoController(
            ClienteRepository clienteRepository,
            MembresiaRepository membresiaRepository,
            PagoService pagoService,
            UsuarioRepository usuarioRepository
    ) {
        this.clienteRepository = clienteRepository;
        this.membresiaRepository = membresiaRepository;
        this.pagoService = pagoService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/pagos")
    public String mostrarFormulario(Model model) {

        model.addAttribute(
                "clientes",
                clienteRepository.findAll()
        );

        model.addAttribute(
                "membresias",
                membresiaRepository.findAll()
        );

        model.addAttribute(
                "pagoRequest",
                new PagoRequest()
        );

        return "pagos/formulario";
    }

    @PostMapping("/pagos")
    public String registrarPago(
            @ModelAttribute PagoRequest pagoRequest,
            Authentication authentication
    ) {

        Cliente cliente = clienteRepository
                .findById(pagoRequest.getIdCliente())
                .orElseThrow(() ->
                        new RuntimeException("Cliente no encontrado"));

        Membresia membresia = membresiaRepository
                .findById(pagoRequest.getIdMembresia())
                .orElseThrow(() ->
                        new RuntimeException("Membresía no encontrada"));

        Usuario usuario = usuarioRepository
                .findByUsuario(authentication.getName())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        pagoService.registrarPago(
                cliente,
                membresia,
                pagoRequest.getPeriodos(),
                pagoRequest.getDescuento() != null
                        ? pagoRequest.getDescuento()
                        : BigDecimal.ZERO,
                pagoRequest.getMetodoPago().toUpperCase(),
                usuario
        );

        return "redirect:/pagos?success";

    }

    @GetMapping("/pagos/nuevo/{idCliente}")
    public String nuevoPago(
            @PathVariable String idCliente,
            Model model
    ) {

        model.addAttribute(
                "clientes",
                clienteRepository.findAll()
        );

        model.addAttribute(
                "membresias",
                membresiaRepository.findAll()
        );

        PagoRequest pagoRequest = new PagoRequest();

        pagoRequest.setIdCliente(idCliente);

        model.addAttribute(
                "pagoRequest",
                pagoRequest
        );

        return "pagos/formulario";
    }

}
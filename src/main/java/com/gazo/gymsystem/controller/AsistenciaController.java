package com.gazo.gymsystem.controller;

import com.gazo.gymsystem.service.AsistenciaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/asistencias")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    public AsistenciaController(
            AsistenciaService asistenciaService
    ) {
        this.asistenciaService = asistenciaService;
    }

    @GetMapping
    public String mostrarPantalla() {

        return "asistencias/control";
    }

    @PostMapping
    public String registrarAsistencia(
            @RequestParam("qr") String qr,
            Model model
    ) {

        String mensaje =
                asistenciaService.registrarAsistencia(qr);

        model.addAttribute(
                "mensaje",
                mensaje
        );

        return "asistencias/control";
    }

}
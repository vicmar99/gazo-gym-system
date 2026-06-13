package com.gazo.gymsystem.controller;

import com.gazo.gymsystem.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService
    ) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {

        model.addAttribute(
                "clientesActivos",
                dashboardService.obtenerClientesActivos()
        );

        model.addAttribute(
                "asistenciasHoy",
                dashboardService.obtenerAsistenciasHoy()
        );

        model.addAttribute(
                "ingresosHoy",
                dashboardService.obtenerIngresosHoy()
        );

        model.addAttribute(
                "ingresosMes",
                dashboardService.obtenerIngresosMes()
        );

        model.addAttribute(
                "proximosVencimientos",
                dashboardService.obtenerProximosVencimientos()
        );

        model.addAttribute(
                "ultimosPagos",
                dashboardService.obtenerUltimosPagos()
        );

        model.addAttribute(
                "ultimasAsistencias",
                dashboardService.obtenerUltimasAsistencias()
        );

        return "dashboard";
    }
}
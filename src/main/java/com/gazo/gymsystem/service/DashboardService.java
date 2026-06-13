package com.gazo.gymsystem.service;

import com.gazo.gymsystem.entity.Cliente;

import java.math.BigDecimal;
import java.util.List;

public interface DashboardService {

    Long obtenerClientesActivos();

    Long obtenerAsistenciasHoy();

    BigDecimal obtenerIngresosHoy();

    BigDecimal obtenerIngresosMes();

    List<Cliente> obtenerProximosVencimientos();

}
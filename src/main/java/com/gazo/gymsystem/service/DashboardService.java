package com.gazo.gymsystem.service;

import java.math.BigDecimal;

public interface DashboardService {

    Long obtenerClientesActivos();

    Long obtenerAsistenciasHoy();

    BigDecimal obtenerIngresosHoy();

    BigDecimal obtenerIngresosMes();

}
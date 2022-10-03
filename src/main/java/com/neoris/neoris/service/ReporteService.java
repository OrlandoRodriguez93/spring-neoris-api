package com.neoris.neoris.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ReporteService {

    public List<Map<String, Object>> getEstadoCuenta(String fechaInicial, String fechaFinal, Long idCliente);
}

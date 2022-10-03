package com.neoris.neoris.controller;

import com.neoris.neoris.dto.ClienteDTO;
import com.neoris.neoris.service.ClienteService;
import com.neoris.neoris.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    ReporteService reporteService;

    @GetMapping(value="/{idCliente}")
    public ResponseEntity<List<Map<String, Object>>> estadoCuenta(@PathParam("fechaInicial") String fechaInicial, @PathParam("fechaFinal") String fechaFinal, @PathVariable("idCliente") Long idCliente) {
        List<Map<String, Object>> resultado = reporteService.getEstadoCuenta(fechaInicial, fechaFinal, idCliente);
        return ResponseEntity.ok(resultado);
    }
}

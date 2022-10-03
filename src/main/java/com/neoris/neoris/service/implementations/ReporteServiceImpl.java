package com.neoris.neoris.service.implementations;

import com.neoris.neoris.dto.ReporteDTO;
import com.neoris.neoris.repository.MovimientoRepository;
import com.neoris.neoris.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    MovimientoRepository movimientoRepository;

    @Override
    public List<Map<String, Object>> getEstadoCuenta(String fechaInicial, String fechaFinal, Long idCliente) {

        List<Map<String, Object>> resultados = new ArrayList<Map<String, Object>>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        try {
            Date dateI = formatter.parse(fechaInicial);
            Date dateF = formatter.parse(fechaFinal);
            resultados = movimientoRepository.estadoCuenta(fechaInicial, fechaFinal, idCliente);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return resultados;
    }
}

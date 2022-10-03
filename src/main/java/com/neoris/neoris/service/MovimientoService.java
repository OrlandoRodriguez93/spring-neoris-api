package com.neoris.neoris.service;

import java.util.List;

import com.neoris.neoris.dto.MovimientoDTO;
import com.neoris.neoris.exceptions.ErrorResponse;
import org.springframework.stereotype.Service;

import com.neoris.neoris.entity.Movimiento;

@Service
public interface MovimientoService {

    public List<MovimientoDTO> all();

    public MovimientoDTO get(Long id);

    public MovimientoDTO create(MovimientoDTO movimientoDTO) throws ErrorResponse;

    public MovimientoDTO update(MovimientoDTO movimientoDTO);

    public MovimientoDTO delete(Long id);

}

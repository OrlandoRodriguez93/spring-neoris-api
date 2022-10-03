package com.neoris.neoris.converter;

import com.neoris.neoris.dto.MovimientoDTO;
import com.neoris.neoris.entity.Movimiento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MovimientoConverter {

    public MovimientoDTO convertEntityToDto(Movimiento movimiento) {
        ModelMapper modelMapper = new ModelMapper();
        MovimientoDTO movimientoDTO = modelMapper.map(movimiento, MovimientoDTO.class);
        return movimientoDTO;
    }

    public Movimiento convertDtoToEntity(MovimientoDTO movimientoDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Movimiento movimiento = modelMapper.map(movimientoDTO, Movimiento.class);
        return movimiento;
    }
}

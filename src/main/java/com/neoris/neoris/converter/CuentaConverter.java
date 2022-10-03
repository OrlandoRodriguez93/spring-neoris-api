package com.neoris.neoris.converter;

import com.neoris.neoris.dto.CuentaDTO;
import com.neoris.neoris.entity.Cuenta;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CuentaConverter {

    public CuentaDTO convertEntityToDto(Cuenta cuenta) {
        ModelMapper modelMapper = new ModelMapper();
        CuentaDTO cuentaDTO = modelMapper.map(cuenta, CuentaDTO.class);
        return cuentaDTO;
    }

    public Cuenta convertDtoToEntity(CuentaDTO cuentaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Cuenta cuenta = modelMapper.map(cuentaDTO, Cuenta.class);
        return cuenta;
    }
}

package com.neoris.neoris.service;

import java.util.List;

import com.neoris.neoris.dto.CuentaDTO;
import org.springframework.stereotype.Service;

import com.neoris.neoris.entity.Cuenta;

@Service
public interface CuentaService {

    public List<CuentaDTO> all();

    public CuentaDTO get(Long id);

    public CuentaDTO create(CuentaDTO cuentaDTO);

    public CuentaDTO update(CuentaDTO cuentaDTO);

    public CuentaDTO delete(Long id);

}

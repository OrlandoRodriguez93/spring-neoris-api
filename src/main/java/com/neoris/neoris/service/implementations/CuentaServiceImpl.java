package com.neoris.neoris.service.implementations;

import com.neoris.neoris.converter.CuentaConverter;
import com.neoris.neoris.dto.CuentaDTO;
import com.neoris.neoris.entity.Cuenta;
import com.neoris.neoris.repository.CuentaRepository;
import com.neoris.neoris.service.CuentaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;

    @Autowired
    CuentaConverter cuentaConverter;

    @Override
    public List<CuentaDTO> all() {
        log.info("Obteniendo todas las cuentas");
        List<CuentaDTO> cuentaDTOList = new ArrayList<>();
        List<Cuenta> cuentas = cuentaRepository.findAll();

        for (Cuenta cuenta: cuentas){
            CuentaDTO cuentaDTO = cuentaConverter.convertEntityToDto(cuenta);
            cuentaDTOList.add(cuentaDTO);
        }
        return cuentaDTOList;
    }

    @Override
    public CuentaDTO get(Long id) {
        log.info("Obteniendo cuenta {}", id);
        Cuenta cuentaDB = cuentaRepository.findById(id).orElse(null);
        return cuentaConverter.convertEntityToDto(cuentaDB);
    }

    @Override
    public CuentaDTO create(CuentaDTO cuentaDTO) {
        log.info("Guardando cuenta en la base de datos");

        Cuenta cuenta = cuentaConverter.convertDtoToEntity(cuentaDTO);
        cuenta = cuentaRepository.save(cuenta);

        return cuentaConverter.convertEntityToDto(cuenta);
    }

    @Override
    public CuentaDTO update(CuentaDTO cuentaDTO) {
        log.info("Actualizando cuenta en la base de datos");
        Cuenta cuenta = cuentaRepository.findById(cuentaDTO.getId()).orElse(null);
        if (cuenta == null)
            return null;
        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
        cuenta.setSaldo(cuentaDTO.getSaldo());
        cuentaRepository.save(cuenta);
        return cuentaConverter.convertEntityToDto(cuenta);
    }

    @Override
    public CuentaDTO delete(Long id) {
        log.info("Eliminando cuenta de la base de datos");
        Cuenta cuenta = cuentaRepository.findById(id).orElse(null);
        if (cuenta == null)
            return null;
        cuenta.setStatus(false);
        cuentaRepository.save(cuenta);
        return cuentaConverter.convertEntityToDto(cuenta);
    }
}

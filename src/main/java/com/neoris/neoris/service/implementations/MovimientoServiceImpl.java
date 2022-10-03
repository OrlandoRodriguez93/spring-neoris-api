package com.neoris.neoris.service.implementations;

import com.neoris.neoris.converter.MovimientoConverter;
import com.neoris.neoris.dto.MovimientoDTO;
import com.neoris.neoris.entity.Cliente;
import com.neoris.neoris.entity.Cuenta;
import com.neoris.neoris.entity.Movimiento;
import com.neoris.neoris.enums.TipoMovimiento;
import com.neoris.neoris.enums.ValorMovimiento;
import com.neoris.neoris.exceptions.ErrorResponse;
import com.neoris.neoris.repository.ClienteRepository;
import com.neoris.neoris.repository.CuentaRepository;
import com.neoris.neoris.repository.MovimientoRepository;
import com.neoris.neoris.service.MovimientoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    MovimientoConverter movimientoConverter;

    @Override
    public List<MovimientoDTO> all() {
        log.info("Obteniendo todos los movimientos");
        List<MovimientoDTO> movimientoDTOList = new ArrayList<>();
        List<Movimiento> movimientos = movimientoRepository.findAll();

        for (Movimiento movimiento: movimientos){
            MovimientoDTO movimientoDTO = movimientoConverter.convertEntityToDto(movimiento);
            movimientoDTOList.add(movimientoDTO);
        }
        return movimientoDTOList;
    }

    @Override
    public MovimientoDTO get(Long id) {
        log.info("Obteniendo movimiento {}", id);
        Movimiento movimiento = movimientoRepository.findById(id).orElse(null);
        return movimientoConverter.convertEntityToDto(movimiento);
    }

    @Override
    public MovimientoDTO create(MovimientoDTO movimientoDTO) throws ErrorResponse {
        log.info("Guardando movimiento en la base de datos");

        Movimiento movimiento = movimientoConverter.convertDtoToEntity(movimientoDTO);
        //Obtenemos la cuenta del movimiento
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(movimientoDTO.getNumeroCuenta());
        if(cuenta == null){
            return null;
        }

        double saldoFinal = 0.00;

        if(movimiento.getTipoMovimiento().equals(TipoMovimiento.RETIRO)){
            if(movimiento.getValor().equals(ValorMovimiento.DEBITO)){
                saldoFinal = cuenta.getSaldo() - movimiento.getSaldo();
                if(cuenta.getSaldo()<= 0.00 || saldoFinal<=0){
                    throw new ErrorResponse("Saldo no disponible.");
                }
                cuenta.setSaldo(saldoFinal);
                Cuenta cuentaDB = cuentaRepository.saveAndFlush(cuenta);
                movimiento.setCuenta(cuentaDB);
                movimientoRepository.save(movimiento);
            }
        } else if(movimiento.getTipoMovimiento().equals(TipoMovimiento.DEPOSITO)){
            saldoFinal = cuenta.getSaldo() + movimiento.getSaldo();
            cuenta.setSaldo(saldoFinal);
            Cuenta cuentaDB = cuentaRepository.saveAndFlush(cuenta);
            movimiento.setCuenta(cuentaDB);
            movimientoRepository.save(movimiento);
        }
        return movimientoConverter.convertEntityToDto(movimiento);
    }

    @Override
    public MovimientoDTO update(MovimientoDTO movimientoDTO) {
        log.info("Actualizando movimiento en la base de datos");
        Movimiento movimiento = movimientoRepository.findById(movimientoDTO.getId()).orElse(null);
        if (movimiento == null)
            return null;
        movimiento.setTipoMovimiento(movimientoDTO.getTipoMovimiento());
        movimiento.setSaldo(movimientoDTO.getSaldo());
        movimiento.setFecha(movimientoDTO.getFecha());
        movimiento.setValor(movimientoDTO.getValorMovimiento());
        movimientoRepository.save(movimiento);
        return movimientoConverter.convertEntityToDto(movimiento);
    }

    @Override
    public MovimientoDTO delete(Long id) {
        log.info("Eliminando movimiento de la base de datos");
        Movimiento movimiento = movimientoRepository.findById(id).orElse(null);
        if (movimiento == null)
            return null;
        movimiento.setStatus(false);
        movimientoRepository.save(movimiento);
        return movimientoConverter.convertEntityToDto(movimiento);
    }


}

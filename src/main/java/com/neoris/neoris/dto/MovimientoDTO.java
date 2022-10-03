package com.neoris.neoris.dto;

import com.neoris.neoris.enums.TipoMovimiento;
import com.neoris.neoris.enums.ValorMovimiento;
import lombok.Data;

import java.util.Date;

@Data
public class MovimientoDTO {

    private String numeroCuenta;
    private Long id;
    private TipoMovimiento tipoMovimiento;
    private ValorMovimiento valorMovimiento;
    private Double saldo;
    private Date fecha;
}

package com.neoris.neoris.dto;

import com.neoris.neoris.enums.TipoCuenta;
import lombok.Data;

@Data
public class CuentaDTO {

    private Long id;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private Double saldo;
}

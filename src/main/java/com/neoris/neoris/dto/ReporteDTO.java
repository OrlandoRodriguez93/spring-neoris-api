package com.neoris.neoris.dto;

import com.neoris.neoris.enums.Genero;
import lombok.Data;

import java.util.Date;

@Data
public class ReporteDTO {

    private Date fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private Double saldoInicial;
    private boolean status;
    private Double movimiento;
    private Double saldoDisponible;
}

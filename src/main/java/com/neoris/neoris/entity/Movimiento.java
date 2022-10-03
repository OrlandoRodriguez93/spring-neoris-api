package com.neoris.neoris.entity;

import com.neoris.neoris.enums.TipoMovimiento;
import com.neoris.neoris.enums.ValorMovimiento;
import com.neoris.neoris.model.Control;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "tbl_movimiento")
@Where(clause = "status=true")
public class Movimiento extends Control implements Serializable {

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    @Enumerated(EnumType.STRING)
    private ValorMovimiento valor;
    private Double saldo;
    private Date fecha;

    @ManyToOne()
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

}

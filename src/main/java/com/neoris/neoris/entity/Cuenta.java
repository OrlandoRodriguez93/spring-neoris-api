package com.neoris.neoris.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.neoris.neoris.enums.TipoCuenta;
import com.neoris.neoris.model.Control;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Setter
@Getter
@Table(name = "tbl_cuenta")
@Where(clause = "status=true")
public class Cuenta extends Control implements Serializable {

    private String numeroCuenta;

    @Enumerated(EnumType.STRING)
    private TipoCuenta tipoCuenta;

    @DecimalMin("0.0")
    private Double saldo;

    @ManyToOne()
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private List<Movimiento> movimientos;

}

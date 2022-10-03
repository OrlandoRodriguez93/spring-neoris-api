package com.neoris.neoris.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.neoris.neoris.enums.TipoCuenta;
import lombok.*;
import org.hibernate.annotations.Where;

@Entity
@Setter
@Getter
@Table(name = "tbl_cuenta")
@Where(clause = "status=true")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cuenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status = true;

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

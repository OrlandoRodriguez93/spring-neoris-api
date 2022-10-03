package com.neoris.neoris.entity;

import com.neoris.neoris.enums.TipoMovimiento;
import com.neoris.neoris.enums.ValorMovimiento;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "tbl_movimiento")
@Where(clause = "status=true")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movimiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status = true;

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

package com.neoris.neoris.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "tbl_cliente")
@Where(clause = "status=true")
public class Cliente extends Persona {

    private String password;
    @Column(name = "id_persona")
    private Long idPersona;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private List<Cuenta> cuentas;
}

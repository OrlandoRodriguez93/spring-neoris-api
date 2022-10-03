package com.neoris.neoris.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.neoris.neoris.enums.Genero;
import com.neoris.neoris.model.Control;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Setter
@Getter
@Entity
@Table(name="tbl_persona")
@Where(clause = "status=true")
@Inheritance(strategy= InheritanceType.JOINED)
public class Persona extends Control implements Serializable {

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Genero genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}

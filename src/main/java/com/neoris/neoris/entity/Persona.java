package com.neoris.neoris.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.neoris.neoris.enums.Genero;
import lombok.*;
import org.hibernate.annotations.Where;

@Setter
@Getter
@Entity
@Table(name="tbl_persona")
@Where(clause = "status=true")
@Inheritance(strategy= InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status = true;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Genero genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}

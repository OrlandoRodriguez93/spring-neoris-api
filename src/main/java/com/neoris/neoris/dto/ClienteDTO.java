package com.neoris.neoris.dto;

import com.neoris.neoris.enums.Genero;
import lombok.Data;

@Data
public class ClienteDTO {

    private Long id;
    private String nombre;
    private Genero genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String password;
}

package com.veterinaria_back.dto;

import lombok.Data;

@Data
public class Dueño {

    private int id;

    private String nombre;

    private String tipo_identificacion;

    private String identificacion;

    private String ciudad;

    private String direccion;

    private String telefono;
}

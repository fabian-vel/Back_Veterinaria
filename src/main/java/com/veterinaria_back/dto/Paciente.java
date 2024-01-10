package com.veterinaria_back.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Paciente {

    private int id;
    private String nombre;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_nacimiento;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_registro;
    private int id_raza;
    private  String nombre_raza;
    private int id_especie;
    private String nombre_especie;
    private int id_dueño;
    private String nombre_duenio;
    private String ciudad;
    private String tipo_identificacion;
    private String identificacion;
    private String direccion;
    private String telefono;
}

package com.veterinaria_back.dao;

import com.veterinaria_back.dto.Paciente;
import com.veterinaria_back.exception.CustomException;

import java.util.List;

public interface PacienteDAO {
    void insert(Paciente paciente) throws CustomException;

    void update(Paciente paciente) throws CustomException;

    void delete(int id) throws CustomException;

    Paciente getById(int id) throws CustomException;

    List<Paciente> getAll() throws CustomException;
}

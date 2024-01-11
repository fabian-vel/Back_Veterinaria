package com.veterinaria_back.manager;

import com.veterinaria_back.dto.Paciente;
import com.veterinaria_back.exception.CustomException;

import java.util.List;

public interface PacienteManager {
    void insertOrUpdatePaciente(Paciente paciente) throws CustomException;

    void deletePaciente(int id) throws CustomException;

    List<Paciente> getAllPaciente() throws CustomException;
}

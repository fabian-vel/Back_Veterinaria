package com.veterinaria_back.service;

import com.veterinaria_back.dto.Paciente;
import com.veterinaria_back.exception.CustomException;

import java.util.List;

public interface PacienteService {
    void findSaveOrUpdatePaciente(Paciente paciente) throws CustomException;

    void findDeletePacinete(int id) throws CustomException;

    List<Paciente> findGetAllPaciente() throws CustomException;
}

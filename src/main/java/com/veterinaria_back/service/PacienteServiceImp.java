package com.veterinaria_back.service;

import com.veterinaria_back.dto.Paciente;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.manager.PacienteManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PacienteServiceImp implements PacienteService{

    private PacienteManager pacienteManager;

    public PacienteServiceImp(PacienteManager pacienteManager) {
        this.pacienteManager = pacienteManager;
    }

    @Override
    public void findSaveOrUpdatePaciente(Paciente paciente) throws CustomException {
        try {
            pacienteManager.insertOrUpdatePaciente(paciente);
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
    }

    @Override
    public void findDeletePacinete(int id) throws CustomException {
        try {
            pacienteManager.deletePaciente(id);
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
    }

    @Override
    public List<Paciente> findGetAllPaciente() throws CustomException {
        List<Paciente> listPacientes;
        try {
            listPacientes = pacienteManager.getAllPaciente();
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return listPacientes;
    }
}

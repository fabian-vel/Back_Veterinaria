package com.veterinaria_back.manager;

import com.veterinaria_back.dao.PacienteDAO;
import com.veterinaria_back.dto.Paciente;
import com.veterinaria_back.exception.CustomException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PacienteManagerImp implements PacienteManager{

    private PacienteDAO pacienteDAO;

    public PacienteManagerImp(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    @Override
    public void insertOrUpdatePaciente(Paciente paciente) throws CustomException {
        try {
            Paciente pac = pacienteDAO.getById(paciente.getId());
            if(pac == null){
                pacienteDAO.insert(paciente);
            }else {
                pacienteDAO.update(paciente);
            }
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
    }

    @Override
    public void deletePaciente(int id) throws CustomException {
        try {
            pacienteDAO.delete(id);
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
    }

    @Override
    public List<Paciente> getAllPaciente() throws CustomException {
        List<Paciente> listPacientes;
        try {
            listPacientes = pacienteDAO.getAll();
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return listPacientes;
    }
}

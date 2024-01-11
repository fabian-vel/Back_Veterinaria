package com.veterinaria_back.service;

import com.veterinaria_back.dto.Dueño;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.manager.DueñoManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DueñoServiceImp implements DueñoService{

    private DueñoManager dueñoManager;

    public DueñoServiceImp(DueñoManager dueñoManager) {
        this.dueñoManager = dueñoManager;
    }

    @Override
    public void findInsertOrUpdateDueño(Dueño dueño) throws CustomException {
        try {
            dueñoManager.insertOrUpdateDueño(dueño);
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
    }

    @Override
    public void findDeleteDueño(int id) throws CustomException {
        try {
            dueñoManager.deleteDueño(id);
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
    }

    @Override
    public List<Dueño> findGetAllDueño() throws CustomException {
        List<Dueño> listDueños;
        try {
            listDueños = dueñoManager.getAllDueño();
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return listDueños;
    }
}

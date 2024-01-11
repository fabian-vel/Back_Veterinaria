package com.veterinaria_back.manager;

import com.veterinaria_back.dao.DueñoDAO;
import com.veterinaria_back.dto.Dueño;
import com.veterinaria_back.exception.CustomException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DueñoManagerImp implements DueñoManager{

    private DueñoDAO dueñoDAO;

    public DueñoManagerImp(DueñoDAO dueñoDAO) {
        this.dueñoDAO = dueñoDAO;
    }

    @Override
    public void insertOrUpdateDueño(Dueño dueño) throws CustomException {
        try {
            Dueño due = dueñoDAO.getById(dueño.getId());
            if (due == null){
                dueñoDAO.insert(dueño);
            }else {
                dueñoDAO.update(dueño);
            }
        }catch (CustomException e){
            throw  e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
    }

    @Override
    public void deleteDueño(int id) throws CustomException {
        try {
            dueñoDAO.delete(id);
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
    }

    @Override
    public List<Dueño> getAllDueño() throws CustomException {
        List<Dueño> listDueños;
        try {
            listDueños = dueñoDAO.getAll();
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return listDueños;
    }
}

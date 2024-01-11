package com.veterinaria_back.manager;

import com.veterinaria_back.dao.RazaDAO;
import com.veterinaria_back.dto.Raza;
import com.veterinaria_back.exception.CustomException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RazaManagerImp implements RazaManager{

    private RazaDAO razaDAO;

    public RazaManagerImp(RazaDAO razaDAO) {
        this.razaDAO = razaDAO;
    }

    @Override
    public List<Raza> getALLRaza() throws CustomException {
        List<Raza> listRazas;
        try {
            listRazas = razaDAO.getAll();
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return listRazas;
    }

    @Override
    public List<Raza> getRazaByEspecie(int id) throws CustomException {
        List<Raza> razas = null;
        try {
            razas = razaDAO.getByEspecie(id);
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return razas;
    }

    @Override
    public Raza getRazaById(int id) throws CustomException {
        Raza raza;
        try {
            raza = razaDAO.getById(id);
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return raza;
    }
}

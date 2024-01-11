package com.veterinaria_back.manager;

import com.veterinaria_back.dao.EspecieDAO;
import com.veterinaria_back.dto.Especie;
import com.veterinaria_back.exception.CustomException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EspecieManagerImp implements EspecieManager{

    private EspecieDAO especieDAO;

    public EspecieManagerImp(EspecieDAO especieDAO) {
        this.especieDAO = especieDAO;
    }

    @Override
    public List<Especie> getlAllEspecies() throws CustomException {
        List<Especie> listEspecies;
        try {
            listEspecies = especieDAO.getAll();
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return listEspecies;
    }

    @Override
    public Especie getEspecienById(int id) throws CustomException {
        Especie especies;
        try {
            especies = especieDAO.getById(id);
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return especies;
    }
}

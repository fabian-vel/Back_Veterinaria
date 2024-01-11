package com.veterinaria_back.service;

import com.veterinaria_back.dto.Especie;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.manager.EspecieManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EspecieServiceImp implements EspecieService{

    private EspecieManager especieManager;

    public EspecieServiceImp(EspecieManager especieManager) {
        this.especieManager = especieManager;
    }

    @Override
    public List<Especie> findGetAllEspecies() throws CustomException {
        List<Especie> listEspecies;
        try {
            listEspecies = especieManager.getlAllEspecies();
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return listEspecies;
    }

    @Override
    public Especie findGetEspecieById(int id) throws CustomException {
        Especie especie;
        try {
            especie = especieManager.getEspecienById(id);
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return especie;
    }
}

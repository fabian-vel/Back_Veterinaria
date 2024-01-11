package com.veterinaria_back.service;

import com.veterinaria_back.dto.Raza;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.manager.RazaManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RazaServiceImp implements RazaService{

    private RazaManager razaManager;

    public RazaServiceImp(RazaManager razaManager) {
        this.razaManager = razaManager;
    }

    @Override
    public List<Raza> findGetAllRaza() throws CustomException {
        List<Raza> listRazas;
        try {
            listRazas = razaManager.getALLRaza();
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return listRazas;
    }

    @Override
    public List<Raza> findGetRazaByEspecie(int id) throws CustomException {
        List<Raza> razas;
        try {
            razas = razaManager.getRazaByEspecie(id);
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return razas;
    }

    @Override
    public Raza findGetRazaById(int id) throws CustomException {
        Raza raza;
        try {
            raza = razaManager.getRazaById(id);
        }catch (CustomException e){
            throw e;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return raza;
    }
}

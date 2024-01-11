package com.veterinaria_back.manager;

import com.veterinaria_back.dto.Raza;
import com.veterinaria_back.exception.CustomException;

import java.util.List;

public interface RazaManager {
    List<Raza> getALLRaza() throws CustomException;

    List<Raza> getRazaByEspecie(int id) throws CustomException;

    Raza getRazaById(int id) throws CustomException;
}

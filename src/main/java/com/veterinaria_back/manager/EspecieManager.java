package com.veterinaria_back.manager;

import com.veterinaria_back.dto.Especie;
import com.veterinaria_back.exception.CustomException;

import java.util.List;

public interface EspecieManager {
    List<Especie> getlAllEspecies() throws CustomException;

    Especie getEspecienById(int id) throws CustomException;
}

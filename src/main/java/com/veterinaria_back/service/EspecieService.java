package com.veterinaria_back.service;

import com.veterinaria_back.dto.Especie;
import com.veterinaria_back.exception.CustomException;

import java.util.List;

public interface EspecieService {
    List<Especie> findGetAllEspecies() throws CustomException;

    Especie findGetEspecieById(int id) throws CustomException;
}

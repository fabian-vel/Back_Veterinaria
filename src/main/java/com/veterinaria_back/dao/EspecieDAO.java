package com.veterinaria_back.dao;

import com.veterinaria_back.dto.Especie;
import com.veterinaria_back.exception.CustomException;

import java.util.List;

public interface EspecieDAO {
    Especie getById(int id) throws CustomException;

    List<Especie> getAll() throws CustomException;
}

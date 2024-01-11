package com.veterinaria_back.dao;

import com.veterinaria_back.dto.Raza;
import com.veterinaria_back.exception.CustomException;

import java.util.List;

public interface RazaDAO {
    Raza getById(int id) throws CustomException;

    List<Raza> getByEspecie(int id) throws CustomException;

    List<Raza> getAll() throws CustomException;
}

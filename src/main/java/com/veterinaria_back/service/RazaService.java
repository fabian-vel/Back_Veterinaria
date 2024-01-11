package com.veterinaria_back.service;

import com.veterinaria_back.dto.Raza;
import com.veterinaria_back.exception.CustomException;

import java.util.List;

public interface RazaService {
    List<Raza> findGetAllRaza() throws CustomException;

    List<Raza> findGetRazaByEspecie(int id) throws CustomException;

    Raza findGetRazaById(int id) throws CustomException;
}

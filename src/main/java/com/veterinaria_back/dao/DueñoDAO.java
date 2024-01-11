package com.veterinaria_back.dao;

import com.veterinaria_back.dto.Dueño;
import com.veterinaria_back.exception.CustomException;

import java.util.List;

public interface DueñoDAO {
    void insert(Dueño dueño) throws CustomException;

    void update(Dueño dueño) throws CustomException;

    void delete(int id) throws CustomException;

    Dueño getById(int id) throws CustomException;

    List<Dueño> getAll() throws CustomException;
}

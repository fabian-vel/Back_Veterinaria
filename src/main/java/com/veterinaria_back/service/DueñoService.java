package com.veterinaria_back.service;

import com.veterinaria_back.dto.Dueño;
import com.veterinaria_back.exception.CustomException;

import java.util.List;

public interface DueñoService {
    void findInsertOrUpdateDueño(Dueño dueño) throws CustomException;

    void findDeleteDueño(int id) throws CustomException;

    List<Dueño> findGetAllDueño() throws CustomException;
}

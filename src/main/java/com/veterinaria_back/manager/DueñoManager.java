package com.veterinaria_back.manager;

import com.veterinaria_back.dto.Dueño;
import com.veterinaria_back.exception.CustomException;

import java.util.List;

public interface DueñoManager {
    void insertOrUpdateDueño(Dueño dueño) throws CustomException;

    void deleteDueño(int id) throws CustomException;

    List<Dueño> getAllDueño() throws CustomException;
}

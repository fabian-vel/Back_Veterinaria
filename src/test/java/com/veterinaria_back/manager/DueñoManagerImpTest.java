package com.veterinaria_back.manager;

import com.veterinaria_back.dao.DueñoDAO;
import com.veterinaria_back.dto.Dueño;
import com.veterinaria_back.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class DueñoManagerImpTest {

    @Mock
    private DueñoDAO dueñoDAO;

    @InjectMocks
    private DueñoManagerImp dueñoManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertOrUpdateDueño_Insert() throws CustomException {
        Dueño dueño = new Dueño();
        dueño.setId(1);

        when(dueñoDAO.getById(1)).thenReturn(null);

        assertDoesNotThrow(() -> dueñoManager.insertOrUpdateDueño(dueño));
    }

    @Test
    void testInsertOrUpdateDueño_Update() throws CustomException {
        Dueño dueño = new Dueño();
        dueño.setId(1);

        when(dueñoDAO.getById(1)).thenReturn(dueño);

        assertDoesNotThrow(() -> dueñoManager.insertOrUpdateDueño(dueño));
    }

    @Test
    void testInsertOrUpdateDueño_ExceptionThrown() throws CustomException {

        doThrow(RuntimeException.class).when(dueñoDAO).insert(new Dueño());

        assertThrows(Exception.class, () -> dueñoManager.insertOrUpdateDueño(new Dueño()));
    }

    @Test
    void testInsertOrUpdateDueño_CustomExceptionThrown() throws CustomException{

        doThrow(CustomException.class).when(dueñoDAO).insert(new Dueño());

        assertThrows(CustomException.class, () -> dueñoManager.insertOrUpdateDueño(new Dueño()));
    }

    @Test
    void testDeleteDueño() throws CustomException {
        int id = 1;
        assertDoesNotThrow(() -> dueñoManager.deleteDueño(id));
    }

    @Test
    void testDeleteDueño_ExceptionThrown() throws CustomException{
        int id = 77;

        doThrow(RuntimeException.class).when(dueñoDAO).delete(id);

        assertThrows(Exception.class, () -> dueñoManager.deleteDueño(id));
    }

    @Test
    void testDeleteDueño_CustomExceptionThrown() throws CustomException{
        int id = 77;
        doThrow(CustomException.class).when(dueñoDAO).delete(id);

        assertThrows(CustomException.class, () -> dueñoManager.deleteDueño(id));
    }

    @Test
    void testGetAllDueño() throws CustomException {
        List<Dueño> expectedListaDueño = Arrays.asList(new Dueño(), new Dueño());
        when(dueñoDAO.getAll()).thenReturn(expectedListaDueño);

        List<Dueño> actualListDueño = dueñoManager.getAllDueño();

        assertEquals(expectedListaDueño, actualListDueño);
    }

    @Test
    void testGetlAllDueño_ExceptionThrown() throws CustomException{

        when(dueñoDAO.getAll()).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> dueñoManager.getAllDueño());
    }

    @Test
    void testGetlAllDueño_CustomExceptionThrown() throws CustomException{

        when(dueñoDAO.getAll()).thenThrow(new CustomException());

        assertThrows(CustomException.class, () -> dueñoManager.getAllDueño());
    }

}
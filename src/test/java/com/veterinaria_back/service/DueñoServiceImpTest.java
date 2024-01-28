package com.veterinaria_back.service;

import com.veterinaria_back.dto.Dueño;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.manager.DueñoManager;
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

class DueñoServiceImpTest {

    @Mock
    private DueñoManager dueñoManager;

    @InjectMocks
    private DueñoServiceImp dueñoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindInsertOrUpdateDueño() {
        Dueño dueño = new Dueño();
        assertDoesNotThrow(() -> dueñoService.findInsertOrUpdateDueño(dueño));
    }

    @Test
    void testFindInsertOrUpdateDueño_ExceptionThrown() throws CustomException {

        doThrow(RuntimeException.class).when(dueñoManager).insertOrUpdateDueño(new Dueño());

        assertThrows(Exception.class, () -> dueñoService.findInsertOrUpdateDueño(new Dueño()));
    }

    @Test
    void testFindInsertOrUpdateDueño_CustomExceptionThrown() throws CustomException {

        doThrow(CustomException.class).when(dueñoManager).insertOrUpdateDueño(new Dueño());

        assertThrows(CustomException.class, () -> dueñoService.findInsertOrUpdateDueño(new Dueño()));
    }

    @Test
    void findDeleteDueño() {
        int id = 1;
        assertDoesNotThrow(() -> dueñoService.findDeleteDueño(id));
    }

    @Test
    void testFindDeleteDueño_ExceptionThrown() throws CustomException {
        int id = 201;
        doThrow(RuntimeException.class).when(dueñoManager).deleteDueño(id);

        assertThrows(Exception.class, () -> dueñoService.findDeleteDueño(id));
    }

    @Test
    void testFindDeleteDueño_CustomExceptionManagerThrown() throws CustomException {
        int id = 201;
        doThrow(CustomException.class).when(dueñoManager).deleteDueño(id);

        assertThrows(CustomException.class, () -> dueñoService.findDeleteDueño(id));
    }

    @Test
    void findGetAllDueño() throws CustomException {
        List<Dueño> expectedListDueño = Arrays.asList(new Dueño(), new Dueño());

        when(dueñoManager.getAllDueño()).thenReturn(expectedListDueño);

        List<Dueño> actualListDueño = dueñoService.findGetAllDueño();

        assertEquals(expectedListDueño, actualListDueño);
    }

    @Test
    void testFindGetAllDueño_ExceptionThrown() throws CustomException {

        when(dueñoManager.getAllDueño()).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> dueñoService.findGetAllDueño());
    }

    @Test
    void testFindGetAllDueño_CustomExceptionThrown() throws CustomException {

        when(dueñoManager.getAllDueño()).thenThrow(new CustomException());

        assertThrows(CustomException.class, () -> dueñoService.findGetAllDueño());
    }

}
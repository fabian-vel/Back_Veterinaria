package com.veterinaria_back.manager;

import com.veterinaria_back.dao.RazaDAO;
import com.veterinaria_back.dto.Raza;
import com.veterinaria_back.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RazaManagerImpTest {

    @Mock
    private RazaDAO razaDAO;

    @InjectMocks
    private RazaManagerImp razaManager;

    private List<Raza> expectedListRaza;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        expectedListRaza = Arrays.asList(new Raza(), new Raza());
    }

    @Test
    void testGetALLRaza() throws CustomException {

        when(razaDAO.getAll()).thenReturn(expectedListRaza);

        List<Raza> actualListRaza = razaManager.getALLRaza();

        assertEquals(expectedListRaza, actualListRaza);
    }

    @Test
    void testGetlAllRaza_ExceptionThrown() throws CustomException{

        when(razaDAO.getAll()).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> razaManager.getALLRaza());
    }

    @Test
    void testGetlAllRaza_CustomExceptionThrown() throws CustomException{

        when(razaDAO.getAll()).thenThrow(new CustomException());

        assertThrows(CustomException.class, () -> razaManager.getALLRaza());
    }

    @Test
    void testGetRazaByEspecie() throws CustomException {
        int id = 1;

        when(razaDAO.getByEspecie(id)).thenReturn(expectedListRaza);

        List<Raza> actualListRaza = razaManager.getRazaByEspecie(id);

        assertEquals(expectedListRaza, actualListRaza);
    }

    @Test
    void testGetRazaByEspecie_ExceptionThrown() throws CustomException {
        int id = 99;

        when(razaDAO.getByEspecie(id)).thenThrow(new RuntimeException());

        assertThrows(CustomException.class, () -> razaManager.getRazaByEspecie(id));
    }

    @Test
    void testGetRazaByEspecie_CustomExceptionThrown() throws CustomException {
        int id = 99;

        when(razaDAO.getByEspecie(id)).thenThrow(new CustomException());

        assertThrows(CustomException.class, () -> razaManager.getRazaByEspecie(id));
    }

    @Test
    void testGetRazaById() throws CustomException {
        int id = 1;
        Raza expectedRaza = new Raza();

        when(razaDAO.getById(id)).thenReturn(expectedRaza);

        Raza actualRaza = razaManager.getRazaById(id);

        assertEquals(expectedRaza, actualRaza);
    }

    @Test
    void testGetRazaById_ExceptionThrown() throws CustomException{
        int id = 2;

        when(razaDAO.getById(id)).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> razaManager.getRazaById(id));
    }

    @Test
    void testGetRazaById_CustomExceptionThrown() throws CustomException{
        int id = 2;

        when(razaDAO.getById(id)).thenThrow(new CustomException());

        assertThrows(CustomException.class, () -> razaManager.getRazaById(id));
    }

}
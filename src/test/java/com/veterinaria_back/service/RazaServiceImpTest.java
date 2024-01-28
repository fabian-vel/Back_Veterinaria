package com.veterinaria_back.service;

import com.veterinaria_back.dto.Raza;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.manager.RazaManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RazaServiceImpTest {

    @Mock
    private RazaManager razaManager;

    @InjectMocks
    private RazaServiceImp razaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void    testFindGetAllRaza() throws CustomException {
        List<Raza> expectedListRaza = Arrays.asList(new Raza(), new Raza());

        when(razaManager.getALLRaza()).thenReturn(expectedListRaza);

        List<Raza> actualListRaza = razaService.findGetAllRaza();

        assertEquals(expectedListRaza, actualListRaza);
    }

    @Test
    void testFindGetAllRaza_ExceptionThrown() throws CustomException {

        when(razaManager.getALLRaza()).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> razaService.findGetAllRaza());
    }

    @Test
    void testFindGetAllRaza_CustomExceptionThrown() throws CustomException {

        when(razaManager.getALLRaza()).thenThrow(new CustomException());

        assertThrows(CustomException.class, () -> razaService.findGetAllRaza());
    }

    @Test
    void testFindGetRazaByEspecie() throws CustomException {
        int id = 1;
        List<Raza> expectedListRaza = Arrays.asList(new Raza(), new Raza());

        when(razaManager.getRazaByEspecie(id)).thenReturn(expectedListRaza);

        List<Raza> actualListRaza = razaService.findGetRazaByEspecie(id);

        assertEquals(expectedListRaza, actualListRaza);
    }

    @Test
    void testFindGetRazaByEspecie_ExceptionThrown() throws CustomException {
        int id = 2;
        when(razaManager.getRazaByEspecie(id)).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> razaService.findGetRazaByEspecie(id));
    }

    @Test
    void testFindGetRazaByEspecie_CustomExceptionThrown() throws CustomException {
        int id = 2;
        when(razaManager.getRazaByEspecie(id)).thenThrow(new CustomException());

        assertThrows(CustomException.class, () -> razaService.findGetRazaByEspecie(id));
    }

    @Test
    void testFindGetRazaById() throws CustomException {
        int id = 1;
        Raza expectedRaza = new Raza();

        when(razaManager.getRazaById(id)).thenReturn(expectedRaza);

        Raza actualRaza = razaService.findGetRazaById(id);

        assertEquals(expectedRaza, actualRaza);
    }

    @Test
    void testFindGetRazaById_ExceptionThrown() throws CustomException {
        int id = 48;

        when(razaManager.getRazaById(id)).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> razaService.findGetRazaById(id));
    }

    @Test
    void testGetRazaById_ExceptionDAOThrown() throws CustomException {
        int id = 48;

        when(razaManager.getRazaById(id)).thenThrow(new CustomException());

        assertThrows(CustomException.class, () -> razaService.findGetRazaById(id));
    }

}
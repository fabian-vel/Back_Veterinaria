package com.veterinaria_back.service;

import com.veterinaria_back.dto.Especie;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.manager.EspecieManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EspecieServiceImpTest {

    @Mock
    private EspecieManager especieManager;

    @InjectMocks
    private EspecieServiceImp especieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindGetAllEspecies() throws CustomException {
        List<Especie> expectedListEspecie = Arrays.asList(new Especie(), new Especie());

        when(especieManager.getlAllEspecies()).thenReturn(expectedListEspecie);

        List<Especie> actualListEspecie = especieService.findGetAllEspecies();

        assertEquals(expectedListEspecie, actualListEspecie);
    }

    @Test
    void testFindGetAllEspecies_ExceptionThrown() throws CustomException {

        when(especieManager.getlAllEspecies()).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> especieService.findGetAllEspecies());
    }

    @Test
    void testFindGetAllEspecies_CustomExceptionThrown() throws CustomException {

        when(especieManager.getlAllEspecies()).thenThrow(new CustomException());

        assertThrows(CustomException.class, () -> especieService.findGetAllEspecies());
    }

    @Test
    void testFindGetEspecieById() throws CustomException {
        int id = 1;
        Especie expectedEspecie = new Especie();

        when(especieManager.getEspecienById(id)).thenReturn(expectedEspecie);

        Especie actualEspecie = especieService.findGetEspecieById(id);

        assertEquals(expectedEspecie, actualEspecie);
    }

    @Test
    void testFindGetEspecieById_ExceptionThrown() throws CustomException {
        int id = 12;

        when(especieManager.getEspecienById(id)).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> especieService.findGetEspecieById(id));
    }

    @Test
    void testGetEspecienById_ExceptionDAOThrown() throws CustomException {
        int id = 12;

        when(especieManager.getEspecienById(id)).thenThrow(new CustomException());

        assertThrows(CustomException.class, () -> especieService.findGetEspecieById(id));
    }

}
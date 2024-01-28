package com.veterinaria_back.manager;

import com.veterinaria_back.dao.EspecieDAO;
import com.veterinaria_back.dto.Especie;
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

class EspecieManagerImpTest {

    @Mock
    private EspecieDAO especieDAO;

    @InjectMocks
    private EspecieManagerImp especieManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testGetlAllEspecies() throws CustomException {
        List<Especie> expectedListEspecie = Arrays.asList(new Especie(), new Especie());

        when(especieDAO.getAll()).thenReturn(expectedListEspecie);

        List<Especie> actualtedListEspecie = especieManager.getlAllEspecies();

        assertEquals(expectedListEspecie, actualtedListEspecie);
    }

    @Test
    void testGetlAllEspecies_ExceptionThrown() throws CustomException{

        when(especieDAO.getAll()).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> especieManager.getlAllEspecies());
    }

    @Test
    void testGetlAllEspecies_CustomExceptionThrown() throws CustomException{

        when(especieDAO.getAll()).thenThrow(new CustomException());

        assertThrows(CustomException.class, () -> especieManager.getlAllEspecies());
    }

    @Test
    void testGetEspecienById() throws CustomException {
        int id = 1;
        Especie  expectedEspecie = new Especie();

        when(especieDAO.getById(id)).thenReturn(expectedEspecie);

        Especie actualEspecie = especieManager.getEspecienById(id);

        assertEquals(expectedEspecie, actualEspecie);
    }

    @Test
    void testGetEspecienById_ExceptionThrown() throws CustomException{
        int id = 2;

        when(especieDAO.getById(id)).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> especieManager.getEspecienById(id));
    }

    @Test
    void testGetEspecienById_CustomExceptionThrown() throws CustomException{
        int id = 2;

        when(especieDAO.getById(id)).thenThrow(new CustomException());

        assertThrows(CustomException.class, () -> especieManager.getEspecienById(id));
    }

}
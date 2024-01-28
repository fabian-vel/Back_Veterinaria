package com.veterinaria_back.dao;

import com.veterinaria_back.dto.Especie;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.mapper.EspecieMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EspecieDAOImpTest {

    private static final String SELECT = "SELECT id, nombre, nombre_cientifico FROM especie";
    private static final String SELECTBYID = SELECT + " WHERE id = ?";

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private DataSource dataSource;

    @InjectMocks
    private EspecieDAOImp especieDAO;

    private Especie expectedEspecie;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        especieDAO.jdbcTemplate = jdbcTemplate;

        expectedEspecie = new Especie();
        expectedEspecie.setId(1);
        expectedEspecie.setNombre("Perro");
        expectedEspecie.setNombre_cientifico("Canis lupus familiaris");
    }


    @Test
    void testGetById() throws CustomException {

        when(jdbcTemplate.queryForObject(eq(SELECTBYID), any(EspecieMapper.class), anyInt())).thenReturn(expectedEspecie);

        Especie actualEspecie = especieDAO.getById(1);

        assertEquals(expectedEspecie, actualEspecie);
    }

    @Test
    void testGetByIdReturnsNull() throws CustomException {
        int id = 99;

        when(jdbcTemplate.queryForObject(eq(SELECTBYID), any(EspecieMapper.class), anyInt())).thenReturn(null);

        Especie actualEspecie = especieDAO.getById(id);

        assertEquals(null, actualEspecie);
    }

    @Test
    void testGetById_CustomExceptionThrown() {

        int id = 12;

        when(jdbcTemplate.queryForObject(eq(SELECTBYID), any(EspecieMapper.class), anyInt())).thenThrow(new RuntimeException());

        assertThrows(CustomException.class, () -> especieDAO.getById(id));
    }

    @Test
    void testGetById_EmptyResultDataAccessException() throws CustomException {

        int id = 12;
        when(jdbcTemplate.queryForObject(eq(SELECTBYID), any(EspecieMapper.class), anyInt())).thenThrow(EmptyResultDataAccessException.class);

        Especie especieNull = especieDAO.getById(id);

        assertNull(especieNull);
    }

    @Test
    void testGetAll() throws CustomException {

        List<Especie> expectedListEspecie = new ArrayList();
        Especie especie = new Especie();
        especie.setId(2);
        especie.setNombre("Gato");
        especie.setNombre_cientifico("Felis catus");
        expectedListEspecie.add(expectedEspecie);
        expectedListEspecie.add(especie);

        when(jdbcTemplate.query(eq(SELECT), any(EspecieMapper.class))).thenReturn(expectedListEspecie);

        List<Especie> actualListEspecie = especieDAO.getAll();

        assertEquals(expectedListEspecie, actualListEspecie);
    }

    @Test
    void testGetAll_CustomExceptionThrown() throws CustomException {

        when(jdbcTemplate.query(anyString(), any(EspecieMapper.class))).thenThrow(new RuntimeException());

        assertThrows(CustomException.class, () -> especieDAO.getAll());
    }

}
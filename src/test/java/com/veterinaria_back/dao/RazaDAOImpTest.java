package com.veterinaria_back.dao;

import com.veterinaria_back.dto.Raza;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.mapper.RazaMapper;
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

class RazaDAOImpTest {

    private static final String SELECT = "SELECT id, nombre, id_especie FROM raza";
    private static final String SELECTBYID = SELECT + " WHERE id = ?";
    private static final String SELECTBYESPECIE = SELECT + " WHERE id_especie = ?";

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private DataSource dataSource;

    @InjectMocks
    private RazaDAOImp razaDAO;

    private Raza expectedRaza1, expectedRaza2;
    private List<Raza> expectedListRaza;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        razaDAO.jdbcTemplate = jdbcTemplate;

        expectedRaza1 = new Raza();
        expectedRaza1.setId(1);
        expectedRaza1.setNombre("Criollo");
        expectedRaza1.setId_especie(1);

        expectedRaza2 = new Raza();
        expectedRaza2.setId(2);
        expectedRaza2.setNombre("Labrador Retriever");
        expectedRaza2.setId_especie(1);

        expectedListRaza = new ArrayList();
        expectedListRaza.add(expectedRaza1);
        expectedListRaza.add(expectedRaza2);
    }

    @Test
    void testGetById() throws CustomException {
        when(jdbcTemplate.queryForObject(eq(SELECTBYID), any(RazaMapper.class), any())).thenReturn(expectedRaza1);

        Raza actualRaza = razaDAO.getById(1);

        assertEquals(expectedRaza1, actualRaza);
    }

    @Test
    void testGetByIdReturnsNull() throws CustomException {
        int id = 99;

        when(jdbcTemplate.queryForObject(eq(SELECTBYID), any(RazaMapper.class), anyInt())).thenReturn(null);

        Raza actualRaza = razaDAO.getById(id);

        assertEquals(null, actualRaza);
    }

    @Test
    void testGetById_CustomExceptionThrown() {

        int id = 12;

        when(jdbcTemplate.queryForObject(eq(SELECTBYID), any(RazaMapper.class), anyInt())).thenThrow(new RuntimeException());

        assertThrows(CustomException.class, () -> razaDAO.getById(id));
    }

    @Test
    void testGetById_EmptyResultDataAccessException() throws CustomException {

        int id = 12;
        when(jdbcTemplate.queryForObject(eq(SELECTBYID), any(RazaMapper.class), anyInt())).thenThrow(EmptyResultDataAccessException.class);

        Raza razaNull = razaDAO.getById(id);

        assertNull(razaNull);
    }

    @Test
    void testGetByEspecie() throws CustomException {
        int id = 1;

        when(jdbcTemplate.query(eq(SELECTBYESPECIE), any(RazaMapper.class), any())).thenReturn(expectedListRaza);

        List<Raza> actualListRaza = razaDAO.getByEspecie(id);

        assertEquals(expectedListRaza, actualListRaza);
    }

    @Test
    void testGetByEspecie_CustomExceptionThrown() {

        int id = 5;

        when(jdbcTemplate.query(eq(SELECTBYESPECIE), any(RazaMapper.class), anyInt())).thenThrow(new RuntimeException());

        assertThrows(CustomException.class, () -> razaDAO.getByEspecie(id));
    }

    @Test
    void testGetByEspecie_EmptyResultDataAccessException() throws CustomException {

        int id = 5;
        when(jdbcTemplate.query(eq(SELECTBYESPECIE), any(RazaMapper.class), anyInt())).thenThrow(EmptyResultDataAccessException.class);

        List<Raza> listRazaNull = razaDAO.getByEspecie(id);

        assertNull(listRazaNull);
    }

    @Test
    void testGetAll() throws CustomException {

        when(jdbcTemplate.query(eq(SELECT), any(RazaMapper.class))).thenReturn(expectedListRaza);

        List<Raza> actualListRaza = razaDAO.getAll();

        assertEquals(expectedListRaza, actualListRaza);
    }

    @Test
    void testGetAll_CustomExceptionThrown() throws CustomException {

        when(jdbcTemplate.query(anyString(), any(RazaMapper.class))).thenThrow(new RuntimeException());

        assertThrows(CustomException.class, () -> razaDAO.getAll());
    }

}
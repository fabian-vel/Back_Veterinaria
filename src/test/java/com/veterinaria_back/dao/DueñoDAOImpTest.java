package com.veterinaria_back.dao;

import com.veterinaria_back.dto.Dueño;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.mapper.DueñoMapper;
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

class DueñoDAOImpTest {

    private static final String SELECT = "SELECT id, nombre, tipo_identificacion, identificacion, ciudad, direccion, telefono FROM dueño ORDER BY id DESC";
    private static final String SELCETBYID = "SELECT id, nombre, tipo_identificacion, identificacion, ciudad, direccion, telefono FROM dueño WHERE id = ?";
    private static final String INSERT = "INSERT INTO dueño(nombre, tipo_identificacion, identificacion, ciudad, direccion, telefono) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE dueño SET nombre=?, tipo_identificacion=?, identificacion=?, ciudad=?, direccion=?, telefono=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM dueño WHERE id = ?;";

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private DataSource dataSource;

    @InjectMocks
    private DueñoDAOImp dueñoDAO;

    private Dueño dueño;
    private Dueño expectedDueño;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dueñoDAO.jdbcTemplate = jdbcTemplate;

        expectedDueño = new Dueño();
        expectedDueño.setId(1);
        expectedDueño.setNombre("Manuel Palacios");
        expectedDueño.setTipo_identificacion("CC");
        expectedDueño.setIdentificacion("12365478900");
        expectedDueño.setCiudad("Monteria");
        expectedDueño.setDireccion("Cl 3 Cr. 5C");
        expectedDueño.setTelefono("3246789811");
    }

    @Test
    void testInsert() {
        when(jdbcTemplate.update(eq(INSERT), any(Dueño.class))).thenReturn(expectedDueño.getId());

        assertDoesNotThrow(() -> dueñoDAO.insert(new Dueño()));
    }

    @Test
    void testInser_CustomExceptionThrown() {
        when(jdbcTemplate.update(eq(INSERT), any(Dueño.class))).thenThrow(new RuntimeException());

        assertThrows(CustomException.class, () -> dueñoDAO.insert(null));
    }

    @Test
    void testUpdate() {
        when(jdbcTemplate.update(eq(UPDATE), any(Dueño.class))).thenReturn(expectedDueño.getId());

        assertDoesNotThrow(() -> dueñoDAO.update(new Dueño()));
    }

    @Test
    void testUpdate_CustomExceptionThrown() {
        when(jdbcTemplate.update(eq(UPDATE), any(Dueño.class))).thenThrow(new RuntimeException());

        assertThrows(CustomException.class, () -> dueñoDAO.update(null));
    }

    @Test
    void testDelete() {
        int id = 1;
        when(jdbcTemplate.update(eq(DELETE), anyInt())).thenReturn(expectedDueño.getId());

        assertDoesNotThrow(() -> dueñoDAO.delete(id));
    }

    @Test
    void testDelete_CustomExceptionThrown() {
        int id = 32;
        when(jdbcTemplate.update(eq(DELETE), anyInt())).thenThrow(new RuntimeException());

        assertThrows(CustomException.class, () -> dueñoDAO.delete(id));
    }

    @Test
    void testGetById() throws CustomException {
        int id = 1;
        when(jdbcTemplate.queryForObject(eq(SELCETBYID), any(DueñoMapper.class), anyInt())).thenReturn(expectedDueño);

        Dueño actualDueño = dueñoDAO.getById(id);

        assertEquals(expectedDueño, actualDueño);
    }

    @Test
    void testGetByIdCustomExceptionThrown() {
        int id = 544;

        when(jdbcTemplate.queryForObject(eq(SELCETBYID), any(DueñoMapper.class), anyInt())).thenThrow(new RuntimeException());

        assertThrows(CustomException.class, () -> dueñoDAO.getById(id));
    }

    @Test
    void testGetById_EmptyResultDataAccessException() throws CustomException {
        int id = 132;

        when(jdbcTemplate.queryForObject(eq(SELCETBYID), any(DueñoMapper.class), anyInt())).thenThrow(EmptyResultDataAccessException.class);

        Dueño dueñoNull = dueñoDAO.getById(id);

        assertNull(dueñoNull);
    }

    @Test
    void testGetAll() throws CustomException {
        List<Dueño> expectedListDueño = new ArrayList();
        dueño = new Dueño();
        dueño.setId(2);
        dueño.setNombre("Sol Martinez");
        dueño.setTipo_identificacion("CC");
        dueño.setIdentificacion("45678980");
        dueño.setCiudad("Monteria");
        dueño.setDireccion("Cr. 34 Tr. 4");
        dueño.setTelefono("3123457890");
        expectedListDueño.add(expectedDueño);
        expectedListDueño.add(dueño);

        when(jdbcTemplate.query(eq(SELECT), any(DueñoMapper.class))).thenReturn(expectedListDueño);

        List<Dueño> actualListDueño = dueñoDAO.getAll();

        assertEquals(expectedListDueño, actualListDueño);
    }

    @Test
    void testGetAll_CustomExceptionThrown() {

        when(jdbcTemplate.query(anyString(), any(DueñoMapper.class))).thenThrow(new RuntimeException());

        assertThrows(CustomException.class, () -> dueñoDAO.getAll());
    }
}
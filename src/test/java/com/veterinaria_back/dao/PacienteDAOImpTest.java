package com.veterinaria_back.dao;

import com.veterinaria_back.dto.Paciente;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.mapper.PacienteMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class PacienteDAOImpTest {

    private static final String SELECT = "SELECT P.id, P.nombre, P.id_especie, P.id_raza, P.id_dueño, P.fecha_nacimiento, P.fecha_registro,\n" +
            "\tD.nombre AS nombre_dueño, D.ciudad, D.tipo_identificacion, D.identificacion, D.direccion,\n" +
            "\tD.telefono, E.nombre AS nombre_especie, R.nombre AS nombre_raza\n" +
            "\tFROM paciente P INNER JOIN dueño D ON P.id_dueño = D.id INNER JOIN raza R ON R.id = P.id_raza\n" +
            "\tINNER JOIN especie E ON R.id_especie = E.id ORDER BY P.id DESC";
    private static final String SELECTBYID = "SELECT P.id, P.nombre, P.id_especie, P.id_raza, P.id_dueño, P.fecha_nacimiento, P.fecha_registro,\n" +
            "\tD.nombre AS nombre_dueño, D.ciudad, D.tipo_identificacion, D.identificacion, D.direccion,\n" +
            "\tD.telefono, E.nombre AS nombre_especie, R.nombre AS nombre_raza\n" +
            "\tFROM paciente P INNER JOIN dueño D ON P.id_dueño = D.id INNER JOIN raza R ON R.id = P.id_raza\n" +
            "\tINNER JOIN especie E ON R.id_especie = E.id WHERE P.id = ?";
    private static final String INSERT = "INSERT INTO paciente(id_especie, id_raza, id_dueño, nombre, fecha_nacimiento, fecha_registro) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE paciente SET id_especie=?, id_raza=?, id_dueño=?, nombre=?, fecha_nacimiento=?, fecha_registro=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM paciente WHERE id=?;";

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private DataSource dataSource;

    @InjectMocks
    private PacienteDAOImp pacienteDAO;

    private Paciente expectedPaciente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pacienteDAO.jdbcTemplate = jdbcTemplate;

        expectedPaciente = new Paciente();
        expectedPaciente.setId(1);
        expectedPaciente.setNombre("Sasha");
        expectedPaciente.setFecha_nacimiento(new Date(2021, 4, 12));
        expectedPaciente.setFecha_registro(new Date(2023, 4, 1));
        expectedPaciente.setId_dueño(1);
        expectedPaciente.setId_especie(1);
        expectedPaciente.setId_raza(1);
    }

    @Test
    void testInsert() {
        when(jdbcTemplate.update(eq(INSERT), any(Paciente.class))).thenReturn(expectedPaciente.getId());

        assertDoesNotThrow(()-> pacienteDAO.insert(new Paciente()));
    }

    @Test
    void testInser_CustomExceptionThrown() {
        when(jdbcTemplate.update(eq(INSERT), any(Paciente.class))).thenThrow(new RuntimeException());

        assertThrows(CustomException.class, () -> pacienteDAO.insert(null));
    }

    @Test
    void testUpdate() {
        when(jdbcTemplate.update(eq(UPDATE), any(Paciente.class))).thenReturn(expectedPaciente.getId());

        assertDoesNotThrow(() -> pacienteDAO.update(new Paciente()));
    }

    @Test
    void testUpdate_CustomExceptionThrown() {
        when(jdbcTemplate.update(eq(UPDATE), any(Paciente.class))).thenThrow(new RuntimeException());

        assertThrows(CustomException.class, () -> pacienteDAO.update(null));
    }

    @Test
    void testDelete() {
        int id = 1;
        when(jdbcTemplate.update(eq(DELETE), anyInt())).thenReturn(expectedPaciente.getId());

        assertDoesNotThrow(() -> pacienteDAO.delete(id));
    }

    @Test
    void testDelete_CustomExceptionThrown() {
        int id = 32;
        when(jdbcTemplate.update(eq(DELETE), anyInt())).thenThrow(new RuntimeException());

        assertThrows(CustomException.class, () -> pacienteDAO.delete(id));
    }

    @Test
    void testGetById() throws CustomException {
        int id = 1;
        when(jdbcTemplate.queryForObject(eq(SELECTBYID), any(PacienteMapper.class), anyInt())).thenReturn(expectedPaciente);

        Paciente actualPaciente = pacienteDAO.getById(id);

        assertEquals(expectedPaciente, actualPaciente);
    }

    @Test
    void testGetByIdCustomExceptionThrown() {
        int id = 321;

        when(jdbcTemplate.queryForObject(eq(SELECTBYID), any(PacienteMapper.class), anyInt())).thenThrow(new RuntimeException());

        assertThrows(CustomException.class, () -> pacienteDAO.getById(id));
    }

    @Test
    void testGetById_EmptyResultDataAccessException() throws CustomException {
        int id = 4;

        when(jdbcTemplate.queryForObject(eq(SELECTBYID), any(PacienteMapper.class), anyInt())).thenThrow(EmptyResultDataAccessException.class);

        Paciente pacienteNull = pacienteDAO.getById(id);

        assertNull(pacienteNull);
    }

    @Test
    void testGetAll() throws  CustomException{
        List<Paciente> expectedListPaciente = new ArrayList();
        Paciente paciente = new Paciente();
        paciente.setId(2);
        paciente.setNombre("Bruno");
        paciente.setFecha_nacimiento(new Date(2021, 4, 12));
        paciente.setFecha_registro(new Date(2023, 4, 1));
        paciente.setId_dueño(1);
        paciente.setId_especie(1);
        paciente.setId_raza(1);
        expectedListPaciente.add(expectedPaciente);
        expectedListPaciente.add(paciente);

        when(jdbcTemplate.query(eq(SELECT), any(PacienteMapper.class))).thenReturn(expectedListPaciente);

        List<Paciente> actualListPaciente = pacienteDAO.getAll();

        assertEquals(expectedListPaciente, actualListPaciente);
    }

    @Test
    void testGetAll_CustomExceptionThrown() {

        when(jdbcTemplate.query(anyString(), any(PacienteMapper.class))).thenThrow(new RuntimeException());

        assertThrows(CustomException.class, () -> pacienteDAO.getAll());
    }
}
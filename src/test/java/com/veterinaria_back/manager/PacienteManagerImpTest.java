package com.veterinaria_back.manager;

import com.veterinaria_back.dao.PacienteDAO;
import com.veterinaria_back.dto.Paciente;
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

class PacienteManagerImpTest {
    @Mock
    private PacienteDAO pacienteDAO;

    @InjectMocks
    private PacienteManagerImp pacienteManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertOrUpdatePaciente_Insert() throws CustomException {
        Paciente paciente = new Paciente();
        paciente.setId(1);

        when(pacienteDAO.getById(1)).thenReturn(null);

        assertDoesNotThrow(() -> pacienteManager.insertOrUpdatePaciente(paciente));
    }

    @Test
    void testInsertOrUpdatePaciente_Update() throws CustomException {
        Paciente paciente = new Paciente();
        paciente.setId(1);

        when(pacienteDAO.getById(1)).thenReturn(paciente);

        assertDoesNotThrow(() -> pacienteManager.insertOrUpdatePaciente(paciente));
    }

    @Test
    void testInsertOrUpdatePaciente_CustomExceptionThrown() throws CustomException {

        doThrow(CustomException.class).when(pacienteDAO).insert(new Paciente());

        assertThrows(CustomException.class, () -> pacienteManager.insertOrUpdatePaciente(new Paciente()));
    }

    @Test
    void testInsertOrUpdatePaciente_ExceptionThrown() throws CustomException {

        doThrow(RuntimeException.class).when(pacienteDAO).insert(new Paciente());

        assertThrows(Exception.class, () -> pacienteManager.insertOrUpdatePaciente(new Paciente()));
    }

    @Test
    void testDeletePaciente() {
        int id = 1;
        assertDoesNotThrow(() -> pacienteManager.deletePaciente(id));
    }

    @Test
    void testDeletePaciente_CustomExceptionThrown() throws CustomException {
        int id = 5;

        doThrow(CustomException.class).when(pacienteDAO).delete(id);

        assertThrows(CustomException.class, () -> pacienteManager.deletePaciente(id));
    }

    @Test
    void testDeletePaciente_ExceptionThrown() throws CustomException {
        int id = 5;

        doThrow(RuntimeException.class).when(pacienteDAO).delete(id);

        assertThrows(Exception.class, () -> pacienteManager.deletePaciente(id));
    }

    @Test
    void getAllPaciente() throws CustomException {
        List<Paciente> expectedListaPaciente = Arrays.asList(new Paciente(), new Paciente());
        when(pacienteDAO.getAll()).thenReturn(expectedListaPaciente);

        List<Paciente> actualListPaciente = pacienteManager.getAllPaciente();

        assertEquals(expectedListaPaciente, actualListPaciente);
    }

    @Test
    void testGetlAllPaciente_CustomExceptionThrown() throws CustomException {

        when(pacienteDAO.getAll()).thenThrow(new CustomException());

        assertThrows(CustomException.class, () -> pacienteManager.getAllPaciente());
    }

    @Test
    void testGetlAllPaciente_ExceptionThrown() throws CustomException {

        when(pacienteDAO.getAll()).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> pacienteManager.getAllPaciente());
    }
}
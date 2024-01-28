package com.veterinaria_back.service;

import com.veterinaria_back.dto.Paciente;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.manager.PacienteManager;
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

class PacienteServiceImpTest {

    @Mock
    private PacienteManager pacienteManager;

    @InjectMocks
    private PacienteServiceImp pacienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindSaveOrUpdatePaciente() {
        Paciente paciente = new Paciente();
        assertDoesNotThrow(() -> pacienteService.findSaveOrUpdatePaciente(paciente));
    }

    @Test
    void testFindInsertOrUpdatePaciente_ExceptionThrown() throws CustomException {

        doThrow(RuntimeException.class).when(pacienteManager).insertOrUpdatePaciente(new Paciente());

        assertThrows(Exception.class, () -> pacienteService.findSaveOrUpdatePaciente(new Paciente()));
    }

    @Test
    void testFindInsertOrUpdatePanciente_CustomExceptionThrown() throws CustomException {

        doThrow(CustomException.class).when(pacienteManager).insertOrUpdatePaciente(new Paciente());

        assertThrows(CustomException.class, () -> pacienteService.findSaveOrUpdatePaciente(new Paciente()));
    }

    @Test
    void testFindDeletePacinete() {
        int id = 1;
        assertDoesNotThrow(() -> pacienteService.findDeletePacinete(id));
    }

    @Test
    void testFindDeletePaciente_ExceptionThrown() throws CustomException {
        int id = 30;
        doThrow(RuntimeException.class).when(pacienteManager).deletePaciente(id);

        assertThrows(Exception.class, () -> pacienteService.findDeletePacinete(id));
    }

    @Test
    void testFindDeletePaciente_CustomExceptionThrown() throws CustomException {
        int id = 30;
        doThrow(CustomException.class).when(pacienteManager).deletePaciente(id);

        assertThrows(CustomException.class, () -> pacienteService.findDeletePacinete(id));
    }

    @Test
    void testFindGetAllPaciente() throws CustomException {
        List<Paciente> expectedListPaciente = Arrays.asList(new Paciente(), new Paciente());

        when(pacienteManager.getAllPaciente()).thenReturn(expectedListPaciente);

        List<Paciente> actualListPaciente = pacienteService.findGetAllPaciente();

        assertEquals(expectedListPaciente, actualListPaciente);
    }

    @Test
    void testFindGetAllPaciente_ExceptionThrown() throws CustomException {

        when(pacienteManager.getAllPaciente()).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> pacienteService.findGetAllPaciente());
    }

    @Test
    void testFindGetAllPaciente_CustomExceptionThrown() throws CustomException {

        when(pacienteManager.getAllPaciente()).thenThrow(new CustomException());

        assertThrows(CustomException.class, () -> pacienteService.findGetAllPaciente());
    }

}
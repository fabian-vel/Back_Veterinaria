package com.veterinaria_back.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.veterinaria_back.dto.Paciente;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.service.PacienteService;
import com.veterinaria_back.util.MessagesConstant;
import com.veterinaria_back.util.ResponseMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PacienteControllerTest {

    @Mock
    private PacienteService pacienteService;

    @InjectMocks
    private PacienteController pacienteController;

    private MockMvc mockMvc;
    private static final String URI1 = "/api/paciente/createpaciente";
    private static final String URI2 = "/api/paciente/deletepaciente/";
    private static final String URI3 = "/api/paciente/getpaciente";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pacienteController)
                .build();
    }

    @Test
    void testCreateOrUpdatePaciente() throws Exception {
        Paciente paciente = new Paciente();
        paciente.setId(1);
        paciente.setNombre("Bruno");
        paciente.setFecha_nacimiento(new Date(2021, 4, 21));
        paciente.setFecha_registro(new Date(2023, 5, 14));
        paciente.setId_dueño(1);
        paciente.setId_especie(1);
        paciente.setId_raza(1);

        String pacienteJson = mapToJson(paciente);

        MvcResult mockMvcRwsult = mockMvc.perform(MockMvcRequestBuilders.post(URI1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(pacienteJson)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mockMvcRwsult.getResponse().getStatus();

        assertEquals(200, status);
    }

    private String mapToJson(Paciente paciente) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String pacienteJson = objectMapper.writeValueAsString(paciente);
        return pacienteJson;
    }

    @Test
    void testCreateOrUpdatePaciente_throwCustomException() throws CustomException {
        String message = "Error, proceso fallido";

        doThrow(new CustomException(message)).when(pacienteService).findSaveOrUpdatePaciente(new Paciente());

        ResponseEntity<ResponseMessage<?>> response = pacienteController.createOrUpdatePaciente(new Paciente());

        verify(pacienteService).findSaveOrUpdatePaciente(new Paciente());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(MessagesConstant.ERROR_DEFAULT_CODE, response.getBody().getCode());
        assertEquals(String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, message), response.getBody().getMessage());
        assertNull(response.getBody().getData());
    }

    @Test
    void testDeletePaciente() throws Exception {
        String id = "1";
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(URI2+""+id)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mockMvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    void testDeleteDueño_throwRuntimeException() throws CustomException {
        int id = 1;
        String message = "Error, proceso fallido";

        doThrow(new CustomException(message)).when(pacienteService).findDeletePacinete(id);

        ResponseEntity<ResponseMessage<?>> response = pacienteController.deletePaciente(id);

        verify(pacienteService).findDeletePacinete(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(MessagesConstant.ERROR_DEFAULT_CODE, response.getBody().getCode());
        assertEquals(String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, message), response.getBody().getMessage());
        assertNull(response.getBody().getData());
    }

    @Test
    void testGetPaciente() throws Exception {
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URI3)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mockMvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    void testGetDueño_throwRuntimeException() throws CustomException {
        String message = "Error, proceso fallido";

        doThrow(new CustomException(message)).when(pacienteService).findGetAllPaciente();

        ResponseEntity<ResponseMessage<List<Paciente>>> response = pacienteController.getPaciente();

        verify(pacienteService).findGetAllPaciente();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(MessagesConstant.ERROR_DEFAULT_CODE, response.getBody().getCode());
        assertEquals(String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, message), response.getBody().getMessage());
        assertNull(response.getBody().getData());
    }

}
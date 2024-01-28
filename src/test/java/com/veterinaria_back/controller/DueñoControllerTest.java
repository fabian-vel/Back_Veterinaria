package com.veterinaria_back.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.veterinaria_back.dto.Dueño;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.service.DueñoService;
import com.veterinaria_back.util.MessagesConstant;
import com.veterinaria_back.util.ResponseMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DueñoControllerTest {

    @Mock
    private DueñoService dueñoService;

    @InjectMocks
    private DueñoController dueñoController;

    private MockMvc mockMvc;
    private static final String URI1 = "/api/dueño/getdueño";
    private static final String URI2 = "/api/dueño/createdueño";
    private static final String URI3 = "/api/dueño/deletedueño/";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(dueñoController)
                .build();
    }

    @Test
    void testGetDueño() throws Exception {
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URI1)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mockMvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    void testGetDueño_throwRuntimeException() throws CustomException {
        String message = "Error, proceso fallido";

        doThrow(new CustomException(message)).when(dueñoService).findGetAllDueño();

        ResponseEntity<ResponseMessage<List<Dueño>>> response = dueñoController.getDueño();

        verify(dueñoService).findGetAllDueño();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(MessagesConstant.ERROR_DEFAULT_CODE, response.getBody().getCode());
        assertEquals(String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, message), response.getBody().getMessage());
        assertNull(response.getBody().getData());
    }

    @Test
    void testCreateOrUpdateDueño() throws Exception {
        Dueño dueño = new Dueño();
        dueño.setId(2);
        dueño.setNombre("Sol Martinez");
        dueño.setTipo_identificacion("CC");
        dueño.setIdentificacion("45678980");
        dueño.setCiudad("Monteria");
        dueño.setDireccion("Cr. 34 Tr. 4");
        dueño.setTelefono("3123457890");

        String duenoJson = mapToJson(dueño);

        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.post(URI2)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(duenoJson)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mockMvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    private String mapToJson(Dueño dueño) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String dueñoJson = objectMapper.writeValueAsString(dueño);
        return dueñoJson;
    }

    @Test
    void testCreateOrUpdateDueño_throwCustomException() throws CustomException {
        String message = "Error, proceso fallido";

        doThrow(new CustomException(message)).when(dueñoService).findInsertOrUpdateDueño(new Dueño());

        ResponseEntity<ResponseMessage<?>> response = dueñoController.createOrUpdateDueño(new Dueño());

        verify(dueñoService).findInsertOrUpdateDueño(new Dueño());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(MessagesConstant.ERROR_DEFAULT_CODE, response.getBody().getCode());
        assertEquals(String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, message), response.getBody().getMessage());
        assertNull(response.getBody().getData());
    }

    @Test
    void testDeleteDueño() throws Exception {
        String id = "1";
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(URI3+""+id)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mockMvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    void testDeleteDueño_throwCustomException() throws Exception {
        int id = 1;
        String message = "Error, proceso fallido";

        doThrow(new CustomException(message)).when(dueñoService).findDeleteDueño(id);

        ResponseEntity<ResponseMessage<?>> response = dueñoController.deleteDueño(id);

        verify(dueñoService).findDeleteDueño(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(MessagesConstant.ERROR_DEFAULT_CODE, response.getBody().getCode());
        assertEquals(String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, message), response.getBody().getMessage());
        assertNull(response.getBody().getData());
    }


}
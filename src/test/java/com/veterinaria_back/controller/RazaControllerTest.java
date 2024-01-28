package com.veterinaria_back.controller;

import com.veterinaria_back.dto.Dueño;
import com.veterinaria_back.dto.Raza;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.service.RazaService;
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

class RazaControllerTest {

    @Mock
    private RazaService razaService;

    @InjectMocks
    private RazaController razaController;

    private MockMvc mockMvc;
    private static final String URI1 = "/api/raza/getRazas";
    private static final String URI2 = "/api/raza/getoneraza/";
    private static final String URI3 = "/api/raza/getrazaespecie/";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(razaController)
                .build();
    }

    @Test
    void testGetRaza() throws Exception {
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URI1)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mockMvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    void testGetRaza_throwCustomException() throws Exception {
        String message = "Error, proceso fallido";

        doThrow(new CustomException(message)).when(razaService).findGetAllRaza();

        ResponseEntity<ResponseMessage<List<Raza>>> response = razaController.getRaza();

        verify(razaService).findGetAllRaza();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(MessagesConstant.ERROR_DEFAULT_CODE, response.getBody().getCode());
        assertEquals(String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, message), response.getBody().getMessage());
        assertNull(response.getBody().getData());
    }

    @Test
    void testGetOneRaza() throws Exception {
        String id = "1";
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URI2+""+id)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mockMvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    void testGetRazaById_throwCustomException() throws Exception {
        int id = 11;
        String message = "Error, proceso fallido";

        doThrow(new CustomException(message)).when(razaService).findGetRazaById(id);

        ResponseEntity<ResponseMessage<Raza>> response = razaController.getOneRaza(id);

        verify(razaService).findGetRazaById(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(MessagesConstant.ERROR_DEFAULT_CODE, response.getBody().getCode());
        assertEquals(String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, message), response.getBody().getMessage());
        assertNull(response.getBody().getData());
    }

    @Test
    void testGetRazaByEspecie() throws Exception {
        String id = "1";
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URI3+""+id)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mockMvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    void testGetRazaByEspecie_throwCustomException() throws Exception {
        int id = 11;
        String message = "Error, proceso fallido";

        doThrow(new CustomException(message)).when(razaService).findGetRazaByEspecie(id);

        ResponseEntity<ResponseMessage<List<Raza>>> response = razaController.getRazaByEspecie(id);

        verify(razaService).findGetRazaByEspecie(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(MessagesConstant.ERROR_DEFAULT_CODE, response.getBody().getCode());
        assertEquals(String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, message), response.getBody().getMessage());
        assertNull(response.getBody().getData());
    }

}
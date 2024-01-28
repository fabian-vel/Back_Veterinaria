package com.veterinaria_back.controller;

import com.veterinaria_back.dto.Dueño;
import com.veterinaria_back.dto.Especie;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.service.EspecieService;
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

class EspecieControllerTest {

    @Mock
    private EspecieService especieService;

    @InjectMocks
    private EspecieController especieController;

    private MockMvc mockMvc;
    public static final String URI1 = "/api/especie/getespecies";
    public static final String URI2 = "/api/especie/getespecie/";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(especieController)
                .build();
    }

    @Test
    void testGetEspecie() throws Exception {
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URI1)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mockMvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    void testGetEspecie_throwCustomException() throws Exception {

        String message = "Error, proceso fallido";

        doThrow(new CustomException(message)).when(especieService).findGetAllEspecies();

        ResponseEntity<ResponseMessage<List<Especie>>> response = especieController.getEspecie();

        verify(especieService).findGetAllEspecies();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(MessagesConstant.ERROR_DEFAULT_CODE, response.getBody().getCode());
        assertEquals(String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, message), response.getBody().getMessage());
        assertNull(response.getBody().getData());
    }

    @Test
    void testGetEspecieById() throws Exception {
        String id = "1";
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URI2+""+id)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mockMvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    void testGetEspecieById_throwCustomException() throws Exception {
        int id = 154;

        String message = "Error, proceso fallido";

        doThrow(new CustomException(message)).when(especieService).findGetEspecieById(id);

        ResponseEntity<ResponseMessage<Especie>> response = especieController.getEspecieById(id);

        verify(especieService).findGetEspecieById(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(MessagesConstant.ERROR_DEFAULT_CODE, response.getBody().getCode());
        assertEquals(String.format(MessagesConstant.ERROR_CUSTOM_MESSAGE, message), response.getBody().getMessage());
        assertNull(response.getBody().getData());
    }

}
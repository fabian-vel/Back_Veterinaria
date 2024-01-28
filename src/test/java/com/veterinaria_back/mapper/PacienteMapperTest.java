package com.veterinaria_back.mapper;

import com.veterinaria_back.dto.Paciente;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PacienteMapperTest {

    @Test
    void mapRow() throws SQLException {
        PacienteMapper pacienteMapper = new PacienteMapper();
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("nombre")).thenReturn("Tony");
        when(resultSet.getDate("fecha_nacimiento")).thenReturn(new Date(2022, 12, 1));
        when(resultSet.getDate("fecha_registro")).thenReturn(new Date(2023, 4, 5));
        when(resultSet.getInt("id_dueño")).thenReturn(1);
        when(resultSet.getInt("id_especie")).thenReturn(1);
        when(resultSet.getInt("id_raza")).thenReturn(1);

        Paciente expectedPaciente = new Paciente();
        expectedPaciente.setId(1);
        expectedPaciente.setNombre("Tony");
        expectedPaciente.setFecha_nacimiento(new Date(2022, 12, 1));
        expectedPaciente.setFecha_registro(new Date(2023, 4, 5));
        expectedPaciente.setId_dueño(1);
        expectedPaciente.setId_especie(1);
        expectedPaciente.setId_raza(1);

        Paciente actualPaciente = pacienteMapper.mapRow(resultSet, 1);

        assertEquals(expectedPaciente, actualPaciente);
    }
}
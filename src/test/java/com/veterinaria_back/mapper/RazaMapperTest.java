package com.veterinaria_back.mapper;

import com.veterinaria_back.dto.Raza;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RazaMapperTest {

    @Test
    void mapRow() throws SQLException {
        RazaMapper razaMapper = new RazaMapper();
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("nombre")).thenReturn("Criollo");
        when(resultSet.getInt("id_especie")).thenReturn(1);

        Raza expectedRaza = new Raza();
        expectedRaza.setId(1);
        expectedRaza.setNombre("Criollo");
        expectedRaza.setId_especie(1);

        Raza actualRaza = razaMapper.mapRow(resultSet, 1);

        assertEquals(expectedRaza, actualRaza);
    }
}
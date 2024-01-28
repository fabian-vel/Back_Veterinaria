package com.veterinaria_back.mapper;

import com.veterinaria_back.dto.Especie;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EspecieMapperTest {

    @Test
    void mapRow() throws SQLException {

        EspecieMapper especieMapper = new EspecieMapper();
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("nombre")).thenReturn("Perro");
        when(resultSet.getString("nombre_cientifico")).thenReturn("Canis lupus familiaris");

        Especie expectedEspecie = new Especie();
        expectedEspecie.setId(1);
        expectedEspecie.setNombre("Perro");
        expectedEspecie.setNombre_cientifico("Canis lupus familiaris");

        Especie actualEspecie = especieMapper.mapRow(resultSet, 1);

        assertEquals(expectedEspecie, actualEspecie);
    }
}
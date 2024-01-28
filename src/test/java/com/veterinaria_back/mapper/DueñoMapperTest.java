package com.veterinaria_back.mapper;

import com.veterinaria_back.dto.Dueño;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DueñoMapperTest {

    @Test
    void mapRow() throws SQLException {

        DueñoMapper dueñoMapper = new DueñoMapper();
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("nombre")).thenReturn("Paula Salcedo");
        when(resultSet.getString("tipo_identificacion")).thenReturn("CC");
        when(resultSet.getString("identificacion")).thenReturn("10664539800");
        when(resultSet.getString("ciudad")).thenReturn("Cartagena");
        when(resultSet.getString("direccion")).thenReturn("Tr. 7 Cl. 12 #2A");
        when(resultSet.getString("telefono")).thenReturn("3145678900");

        Dueño expectedDueño = new Dueño();
        expectedDueño.setId(1);
        expectedDueño.setNombre("Paula Salcedo");
        expectedDueño.setTipo_identificacion("CC");
        expectedDueño.setIdentificacion("10664539800");
        expectedDueño.setCiudad("Cartagena");
        expectedDueño.setDireccion("Tr. 7 Cl. 12 #2A");
        expectedDueño.setTelefono("3145678900");

        Dueño actualDueño = dueñoMapper.mapRow(resultSet, 1);

        assertEquals(expectedDueño, actualDueño);
    }
}
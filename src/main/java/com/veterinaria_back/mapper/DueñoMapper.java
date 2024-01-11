package com.veterinaria_back.mapper;

import com.veterinaria_back.dto.Dueño;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DueñoMapper implements RowMapper<Dueño> {
    @Override
    public Dueño mapRow(ResultSet rs, int rowNum) throws SQLException {
        Dueño dueño = new Dueño();
        dueño.setId(rs.getInt("id"));
        dueño.setNombre(rs.getString("nombre"));
        dueño.setTipo_identificacion(rs.getString("tipo_identificacion"));
        dueño.setIdentificacion(rs.getString("identificacion"));
        dueño.setCiudad(rs.getString("ciudad"));
        dueño.setDireccion(rs.getString("direccion"));
        dueño.setTelefono(rs.getString("telefono"));
        return dueño;
    }
}

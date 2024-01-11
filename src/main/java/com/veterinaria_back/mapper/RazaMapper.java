package com.veterinaria_back.mapper;

import com.veterinaria_back.dto.Raza;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RazaMapper implements RowMapper<Raza> {
    @Override
    public Raza mapRow(ResultSet rs, int rowNum) throws SQLException {
        Raza raza = new Raza();
        raza.setId(rs.getInt("id"));
        raza.setNombre(rs.getString("nombre"));
        raza.setId_especie(rs.getInt("id_especie"));
        return raza;
    }
}

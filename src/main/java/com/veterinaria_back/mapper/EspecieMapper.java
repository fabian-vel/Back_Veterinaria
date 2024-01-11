package com.veterinaria_back.mapper;

import com.veterinaria_back.dto.Especie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EspecieMapper implements RowMapper<Especie> {
    @Override
    public Especie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Especie especie = new Especie();
        especie.setId(rs.getInt("id"));
        especie.setNombre(rs.getString("nombre"));
        especie.setNombre_cientifico(rs.getString("nombre_cientifico"));
        return especie;
    }
}

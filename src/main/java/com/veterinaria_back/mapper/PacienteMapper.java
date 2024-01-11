package com.veterinaria_back.mapper;

import com.veterinaria_back.dto.Paciente;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PacienteMapper implements RowMapper<Paciente> {
    @Override
    public Paciente mapRow(ResultSet rs, int rowNum) throws SQLException {
        Paciente paciente = new Paciente();
        paciente.setId(rs.getInt("id"));
        paciente.setNombre(rs.getString("nombre"));
        paciente.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
        paciente.setFecha_registro(rs.getDate("fecha_registro"));
        paciente.setId_dueño(rs.getInt("id_dueño"));
        paciente.setNombre_duenio(rs.getString("nombre_dueño"));
        paciente.setCiudad(rs.getString("ciudad"));
        paciente.setTipo_identificacion(rs.getString("tipo_identificacion"));
        paciente.setIdentificacion(rs.getString("identificacion"));
        paciente.setDireccion(rs.getString("direccion"));
        paciente.setTelefono(rs.getString("telefono"));
        paciente.setNombre_especie(rs.getString("nombre_especie"));
        paciente.setId_especie(rs.getInt("id_especie"));
        paciente.setNombre_raza(rs.getString("nombre_raza"));
        paciente.setId_raza(rs.getInt("id_raza"));
        return paciente;
    }
}

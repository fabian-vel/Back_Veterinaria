package com.veterinaria_back.dao;

import com.veterinaria_back.dto.Paciente;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.mapper.PacienteMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PacienteDAOImp implements PacienteDAO {

    private static final String SELECT = "SELECT P.id, P.nombre, P.id_especie, P.id_raza, P.id_dueño, P.fecha_nacimiento, P.fecha_registro,\n" +
            "\tD.nombre AS nombre_dueño, D.ciudad, D.tipo_identificacion, D.identificacion, D.direccion,\n" +
            "\tD.telefono, E.nombre AS nombre_especie, R.nombre AS nombre_raza\n" +
            "\tFROM paciente P INNER JOIN dueño D ON P.id_dueño = D.id INNER JOIN raza R ON R.id = P.id_raza\n" +
            "\tINNER JOIN especie E ON R.id_especie = E.id ORDER BY P.id DESC";
    private static final String SELECTBYID = "SELECT P.id, P.nombre, P.id_especie, P.id_raza, P.id_dueño, P.fecha_nacimiento, P.fecha_registro,\n" +
            "\tD.nombre AS nombre_dueño, D.ciudad, D.tipo_identificacion, D.identificacion, D.direccion,\n" +
            "\tD.telefono, E.nombre AS nombre_especie, R.nombre AS nombre_raza\n" +
            "\tFROM paciente P INNER JOIN dueño D ON P.id_dueño = D.id INNER JOIN raza R ON R.id = P.id_raza\n" +
            "\tINNER JOIN especie E ON R.id_especie = E.id WHERE P.id = ?";
    private static final String INSERT = "INSERT INTO paciente(id_especie, id_raza, id_dueño, nombre, fecha_nacimiento, fecha_registro) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE paciente SET id_especie=?, id_raza=?, id_dueño=?, nombre=?, fecha_nacimiento=?, fecha_registro=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM paciente WHERE id=?;";

    JdbcTemplate jdbcTemplate;

    public PacienteDAOImp(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void insert(Paciente paciente) throws CustomException {
        try {
            jdbcTemplate.update(INSERT,
                    paciente.getId_especie(),
                    paciente.getId_raza(),
                    paciente.getId_dueño(),
                    paciente.getNombre(),
                    paciente.getFecha_nacimiento(),
                    paciente.getFecha_registro()
            );
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Override
    public void update(Paciente paciente) throws CustomException {
        try {
            jdbcTemplate.update(UPDATE,
                    paciente.getId_especie(),
                    paciente.getId_raza(),
                    paciente.getId_dueño(),
                    paciente.getNombre(),
                    paciente.getFecha_nacimiento(),
                    paciente.getFecha_registro(),
                    paciente.getId()
            );
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Override
    public void delete(int id) throws CustomException {
        try {
            jdbcTemplate.update(DELETE, id);
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Override
    public Paciente getById(int id) throws CustomException {
        Paciente paciente = null;
        try {
            paciente = jdbcTemplate.queryForObject(SELECTBYID, new PacienteMapper(), id);
        } catch (EmptyResultDataAccessException e) {

        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return paciente;
    }

    @Override
    public List<Paciente> getAll() throws CustomException {
        List<Paciente> listPacientes;
        try {
            listPacientes = jdbcTemplate.query(SELECT, new PacienteMapper());
        } catch (Exception e) {
            throw new CustomException(e);
        }
        return listPacientes;
    }
}

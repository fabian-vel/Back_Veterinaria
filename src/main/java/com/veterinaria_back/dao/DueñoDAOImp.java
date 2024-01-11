package com.veterinaria_back.dao;

import com.veterinaria_back.dto.Dueño;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.mapper.DueñoMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class DueñoDAOImp implements DueñoDAO{

    private static final String SELECT = "SELECT id, nombre, tipo_identificacion, identificacion, ciudad, direccion, telefono FROM dueño ORDER BY id DESC";
    private static final String SELCETBYID = "SELECT id, nombre, tipo_identificacion, identificacion, ciudad, direccion, telefono FROM dueño WHERE id = ?";
    private static final String INSERT = "INSERT INTO dueño(nombre, tipo_identificacion, identificacion, ciudad, direccion, telefono) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE dueño SET nombre=?, tipo_identificacion=?, identificacion=?, ciudad=?, direccion=?, telefono=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM dueño WHERE id = ?;";

    JdbcTemplate jdbcTemplate;

    public DueñoDAOImp(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void insert(Dueño dueño) throws CustomException {
        try {
            jdbcTemplate.update(INSERT,
                    dueño.getNombre(),
                    dueño.getTipo_identificacion(),
                    dueño.getIdentificacion(),
                    dueño.getCiudad(),
                    dueño.getDireccion(),
                    dueño.getTelefono()
            );
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Override
    public void update(Dueño dueño) throws CustomException {
        try {
            jdbcTemplate.update(UPDATE,
                    dueño.getNombre(),
                    dueño.getTipo_identificacion(),
                    dueño.getIdentificacion(),
                    dueño.getCiudad(),
                    dueño.getDireccion(),
                    dueño.getTelefono(),
                    dueño.getId()
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
    public Dueño getById(int id) throws CustomException {
        Dueño dueño = null;
        try {
            dueño = jdbcTemplate.queryForObject(SELCETBYID, new DueñoMapper(), id);
        }catch (EmptyResultDataAccessException e) {

        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return dueño;
    }

    @Override
    public List<Dueño> getAll() throws CustomException {
        List<Dueño> listDueños;
        try {
            listDueños = jdbcTemplate.query(SELECT, new DueñoMapper());
        }catch (Exception e){
            throw new CustomException(e);
        }
        return listDueños;
    }
}

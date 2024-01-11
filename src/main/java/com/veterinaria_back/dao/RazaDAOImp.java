package com.veterinaria_back.dao;

import com.veterinaria_back.dto.Raza;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.mapper.RazaMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RazaDAOImp implements RazaDAO{

    private static final String SELECT = "SELECT id, nombre, id_especie FROM raza";
    private static final String SELECTBYID = SELECT + " WHERE id = ?";
    private static final String SELECTBYESPECIE = SELECT + " WHERE id_especie = ?";

    JdbcTemplate jdbcTemplate;

    public RazaDAOImp( DataSource dataSource) {jdbcTemplate = new  JdbcTemplate(dataSource);}

    @Override
    public Raza getById(int id) throws CustomException {
        Raza raza = null;
        try {
            raza = jdbcTemplate.queryForObject(SELECTBYID, new RazaMapper(), id);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return raza;
    }

    @Override
    public List<Raza> getByEspecie(int id) throws CustomException {
        List<Raza> razas = null;
        try {
            razas = jdbcTemplate.query(SELECTBYESPECIE, new RazaMapper(), id);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return razas;
    }

    @Override
    public List<Raza> getAll() throws CustomException {
        List<Raza> listRazas;
        try {
            listRazas = jdbcTemplate.query(SELECT, new RazaMapper());
        }catch (Exception e){
            throw new CustomException(e);
        }
        return listRazas;
    }
}

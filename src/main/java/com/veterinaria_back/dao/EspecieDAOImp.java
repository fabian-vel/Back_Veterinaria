package com.veterinaria_back.dao;

import com.veterinaria_back.dto.Especie;
import com.veterinaria_back.exception.CustomException;
import com.veterinaria_back.mapper.EspecieMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class EspecieDAOImp implements EspecieDAO{

    private static final String SELECT = "SELECT id, nombre, nombre_cientifico FROM especie";
    private static final String SELECTBYID = SELECT + " WHERE id = ?";

    JdbcTemplate jdbcTemplate;

    public EspecieDAOImp( DataSource dataSource) {jdbcTemplate = new  JdbcTemplate(dataSource);}

    @Override
    public Especie getById(int id) throws CustomException {
        Especie especie = null;
        try {
            especie = jdbcTemplate.queryForObject(SELECTBYID, new EspecieMapper(), id);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }catch (Exception ex){
            throw new CustomException(ex);
        }
        return especie;
    }

    @Override
    public List<Especie> getAll() throws CustomException {
        List<Especie> listEspecies;
        try {
            listEspecies = jdbcTemplate.query(SELECT, new EspecieMapper());
        }catch (Exception e){
            throw new CustomException(e);
        }
        return listEspecies;
    }
}

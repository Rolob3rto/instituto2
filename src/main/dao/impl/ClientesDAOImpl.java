package com.springprojects.roberto.instituto.instituto.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.springprojects.roberto.instituto.instituto.dao.AlumnosDAO;
import com.springprojects.roberto.instituto.instituto.model.Alumno;

@Repository
public class AlumnosDAOImpl extends JdbcDaoSupport implements AlumnosDAO {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        
    }

    @Override
    public Page<Alumno> findAll(Pageable page) {
    
    String queryCount = "select count(1) from Alumnos";
    Integer total = getJdbcTemplate().queryForObject(queryCount,Integer.class);
    
    
    Order order = !page.getSort().isEmpty() ? page.getSort().toList().get(0) : Order.by("codigo");
    
    String query = "SELECT * FROM Alumnos ORDER BY " + order.getProperty() + " "
    + order.getDirection().name() + " LIMIT " + page.getPageSize() + " OFFSET " + page.getOffset();
    
    final List<Alumno> alumnos = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Alumno.class));
    
    
    return new PageImpl<Alumno>(alumnos, page, total);
    
    }

    @Override
    public Alumno findAlumno(int codigo) {

        String query = "select * from Alumnos where codigo = ?";
        Object params[] = { codigo };
        int types[] = { Types.INTEGER };

    
        Alumno alumno = (Alumno) getJdbcTemplate().queryForObject(query, params, types,
                new BeanPropertyRowMapper(Alumno.class));

        return alumno;
    }


    @Override
    public void insert(Alumno alumno) {

        String query = "insert into Alumnos (nombre," +
                " apellidos," +
                " dni," +
                " fechaNacimiento," +
                " email," +
                " nuevo)" +
                " values (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        getJdbcTemplate().update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, alumno.getNombre());
                ps.setString(2, alumno.getApellidos());
                ps.setString(3, alumno.getDni());
                ps.setString(4, alumno.getFechaNacimiento());
                ps.setString(5, alumno.getEmail());
                ps.setBoolean(6, alumno.isNuevo());

                return ps;
            }
        }, keyHolder);

        alumno.setCodigo(keyHolder.getKey().intValue());
    }

    @Override
    public void update(Alumno alumno) {

        String query = "update Alumnos set nombre = ?, apellidos = ?, dni = ?, fechaNacimiento = ?, email = ?, nuevo = ? where codigo = ?";

        Object[] params = {
                alumno.getNombre(),
                alumno.getApellidos(),
                alumno.getDni(),
                alumno.getFechaNacimiento(),
                alumno.getEmail(),
                alumno.isNuevo(),
                alumno.getCodigo()

        };

        int[] types = {
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.DATE,
                Types.VARCHAR,
                Types.BOOLEAN,
                Types.INTEGER

        };

        int update = getJdbcTemplate().update(query, params, types);
    }

    @Override
    public void delete(int codigo) {

        String query = "delete from Alumnos where codigo = ?";

        Object[] params = {
                codigo
        };

        int[] types = {
                Types.INTEGER
        };

        int delete = getJdbcTemplate().update(query, params, types);

    }


}

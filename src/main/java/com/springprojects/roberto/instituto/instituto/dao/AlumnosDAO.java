package com.springprojects.roberto.instituto.instituto.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springprojects.roberto.instituto.instituto.model.Alumno;

public interface AlumnosDAO {

    public Page<Alumno> findAll(Pageable page);

    public Alumno findAlumno(int codigo);

    public void insert(Alumno alumno);

    public void update(Alumno alumno);

    public void delete(int codigo);

    public void updateImg(Alumno alumno);
}

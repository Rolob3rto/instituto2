package com.springprojects.roberto.instituto.instituto.services.impl;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springprojects.roberto.instituto.instituto.dao.AlumnosDAO;
import com.springprojects.roberto.instituto.instituto.model.Alumno;
import com.springprojects.roberto.instituto.instituto.services.AlumnosServices;

@Service
public class AlumnosServicesImpl implements AlumnosServices {

    @Autowired
    AlumnosDAO alumnosDAO;

    @Override
    public Page<Alumno> findAll(Pageable page) {        
        
        return alumnosDAO.findAll(page);
    }

    @Override
    public Alumno findAlumno(int codigo) {        
        
        return alumnosDAO.findAlumno(codigo);
    }

    @Override
    public void insert(Alumno alumno) {
        alumnosDAO.insert(alumno);
    }

    @Override
    public void update(Alumno alumno) {
        alumnosDAO.update(alumno);
    }

    @Override
    public void delete(int codigo) {
        alumnosDAO.delete(codigo);
    }

}

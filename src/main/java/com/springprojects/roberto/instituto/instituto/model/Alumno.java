package com.springprojects.roberto.instituto.instituto.model;

import java.sql.Date;

import com.springprojects.roberto.instituto.instituto.Utils.ImageUtil;

public class Alumno {
    private int codigo;
    private String nombre;
    private String apellidos;
    private String email;
    private String dni;
    private Date fechaNacimiento;
    private boolean nuevo;
    private byte[] img;

    
    public Alumno(int codigo, String nombre, String apellidos, String email, String dni, Date fechaNacimiento,
             boolean nuevo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.nuevo = nuevo;
    }

    public Alumno() {}

    public Alumno(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }


    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }
    
        public Date getFechaNacimiento() {
            return fechaNacimiento;
        }
    
        public void setFechaNacimiento(Date fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }

        public byte[] getImg() {
            return img;
        }
    
        public void setImg(byte[] fileName) {
            this.img = fileName;
        }
    
        public String getImageView(){
            return ImageUtil.getImgData(this.img);
        }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigo;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Alumno other = (Alumno) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }
    
    
}



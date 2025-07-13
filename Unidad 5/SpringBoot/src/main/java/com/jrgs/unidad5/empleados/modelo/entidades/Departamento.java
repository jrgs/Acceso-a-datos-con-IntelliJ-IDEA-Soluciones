package com.jrgs.unidad5.empleados.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departamentos")
public class Departamento {
    private int depno;
    private String nombre;
    private String ubicacion;
    //private List<Empleado> empleados;

    public Departamento() {

    }

    public Departamento(int depno, String nombre, String ubicacion) {
        this.depno = depno;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    @Id
    @Column(name = "depno", nullable = false)
    public int getDepno() {
        return depno;
    }

    public void setDepno(int depno) {
        this.depno = depno;
    }

    @Basic
    @Column(name = "nombre", nullable = true, length = 14)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "ubicacion", nullable = true, length = 13)
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Departamento that = (Departamento) o;

        if (depno != that.depno) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (ubicacion != null ? !ubicacion.equals(that.ubicacion) : that.ubicacion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = depno;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (ubicacion != null ? ubicacion.hashCode() : 0);
        return result;
    }
/*
    @OneToMany(mappedBy = "departamento")
    @JsonIgnoreProperties("departamento")
    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<EntidadEmpleados> empleados) {
        this.empleados = empleados;
    }*/
}

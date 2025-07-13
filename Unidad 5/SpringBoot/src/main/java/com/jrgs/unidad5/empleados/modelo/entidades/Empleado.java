package com.jrgs.unidad5.empleados.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "empleados", schema = "public", catalog = "Empleados")
public class Empleado {
    private int empno;
    private String nombre;
    private String puesto;
    private int depno;
    //private Departamento departamento;

    // Needed by Hibernate
    //
    public Empleado() {

    }

    // Needed for tests
    //
    public Empleado(int empno, String nombre, String puesto, int depno) {
        this.empno = empno;
        this.nombre = nombre;
        this.puesto = puesto;
        this.depno = depno;
    }

    @Id
    @Column(name = "empno", nullable = false)
    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    @Basic
    @NotEmpty(message="El nombre no puede estar vacío")
    @Size(min = 2, max = 10, message = "El nombre tiene que tener entre 2 y 10 caracteres")
    @Column(name = "nombre", nullable = true, length = 10)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "puesto", nullable = true, length = 15)
    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Empleado that = (Empleado) o;

        if (empno != that.empno) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (puesto != null ? !puesto.equals(that.puesto) : that.puesto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = empno;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (puesto != null ? puesto.hashCode() : 0);
        return result;
    }
/*
    @ManyToOne
    @JoinColumn(name = "depno", referencedColumnName = "depno", updatable = false, insertable = false)
    @JsonIgnoreProperties("empleados")
    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(EntidadDepartamentos departamento) {
        this.departamento = departamento;
    }
*/
    public int getDepno() {
        return depno;
    }

    public void setDepno(int depno) {
        this.depno = depno;
    }
}

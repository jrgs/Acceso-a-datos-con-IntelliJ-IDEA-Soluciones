package com.jrgs.unidad5.empleados.modelo.dao;

import com.jrgs.unidad5.empleados.modelo.entidades.Empleado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmpleadosDAO extends CrudRepository<Empleado, Integer> {
    List<Empleado> findByNombreStartsWith(String prefix);

    List<Empleado> findByPuestoContains(String string);
}

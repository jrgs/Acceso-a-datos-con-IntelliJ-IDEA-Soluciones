package com.jrgs.unidad5.empleados.modelo.dao;

import com.jrgs.unidad5.empleados.modelo.entidades.EntidadDepartamentos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDepartamentosDAO extends CrudRepository<EntidadDepartamentos, Integer> {
    List<EntidadDepartamentos> findByDepnoGreaterThan(int depno);

    List<EntidadDepartamentos> findByUbicacionIsIgnoreCase(String ubicacion);

    List<EntidadDepartamentos> findByUbicacionEqualsOrUbicacionEquals(String firstPlace, String secondPlace);
}


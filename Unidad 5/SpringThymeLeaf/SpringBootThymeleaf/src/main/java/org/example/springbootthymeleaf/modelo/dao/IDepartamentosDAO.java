package org.example.springbootthymeleaf.modelo.dao;

import org.example.springbootthymeleaf.modelo.entidades.Departamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDepartamentosDAO extends CrudRepository<Departamento, Integer> {
    List<Departamento> findByDepnoGreaterThan(int depno);

    List<Departamento> findByUbicacionIsIgnoreCase(String ubicacion);

    List<Departamento> findByUbicacionEqualsOrUbicacionEquals(String firstPlace, String secondPlace);
}


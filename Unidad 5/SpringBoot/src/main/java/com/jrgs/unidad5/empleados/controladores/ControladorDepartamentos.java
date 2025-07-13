package com.jrgs.unidad5.empleados.controladores;

import com.jrgs.unidad5.empleados.modelo.dao.IDepartamentosDAO;
import com.jrgs.unidad5.empleados.modelo.entidades.EntidadDepartamentos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class ControladorDepartamentos {
    @Autowired
    IDepartamentosDAO departamentosDAO;

    @GetMapping
    public List<EntidadDepartamentos> buscarDepartamentos() {
        return (List<EntidadDepartamentos>) departamentosDAO.findAll();
    }

    @GetMapping("/prueba/{value}")
    public List<EntidadDepartamentos> buscarDepartamentoMayorQue(@PathVariable(value = "value") String value) {
        return (List<EntidadDepartamentos>) departamentosDAO.findByUbicacionIsIgnoreCase(value);
    }

    @GetMapping("/prueba/{value1}/{value2}")
    public List<EntidadDepartamentos> buscarDepartamentoUbicacion(@PathVariable(value = "value1") String value1, @PathVariable(value = "value2") String value2) {
        return (List<EntidadDepartamentos>) departamentosDAO.findByUbicacionEqualsOrUbicacionEquals(value1, value2);
    }


}

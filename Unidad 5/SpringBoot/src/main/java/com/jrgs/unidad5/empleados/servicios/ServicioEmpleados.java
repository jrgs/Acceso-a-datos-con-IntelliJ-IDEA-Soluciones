package com.jrgs.unidad5.empleados.servicios;

import com.jrgs.unidad5.empleados.modelo.dao.IEmpleadosDAO;
import com.jrgs.unidad5.empleados.modelo.entidades.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioEmpleados {
    @Autowired
    private IEmpleadosDAO empleadosDAO;

    public List<Empleado> buscarEmpleados() {
        return (List<Empleado>) empleadosDAO.findAll();
    }

    public Optional<Empleado> buscarEmpleadoPorCodigo(int id) {
        return empleadosDAO.findById(id);
    }

    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadosDAO.save(empleado);
    }

    public boolean actualizarEmpleado(int id, Empleado nuevoEmpleado) {
        Optional<Empleado> empleado = buscarEmpleadoPorCodigo(id);
        if(empleado.isPresent()) {
            empleado.get().setNombre(nuevoEmpleado.getNombre());
            empleado.get().setPuesto(nuevoEmpleado.getPuesto());
            empleado.get().setDepno(nuevoEmpleado.getDepno());
            empleadosDAO.save(empleado.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean borrarEmpleado(int id) {
        Optional<Empleado> empleado = buscarEmpleadoPorCodigo(id);
        if(empleado.isPresent()) {
            empleadosDAO.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Empleado> buscarEmpleadosPorPuesto(String puesto) {
        return empleadosDAO.findByPuestoContains(puesto);
    }
}

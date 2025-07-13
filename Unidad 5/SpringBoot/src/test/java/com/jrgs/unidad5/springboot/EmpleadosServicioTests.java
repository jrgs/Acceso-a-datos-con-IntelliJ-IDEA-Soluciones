package com.jrgs.unidad5.springboot;

import com.jrgs.unidad5.empleados.Application;
import com.jrgs.unidad5.empleados.modelo.dao.IEmpleadosDAO;
import com.jrgs.unidad5.empleados.modelo.entidades.Empleado;
import com.jrgs.unidad5.empleados.servicios.ServicioEmpleados;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
class EmpleadosServicioTests {

    @Mock
    private IEmpleadosDAO empleadosDAO;

    @InjectMocks
    private ServicioEmpleados servicioEmpleados;

    @Test
    void buscarEmpleados() {
        // Preparación
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(1000, "Nombre1", "Puesto1", 10));
        empleados.add(new Empleado(1001, "Nombre2", "Puesto2", 20));

        when(empleadosDAO.findAll()).thenReturn(empleados);

        // Ejecución
        List<Empleado> result = servicioEmpleados.buscarEmpleados();

        // Comprobación
        assertEquals(2, result.size());
    }

    @Test
    void buscarEmpleadoPorIdExistente() {
        // Preparación
        int id = 1000;
        Empleado empleado = new Empleado(id, "Nombre1", "Puesto1", 10);

        when(empleadosDAO.findById(id)).thenReturn(Optional.of(empleado));

        // Ejecución
        Optional<Empleado> result = servicioEmpleados.buscarEmpleadoPorCodigo(id);

        // Comprobación
        assertEquals(empleado, result.get());
    }

    @Test
    void buscarEmpleadoPorIdNoExistente() {
        // Preparación
        int id = 1099;

        when(empleadosDAO.findById(id)).thenReturn(Optional.empty());

        // Ejecución
        Optional<Empleado> result = servicioEmpleados.buscarEmpleadoPorCodigo(id);

        // Comprobación
        assertEquals(result, Optional.empty());
    }

    @Test
    void guardarEmpleado() {
        // Preparación
        Empleado empleado = new Empleado(1000, "Nombre1", "Puesto1", 10);

        when(empleadosDAO.save(any(Empleado.class))).thenReturn(empleado);

        // Ejecución
        Empleado result = servicioEmpleados.guardarEmpleado(empleado);

        // Comprobación
        assertEquals(empleado, result);
    }

    @Test
    void actualizarEmpleadoExistente() {
        // Preparación
        int id = 1000;
        Empleado nuevoEmpleado = new Empleado(id, "NuevoNombre", "NuevoPuesto", 10);
        Empleado empleadoExistente = new Empleado(id, "NombreAntiguo", "PuestoAntiguo", 20);

        when(empleadosDAO.findById(id)).thenReturn(Optional.of(empleadoExistente));
        when(empleadosDAO.save(any(Empleado.class))).thenReturn(empleadoExistente);

        // Ejecución
       boolean result = servicioEmpleados.actualizarEmpleado(id, nuevoEmpleado);

        // Comprobación
        assertEquals(result, true);
        assertEquals("NuevoNombre", empleadoExistente.getNombre()); // Verificar que el nombre se actualizó correctamente
    }

    @Test
    void actualizarEmpleadoNoExistente() {
        // Preparación
        int id = 1099;
        Empleado nuevoEmpleado = new Empleado(1000, "NuevoNombre", "NuevoPuesto", 10);

        when(empleadosDAO.findById(id)).thenReturn(Optional.empty());

        // Ejecución
        boolean result = servicioEmpleados.actualizarEmpleado(id, nuevoEmpleado);

        // Comprobación
        assertEquals(false, result);
    }

    @Test
    void borrarEmpleadoExistente() {
        // Preparación
        int id = 1000;
        Empleado empleadoExistente = new Empleado(id,"NombreAntiguo", "PuestoAntiguo", 10);

        when(empleadosDAO.findById(id)).thenReturn(Optional.of(empleadoExistente));

        // Ejecución
        boolean result = servicioEmpleados.borrarEmpleado(id);

        // Comprobación
        assertEquals(true, result);
        verify(empleadosDAO, times(1)).deleteById(id); // Verificar que se llamó al método deleteById
    }

    @Test
    void borrarEmpleadoNoExistente() {
        // Preparación
        int id = 1099;

        when(empleadosDAO.findById(id)).thenReturn(Optional.empty());

        // Ejecución
        boolean result = servicioEmpleados.borrarEmpleado(id);

        // Comprobación
        assertEquals(false, result);
    }
}


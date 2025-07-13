package com.jrgs.unidad5.empleados.controladores;

import com.jrgs.unidad5.empleados.modelo.dao.IDepartamentosDAO;
import com.jrgs.unidad5.empleados.modelo.dao.IEmpleadosDAO;
import com.jrgs.unidad5.empleados.modelo.*;
import com.jrgs.unidad5.empleados.modelo.dto.EmpleadosDTO;
import com.jrgs.unidad5.empleados.modelo.entidades.Departamento;
import com.jrgs.unidad5.empleados.modelo.entidades.Empleado;
import com.jrgs.unidad5.empleados.servicios.ServicioEmpleados;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
public class ControladorEmpleados {
    final
    ServicioEmpleados servicioEmpleados;

    public ControladorEmpleados(ServicioEmpleados servicioEmpleados) {
        this.servicioEmpleados = servicioEmpleados;
    }

    @GetMapping
    public List<Empleado> buscarEmpleados(@RequestParam(name = "puesto", required = false) String puesto) {
        if (puesto == null)
            return servicioEmpleados.buscarEmpleados();
        else
            return servicioEmpleados.buscarEmpleadosPorPuesto(puesto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@PathVariable(value = "id") int id) {
        Optional<Empleado> empleado = servicioEmpleados.buscarEmpleadoPorCodigo(id);
        if(empleado.isPresent()) {
            return ResponseEntity.ok().body(empleado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarEmpleado(@Validated @RequestBody Empleado empleado) {
        if (!servicioEmpleados.buscarEmpleadoPorCodigo(empleado.getEmpno()).isPresent())
            return ResponseEntity.ok().body(servicioEmpleados.guardarEmpleado(empleado));
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@RequestBody Empleado nuevoEmpleado,
                                                @PathVariable(value = "id") int id) {
        if(servicioEmpleados.actualizarEmpleado(id, nuevoEmpleado)) {
            return ResponseEntity.ok().body("Updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarEmpleado(@PathVariable(value = "id") int id) {
        if(servicioEmpleados.borrarEmpleado(id)) {
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
/*
    -------- CONTROLADOR SIN CAPA DE SERVICIO -----------

    @Autowired
    IEmpleadosDAO empleadosDAO;

    @Autowired
    IDepartamentosDAO departamentosDAO;

    @GetMapping
    public List<Empleado> buscarEmpleados() {
            return (List<Empleado>) empleadosDAO.findAll();
    }

//    @GetMapping
//    public List<Empleado> buscarEmpleados(@RequestParam(name = "puesto", required = false) String puesto) {
//        if (puesto == null)
//            return (List<Empleado>) empleadosDAO.findAll();
//        else
//            return (List<Empleado>) empleadosDAO.findByPuestoContains(puesto);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@PathVariable(value = "id") int id) {
        Optional<Empleado> empleado = empleadosDAO.findById(id);
        if (empleado.isPresent())
            return ResponseEntity.ok().body(empleado.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> guardarEmpleado(@Validated @RequestBody Empleado empleado) {
        if (!empleadosDAO.existsById(empleado.getEmpno()))
            return ResponseEntity.ok().body(empleadosDAO.save(empleado));
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@RequestBody Empleado nuevoEmpleado,
                                                @PathVariable(value = "id") int id) {
        if(empleadosDAO.existsById(id)) {
            empleadosDAO.save(nuevoEmpleado);
            return ResponseEntity.ok().body("Updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarEmpleado(@PathVariable(value = "id") int id) {
        if(empleadosDAO.existsById(id)) {
            empleadosDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    IDepartamentosDAO departamentosDAO;

    @GetMapping("dto/{id}")
    public ResponseEntity<EmpleadosDTO> buscarEmpleadoDTOporIdv1(@PathVariable(value = "id") int id) {
        Optional<Empleado> empleado = empleadosDAO.findById(id);

        if (empleado.isPresent()) {
            Optional<Departamento> departamento = departamentosDAO.findById(empleado.get().getDepno());

            EmpleadosDTO empleadoDTO = new EmpleadosDTO();
            empleadoDTO.setEmpno(empleado.get().getEmpno());
            empleadoDTO.setNombre(empleado.get().getNombre());
            empleadoDTO.setPuesto(empleado.get().getPuesto());
            empleadoDTO.setDepno(empleado.get().getDepno());
            empleadoDTO.setDepartamentoNombre(departamento.get().getNombre());
            empleadoDTO.setDepartamentoUbicacion(departamento.get().getUbicacion());

            return ResponseEntity.ok().body(empleadoDTO);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("dto/{id}")
    public ResponseEntity<EmpleadosDTO> buscarEmpleadoDTOporId(@PathVariable(value = "id") int id) {
        Optional<Empleado> empleado = empleadosDAO.findById(id);

        if (empleado.isPresent()) {
            Optional<Departamento> departamento = departamentosDAO.findById(empleado.get().getDepno());

            ModelMapper mapper = new ModelMapper();
            EmpleadosDTO empleadoDTO = mapper.map(empleado.get(), EmpleadosDTO.class);
            mapper.typeMap(Departamento.class, EmpleadosDTO.class).
                    addMappings( mapping -> mapping.skip(EmpleadosDTO::setNombre) );
            mapper.map(departamento.get(), empleadoDTO);

            return ResponseEntity.ok().body(empleadoDTO);
        }
        else
            return ResponseEntity.notFound().build();
    }
    */
}
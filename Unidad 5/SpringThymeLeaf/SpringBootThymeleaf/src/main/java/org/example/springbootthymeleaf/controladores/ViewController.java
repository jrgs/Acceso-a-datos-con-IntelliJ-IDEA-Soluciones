package org.example.springbootthymeleaf.controladores;

import org.example.springbootthymeleaf.modelo.dao.IEmpleadosDAO;
import org.example.springbootthymeleaf.modelo.entidades.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.example.springbootthymeleaf.modelo.dao.IDepartamentosDAO;
import org.example.springbootthymeleaf.modelo.entidades.Departamento;

import java.util.List;
import java.util.Optional;

@Controller
public class ViewController {

    @Autowired
    IDepartamentosDAO departamentosDAO;
    @Autowired
    IEmpleadosDAO empleadosDAO;

    @GetMapping({"/", "/index", "/index.html", "/index.htm"})
    public String index() {
        return "index";
    }

    @GetMapping({"/verdepartamentos", "/verdepartamentos.html"})
    public String mostrarDepartamentos(Model model) {
        List<Departamento> departamentos = (List<Departamento>) departamentosDAO.findAll();
        model.addAttribute("departamentos", departamentos);
        model.addAttribute("nombredepartamento", "Todos");
        return "verdepartamentos";
    }

    @GetMapping("/verempleados")
    public String mostrarEmpleados(Model model, @RequestParam(name = "depno", required = false) Integer depno) {
        List<Departamento> departamentos = (List<Departamento>) departamentosDAO.findAll();
        //       departamentos.add(new EntidadDepartamentos(0, "Todos", "Todas"));
        //       departamentos.sort(Comparator.comparing(EntidadDepartamentos::getDepno));
        model.addAttribute("departamentos", departamentos);
        List<Empleado> empleados;
        if (depno == null)  { //|| depno == 0)
            empleados = (List<Empleado>) empleadosDAO.findAll();
            model.addAttribute("nombredepartamento", "Todos");
        }
        else {
            empleados = (List<Empleado>) empleadosDAO.findByDepno(depno);
            Optional<Departamento> departamento = departamentosDAO.findById(depno);
            model.addAttribute("nombredepartamento", departamento.get().getNombre());
        }
        model.addAttribute("empleados", empleados);
        return "verempleados";
    }

    @GetMapping("/verempleado")
    public String mostrarEmpleado(Model model, @RequestParam(name = "id", required = true) int id) {
        Optional<Empleado> empleado = empleadosDAO.findById(id);
        if (!empleado.isPresent()) {
            model.addAttribute("titulo", "Error");
            model.addAttribute("mensaje", "No se encontro el empleado con el id " + id);
            return "error";
        }
        model.addAttribute("empleado", empleado.get());
        return "verempleado";
    }

    @GetMapping("/altadepartamento")
    public String altaDepartamento(Model model) {
        model.addAttribute("departamento", new Departamento());
        return "altadepartamento";
    }

    @PostMapping("/altadepartamento")
    public String crearDepartamento(@ModelAttribute Departamento departamento, Model model) {
        if (!departamentosDAO.existsById(departamento.getDepno()))
        {
            departamentosDAO.save(departamento);
            model.addAttribute("tipo_operacion", "ok");
            model.addAttribute("mensaje", "Departamento creado correctamente");
        }
        else {
            model.addAttribute("tipo_operacion", "error");
            model.addAttribute("mensaje", "Error al crear el departamento: clave duplicada");
        }
        return "altadepartamento";
    }
}

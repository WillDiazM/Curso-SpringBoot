package cl.kibernum.springdemo.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import cl.kibernum.springdemo.repository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.kibernum.springdemo.exception.ResourceNotFoundException;
import cl.kibernum.springdemo.model.Empleados;

@RestController
@RequestMapping("/api/v1")
public class EmpleadosController {
    @Autowired
    private EmpleadosRepository empleadosRepository;

    @GetMapping("/empleados")
    public List < Empleados > getAllEmpleados() {
        return empleadosRepository.findAll();
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity < Empleados > getEmpleadoById(@PathVariable(value = "id") Long empleadosId)
            throws ResourceNotFoundException {
        Empleados empleado = empleadosRepository.findById(empleadosId)
                .orElseThrow(() -> new ResourceNotFoundException("Empleados no encontrada con el ID :: " + empleadosId));
        return ResponseEntity.ok().body(empleado);
    }

    @PostMapping("/empleados")
    public Empleados createEmpleado(@Valid @RequestBody Empleados empleado) {
        return empleadosRepository.save(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity < Empleados > updateEmpleado(@PathVariable(value = "id") Long empleadosId,
                                                        @Valid @RequestBody Empleados empleadoDetails) throws ResourceNotFoundException {
        Empleados empleado = empleadosRepository.findById(empleadosId)
                .orElseThrow(() -> new ResourceNotFoundException("Empleados no encontrada con el ID :: " + empleadosId));

        empleado.setIdEmpleado(empleadoDetails.getIdEmpleado());
        empleado.setApePat(empleadoDetails.getApePat());
        empleado.setApeMat(empleadoDetails.getApeMat());
        empleado.setNombre(empleadoDetails.getNombre());
        empleado.setIdSucursal(empleadoDetails.getIdSucursal());
        final Empleados updatedEmpleado = empleadosRepository.save(empleado);
        return ResponseEntity.ok(updatedEmpleado);
    }

    @DeleteMapping("/empleados/{id}")
    public Map < String, Boolean > deleteEmpleado(@PathVariable(value = "id") Long empleadosId)
            throws ResourceNotFoundException {
        Empleados empleado = empleadosRepository.findById(empleadosId)
                .orElseThrow(() -> new ResourceNotFoundException("Empleados no encontrada con el ID :: " + empleadosId));

        empleadosRepository.delete(empleado);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

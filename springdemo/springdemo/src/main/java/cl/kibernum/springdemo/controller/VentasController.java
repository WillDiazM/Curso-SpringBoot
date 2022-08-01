package cl.kibernum.springdemo.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import cl.kibernum.springdemo.repository.VentasRepository;
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
import cl.kibernum.springdemo.model.Ventas;
import cl.kibernum.springdemo.repository.VentasRepository;

@RestController
@RequestMapping("/api/v1")
public class VentasController {
    @Autowired
    private VentasRepository ventasRepository;

    @GetMapping("/ventas")
    public List < Ventas > getAllVentass() {
        return ventasRepository.findAll();
    }

    @GetMapping("/ventas/{id}")
    public ResponseEntity < Ventas > getVentasById(@PathVariable(value = "id") Long ventasId)
            throws ResourceNotFoundException {
        Ventas ventas = ventasRepository.findById(ventasId)
                .orElseThrow(() -> new ResourceNotFoundException("Ventas no encontrada con el ID :: " + ventasId));
        return ResponseEntity.ok().body(ventas);
    }

    @PostMapping("/ventas")
    public Ventas createVentas(@Valid @RequestBody Ventas ventas) {
        return ventasRepository.save(ventas);
    }

    @PutMapping("/ventas/{id}")
    public ResponseEntity < Ventas > updateVentas(@PathVariable(value = "id") Long ventasId,
                                                        @Valid @RequestBody Ventas ventasDetails) throws ResourceNotFoundException {
        Ventas ventas = ventasRepository.findById(ventasId)
                .orElseThrow(() -> new ResourceNotFoundException("Ventas no encontrada con el ID :: " + ventasId));

        ventas.setIdVenta(ventasDetails.getIdVenta());
        ventas.setFecha(ventasDetails.getFecha());
        ventas.setIdCliente(ventasDetails.getIdCliente());
        ventas.setIdEmpleado(ventasDetails.getIdEmpleado());
        final Ventas updatedVentas = ventasRepository.save(ventas);
        return ResponseEntity.ok(updatedVentas);
    }

    @DeleteMapping("/ventas/{id}")
    public Map < String, Boolean > deleteVentas(@PathVariable(value = "id") Long ventasId)
            throws ResourceNotFoundException {
        Ventas ventas = ventasRepository.findById(ventasId)
                .orElseThrow(() -> new ResourceNotFoundException("Ventas no encontrada con el ID :: " + ventasId));

        ventasRepository.delete(ventas);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

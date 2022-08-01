package cl.kibernum.springdemo.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import cl.kibernum.springdemo.repository.DetalleVentasRepository;
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
import cl.kibernum.springdemo.model.DetalleVentas;
import cl.kibernum.springdemo.repository.DetalleVentasRepository;

@RestController
@RequestMapping("/api/v1")
public class DetalleVentasController {
    @Autowired
    private DetalleVentasRepository detalleVentasRepository;

    @GetMapping("/detalleVentass")
    public List < DetalleVentas > getAllDetalleVentas() {
        return detalleVentasRepository.findAll();
    }

    @GetMapping("/detalleVentass/{id}")
    public ResponseEntity < DetalleVentas > getDetalleVentasById(@PathVariable(value = "id") Long ventaId)
            throws ResourceNotFoundException {
        DetalleVentas detalleVentas = detalleVentasRepository.findById(ventaId)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleVentas no encontrada con el ID :: " + ventaId));
        return ResponseEntity.ok().body(detalleVentas);
    }

    @PostMapping("/detalleVentass")
    public DetalleVentas createDetalleVentas(@Valid @RequestBody DetalleVentas detalleVentas) {
        return detalleVentasRepository.save(detalleVentas);
    }

    @PutMapping("/detalleVentass/{id}")
    public ResponseEntity < DetalleVentas > updateDetalleVentas(@PathVariable(value = "id") Long ventaId,
                                                        @Valid @RequestBody DetalleVentas detalleVentasDetails) throws ResourceNotFoundException {
        DetalleVentas detalleVentas = detalleVentasRepository.findById(ventaId)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleVentas no encontrada con el ID :: " + ventaId));

        detalleVentas.setIdVenta(detalleVentasDetails.getIdVenta());
        detalleVentas.setCantidad(detalleVentasDetails.getCantidad());
        detalleVentas.setIdProducto(detalleVentasDetails.getIdProducto());
        final DetalleVentas updatedDetalleVentas = detalleVentasRepository.save(detalleVentas);
        return ResponseEntity.ok(updatedDetalleVentas);
    }

    @DeleteMapping("/detalleVentass/{id}")
    public Map < String, Boolean > deleteDetalleVentas(@PathVariable(value = "id") Long ventaId)
            throws ResourceNotFoundException {
        DetalleVentas detalleVentas = detalleVentasRepository.findById(ventaId)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleVentas no encontrada con el ID :: " + ventaId));

        detalleVentasRepository.delete(detalleVentas);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

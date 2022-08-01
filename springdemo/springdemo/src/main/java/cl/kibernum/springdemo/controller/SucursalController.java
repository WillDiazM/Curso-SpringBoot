package cl.kibernum.springdemo.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import cl.kibernum.springdemo.model.Sucursal;
import cl.kibernum.springdemo.repository.SucursalRepository;

@RestController
@RequestMapping("/api/v1")
public class SucursalController {
    @Autowired
    private SucursalRepository sucursalRepository;

    @GetMapping("/sucursal")
    public List < Sucursal > getAllSucursals() {
        return sucursalRepository.findAll();
    }

    @GetMapping("/sucursal/{id}")
    public ResponseEntity < Sucursal > getSucursalById(@PathVariable(value = "id") Long sucursalId)
            throws ResourceNotFoundException {
        Sucursal sucursal = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new ResourceNotFoundException("Sucursal no encontrada con el ID :: " + sucursalId));
        return ResponseEntity.ok().body(sucursal);
    }

    @PostMapping("/sucursal")
    public Sucursal createSucursal(@Valid @RequestBody Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    @PutMapping("/sucursal/{id}")
    public ResponseEntity < Sucursal > updateSucursal(@PathVariable(value = "id") Long sucursalId,
                                                        @Valid @RequestBody Sucursal sucursalDetails) throws ResourceNotFoundException {
        Sucursal sucursal = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new ResourceNotFoundException("Sucursal no encontrada con el ID :: " + sucursalId));

        sucursal.setIdSucursal(sucursalDetails.getIdSucursal());
        sucursal.setDescripcion(sucursalDetails.getDescripcion());
        sucursal.setIdComuna(sucursalDetails.getIdComuna());
        final Sucursal updatedSucursal = sucursalRepository.save(sucursal);
        return ResponseEntity.ok(updatedSucursal);
    }

    @DeleteMapping("/sucursal/{id}")
    public Map < String, Boolean > deleteSucursal(@PathVariable(value = "id") Long sucursalId)
            throws ResourceNotFoundException {
        Sucursal sucursal = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new ResourceNotFoundException("Sucursal no encontrada con el ID :: " + sucursalId));

        sucursalRepository.delete(sucursal);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

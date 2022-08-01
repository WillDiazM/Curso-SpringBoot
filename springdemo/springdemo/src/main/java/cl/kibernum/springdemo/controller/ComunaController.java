package cl.kibernum.springdemo.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import cl.kibernum.springdemo.model.Comuna;
import cl.kibernum.springdemo.repository.ComunaRepository;
import cl.kibernum.springdemo.repository.ComunaRepository;
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
import cl.kibernum.springdemo.model.Categoria;
import cl.kibernum.springdemo.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/v1")
public class ComunaController {


    @Autowired
    private ComunaRepository comunaRepository;

    @GetMapping("/comuna")
    public List< Comuna > getAllComuna() {
        return comunaRepository.findAll();
    }

    @GetMapping("/comuna/{id}")
    public ResponseEntity < Comuna > getComunaById(@PathVariable(value = "id") Long comunaId)
            throws ResourceNotFoundException {
        Comuna comuna = comunaRepository.findById(comunaId)
                .orElseThrow(() -> new ResourceNotFoundException("comuna no encontrada con el ID :: " + comunaId));
        return ResponseEntity.ok().body(comuna);
    }

    @PostMapping("/comuna")
    public Comuna createComuna(@Valid @RequestBody Comuna comuna) {
        return comunaRepository.save(comuna);
    }

    @PutMapping("/comuna/{id}")
    public ResponseEntity < Comuna > updateComuna(@PathVariable(value = "id") Long comunaId,
                                                      @Valid @RequestBody Comuna comunaDetails) throws ResourceNotFoundException {
        Comuna comuna = comunaRepository.findById(comunaId)
                .orElseThrow(() -> new ResourceNotFoundException("comuna no encontrada con el ID :: " + comunaId));

        comuna.setDescripcion(comunaDetails.getDescripcion());
        comuna.setIdRegion(comunaDetails.getIdRegion());
        final Comuna updatedComuna = comunaRepository.save(comuna);
        return ResponseEntity.ok(updatedComuna);
    }

    @DeleteMapping("/comuna/{id}")
    public Map < String, Boolean > deleteComuna(@PathVariable(value = "id") Long comunaId)
            throws ResourceNotFoundException {
        Comuna comuna = comunaRepository.findById(comunaId)
                .orElseThrow(() -> new ResourceNotFoundException("comuna no encontrada con el ID :: " + comunaId));

        comunaRepository.delete(comuna);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

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
import cl.kibernum.springdemo.model.Categoria;
import cl.kibernum.springdemo.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/v1")
public class CategoriaController  {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categorias")
    public List < Categoria > getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity < Categoria > getCategoriaById(@PathVariable(value = "id") Long categoriaId)
            throws ResourceNotFoundException {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con el ID :: " + categoriaId));
        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping("/categorias")
    public Categoria createCategoria(@Valid @RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity < Categoria > updateCategoria(@PathVariable(value = "id") Long categoriaId,
                                                      @Valid @RequestBody Categoria categoriaDetails) throws ResourceNotFoundException {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con el ID :: " + categoriaId));

        categoria.setNombre(categoriaDetails.getNombre());
        final Categoria updatedCategoria = categoriaRepository.save(categoria);
        return ResponseEntity.ok(updatedCategoria);
    }

    @DeleteMapping("/categorias/{id}")
    public Map < String, Boolean > deleteCategoria(@PathVariable(value = "id") Long categoriaId)
            throws ResourceNotFoundException {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con el ID :: " + categoriaId));

        categoriaRepository.delete(categoria);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

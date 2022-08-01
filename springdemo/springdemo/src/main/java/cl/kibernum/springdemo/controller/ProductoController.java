package cl.kibernum.springdemo.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import cl.kibernum.springdemo.repository.ProductoRepository;
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
import cl.kibernum.springdemo.model.Producto;

@RestController
@RequestMapping("/api/v1")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/productos")
    public List < Producto > getAllProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity < Producto > getProductoById(@PathVariable(value = "id") Long productoId)
            throws ResourceNotFoundException {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrada con el ID :: " + productoId));
        return ResponseEntity.ok().body(producto);
    }

    @PostMapping("/productos")
    public Producto createProducto(@Valid @RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity < Producto > updateProducto(@PathVariable(value = "id") Long productoId,
                                                        @Valid @RequestBody Producto productoDetails) throws ResourceNotFoundException {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrada con el ID :: " + productoId));

        producto.setIdProducto(productoDetails.getIdProducto());
        producto.setDescripcion(productoDetails.getDescripcion());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setStock(productoDetails.getStock());
        producto.setIdCategoria(productoDetails.getIdCategoria());
        final Producto updatedProducto = productoRepository.save(producto);
        return ResponseEntity.ok(updatedProducto);
    }

    @DeleteMapping("/productos/{id}")
    public Map < String, Boolean > deleteProducto(@PathVariable(value = "id") Long productoId)
            throws ResourceNotFoundException {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrada con el ID :: " + productoId));

        productoRepository.delete(producto);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

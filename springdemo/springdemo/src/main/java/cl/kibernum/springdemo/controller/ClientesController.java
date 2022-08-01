package cl.kibernum.springdemo.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import cl.kibernum.springdemo.model.Clientes;
import cl.kibernum.springdemo.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.kibernum.springdemo.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class ClientesController {

    @Autowired
    private ClientesRepository clientesRepository;

    @GetMapping("/clientes")
    public List< Clientes > getAllClientes() {
        return clientesRepository.findAll();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity < Clientes > getClientesById(@PathVariable(value = "id") Long clientesId)
            throws ResourceNotFoundException {
        Clientes clientes = clientesRepository.findById(clientesId)
                .orElseThrow(() -> new ResourceNotFoundException("clientes no encontrada con el ID :: " + clientesId));
        return ResponseEntity.ok().body(clientes);
    }

    @PostMapping("/clientes")
    public Clientes createClientes(@Valid @RequestBody Clientes clientes) {
        return clientesRepository.save(clientes);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity < Clientes > updateClientes(@PathVariable(value = "id") Long clientesId,
                                                        @Valid @RequestBody Clientes clientesDetails) throws ResourceNotFoundException {
        Clientes clientes = clientesRepository.findById(clientesId)
                .orElseThrow(() -> new ResourceNotFoundException("clientes no encontrada con el ID :: " + clientesId));

        clientes.setNombre(clientesDetails.getNombre());
        clientes.setApePat(clientesDetails.getApePat());
        clientes.setApeMat(clientesDetails.getApeMat());
        clientes.setEmail(clientesDetails.getEmail());
        clientes.setTelefono(clientesDetails.getTelefono());
        clientes.setIdComuna(clientesDetails.getIdComuna());
        final Clientes updatedClientes = clientesRepository.save(clientes);
        return ResponseEntity.ok(updatedClientes);
    }

    @DeleteMapping("/clientes/{id}")
    public Map < String, Boolean > deleteClientes(@PathVariable(value = "id") Long clientesId)
            throws ResourceNotFoundException {
        Clientes clientes = clientesRepository.findById(clientesId)
                .orElseThrow(() -> new ResourceNotFoundException("clientes no encontrada con el ID :: " + clientesId));

        clientesRepository.delete(clientes);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

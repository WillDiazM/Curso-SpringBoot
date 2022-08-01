package cl.kibernum.springdemo.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import cl.kibernum.springdemo.repository.RegionRepository;
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
import cl.kibernum.springdemo.model.Region;
import cl.kibernum.springdemo.repository.RegionRepository;

@RestController
@RequestMapping("/api/v1")
public class RegionController {

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping("/region")
    public List < Region > getAllRegions() {
        return regionRepository.findAll();
    }

    @GetMapping("/region/{id}")
    public ResponseEntity < Region > getRegionById(@PathVariable(value = "id") Long regionId)
            throws ResourceNotFoundException {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new ResourceNotFoundException("Region no encontrada con el ID :: " + regionId));
        return ResponseEntity.ok().body(region);
    }

    @PostMapping("/region")
    public Region createRegion(@Valid @RequestBody Region region) {
        return regionRepository.save(region);
    }

    @PutMapping("/region/{id}")
    public ResponseEntity < Region > updateRegion(@PathVariable(value = "id") Long regionId,
                                                        @Valid @RequestBody Region regionDetails) throws ResourceNotFoundException {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new ResourceNotFoundException("Region no encontrada con el ID :: " + regionId));

        region.setIdRegion(regionDetails.getIdRegion());
        region.setDescripcion(regionDetails.getDescripcion());
        final Region updatedRegion = regionRepository.save(region);
        return ResponseEntity.ok(updatedRegion);
    }

    @DeleteMapping("/region/{id}")
    public Map < String, Boolean > deleteRegion(@PathVariable(value = "id") Long regionId)
            throws ResourceNotFoundException {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new ResourceNotFoundException("Region no encontrada con el ID :: " + regionId));

        regionRepository.delete(region);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

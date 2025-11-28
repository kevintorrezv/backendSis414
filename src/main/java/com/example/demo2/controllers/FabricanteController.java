package com.example.demo2.controllers;

import com.example.demo2.models.Fabricante;
import com.example.demo2.repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fabricantes")
public class FabricanteController {

    @Autowired
    private FabricanteRepository fabricanteRepository;

    @GetMapping
    public List<Fabricante> getAllFabricantes() {
        return fabricanteRepository.findAll();
    }

    @PostMapping
    public Fabricante createFabricante(@RequestBody Fabricante fabricante) {
        return fabricanteRepository.save(fabricante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fabricante> updateFabricante(@PathVariable Long id, @RequestBody Fabricante fabricanteDetails) {
        Optional<Fabricante> fabricanteOptional = fabricanteRepository.findById(id);

        if (fabricanteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Fabricante fabricante = fabricanteOptional.get();
        // Actualizamos todos los campos con los nuevos datos
        fabricante.setNombre(fabricanteDetails.getNombre());
        fabricante.setAnoFundacion(fabricanteDetails.getAnoFundacion());
        fabricante.setSitioWeb(fabricanteDetails.getSitioWeb());

        Fabricante updatedFabricante = fabricanteRepository.save(fabricante);
        return ResponseEntity.ok(updatedFabricante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFabricante(@PathVariable Long id) {
        if (!fabricanteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        fabricanteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
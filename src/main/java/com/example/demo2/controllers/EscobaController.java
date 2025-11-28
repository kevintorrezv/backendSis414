package com.example.demo2.controllers;

import com.example.demo2.models.Escoba;
import com.example.demo2.models.Fabricante;
import com.example.demo2.repository.EscobaRepository;
import com.example.demo2.repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/escobas")
public class EscobaController {

    @Autowired
    private EscobaRepository escobaRepository;

    @Autowired
    private FabricanteRepository fabricanteRepository;

    @GetMapping
    public List<Escoba> getAllEscobas() {
        return escobaRepository.findAll();
    }

    @PostMapping("/fabricante/{fabricanteId}")
    public ResponseEntity<Escoba> createEscoba(@PathVariable Long fabricanteId, @RequestBody Escoba escoba) {
        Optional<Fabricante> fabricanteOptional = fabricanteRepository.findById(fabricanteId);
        if (fabricanteOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Malo porque el fabricante no existe
        }

        if (escoba.getNombre() != null) {
            escoba.setModelo(escoba.getNombre());
        }

        escoba.setFabricante(fabricanteOptional.get());
        Escoba newEscoba = escobaRepository.save(escoba);
        return ResponseEntity.ok(newEscoba);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Escoba> updateEscoba(@PathVariable Long id, @RequestBody Escoba escobaDetails) {
        Optional<Escoba> escobaOptional = escobaRepository.findById(id);

        if (escobaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Escoba escoba = escobaOptional.get();
        // Actualizamos todos los campos con los nuevos datos
        escoba.setModelo(escobaDetails.getModelo());
        escoba.setColor(escobaDetails.getColor());
        escoba.setTipoDeMadera(escobaDetails.getTipoDeMadera());
        escoba.setLongitudCm(escobaDetails.getLongitudCm());
        escoba.setUsoRecomendado(escobaDetails.getUsoRecomendado());

        Escoba updatedEscoba = escobaRepository.save(escoba);
        return ResponseEntity.ok(updatedEscoba);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEscoba(@PathVariable Long id) {
        if (!escobaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        escobaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
package com.monstage.controleur;

import com.monstage.modele.Entreprise;
import com.monstage.service.EntrepriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entreprises")
@RequiredArgsConstructor
public class EntrepriseControleur {

    private final EntrepriseService entrepriseService;

    @GetMapping
    public ResponseEntity<List<Entreprise>> listerEntreprises() {
        return ResponseEntity.ok(entrepriseService.toutesLesEntreprises());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entreprise> trouverParId(@PathVariable Long id) {
        return ResponseEntity.ok(entrepriseService.trouverParId(id));
    }
}
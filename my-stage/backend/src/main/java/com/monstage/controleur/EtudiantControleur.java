package com.monstage.controleur;

import com.monstage.modele.Etudiant;
import com.monstage.service.EtudiantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
@RequiredArgsConstructor
public class EtudiantControleur {

    private final EtudiantService etudiantService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESSEUR')")
    public ResponseEntity<List<Etudiant>> listerEtudiants() {
        return ResponseEntity.ok(etudiantService.tousLesEtudiants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> trouverParId(@PathVariable Long id) {
        return ResponseEntity.ok(etudiantService.trouverParId(id));
    }
}
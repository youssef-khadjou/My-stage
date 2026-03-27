package com.monstage.controleur;

import com.monstage.modele.Professeur;
import com.monstage.repository.ProfesseurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professeurs")
@RequiredArgsConstructor
public class ProfesseurControleur {

    private final ProfesseurRepository professeurRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Professeur>> listerProfesseurs() {
        return ResponseEntity.ok(professeurRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professeur> trouverParId(@PathVariable Long id) {
        return ResponseEntity.ok(professeurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professeur non trouvé")));
    }
}
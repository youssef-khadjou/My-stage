package com.monstage.controleur;

import com.monstage.dto.RequeteCandidature;
import com.monstage.modele.Candidature;
import com.monstage.service.CandidatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidatures")
@RequiredArgsConstructor
public class CandidatureControleur {

    private final CandidatureService candidatureService;

    @PostMapping
    @PreAuthorize("hasRole('ETUDIANT')")
    public ResponseEntity<Candidature> postuler(@RequestBody RequeteCandidature requete,
                                                 @RequestParam Long etudiantId) {
        return ResponseEntity.ok(candidatureService.postuler(requete, etudiantId));
    }

    @GetMapping("/etudiant/{etudiantId}")
    @PreAuthorize("hasRole('ETUDIANT') or hasRole('ADMIN')")
    public ResponseEntity<List<Candidature>> candidaturesEtudiant(@PathVariable Long etudiantId) {
        return ResponseEntity.ok(candidatureService.candidaturesEtudiant(etudiantId));
    }

    @GetMapping("/stage/{stageId}")
    @PreAuthorize("hasRole('ENTREPRISE') or hasRole('ADMIN')")
    public ResponseEntity<List<Candidature>> candidaturesStage(@PathVariable Long stageId) {
        return ResponseEntity.ok(candidatureService.candidaturesStage(stageId));
    }

    @PatchMapping("/{id}/statut")
    @PreAuthorize("hasRole('ENTREPRISE') or hasRole('ADMIN')")
    public ResponseEntity<Candidature> changerStatut(@PathVariable Long id,
                                                      @RequestParam Candidature.Statut statut) {
        return ResponseEntity.ok(candidatureService.changerStatut(id, statut));
    }
}
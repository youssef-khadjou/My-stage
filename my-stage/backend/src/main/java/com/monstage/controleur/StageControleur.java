package com.monstage.controleur;

import com.monstage.dto.RequeteStage;
import com.monstage.modele.Stage;
import com.monstage.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stages")
@RequiredArgsConstructor
public class StageControleur {

    private final StageService stageService;

    @GetMapping("/liste")
    public ResponseEntity<List<Stage>> listerStagesValides() {
        return ResponseEntity.ok(stageService.stagesValides());
    }

    @GetMapping
    @PreAuthorize("hasRole('SECRETARIAT') or hasRole('ADMIN')")
    public ResponseEntity<List<Stage>> tousLesStages() {
        return ResponseEntity.ok(stageService.tousLesStages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stage> trouverParId(@PathVariable Long id) {
        return ResponseEntity.ok(stageService.trouverParId(id));
    }

    @GetMapping("/entreprise/{entrepriseId}")
    @PreAuthorize("hasRole('ENTREPRISE') or hasRole('SECRETARIAT')")
    public ResponseEntity<List<Stage>> stagesEntreprise(@PathVariable Long entrepriseId) {
        return ResponseEntity.ok(stageService.stagesParEntreprise(entrepriseId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ENTREPRISE')")
    public ResponseEntity<Stage> creer(@RequestBody RequeteStage requete,
                                        @RequestParam Long entrepriseId) {
        return ResponseEntity.ok(stageService.creer(requete, entrepriseId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ENTREPRISE') or hasRole('SECRETARIAT')")
    public ResponseEntity<Stage> modifier(@PathVariable Long id,
                                           @RequestBody RequeteStage requete) {
        return ResponseEntity.ok(stageService.modifier(id, requete));
    }

    @PatchMapping("/{id}/statut")
    @PreAuthorize("hasRole('SECRETARIAT')")
    public ResponseEntity<Stage> changerStatut(@PathVariable Long id,
                                                @RequestParam Stage.Statut statut) {
        return ResponseEntity.ok(stageService.changerStatut(id, statut));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ENTREPRISE') or hasRole('SECRETARIAT')")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        stageService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
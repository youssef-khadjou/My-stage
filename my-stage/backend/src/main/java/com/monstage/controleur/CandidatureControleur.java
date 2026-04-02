package com.monstage.controleur;

import com.monstage.dto.RequeteCandidature;
import com.monstage.modele.Candidature;
import com.monstage.modele.Candidature.Statut;
import com.monstage.modele.Etudiant;
import com.monstage.modele.Stage;
import com.monstage.repository.CandidatureRepository;
import com.monstage.repository.EtudiantRepository;
import com.monstage.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidatures")
@CrossOrigin(origins = "*")
public class CandidatureControleur {

    @Autowired
    private CandidatureRepository candidatureRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private StageRepository stageRepository;

    @GetMapping
    public List<Candidature> getAllCandidatures() {
        return candidatureRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidature> getCandidatureById(@PathVariable Long id) {
        Optional<Candidature> candidature = candidatureRepository.findById(id);
        return candidature.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/etudiant/{etudiantId}")
    public List<Candidature> getCandidaturesByEtudiant(@PathVariable Long etudiantId) {
        return candidatureRepository.findByEtudiantId(etudiantId);
    }

    @GetMapping("/stage/{stageId}")
    public List<Candidature> getCandidaturesByStage(@PathVariable Long stageId) {
        return candidatureRepository.findByStageId(stageId);
    }

    @GetMapping("/stage/{stageId}/statut/{statut}")
    public List<Candidature> getCandidaturesByStageAndStatut(@PathVariable Long stageId, @PathVariable String statut) {
        return candidatureRepository.findByStageIdAndStatut(stageId, Statut.valueOf(statut));
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> postuler(@RequestBody RequeteCandidature requete) {
        // Vérifier si l'étudiant a déjà postulé à ce stage
        if (candidatureRepository.existsByEtudiantIdAndStageId(requete.getEtudiantId(), requete.getStageId())) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Vous avez déjà postulé à ce stage");
            return ResponseEntity.badRequest().body(error);
        }

        Optional<Etudiant> etudiantOpt = etudiantRepository.findById(requete.getEtudiantId());
        Optional<Stage> stageOpt = stageRepository.findById(requete.getStageId());

        if (etudiantOpt.isEmpty() || stageOpt.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Étudiant ou Stage non trouvé");
            return ResponseEntity.badRequest().body(error);
        }

        Candidature candidature = new Candidature();
        candidature.setEtudiant(etudiantOpt.get());
        candidature.setStage(stageOpt.get());
        candidature.setLettreMotivation(requete.getLettreMotivation());
        candidature.setStatut(Statut.EN_ATTENTE);
        // datePostulation est automatiquement gérée par @PrePersist

        candidatureRepository.save(candidature);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Candidature envoyée avec succès");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<Candidature> updateStatut(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Optional<Candidature> candidatureOpt = candidatureRepository.findById(id);
        if (candidatureOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Candidature candidature = candidatureOpt.get();
        candidature.setStatut(Statut.valueOf(body.get("statut")));
        return ResponseEntity.ok(candidatureRepository.save(candidature));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidature(@PathVariable Long id) {
        if (candidatureRepository.existsById(id)) {
            candidatureRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
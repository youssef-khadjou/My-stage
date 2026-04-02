package com.monstage.controleur;

import com.monstage.dto.RequeteStage;
import com.monstage.modele.Stage;
import com.monstage.modele.Stage.Statut;
import com.monstage.modele.Entreprise;
import com.monstage.repository.StageRepository;
import com.monstage.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/stages")
@CrossOrigin(origins = "*")
public class StageControleur {

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @GetMapping
    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stage> getStageById(@PathVariable Long id) {
        Optional<Stage> stage = stageRepository.findById(id);
        return stage.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/entreprise/{entrepriseId}")
    public List<Stage> getStagesByEntreprise(@PathVariable Long entrepriseId) {
        return stageRepository.findByEntrepriseId(entrepriseId);
    }

    @GetMapping("/statut/{statut}")
    public List<Stage> getStagesByStatut(@PathVariable String statut) {
        return stageRepository.findByStatut(Statut.valueOf(statut));
    }

    @GetMapping("/recherche/titre")
    public List<Stage> getStagesByTitre(@RequestParam String titre) {
        return stageRepository.findByTitreContaining(titre);
    }

    @PostMapping
    public ResponseEntity<Stage> createStage(@RequestBody RequeteStage requete) {
        Optional<Entreprise> entrepriseOpt = entrepriseRepository.findById(requete.getEntrepriseId());
        
        if (entrepriseOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        Stage stage = new Stage();
        stage.setTitre(requete.getTitre());
        stage.setDescription(requete.getDescription());
        stage.setDuree(requete.getDuree());
        stage.setLieu(requete.getLieu());
        stage.setDateDebut(requete.getDateDebut());
        stage.setDateFin(requete.getDateFin());
        stage.setNbPlaces(requete.getNbPlaces());
        stage.setCompetencesRequises(requete.getCompetencesRequises());
        stage.setRemuneration(requete.getRemuneration());
        stage.setStatut(Statut.EN_ATTENTE);
        stage.setDateCreation(LocalDateTime.now());
        stage.setEntreprise(entrepriseOpt.get());
        
        return ResponseEntity.ok(stageRepository.save(stage));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stage> updateStage(@PathVariable Long id, @RequestBody RequeteStage requete) {
        Optional<Stage> stageOpt = stageRepository.findById(id);
        if (stageOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Stage stage = stageOpt.get();
        stage.setTitre(requete.getTitre());
        stage.setDescription(requete.getDescription());
        stage.setDuree(requete.getDuree());
        stage.setLieu(requete.getLieu());
        stage.setDateDebut(requete.getDateDebut());
        stage.setDateFin(requete.getDateFin());
        stage.setNbPlaces(requete.getNbPlaces());
        stage.setCompetencesRequises(requete.getCompetencesRequises());
        stage.setRemuneration(requete.getRemuneration());
        
        return ResponseEntity.ok(stageRepository.save(stage));
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<Stage> updateStatut(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Optional<Stage> stageOpt = stageRepository.findById(id);
        if (stageOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Stage stage = stageOpt.get();
        stage.setStatut(Statut.valueOf(body.get("statut")));
        return ResponseEntity.ok(stageRepository.save(stage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable Long id) {
        if (stageRepository.existsById(id)) {
            stageRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
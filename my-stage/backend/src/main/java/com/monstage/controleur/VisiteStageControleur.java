package com.monstage.controleur;

import com.monstage.dto.RequeteVisiteStage;
import com.monstage.modele.VisiteStage;
import com.monstage.modele.Stage;
import com.monstage.repository.VisiteStageRepository;
import com.monstage.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/visites")
@CrossOrigin(origins = "http://localhost:4200")
public class VisiteStageControleur {

    @Autowired
    private VisiteStageRepository visiteStageRepository;

    @Autowired
    private StageRepository stageRepository;

    @GetMapping("/stage/{stageId}")
    public List<VisiteStage> getVisitesByStage(@PathVariable Long stageId) {
        return visiteStageRepository.findByStageIdOrderByDateVisiteDesc(stageId);
    }

    @PostMapping
    public ResponseEntity<VisiteStage> creerVisite(@RequestBody RequeteVisiteStage requete) {
        Optional<Stage> stageOpt = stageRepository.findById(requete.getStageId());
        if (stageOpt.isEmpty()) return ResponseEntity.badRequest().build();

        VisiteStage visite = new VisiteStage();
        visite.setDateVisite(LocalDate.parse(requete.getDateVisite()));
        visite.setPresent(requete.getPresent());
        visite.setCompteRendu(requete.getCompteRendu());
        visite.setNomTuteur(requete.getNomTuteur());
        visite.setDateSaisie(LocalDateTime.now());
        visite.setStage(stageOpt.get());

        return ResponseEntity.ok(visiteStageRepository.save(visite));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerVisite(@PathVariable Long id) {
        if (!visiteStageRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        visiteStageRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
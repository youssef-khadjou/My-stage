package com.monstage.controleur;

import com.monstage.dto.RequeteSujetStage;
import com.monstage.modele.SujetStage;
import com.monstage.modele.SujetStage.Statut;
import com.monstage.modele.Entreprise;
import com.monstage.repository.SujetStageRepository;
import com.monstage.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sujets")
@CrossOrigin(origins = "http://localhost:4200")
public class SujetStageControleur {

    @Autowired
    private SujetStageRepository sujetStageRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @GetMapping
    public List<SujetStage> getAllSujets() {
        return sujetStageRepository.findAll();
    }

    @GetMapping("/valides")
    public List<SujetStage> getSujetsValides() {
        return sujetStageRepository.findByStatut(Statut.VALIDE);
    }

    @GetMapping("/en-attente")
    public List<SujetStage> getSujetsEnAttente() {
        return sujetStageRepository.findByStatut(Statut.EN_ATTENTE);
    }

    @GetMapping("/entreprise/{entrepriseId}")
    public List<SujetStage> getSujetsByEntreprise(@PathVariable Long entrepriseId) {
        return sujetStageRepository.findByEntrepriseId(entrepriseId);
    }

    @PostMapping
    public ResponseEntity<SujetStage> createSujet(@RequestBody RequeteSujetStage requete,
                                                  @RequestParam Long entrepriseId) {
        Optional<Entreprise> entrepriseOpt = entrepriseRepository.findById(entrepriseId);
        if (entrepriseOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        SujetStage sujet = new SujetStage();
        sujet.setIntitule(requete.getIntitule());
        sujet.setDescription(requete.getDescription());
        sujet.setCompetencesRequises(requete.getCompetencesRequises());
        sujet.setDureeSemaines(requete.getDureeSemaines());
        sujet.setRemuneration(requete.getRemuneration());
        sujet.setSecteurActivite(requete.getSecteurActivite());
        sujet.setLieuTravail(requete.getLieuTravail());
        sujet.setStatut(Statut.EN_ATTENTE);
        sujet.setDateProposition(LocalDateTime.now());
        sujet.setEntreprise(entrepriseOpt.get());

        return ResponseEntity.ok(sujetStageRepository.save(sujet));
    }

    @PutMapping("/{id}/valider")
    public ResponseEntity<SujetStage> valider(@PathVariable Long id) {
        Optional<SujetStage> sujetOpt = sujetStageRepository.findById(id);
        if (sujetOpt.isEmpty()) return ResponseEntity.notFound().build();
        SujetStage sujet = sujetOpt.get();
        sujet.setStatut(Statut.VALIDE);
        sujet.setDateValidation(LocalDateTime.now());
        return ResponseEntity.ok(sujetStageRepository.save(sujet));
    }

    @PutMapping("/{id}/refuser")
    public ResponseEntity<SujetStage> refuser(@PathVariable Long id) {
        Optional<SujetStage> sujetOpt = sujetStageRepository.findById(id);
        if (sujetOpt.isEmpty()) return ResponseEntity.notFound().build();
        SujetStage sujet = sujetOpt.get();
        sujet.setStatut(Statut.REFUSE);
        sujet.setDateValidation(LocalDateTime.now());
        return ResponseEntity.ok(sujetStageRepository.save(sujet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSujet(@PathVariable Long id) {
        if (sujetStageRepository.existsById(id)) {
            sujetStageRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
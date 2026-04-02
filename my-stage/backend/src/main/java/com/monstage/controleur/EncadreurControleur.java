package com.monstage.controleur;

import com.monstage.dto.RequeteEncadreur;
import com.monstage.modele.Encadreur;
import com.monstage.repository.EncadreurRepository;
import com.monstage.repository.EntrepriseRepository;
import com.monstage.repository.StageRepository;
import com.monstage.repository.ConventionStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/encadreurs")
@CrossOrigin(origins = "*")
public class EncadreurControleur {

    @Autowired
    private EncadreurRepository encadreurRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private ConventionStageRepository conventionRepository;

    @GetMapping
    public List<Encadreur> getAllEncadreurs() {
        return encadreurRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Encadreur> getEncadreurById(@PathVariable Long id) {
        return encadreurRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/entreprise/{entrepriseId}")
    public List<Encadreur> getEncadreursByEntreprise(@PathVariable Long entrepriseId) {
        return encadreurRepository.findByEntrepriseId(entrepriseId);
    }

    @GetMapping("/stage/{stageId}")
    public List<Encadreur> getEncadreursByStage(@PathVariable Long stageId) {
        return encadreurRepository.findByStageId(stageId);
    }

    @PostMapping
    public Encadreur createEncadreur(@RequestBody RequeteEncadreur requete) {
        Encadreur encadreur = new Encadreur();
        encadreur.setNom(requete.getNom());
        encadreur.setPrenom(requete.getPrenom());
        encadreur.setFonction(requete.getFonction());
        encadreur.setEmail(requete.getEmail());
        encadreur.setTelephone(requete.getTelephone());
        encadreur.setEstEncadrantPrincipal(requete.getEstEncadrantPrincipal());
        
        entrepriseRepository.findById(requete.getIdEntreprise()).ifPresent(encadreur::setEntreprise);
        stageRepository.findById(requete.getIdStage()).ifPresent(encadreur::setStage);
        conventionRepository.findById(requete.getIdConvention()).ifPresent(encadreur::setConvention);
        
        return encadreurRepository.save(encadreur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Encadreur> updateEncadreur(@PathVariable Long id, @RequestBody RequeteEncadreur requete) {
        return encadreurRepository.findById(id).map(encadreur -> {
            encadreur.setNom(requete.getNom());
            encadreur.setPrenom(requete.getPrenom());
            encadreur.setFonction(requete.getFonction());
            encadreur.setEmail(requete.getEmail());
            encadreur.setTelephone(requete.getTelephone());
            encadreur.setEstEncadrantPrincipal(requete.getEstEncadrantPrincipal());
            return ResponseEntity.ok(encadreurRepository.save(encadreur));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEncadreur(@PathVariable Long id) {
        if (encadreurRepository.existsById(id)) {
            encadreurRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
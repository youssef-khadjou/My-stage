package com.monstage.controleur;

import com.monstage.modele.Professeur;
import com.monstage.repository.ProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/professeurs")
@CrossOrigin(origins = "*")
public class ProfesseurControleur {

    @Autowired
    private ProfesseurRepository professeurRepository;

    @GetMapping
    public List<Professeur> getAllProfesseurs() {
        return professeurRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professeur> getProfesseurById(@PathVariable Long id) {
        Optional<Professeur> professeur = professeurRepository.findById(id);
        return professeur.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<Professeur> getProfesseurByUtilisateurId(@PathVariable Long utilisateurId) {
        Optional<Professeur> professeur = professeurRepository.findByUtilisateurId(utilisateurId);
        return professeur.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/specialite/{specialite}")
    public List<Professeur> getProfesseursBySpecialite(@PathVariable String specialite) {
        return professeurRepository.findBySpecialite(specialite);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professeur> updateProfesseur(@PathVariable Long id, @RequestBody Professeur professeurDetails) {
        Optional<Professeur> professeurOpt = professeurRepository.findById(id);
        if (professeurOpt.isPresent()) {
            Professeur professeur = professeurOpt.get();
            professeur.setNom(professeurDetails.getNom());
            professeur.setPrenom(professeurDetails.getPrenom());
            professeur.setSpecialite(professeurDetails.getSpecialite());
            professeur.setBureau(professeurDetails.getBureau());
            professeur.setTelephone(professeurDetails.getTelephone());
            return ResponseEntity.ok(professeurRepository.save(professeur));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesseur(@PathVariable Long id) {
        if (professeurRepository.existsById(id)) {
            professeurRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
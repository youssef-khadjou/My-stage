package com.monstage.controleur;

import com.monstage.modele.Enseignant;
import com.monstage.repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enseignants")
@CrossOrigin(origins = "http://localhost:4200")
public class EnseignantControleur {

    @Autowired
    private EnseignantRepository enseignantRepository;

    @GetMapping
    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enseignant> getEnseignantById(@PathVariable Long id) {
        Optional<Enseignant> enseignant = enseignantRepository.findById(id);
        return enseignant.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<Enseignant> getEnseignantByUtilisateurId(@PathVariable Long utilisateurId) {
        Optional<Enseignant> enseignant = enseignantRepository.findByUtilisateurId(utilisateurId);
        return enseignant.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/specialite/{specialite}")
    public List<Enseignant> getEnseignantsBySpecialite(@PathVariable String specialite) {
        return enseignantRepository.findBySpecialite(specialite);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enseignant> updateEnseignant(@PathVariable Long id, @RequestBody Enseignant enseignantDetails) {
        Optional<Enseignant> enseignantOpt = enseignantRepository.findById(id);
        if (enseignantOpt.isPresent()) {
            Enseignant enseignant = enseignantOpt.get();
            enseignant.setNom(enseignantDetails.getNom());
            enseignant.setPrenom(enseignantDetails.getPrenom());
            enseignant.setSpecialite(enseignantDetails.getSpecialite());
            enseignant.setBureau(enseignantDetails.getBureau());
            enseignant.setTelephone(enseignantDetails.getTelephone());
            return ResponseEntity.ok(enseignantRepository.save(enseignant));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnseignant(@PathVariable Long id) {
        if (enseignantRepository.existsById(id)) {
            enseignantRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
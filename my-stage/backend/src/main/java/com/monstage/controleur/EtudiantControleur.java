package com.monstage.controleur;

import com.monstage.modele.Etudiant;
import com.monstage.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin(origins = "*")
public class EtudiantControleur {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @GetMapping
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        return etudiant.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/numero/{numeroEtudiant}")
    public ResponseEntity<Etudiant> getEtudiantByNumero(@PathVariable String numeroEtudiant) {
        Optional<Etudiant> etudiant = etudiantRepository.findByNumeroEtudiant(numeroEtudiant);
        return etudiant.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<Etudiant> getEtudiantByUtilisateurId(@PathVariable Long utilisateurId) {
        Optional<Etudiant> etudiant = etudiantRepository.findByUtilisateurId(utilisateurId);
        return etudiant.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filiere/{filiere}")
    public List<Etudiant> getEtudiantsByFiliere(@PathVariable String filiere) {
        return etudiantRepository.findByFiliere(filiere);
    }

    @GetMapping("/niveau/{niveau}")
    public List<Etudiant> getEtudiantsByNiveau(@PathVariable String niveau) {
        return etudiantRepository.findByNiveau(niveau);
    }

    @GetMapping("/annee/{anneeScolaire}")
    public List<Etudiant> getEtudiantsByAnneeScolaire(@PathVariable String anneeScolaire) {
        return etudiantRepository.findByAnneeScolaire(anneeScolaire);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiantDetails) {
        Optional<Etudiant> etudiantOpt = etudiantRepository.findById(id);
        if (etudiantOpt.isPresent()) {
            Etudiant etudiant = etudiantOpt.get();
            if (etudiantDetails.getNumeroEtudiant() != null) {
                etudiant.setNumeroEtudiant(etudiantDetails.getNumeroEtudiant());
            }
            if (etudiantDetails.getFiliere() != null) {
                etudiant.setFiliere(etudiantDetails.getFiliere());
            }
            if (etudiantDetails.getNiveau() != null) {
                etudiant.setNiveau(etudiantDetails.getNiveau());
            }
            if (etudiantDetails.getAnneeScolaire() != null) {
                etudiant.setAnneeScolaire(etudiantDetails.getAnneeScolaire());
            }
            if (etudiantDetails.getCvPath() != null) {
                etudiant.setCvPath(etudiantDetails.getCvPath());
            }
            if (etudiantDetails.getLettreMotivationPath() != null) {
                etudiant.setLettreMotivationPath(etudiantDetails.getLettreMotivationPath());
            }
            return ResponseEntity.ok(etudiantRepository.save(etudiant));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/cv")
    public ResponseEntity<Etudiant> updateCv(@PathVariable Long id, @RequestBody Map<String, String> cvInfo) {
        Optional<Etudiant> etudiantOpt = etudiantRepository.findById(id);
        if (etudiantOpt.isPresent()) {
            Etudiant etudiant = etudiantOpt.get();
            etudiant.setCvPath(cvInfo.get("cvPath"));
            return ResponseEntity.ok(etudiantRepository.save(etudiant));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/lettre-motivation")
    public ResponseEntity<Etudiant> updateLettreMotivation(@PathVariable Long id, @RequestBody Map<String, String> lettreInfo) {
        Optional<Etudiant> etudiantOpt = etudiantRepository.findById(id);
        if (etudiantOpt.isPresent()) {
            Etudiant etudiant = etudiantOpt.get();
            etudiant.setLettreMotivationPath(lettreInfo.get("lettreMotivationPath"));
            return ResponseEntity.ok(etudiantRepository.save(etudiant));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        if (etudiantRepository.existsById(id)) {
            etudiantRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
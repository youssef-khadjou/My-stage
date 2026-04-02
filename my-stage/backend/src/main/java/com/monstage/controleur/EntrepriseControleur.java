package com.monstage.controleur;

import com.monstage.modele.Entreprise;
import com.monstage.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/entreprises")
@CrossOrigin(origins = "*")
public class EntrepriseControleur {

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @GetMapping
    public List<Entreprise> getAllEntreprises() {
        return entrepriseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entreprise> getEntrepriseById(@PathVariable Long id) {
        Optional<Entreprise> entreprise = entrepriseRepository.findById(id);
        return entreprise.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/siret/{siret}")
    public ResponseEntity<Entreprise> getEntrepriseBySiret(@PathVariable String siret) {
        Optional<Entreprise> entreprise = entrepriseRepository.findBySiret(siret);
        return entreprise.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nom/{nomEntreprise}")
    public ResponseEntity<Entreprise> getEntrepriseByNom(@PathVariable String nomEntreprise) {
        Optional<Entreprise> entreprise = entrepriseRepository.findByNomEntreprise(nomEntreprise);
        return entreprise.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<Entreprise> getEntrepriseByUtilisateurId(@PathVariable Long utilisateurId) {
        Optional<Entreprise> entreprise = entrepriseRepository.findByUtilisateurId(utilisateurId);
        return entreprise.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/secteur/{secteurActivite}")
    public List<Entreprise> getEntreprisesBySecteur(@PathVariable String secteurActivite) {
        return entrepriseRepository.findBySecteurActivite(secteurActivite);
    }

    @GetMapping("/ville/{ville}")
    public List<Entreprise> getEntreprisesByVille(@PathVariable String ville) {
        return entrepriseRepository.findByVille(ville);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createEntreprise(@RequestBody Entreprise entreprise) {
        if (entrepriseRepository.existsBySiret(entreprise.getSiret())) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Une entreprise avec ce SIRET existe déjà");
            return ResponseEntity.badRequest().body(error);
        }
        entrepriseRepository.save(entreprise);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Entreprise créée avec succès");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entreprise> updateEntreprise(@PathVariable Long id, @RequestBody Entreprise entrepriseDetails) {
        Optional<Entreprise> entrepriseOpt = entrepriseRepository.findById(id);
        if (entrepriseOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Entreprise entreprise = entrepriseOpt.get();
        entreprise.setSiret(entrepriseDetails.getSiret());
        entreprise.setNomEntreprise(entrepriseDetails.getNomEntreprise());
        entreprise.setAdresse(entrepriseDetails.getAdresse());
        entreprise.setVille(entrepriseDetails.getVille());
        entreprise.setCodePostal(entrepriseDetails.getCodePostal());
        entreprise.setSecteurActivite(entrepriseDetails.getSecteurActivite());
        entreprise.setSiteWeb(entrepriseDetails.getSiteWeb());
        entreprise.setTelephoneContact(entrepriseDetails.getTelephoneContact());
        entreprise.setNomContact(entrepriseDetails.getNomContact());
        
        return ResponseEntity.ok(entrepriseRepository.save(entreprise));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntreprise(@PathVariable Long id) {
        if (entrepriseRepository.existsById(id)) {
            entrepriseRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
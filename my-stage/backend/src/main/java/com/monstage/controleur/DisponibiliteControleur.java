package com.monstage.controleur;

import com.monstage.modele.Disponibilite;
import com.monstage.modele.Utilisateur;
import com.monstage.repository.DisponibiliteRepository;
import com.monstage.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/disponibilites")
@CrossOrigin(origins = "http://localhost:4200")
public class DisponibiliteControleur {

    @Autowired
    private DisponibiliteRepository disponibiliteRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/utilisateur/{utilisateurId}")
    public List<Disponibilite> getDisponibilites(@PathVariable Long utilisateurId) {
        return disponibiliteRepository.findByUtilisateurIdOrderByDateAscHeureDebutAsc(utilisateurId);
    }

    @PostMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<Disponibilite> ajouterDisponibilite(
            @PathVariable Long utilisateurId,
            @RequestBody Map<String, String> body) {

        Optional<Utilisateur> userOpt = utilisateurRepository.findById(utilisateurId);
        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();

        Disponibilite d = new Disponibilite();
        d.setDate(LocalDate.parse(body.get("date")));
        d.setHeureDebut(LocalTime.parse(body.get("heureDebut")));
        d.setHeureFin(LocalTime.parse(body.get("heureFin")));
        d.setUtilisateur(userOpt.get());

        return ResponseEntity.ok(disponibiliteRepository.save(d));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerDisponibilite(@PathVariable Long id) {
        if (!disponibiliteRepository.existsById(id)) return ResponseEntity.notFound().build();
        disponibiliteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}